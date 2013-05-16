package com.example.turtlestack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class TagCloudFragment extends Fragment {
	TagCloud tc;
	String mainTag;
	View view;
	ArrayList allTags;
	ViewPager pager;
	String before,after,relationed1,relationed2,relationed3,relationed4;
	static Context context;
	Intent intent;
	Bundle bundle;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mainTag = this.getArguments().getString("mainTag");
		this.allTags = this.getArguments().getParcelableArrayList("allTags");
		pager = (ViewPager) this.getActivity().findViewById(R.id.pager);
	}
	
	public static TagCloudFragment newInstance(String s, ArrayList a) {
	    TagCloudFragment tagCloudFragment = new TagCloudFragment();
	    Bundle frag1Args = new Bundle();
	    frag1Args.putString("mainTag", s);
	    frag1Args.putStringArrayList("allTags", a);
	    tagCloudFragment.setArguments(frag1Args);
	    tagCloudFragment.setRetainInstance(true);
	    return tagCloudFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)   {
	    View view = inflater.inflate(R.layout.activity_cloud_fragment, container, false);
		Button buttonBefore = (Button) view.findViewById(R.id.buttonBefore);
		Button buttonMain = (Button) view.findViewById(R.id.buttonMain);
		Button buttonAfter = (Button) view.findViewById(R.id.buttonAfter);
		Button buttonRelationed1 = (Button) view.findViewById(R.id.buttonRelationed1);
		Button buttonRelationed2 = (Button) view.findViewById(R.id.buttonRelationed2);
		Button buttonRelationed3 = (Button) view.findViewById(R.id.buttonRelationed3);
		Button buttonRelationed4 = (Button) view.findViewById(R.id.buttonRelationed4);
		displayTags(view);
		buttonBefore.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
				pager.setCurrentItem(allTags.indexOf(before));
			  }
		});
		buttonMain.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
					pager.setCurrentItem(allTags.indexOf(mainTag));
			  }
			});
		buttonAfter.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
					pager.setCurrentItem(allTags.indexOf(after));
			  }
			});
		buttonRelationed1.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
					pager.setCurrentItem(allTags.indexOf(relationed1));
			  }
			});
		buttonRelationed2.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
					pager.setCurrentItem(allTags.indexOf(relationed2));
			  }
			});
		buttonRelationed3.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
					pager.setCurrentItem(allTags.indexOf(relationed3));
			  }
			});
		buttonRelationed4.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
					pager.setCurrentItem(allTags.indexOf(relationed4));
			  }
			});

	    return view;
	}
		
	public void displayTags(View view){
		tc = TagCloud.getInstance(this.getActivity());
		TreeMap<String, Integer> cloud = tc.getTagCloud().get(mainTag);
		Iterator<String> it = cloud.keySet().iterator();
		Button buttonBefore = (Button) view.findViewById(R.id.buttonBefore);
		Button buttonMain = (Button) view.findViewById(R.id.buttonMain);
		Button buttonAfter = (Button) view.findViewById(R.id.buttonAfter);
		Button buttonRelationed1 = (Button) view.findViewById(R.id.buttonRelationed1);
		Button buttonRelationed2 = (Button) view.findViewById(R.id.buttonRelationed2);
		Button buttonRelationed3 = (Button) view.findViewById(R.id.buttonRelationed3);
		Button buttonRelationed4 = (Button) view.findViewById(R.id.buttonRelationed4);
		
		buttonMain.setText(mainTag);
		before = tc.getPrevious(mainTag);
		buttonBefore.setText(before);
		after = tc.getNext(mainTag);
		buttonAfter.setText(after);
		if (it.hasNext()) {
			buttonRelationed1.setEnabled(true);
			buttonRelationed1.setVisibility(View.VISIBLE);
			relationed1 = it.next().toString();
			Log.v("r1", relationed1);
			buttonRelationed1.setText(relationed1);
		}
		else {
			buttonRelationed1.setEnabled(false);
			buttonRelationed1.setVisibility(View.INVISIBLE);
		}
		if (it.hasNext()) {
			buttonRelationed2.setEnabled(true);
			buttonRelationed2.setVisibility(View.VISIBLE);
			relationed2 = it.next().toString();
			Log.v("r2", relationed2);
			buttonRelationed2.setText(relationed2);
		}
		else {
			buttonRelationed2.setEnabled(false);
			buttonRelationed2.setVisibility(View.INVISIBLE);
		}
		if (it.hasNext()) {
			buttonRelationed3.setEnabled(true);
			buttonRelationed3.setVisibility(View.VISIBLE);
			relationed3 = it.next().toString();
			Log.v("r3", relationed3);
			buttonRelationed3.setText(relationed3);
		}
		else {
			buttonRelationed3.setEnabled(false);
			buttonRelationed3.setVisibility(View.INVISIBLE);
		}
		if (it.hasNext()) {
			buttonRelationed4.setEnabled(true);
			buttonRelationed4.setVisibility(View.VISIBLE);
			relationed4 = it.next().toString();
			Log.v("r4", relationed4);
			buttonRelationed4.setText(relationed4);
		}
		else {
			buttonRelationed4.setEnabled(false);
			buttonRelationed4.setVisibility(View.INVISIBLE);
		}
	}
}
