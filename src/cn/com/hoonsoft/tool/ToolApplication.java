package cn.com.hoonsoft.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

//
public class ToolApplication extends Application {

	private static Context instance;
	
	public static Stack<Activity> activitys = new Stack<Activity>(); 

	public static Context gainContext() {
		return instance;
	}

	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	private static Map<String, Object> data = new HashMap<String, Object>();

	public static void assignData(String strKey, Object strValue) {
		if (data.size() > 5) {
			throw new RuntimeException("超过允许最大数");
		}
		data.put(strKey, strValue);
	}

	public static Object gainData(String strKey) {
		return data.get(strKey);
	}
	
	public static void pushTask(Activity task){
		activitys.push(task);
	}
	
	public static void removeTask(Activity task){
		activitys.remove(task);
	}
	
	public static void removeTask(int taskIndex){
		if(activitys.size() > taskIndex)
			activitys.remove(taskIndex);
	}
	
	public static void removeToTop(){
		int end = activitys.size();
		int start = 1;
		for (int i = end - 1; i >= start; i--) {
			activitys.get(i).finish();
		}
	}
	
	public static void removeAll(){
		for (Activity task : activitys) {
			task.finish();
		}
	}
}
