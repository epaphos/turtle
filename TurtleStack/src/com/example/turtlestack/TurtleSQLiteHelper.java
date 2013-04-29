package com.example.turtlestack;

import java.io.*;
import android.content.Context;
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
				Log.v("DATABASE", "Database Copied");
				createAndPopulateQuestionHasAnswer();
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
		Log.v("FAIL", "FLAG");
	}
}
