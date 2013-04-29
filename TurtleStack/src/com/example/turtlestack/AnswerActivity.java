package com.example.turtlestack;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AnswerActivity extends Activity {
	AnswerDataSource ds;
	int parentId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer);
        ds = AnswerDataSource.getInstance(this);
		ds.open();
		Intent intent = getIntent();
		parentId = intent.getIntExtra("parentId", 0);
		ds.close();
	}
	
	public void postAnswerButton(View v) {
    	EditText mEdit = (EditText) findViewById(R.id.text);
    	String body  = mEdit.getText().toString();
		Answer answer = new Answer(parentId,body);
		ds.setAnswer(answer);
		back(v);
	}
	
	public void back(View v) {
		Intent i = new Intent(this,MainActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.answer, menu);
		return true;
	}

}
