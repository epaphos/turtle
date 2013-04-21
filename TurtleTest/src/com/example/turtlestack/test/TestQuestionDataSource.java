package com.example.turtlestack.test;

import java.util.ArrayList;

import junit.framework.Assert;
import android.test.AndroidTestCase;
import com.example.turtlestack.*;

public class TestQuestionDataSource extends AndroidTestCase {

	QuestionDataSource datasource;
	
	public TestQuestionDataSource() {
		super();
	}

	protected void setUp() throws Exception {
        super.setUp();
		datasource = QuestionDataSource.getInstance(getContext());
		datasource.open();		
    }
	
	public void testGetRecent() {
		ArrayList<Question> list = datasource.getRecentQuestions(5);
		Assert.assertEquals(list.size(), 5);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
