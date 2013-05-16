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
import android.widget.Button;
import android.widget.TextView;

public class TagCloudActivity extends FragmentActivity {
	TagDataSource tc;
	String mainTag;
	int mainTagIndex = 0;
	ViewPager pager = null;
	boolean occurence = true;
	TagCloudOnPageListener pageChangeListener = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_cloud);
		Intent intent = getIntent();
		mainTag = intent.getStringExtra("mainTag");
		tc = TagDataSource.getInstance(this);
		pager =  (ViewPager) findViewById(R.id.pager);
        TagCloudPageAdapter adapter = new TagCloudPageAdapter(getSupportFragmentManager());
        ArrayList allTags = tc.getAllTheTags();
        Iterator it = allTags.iterator();
        while (it.hasNext()) {
            adapter.addFragment(TagCloudFragment.newInstance(it.next().toString(),allTags));
		}
        pageChangeListener = new TagCloudOnPageListener();
        pager.setOnPageChangeListener(pageChangeListener);
        pager.setPageTransformer(true, new TagCloudPageTransformer());
        pager.setAdapter(adapter);
        pager.setCurrentItem(allTags.indexOf(mainTag));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tag_cloud, menu);
		return super.onCreateOptionsMenu(menu);
	}
}
