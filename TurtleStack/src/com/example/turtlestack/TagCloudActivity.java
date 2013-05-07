package com.example.turtlestack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TagCloudActivity extends Activity {
	TagCloud tc;
	String mainTag;
	String pink,green,yellow,blue,left,right;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_cloud);
		Intent intent = getIntent();
		mainTag = intent.getStringExtra("mainTag");
		tc = TagCloud.getInstance(this);
		displayTags();
//        ArrayList<String> deadSet = new ArrayList<String>();
//        
//        deadSet.add("android");
//        deadSet.add("eclipse");
//		tc = TagCloud.getInstance(this);
//
//        Log.v("cloud",tc.getTagCloud().get("java").toString());
//        Log.v("Navigate",tc.Navigate(deadSet, "java").toString());
	}

		
	public void displayTags(){
		TextView bubble = (TextView) findViewById(R.id.button1);
		TextView leftBubble = (TextView) findViewById(R.id.button6);
		TextView rightBubble = (TextView) findViewById(R.id.button7);
		TextView pinkBubble = (TextView) findViewById(R.id.button3);
		TextView greenBubble = (TextView) findViewById(R.id.button5);
		TextView yellowBubble = (TextView) findViewById(R.id.button4);
		TextView blueBubble = (TextView) findViewById(R.id.button2);
		tc = TagCloud.getInstance(this);
		Log.v("tag",tc.getPrevious("java"));
		TreeMap<String, Integer> cloud = tc.getTagCloud().get(mainTag);
		Iterator<String> it = cloud.keySet().iterator();
		bubble.setText(mainTag);
		left = tc.getPrevious(mainTag);
		leftBubble.setText(left);
		right = tc.getNext(mainTag);
		rightBubble.setText(right);
		it.next().toString();
		if (it.hasNext()) {
			pinkBubble.setEnabled(true);
			pinkBubble.setVisibility(View.VISIBLE);
			pink = it.next().toString();
			pinkBubble.setText(pink);
		}
		else {
			pinkBubble.setEnabled(false);
			pinkBubble.setVisibility(View.INVISIBLE);
		}
		if (it.hasNext()) {
			greenBubble.setEnabled(true);
			greenBubble.setVisibility(View.VISIBLE);
			green = it.next().toString();
			greenBubble.setText(green);
		}
		else {
			greenBubble.setEnabled(false);
			greenBubble.setVisibility(View.INVISIBLE);
		}
		if (it.hasNext()) {
			yellowBubble.setEnabled(true);
			yellowBubble.setVisibility(View.VISIBLE);
			yellow = it.next().toString();
			yellowBubble.setText(yellow);
		}
		else {
			yellowBubble.setEnabled(false);
			yellowBubble.setVisibility(View.INVISIBLE);
		}
		if (it.hasNext()) {
			blueBubble.setEnabled(true);
			blueBubble.setVisibility(View.VISIBLE);
			blue = it.next().toString();
			blueBubble.setText(blue);
		}
		else {
			blueBubble.setEnabled(false);
			blueBubble.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tag_cloud, menu);
		return true;
	}
	
	//REFRESH FUNCTIONS
	public void refreshWithPink(View v) {
	    Intent intent = getIntent();
	    finish();
	    intent.putExtra("mainTag", pink);
	    startActivity(intent);
	}
	
	public void refreshWithBlue(View v) {
	    Intent intent = getIntent();
	    finish();
	    intent.putExtra("mainTag", blue);
	    startActivity(intent);
	}
	
	public void refreshWithGreen(View v) {
	    Intent intent = getIntent();
	    finish();
	    intent.putExtra("mainTag",green);
	    startActivity(intent);
	}
	
	public void refreshWithYellow(View v) {
	    Intent intent = getIntent();
	    finish();
	    intent.putExtra("mainTag", yellow);
	    startActivity(intent);
	}
	
	public void refreshWithLeft(View v) {
	    Intent intent = getIntent();
	    finish();
	    intent.putExtra("mainTag", left);
	    startActivity(intent);
	}
	
	public void refreshWithRight(View v) {
	    Intent intent = getIntent();
	    finish();
	    intent.putExtra("mainTag", right);
	    startActivity(intent);
	}
}
