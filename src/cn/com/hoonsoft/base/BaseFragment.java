package cn.com.hoonsoft.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initIntent();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = initView(inflater, container);
		create(savedInstanceState);
		return view;
	}

	/**
	 * 初始化参数
	 */
	protected abstract void initIntent();

	/**
	 * 初始化控件
	 */
	protected abstract View initView(LayoutInflater inflater,
			ViewGroup container);

	/**
	 * 创建
	 */
	protected abstract void create(Bundle savedInstanceState);

	// //////////////////////////////////
	private Intent intent = new Intent();

	public void addIntentValue(String name, String value) {
		intent.putExtra(name, value);
	}

	public void startActivity(Class oneClass) {
		intent.setClass(this.getActivity(), oneClass);
		this.getActivity().startActivity(intent);

	}

	public String gainString(String name) {
		return this.getActivity().getIntent().getStringExtra(name);
	}

	// ///////
	protected Activity gainFrontActivity() {
		return BaseActivity.activityStack
				.get(BaseActivity.activityStack.size() - 2);
	}

	protected Activity gainFirstActivity() {
		return BaseActivity.activityStack.get(0);
	}

	protected void finishActivityToFirst() {
		int end = BaseActivity.activityStack.size();
		int start = 1;
		for (int i = end - 1; i >= start; i--) {
			BaseActivity.activityStack.get(i).finish();
		}

	}

	// ///////
	private ProgressDialog progressDialog = null;

	public void showWait() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this.getActivity());
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
