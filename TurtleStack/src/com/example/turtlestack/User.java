/**
 * 
 */
package com.example.turtlestack;

/**
 * @author 
 *
 */
public class User {
	private int userId;
	private int reputation;
	private String creationDate;
	private String displayName;
	private String emailHash; //what to do with this?
	private String lastAccessDate;
	private String websiteURL;
	private String location;
	private int age;
	private String aboutMe;
	private int views;
	private int upVotes;
	private int downVotes;
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", reputation=" + reputation
				+ ", creationDate=" + creationDate + ", displayName="
				+ displayName + ", emailHash=" + emailHash
				+ ", lastAccessDate=" + lastAccessDate + ", websiteURL="
				+ websiteURL + ", location=" + location + ", age=" + age
				+ ", aboutMe=" + aboutMe + ", views=" + views + ", upVotes="
				+ upVotes + ", downVotes=" + downVotes + "]";
	}

	/**
	 * @param userId
	 */
	public User(int userId) {
		super();
		this.userId = userId;
	}

	/**
	 * @param userId
	 * @param reputation
	 * @param creationDate
	 * @param displayName
	 * @param emailHash
	 * @param lastAccessDate
	 * @param websiteURL
	 * @param location
	 * @param age
	 * @param aboutMe
	 * @param views
	 * @param upVotes
	 * @param downVotes
	 */
	public User(int userId, int reputation, String creationDate,
			String displayName, String emailHash, String lastAccessDate,
			String websiteURL, String location, int age, String aboutMe,
			int views, int upVotes, int downVotes) {
		super();
		this.userId = userId;
		this.reputation = reputation;
		this.creationDate = creationDate;
		this.displayName = displayName;
		this.emailHash = emailHash;
		this.lastAccessDate = lastAccessDate;
		this.websiteURL = websiteURL;
		this.location = location;
		this.age = age;
		this.aboutMe = aboutMe;
		this.views = views;
		this.upVotes = upVotes;
		this.downVotes = downVotes;
	}

	/**
	 * @return the reputation
	 */
	public int getReputation() {
		return reputation;
	}

	/**
	 * @param reputation the reputation to set
	 */
	public void setReputation(int reputation) {
		this.reputation = reputation;
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
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the emailHash
	 */
	public String getEmailHash() {
		return emailHash;
	}

	/**
	 * @param emailHash the emailHash to set
	 */
	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}

	/**
	 * @return the lastAccessDate
	 */
	public String getLastAccessDate() {
		return lastAccessDate;
	}

	/**
	 * @param lastAccessDate the lastAccessDate to set
	 */
	public void setLastAccessDate(String lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	/**
	 * @return the websiteURL
	 */
	public String getWebsiteURL() {
		return websiteURL;
	}

	/**
	 * @param websiteURL the websiteURL to set
	 */
	public void setWebsiteURL(String websiteURL) {
		this.websiteURL = websiteURL;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the aboutMe
	 */
	public String getAboutMe() {
		return aboutMe;
	}

	/**
	 * @param aboutMe the aboutMe to set
	 */
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	/**
	 * @return the views
	 */
	public int getViews() {
		return views;
	}

	/**
	 * @param views the views to set
	 */
	public void setViews(int views) {
		this.views = views;
	}

	/**
	 * @return the upVotes
	 */
	public int getUpVotes() {
		return upVotes;
	}

	/**
	 * @param upVotes the upVotes to set
	 */
	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}

	/**
	 * @return the downVotes
	 */
	public int getDownVotes() {
		return downVotes;
	}

	/**
	 * @param downVotes the downVotes to set
	 */
	public void setDownVotes(int downVotes) {
		this.downVotes = downVotes;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
