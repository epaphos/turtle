package com.example.turtlestack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TurtleSQLiteDatabase extends SQLiteOpenHelper {

	private static String DB_PATH="/databases/";
	private static String DB_NAME="so.sqlite";
	
	private SQLiteDatabase database;
	
	
	public TurtleSQLiteDatabase (Context context) {
		super(context, DB_NAME, null, 1);
	}
	
	public TurtleSQLiteDatabase(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
