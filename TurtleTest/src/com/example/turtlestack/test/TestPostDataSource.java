package com.example.turtlestack.test;
import junit.framework.Assert;
import android.test.AndroidTestCase;

import com.example.turtlestack.PostDataSource;
import com.example.turtlestack.Question;


public class TestPostDataSource extends AndroidTestCase {

	PostDataSource datasource;

	protected void setUp() throws Exception {
        super.setUp();
		datasource = PostDataSource.getInstance(getContext());
		datasource.open();		
    }
	
	public void testWritePostIsAddedToDatabase() {
		Question question = new Question("title", "Lorem ipsum dolor sit amet", "tag");
		datasource.writePost(question);
		Assert.assertEquals(question.getBody(), datasource.getLastPost().getBody());
	}

	protected void tearDown() throws Exception {
		datasource.close();		
		super.tearDown();
	}
}
