package cn.com.hoonsoft.tool.http;

import org.apache.http.Header;
import org.json.JSONArray;

import com.loopj.android.http.JsonHttpResponseHandler;

public abstract class JSONArrayHandler extends JsonHttpResponseHandler {

	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
		super.onSuccess(statusCode, headers, response);
		success(response);
	}

	public void onFailure(int statusCode, Header[] headers,String responseBody, Throwable e) {
		super.onFailure(statusCode, headers, responseBody, e);
		failure(statusCode, responseBody, e);
	}
	
	public abstract void success(JSONArray jsonArray);
	
	public abstract void failure(int statusCode, String responseBody,Throwable e);

}
