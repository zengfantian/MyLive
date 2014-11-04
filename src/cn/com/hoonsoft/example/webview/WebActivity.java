package cn.com.hoonsoft.example.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.com.hoonsoft.R;

@SuppressLint("SetJavaScriptEnabled")
public class WebActivity extends Activity
{
    private WebView myWebView = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_webview);

        // 打开网页
        myWebView = (WebView) findViewById(R.id.webView1);
        //

        // myWebView.loadUrl("http://www.cnblogs.com/mengdd/");// 博客链接
        myWebView.loadUrl("http://www.baidu.com/");// 百度链接

        // JavaScript使能(如果要加载的页面中有JS代码，则必须使能JS)
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // 在WebView中打开链接（默认行为是使用浏览器，设置此项后都用WebView打开）
        // myWebView.setWebViewClient(new WebViewClient());
        // 这样设置后所有的链接都会在当前WebView中打开

        // 更强的打开链接控制：自己覆写一个WebViewClient类：除了指定链接从WebView打开，其他的链接默认打开
        myWebView.setWebViewClient(new MyWebViewClient());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.demo, menu);
        return true;
    }

    /**
     * 自定义的WebViewClient类，将特殊链接从WebView打开，其他链接仍然用默认浏览器打开
     * 
     * @author 1
     * 
     */
    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
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
			Intent i = new Intent(WebActivity.this, OtherActivity.class);
			i.putExtra("action_url", url);
			startActivity(i);
			return true; // true自身处理，false系统浏览器处理。
			
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            startActivity(intent);
//            return true;
        }
    }

    /**
     * 按键响应，在WebView中查看网页时，按返回键的时候按浏览历史退回,如果不做此项处理则整个WebView返回退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack())
        {
            // 返回键退回
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up
        // to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

}