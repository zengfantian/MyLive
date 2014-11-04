package cn.com.hoonsoft.example.webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import cn.com.hoonsoft.R;

public class MainActivity extends Activity {
	WebView webView;
	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_webview);
		webView = (WebView) findViewById(R.id.webView1);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://www.baidu.com/");
		
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				
				String host = Uri.parse(url).getHost();
	        	if(!host.startsWith("http")) host = "http://"+host;
	        	if(!host.endsWith("/")) host = host + "/";
	        	
	            if (host.equals("http://www.cnblogs.com/mengdd/archive/2013/02/27/2935811.html")|| host.equals("http://music.baidu.com/"))
	            {
	                // This is my web site, so do not override; let my WebView load
	                // the page

	                // 这是官网上的例子，但是我点击特定链接的时候仍然是用浏览器而不是用自己的WebView打开，加上下面这句view.loadUrl(url)仍然是用浏览器，无解，不知道哪里出了问题
	                // view.loadUrl(url);
	                return false;
	            }
	            // Otherwise, the link is not for a page on my site, so launch
	            // another Activity that handles URLs
				Intent i = new Intent(MainActivity.this, OtherActivity.class);
				i.putExtra("action_url", url);
				startActivity(i);
				return true; // true自身处理，false系统浏览器处理。
				
			}

		});
		
		webView.setWebChromeClient(new WebChromeClient() {

			public void onProgressChanged(WebView view, int progress) {

				setTitle("页面加载中，请稍候..." + progress + "%");

				setProgress(progress * 100);

				if (progress == 100) {

					setTitle(R.string.app_name);

					progressBar.setVisibility(4);
				}

			}
			
			

		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.demo, menu);
		return true;
	}

}