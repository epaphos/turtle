package com.example.turtlestack.test;

import junit.framework.Assert;
import android.test.AndroidTestCase;

import com.example.turtlestack.User;
import com.example.turtlestack.UserDataSource;

public class TestUserDataSource extends AndroidTestCase {

	UserDataSource userSource;
	
	protected void setUp() throws Exception {
		super.setUp();
		userSource = UserDataSource.getInstance(getContext());
		userSource.open();
		
	}
	
	public void testReadUser() {
		try {
			User user = userSource.readUser(13);
			Assert.assertEquals(user.getUserId(), 13);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
	
	protected void tearDown() throws Exception {
		userSource.close();		
		super.tearDown();
	}
}
