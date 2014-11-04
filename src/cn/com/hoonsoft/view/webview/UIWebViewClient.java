package cn.com.hoonsoft.view.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.com.hoonsoft.tool.ToolAlert;

/**
 * 自定义的浏览器
 * @author 曾繁添
 *
 */
public class UIWebViewClient extends WebViewClient {

	/***跳转上下文**/
	private Context context;
	
	/***跳转界面对象**/
	private Class target;
	
	/***是否程序内部打开：true-->程序内部打开网页，false-->调用别的窗体打开**/
	private Boolean isInnerOpen = true;

	/**过滤目标字符串**/
	private String filter = "";
	
	private String TAG = UIWebViewClient.class.getSimpleName();
	
	/**当前加载网页URL***/
	public static String currentURL = "";
	
	/**
	 * 默认内部打开链接
	 */
	public UIWebViewClient() {
		this(true);
	}
	
	/**
	 * @param isInnerOpen 是否内部打开
	 */
	public UIWebViewClient(Boolean isInnerOpen) {
		this(null,null,isInnerOpen);
	}
	
	/**
	 * 
	 * @param context 上下文
	 * @param target 处理新开网页URL界面
	 * @param isInnerOpen 是否内部打开
	 */
	public UIWebViewClient(Context context, Class target, Boolean isInnerOpen) {
		this(context,target,isInnerOpen,"");
	}
	
	/**
	 * 
	 * @param context 上下文
	 * @param target 处理新开网页URL界面
	 * @param isInnerOpen 是否内部打开
	 * @param filter 过滤字符串
	 */
	public UIWebViewClient(Context context, Class target, Boolean isInnerOpen,String filter) {
		this.context = context;
		this.target = target;
		this.isInnerOpen = isInnerOpen;
		this.filter = filter;
	}

	/***
	 * 让浏览器支持访问https请求
	 */
	@SuppressLint("NewApi")
	public void onReceivedSslError(WebView view, SslErrorHandler handler,
			SslError error) {
		 handler.proceed();  
		 super.onReceivedSslError(view, handler, error);
	}
	
	/**
	 * 控制网页的链接跳转打开方式（拦截URL）
	 */
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		currentURL = url;
		if (isInnerOpen) {
			view.loadUrl(url);
			return true;
		} else {
			String host = Uri.parse(url).getHost();
			if (!host.startsWith("http")){
				host = "http://" + host;
			}
			if (!host.endsWith("/")){
				host = host + "/";
			}

			if (null != context && null != target) {
				if (host.equals(filter)) {
					Intent intent = new Intent(context, target);
					intent.putExtra("url", url);
					context.startActivity(intent);
					return true;
				}
			}
			return false;
		}
	}
	
	 @Override  
     public void onPageStarted(WebView view, String url, Bitmap favicon) {  
         super.onPageStarted(view, url, favicon);
         Log.e(TAG, "onPageStarted--->url="+url);
     }  

     @Override  
     public void onPageFinished(WebView view, String url) {  
    	//加载完毕后，开始加载图片
         //view.getSettings().setBlockNetworkImage(false);
         
         Log.e(TAG, "onPageFinished--->url="+url);
         super.onPageFinished(view, url);  
     }  

     @Override  
     public void onReceivedError(WebView view, int errorCode,String description, String failingUrl) {  
    	 ToolAlert.closeLoading();
    	 ToolAlert.showShort("加载数据失败，错误码："+errorCode+ "\n 原因描述："+description);
         super.onReceivedError(view, errorCode, description, failingUrl);  
     }  
}
