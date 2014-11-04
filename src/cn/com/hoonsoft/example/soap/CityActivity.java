package cn.com.hoonsoft.example.soap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.config.URLConfig;
import cn.com.hoonsoft.tool.ToolAlert;
import cn.com.hoonsoft.tool.ToolSOAP;

/**
 * 显示城市的Activity
 * 
 * @see http://blog.csdn.net/xiaanming
 * 
 * @author xiaanming
 *
 */
public class CityActivity extends Activity {
	private List<String> cityStringList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_soap_main);
		init();
	}

	private void init() {
		final ListView mCityList = (ListView) findViewById(R.id.province_list);
		
		//显示进度条
		ToolAlert.showLoading(this, "数据加载中...");
		
		//添加参数
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("byProvinceName", getIntent().getStringExtra("province"));
		
		ToolSOAP.callService(URLConfig.WEB_SERVER_URL, URLConfig.NAME_SPACE,"getSupportCity", properties, new ToolSOAP.WebServiceCallBack() {
			
			@Override
			public void onSucced(SoapObject result) {
				ToolAlert.closeLoading();
				if(result != null){
					cityStringList = parseSoapObject(result);
					mCityList.setAdapter(new ArrayAdapter<String>(CityActivity.this, android.R.layout.simple_list_item_1, cityStringList));
				}else{
					Toast.makeText(CityActivity.this, "获取WebService数据错误", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(String result) {
				Toast.makeText(CityActivity.this, "获取WebService数据错误，原因："+result, Toast.LENGTH_SHORT).show();
			}
		});
		
		mCityList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(CityActivity.this, WeatherActivity.class);
				intent.putExtra("city", cityStringList.get(position));
				startActivity(intent);
			}
		});
	}
	
	/**
	 * 解析SoapObject对象
	 * @param result
	 * @return
	 */
	private List<String> parseSoapObject(SoapObject result){
		List<String> list = new ArrayList<String>();
		SoapObject provinceSoapObject = (SoapObject) result.getProperty("getSupportCityResult");
		for(int i=0; i<provinceSoapObject.getPropertyCount(); i++){
			String cityString = provinceSoapObject.getProperty(i).toString();
			list.add(cityString.substring(0, cityString.indexOf("(")).trim());
		}
		
		return list;
	}
}
