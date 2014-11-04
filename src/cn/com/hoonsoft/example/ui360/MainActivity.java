package cn.com.hoonsoft.example.ui360;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.example.ui360.adapter.ContentPagerAdapter;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends FragmentActivity {

	private ViewPager vp_content;
	private TextView tv_find;
	private TextView tv_my_file;
	private SlidingMenu slidingMenu;
	private ImageButton ibtn_trigger;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_ui360_activity_main);
		findView();
		init();
	}

	private void findView() {
		vp_content = (ViewPager) findViewById(R.id.vp_content);
		tv_find = (TextView) findViewById(R.id.tv_find);
		tv_my_file = (TextView) findViewById(R.id.tv_my_file);
		ibtn_trigger = (ImageButton) findViewById(R.id.ibtn_right_menu);
	}

	private void init() {
		vp_content.setAdapter(new ContentPagerAdapter(getSupportFragmentManager()));
		vp_content.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				setCurrentPage(position);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// ignore
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// ignore
			}
		});

		ibtn_trigger.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				slidingMenu.toggle();
			}
		});

		slidingMenu = new SlidingMenu(this);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE); // 滑动方式
		slidingMenu.setShadowDrawable(R.drawable.shadow_right); // 阴影
		slidingMenu.setShadowWidth(30); // 阴影宽度
		slidingMenu.setBehindOffset(80); // 前面的视图剩下多少
		slidingMenu.setMode(SlidingMenu.RIGHT); // 左滑出不是右滑出
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.demo_ui360_menu_frame); // 设置menu容器
		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction().replace(R.id.menu_frame, new MenuFragment()).commit();
	}

	// 按下返回键时
	@Override
	public void onBackPressed() {
		if (slidingMenu != null && slidingMenu.isMenuShowing()) {
			slidingMenu.showContent();
		} else {
			super.onBackPressed();
		}
	}

	// 按下菜单键时
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			slidingMenu.toggle();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void setCurrentPage(int current) {
		if (current == 0) {
			tv_find.setBackgroundResource(R.drawable.title_menu_current);
			tv_find.setTextColor(getResources().getColor(R.color.blue));
			tv_my_file.setBackgroundResource(R.drawable.title_menu_bg);
			tv_my_file.setTextColor(getResources().getColor(R.color.grey));
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		} else {
			tv_my_file.setBackgroundResource(R.drawable.title_menu_current);
			tv_my_file.setTextColor(getResources().getColor(R.color.blue));
			tv_find.setBackgroundResource(R.drawable.title_menu_bg);
			tv_find.setTextColor(getResources().getColor(R.color.grey));
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}
	}
}