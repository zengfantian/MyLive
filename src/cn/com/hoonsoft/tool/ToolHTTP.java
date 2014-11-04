package cn.com.hoonsoft.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import cn.com.hoonsoft.tool.http.BinaryHandler;
import cn.com.hoonsoft.tool.http.JSONArrayHandler;
import cn.com.hoonsoft.tool.http.JSONHandler;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public abstract class ToolHTTP {

	public static AsyncHttpClient client = new AsyncHttpClient();

	public static void get(String url, Map parmsMap, BinaryHandler binaryHandler) {
		if(checkNetwork()){
			client.get(url, fillParms(parmsMap), binaryHandler);
		}
	}
	
	public static void get(String url, Map parmsMap, JSONHandler jsonHandler) {
		if(checkNetwork()){
			//关闭过期连接.
		    client.getHttpClient().getConnectionManager().closeExpiredConnections();
			client.get(url, fillParms(parmsMap), jsonHandler);
		}
	}

	public static void get(String url, Map parmsMap,
			JSONArrayHandler jsonHandler) {
		if(checkNetwork()){
			//关闭过期连接.
		    client.getHttpClient().getConnectionManager().closeExpiredConnections();
			client.get(url, fillParms(parmsMap), jsonHandler);
		}
	}
	
	public static void post(String url, Map parmsMap, BinaryHandler binaryHandler) {
		if(checkNetwork()){
			//关闭过期连接.
		    client.getHttpClient().getConnectionManager().closeExpiredConnections();
			client.post(url, fillParms(parmsMap), binaryHandler);
		}
	}
	
	public static void post(String url, Map parmsMap, JSONHandler jsonHandler) {
		if(checkNetwork()){
			//关闭过期连接.
		    client.getHttpClient().getConnectionManager().closeExpiredConnections();
			client.post(url, fillParms(parmsMap), jsonHandler);
		}
	}

	public static void post(String url, Map parmsMap,
			JSONArrayHandler jsonHandler) {
		if(checkNetwork()){
			//关闭过期连接.
		    client.getHttpClient().getConnectionManager().closeExpiredConnections();
			client.post(url, fillParms(parmsMap), jsonHandler);
		}
	}

	/**
	 * 装填参数
	 * @param map 参数
	 * @return 
	 */
	private static RequestParams fillParms(Map map) {
		RequestParams params = new RequestParams();
		if(null != map && map.entrySet().size() > 0)
		{
			for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) 
			{
				Entry entity = (Entry) iterator.next();
				Object key = entity.getKey();
				Object value = entity.getValue();
				if (value instanceof File) {
					try {
						//params.put((String) key, (File) value);用不了 AsyncHttp算错字节少了
						params.put((String) key, new FileInputStream((File) value),((File) value).getName());
					} catch (FileNotFoundException e) {
						throw new RuntimeException("文件不存在！", e);
					}
				} else if (value instanceof InputStream) {
					//用不了 少文件名
					params.put((String) key, (InputStream) value);
				} else {
					params.put((String) key, value.toString());
				}
			}
		}
		return params;
	}

	/**
	 * 检测网络状态
	 * @return 网络是否连接
	 */
	public static boolean checkNetwork(){
		boolean isConnected = ToolNetwork.getInstance(ToolApplication.gainContext()).isConnected();
		if(isConnected){
			return true;
		}else{
			ToolAlert.showShort("网络连接失败");
			return false;
		}
	}
	
}
