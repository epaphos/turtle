package com.example.turtlestack;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author cyrill
 * @since 2013-04-20
 */
public class UserViewActivity extends Activity {

	private UserDataSource ds;
	private User user;
	private int userId;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_view);
		// Get userId from intent
		Intent intent = getIntent();
		userId = intent.getIntExtra("userId", 0);

		ds = UserDataSource.getInstance(this);
		ds.open();
		user = ds.readUser(userId);
		ds.close();
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		displayUser(user);
	}

	/**
	 * Displays the data of a user
	 * 
	 * @param user
	 */
	public void displayUser(User user) {
		ImageView image = (ImageView) findViewById(R.id.profilePicture);
		image.setImageResource(R.drawable.skalman);

		TextView displayName = (TextView) findViewById(R.id.userLblDisplayName_);
		displayName.setText(user.getDisplayName());

		TextView aboutMe = (TextView) findViewById(R.id.AboutMeText);
		aboutMe.setText(user.getAboutMe());

		TextView userLocation = (TextView) findViewById(R.id.userLocation);
		userLocation.setText(user.getLocation());

		TextView creationDate = (TextView) findViewById(R.id.userLblCreationDate);
		creationDate.setText("Joined " + user.getCreationDate());

		TextView age = (TextView) findViewById(R.id.userAge);
		age.setText(user.getAge() + " years old");
		
		TextView userWebsite = (TextView) findViewById(R.id.userWebsite);
		SpannableString url = new SpannableString(user.getWebsiteURL());
		url.setSpan(new UnderlineSpan(), 0, url.length(), 0);
		userWebsite.setText(url);

		TextView reputation = (TextView) findViewById(R.id.userLblReputation);
		reputation.setText(Integer.toString(user.getReputation()));
		setTitle(user.getDisplayName());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_view, menu);
		return true;
	}

}
