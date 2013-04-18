package com.example.turtlestack;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BrowseActivity extends ListActivity {
	
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse);
		
		QuestionDataSource ds = new QuestionDataSource(this);
		ds.open();
		
		ArrayList<Question> list = ds.getRecentQuestions(10);
		ArrayList<String> listOfTitles = new ArrayList<String>();
		for (Question question : list) {
			listOfTitles.add(question.getTitle());
		}
        
		lv = (ListView) findViewById(android.R.id.list);
        ArrayAdapter<String> arrayAdapter = 
        		new ArrayAdapter<String>(this, R.layout.list_row, listOfTitles);
        lv.setAdapter(arrayAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse, menu);
		return true;
	}

}
