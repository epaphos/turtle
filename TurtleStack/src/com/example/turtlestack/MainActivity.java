package com.example.turtlestack;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.v("troll", "Hej");
		TurtleSQLiteDatabase db = new TurtleSQLiteDatabase(this);
		Log.v("question", db.getQuestion(8414075).getTitle());
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public Question getQuestion(int id) {
		TurtleSQLiteDatabase db = new TurtleSQLiteDatabase(this);
		db.createDatabase();
		db.openDataBase();
		Cursor cursor = db.query("posts", new String[] {"title"}, "id = ?", 
				new String [] {String.valueOf(id)}, null, null, null, null);
		return new Question(cursor.getString(0));
		 
	}
	

}
