package com.example.turtlestack;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TagDataSource implements DataSourceUtils {
	TurtleSQLiteHelper helper;
	protected SQLiteDatabase database;
	protected static TagDataSource instance = null;
	
	public TagDataSource(Context context) {
		helper = new TurtleSQLiteHelper(context);
		helper.createDatabase();
	}
	
	public static TagDataSource getInstance(Context context){
		if(instance == null) instance = new TagDataSource(context);
		return (TagDataSource) instance;
	}
		
	public void open() throws SQLException {
		database = helper.openDataBase();
	}
	
	public void close() {
		helper.close();
	}
	
	public ArrayList<Tag> getAllTheTags() {
		Cursor cursor = database.rawQuery("SELECT tag FROM tags", new String [] {});
		ArrayList<Tag> list = new ArrayList<Tag>();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			try {
				Tag tag = new Tag(cursor.getString (cursor.getColumnIndex("tag")));
				list.add(tag);
				Log.v("tag", tag.toString());
			} catch (Exception e) {
				// TODO: handle exception
			}
			cursor.moveToNext();			
		}
		return list;
	
	}
}
