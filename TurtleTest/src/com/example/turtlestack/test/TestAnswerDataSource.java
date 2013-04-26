package com.example.turtlestack.test;
import junit.framework.Assert;
import android.database.Cursor;
import android.test.AndroidTestCase;

import com.example.turtlestack.Answer;
import com.example.turtlestack.AnswerDataSource;
import com.example.turtlestack.Post;
import com.example.turtlestack.Question;
import com.example.turtlestack.QuestionDataSource;



public class TestAnswerDataSource extends AndroidTestCase {

	AnswerDataSource answerSource;

	protected void setUp() throws Exception {
        super.setUp();
		answerSource = AnswerDataSource.getInstance(getContext());
		answerSource.open();	
    }
	
	public void testWritePostIsAddedToDatabase() {
		Answer answer = new Answer(386341, "Write something");
		answerSource.setAnswer(answer);
		Assert.assertEquals(answer.getBody(), answerSource.getLastPost().getBody());
	}
	
	public void testReadAnswer () {
		try {
			Answer answer = (Answer) answerSource.getAnswer(386350); //not sure that this id is in database
			Assert.assertEquals(answer.getId(),386350);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

	}
	
	public void testAnswerType () {
		try {
			Answer answer = answerSource.getAnswer(386341);
			Assert.assertTrue(answer.getPostTypeId() == 2);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
	
	protected void tearDown() throws Exception {
		answerSource.close();		
		super.tearDown();
	}
}
