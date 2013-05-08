package com.example.turtlestack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TagsListActivity extends Activity {
	TagDataSource ts;
	
	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tags_list);
		ts = TagDataSource.getInstance(this);
		ts.open();
        ArrayList<String> arrayOfTags =ts.getAllTheTags();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_row,arrayOfTags);
		lv = (ListView) findViewById(android.R.id.list);
        lv.setAdapter(arrayAdapter);

        ts.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tags_list, menu);
		return true;
	}

}
