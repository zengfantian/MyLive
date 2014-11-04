package cn.com.hoonsoft.example.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import cn.com.hoonsoft.R;

public class OtherActivity extends Activity {

	WebView webView;
	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_webview_forward);
		webView = (WebView) findViewById(R.id.webView2);
		webView.getSettings().setJavaScriptEnabled(true);
		
		String url = getIntent().getExtras().getString("action_url");
		
		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}
}
