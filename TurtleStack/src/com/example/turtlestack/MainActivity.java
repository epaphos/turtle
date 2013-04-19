package com.example.turtlestack;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button postButton = (Button) findViewById(R.id.button1);		
		Button recentQuestionsButton = (Button) findViewById(R.id.recentquestions);
		postButton.setOnClickListener(listener);
		recentQuestionsButton.setOnClickListener(listener);
		}

		View.OnClickListener listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Button clickedButton = (Button) v;
				switch(clickedButton.getId()) {
				case R.id.button1 :
					launch(v, QuestionActivity.class);
					break;
				case R.id.recentquestions : 
					launch(v, BrowseActivity.class);
					break;
				}
				
			}
		};
	
	public void launch(View v, Class<?> c) {
		Intent i = new Intent(this, c);
		startActivity(i);
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/*public void sendMessage(View view) {
		Intent intent = new Intent(this, QuestionActivity.class);
		EditText editText = (EditText) findViewById(R.id.text);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}*/
	

	//Function that gets called by button2
	public void gotoPostMessage(View view){
		Intent intent = new Intent(this, QuestionActivity.class);
		startActivity(intent);
	}
	//Function that gets called by button4
	public void gotoDetailedQuestion(View view){
		Intent intent = new Intent(this, QuestionDisplay.class);
		startActivity(intent);
	}
}
	
