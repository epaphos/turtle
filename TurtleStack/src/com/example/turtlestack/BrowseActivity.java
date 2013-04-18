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
		
		PostDataSource ds = new PostDataSource(this);
		ds.open();
		
		ArrayList<Post> list= ds.getRecentPosts(10);
		ArrayList<String> list2 = new ArrayList<String>();
		for (Post post : list) {
			list2.add(post.getTitle());
		}
        
		lv = (ListView) findViewById(android.R.id.list);
        ArrayAdapter<String> arrayAdapter = 
        		new ArrayAdapter<String>(this, R.layout.list_row, list2);
        lv.setAdapter(arrayAdapter); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse, menu);
		return true;
	}

}
