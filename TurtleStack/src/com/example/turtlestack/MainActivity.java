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
		Button postButton = (Button) findViewById(R.id.button1);
		postButton.setOnClickListener(postButtonListener);
		}
	

		View.OnClickListener postButtonListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launch(v);
			}
		};
	
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
	
	/*public void sendMessage(View view) {
		Intent intent = new Intent(this, QuestionActivity.class);
		EditText editText = (EditText) findViewById(R.id.text);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}*/
}
	
