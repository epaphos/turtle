package com.example.turtlestack;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

public class QuestionDataSource extends PostDataSource {

	public QuestionDataSource(Context context) {
		super(context);		
	}
	
	public ArrayList<Question> getRecentQuestions(int numberOfPosts) {
		Cursor cursor = database.rawQuery("SELECT id, post_type_id, title, body, tags, creation_date FROM posts where post_type_id=1 ORDER BY creation_date DESC LIMIT ?", new String [] {String.valueOf(numberOfPosts)});
		ArrayList<Question> list = new ArrayList<Question>();
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			list.add(new Question(cursor.getString(cursor.getColumnIndex("title")), 
					cursor.getString(cursor.getColumnIndex("body")),
					cursor.getString(cursor.getColumnIndex("tags"))));
			cursor.moveToNext();			
		}
		return list;
	}
}
