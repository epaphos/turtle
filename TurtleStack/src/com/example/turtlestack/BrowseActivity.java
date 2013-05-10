package com.example.turtlestack;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BrowseActivity extends ListActivity implements OnItemClickListener{
	QuestionDataSource ds;
	
	private ListView lv;
	private ArrayList<Question> questionList;
	MyBrowseAdapter arrayAdapter; 
	        
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse);
		
		ds = QuestionDataSource.getInstance(this);
		ds.open();
		
		questionList = ds.getRecentQuestions(10);
		
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
		return true;
	}

}
