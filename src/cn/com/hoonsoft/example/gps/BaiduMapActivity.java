package cn.com.hoonsoft.example.gps;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.tool.ToolLocation;
import cn.com.hoonsoft.tool.ToolLocation.InterfaceBDLocation;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

public class BaiduMapActivity extends Activity implements InterfaceBDLocation {

	MapView mMapView = null;
	
	TextView tv_locInfo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		//SDKInitializer.initialize(getApplicationContext());，因此我们建议该方法放在Application的初始化方法中
		SDKInitializer.initialize(getApplicationContext());
		
		setContentView(R.layout.demo_baidu_map);
		// 获取地图控件引用
		mMapView = (MapView) findViewById(R.id.bmapView);
		
		tv_locInfo = (TextView)findViewById(R.id.loc_info);
		
		ToolLocation.requestLocation(this,true);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
		if(ToolLocation.mLocClient.isStarted()){
			ToolLocation.mLocClient.stop();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}

	@Override
	public void onLocationSuccess(BDLocation location) {
		Log.i("MyBDLocationListener", "location.getAddrStr()="+location.getAddrStr());
		Log.i("MyBDLocationListener", "location.getProvince()="+location.getProvince());
		Log.i("MyBDLocationListener", "location.getCity()="+location.getCity());
		Log.i("MyBDLocationListener", "location.getDistrict()="+location.getDistrict());
		Log.i("MyBDLocationListener", "location.getStreet()="+location.getStreet());
		Log.i("MyBDLocationListener", "location.getDirection()="+location.getDirection());
		
		StringBuffer sb = new StringBuffer(256);
		sb.append("time : ");
		sb.append(location.getTime());
		sb.append("\nerror code : ");
		sb.append(location.getLocType());
		sb.append("\nlatitude : ");
		sb.append(location.getLatitude());
		sb.append("\nlontitude : ");
		sb.append(location.getLongitude());
		sb.append("\nradius : ");
		sb.append(location.getRadius());
		if (location.getLocType() == BDLocation.TypeGpsLocation){
			sb.append("\nspeed : ");
			sb.append(location.getSpeed());
			sb.append("\nsatellite : ");
			sb.append(location.getSatelliteNumber());
		} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
			sb.append("\naddr : ");
			sb.append(location.getAddrStr());
		} 
		
		sb.append("\nlocation.getProvince()=");
		sb.append(location.getProvince());
		
		sb.append("\nlocation.getCity()=");
		sb.append(location.getCity());
		
		sb.append("\nlocation.getDistrict()=");
		sb.append(location.getDistrict());
		
		sb.append("\nlocation.getStreet()=");
		sb.append(location.getStreet());
		
		tv_locInfo.setText(sb.toString());
	}

}
