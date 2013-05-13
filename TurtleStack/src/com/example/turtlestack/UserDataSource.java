 package com.example.turtlestack;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
/**
 * 
 * @author cyrill
 * @since 2013-04-20
 */
public class UserDataSource implements DataSourceUtils {
	TurtleSQLiteHelper helper;
	SQLiteDatabase database;
	private static UserDataSource instance = null;
	
	public UserDataSource(Context context) {
		helper = new TurtleSQLiteHelper(context);
		helper.createDatabase();
	}
	
	public static UserDataSource getInstance(Context context){
		if(instance == null) instance = new UserDataSource(context);
		return instance;
	}
	
	public void open() throws SQLException {
		database = helper.openDataBase();
	}
	
	public void close() {
		helper.close();
	}

	public User readUser(int id){
		Cursor cursor = database.rawQuery("select * from users where id = ?", new String[] { String.valueOf(id) });
		cursor.moveToFirst();
		
		  //int userId = 
		  int reputation = cursor.getInt(cursor.getColumnIndex("reputation"));
		  String creationDate = cursor.getString(cursor.getColumnIndex("creation_date"));
		  String displayName = cursor.getString(cursor.getColumnIndex("display_name"));
		  String emailHash = cursor.getString(cursor.getColumnIndex("email_hash"));
		  String lastAccessDate = cursor.getString(cursor.getColumnIndex("last_access_date"));
		  String websiteURL = cursor.getString(cursor.getColumnIndex("website_url"));
		  String location = cursor.getString(cursor.getColumnIndex("location"));
		  int age = cursor.getInt(cursor.getColumnIndex("age"));
		  String aboutMe = cursor.getString(cursor.getColumnIndex("about_me"));
		  int views = cursor.getInt(cursor.getColumnIndex("views"));
		  int upVotes = cursor.getInt(cursor.getColumnIndex("up_votes"));
		  int downVotes = cursor.getInt(cursor.getColumnIndex("down_votes"));
		  
		  return(new User(
				  id, 
				  reputation, 
				  creationDate,
				  displayName,
				  emailHash,
				  lastAccessDate,
				  websiteURL,
				  location,
				  age,
				  aboutMe,
				  views,
				  upVotes,
				  downVotes));
	}
	
	public User getDummyUser(){
		Log.v("question", "in method");
		
		Cursor cursor = database.rawQuery("SELECT * FROM users LIMIT 1", new String[] {});
		Log.v("question", "before move to first");
		
		cursor.moveToFirst();
		Log.v("question", "after cursor");
		  int userId = cursor.getInt(cursor.getColumnIndex("id"));
		  int reputation = cursor.getInt(cursor.getColumnIndex("reputation"));
		  String creationDate = cursor.getString(cursor.getColumnIndex("creation_date"));
		  String displayName = cursor.getString(cursor.getColumnIndex("display_name"));
		  String emailHash = cursor.getString(cursor.getColumnIndex("email_hash"));
		  String lastAccessDate = cursor.getString(cursor.getColumnIndex("last_access_date"));
		  String websiteURL = cursor.getString(cursor.getColumnIndex("website_url"));
		  String location = cursor.getString(cursor.getColumnIndex("location"));
		  int age = cursor.getInt(cursor.getColumnIndex("age"));
		  String aboutMe = cursor.getString(cursor.getColumnIndex("about_me"));
		  int views = cursor.getInt(cursor.getColumnIndex("views"));
		  int upVotes = cursor.getInt(cursor.getColumnIndex("up_votes"));
		  int downVotes = cursor.getInt(cursor.getColumnIndex("down_votes"));
		  
		  return(new User(
				  userId, 
				  reputation, 
				  creationDate,
				  displayName,
				  emailHash,
				  lastAccessDate,
				  websiteURL,
				  location,
				  age,
				  aboutMe,
				  views,
				  upVotes,
				  downVotes));
	}
	
}
