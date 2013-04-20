package com.example.turtlestack;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button postButton = (Button) findViewById(R.id.button1);		
		Button recentQuestionsButton = (Button) findViewById(R.id.recentquestions);
		Button viewQuestionDetails = (Button) findViewById(R.id.button3);
		postButton.setOnClickListener(listener);
		recentQuestionsButton.setOnClickListener(listener);
		//viewQuestionDetails.setOnClickListener(listener);
		}

		View.OnClickListener listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Button clickedButton = (Button) v;
				switch(clickedButton.getId()) {
				case R.id.button1 :
					launch(v, QuestionActivity.class);
					break;
				case R.id.recentquestions : 
					testUser();
					launch(v, BrowseActivity.class);
					break;
				//case R.id.button3 :
				//	launch(v, QuestionDisplayActivity.class);
				//	break;
				}
				
			}
			
			
		};
	
	public void launch(View v, Class<?> c) {
		Intent i = new Intent(this, c);
		startActivity(i);
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void testUser(){
		UserDataSource ds = new UserDataSource(this);
		ds.open();
		User u = ds.readUser(40310);
		Log.i("User",u.toString());
	}
	/*public void sendMessage(View view) {
		Intent intent = new Intent(this, QuestionActivity.class);
		EditText editText = (EditText) findViewById(R.id.text);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}*/
	
	/*
	//Function that gets called by button2
	public void gotoPostMessage(View view){
		Intent intent = new Intent(this, QuestionActivity.class);
		startActivity(intent);
	}
	//Function that gets called by button4
	public void gotoDetailedQuestion(View view){
		Intent intent = new Intent(this, QuestionDisplay.class);
		startActivity(intent);
	}*/
	
	public void showQuestionDetails(View view){
		Intent intent = new Intent(this, QuestionDisplayActivity.class);
		intent.putExtra("questionId", 8414099); //Sample Id which exists in database
		startActivity(intent);
	}
	
	public void showUserDetails(View view){
		Intent intent = new Intent(this, UserViewActivity.class);
		intent.putExtra("userId", 40310); //Sample Id which exists in database
		startActivity(intent);
	}
}
	
