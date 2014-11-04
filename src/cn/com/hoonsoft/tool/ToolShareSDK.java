package cn.com.hoonsoft.tool;

import java.util.HashMap;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.PopupWindow;
import cn.com.hoonsoft.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.weibo.TencentWeibo;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * ShareSDK工具类
 * @author 曾繁添
 * @version 1.0
 */
public class ToolShareSDK {

	private static final String TAG = "ToolShareSDK";
	private static Context cocalContext;
	private static ToolShareSDK instance;
	
	private ToolShareSDK(){
		
	}
	
	public static ToolShareSDK gainInstance(Context context){
		if(null == instance){
			instance = new ToolShareSDK();
			cocalContext = context;
		}
		return instance;
	}
	
	/**
	 * 初始化分享平台，分享不同的平台需要重新初始化
	 * @param platform 分享平台
	 */
	public Platform initPlatform(SharePlatform type){
		Platform platForm = null;
		switch (type) {
		case SinaWeibo:
			platForm = ShareSDK.getPlatform(ToolApplication.gainContext(), SinaWeibo.NAME);
			break;
		case TencentWeibo:
			platForm = ShareSDK.getPlatform(ToolApplication.gainContext(), TencentWeibo.NAME);
			break;
		case Wechat:
			//分享给朋友圈
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("BypassApproval", false);
			ShareSDK.setPlatformDevInfo("Wechat", map);
			ShareSDK.setPlatformDevInfo("WechatMoments", map);
			platForm = ShareSDK.getPlatform(ToolApplication.gainContext(), WechatMoments.NAME);
			break;
		default:
			break;
		}
		
		//设置分享动作监听器
		if(null != platForm){
			platForm.setPlatformActionListener(new SharePlatformActionListener());
		}
		
		return platForm;
	}
	
	/**
	 * 分享内容
	 * @param platForm 分享平台
	 * @param parms 分享参数 <a>http://wiki.mob.com/Android_%E4%B8%8D%E5%90%8C%E5%B9%B3%E5%8F%B0%E5%88%86%E4%BA%AB%E5%86%85%E5%AE%B9%E7%9A%84%E8%AF%A6%E7%BB%86%E8%AF%B4%E6%98%8E#.E6.96.B0.E6.B5.AA.E5.BE.AE.E5.8D.9A</a>
	 * @param pw 弹出分享的POP窗口
	 */
	public void share(Platform platForm,ShareParams parms,PopupWindow pw){
		
		try {
			//微信分享
			if(platForm.getName().equals(WechatMoments.NAME) || platForm.getName().equals(Wechat.NAME)|| platForm.getName().equals(WechatFavorite.NAME)){
				platForm.setPlatformActionListener(new SharePlatformActionListener(pw));
				platForm.share(parms);
			}else{
				
				final OnekeyShare oks = new OnekeyShare();
		        // 分享时Notification的图标和文字
		        oks.setNotification(ToolResource.getDrawableId("ic_launcher"), "正在分享");
				oks.setAddress(parms.getAddress());
				// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
				oks.setTitle(parms.getTitle());
				// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
				oks.setTitleUrl(parms.getTitleUrl());
		        // text是分享文本，所有平台都需要这个字段
		        oks.setText(parms.getText());
		        // url仅在微信（包括好友和朋友圈）中使用
				oks.setUrl(parms.getUrl());
		        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
				oks.setComment(parms.getComment());
		        // site是分享此内容的网站名称，仅在QQ空间使用
				oks.setSite(parms.getSite());
		        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
		        oks.setSiteUrl(parms.getSiteUrl());
		        //设置当前位置
				//oks.setLatitude(23.056081f);
				//oks.setLongitude(113.385708f);
				//设置是否无编辑界面分享
				oks.setSilent(true);
				//分享平台
				if (platForm != null) {
					oks.setPlatform(platForm.getName());
				}
				// 令编辑页面显示为Dialog模式
				oks.setDialogMode();
				// 在自动授权时可以禁用SSO方式
				oks.disableSSOWhenAuthorize();

				// 去除注释，则快捷分享的操作结果将通过OneKeyShareCallback回调
				oks.setCallback(new SharePlatformActionListener(pw));
				
				/*
				oks.setShareContentCustomizeCallback(new ShareContentCustomizeDemo());
				// 去除注释，演示在九宫格设置自定义的图标
				Bitmap logo = BitmapFactory.decodeResource(menu.getResources(), R.drawable.ic_launcher);
				String label = menu.getResources().getString(R.string.app_name);
				OnClickListener listener = new OnClickListener() {
					public void onClick(View v) {
						String text = "Customer Logo -- ShareSDK " + ShareSDK.getSDKVersionName();
						Toast.makeText(menu.getContext(), text, Toast.LENGTH_SHORT).show();
						oks.finish();
					}
				};
				oks.setCustomerLogo(logo, label, listener);
				// 去除注释，则快捷分享九宫格中将隐藏新浪微博和腾讯微博
				oks.addHiddenPlatform(SinaWeibo.NAME);
				oks.addHiddenPlatform(TencentWeibo.NAME);
				// 为EditPage设置一个背景的View
				oks.setEditPageBackground(getPage());
				*/
				oks.show(cocalContext);
			}

		} catch (Exception e) {
			Log.e(TAG, "分享出现失败，原因："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 平台分享行为监听器
	 */
	public class SharePlatformActionListener implements PlatformActionListener{

		private PopupWindow pw;
		private Handler mShareHandle;
		public final static int ACTION_CANCEL = -1;
		public final static int ACTION_COMPLETE = 0;
		public final static int ACTION_ERROR = 1;
		
		public SharePlatformActionListener() {
			this(null);
		}
		
		public SharePlatformActionListener(PopupWindow popWin) {
			this.pw = popWin;
			mShareHandle = new MyShareHandler();
		}

		@Override
		public void onCancel(Platform arg0, int arg1) {
			//组装消息
			Message msg = mShareHandle.obtainMessage();
			msg.what = ACTION_CANCEL;
			msg.obj = "取消了"+arg0.getName()+"分享操作";
			mShareHandle.sendMessage(msg);
			
			//记录日志
			Log.i(TAG, "取消了分享操作 Platform=" + arg0.toString() + "arg1="+arg1);
		}

		@Override
		public void onComplete(Platform arg0, int arg1,HashMap<String, Object> arg2) {
			//组装消息
			Message msg = mShareHandle.obtainMessage();
			msg.what = ACTION_COMPLETE;
			msg.obj = "分享成功";
			mShareHandle.sendMessage(msg);
			
			//记录日志
			Log.i(TAG, "分享完成 Platform=" + arg0.toString() + "arg1="+arg1 + "HashMap="+arg2.toString());
		}

		@Override
		public void onError(Platform arg0, int arg1, Throwable arg2) {
			//组装消息
			Message msg = mShareHandle.obtainMessage();
			msg.what = ACTION_COMPLETE;
			msg.obj = "分享失败，原因："+arg2.getMessage();
			mShareHandle.sendMessage(msg);
			
			//记录日志
			Log.i(TAG, "分享失败 Platform=" + arg0.toString() + "arg1="+arg1 + "异常="+arg2.toString());
		}
	    
	    /**
	     * 消息处理Handle
	     */
	    private class MyShareHandler extends Handler{
	    	@Override
			public void handleMessage(Message msg) {
	    		//提示信息
	    		ToolToast.showShort(cocalContext,msg.obj.toString());
	    		//关闭弹出pop
				if(msg.what != ACTION_ERROR && null != pw){
					if(pw.isShowing())
						pw.dismiss();
				}
				
				//关闭等待对话框
				ToolAlert.closeLoading();
				
				super.handleMessage(msg);
			}
	    }
	};
	
	/**定义标题栏按钮眉举类型**/
    public enum SharePlatform {
    	SinaWeibo,TencentWeibo,Wechat
    }
}
