package cn.com.hoonsoft.tool;

import android.content.Context;
import android.location.LocationManager;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

/**
 * 地理位置相关工具类（百度地图API）
 * 
 * @author 曾繁添
 * 
 */
public class ToolLocation {

	// 须在主线程中声明,Context需要时全进程有效的context
	public static LocationClient mLocClient = new LocationClient(
			ToolApplication.gainContext());

	/**
	 * 判断GPS是否打开
	 * 
	 * @return
	 */
	public static boolean isGpsOPen() {
		LocationManager locationManager = (LocationManager) ToolApplication
				.gainContext().getSystemService(Context.LOCATION_SERVICE);
		// 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
		boolean isGpsOkay = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (isGpsOkay) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 请求定位，附带地址信息
	 * @param callback 定位成功回调函数
	 * @param isLocOnce 是否只定位一次，true的情况执行完回调函数之后，将不再监听定位，反之，一直监听定位，需要手动停止定位
	 * 示例代码如下：<br>
	 * <per>
		protected void onDestroy() {
			if(ToolLocation.mLocClient.isStarted()){
				ToolLocation.mLocClient.stop();
			}
			super.onDestroy();
		}
	 * </per>
	 */
	public static void requestLocation(final InterfaceBDLocation callback,
			final boolean isLocOnce) {
		mLocClient.registerLocationListener(new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {
				if (null == location || null == callback)
					return;
				callback.onLocationSuccess(location);
				if (isLocOnce)
					mLocClient.stop();
			}
		});
		LocationClientOption options = configOptions();
		mLocClient.setLocOption(options);
		mLocClient.start();
	}

	private static LocationClientOption configOptions() {
		// 定位相关参数
		LocationClientOption option = new LocationClientOption();
		// 设置定位模式
		/**
		 * 定位模式分为： 高精度定位模式-->这种定位模式下，会同时使用网络定位和GPS定位，优先返回最高精度的定位结果；
		 * 低功耗定位模-->这种定位模式下，不会使用GPS，只会使用网络定位（Wi-Fi和基站定位）
		 * 仅设备定位模式-->这种定位模式下，不需要连接网络，只使用GPS进行定位，这种模式下不支持室内环境的定位
		 */
		if (isGpsOPen()) {
			option.setLocationMode(LocationMode.Hight_Accuracy);
		} else {
			if (ToolNetwork.getInstance(ToolApplication.gainContext())
					.isConnected()) {
				option.setLocationMode(LocationMode.Battery_Saving);
			}
		}
		// 设置是否需要地址信息，默认为无地址
		option.setIsNeedAddress(true);
		// 设置是否打开gps，使用gps前提是用户硬件打开gps，默认是不打开gps的
		if (isGpsOPen()) {
			option.setOpenGps(true);
		}
		/**
		 * 设置坐标类型：
		 * 我们支持返回若干种坐标系，包括国测局坐标系、百度坐标系，需要更多坐标系请联系我们，需要深度合作。目前这些参数的代码为。因此需要在请求时指定类型
		 * ，如果不指定，默认返回百度坐标系。注意当仅输入IP时，不会返回坐标。目前这些参数的代码为 
		 * 返回国测局经纬度坐标系 coor=gcj02
		 * 返回百度墨卡托坐标系 coor=bd09 
		 * 返回百度经纬度坐标系 coor=bd09ll
		 * 百度手机地图对外接口中的坐标系默认是bd09ll，如果配合百度地图产品的话，需要注意坐标系对应问题。
		 */
		option.setCoorType("bd09ll");
		// 设置定时定位的时间间隔(单位ms)
		option.setScanSpan(1000);
		// 设置超时间隔，单位是毫秒
		option.setTimeOut(60 * 60 * 1000);

		return option;
	}

	/**
	 * 定位成功接口
	 * 
	 */
	public interface InterfaceBDLocation {
		public void onLocationSuccess(BDLocation location);
	}
}
