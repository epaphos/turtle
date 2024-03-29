package com.example.turtlestack;

import java.io.*;
import java.util.ArrayList;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TurtleSQLiteHelper extends SQLiteOpenHelper {

	private static String DB_PATH="";
	private static String DB_NAME="so.db";
	Context context;	
	
	public TurtleSQLiteHelper (Context context) {
		super(context, DB_NAME, null, 1);
		DB_PATH = context.getFilesDir().getPath() + "/databases/";
		this.context = context;
	}
	
	/**
	 * If the database is not already created - create a new database on the device.
	 */
	public void createDatabase() {
		if(!databaseExists()) {
			File f = new File(DB_PATH);
			if (!f.exists()) {
				f.mkdir();
			}
		
			this.getReadableDatabase().close();
		
			try {
				copyDatabase();
				createAndPopulateQuestionHasAnswer();
				createTagTable();
				//createTagRelationTable
				Log.v("DATABASE", "Database Copied");
			}
			catch(IOException exception){
				throw new Error("ErrorCopyingDatabase");
			}
		}	
		
		else {
			Log.v("DATABASE", "Database Already Exists");
		}		
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		
	}
	
	private boolean databaseExists() {
		File file = new File(DB_PATH + DB_NAME);
		return file.exists();
	}
	
	/**
	 * Copy database to DB_PATH+DB_NAME
	 * @throws IOException
	 */
	private void copyDatabase() throws IOException {
		InputStream input = context.getAssets().open(DB_NAME);
		OutputStream output = new FileOutputStream(DB_PATH + DB_NAME);
		byte[] buffer = new byte[1024];
		int length;
		while((length = input.read(buffer))>0) {
			output.write(buffer, 0, length);
		}
		output.flush();
		output.close();
		input.close();		
	}
	
	public TurtleSQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		
	}
	/**
	 * Open the database defined with DB_PATH and DB_NAME
	 * @return an opened database
	 * @throws SQLException
	 */
	public SQLiteDatabase openDataBase() throws SQLException{
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	return SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
	
	/**
	 * Creates and populate the questionHasAnswer relation table with existing data from the database
	 */
	public void createAndPopulateQuestionHasAnswer() {
		SQLiteDatabase db = this.openDataBase();
		// create questionHasAnswer relation table between questions and answers
		db.execSQL("CREATE TABLE QuestionHasAnswer(question_id INTEGER, answer_id INTEGER, FOREIGN KEY (question_id) REFERENCES posts(id) FOREIGN KEY (answer_id) REFERENCES posts(id));");
		// populate the questionHasAnswer relation table with the data existing in the database
		db.execSQL("INSERT INTO QuestionHasAnswer(question_id, answer_id) SELECT a.id, b.id FROM posts a, posts b WHERE a.id =  b.parent_id;"); 	
		db.close();
	}
	
	public void incrementAnswerCounter (int qId) {
		SQLiteDatabase db = this.openDataBase();
		Cursor cursor = db.rawQuery("select answer_count from posts where id = ?", new String [] {String.valueOf(qId)});
		cursor.moveToFirst();
		int newValue = cursor.getInt(cursor.getColumnIndex("answer_count")) +1;
		ContentValues values = new ContentValues();
		values.put("answer_count", newValue);
		Log.v("HERE", Integer.toString(qId)+ " " + Integer.toString(newValue));
		db.update("posts", values, "id = ?", new String[]{String.valueOf(qId)});
		db.close();
	}
	
	public void addAnswerToRT(int qId,int aId) {
		SQLiteDatabase db = this.openDataBase();
		ContentValues values = new ContentValues();
		Log.v("ID QUESTION", Integer.toString(qId));
		Log.v("ID ANSWER 1", Integer.toString(aId));
		values.put("question_id",qId);
		values.put("answer_id", aId);
		Log.v("QUID",String.valueOf(qId)+" "+ String.valueOf(aId) );
		values.put("question_id", qId);
		values.put("answer_id", aId);
//		database.insert("QuestionHasAnswer", null, values);
		db.rawQuery("INSERT INTO QuestionHasAnswer(question_id, answer_id) VALUES (?,?)",new String []{String.valueOf(qId), String.valueOf(aId)});
		//database.insert("QuestionHasAnswer",null, values);
		db.close();
	}
	
	public void createTagTable() {
		SQLiteDatabase db = this.openDataBase();
		ArrayList <String> tagsList = new ArrayList<String>();
		db.execSQL("CREATE TABLE tags(tag STRING PRIMARY KEY);");
		Cursor cursor = db.rawQuery("SELECT tags FROM posts where post_type_id = 1", new String[] {});
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			StringTokenizer st = new StringTokenizer(cursor.getString(cursor.getColumnIndex("tags")), "<>");
			int id = cursor.getInt(cursor.getColumnIndex("tags"));
			while (st.hasMoreTokens()) { 
				String tag = (String) st.nextElement();
				if (!tagsList.contains(tag)) {
					tagsList.add(tag);
					db.execSQL("INSERT INTO tags VALUES(?);",new String[]{tag});
				}
			}
		cursor.moveToNext();
		}
	db.close();
	}
}
