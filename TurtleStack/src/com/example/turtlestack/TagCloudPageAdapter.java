package com.example.turtlestack;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class TagCloudPageAdapter extends FragmentStatePagerAdapter{

	List<Fragment> fragments = null;
	public TagCloudPageAdapter(FragmentManager fm) {
		super(fm);
		fragments = new ArrayList<Fragment>();
	}
	public void addFragment(Fragment fragment){
		fragments.add(fragment);
		
	}
	@Override
	public Fragment getItem(int arg0) {
		return fragments.get(arg0);
	}
	@Override
	public int getCount() {
		return fragments.size();
	}
}