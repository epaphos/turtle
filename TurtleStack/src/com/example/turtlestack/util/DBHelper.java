package com.example.turtlestack;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	 
	//Android Default route by defect in system databases
	private static String DB_PATH = "/data/data/com.example.turtlestack/databases/";
	 
	private static String DB_NAME = "so.sqlite";
	 
	private SQLiteDatabase myDataBase;
	 
	private final Context myContext;
	 
	/**
	* Constructor
	* Making reference to the context of the application that invokes it to access the 'assets' and 'resources'
	* of the application. Creates a DBOpenHelper that allow us to control the opening of the database.
	* @param context
	*/
	public DBHelper(Context context) {
	 
	super(context, DB_NAME, null, 1);
	this.myContext = context;
	 
	}
	 
	/**
	* Create an empty database on the system and rewrites our database file.
	* */
	public void createDataBase() throws IOException{
	 
	boolean dbExist = checkDataBase();
	 
	if(dbExist){
	// we don't do anything because the database already exists
	}else{
	//Calling this method is created empty database in the default system path of
	//your application so we can override it with our database.
	this.getReadableDatabase();
	 
	try {
	 
	copyDataBase();
	 
	} catch (IOException e) {
	throw new Error("Mistake copying Database");
	}
	}
	 
	}
	 
	/**
	* Checks if the Database exists for avoid to copy the file everytime you open the application.
	* @return true if exists, false if not.
	*/
	private boolean checkDataBase(){
	 
	SQLiteDatabase checkDB = null;
	 
	try{
	 
	String myPath = DB_PATH + DB_NAME;
	checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	 
	}catch(SQLiteException e){
	 
	//if we arrive here is because the DB doesn't exist
	 
	}
	if(checkDB != null){
	 
	checkDB.close();
	 
	}
	return checkDB != null ? true : false;
	}
	 
	/**
	* Copy our database from the assets folder to the recently created DB in the system folder, from we can access to it.
	* This is done with bytestream.
	* */
	private void copyDataBase() throws IOException{
	 
	//Abrimos el fichero de base de datos como entrada
	InputStream myInput = myContext.getAssets().open(DB_NAME);
	 
	//Ruta a la base de datos vacía recién creada
	String outFileName = DB_PATH + DB_NAME;
	 
	//Abrimos la base de datos vacía como salida
	OutputStream myOutput = new FileOutputStream(outFileName);
	 
	//Transferimos los bytes desde el fichero de entrada al de salida
	byte[] buffer = new byte[1024];
	int length;
	while ((length = myInput.read(buffer))>0){
	myOutput.write(buffer, 0, length);
	}
	 
	//Liberamos los streams
	myOutput.flush();
	myOutput.close();
	myInput.close();
	 
	}
	 
	public void open() throws SQLException{
	 
	//Abre la base de datos
	try {
	createDataBase();
	} catch (IOException e) {
	throw new Error("Ha sido imposible crear la Base de Datos");
	}
	 
	String myPath = DB_PATH + DB_NAME;
	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	 
	}
	 
	@Override
	public synchronized void close() {
	if(myDataBase != null)
	myDataBase.close();
	super.close();
	}
	 
	@Override
	public void onCreate(SQLiteDatabase db) {
	 
	}
	 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	 
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	/**
	* A continuación se crearán los métodos de lectura, inserción, actualización
	* y borrado de la base de datos.
	* */
