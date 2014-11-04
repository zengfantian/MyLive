package cn.com.hoonsoft.example.sms;

import java.util.Timer;
import java.util.TimerTask;

import cn.com.hoonsoft.base.BaseIocActivity;
import cn.com.hoonsoft.tool.ToolAlert;
import cn.com.hoonsoft.tool.ToolSMS;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import cn.com.hoonsoft.R;

/**
 * 测试短信发送示例
 * 
 * @author ZengFantian
 * 
 */
@ContentView(R.layout.demo_sms)
public class SMSSampleActivity extends BaseIocActivity {

	@InjectView(R.id.ll_text)			LinearLayout ll_text;
	@InjectView(R.id.et_phone)			EditText et_phone;
	@InjectView(R.id.et_phone_code)			EditText et_phone_code;
	@InjectView(R.id.btn_gain_smscode)	Button btn_gain_smscode;
	@InjectView(R.id.btn_validate)		Button btn_validate;

	private Timer mTimer = null;
	private TimerTask mTimerTask = null;
	private static int delay = 1 * 1000;  //1s
	private static int period = 1 * 1000;  //1s
	private static int count = 60;  
	private static final int UPDATE_TEXTVIEW = 99;
	private String TAG = "SMSSampleActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ToolSMS.initSDK(ToolSMS.APPKEY, ToolSMS.APPSECRET);
		
		//验证不可用
		et_phone_code.setEnabled(false);
		btn_validate.setEnabled(false);
		
		btn_gain_smscode.setText(String.format(getResources().getString(R.string.sms_timer), count));
		
		
		//获取验证码
		btn_gain_smscode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(et_phone.getText().toString() != ""){
					ToolSMS.getVerificationCode(et_phone.getText().toString());
					startTimer();
				}else{
					ToolAlert.showShort("请输入大陆的手机号码");
				}
			}
		});
		
		//校验验证码是否正确
		btn_validate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(et_phone_code.getText().toString() != ""){
					ToolSMS.submitVerificationCode(et_phone.getText().toString(),et_phone_code.getText().toString(),new ToolSMS.IValidateSMSCode() {
						
						@Override
						public void onSucced() {
							ToolAlert.showShort(SMSSampleActivity.this, "验证成功");
							//释放监听器
							ToolSMS.release();
							
						}
						
						@Override
						public void onFailed(Throwable e) {
							ToolAlert.showShort(SMSSampleActivity.this, "验证失败，原因："+e.getMessage());
						}
					});
				}else{
					ToolAlert.showShort("请输入手机验证码");
				}
			}
		});
	}
	
	/**
	 * 发送消息
	 * @param id
	 */
	public void sendTimerMessage(int id){  
        if (handler != null) {  
        	android.os.Message message = android.os.Message.obtain(handler, id);     
            handler.sendMessage(message);   
        }  
    }
	
	/**
	 * 启动Timer
	 */
	private void startTimer(){
		
		stopTimer();
		
		//输入框不可用，获取验证码按钮不可用
		et_phone.setEnabled(false);
		btn_gain_smscode.setEnabled(false);
		btn_validate.setEnabled(true);
		et_phone_code.setEnabled(true);
		
		if (mTimer == null) {
			mTimer = new Timer();
		}
		if (mTimerTask == null) {
			mTimerTask = new TimerTask() {
				@Override
				public void run() {
					Log.i(TAG, "count: "+String.valueOf(count));
					sendTimerMessage(UPDATE_TEXTVIEW);
					count --;  
				}
			};
		}

		if(mTimer != null && mTimerTask != null )
			mTimer.schedule(mTimerTask, delay, period);
	}
	
	/**
	 * 更新倒计时
	 */
	private void updateTextView(){
		
		//等于10重新计时
		if(count == 0){
			
			//停止Timer
			stopTimer();
			
			return ;
		}
		
		if(count < 10){
			btn_gain_smscode.setText(String.format(getResources().getString(R.string.sms_timer), "0"+String.valueOf(count)));
		}else{
			btn_gain_smscode.setText(String.format(getResources().getString(R.string.sms_timer), count));
		}
	}
	
	
	/**
	 * 停止Timer
	 */
	private void stopTimer(){
		
		btn_gain_smscode.setEnabled(true);
		et_phone.setEnabled(true);
		
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}

		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimerTask = null;
		}	
		
		count = 60;
		btn_gain_smscode.setText(String.format(getResources().getString(R.string.sms_timer), count));
		
	}
	
	/***处理UI线程更新Handle**/
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) 
		{
							
			switch (msg.what) {
			case 1:
				break;			
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case UPDATE_TEXTVIEW:
				updateTextView();
				break;
			
			default:
				break;
			}
		};
	};
}
