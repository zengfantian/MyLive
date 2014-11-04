package cn.com.hoonsoft.example.http;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Random;

import org.apache.http.Header;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.config.IConstant;
import cn.com.hoonsoft.config.URLConfig;
import cn.com.hoonsoft.dto.DTO;
import cn.com.hoonsoft.tool.ToolAlert;
import cn.com.hoonsoft.tool.ToolFile;
import cn.com.hoonsoft.tool.ToolHTTP;
import cn.com.hoonsoft.tool.http.BinaryHandler;
import cn.com.hoonsoft.view.progressbar.RoundProgressBar;

public class UploadFileProgressActivity extends Activity {

	private EditText et_upload_url;
	private TextView tv_process;
	private Button btn_upload,btn_cancel;
	private ProgressBar pb_upload1;
	private RoundProgressBar round_pb_upload;
	
	private String LOG_TAG = UploadFileProgressActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_upload_progress);
		
		initView();
		
		File file1 = createTempFile("test1.txt",1024);
		File file2 = createTempFile("test2.txt",1024);
		
		//初始化控件
		if("".equals(et_upload_url.getText().toString())){
			et_upload_url.setText(file1.getAbsolutePath() + ";"+ file2.getAbsolutePath());
		}
		
		btn_upload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ToolHTTP.get(et_upload_url.getText().toString(), null, getBinaryResponseHandler());
				
				DTO parmsDTO = new DTO();
				parmsDTO.put(IConstant.HANDLE_CLASS_NAME, "");
				ToolHTTP.post(URLConfig.BASIC_URL, parmsDTO, getBinaryResponseHandler());
				btn_upload.setEnabled(false);
				btn_cancel.setEnabled(true);
			}
		});
		
		Log.e(LOG_TAG, "主线程ID-->"+Thread.currentThread().getId());
		
		btn_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ToolHTTP.client.cancelRequests(UploadFileProgressActivity.this, true);
				btn_upload.setEnabled(true);
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
					
					pb_upload1.setProgress(num.intValue());
					round_pb_upload.setProgress(num.intValue());
					
					BigDecimal mData1 = new BigDecimal((bytesWritten/(1024*1024))).setScale(2, BigDecimal.ROUND_HALF_UP);
					BigDecimal mData2 = new BigDecimal((totalSize/(1024*1024))).setScale(2, BigDecimal.ROUND_HALF_UP);
					tv_process.setText(mData1 + "/" + mData2 + "Mb");
				}
			}

			@Override
			public void success(Header[] headers, byte[] binaryData) {
				Log.e(LOG_TAG, "headers="+headers + "\tbinaryData.length="+binaryData.length);
            	
            	btn_upload.setEnabled(true);
				btn_cancel.setEnabled(true);
				
				ToolFile.write(getApplicationContext(), "Android360UI.zip", binaryData);
				
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
            	btn_upload.setEnabled(true);
				btn_cancel.setEnabled(true);
			}
        };
    }
	
	/***
	 * 初始化控件
	 */
	private void initView(){
		tv_process = (TextView)findViewById(R.id.tv_process);
		et_upload_url = (EditText)findViewById(R.id.et_upload_url);
		btn_upload = (Button)findViewById(R.id.btn_upload);
		btn_cancel = (Button)findViewById(R.id.btn_cancel);
		pb_upload1 = (ProgressBar)findViewById(R.id.pb_upload1);
		round_pb_upload = (RoundProgressBar)findViewById(R.id.round_pb_upload);
	}
	
    public File createTempFile(String namePart, int byteSize) {
        try {
            File f = File.createTempFile(namePart, "_handled", getCacheDir());
            FileOutputStream fos = new FileOutputStream(f);
            Random r = new Random();
            byte[] buffer = new byte[byteSize];
            r.nextBytes(buffer);
            fos.write(buffer);
            fos.flush();
            fos.close();
            return f;
        } catch (Throwable t) {
            Log.e(LOG_TAG, "createTempFile failed", t);
        }
        return null;
    }
}
