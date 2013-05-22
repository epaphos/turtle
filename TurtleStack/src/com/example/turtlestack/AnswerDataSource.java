package com.example.turtlestack;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class AnswerDataSource extends PostDataSource{
	protected static AnswerDataSource instance = null;

	public AnswerDataSource(Context context) {
		super(context);		
	}
	
	public static AnswerDataSource getInstance(Context context){
		if(instance == null) instance = new AnswerDataSource(context);
		return (AnswerDataSource) instance;
	}
	
	public Answer getAnswer(int Id) throws wrongTypeException {

		Answer answer = (Answer) super.read(Id);
		if (answer.getPostTypeId() == 2) {
			return answer;

		}
		else {
			throw new wrongTypeException();
		}
	}
	
	public ArrayList<Answer> getAnswers(int id) {
		Cursor cursor = database.rawQuery("SELECT answer_id FROM QuestionHasAnswer WHERE question_id = ? ORDER BY score", 
				new String[] {String.valueOf(id)});
		cursor.moveToFirst();
		ArrayList<Answer> answers = new ArrayList<Answer>();
		while(!cursor.isAfterLast()) {			
			try {
				answers.add(getAnswer(cursor.getInt(cursor.getColumnIndex("answer_id"))));
			} 
			catch (wrongTypeException e) {
			}
			cursor.moveToNext();
		}
		return answers;
	}
	
	public ArrayList<Answer> getSortedAnswers(int id) {
		Cursor cursor = database.rawQuery("SELECT id,parent_id, score FROM posts WHERE parent_id = ? ORDER BY score DESC", 
				new String[] {String.valueOf(id)});
		cursor.moveToFirst();
		ArrayList<Answer> answers = new ArrayList<Answer>();
		
		while(!cursor.isAfterLast()) {			
			try {
				
				answers.add(getAnswer(cursor.getInt(cursor.getColumnIndex("id"))));
			} 
			catch (wrongTypeException e) {
			}
			cursor.moveToNext();
		}
		return answers;
	}
	
	
	

	public int setAnswer(Answer answer) {
		return super.write(answer);
	}
	
	//Adds answer to relation table QuestionHasAnswer
	public void addAnswerToRT(int qId,int aId) {
		ContentValues values = new ContentValues();
		Log.v("ID QUESTION", Integer.toString(qId));
		Log.v("ID ANSWER 1", Integer.toString(aId));
		values.put("question_id",qId);
		values.put("answer_id", aId);
		Log.v("QUID",String.valueOf(qId)+" "+ String.valueOf(aId) );
		values.put("question_id", qId);
		values.put("answer_id", aId);
		database.insert("QuestionHasAnswer", null, values);
//		database.rawQuery("INSERT INTO QuestionHasAnswer(question_id, answer_id) VALUES (?,?)",new String []{String.valueOf(qId), String.valueOf(aId)});
		//database.insert("QuestionHasAnswer",null, values);
	}
	
}
