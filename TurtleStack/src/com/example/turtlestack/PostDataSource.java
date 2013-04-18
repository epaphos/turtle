package com.example.turtlestack;

import java.util.ArrayList;
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
	
	public Post readPost(int id) {
		
		Cursor cursor = database.rawQuery("select * from posts where id = ?", new String[] { String.valueOf(id) });
		cursor.moveToFirst();
		
		/*Extracting values
		 * 
		 */
		/**
		 * @param id
		 * @param postTypeId
		 * @param creationDate
		 * @param score
		 * @param body
		 * @param ownerUserId
		 * @param lastEditorUserId
		 * @param lastEditorUserName
		 * @param lastEditDate
		 * @param lastActivityDate
		 * @param communityOwnedDate
		 * @param closedDate
		 * @param commentCount
		 */
		int postTypeId = cursor.getInt(cursor.getColumnIndex("post_type_id"));
		int parentId = cursor.getInt(cursor.getColumnIndex("parent_id"));
		int acceptedAnswer = cursor.getInt(cursor.getColumnIndex("accepted_answer_id"));
		String creationDate = cursor.getString(cursor.getColumnIndex("creation_date"));
		int score = cursor.getInt(cursor.getColumnIndex("score"));
		int viewCount = cursor.getInt(cursor.getColumnIndex("view_count"));
		String body = cursor.getString(cursor.getColumnIndex("body"));
		int ownerUserId = cursor.getInt(cursor.getColumnIndex("owner_user_id"));
		int lastEditorUserId = cursor.getInt(cursor.getColumnIndex("last_editor_user_id"));
		String lastEditorUserName = cursor.getString(cursor.getColumnIndex("last_editor_display_name"));
		String lastEditDate = cursor.getString(cursor.getColumnIndex("last_edit_date"));
		String lastActivityDate = cursor.getString(cursor.getColumnIndex("last_activity_date"));
		String communityOwnedDate = cursor.getString(cursor.getColumnIndex("community_owned_date"));
		String closedDate = cursor.getString(cursor.getColumnIndex("closed_date"));
		String title = cursor.getString(cursor.getColumnIndex("title")); 
		String tags = cursor.getString(cursor.getColumnIndex("tags"));
		int answerCount = cursor.getInt(cursor.getColumnIndex("answer_count"));
		int commentCount = cursor.getInt(cursor.getColumnIndex("comment_count"));
		int favoriteCount = cursor.getInt(cursor.getColumnIndex("favorite_count"));
		
		if (postTypeId == 1) {
			return new Question(
					this,
					id,
					postTypeId,
					creationDate,
					score,
					body,
					ownerUserId,
					lastEditorUserId,
					lastEditorUserName,
					lastEditDate,
					lastActivityDate,
					communityOwnedDate,
					closedDate,
					commentCount,
					acceptedAnswer,
					viewCount,
					title,
					tags,
					answerCount,
					favoriteCount
			);
		}
		else if (postTypeId == 2) {
			return new Answer(
					this,
					id,
					postTypeId,
					creationDate,
					score,
					body,
					ownerUserId,
					lastEditorUserId,
					lastEditorUserName,
					lastEditDate,
					lastActivityDate,
					communityOwnedDate,
					closedDate,
					commentCount,
					parentId
			);
		}
		return null;
		//return new Post getTitle(cursor.getString(cursor.getColumnIndex("title")));
	}

	public boolean writePost(Post post) {

		if (post instanceof Answer) {
			Answer answer = (Answer) post;
			ContentValues values = new ContentValues();
			
			values.put("id",answer.getId());
			values.put("post_type_id", answer.getPostTypeId());
			values.put("parent_id", answer.getParentId());
			values.put("creation_date",answer.getCreationDate());
			values.put("score", answer.getScore());
			values.put("view_count",0);
			values.put("body",answer.getBody());
			values.put("owner_user_id",answer.getOwnerUserId());
			//values.put("last_editor_user_id", answer.getLastEditorUserId());
			values.put("last_editor_display_name", answer.getLastEditorUserName());
			values.put("last_edit_date", answer.getLastEditDate());
			values.put("last_activity_date", answer.getLastActivityDate());
			values.put("community_owned_date",answer.getCommunityOwnedDate());
			values.put("closed_date",answer.getClosedDate());
			values.put("title","NULL");
			values.put("tags", "NULL");
			values.put("comment_count",answer.getCommentCount());
			//Log.v("LOG",answer.toString());
			//The following attributes are empty and only belong to question
			//accepted_answer_id
			//answer_count
			//favorite_count
	        database.insert("posts", "answer_count", values);
			return true;
		}
		else if (post instanceof Question) {
			Question question = (Question) post;	
			ContentValues values = new ContentValues();
			
			values.put("id",question.getId());
			values.put("post_type_id", question.getPostTypeId());
			values.put("accepted_answer_id", question.getAcceptedAnswer());
			values.put("creation_date",question.getCreationDate());
			values.put("score", question.getScore());
			values.put("view_count",question.getViewCount());
			values.put("body",question.getBody());
			values.put("owner_user_id",question.getOwnerUserId());
			values.put("last_editor_user_id", question.getLastEditorUserId());
			values.put("last_editor_display_name", question.getLastEditorUserName());
			values.put("last_edit_date", question.getLastEditDate());
			values.put("last_activity_date", question.getLastActivityDate());
			values.put("community_owned_date",question.getCommunityOwnedDate());
			values.put("closed_date",question.getClosedDate());
			values.put("title",question.getTitle());
			values.put("tags", question.getTags());
			values.put("answer_count", question.getAnswerCount());
			values.put("comment_count",question.getCommentCount());
			values.put("favorite_count", question.getFavoriteCount());
			//The following attributes are empty and only belong to answer
			//parent_id
			
	        database.insert("posts", "parent_id", values);
			return true;
		}
		
		return false;
	}
	
	public ArrayList<Post> getRecentPosts(int numberOfPosts) {
		//Cursor cursor = database.rawQuery("SELECT * FROM posts p WHERE p.creation_date = (SELECT (creation_date)) LIMIT ?", new String [] {String.valueOf(numberOfPosts)}) ;
		Cursor cursor = database.rawQuery("SELECT id, post_type_id, title, creation_date FROM posts where post_type_id=1 ORDER BY creation_date DESC LIMIT ?", new String [] {String.valueOf(numberOfPosts)});
		ArrayList<Post> list = new ArrayList<Post>();
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			list.add(new Post(cursor.getString(cursor.getColumnIndex("title"))));
			cursor.moveToNext();			
		}
	
		return list;
	}
}
