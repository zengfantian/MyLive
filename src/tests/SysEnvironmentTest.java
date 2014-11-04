package tests;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.test.AndroidTestCase;
import android.util.Log;
import cn.com.hoonsoft.model.SysEnv;

public class SysEnvironmentTest extends AndroidTestCase{

	private static final String TAG = SysEnvironmentTest.class.getSimpleName();
	
	public void testGetVersion() throws Throwable{
		String version = SysEnv.getKernelVersion();
		
		Map map = new HashMap();
		map.put("name", "JackSon");
		map.put("age", 12);
		map.put("isMan", false);
		map.put("school", "Nan Kai");
		
		JSONObject json = new JSONObject(map);
		Log.i(TAG, "json.optString(\"type\")="+json.optString("type"));
		Log.i(TAG, "json.optString(\"type\",\"no type\")="+json.optString("type","no type"));
		Log.i(TAG, "getKernelVersion="+version);
	}
	
	public void testGetMacAddress() throws Throwable{
		String mac = SysEnv.getMacAddress();
		Log.i(TAG, "WIFI  mac 地址 --> "+mac);
	}
	
	public void testGetRunTimes() throws Throwable{
		long strTime = SysEnv.getRunTimes();
		int m = (int) ((strTime / 60) % 60);
		int h = (int) ((strTime / 3600));
		Log.i(TAG, "系统运行时长为："+(h+"小时"+m+"分钟"));
	}
	
	public void testSysEnvironment() throws Throwable{
		Log.i(TAG, "appVersion="+SysEnv.APP_VERSION);
		Log.i(TAG, "displayName="+SysEnv.DISPLAY_NAME);
		Log.i(TAG, "modelNumber="+SysEnv.MODEL_NUMBER);
		Log.i(TAG, "osVersion="+SysEnv.OS_VERSION);
		Log.i(TAG, "getKernelVersion()="+SysEnv.getKernelVersion());
		Log.i(TAG, "getMacAddress="+SysEnv.getMacAddress());
		Log.i(TAG, "getRunTimes="+SysEnv.getRunTimes());
		Log.i(TAG, "screenHeight="+SysEnv.SCREEN_HEIGHT);
		Log.i(TAG, "screenWidth="+SysEnv.SCREEN_WIDTH);
		
		Log.i(TAG, "phone_number="+SysEnv.PHONE_NUMBER);
		Log.i(TAG, "device_id="+SysEnv.DEVICE_ID);
		Log.i(TAG, "imei="+SysEnv.IMEI);
		Log.i(TAG, "imsi="+SysEnv.IMSI);
	}
	
}
