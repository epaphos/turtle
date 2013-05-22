package com.example.turtlestack;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class QuestionDisplayActivity extends Activity {

	private Myadapter adap;
	private ListView lstview;
	private AnswerDataSource as;
	private ArrayList<Post> answerList;
	private UserDataSource us;
	private ArrayList<User> userList;
	private QuestionDataSource qs;
	private Question question;
	private int questionId;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_display);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		//get Intent and fill ArrayLists
		Intent intent = getIntent();
		questionId = intent.getIntExtra("questionId", 0);
		
		us = UserDataSource.getInstance(this);
		as = AnswerDataSource.getInstance(this);
		qs = QuestionDataSource.getInstance(this);
		
		fillQuestion(questionId);
		fillAnswerList(question);
		fillUserList(answerList);
		
		lstview = (ListView) findViewById(R.id.listViewQuestionAnswer);
		lstview.setDrawingCacheEnabled(false);
		adap = new Myadapter(this, answerList, userList);
		
		View v = getLayoutInflater().inflate(R.layout.footer_layout, null);
        lstview.addFooterView(v);		
        lstview.setAdapter(adap);
        
        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
		
	}
	public void postAnswerButton(View v) {
    	EditText mEdit = (EditText) findViewById(R.id.answerText);
    	String body  = mEdit.getText().toString();
    	us.open();
    	as.open();
		Answer answer = new Answer(questionId, body, us.getDummyUser().getUserId());
		int answerId = as.setAnswer(answer);
		try {
			Log.v("HERE","HERE");
			Question question = qs.getQuestion(questionId);
			question.setAnswerCount(question.getAnswerCount() +1);
			qs.setQuestion(question);
		} catch (wrongTypeException e) { }
		if (question.getAnswerCount() == 0) as.addAnswerToRT(questionId, answerId);
		us.close();	
		as.close();
		finish();
		startActivity(getIntent());
	}
	
	public void back(View v) {
		Intent i = new Intent(this,QuestionDisplayActivity.class);
		startActivity(i);
	}

	private void fillQuestion(int id){
		qs.open();
		try{
			question = qs.getQuestion(id);
		} catch (Exception e) {
			Log.v("Exception","Wasn't able to get Question with id:"+id);
		}
		qs.close();
	}
	private void fillAnswerList(Question q){
		us.open();
		int id = q.getId();
		answerList = new ArrayList<Post>();
		answerList.add(q);
		if(qs.getNumberOfAnswers(id)>0){
			as = AnswerDataSource.getInstance(this);
			as.open();
			ArrayList<Answer> answers = as.getSortedAnswers(id);
			as.close();
			for (Answer answer : answers) {
				answerList.add(answer);
			}
		}
		answerList = sort(answerList);
		us.close();
	}
	
	public ArrayList<Post> sort(ArrayList<Post> list){
		Question q = (Question) list.get(0);
		int acceptedAnswer;
		ArrayList<Post> sorting = list;
		if (q.getAcceptedAnswer()>0) {
			acceptedAnswer = q.getAcceptedAnswer();
			for (Post post : sorting) {
				if(post.getId()==acceptedAnswer) {
					sorting.remove(post);
					sorting.add(1, post);
					break;
				}
			}		
		}
		return sorting;
	}
	private void fillUserList(ArrayList<Post> lst){
		
		us.open();
		userList = new ArrayList<User>();
		for (Post answer : lst) {
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
	
	public void voteUp(View view){
		
		if (view.findViewById(R.id.btnQuestionVoteUp) != null)
			Log.v("Adapter", "Voteup Question  clicked");
		if (view.findViewById(R.id.btnVoteDown) != null)
			Log.v("Adapter", "Voteup for Answer clicked");
			
	}
}
