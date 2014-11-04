package cn.com.hoonsoft.example.http;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import org.apache.http.Header;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.tool.ToolAlert;
import cn.com.hoonsoft.tool.ToolFile;
import cn.com.hoonsoft.tool.ToolHTTP;
import cn.com.hoonsoft.tool.ToolNetwork;
import cn.com.hoonsoft.tool.http.BinaryHandler;
import cn.com.hoonsoft.view.progressbar.RoundProgressBar;

public class DownloadActivity extends Activity {

	private EditText et_down_url;
	private TextView tv_process;
	private Button btn_download,btn_cancel;
	private ProgressBar pb_down1;
	private RoundProgressBar round_pb_down;
	
	private String LOG_TAG = DownloadActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_download_progress);
		
		initView();
		
		//初始化控件
		if("".equals(et_down_url.getText().toString())){
			et_down_url.setText("http://zftlive.qiniudn.com/Android360UI.zip");
		}
		
		btn_download.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(ToolNetwork.getInstance(DownloadActivity.this).isConnected()){
					ToolHTTP.get(et_down_url.getText().toString(), null, getBinaryResponseHandler());
					btn_download.setEnabled(false);
					btn_cancel.setEnabled(true);
				}else{
					ToolAlert.showShort("请先开启网络，再进行下载！");
				}
			}
		});
		
		Log.e(LOG_TAG, "主线程ID-->"+Thread.currentThread().getId());
		
		btn_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ToolHTTP.client.cancelRequests(DownloadActivity.this, true);
				btn_download.setEnabled(true);
				btn_cancel.setEnabled(false);
			}
		});
	}
	
    public BinaryHandler getBinaryResponseHandler() {
        return new BinaryHandler() {

			@Override
			public void progress(int bytesWritten, int totalSize) {
				Log.e(LOG_TAG, "线程ID-->"+Thread.currentThread().getId() + " bytesWritten="+bytesWritten+"  " + "totalSize="+totalSize);
				//文件不存在404时bytesWritten=totalSize
				if(bytesWritten>0 && totalSize >0){
					Double fenzi = Double.valueOf(bytesWritten);
					Double fenmu = Double.valueOf(totalSize);
					Double num = (fenzi / fenmu) * 100;
					
					pb_down1.setProgress(num.intValue());
					round_pb_down.setProgress(num.intValue());
					
					BigDecimal mData1 = new BigDecimal((bytesWritten/(1024*1024))).setScale(2, BigDecimal.ROUND_HALF_UP);
					BigDecimal mData2 = new BigDecimal((totalSize/(1024*1024))).setScale(2, BigDecimal.ROUND_HALF_UP);
					tv_process.setText(mData1 + "/" + mData2 + "Mb");
				}
			}

			@Override
			public void success(Header[] headers, byte[] binaryData) {
				Log.e(LOG_TAG, "headers="+headers + "\tbinaryData.length="+binaryData.length);
            	
            	btn_download.setEnabled(true);
				btn_cancel.setEnabled(true);
				
				try {
					ToolFile.write(Environment.getExternalStorageDirectory()+"/MyLive/download/"+UUID.randomUUID().toString()+"_Android360UI.zip", binaryData);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ToolAlert.showShort("文件下载完成！");
			}

			@Override
			public void failure(int statusCode, Header[] headers,byte[] errorResponse, Throwable e) {
				if(404 == statusCode){
            		ToolAlert.showShort("资源路径不存在，请输入正确的资源URL！");
            	}
            	Log.e(LOG_TAG, "statusCode="+statusCode+"\theaders="+headers + "\tbinaryData.length="+errorResponse.length + "\terror="+ e.getMessage());
                if (errorResponse != null) {
                	Log.e(LOG_TAG, "Received response is " + errorResponse.length + " bytes");
                }
            	btn_download.setEnabled(true);
				btn_cancel.setEnabled(true);
			}
        };
    }
	
	/***
	 * 初始化控件
	 */
	private void initView(){
		tv_process = (TextView)findViewById(R.id.tv_process);
		et_down_url = (EditText)findViewById(R.id.et_down_url);
		btn_download = (Button)findViewById(R.id.btn_download);
		btn_cancel = (Button)findViewById(R.id.btn_cancel);
		pb_down1 = (ProgressBar)findViewById(R.id.pb_down1);
		round_pb_down = (RoundProgressBar)findViewById(R.id.round_pb_down);
	}
}
