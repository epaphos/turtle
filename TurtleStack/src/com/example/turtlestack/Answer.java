package com.example.turtlestack;

public class Answer extends Post {
	private int parentId;

	/**
	 * @param id
	 * @param postTypeId
	 * @param creationDate
	 * @param score
	 * @param body
	 * @param ownerUserId
	 */
	public Answer(int id, int postTypeId, String creationDate,
			String body, int ownerUserId,int parentID) {
		super(id, postTypeId, creationDate, body, ownerUserId);
		this.parentId = parentId;
	}
	
	public Answer() {
		
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
