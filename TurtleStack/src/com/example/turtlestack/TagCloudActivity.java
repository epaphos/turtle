package com.example.turtlestack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

public class TagCloudActivity extends FragmentActivity {
	TagDataSource tc;
	String mainTag;
	int mainTagIndex = 0;
	ViewPager pager = null;
	SearchView searchView = null;
	boolean occurence = true;
	TagCloudOnPageListener pageChangeListener = null;
	ArrayList allTags = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_cloud);
		Intent intent = getIntent();
		mainTag = intent.getStringExtra("mainTag");
		tc = TagDataSource.getInstance(this);
//		search = (ViewPager findViewById())
		searchView = (SearchView) findViewById(R.id.searchView1);
		pager =  (ViewPager) findViewById(R.id.pager);
        TagCloudPageAdapter adapter = new TagCloudPageAdapter(getSupportFragmentManager());
        allTags = tc.getAllTheTags();
        Iterator it = allTags.iterator();        
        while (it.hasNext()) {
            adapter.addFragment(TagCloudFragment.newInstance(it.next().toString(),allTags));
		}
        pageChangeListener = new TagCloudOnPageListener();
        pager.setOnPageChangeListener(pageChangeListener);
        pager.setPageTransformer(true, new TagCloudPageTransformer());
        pager.setAdapter(adapter);
        TagCloudFragment.newDeadSet();
        TagCloudFragment.addToDeadList(mainTag);
        pager.setCurrentItem(allTags.indexOf(mainTag));
        
        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() { 
            @Override 
            public boolean onQueryTextChange(String newText) { 
                // Do something 
                return true; 
            } 

            @Override 
            public boolean onQueryTextSubmit(String query) {
            	if (tc.isTagInTable(query)) {
                    TagCloudFragment.addToDeadList(mainTag);
            		pager.setCurrentItem(allTags.indexOf(query));
            		hideKeyboard();
            	}
            	return true; 
            } 
        }; 

        searchView.setOnQueryTextListener(queryTextListener);
	}
	
	public void hideKeyboard () {
    	InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
    	imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tag_cloud, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
}
