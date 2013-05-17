package com.example.turtlestack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TagDataSource implements DataSourceUtils {
	TurtleSQLiteHelper helper;
	protected SQLiteDatabase database;
	protected static TagDataSource instance = null;
	
	public TagDataSource(Context context) {
		helper = new TurtleSQLiteHelper(context);
		helper.createDatabase();
	}
	
	public static TagDataSource getInstance(Context context){
		if(instance == null) instance = new TagDataSource(context);
		return (TagDataSource) instance;
	}
		
	public void open() throws SQLException {
		database = helper.openDataBase();
	}
	
	public void close() {
		helper.close();
	}
	
	public TreeMap<String,TreeMap<String,Integer>> createTagRelationMap() {
		String tag, relationedTag;
		TreeMap<String,TreeMap<String,Integer>> TagMap = new TreeMap<String, TreeMap<String,Integer>>();
		Cursor cursor = database.rawQuery("SELECT tags FROM posts where post_type_id = 1", new String[] {});
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			StringTokenizer st = new StringTokenizer(cursor.getString(cursor.getColumnIndex("tags")), "<>");
			while (st.hasMoreTokens()) {
				TreeMap<String, Integer> relationTagMap =  new TreeMap<String, Integer>();
				tag = (String) st.nextElement();
				StringTokenizer at = new StringTokenizer(cursor.getString(cursor.getColumnIndex("tags")), "<>");
				while(at.hasMoreTokens()) {
					relationedTag = (String) at.nextElement();
					if (!tag.equals(relationedTag)) {
						if (!TagMap.containsKey(tag)) TagMap.put(tag,relationTagMap);
							if (!TagMap.get(tag).containsKey(relationedTag)) TagMap.get(tag).put(relationedTag, 1);
							else TagMap.get(tag).put(relationedTag, TagMap.get(tag).get(relationedTag).intValue()+1);
					}
				}
			}
		cursor.moveToNext();
		}
	return TagMap;
	}
	
	public boolean isTagInTable (String tag) {
		Cursor cursor = database.rawQuery("SELECT tag FROM tags where tag = ?", new String [] {tag});
		return !(cursor.isAfterLast());
	}
	
	
	public ArrayList<String> getAllTheTags() {
		Cursor cursor = database.rawQuery("SELECT tag FROM tags Order by tag ", new String [] {});
		ArrayList<String> list = new ArrayList<String>();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			try {
				list.add(cursor.getString(cursor.getColumnIndex("tag")));
			} catch (Exception e) {
				// TODO: handle exception
			}
			cursor.moveToNext();			
		}
		return list;
	
	}
}
