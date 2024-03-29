package com.example.turtlestack;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class QuestionActivity extends Activity {
	QuestionDataSource ds;
	UserDataSource us; 
	
	@SuppressLint("NewApi")   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_act);       
    }
	
	public void postQuestionButton(View v) {
        ds = QuestionDataSource.getInstance(this);
        us = UserDataSource.getInstance(this);
		ds.open();
		us.open();
		EditText mEdit; 
    	mEdit = (EditText) findViewById(R.id.title);
    	String title  = mEdit.getText().toString();
    	mEdit = (EditText) findViewById(R.id.text);
    	String body  = mEdit.getText().toString();
    	mEdit = (EditText) findViewById(R.id.tags);
    	String tags  = mEdit.getText().toString();
    	User user = us.getDummyUser();
    	Question question = new Question(title, body,tags, user.getUserId());
		ds.write(question);
        ds.close();
        us.close();
		back(v);
	}
	
	public void back(View v) {
		Intent i = new Intent(this,BrowseActivity.class);
		startActivity(i);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    
}
