package cn.com.hoonsoft.example;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.example.camera.CameraDemoActivity;
import cn.com.hoonsoft.example.gps.BaiduMapActivity;
import cn.com.hoonsoft.example.http.DownloadActivity;
import cn.com.hoonsoft.example.image.GalleryFlowActivity;
import cn.com.hoonsoft.example.image.ImageComposActivity;
import cn.com.hoonsoft.example.menu.TestExpandableListActivity;
import cn.com.hoonsoft.example.menu.tree.TreeActivity;
import cn.com.hoonsoft.example.message.PushDemoActivity;
import cn.com.hoonsoft.example.progressbar.RoundPBActivity;
import cn.com.hoonsoft.example.pulltorefresh.LauncherActivity;
import cn.com.hoonsoft.example.share.ShareSDKDemoActivity;
import cn.com.hoonsoft.example.sms.SMSMainActivity;
import cn.com.hoonsoft.example.soap.SOAPMainActivity;
import cn.com.hoonsoft.example.timer.TimerDemoActivity;
import cn.com.hoonsoft.example.ui.AutoGainFormActivity;
import cn.com.hoonsoft.example.ui.DialogDemoActivity;
import cn.com.hoonsoft.example.ui.FormValidationActivity;
import cn.com.hoonsoft.example.ui.InjectViewActivity;
import cn.com.hoonsoft.example.ui.RoundListviewActivity;
import cn.com.hoonsoft.example.ui360.LoginActivity;
import cn.com.hoonsoft.example.webview.MainActivity;
import cn.com.hoonsoft.example.xml.XMLRWActivity;
import cn.com.hoonsoft.example.zxing.ZxingGenBinActivity;
import cn.com.hoonsoft.zxing.CaptureActivity;

public class SampleListActivity extends ListActivity {

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ListView lv = getListView();
		lv.setAdapter( new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items));
		lv.setOnItemClickListener(listener);
		
		lv.setBackgroundColor(R.color.light_gray);
	}

	private static final String[] items = {
		"圆角进度条示例",
		"360UI设计示例",
		"百度地图示例",
		"XML解析读写",
		"百度消息云推送接受界面",
		"文件下载示例",
		"拍照示例",
		"二维码/条形码扫描",
		"生成二维码示例",
		"短信验证码示例",
		"内容分享示例",
		"图片合成示例",
		"自动获取表单数据示例",
		"基于注解获取控件示例",
		"调用WebService示例",
		"炫丽图片浏览器",
		"表单验证示例",
		"对话框示例",
		"WebView示例",
		"三级树形菜单",
		"无线级菜单",
		"圆角Listview",
		"Timer操作",
		"PullToRefresh操作",
		"StatusBar操作"
	};
	
	private OnItemClickListener listener = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			
			Intent intent = new Intent();
			
			switch (arg2) 
			{
				case 0:
					intent.setClass(getApplicationContext(), RoundPBActivity.class);
					break;
				case 1:
					intent.setClass(getApplicationContext(), LoginActivity.class);
					break;
				case 2:
					intent.setClass(getApplicationContext(), BaiduMapActivity.class);
					break;
				case 3:
					intent.setClass(getApplicationContext(), XMLRWActivity.class);
					break;
				case 4:
					intent.setClass(getApplicationContext(), PushDemoActivity.class);
					break;
				case 5:
					intent.setClass(getApplicationContext(), DownloadActivity.class);
					break;
				case 6:
					intent.setClass(getApplicationContext(), CameraDemoActivity.class);
					break;
				case 7:
					intent.setClass(getApplicationContext(), CaptureActivity.class);
					break;
				case 8:
					intent.setClass(getApplicationContext(), ZxingGenBinActivity.class);
					break;
				case 9:
					intent.setClass(getApplicationContext(), SMSMainActivity.class);
					break;
				case 10:
					intent.setClass(getApplicationContext(), ShareSDKDemoActivity.class);
					break;
				case 11:
					intent.setClass(getApplicationContext(), ImageComposActivity.class);
					break;
				case 12:
					intent.setClass(getApplicationContext(), AutoGainFormActivity.class);
					break;
				case 13:
					intent.setClass(getApplicationContext(), InjectViewActivity.class);
					break;
				case 14:
					intent.setClass(getApplicationContext(), SOAPMainActivity.class);
					break;
				case 15:
					intent.setClass(getApplicationContext(), GalleryFlowActivity.class);
					break;
				case 16:
					intent.setClass(getApplicationContext(), FormValidationActivity.class);
					break;
				case 17:
					intent.setClass(getApplicationContext(), DialogDemoActivity.class);
					break;
				case 18:
					intent.setClass(getApplicationContext(), MainActivity.class);
					break;
				case 19:
					intent.setClass(getApplicationContext(), TestExpandableListActivity.class);
					break;
				case 20:
					intent.setClass(getApplicationContext(), TreeActivity.class);
					break;
				case 21:
					intent.setClass(getApplicationContext(), RoundListviewActivity.class);
					break;
				case 22:
					intent.setClass(getApplicationContext(), TimerDemoActivity.class);
					break;
				case 23:
					intent.setClass(getApplicationContext(), LauncherActivity.class);
					break;
				case 24:
					intent.setClass(getApplicationContext(), cn.com.hoonsoft.example.statusbar.MainActivity.class);
					break;
				default:
					break;
			}
			startActivity(intent);
		}
		
	};
	
}
