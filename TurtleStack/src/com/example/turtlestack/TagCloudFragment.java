package com.example.turtlestack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
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
	static ArrayList<String> deadSet;
	ViewPager pager;
	String before,after,relationed1,relationed2,relationed3,relationed4;
	static Context context;
	Intent intent;
	Bundle bundle;
	boolean heat = true;
	
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
	    frag1Args.putStringArrayList("deadSet", deadSet);
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
				    if (deadSet.size() <5)
				    	deadSet.add("<"+before+">");
				    else {
				    	deadSet.remove(0);
			    		deadSet.add("<"+before+">");
				    }
					pager.setCurrentItem(allTags.indexOf(before));
			  }
			});
		buttonMain.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
				  goQuestionsView(mainTag);
			  }
			});
		buttonAfter.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {				    
				  if (deadSet.size() <5)
			    	deadSet.add("<"+after+">");
			    else {
			    	deadSet.remove(0);
		    		deadSet.add("<"+after+">");
			    }
				pager.setCurrentItem(allTags.indexOf(after));
			  }
			});
		buttonRelationed1.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
				    if (deadSet.size() <5)
				    	deadSet.add("<"+relationed1+">");
				    else {
				    	deadSet.remove(0);
			    		deadSet.add("<"+relationed1+">");
				    }
				  	pager.setCurrentItem(allTags.indexOf(relationed1));
			  }
			});
		buttonRelationed2.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
				    if (deadSet.size() <5)
				    	deadSet.add("<"+relationed2+">");
				    else {
				    	deadSet.remove(0);
			    		deadSet.add("<"+relationed2+">");
				    }
					pager.setCurrentItem(allTags.indexOf(relationed2));
			  }
			});
		buttonRelationed3.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
				    if (deadSet.size() <5)
				    	deadSet.add("<"+relationed3+">");
				    else {
				    	deadSet.remove(0);
			    		deadSet.add("<"+relationed3+">");
				    }
					pager.setCurrentItem(allTags.indexOf(relationed3));
			  }
			});
		buttonRelationed4.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
				    if (deadSet.size() <5)
				    	deadSet.add("<"+relationed4+">");
				    else {
				    	deadSet.remove(0);
			    		deadSet.add("<"+relationed4+">");
				    }
					pager.setCurrentItem(allTags.indexOf(relationed4));
			  }
			});

	    return view;
	}
		
	public void displayTags(View view){
		tc = TagCloud.getInstance(this.getActivity());
		Map<String, Integer> cloud = tc.getTagCloud().get(mainTag);
		String aux;
		int max = 0, nRelations = 0,r1v=0,r2v=0,r3v=0,r4v=0;
		boolean assigned = false;
		Iterator it = Helpers.entriesSortedByValues(cloud).iterator();
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
			assigned = false;
			aux =  it.next().toString();
			while (!assigned) {
				relationed1 = aux.split("=")[0];
				if (!deadSet.contains("<"+relationed1+">")) {
					buttonRelationed1.setEnabled(true);
					buttonRelationed1.setVisibility(View.VISIBLE);
					r1v = Integer.parseInt(aux.split("=")[1]);//120
					max = r1v;
					nRelations++;
					buttonRelationed1.setText(relationed1);
					assigned = true;
				}
				else {
					if (it.hasNext()) aux = it.next().toString();
				
					else {
						assigned = true;
						buttonRelationed1.setEnabled(false);
						buttonRelationed1.setVisibility(View.INVISIBLE);
					}
				}
			}
		}
		else {
			buttonRelationed1.setEnabled(false);
			buttonRelationed1.setVisibility(View.INVISIBLE);
		}
		if (it.hasNext()) {
			assigned = false;
			aux =  it.next().toString();
			while (!assigned) {
				relationed2 = aux.split("=")[0];
				if (!deadSet.contains("<"+relationed2+">")) {
						buttonRelationed2.setEnabled(true);
						buttonRelationed2.setVisibility(View.VISIBLE);
						r2v = Integer.parseInt(aux.split("=")[1]);
						nRelations ++;
						buttonRelationed2.setText(relationed2);
						assigned = true;
				}
				else { 
					if (it.hasNext()) aux = it.next().toString();
					else {
						assigned = true;
						buttonRelationed2.setEnabled(false);
						buttonRelationed2.setVisibility(View.INVISIBLE);
					}
				}
			}
		}
		else {
			buttonRelationed2.setEnabled(false);
			buttonRelationed2.setVisibility(View.INVISIBLE);
		}
		if (it.hasNext()) {
			assigned = false;
			aux =  it.next().toString();
			while (!assigned) {
				relationed3= aux.split("=")[0];
				if (!deadSet.contains("<"+relationed3+">")) {
						buttonRelationed3.setEnabled(true);
						buttonRelationed3.setVisibility(View.VISIBLE);
						r3v = Integer.parseInt(aux.split("=")[1]);
						nRelations ++;
						buttonRelationed3.setText(relationed3);
						assigned = true;
				}
				else {
					if (it.hasNext()) aux = it.next().toString();
					else {
						assigned = true;
						buttonRelationed3.setEnabled(false);
						buttonRelationed3.setVisibility(View.INVISIBLE);
					}
				}
			}
		}
		else {
			buttonRelationed3.setEnabled(false);
			buttonRelationed3.setVisibility(View.INVISIBLE);
		}
		if (it.hasNext()) {
			assigned = false;
			aux =  it.next().toString();
			while (!assigned) {
				relationed4= aux.split("=")[0];
				if (!deadSet.contains("<"+relationed4+">")) {
						buttonRelationed4.setEnabled(true);
						buttonRelationed4.setVisibility(View.VISIBLE);
						r4v = Integer.parseInt(aux.split("=")[1]);
						nRelations ++;
						buttonRelationed4.setText(relationed4);
						assigned = true;
				}
				else {
					if (it.hasNext()) aux = it.next().toString();
					else {
						assigned = true;
						buttonRelationed4.setEnabled(false);
						buttonRelationed4.setVisibility(View.INVISIBLE);
					}
				}
			}
		}
		else {
			
			buttonRelationed4.setEnabled(false);
			buttonRelationed4.setVisibility(View.INVISIBLE);
		}
		// HEAT THE BUTTONS CHANGING IMAGE
		if(heat) {
			if (nRelations >= 1) heatIt(max,nRelations,r1v,buttonRelationed1);
			if (nRelations >= 2) heatIt(max,nRelations,r2v,buttonRelationed2);
			if (nRelations >= 3) heatIt(max,nRelations,r3v,buttonRelationed3);//70
			if (nRelations >= 4) heatIt(max,nRelations,r4v,buttonRelationed4);//30
		}

	}
	private void heatIt(int a,int b,int value,Button button) {
		double max = a;
		double nRelations =b;
		double i1=0,i2=0,i3=0,i4=0;
		if(nRelations != 0)
			i1 = (nRelations)*max/nRelations;
			i2 = (nRelations-1)*max/nRelations;
			i3 = (nRelations-2)*max/nRelations;
			i4 = (nRelations-3)*max/nRelations;
	    	if(( i1 >= value ) && (value > i2)) {
	    		button.setBackgroundResource(R.drawable.redbubble);
	    	}
    		if((i2 >= value ) && (value > i3 )) {
    			button.setBackgroundResource(R.drawable.yellowbubble);
    		}

    		if((i3 >= value ) && (value > i4 )) {
    			button.setBackgroundResource(R.drawable.greenbubble);
    		}
    		if(( i4 >= value ) && (value > 0 )){
    			button.setBackgroundResource(R.drawable.bluebubble);
    		}

 
	}
	
	public static void newDeadSet(){
		deadSet = new ArrayList();
	}
	
    public void goQuestionsView (String s) {
		Intent intent = new Intent(getActivity(), BrowseActivity.class);
		intent.putExtra("tagList", s);
		startActivity(intent);
    }
    
	public static void addToDeadList(String tag) {
		if (deadSet.size() <5) {
	    	deadSet.add("<"+tag+">");
		}
	    else {
	    	deadSet.remove(0);
    		deadSet.add("<"+tag+">");

	    }
	}
}
