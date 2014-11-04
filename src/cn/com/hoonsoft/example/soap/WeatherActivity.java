package cn.com.hoonsoft.example.soap;

import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.config.URLConfig;
import cn.com.hoonsoft.tool.ToolAlert;
import cn.com.hoonsoft.tool.ToolSOAP;

/**
 * 显示天气的Activity
 * 
 * @see http://blog.csdn.net/xiaanming
 * 
 * @author xiaanming
 *
 */
public class WeatherActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_soap_weather);
		init();
	}

	private void init() {
		final TextView mTextWeather = (TextView) findViewById(R.id.weather);
		ToolAlert.showLoading(this, "数据加载中...");
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("theCityName", getIntent().getStringExtra("city"));
		
		ToolSOAP.callService(URLConfig.WEB_SERVER_URL,URLConfig.NAME_SPACE,"getWeatherbyCityName", properties, new ToolSOAP.WebServiceCallBack() {
			
			@Override
			public void onSucced(SoapObject result) {
				ToolAlert.closeLoading();
				if(result != null){
					SoapObject detail = (SoapObject) result.getProperty("getWeatherbyCityNameResult");
					StringBuilder sb = new StringBuilder();
					for(int i=0; i<detail.getPropertyCount(); i++){
						sb.append(detail.getProperty(i)).append("\r\n");
					}
					mTextWeather.setText(sb.toString());
				}else{
					Toast.makeText(WeatherActivity.this, "获取WebService数据错误", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(String result) {
				Toast.makeText(WeatherActivity.this, "获取WebService数据错误，原因："+result, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
