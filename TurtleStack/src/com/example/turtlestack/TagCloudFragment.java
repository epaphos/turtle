package com.example.turtlestack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class TagCloudFragment extends Fragment {
	TagCloud tc;
	String mainTag;
	ArrayList allTags;
	static ArrayList<String> deadSet;
	static ArrayList<String> historical = new ArrayList<String>();
	ViewPager pager;
	ListView lv;
	private ArrayAdapter<String> listAdapter;
	String before,after,relationed1,relationed2,relationed3,relationed4;
	static Context context;
	Intent intent;
	Bundle bundle;
	boolean heat = true;
	public static boolean callTheDragon = false;
	
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
		Button buttonHistorical = (Button) view.findViewById(R.id.buttonHistorical);
		Button tv1 = (Button) view.findViewById(R.id.tv1);
		Button tv2 = (Button) view.findViewById(R.id.tv2);
		Button tv3 = (Button) view.findViewById(R.id.tv3);
		Button tv4 = (Button) view.findViewById(R.id.tv4);
		Button tv5 = (Button) view.findViewById(R.id.tv5);
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
				  ArrayList<String> aux = new ArrayList<String>();
				  aux.add("<"+mainTag+">");
				  goQuestionsView(aux);
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
		//LONG CLICK LISTENERS
		buttonBefore.setOnLongClickListener(new OnLongClickListener() {
			  public boolean onLongClick(View view) {
				    if (historical.size() <5)
				    	historical.add("<"+before+">");
				    else {
				    	historical.remove(0);
			    		historical.add("<"+before+">");
				    }
					return true;
			  }
			});
		buttonMain.setOnLongClickListener(new OnLongClickListener() {
			  public boolean onLongClick(View view) {
				    if (historical.size() <5)
				    	historical.add("<"+mainTag+">");
				    else {
				    	historical.remove(0);
			    		historical.add("<"+mainTag+">");
				    }
				  return true;
			  }
			});
		buttonAfter.setOnLongClickListener(new OnLongClickListener() {
			  public boolean onLongClick(View view) {				    
				  if (historical.size() <5) 
			    	historical.add("<"+after+">");
			    else {
			    	historical.remove(0);
		    		historical.add("<"+after+">");
			    }
				return true;
			  }
			});
		buttonRelationed1.setOnLongClickListener(new OnLongClickListener() {
			  public boolean onLongClick(View view) {
				    if (historical.size() <5)
				    	historical.add("<"+relationed1+">");
				    else {
				    	historical.remove(0);
			    		historical.add("<"+relationed1+">");
				    }
 				    return true;
			  }
			});
		buttonRelationed2.setOnLongClickListener(new OnLongClickListener() {
			  public boolean onLongClick(View view) {
				  	Log.v("TAG", relationed2);
				    if (historical.size() <5)
				    	historical.add("<"+relationed2+">");
				    else {
				    	historical.remove(0);
			    		historical.add("<"+relationed2+">");
				    }
				    return true;
			  }
			});
		buttonRelationed3.setOnLongClickListener(new OnLongClickListener() {
			  public boolean onLongClick(View view) {
				    if (historical.size() <5)
				    	historical.add("<"+relationed3+">");
				    else {
				    	historical.remove(0);
			    		historical.add("<"+relationed3+">");
				    }
				    return true;
			  }
			});
		buttonRelationed4.setOnLongClickListener(new OnLongClickListener() {
			  public boolean onLongClick(View view) {
				    if (historical.size() <5)
				    	historical.add("<"+relationed4+">");
				    else {
				    	historical.remove(0);
			    		historical.add("<"+relationed4+">");
				    }
				    return true;
			  }
			});
		buttonHistorical.setOnClickListener(new OnClickListener() {
			  public void onClick(View view) {
				    if (historical.size() >0) {
			    		goQuestionsView(historical);
				    }
			  }
			});
		tv1.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				StringTokenizer tk = new StringTokenizer(historical.get(0),"><");
				String clickedTag = (String) tk.nextElement();
				Log.v("ct",clickedTag);
				pager.setCurrentItem(allTags.indexOf(clickedTag));
			  }
			});
		tv2.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				StringTokenizer tk = new StringTokenizer(historical.get(1),"><");
				String clickedTag = (String) tk.nextElement();
				Log.v("ct",clickedTag);
				pager.setCurrentItem(allTags.indexOf(clickedTag));
			  }
			});
		tv3.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				StringTokenizer tk = new StringTokenizer(historical.get(2),"><");
				String clickedTag = (String) tk.nextElement();
				Log.v("ct",clickedTag);
				pager.setCurrentItem(allTags.indexOf(clickedTag));
			  }
			});
		tv4.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				StringTokenizer tk = new StringTokenizer(historical.get(3),"><");
				String clickedTag = (String) tk.nextElement();
				Log.v("ct",clickedTag);
				pager.setCurrentItem(allTags.indexOf(clickedTag));
			  }
			});
		tv5.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				StringTokenizer tk = new StringTokenizer(historical.get(4),"><");
				String clickedTag = (String) tk.nextElement();
				Log.v("ct",clickedTag);
				pager.setCurrentItem(allTags.indexOf(clickedTag));
			  }
			});
		tv1.setOnLongClickListener(new OnLongClickListener() {
			  public boolean onLongClick(View view) {
				    historical.remove(0);
				    return true;
			  }
			});
		tv2.setOnLongClickListener(new OnLongClickListener() {
			  public boolean onLongClick(View view) {
				    historical.remove(0);
				    return true;
			  }
			});
		tv3.setOnLongClickListener(new OnLongClickListener() {
			  public boolean onLongClick(View view) {
				    historical.remove(0);
				    return true;
			  }
			});
		tv4.setOnLongClickListener(new OnLongClickListener() {
			  public boolean onLongClick(View view) {
				    historical.remove(0);
				    return true;
			  }
			});
		tv5.setOnLongClickListener(new OnLongClickListener() {
			  public boolean onLongClick(View view) {
				    historical.remove(0);
				    return true;
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
		Iterator it = null;
		if (cloud != null)
		if (Helpers.entriesSortedByValues(cloud).size() != 0) 
			it = Helpers.entriesSortedByValues(cloud).iterator();
		Button buttonBefore = (Button) view.findViewById(R.id.buttonBefore);
		Button buttonMain = (Button) view.findViewById(R.id.buttonMain);
		Button buttonAfter = (Button) view.findViewById(R.id.buttonAfter);
		Button buttonRelationed1 = (Button) view.findViewById(R.id.buttonRelationed1);
		Button buttonRelationed2 = (Button) view.findViewById(R.id.buttonRelationed2);
		Button buttonRelationed3 = (Button) view.findViewById(R.id.buttonRelationed3);
		Button buttonRelationed4 = (Button) view.findViewById(R.id.buttonRelationed4);
		
		setHistorical(view);
		buttonMain.setText(mainTag);
		before = tc.getPrevious(mainTag);
		buttonBefore.setText(before);
		after = tc.getNext(mainTag);
		buttonAfter.setText(after);
		if (cloud != null && it.hasNext()) {
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
		if (cloud != null && it.hasNext()) {
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
		if (cloud != null && it.hasNext()) {
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
		if (cloud != null && it.hasNext()) {
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
		if(nRelations == 4 && callTheDragon) {
			callTheDragon(view,pager);
			callTheDragon = false;
		}
;
	}
	
	private static void setHistorical(View view) {
		Button tv1 = (Button) view.findViewById(R.id.tv1);
		Button tv2 = (Button) view.findViewById(R.id.tv2);
		Button tv3 = (Button) view.findViewById(R.id.tv3);
		Button tv4 = (Button) view.findViewById(R.id.tv4);
		Button tv5 = (Button) view.findViewById(R.id.tv5);
		
		//SETTING RECORD OF TAGS
		Iterator it = null;
		if (historical != null) {
			it = historical.iterator();
			Log.v("historical",historical.toString());
		}
			if(historical != null && it.hasNext()) {
				tv1.setEnabled(true);
				tv1.setVisibility(View.VISIBLE);
				tv1.setText((String) it.next());
			}
			else {
				tv1.setEnabled(false);
				tv1.setVisibility(View.INVISIBLE);
			}
			if(historical != null && it.hasNext()) {
				tv2.setEnabled(true);
				tv2.setVisibility(View.VISIBLE);
				tv2.setText((String) it.next());
	
			}
			else {
				tv2.setEnabled(false);
				tv2.setVisibility(View.INVISIBLE);
			}
			if(historical != null && it.hasNext()) {
				tv3.setEnabled(true);
				tv3.setVisibility(View.VISIBLE);
				tv3.setText((String) it.next());
	
			}
			else {
				tv3.setEnabled(false);
				tv3.setVisibility(View.INVISIBLE);
			}
			if(historical != null && it.hasNext()) {
				tv4.setEnabled(true);
				tv4.setVisibility(View.VISIBLE);
				tv4.setText((String) it.next());
	
			}
			else {
				tv4.setEnabled(false);
				tv4.setVisibility(View.INVISIBLE);
			}
			if(historical != null && it.hasNext()) {	
				tv5.setEnabled(true);
				tv5.setVisibility(View.VISIBLE);
				tv5.setText((String) it.next());
	
			}
			else {
				tv5.setEnabled(false);
				tv5.setVisibility(View.INVISIBLE);
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
	    		button.setBackgroundResource(R.drawable.greenbubble);
	    	}
    		if((i2 >= value ) && (value > i3 )) {
    			button.setBackgroundResource(R.drawable.orange);
    		}

    		if((i3 >= value ) && (value > i4 )) {
    			button.setBackgroundResource(R.drawable.yellowbubble);
    		}
    		if(( i4 >= value ) && (value > 0 )){
    			button.setBackgroundResource(R.drawable.redbubble);
    		}

 
	}
	
	public static void callTheDragon(View v,ViewPager pager) {
		Button buttonBefore = (Button) v.findViewById(R.id.buttonBefore);
		Button buttonMain = (Button) v.findViewById(R.id.buttonMain);
		Button buttonAfter = (Button) v.findViewById(R.id.buttonAfter);
		Button buttonRelationed1 = (Button) v.findViewById(R.id.buttonRelationed1);
		Button buttonRelationed2 = (Button) v.findViewById(R.id.buttonRelationed2);
		Button buttonRelationed3 = (Button) v.findViewById(R.id.buttonRelationed3);
		Button buttonRelationed4 = (Button) v.findViewById(R.id.buttonRelationed4); 
	    v.setBackgroundResource(R.drawable.shenron);
	    buttonRelationed1.setBackgroundResource(R.drawable.db1);
	    buttonRelationed2.setBackgroundResource(R.drawable.db2);
	    buttonAfter.setBackgroundResource(R.drawable.db3);
	    buttonRelationed4.setBackgroundResource(R.drawable.db4);
	    buttonRelationed3.setBackgroundResource(R.drawable.db5);
	    buttonBefore.setBackgroundResource(R.drawable.db6);
	    buttonMain.setBackgroundResource(R.drawable.db7);
	}
	
	public static void newDeadSet(){
		deadSet = new ArrayList();
	}
	
    public void goQuestionsView (ArrayList<String> s) {
		Intent intent = new Intent(getActivity(), BrowseActivity.class);
		Log.v("tagList",s.toString());
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
