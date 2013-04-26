package com.example.turtlestack;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionDisplayActivity extends Activity {
	QuestionDataSource questionSource;
	int questionId;
	Question question; //The element that should be displayed
	UserDataSource userSource;
	String author;
	
	@SuppressLint("NewApi")  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_question_display);
        //Bundle bundle = getIntent().getExtras();
        //int questionId =  bundle.getInt("Id");
		Intent intent = getIntent();
		questionId = intent.getIntExtra("questionId", 0);
        //ds = PostDataSource.getInstance(this);
		//ds.open();
		questionSource = QuestionDataSource.getInstance(this);
		questionSource.open();
		
		//q = qs.getQuestionDummy(questionId);
		try {
	        question = questionSource.getQuestion(questionId);
		} catch (Exception e) {
			//TODO we should do something here
		}
		//Question q = QuestionDataSource.getQuestionDummy(5);
        //q = new Question("Title bla","Body bla ","Tag bla");
        //q.setId(123);
		
		Button postButton = (Button) findViewById(R.id.quBtnAnswer);		
		postButton.setOnClickListener(listener);
		//ds.close();
		questionSource.close();
		 // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        getUserString();
        displayQ(question);
	}
	
	
	View.OnClickListener listener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//answerAction(v);
			TextView lblTitle = (TextView) findViewById(R.id.quLblTitle);
			TextView lblBody = (TextView) findViewById(R.id.quLblBody);
			lblTitle.setText(question.getTitle());
			lblBody.setText("some stupid");
			
			}
		};
	
	public void answerAction(View v) {
		Intent i = new Intent(this,AnswerActivity.class);
		try {
			Question question = (Question) questionSource.getQuestion(questionId);
			i.putExtra("parentId",question.getId());		
		} catch (Exception e) {
			Log.v("EXCEPTION", "Post type is not as expected");
		}
		startActivity(i);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question_display, menu);
		return true;
	}
	
	private void getUserString(){
		userSource = UserDataSource.getInstance(this);
        userSource.open();
        try {
        	User user = userSource.readUser(question.getOwnerUserId());
        	author = user.getDisplayName();
        } catch (Exception e) {
        	author = " id: " + Integer.toString(question.getOwnerUserId()) + " not found";
        }
        userSource.close();
	}
	/**
	 * Extracts members of a Question and displays them
	 * @param q
	 */	
	public void displayQ(Question q){
		TextView lblTitle = (TextView) findViewById(R.id.quLblTitle);
		TextView lblBody = (TextView) findViewById(R.id.quLblBody);
		TextView lblId = (TextView) findViewById(R.id.quLblId);
		TextView lblViews = (TextView) findViewById(R.id.quLblViewCount);
		lblTitle.setText(q.getTitle());
		lblBody.setText(q.getBody());
		lblId.setText("ID: " + Integer.toString(q.getId()));//setText must receive a string!
		lblViews.setText("Views: " + Integer.toString(q.getViewCount()));
		
		TextView lblAuthor = (TextView) findViewById(R.id.quLblAuthor);
		
			lblAuthor.setText("Author: " + author);
		
			
	}
	
	/**
	 * Views the details of the post author
	 * @param view
	 */
	public void gotoUserView(View view){
		Intent intent = new Intent(this, UserViewActivity.class);
		intent.putExtra("userId", question.getOwnerUserId()); //Sample Id which exists in database
		startActivity(intent);
	}
	
	
	
	
}