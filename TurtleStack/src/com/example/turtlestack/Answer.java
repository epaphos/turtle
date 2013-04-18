package com.example.turtlestack;

public class Answer extends Post {
	private int parentId = 0; //set on creation

	//Empty Constructor
	public Answer() {
		
	}
	
	//New Answer Constructor
	public Answer(int parentId,String body) {
		super(body);
		this.setPostTypeId(2);
		this.parentId = parentId; //we have to implement it
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
	//Existing Answer Constructor
	public Answer(PostDataSource ds,int id, int postTypeId, String creationDate, int score,
			String body, int ownerUserId, int lastEditorUserId,
			String lastEditorUserName, String lastEditDate,
			String lastActivityDate, String communityOwnedDate,
			String closedDate, int commentCount, int parentId) {
		super(postTypeId, creationDate, score, body, ownerUserId, lastEditorUserId,
				lastEditorUserName, lastEditDate, lastActivityDate, communityOwnedDate,
				closedDate, commentCount);
		this.parentId = parentId;//we have to implement it
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + ", parentId=" + parentId + "]";
	}

	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
