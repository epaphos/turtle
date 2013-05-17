package com.example.turtlestack;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class BrowseActivity extends ListActivity implements OnItemClickListener, OnQueryTextListener{
	QuestionDataSource ds;
	
	private ListView lv;
	private ArrayList<Question> questionList;
	MyBrowseAdapter arrayAdapter; 
	        
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse);
		createInstances();
		ds = QuestionDataSource.getInstance(this);
		ds.open();
//		Log.v("QUESTIONS", ds.searchQuestionByTags("<.net>").toString());
		Intent intent = getIntent();
		String tags = intent.getStringExtra("tagList");
		if (tags != null) questionList = ds.searchQuestionByTags(tags);
		else questionList = ds.getRecentQuestions(10);
		
		lv = (ListView) findViewById(android.R.id.list);
        arrayAdapter = new MyBrowseAdapter(this, questionList);
        
        lv.setAdapter(arrayAdapter);
        ds.close();
        lv.setOnItemClickListener(this);
	}

	//Start new activity with question
	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
		Question q = questionList.get(position);
		int questionId = q.getId();
        Log.i("Question", "Question with id " + q.getId() + " will be displayed");
        //Intent intent = new Intent(this, QuestionDisplayActivity.class);
        Intent intent = new Intent(this, QuestionDisplayActivity.class);
        intent.putExtra("questionId", questionId);
        startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse, menu);
		
		// Get the SearchView and set the searchable configuration
	    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView = (SearchView) menu.findItem(R.id.action_settings).getActionView();
	    
	    // Assumes current activity is the searchable activity
	    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
	    searchView.setOnQueryTextListener(this);
	    //searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
	    //searchView.setSubmitButtonEnabled(true);
	    
		return true;
	}
	
	public void createInstances () {
		TagCloud tc = TagCloud.getInstance(this);
		AnswerDataSource as = AnswerDataSource.getInstance(this);
		QuestionDataSource qs = QuestionDataSource.getInstance(this);
		TagDataSource ts = TagDataSource.getInstance(this);
		UserDataSource us = UserDataSource.getInstance(this);
	}
	
	public void postQuestion(View v) {
		Intent i = new Intent(this, QuestionActivity.class);
		startActivity(i);
	}
	
	public void viewTagScreen (View view) {
		Intent intent = new Intent(this, TagsListActivity.class);
		startActivity(intent);
	}
	
	public void viewUserProfile(View view){
		Intent intent = new Intent(this, UserViewActivity.class);
		UserDataSource us;
		us = UserDataSource.getInstance(this);
		us.open();
		User user = us.getDummyUser();
		us.close();
		int userId = user.getUserId();
		intent.putExtra("userId", userId); //Sample Id which exists in database
		startActivity(intent);
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		Log.v("Search", query);
		
		ds.open();
		
		questionList = ds.getSearchResults(query);		
		
		arrayAdapter = new MyBrowseAdapter(this, questionList);
		lv.setAdapter(arrayAdapter);
		
		ds.close();
		lv.setOnItemClickListener(this);
		
		return false;
	}

}
