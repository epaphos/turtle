package com.example.turtlestack;

import java.util.ArrayList;

import android.content.Context;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;


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
	
	public Post getPost(int id) {
		Cursor cursor = database.rawQuery("select * from posts where id = ?", new String[] { String.valueOf(id) });
		cursor.moveToFirst();
		return new Post(cursor.getString(cursor.getColumnIndex("title")));
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
