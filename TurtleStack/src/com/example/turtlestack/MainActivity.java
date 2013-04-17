package com.example.turtlestack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//PostDataSource ds = new PostDataSource(this);
		//Log.v("question", ds.getPost(8414075).getBody());
		//Question question = new Question("Die Hard", 1, 1, "2013-04-16", "some stuff", 1, "<HTML><MAC><PHONE>");
		//Answer answer = new Answer(2, 2, "2013-04-16", "random answer", 2,1);
		//ds.setPost(answer);
		//Log.v("Value of body", question.getBody());
		//ds.setPost(question);
		//Log.v("question",ds.getPost(1).getBody());
		//Log.v("answer",ds.getPost(2).getBody());
		//Log.v("max post",Integer.toString(ds.newId()));
		}
	
	public void onClick(View v) {
		Button clickedButton = (Button) v;
	    setContentView(0);
	    switch(clickedButton.getId()) {
	    	case R.id.button1:
    		//	launch(v);
            break;
        }
    }
	
	public void launch(View v) {
		Intent i = new Intent(this,QuestionActivity.class);
		startActivity(i);
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendMessage(View view) {
		Intent intent = new Intent(this, QuestionActivity.class);
		EditText editText = (EditText) findViewById(R.id.text);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
}
