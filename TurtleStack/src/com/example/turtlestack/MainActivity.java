package com.example.turtlestack;



import java.util.Map;
import java.util.TreeMap;


import android.app.ActionBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		createInstances();

		ActionBar actionbar = getActionBar();
		actionbar.hide();

		//Add listener to Buttons
		Button postButton = (Button) findViewById(R.id.buttonpost);		
		Button recentQuestionsButton = (Button) findViewById(R.id.recentquestions);
		postButton.setOnClickListener(listener);
		recentQuestionsButton.setOnClickListener(listener);
		}

		
		View.OnClickListener listener = new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Button clickedButton = (Button) v;
				switch(clickedButton.getId()) {
				case R.id.buttonpost :
					launch(v, QuestionActivity.class);
					break;
				case R.id.recentquestions : 
					launch(v, BrowseActivity.class);
					break;
				}				
			}			
		};
	
	public void launch(View v, Class<?> c) {
		Intent i = new Intent(this, c);
		startActivity(i);
	}
	
	public void createInstances () {
		TagCloud tc = TagCloud.getInstance(this);
		AnswerDataSource as = AnswerDataSource.getInstance(this);
		QuestionDataSource qs = QuestionDataSource.getInstance(this);
		TagDataSource ts = TagDataSource.getInstance(this);
		UserDataSource us = UserDataSource.getInstance(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void goTagList(View view){
		Intent intent = new Intent(this, TagsListActivity.class);
		startActivity(intent);
	}
	
	
	
	public void showTagNavigation (View view) {
		Intent intent = new Intent(this, TagCloudActivity.class);
		intent.putExtra("mainTag", ".htaccess");
		startActivity(intent);
	}
	/**
	 * Test method for showing user details
	 * @param view
	 */
	public void showUserDetails(View view){
		Intent intent = new Intent(this, UserViewActivity.class);
		intent.putExtra("userId", 40310); //Sample Id which exists in database
		startActivity(intent);
	}
	
	public void startSearchActivity(View view) {
		Intent intent = new Intent(this, SearchActivity.class);
		startActivity(intent);
	}
	
	public void newmainact(View view){
		Intent intent = new Intent(this, StartActivity.class);
		startActivity(intent);
	}
}
	
