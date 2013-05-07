package com.example.turtlestack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

public class TagCloud {
	private TreeMap<String,TreeMap<String,Integer>> tagCloud;
	private static int MAXRELATIONS = 9;
	protected static TagCloud instance = null;
	
	private TagCloud (TreeMap<String,TreeMap<String,Integer>> tagCloud) {
		this.tagCloud = tagCloud;
	}
	
	public static TagCloud getInstance(Context context){
		if(instance == null) instance = new TagCloud(makeTheCloud (context));
		return (TagCloud) instance;
	}
	
	private static TreeMap <String, Integer> getPopularTagRelations(TreeMap<String,Integer> relationedTagMap){
		int i = 0;
		Map<String,Integer> map = relationedTagMap;
		TreeMap<String, Integer> popularTagRelations = new TreeMap<String,Integer>();
		Iterator itKeys = Helpers.entriesSortedByValues(map).iterator();
		while ( i < MAXRELATIONS && itKeys.hasNext()) {
			String key = (String) itKeys.next().toString().split("=")[0]; //We can not allow strings with an "=" inside as a tag
			popularTagRelations.put(key, relationedTagMap.get(key));
			i++;
		}
		return popularTagRelations;
	}
	
	private static TreeMap<String,TreeMap<String, Integer>> makeTheCloud(Context context) {
		TagDataSource ts = TagDataSource.getInstance(context);
		ts.open();
		TreeMap<String,TreeMap<String, Integer>> bigMap = ts.createTagRelationMap();
		TreeMap<String,TreeMap<String, Integer>> cloudMap = new TreeMap<String, TreeMap<String,Integer>>();
		Iterator itKeys = bigMap.keySet().iterator();
		while (itKeys.hasNext()) {
			String key = (String) itKeys.next();
		    cloudMap.put(key,getPopularTagRelations(bigMap.get(key)));
		}
		ts.close();
		return cloudMap;
	}
	
	public TreeMap<String, Integer> Navigate (ArrayList<String> deadSet, String mainTag) {
		int i = 0;
		TreeMap<String, Integer> NavigateMap = new TreeMap<String,Integer>();
		Map<String,Integer> relationMap = this.tagCloud.get(mainTag);
		Iterator itKeys = Helpers.entriesSortedByValues(relationMap).iterator();
		while (itKeys.hasNext() && i < 5) {
			String key = (String) itKeys.next().toString().split("=")[0];
			if (!deadSet.contains(key)) {
				deadSet.add(key);
				NavigateMap.put(key, relationMap.get(key));
				i++;
			}
		}
		return NavigateMap;
	}
	/**
	 * @return the tagCloud
	 */
	public TreeMap<String, TreeMap<String, Integer>> getTagCloud() {
		return tagCloud;
	}
	
	@SuppressLint("NewApi")
	public String printOrderedCloud() {
		return this.tagCloud.descendingKeySet().toString();
	}
	
	@SuppressLint("NewApi")
	public String getPrevious(String tag) {
		String previous;
		if (this.tagCloud.lowerKey(tag) == null) previous = this.tagCloud.lastKey();
		else previous = this.tagCloud.lowerKey(tag).toString();
		return previous;
	}
	@SuppressLint("NewApi")
	public String getNext(String tag) {
		String next;
		if (this.tagCloud.higherKey(tag) == null) next = this.tagCloud.lastKey();
		else next = this.tagCloud.higherKey(tag).toString();
		return next;
	}
}
