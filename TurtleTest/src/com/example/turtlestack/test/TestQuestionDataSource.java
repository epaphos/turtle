package com.example.turtlestack.test;

import java.util.ArrayList;

import junit.framework.Assert;
import android.test.AndroidTestCase;
import com.example.turtlestack.*;

public class TestQuestionDataSource extends AndroidTestCase {

	QuestionDataSource questionSource;
	
	public TestQuestionDataSource() {
		super();
	}

	protected void setUp() throws Exception {
        super.setUp();
		questionSource = QuestionDataSource.getInstance(getContext());
		questionSource.open();		
    }
	
	public void testPostQuestion(){
		Question question = new Question("some title", "Write something","some tag");
		questionSource.write(question);
		Assert.assertEquals(question.getBody(), questionSource.getLastPost().getBody());
	} 
	public void testReadQuestion () {
		try {
			Question question = (Question) questionSource.getQuestion(8414075);
			Assert.assertEquals(question.getId(),8414075);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}

	public void testReadingQuestion () {
		try {
			Question question = questionSource.getQuestion(387066);
			Assert.assertTrue(question.getPostTypeId() == 1);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
	
	public void testGetLastPost () {
		Question question = new Question("title", "Lorem ipsum dolor sit amet", "tag");
		questionSource.write(question);
		Assert.assertEquals(question.getBody(), questionSource.getLastPost().getBody());
	}
	
	public void testGetRecent() {
		ArrayList<Question> list = questionSource.getRecentQuestions(5);
		Assert.assertEquals(list.size(), 5);
	}

	protected void tearDown() throws Exception {
		questionSource.close();		
		super.tearDown();
	}

}
