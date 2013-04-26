package com.example.turtlestack;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AnswerActivity extends Activity {
	AnswerDataSource ds;
	int parentId;
     
	@SuppressLint("NewApi")   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_act);
        Bundle bundle = getIntent().getExtras();
        int parentId =  bundle.getInt("parentId");
        ds = AnswerDataSource.getInstance(this);
		ds.open();
		Button postButton = (Button) findViewById(R.id.button1);
		postButton.setOnClickListener(postButtonListener);
        ds.close();

     // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
	View.OnClickListener postButtonListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
	    	EditText mEdit = (EditText) findViewById(R.id.text);
	    	String body  = mEdit.getText().toString();
			Answer answer = new Answer(parentId,body);
			ds.setAnswer(answer);
			back(v);
		}
	};
	
	public void back(View v) {
		Intent i = new Intent(this,MainActivity.class);
		startActivity(i);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}