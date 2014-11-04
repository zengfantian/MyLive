package cn.com.hoonsoft.example.share;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.framework.ShareSDK;

public class ShareSDKDemoActivity extends Activity {

	private EditText et_share_txt;
	private Button btn_auth,btn_wechart_share,btn_qzone_share,btn_any_share;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.demo_sharesdk_share);
		
		initView();
		
		btn_any_share.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showShare();
			}
		});
		
	}
	
	/***
	 * 初始化控件
	 */
	private void initView(){
		et_share_txt = (EditText)findViewById(R.id.et_share_txt);
		btn_auth = (Button)findViewById(R.id.btn_auth);
		btn_wechart_share = (Button)findViewById(R.id.btn_wechart_share);
		btn_qzone_share = (Button)findViewById(R.id.btn_qzone_share);
		btn_any_share = (Button)findViewById(R.id.btn_any_share);
	}
	
	private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
       
        // 分享时Notification的图标和文字
        oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
   }
	
}
