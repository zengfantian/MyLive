package cn.com.hoonsoft.view.webview;

import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import cn.com.hoonsoft.tool.ToolAlert;
import cn.com.hoonsoft.view.webview.UIWebView.IOnReceivedTitle;

public class UIWebChromeClient extends android.webkit.WebChromeClient {
	
	private IOnReceivedTitle mIOnReceivedTitle;

	private ProgressBar progressbar;
	
	public UIWebChromeClient() {
		this(null,null);
	}

	public UIWebChromeClient(IOnReceivedTitle mIOnReceivedTitle) {
		this(mIOnReceivedTitle,null);
	}
	
	public UIWebChromeClient(ProgressBar progressbar) {
		this(null,progressbar);
	}
	
	public UIWebChromeClient(IOnReceivedTitle mIOnReceivedTitle,ProgressBar progressbar) {
		this.mIOnReceivedTitle = mIOnReceivedTitle;
		this.progressbar = progressbar;
	}

	/***页面加载进度**/
    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress == 100) {
        	if(null != progressbar){
        		progressbar.setVisibility(View.GONE);
        	}
        	ToolAlert.closeLoading();
        } else {
        	if(null != progressbar)
        	{
        		if (progressbar.getVisibility() == View.GONE){
                	progressbar.setVisibility(View.VISIBLE);
                }
                progressbar.setProgress(newProgress);
        	}
        }
        super.onProgressChanged(view, newProgress);
    }
    
    /**
     * 获取到网页标题回调函数
     */
    public void onReceivedTitle(WebView view, String title) {
    	if(null != mIOnReceivedTitle){
    		mIOnReceivedTitle.onReceivedTitle(title);
    	}
        super.onReceivedTitle(view, title);
    }

}
