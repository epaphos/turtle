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
}
