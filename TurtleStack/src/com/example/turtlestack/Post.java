package com.example.turtlestack;

public class Post {
	
	private int id;
	private int postTypeId;
	private String creationDate;
	private int score = 0;
	private String body;
	private int ownerUserId;
	private int lastEditorUserId;
	private String lastEditorUserName;
	private String lastEditDate;
	private String lastActivityDate;
	private String communityOwnedDate;
	private String closedDate;
	private int commentCount;
	
	/**
	 * @param id
	 * @param postTypeId
	 * @param creationDate
	 * @param score
	 * @param body
	 * @param ownerUserId
	 */
	protected Post(int id, int postTypeId, String creationDate, String body, int ownerUserId) {
		super();
		this.id = id;
		this.postTypeId = postTypeId;
		this.creationDate = creationDate;
		this.lastActivityDate = creationDate;
		this.body = body;
		this.ownerUserId = ownerUserId;
	}
	
	/**
	 * Empty Constructor 
	 */
	protected Post() {}

	/**
	 * @return the id
	 */
	protected int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the postTypeId
	 */
	public int getPostTypeId() {
		return postTypeId;
	}

	/**
	 * @param postTypeId the postTypeId to set
	 */
	public void setPostTypeId(int postTypeId) {
		this.postTypeId = postTypeId;
	}

	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the ownerUserId
	 */
	public int getOwnerUserId() {
		return ownerUserId;
	}

	/**
	 * @param ownerUserId the ownerUserId to set
	 */
	public void setOwnerUserId(int ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	/**
	 * @return the lastEditorUserId
	 */
	public int getLastEditorUserId() {
		return lastEditorUserId;
	}

	/**
	 * @param lastEditorUserId the lastEditorUserId to set
	 */
	public void setLastEditorUserId(int lastEditorUserId) {
		this.lastEditorUserId = lastEditorUserId;
	}

	/**
	 * @return the lastEditorUserName
	 */
	public String getLastEditorUserName() {
		return lastEditorUserName;
	}

	/**
	 * @param lastEditorUserName the lastEditorUserName to set
	 */
	public void setLastEditorUserName(String lastEditorUserName) {
		this.lastEditorUserName = lastEditorUserName;
	}

	/**
	 * @return the lastEditDate
	 */
	public String getLastEditDate() {
		return lastEditDate;
	}

	/**
	 * @param lastEditDate the lastEditDate to set
	 */
	public void setLastEditDate(String lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

	/**
	 * @return the lastActivityDate
	 */
	public String getLastActivityDate() {
		return lastActivityDate;
	}

	/**
	 * @param lastActivityDate the lastActivityDate to set
	 */
	public void setLastActivityDate(String lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	/**
	 * @return the communityOwnedDate
	 */
	public String getCommunityOwnedDate() {
		return communityOwnedDate;
	}

	/**
	 * @param communityOwnedDate the communityOwnedDate to set
	 */
	public void setCommunityOwnedDate(String communityOwnedDate) {
		this.communityOwnedDate = communityOwnedDate;
	}

	/**
	 * @return the closedDate
	 */
	public String getClosedDate() {
		return closedDate;
	}

	/**
	 * @param closedDate the closedDate to set
	 */
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}

	/**
	 * @return the commentCount
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
}