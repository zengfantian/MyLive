package cn.com.hoonsoft.example.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.view.progressbar.RoundProgressBar;

public class RoundPBActivity extends Activity {
	private RoundProgressBar mRoundProgressBar1 ,mRoundProgressBar3, mRoundProgressBar4, mRoundProgressBar5;
	private int progress = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_cricle_progress);
		
		mRoundProgressBar1 = (RoundProgressBar) findViewById(R.id.roundProgressBar1);
		mRoundProgressBar3 = (RoundProgressBar) findViewById(R.id.roundProgressBar3);
		mRoundProgressBar4 = (RoundProgressBar) findViewById(R.id.roundProgressBar4);
		mRoundProgressBar5 = (RoundProgressBar) findViewById(R.id.roundProgressBar5);
		
		
		mRoundProgressBar1.setOnLoadFinishListener(new RoundProgressBar.OnLoadFinishListener() {
			
			@Override
			public void onLoadFinished() {
				Toast.makeText(getApplicationContext(), "进度条加载完成事件触发了", Toast.LENGTH_LONG).show();
			}
		});
		
		((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						while(progress <= 100){
							progress += 3;
							
							System.out.println(progress);
							
							mRoundProgressBar1.setProgress(progress);
							mRoundProgressBar3.setProgress(progress);
							mRoundProgressBar4.setProgress(progress);
							mRoundProgressBar5.setProgress(progress);
							
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
					}
				}).start();
			}
		});
		
	}


}
