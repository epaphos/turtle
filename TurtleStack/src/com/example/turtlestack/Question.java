package com.example.turtlestack;

import java.sql.Date;

import android.util.Log;


public class Question extends Post{
	private int acceptedAnswer;
	private int view;
	private int AnswerCount;
	private int favoriteCount;
	private String tags;
	private String title;

	/**
	 * Empty Constructor
	 */
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param title
	 * @param id
	 * @param postTypeId
	 * @param creationDate
	 * @param score
	 * @param body
	 * @param ownerUserId
	 * @param communityOwnedDate
	 * @param tags
	 */
	public Question(String title, int id, int postTypeId, String creationDate,
			String body, int ownerUserId, String tags) {
		super(id, postTypeId, creationDate, body, ownerUserId);
		this.title = title;
		this.tags = tags;
		// TODO Auto-generated constructor stub
	}
	
	//We have to modify it for implement current user id
	public  Question(String title, String body,String tags) {
		super(4,1,null,body,10);
		java.util.Date currentDate = new java.util.Date();
		Date date = new Date(currentDate.getTime());
		this.setCreationDate(date.toString());
		this.setLastActivityDate(date.toString());
		this.title = title;
		this.tags = tags;
	}
	/**
	 * @return the acceptedAnswer
	 */
	public int getAcceptedAnswer() {
		return acceptedAnswer;
	}
	/**
	 * @param acceptedAnswer the acceptedAnswer to set
	 */
	public void setAcceptedAnswer(int acceptedAnswer) {
		this.acceptedAnswer = acceptedAnswer;
	}
	/**
	 * @return the view
	 */
	public int getView() {
		return view;
	}
	/**
	 * @param view the view to set
	 */
	public void setView(int view) {
		this.view = view;
	}
	/**
	 * @return the answerCount
	 */
	public int getAnswerCount() {
		return AnswerCount;
	}
	/**
	 * @param answerCount the answerCount to set
	 */
	public void setAnswerCount(int answerCount) {
		AnswerCount = answerCount;
	}
	/**
	 * @return the favoriteCount
	 */
	public int getFavoriteCount() {
		return favoriteCount;
	}
	/**
	 * @param favoriteCount the favoriteCount to set
	 */
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
