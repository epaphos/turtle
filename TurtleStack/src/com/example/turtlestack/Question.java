package com.example.turtlestack;

import java.util.ArrayList;
import java.util.Iterator;

import android.util.Log;

public class Question extends Post {
	private int acceptedAnswer;
	private int viewCount;
	private String title; //Set in creation
	private String tags; //Set in creation
	private int answerCount;
	private int favoriteCount;

	/**
	 * Empty Constructor
	 */
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	//New Question Constructor
	public Question(String title, String body, String tags, int userId) {
		super(body);
		this.ownerUserId = userId;
		this.setPostTypeId(1);
		this.viewCount = 0;
		this.title = title;
		this.tags = tags;
		this.answerCount = 0;
	}

	/**
	 * @param id
	 * @param postTypeId
	 * @param creationDate
	 * @param score
	 * @param body
	 * @param ownerUserId
	 * @param lastEditorUserId
	 * @param lastEditorUserName
	 * @param lastEditDate
	 * @param lastActivityDate
	 * @param communityOwnedDate
	 * @param closedDate
	 * @param commentCount
	 */
	//Existing Question Constructor
	public Question(int id, int postTypeId, String creationDate, int score,
			String body, int ownerUserId, int lastEditorUserId,
			String lastEditorUserName, String lastEditDate,
			String lastActivityDate, String communityOwnedDate,
			String closedDate, int commentCount,int acceptedAnswer,
			int viewCount, String title, String tags, int answerCount, int favoriteCount ) {
		super(id,postTypeId, creationDate, score, body, ownerUserId, lastEditorUserId,
				lastEditorUserName, lastEditDate, lastActivityDate, communityOwnedDate,
				closedDate, commentCount);
		this.acceptedAnswer = acceptedAnswer;
		this.viewCount = viewCount;
		this.title = title;
		this.tags = tags;
		this.answerCount = answerCount;
		this.favoriteCount = favoriteCount;
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<Question> intersection(ArrayList<Question> q1,ArrayList<Question> q2) {
		ArrayList<Question> returnValue = new ArrayList();
		Iterator i = q1.iterator();
		while(i.hasNext()) {
			Question target = (Question) i.next();
			if(q2.contains(target)) returnValue.add(target);
			}
		
		return returnValue;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + ", acceptedAnswer=" + acceptedAnswer + ", viewCount="
				+ viewCount + ", title=" + title + ", tags=" + tags
				+ ", answerCount=" + answerCount + ", favoriteCount="
				+ favoriteCount + "]";
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
	public int getViewCount() {
		return viewCount;
	}
	/**
	 * @param view the view to set
	 */
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	/**
	 * @return the answerCount
	 */
	public int getAnswerCount() {
		return answerCount;
	}
	/**
	 * @param answerCount the answerCount to set
	 */
	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
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
	@Override
	public boolean equals(Object another) {
		Question question = (Question) another;
		if(this.getId() == question.getId()) return true;
		return false;
	}

}
