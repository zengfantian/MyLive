package cn.com.hoonsoft.example.statusbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import cn.com.hoonsoft.R;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener
{

	private RelativeLayout mRLayout;
	private Button mBtn1, mBtn2, mBtn3, mBtn4, mBtn5, mBtn6, mBtn7, mBtn8;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_status_bar);
		
		mRLayout = (RelativeLayout)findViewById(R.id.content);
		mBtn1 = (Button)findViewById(R.id.btn1);
		mBtn2 = (Button)findViewById(R.id.btn2);
		mBtn3 = (Button)findViewById(R.id.btn3);
		mBtn4 = (Button)findViewById(R.id.btn4);
		mBtn5 = (Button)findViewById(R.id.btn5);
		mBtn6 = (Button)findViewById(R.id.btn6);
		mBtn7 = (Button)findViewById(R.id.btn7);
		mBtn8 = (Button)findViewById(R.id.btn8);
		
		mBtn1.setOnClickListener(this);
		mBtn2.setOnClickListener(this);
		mBtn3.setOnClickListener(this);
		mBtn4.setOnClickListener(this);
		mBtn5.setOnClickListener(this);
		mBtn6.setOnClickListener(this);
		mBtn7.setOnClickListener(this);
		mBtn8.setOnClickListener(this);		
	}
	
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId()) 
		{
		case R.id.btn1:
			//显示状态栏，Activity不全屏显示(恢复到有状态的正常情况)
			mRLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);	
			break;
		case R.id.btn2:
			//隐藏状态栏，同时Activity会伸展全屏显示
			mRLayout.setSystemUiVisibility(View.INVISIBLE);
			break;
		case R.id.btn3:
			//Activity全屏显示，且状态栏被隐藏覆盖掉。
			mRLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
			break;		
		case R.id.btn4:
			//Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住
			mRLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
			break;
			
        case R.id.btn5:
        	//同mRLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
			mRLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
			break;
        case R.id.btn6:
        	//同mRLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
			mRLayout.setSystemUiVisibility(View.SYSTEM_UI_LAYOUT_FLAGS);
			break;
        case R.id.btn7:
        	//隐藏虚拟按键(导航栏)
			mRLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
			break;
        case R.id.btn8:
        	//状态栏显示处于低能显示状态(low profile模式)，状态栏上一些图标显示会被隐藏。
			mRLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
			break;
		}
	}

}