package com.example.turtlestack;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

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
		//Cursor cursor = database.rawQuery("SELECT title, body FROM posts where id=?", new String [] {String.valueOf(Id)});
		//cursor.moveToFirst();
		Answer answer = (Answer) super.read(Id);
		if (answer.getPostTypeId() == 2) {
			return answer;

		}
		else {
			throw new wrongTypeException();
		}
	}
	
	public ArrayList<Answer> getAnswers(int id) {
		
		Cursor cursor = database.rawQuery("SELECT answer_id FROM QuestionHasAnswer WHERE question_id = ?", 
				new String[] {String.valueOf(id)});
		cursor.moveToFirst();
		ArrayList<Answer> answers = new ArrayList<Answer>();
		while(!cursor.isAfterLast()) {			
			try {
				answers.add((Answer)getAnswer(cursor.getInt(cursor.getColumnIndex("answer_id"))));
			} catch (wrongTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cursor.moveToNext();
		}
		return answers;
	}
	
	public boolean setAnswer(Answer answer) {
		return super.write(answer);
	}
	
}
