package cn.com.hoonsoft.base;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import cn.com.hoonsoft.R;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * 滑块基类Activity(手势识别)
 * 
 * @author Java
 * @version 1.0
 */
public class BaseSlidingActivity extends SlidingFragmentActivity {

	protected SlidingMenu slidingMenu;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		BaseActivity.activityStack.push(this);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 设置屏幕方向
		// ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE（横屏） 或
		// ActivityInfo.SCREEN_ORIENTATION_PORTRAIT（竖屏）
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 设置不自动弹出软件盘
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	protected void onDestroy() {
		BaseActivity.activityStack.remove(this);
		slidingMenu.destroyDrawingCache();
		super.onDestroy();
	}

	// ///////
	private ProgressDialog progressDialog = null;

	public void showWait() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this);
		}
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置风格为圆形进度
		progressDialog.setMessage("Loading...");
		progressDialog.show();
	}

	public void stopWait() {
		progressDialog.dismiss();
	}

	/**
	 * 初始化滑块左、中、右手势识别
	 * 
	 * @param centerFragment
	 * @param leftFragment
	 * @param rightFragment
	 * @param slidingMod
	 *            mode != LEFT && mode != RIGHT && mode != LEFT_RIGHT
	 */
	public void initSlidingMenu(Fragment centerFragment, Fragment leftFragment,
			Fragment rightFragment, int slidingMod) {

		slidingMenu = getSlidingMenu();
		slidingMenu.setMode(slidingMod);// 设置滑动的方向 向左 、向右或向左向右
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); // 设置从哪里可以触发滑动效果：全屏、边界、不可以
		// slidingMenu.attachToActivity (this, SlidingMenu.SLIDING_CONTENT
		// );//该方法为主键设置滑动的是内容还是整个Windows
		// slidingMenu.setBehindOffset(getWindowManager().getDefaultDisplay().getWidth()
		// //设置菜单占屏幕的比例
		// slidingMenu.setBehindScrollScale(0);//设置滑动时拖拽效果

		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);// 根据dimension资源文件设置阴影的宽度
		slidingMenu.setShadowDrawable(R.drawable.shadow);// 根据资源文件ID来设置滑动菜单的阴影效果
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);// 设置菜单占屏幕的比例
		slidingMenu.setFadeEnabled(true);// 设置滑动时菜单的是否淡入淡出
		slidingMenu.setFadeDegree(0.35f);// 设置淡入淡出的比例
		slidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);// 设置右菜单阴影图片
		// slidingMenu.setShadowWidth (20);// 设置阴影的宽度
		// slidingMenu.setShadowDrawable(R.drawable.shadow);// 设置阴影的图片

		setContentView(R.layout.view_sliding_menu_left);
		setBehindContentView(R.layout.view_sliding_menu_center);
		slidingMenu.setSecondaryMenu(R.layout.view_sliding_menu_right);

		FragmentManager fm = this.getSupportFragmentManager();
		FragmentTransaction fragementTransaction = fm.beginTransaction();
		fragementTransaction.replace(R.id.left_frame, leftFragment);
		fragementTransaction.replace(R.id.center_frame, centerFragment);
		fragementTransaction.replace(R.id.right_frame, rightFragment);
		fragementTransaction.commitAllowingStateLoss();
	}

	public void showLeft() {
		slidingMenu.showMenu();
	}

	public void showCenter() {
		slidingMenu.showContent();
	}

	public void showRight() {
		slidingMenu.showSecondaryMenu();
	}

	/***
	 * 获得焦点
	 * 
	 * @param view
	 *            目标控件
	 */
	public void requestFocus(View view) {
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);
		view.requestFocus();
		view.requestFocusFromTouch();
	}

	/**
	 * 失去焦点
	 * 
	 * @param view
	 *            目标控件
	 */
	public void releaseFouse(View view) {
		view.setFocusable(false);
		view.setFocusableInTouchMode(true);
	}
}
