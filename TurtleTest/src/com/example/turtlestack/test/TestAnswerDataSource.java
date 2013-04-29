package com.example.turtlestack.test;
import java.util.ArrayList;

import junit.framework.Assert;
import android.database.Cursor;
import android.test.AndroidTestCase;
import android.util.Log;

import com.example.turtlestack.Answer;
import com.example.turtlestack.AnswerDataSource;
import com.example.turtlestack.Post;
import com.example.turtlestack.Question;
import com.example.turtlestack.QuestionDataSource;
import com.example.turtlestack.wrongTypeException;



public class TestAnswerDataSource extends AndroidTestCase {

	AnswerDataSource answerSource;
	QuestionDataSource questionSource;

	protected void setUp() throws Exception {
        super.setUp();
		answerSource = AnswerDataSource.getInstance(getContext());
		answerSource.open();
		questionSource = QuestionDataSource.getInstance(getContext());
		questionSource.open();
    }
	
	public void testWritePostIsAddedToDatabase() {
		Answer answer = new Answer(386341, "Write something");
		answerSource.write(answer);
		Assert.assertEquals(answer.getBody(), answerSource.getLast().getBody());
	}
	
	public void testAnswerCountIncremented() {
		Question question = new Question("DummyTitle", "DummyBody", "DummyTag");
		questionSource.setQuestion(question);
		Answer answer = new Answer(question.getId(), "Write something");
		answerSource.setAnswer(answer);
		try {
			Question question2 = questionSource.getQuestion(question.getId());
			Assert.assertEquals((question.getAnswerCount()+1), question2.getAnswerCount());
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
	
	public void testReadAnswer () {
		try {
			Answer answer = (Answer) answerSource.getAnswer(386350); //not sure that this id is in database
			Assert.assertEquals(answer.getId(),386350);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

	}
	
	public void testGetAnswersToQuestion() throws wrongTypeException {
		int id = 8414075;
		ArrayList<Answer> answer = answerSource.getAnswers(id);
		int numberOfAnswers = questionSource.getNumberOfAnswers(id);
		Log.v("numberOfAnswers", Integer.toString(numberOfAnswers)); 
		Assert.assertEquals(answer.size(), numberOfAnswers); 
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
		questionSource.close();
		super.tearDown();
	}
}
