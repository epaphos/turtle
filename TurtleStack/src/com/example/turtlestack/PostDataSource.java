package com.example.turtlestack;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class PostDataSource {
	TurtleSQLiteHelper helper;
	SQLiteDatabase database;
	
	public PostDataSource(Context context) {
		helper = new TurtleSQLiteHelper(context);
		helper.createDatabase();
	}
	
	public void open() throws SQLException {
		database = helper.openDataBase();
	}
	
	public void close() {
		helper.close();
	}
	
	public int newId() {
		Cursor cursor = database.rawQuery("select max(id) from posts", null);
		cursor.moveToFirst();
		int maxId = cursor.getInt(cursor.getColumnIndex("max(id)"));
		return maxId +1;
	}
	
	public Post getPost(int id) {
		
		Cursor cursor = database.rawQuery("select * from posts where id = ?", new String[] { String.valueOf(id) });
		cursor.moveToFirst();
		
		/*Extracting values
		 * 
		 */
		String title = cursor.getString(cursor.getColumnIndex("title"));
		int postTypeId = cursor.getInt(cursor.getColumnIndex("post_type_id")); 
		String creationDate = cursor.getString(cursor.getColumnIndex("creation_date"));
		int score = cursor.getInt(cursor.getColumnIndex("score"));
		String body = cursor.getString(cursor.getColumnIndex("body"));
		int ownerUserId =cursor.getInt(cursor.getColumnIndex("owner_user_id"));
		String communityOwnedDate = cursor.getString(cursor.getColumnIndex("community_owned_date"));
		String tags = cursor.getString(cursor.getColumnIndex("tags"));
		
		if (postTypeId == 1) {
			return new Question(title, id, postTypeId, creationDate, body, ownerUserId, tags);
		}
		else if (postTypeId == 2) {
			int parentId = cursor.getInt(cursor.getColumnIndex("parent_id"));
			return new Answer(id, postTypeId, creationDate, body, ownerUserId, parentId);
		}
		return null;
		//return new Post getTitle(cursor.getString(cursor.getColumnIndex("title")));
	}

	public boolean setPost(Post post) {
		if (post.getPostTypeId() == 2) {
			Answer answer = (Answer) post;
			ContentValues values = new ContentValues();
			values.put("id",answer.getId());
			values.put("post_type_id", answer.getPostTypeId());
			values.put("parent_id", answer.getParentId());
			values.put("creation_date",answer.getCreationDate());
			values.put("score", 0);
			values.put("view_count",0);
			values.put("body",answer.getBody());
			values.put("owner_user_id",answer.getOwnerUserId());
			values.put("last_editor_display_name", "NULL");
			values.put("last_edit_date", "NULL");
			values.put("last_activity_date", answer.getLastActivityDate());
			values.put("community_owned_date","NULL");
			values.put("closed_date","NULL");
			values.put("title","NULL");
			values.put("tags", "NULL");
			
	        database.insert("posts", "answer_count", values);
			//database.rawQuery("insert into posts values (?,?,null,?,0,0,?,?,?,null,'NULL','NULL',?,'NULL','NULL','NULL','NULL',null,null,null);",values);
			return true;
		}
		else if (post.getPostTypeId() == 1) {
			Question question = (Question) post;
			
			ContentValues values = new ContentValues();
			values.put("id",question.getId());
			values.put("post_type_id", question.getPostTypeId());
			values.put("creation_date",question.getCreationDate());
			values.put("score", 0);
			values.put("view_count",0);
			values.put("body",question.getBody());
			values.put("owner_user_id",question.getOwnerUserId());
			values.put("last_editor_display_name", "NULL");
			values.put("last_edit_date", "NULL");
			values.put("last_activity_date", question.getLastActivityDate());
			values.put("community_owned_date","NULL");
			values.put("closed_date","NULL");
			values.put("title",question.getTitle());
			values.put("tags",question.getTags());

	        database.insert("posts", "parent_id", values);
			return true;
		}
		
		return false;
	}
}
