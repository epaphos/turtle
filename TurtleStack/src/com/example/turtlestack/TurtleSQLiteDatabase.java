package com.example.turtlestack;

import java.io.*;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TurtleSQLiteDatabase extends SQLiteOpenHelper {

	private static String DB_PATH="";
	private static String DB_NAME="so.sqlite";
	Context context;
	
	private SQLiteDatabase database;
	
	
	public TurtleSQLiteDatabase (Context context) {
		super(context, DB_NAME, null, 1);
		DB_PATH = context.getFilesDir().getPath() + context.getPackageName() + "/databases/";
		this.context = context;
	}
	
	public void createDatabase() {
		if(!databaseExists()) {
			this.getReadableDatabase();
			this.close();
			try {
				copyDatabase();
			}
			catch(IOException exception){
				throw new Error("ErrorCopyingDatabase");
			}
		}	
	}
	
	private boolean databaseExists() {
		File file = new File(DB_PATH + DB_NAME);
		return file.exists();
		
	}
	
	private void copyDatabase() throws IOException {
		InputStream input = context.getAssets().open(DB_NAME);
		OutputStream output = new FileOutputStream(DB_PATH + DB_NAME);
		byte[] buffer = new byte[1024];
		int lenght;
		while((lenght = input.read(buffer)) >0) {
			output.write(buffer, 0, lenght);
		}
		output.flush();
		output.close();
		input.close();		
	}
	
	@Override
	public synchronized void close() {
		if(database != null) {
			database.close();
		}
		super.close();
	}
	
	public TurtleSQLiteDatabase(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void openDataBase() throws SQLException{
		 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
 
    }
	
	

}
