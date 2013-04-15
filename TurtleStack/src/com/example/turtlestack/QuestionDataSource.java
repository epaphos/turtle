package com.example.turtlestack;

import android.content.Context;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;


public class QuestionDataSource {
	TurtleSQLiteHelper helper;
	SQLiteDatabase database;
	
	public QuestionDataSource(Context context) {
		helper = new TurtleSQLiteHelper(context);
		helper.createDatabase();
	}
	
	public void open() throws SQLException {
		database = helper.openDataBase();
	}
	
	public void close() {
		helper.close();
	}
	
	public Question getQuestion(int id) {
		Cursor cursor = database.rawQuery("select * from posts where id = ?", new String[] { String.valueOf(id) });
		cursor.moveToFirst();
		return new Question(cursor.getString(cursor.getColumnIndex("title")));
	}

}
