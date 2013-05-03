package com.example.turtlestack;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends Activity implements OnItemClickListener {

	QuestionDataSource qs;
	ListView lv;
	ArrayList<Question> results;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		// Show the Up button in the action bar.
		setupActionBar();
		
		qs = QuestionDataSource.getInstance(this);
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
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
	
	public void searchQuery(View view) {
		
		EditText search = (EditText) findViewById(R.id.searchString);
		String searchQuery = search.getText().toString();
		
		qs.open();
		
		results = qs.getSearchResults(searchQuery);		
		ArrayList<String> listOfTitles = new ArrayList<String>();
		
		for (Question question : results) {
			listOfTitles.add(question.getTitle());
		}
        
		lv = (ListView) findViewById(R.id.searchResults);
        ArrayAdapter<String> arrayAdapter = 
        		new ArrayAdapter<String>(this, R.layout.list_row, listOfTitles);
        lv.setAdapter(arrayAdapter);
        qs.close();
        lv.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
		Question q = results.get(position);
		int questionId = q.getId();
        Log.i("Question", "Question with id " + q.getId() + " will be displayed");
        Intent intent = new Intent(this, QuestionDisplayActivity.class);
        intent.putExtra("questionId", questionId);
        startActivity(intent);
		
	}

}
