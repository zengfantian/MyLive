package cn.com.hoonsoft.example.ui360.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import cn.com.hoonsoft.example.ui360.FilesFragment;
import cn.com.hoonsoft.example.ui360.FindFragment;

public class ContentPagerAdapter extends FragmentPagerAdapter {

	private Fragment[] fragments;

	public ContentPagerAdapter(FragmentManager fm) {
		super(fm);
		fragments = new Fragment[2];
		fragments[0] = new FindFragment();
		fragments[1] = new FilesFragment();
	}

	@Override
	public Fragment getItem(int position) {
		return fragments[position];
	}

	@Override
	public int getCount() {
		return fragments.length;
	}
}