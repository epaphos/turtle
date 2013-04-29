package com.example.turtlestack;

import java.util.ArrayList;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class QuestionDisplayActivity extends Activity implements OnItemClickListener {
	QuestionDataSource ds;
	AnswerDataSource as;
	int questionId;
	Question q; //The element that should be displayed
	private ListView lv;
	private ArrayList<Answer> answerList;

	@SuppressLint("NewApi")  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_question_display);
		Intent intent = getIntent();
		questionId = intent.getIntExtra("questionId", 0);
		as = AnswerDataSource.getInstance(this);			
		as.open();
		ds = QuestionDataSource.getInstance(this);
		ds.open();
		
		try {
	        q = ds.getQuestion(questionId);
		} 
		catch (Exception e) {
		}
		
		if(ds.getNumberOfAnswers(questionId) > 0) {
			answerList = as.getAnswers(questionId);
			ArrayList<String> listOfTitles = new ArrayList<String>();
			for (Answer answer : answerList) {
				listOfTitles.add(answer.getBody());
			}

			lv = (ListView) findViewById(android.R.id.list);
	        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_row, listOfTitles);
	        lv.setAdapter(arrayAdapter);
	        lv.setOnItemClickListener(this);
		}

		ds.close();
		as.close();
		displayQ(q);
	}

	public void goAnswerView(View v) {
		Intent i = new Intent(this,AnswerActivity.class);
		try {
			Question question = (Question) ds.getQuestion(questionId);
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
		lblId.setText(getString(R.string.post_id) + " " + Integer.toString(q.getId()));
		lblViews.setText(getString(R.string.post_number_of_views) + " " + Integer.toString(q.getViewCount()));

		TextView lblAuthor = (TextView) findViewById(R.id.quLblAuthor);
		lblAuthor.setText(getString(R.string.post_name_of_author) + " " + Integer.toString(q.getOwnerUserId()));
	}

	/**
	 * Views the details of the post author
	 * @param view
	 */
	public void gotoUserView(View view){
		Intent intent = new Intent(this, UserViewActivity.class);
		intent.putExtra("userId", q.getOwnerUserId()); //Sample Id which exists in database
		startActivity(intent);
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}




}
