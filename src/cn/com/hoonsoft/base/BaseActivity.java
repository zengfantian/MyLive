package cn.com.hoonsoft.base;

import java.util.Map;
import java.util.Stack;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public abstract class BaseActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activityStack.push(this);
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
		initIntent();
		initView();
		create(savedInstanceState);
	}

	/**
	 * 初始化参数
	 */
	protected abstract void initIntent();

	/**
	 * 初始化控件
	 */
	protected abstract void initView();
	
	/**
	 * 初始化控件
	 */
	protected void initView(Map<Integer,Class> value){
		
	}
	
	/**
	 * 查找控件
	 */
	protected <T> T findViewById(Class<T> view,int id) {
    	return (T) findViewById(id);
    }
	
	/**
	 * 创建
	 */
	protected abstract void create(Bundle savedInstanceState);

	// ////////
	private Intent intent = new Intent();

	public void addIntentValue(String name, String value) {
		intent.putExtra(name, value);
	}

	public void startActivity(Class oneClass) {
		intent.setClass(BaseActivity.this, oneClass);
		this.startActivity(intent);

	}

	public String gainString(String name) {
		return this.getIntent().getStringExtra(name);
	}

	// //////////
	public static Stack<Activity> activityStack = new Stack<Activity>();

	protected void onDestroy() {
		activityStack.remove(this);
		super.onDestroy();
	}

	protected Activity gainFrontActivity() {
		return activityStack.get(activityStack.size() - 2);
	}

	protected Activity gainFirstActivity() {
		return activityStack.get(0);
	}

	protected void finishActivityToFirst() {
		int end = activityStack.size();
		int start = 1;
		for (int i = end - 1; i >= start; i--) {
			activityStack.get(i).finish();
		}

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
