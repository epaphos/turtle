package com.example.turtlestack;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionDisplayActivity extends Activity {
	PostDataSource ds;
	QuestionDataSource qs;
	int questionId;
	Question q; //The element that should be displayed
	
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
		qs = QuestionDataSource.getInstance(this);
		qs.open();
		//q = qs.getQuestionDummy(questionId);
        q = qs.getQuestion(questionId);
		//Question q = QuestionDataSource.getQuestionDummy(5);
        //q = new Question("Title bla","Body bla ","Tag bla");
        //q.setId(123);
		
		Button postButton = (Button) findViewById(R.id.quBtnAnswer);		
		postButton.setOnClickListener(listener);
		//ds.close();
		qs.close();
		 // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        displayQ(q);
	}
	
	
	View.OnClickListener listener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//answerAction(v);
			TextView lblTitle = (TextView) findViewById(R.id.quLblTitle);
			TextView lblBody = (TextView) findViewById(R.id.quLblBody);
			lblTitle.setText(q.getTitle());
			lblBody.setText("some stupid");
			
		}
		};
	
	public void answerAction(View v) {
		Intent i = new Intent(this,AnswerActivity.class);
		Question question = (Question) ds.readPost(questionId);
		i.putExtra("parentId",question.getId());		
		startActivity(i);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question_display, menu);
		return true;
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
		
	}
}