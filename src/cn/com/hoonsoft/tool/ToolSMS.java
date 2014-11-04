package cn.com.hoonsoft.tool;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


/**
 * 发送短信验证码工具类
 * @author 曾繁添
 * @version 1.0
 *
 */
public class ToolSMS {
	
	public static String APPKEY = "25c13dc2e1c4";
	public static String APPSECRET = "14340f710d155024867d4870786d4c10";
	public static String CHINA = "86";
	private static IValidateSMSCode mIValidateSMSCode;
	private static Handler mSMSHandle = new MySMSHandler();
	private static Context context = ToolApplication.gainContext();
	
	public static void initSDK(String appkey, String appSecrect){
		// 初始化短信SDK
		SMSSDK.initSDK(context, appkey, appSecrect);
		//注册回调监听接口
		SMSSDK.registerEventHandler(new EventHandler() {
			public void afterEvent(int event, int result, Object data) {
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				mSMSHandle.sendMessage(msg);
			}
		});
	}

	/**
	 * 请求获取短信验证码
	 * @param phone 手机号
	 */
	public static void getVerificationCode(String phone){
		SMSSDK.getVerificationCode(CHINA, phone);
	}
	
	/**
	 * 提交短信验证码，校验是否正确
	 * @param phone 手机号
	 * @param validateCode 手机短信验证码
	 */
	public static void submitVerificationCode(String phone, String validateCode,IValidateSMSCode callback){
		mIValidateSMSCode = callback;
		SMSSDK.submitVerificationCode(CHINA, phone, validateCode);
	}
	
	/**
	 * 释放资源
	 */
	public static void release(){
		// 销毁回调监听接口
		SMSSDK.unregisterAllEventHandler();
	}
	
    /**
     * 消息处理Handle
     */
	private static class MySMSHandler extends Handler{
    	@Override
		public void handleMessage(Message msg) {
    		super.handleMessage(msg);
    		
    		int event = msg.arg1;
    		int result = msg.arg2;
    		Object data = msg.obj;
			
			if (result == SMSSDK.RESULT_COMPLETE) {
				//提交验证码成功
				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
					//验证成功回调
					if(null != mIValidateSMSCode){
						mIValidateSMSCode.onSucced();
					}
				} 
			} else {
				Throwable exption = ((Throwable) data);
				//验证成功回调
				if(null != mIValidateSMSCode){
					mIValidateSMSCode.onFailed(exption);
				}
			}
		}
    }
	
    /**
     * 提交短信验证码回调接口
     */
    public interface IValidateSMSCode{
    	void onSucced();
    	void onFailed(Throwable e);
    }
}
