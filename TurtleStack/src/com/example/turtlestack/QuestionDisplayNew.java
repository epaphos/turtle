package com.example.turtlestack;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class QuestionDisplayNew extends Activity {

	private Myadapter adap;
	private ListView lstview;
	private AnswerDataSource as;
	private ArrayList<Answer> answerList;
	private UserDataSource us;
	private ArrayList<User> userList;
	private QuestionDataSource qs;
	private Question question;
	private int questionId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_display_new);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		//get Intent and fill ArrayLists
		Intent intent = getIntent();
		questionId = intent.getIntExtra("questionId", 0);
		
		fillQuestion(questionId);
		fillAnswerList(questionId);
		fillUserList(answerList);
		
		lstview = (ListView) findViewById(R.id.listViewQuestionAnswer);
		lstview.setDrawingCacheEnabled(false);
		adap = new Myadapter(this, question, answerList, userList);
		lstview.setAdapter(adap);
		
	}

	private void fillQuestion(int id){
		qs = QuestionDataSource.getInstance(this);
		qs.open();
		try{
			question = qs.getQuestion(id);
		} catch (Exception e) {
			Log.v("Exception","Wasn't able to get Question with id:"+id);
		}
	}
	private void fillAnswerList(int id){
		
		if(qs.getNumberOfAnswers(id)>0){
			as = AnswerDataSource.getInstance(this);
			as.open();
			answerList = as.getAnswers(id);
			as.close();
		}
		
	}
	private void fillUserList(ArrayList<Answer> lst){
		us = UserDataSource.getInstance(this);
		us.open();
		userList = new ArrayList<User>();
		for (Answer answer : lst) {
			userList.add(us.readUser(answer.getOwnerUserId()));
		}
		us.close();
	}
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question_display_new, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
