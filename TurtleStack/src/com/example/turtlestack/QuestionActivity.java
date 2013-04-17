package com.example.turtlestack;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QuestionActivity extends Activity {

	@SuppressLint("NewApi")   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_act);
		Button postButton = (Button) findViewById(R.id.button1);
		postButton.setOnClickListener(postButtonListener);
     // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        
    }
	View.OnClickListener postButtonListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			EditText mEdit; 
	    	mEdit = (EditText) findViewById(R.id.title);
	    	String title  = mEdit.getText().toString();
	    	mEdit = (EditText) findViewById(R.id.text);
	    	String body  = mEdit.getText().toString();
	    	mEdit = (EditText) findViewById(R.id.tags);
	    	String tags  = mEdit.getText().toString();
	    	Question question = new Question(title, body,tags);
		}
	};
	
	
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
