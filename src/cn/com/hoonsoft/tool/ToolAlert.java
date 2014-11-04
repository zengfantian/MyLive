
package cn.com.hoonsoft.tool;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.Toast;
import cn.com.hoonsoft.view.ProgressDialog;

/**
 * 对话框工具类
 * @author 曾繁添
 * @version 1.0
 */
public class ToolAlert {

	private static ProgressDialog mProgressDialog;
	
	/**
	 * 显示ProgressDialog
	 * @param context 上下文
	 * @param message 消息
	 */
	public static void showLoading(Context context, String message){
		
		showLoading(context,message,true);
	}
	
	/**
	 * 显示ProgressDialog
	 * @param context 上下文
	 * @param message 消息
	 * @param cancelable 是否可以取消
	 */
	public static void showLoading(Context context, String message,boolean cancelable){
		
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(context,message);
			mProgressDialog.setCancelable(cancelable);
		}
		if(mProgressDialog.isShowing()){mProgressDialog.cancel();}
		mProgressDialog.show();
	}
	
	/**
	 * 显示ProgressDialog
	 * @param context 上下文
	 * @param message 消息
	 */
	public static void showLoading(Context context, String message,final ILoadingOnKeyListener listener){
		
		showLoading(context,message,true,listener);
	}
	
	/**
	 * 显示ProgressDialog
	 * @param context 上下文
	 * @param message 消息
	 */
	public static void showLoading(Context context, String message,boolean cancelable ,final ILoadingOnKeyListener listener){
		
		if(mProgressDialog == null){
			mProgressDialog = new ProgressDialog(context,message);
			mProgressDialog.setCancelable(cancelable);
		}
		
		if(null != listener)
		{
			mProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
	            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
	            	listener.onKey(dialog, keyCode, event);
	                return false;
	            }
	        });
		}else{
			mProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
	            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
	                if (keyCode == KeyEvent.KEYCODE_BACK) {
	                	mProgressDialog.dismiss();
	                	ToolHTTP.client.cancelAllRequests(true);
	                }
	                return false;
	            }
	        });
		}
		
		mProgressDialog.show();
	}
	
	/**
	 * 判断加载对话框是否正在加载
	 * @return 是否
	 */
	public static boolean isShowLoading(){
		
		if(null != mProgressDialog){
			return mProgressDialog.isShowing();
		}else{
			return false;
		}
	}
	
	/**
	 * 关闭ProgressDialog
	 */
	public static void closeLoading(){
		if(mProgressDialog != null){
			mProgressDialog.dismiss();
			mProgressDialog = null;
			ToolHTTP.client.cancelAllRequests(true);
		}
	}
	
    /**
     * 弹出对话框
     * @param title 对话框标题
     * @param msg  对话框内容
     * @param okBtnListenner 确定按钮点击事件
     * @param cancelBtnListenner 取消按钮点击事件
     */
    public static AlertDialog dialog(Context context,String msg) {
    	return dialog(context,"",msg);
    }
    
    /**
     * 弹出对话框
     * @param title 对话框标题
     * @param msg  对话框内容
     * @param okBtnListenner 确定按钮点击事件
     * @param cancelBtnListenner 取消按钮点击事件
     */
    public static AlertDialog dialog(Context context,String title,String msg) {
    	return dialog(context,title,msg,null);
    }
    
    /**
     * 弹出对话框
     * @param title 对话框标题
     * @param msg  对话框内容
     * @param okBtnListenner 确定按钮点击事件
     * @param cancelBtnListenner 取消按钮点击事件
     */
    public static AlertDialog dialog(Context context,String title,String msg,OnClickListener okBtnListenner) {
    	return dialog(context,title,msg,okBtnListenner,null);
    }
    
    /**
     * 弹出对话框
     * @param title 对话框标题
     * @param msg  对话框内容
     * @param okBtnListenner 确定按钮点击事件
     * @param cancelBtnListenner 取消按钮点击事件
     */
    public static AlertDialog dialog(Context context,String title,String msg,OnClickListener okBtnListenner,OnClickListener cancelBtnListenner) {
    	return dialog(context,null,title,msg,okBtnListenner,cancelBtnListenner);
    }
    
    /**
     * 弹出对话框
     * @param title 对话框标题
     * @param msg  对话框内容
     * @param okBtnListenner 确定按钮点击事件
     * @param cancelBtnListenner 取消按钮点击事件
     */
    public static AlertDialog dialog(Context context,Drawable icon,String title,String msg) {
    	return dialog(context,icon,title,msg,null);
    }
    
    /**
     * 弹出对话框
     * @param title 对话框标题
     * @param msg  对话框内容
     * @param okBtnListenner 确定按钮点击事件
     * @param cancelBtnListenner 取消按钮点击事件
     */
    public static AlertDialog dialog(Context context,Drawable icon,String title,String msg,OnClickListener okBtnListenner) {
    	return dialog(context,icon,title,msg,okBtnListenner,null);
    }
	
    /**
     * 弹出对话框
     * @param icon  标题图标
     * @param title 对话框标题
     * @param msg  对话框内容
     * @param okBtnListenner 确定按钮点击事件
     * @param cancelBtnListenner 取消按钮点击事件
     */
    public static AlertDialog dialog(Context context,Drawable icon,String title,String msg, OnClickListener okBtnListenner,OnClickListener cancelBtnListenner) {
        Builder dialogBuilder = new AlertDialog.Builder(context);
        if(null != icon){
        	dialogBuilder.setIcon(icon);
        }
        if(!ToolString.isNoBlankAndNoNull(title)){
            dialogBuilder.setTitle(title);
        }
        dialogBuilder.setMessage(msg);
        if(null != okBtnListenner){
        	dialogBuilder.setPositiveButton(android.R.string.ok, okBtnListenner);
        }
        if(null != cancelBtnListenner){
        	dialogBuilder.setNegativeButton(android.R.string.cancel, cancelBtnListenner);
        }
        dialogBuilder.create();
        return dialogBuilder.show();
    }
    
    /**
     * 弹出一个自定义布局对话框
     * @param context 上下文
     * @param view 自定义布局View
     */
	public static AlertDialog dialog(Context context,View view) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setView(view);
		return builder.show();
	}
	
    /**
     * 弹出一个自定义布局对话框
     * @param context 上下文
     * @param resId 自定义布局View对应的layout id
     */
	public static AlertDialog dialog(Context context,int resId) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(resId, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setView(view);
		return builder.show();
	}
    
    /**
     * 弹出较短的Toast消息
     * @param msg 消息内容
     */
    public static void showShort(String msg) {
        Toast.makeText(ToolApplication.gainContext(), msg, Toast.LENGTH_SHORT).show();
    }
    
    /**
     * 弹出较短的Toast消息
     * @param msg 消息内容
     */
    public static void showShort(Context context,String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出较长的Toast消息
     * @param msg 消息内容
     */
    public static void showLong(String msg) {
        Toast.makeText(ToolApplication.gainContext(), msg, Toast.LENGTH_LONG).show();
    }
    
    /**
     * 弹出较长的Toast消息
     * @param msg 消息内容
     */
    public static void showLong(Context context,String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
    
    /**
     * 弹出自定义时长的Toast消息
     * @param msg 消息内容
     */
    public static void show(String msg,int duration) {
        Toast.makeText(ToolApplication.gainContext(), msg, duration).show();
    }
    
    /**
     * 弹出自定义时长的Toast消息
     * @param msg 消息内容
     */
    public static void show(Context context,String msg,int duration) {
        Toast.makeText(context, msg, duration).show();
    }
    
    /**
     * 弹出Pop窗口
     * @param context 依赖界面上下文
     * @param anchor 触发pop界面的控件
     * @param viewId pop窗口界面layout
     * @param xoff 窗口X偏移量
     * @param yoff 窗口Y偏移量
     */
    public static PopupWindow popwindow(Context context,View anchor,int viewId,int xoff,int yoff){
        ViewGroup menuView = (ViewGroup) LayoutInflater.from(context).inflate(viewId, null);
        PopupWindow pw = new PopupWindow(menuView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,true);
        pw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pw.setTouchable(true);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.showAsDropDown(anchor, xoff, yoff);
        pw.update();
        return pw;
    }
    
    /**
     * 弹出Pop窗口
     * @param context 依赖界面上下文
     * @param anchor 触发pop界面的控件
     * @param popView pop窗口界面
     * @param xoff 窗口X偏移量
     * @param yoff 窗口Y偏移量
     */
    public static PopupWindow popwindow(Context context,View anchor,View popView,int xoff,int yoff){
        PopupWindow pw = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,true);
        pw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pw.setOutsideTouchable(true);
        pw.showAsDropDown(anchor, xoff, yoff);
        pw.update();
        return pw;
    }
    
    /**
     * 弹出Pop窗口（可设置是否点击其他地方关闭窗口）
     * @param context 依赖界面上下文
     * @param anchor 触发pop界面的控件
     * @param viewId pop窗口界面layout
     * @param xoff 窗口X偏移量
     * @param yoff 窗口Y偏移量
     * @param outSideTouchable 点击其他地方是否关闭窗口
     */
    public static PopupWindow popwindow(Context context,View anchor,int viewId,int xoff,int yoff,boolean outSideTouchable){
        ViewGroup menuView = (ViewGroup) LayoutInflater.from(context).inflate(viewId, null);
        PopupWindow pw = new PopupWindow(menuView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,true);
        pw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pw.setTouchable(outSideTouchable);
        pw.setFocusable(outSideTouchable);
        pw.setOutsideTouchable(outSideTouchable);
        pw.showAsDropDown(anchor, xoff, yoff);
        pw.update();
        return pw;
    }
    
    /**
     * 弹出Pop窗口（可设置是否点击其他地方关闭窗口）
     * @param context 依赖界面上下文
     * @param anchor 触发pop界面的控件
     * @param popView pop窗口界面
     * @param xoff 窗口X偏移量
     * @param yoff 窗口Y偏移量
     * @param outSideTouchable 点击其他地方是否关闭窗口
     */
    public static PopupWindow popwindow(Context context,View anchor,View popView,int xoff,int yoff,boolean outSideTouchable){
        PopupWindow pw = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,true);
        pw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pw.setOutsideTouchable(outSideTouchable);
        pw.showAsDropDown(anchor, xoff, yoff);
        pw.update();
        
        return pw;
    }
    
    /**
     * Loading监听对话框
     */
    public interface ILoadingOnKeyListener{
    	 public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event);
    }
}
