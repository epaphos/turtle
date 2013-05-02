package com.example.turtlestack;

public class Tag {
	private String tag;

	/**
	 * @param tag
	 */
	public Tag(String tag) {
		this.tag = tag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.tag;
	}
}
