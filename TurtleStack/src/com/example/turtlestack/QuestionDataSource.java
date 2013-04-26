package com.example.turtlestack;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

public class QuestionDataSource extends PostDataSource {
	protected static QuestionDataSource instance = null;

	public QuestionDataSource(Context context) {
		super(context);		
	}
	
	public static QuestionDataSource getInstance(Context context){
		if(instance == null) instance = new QuestionDataSource(context);
		return (QuestionDataSource) instance;
	}

	public ArrayList<Question> getRecentQuestions(int numberOfPosts) {
		Cursor cursor = database.rawQuery("SELECT id, post_type_id, title, body, tags, creation_date FROM posts where post_type_id=1 ORDER BY creation_date DESC LIMIT ?", new String [] {String.valueOf(numberOfPosts)});
		ArrayList<Question> list = new ArrayList<Question>();
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			try {
				list.add((Question) getQuestion(cursor.getInt(cursor.getColumnIndex("id"))));
			} catch (Exception e) {
				// TODO: handle exception
			}
			cursor.moveToNext();			
		}
		return list;
	
	}
	
	public ArrayList<Question> getSearchResults(String searchQuery){
		Cursor cursor = database.rawQuery("SELECT id, post_type_id, title, body, tags, creation_date FROM posts where post_type_id=1, title LIKE '%?%' BY creation_date DESC LIMIT", new String [] {searchQuery});
		ArrayList<Question> list = new ArrayList<Question>();
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			try {
				list.add((Question) getQuestion(cursor.getInt(cursor.getColumnIndex("id"))));
			} catch (Exception e) {
				// TODO: handle exception
			}
			cursor.moveToNext();			
		}
		return list;
		
	}
	
	public Question getQuestion(int Id) throws wrongTypeException {
		//Cursor cursor = database.rawQuery("SELECT title, body FROM posts where id=?", new String [] {String.valueOf(Id)});
		//cursor.moveToFirst();
		Question question = (Question) super.readPost(Id);
		if (question.getPostTypeId() == 1) {
			return question;

		}
		else {
			throw new wrongTypeException();
		}
	}
	
	public Question getQuestionDummy(int ID){
		String body = "<p>I have a ticks value of 28000000000 which should be 480 minutes but how can I be sure? How do I convert a ticks value to minutes?</p>\n\n<p>Thanks</p>\n";
		String title = "I have a question";
		String tags = "question";
		Question q = new Question(title,body,tags);
		q.setId(ID);
		return q;		
	}
	
	public Question getLastPost() {
		return (Question) super.getLastPost();
	}
	
	public boolean setQuestion (Question question) {
		return super.writePost(question);
	}
}
