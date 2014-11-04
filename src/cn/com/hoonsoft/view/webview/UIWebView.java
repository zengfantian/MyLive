package cn.com.hoonsoft.view.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * 自定义浏览器
 * @author 曾繁添
 * @version 1.0
 */
public class UIWebView extends WebView {
	
	private ProgressBar progressbar;
	
	private boolean isShowProgress = true;
	
	private static String htmlTitle = "";
	
	private IOnReceivedTitle mIOnReceivedTitle = null;
	
    /**
     * Construct a new WebView with a Context object.
     * @param context A Context object used to access application assets.
     */
    public UIWebView(Context context) {
        this(context, null);
    }

    /**
     * Construct a new WebView with layout parameters.
     * @param context A Context object used to access application assets.
     * @param attrs An AttributeSet passed to our parent.
     */
    public UIWebView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.webViewStyle,true);
    }

    /**
     * Construct a new WebView with layout parameters and a default style.
     * @param context A Context object used to access application assets.
     * @param attrs An AttributeSet passed to our parent.
     * @param defStyle The default style resource ID.
     */
    public UIWebView(Context context, AttributeSet attrs, int defStyle,boolean isShowProgress) {
        super(context, attrs, defStyle);
        //添加加载进度条
        if(isShowProgress){
        	 progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
             progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 5, 0, 0));
             addView(progressbar);
        }
        setWebChromeClient(new UIWebChromeClient(progressbar));
        //配置webView设置
        settingWebView();
        //设置滚动条样式
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    	if(null != progressbar)
    	{
            LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
            lp.x = l;
            lp.y = t;
            progressbar.setLayoutParams(lp);
    	}
        super.onScrollChanged(l, t, oldl, oldt);
    }
    
    
    @SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	private void settingWebView(){
    	WebSettings settings = getSettings();
    	//设置是否支持Javascript
    	settings.setJavaScriptEnabled(true);
    	//是否支持缩放
    	settings.setSupportZoom(true);
    	//settings.setBuiltInZoomControls(true);
//        if (Build.VERSION.SDK_INT >= 3.0)
//        	settings.setDisplayZoomControls(false);
    	//是否显示缩放按钮
    	//settings.setDisplayZoomControls(false);
    	//提高渲染优先级
    	settings.setRenderPriority(RenderPriority.HIGH);
    	//设置页面自适应手机屏幕
    	settings.setUseWideViewPort(true);
    	//WebView自适应屏幕大小
    	settings.setLoadWithOverviewMode(true);
    	//加载url前，设置不加载图片WebViewClient-->onPageFinished加载图片
    	//settings.setBlockNetworkImage(true);
    	//设置网页编码
    	settings.setDefaultTextEncodingName("UTF-8");
    }
    
    /**
     * 设置当前网页Title
     * @param title 网页Title
     */
    public static void setCurrentTitle(String title){
    	htmlTitle = title;
    }
    
    /**
     * 获取当前加载网页的标题
     * @return 网页<title>网页标题</title>
     */
    public static String getCurrentTitle(){
    	return htmlTitle;
    }
    
    /**
     * 获取当前加载网页的URL
     * @return 网页URL
     */
    public static String getCurrentURL(){
    	return UIWebViewClient.currentURL;
    }
    
    /**
     * 获取网页标题
     *
     */
    public interface IOnReceivedTitle{
    	public void onReceivedTitle(String title);
    }
    
    
    /**
     * 设置是否显示进度条
     * @param isShow 是否显示
     */
    public void isShowProgress(boolean isShow){
    	this.isShowProgress = isShow;
    }
    
    /**
     * 获取网页创建的progressbar
     * @return
     */
    public ProgressBar getProgressBar(){
    	return this.progressbar;
    }
}
