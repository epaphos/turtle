package com.example.turtlestack;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

public class TagCloudOnPageListener implements OnPageChangeListener {

	int currentIndex = 0;

	@Override
	public void onPageScrollStateChanged(int arg0) {
		//Do Nothing
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		//No Nothing
	}

	@Override
	public void onPageSelected(int arg0) {
		currentIndex = arg0;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}
}