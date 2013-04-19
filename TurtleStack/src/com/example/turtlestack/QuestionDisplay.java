package com.example.turtlestack;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class QuestionDisplay extends Activity {
	PostDataSource ds;
	int questionId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        int questionId =  bundle.getInt("Id");
        ds = PostDataSource.getInstance(this);
		ds.open();
		setContentView(R.layout.activity_question_display);
		Button postButton = (Button) findViewById(R.id.button1);		
		postButton.setOnClickListener(listener);
		ds.close();
	}
	
	View.OnClickListener listener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			answerAction(v);
		}
		};
	
	public void answerAction(View v) {
		Intent i = new Intent(this,AnswerActivity.class);
		Question question = (Question) ds.readPost(questionId);
		i.putExtra("parentId",question.getId());		
		startActivity(i);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question_display, menu);
		return true;
	}
}