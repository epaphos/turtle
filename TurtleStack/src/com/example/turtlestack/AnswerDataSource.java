package com.example.turtlestack;

import android.content.Context;

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
	
	public boolean write(Answer answer) {
		return super.write(answer);
	}
	
}
