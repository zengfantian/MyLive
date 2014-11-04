package cn.com.hoonsoft.tool.http;

import org.apache.http.Header;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;

public abstract class JSONHandler extends JsonHttpResponseHandler {

	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
		super.onSuccess(statusCode, headers, response);
		success(response);
	}

	public void onFailure(int statusCode, Header[] headers,String responseBody, Throwable e) {
		super.onFailure(statusCode, headers, responseBody, e);
		failure(statusCode, responseBody, e);
	}

	public abstract void success(JSONObject jsonObject);
	
	public abstract void failure(int statusCode, String responseBody,Throwable e);

}
