package cn.com.hoonsoft.example.soap;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import cn.com.hoonsoft.R;
import cn.com.hoonsoft.config.URLConfig;
import cn.com.hoonsoft.tool.ToolAlert;
import cn.com.hoonsoft.tool.ToolSOAP;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * 显示天气省份的Activity
 * 
 * @see http://blog.csdn.net/xiaanming
 * 
 * @author xiaanming
 *
 */
public class SOAPMainActivity extends Activity {
	private List<String> provinceList = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_soap_main);
		init();
	}

	private void init() {
		final ListView mProvinceList = (ListView) findViewById(R.id.province_list);
		
		//显示进度条
		ToolAlert.showLoading(this, "数据加载中...");
		
		//通过工具类调用WebService接口
		ToolSOAP.callService(URLConfig.WEB_SERVER_URL,URLConfig.NAME_SPACE,"getSupportProvince", null, new ToolSOAP.WebServiceCallBack() {
			
			//WebService接口返回的数据回调到这个方法中
			@Override
			public void onSucced(SoapObject result) {
				//关闭进度条
				ToolAlert.closeLoading();
				if(result != null){
					provinceList = parseSoapObject(result);
					mProvinceList.setAdapter(new ArrayAdapter<String>(SOAPMainActivity.this, android.R.layout.simple_list_item_1, provinceList));
				}else{
					Toast.makeText(SOAPMainActivity.this, "获取WebService数据错误", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(String result) {
				Toast.makeText(SOAPMainActivity.this, "获取WebService数据错误，原因："+result, Toast.LENGTH_SHORT).show();
			}
		});
		
		mProvinceList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(SOAPMainActivity.this, CityActivity.class);
				intent.putExtra("province", provinceList.get(position));
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
		SoapObject provinceSoapObject = (SoapObject) result.getProperty("getSupportProvinceResult");
		if(provinceSoapObject == null) {
			return null;
		}
		for(int i=0; i<provinceSoapObject.getPropertyCount(); i++){
			list.add(provinceSoapObject.getProperty(i).toString());
		}
		
		return list;
	}

}
