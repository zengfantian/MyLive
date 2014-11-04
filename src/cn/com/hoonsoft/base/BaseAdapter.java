package cn.com.hoonsoft.base;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;

public abstract class BaseAdapter extends android.widget.BaseAdapter {

	private List data = new ArrayList();
	private Activity activity;

	public BaseAdapter() {
		super();
	}
	
	public BaseAdapter(Activity activity) {
		super();
		this.activity = activity;
	}

	public Activity getActivity() {
		return activity;
	}

	// //////////////////////////////////
	private Intent intent = new Intent();

	public void addIntentValue(String name, String value) {
		intent.putExtra(name, value);
	}

	public void startActivity(Class oneClass) {
		intent.setClass(activity, oneClass);
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

	// /////////////
	public void clearData() {
		data.clear();
	}
	
	public void removeItem(int location){
		data.remove(location);
	}

	public void addData(Object objData) {
		data.add(objData);
	}
	
	public List getDataSource(){
		return data;
	}

	private int perPageSize = 10;

	public int gainPageNO() {
		return (getCount() / perPageSize) + 1;
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return data.get(position);
	}

	public long getItemId(int position) {
		return position;
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
