package cn.com.hoonsoft.example.zxing;

import java.util.UUID;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.tool.ToolAlert;
import cn.com.hoonsoft.tool.ToolFile;
import cn.com.hoonsoft.tool.ToolPicture;

public class ZxingGenBinActivity extends Activity {

	private EditText et_qr_text;
	private Button btn_make_qr;
	private Button btn_make_bar;
	private ImageView qr_image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_gen_qr_rimage);
		
		initView();
		
		//初始化控件
		if("".equals(et_qr_text.getText().toString())){
			et_qr_text.setText("http://zftlive.qiniudn.com/Android360UI.zip");
		}
		
		btn_make_qr.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try {
					if("".equals(et_qr_text.getText().toString())){
						ToolAlert.showShort("请输入要生成二维码内容！");
						return ;
					}
					
					Bitmap qrImage = ToolPicture.makeQRImage(et_qr_text.getText().toString(), 200, 200);
					qr_image.setImageBitmap(qrImage);
					
					//生成图片
					String filePath = ToolFile.gainSDCardPath() + "/MyLive/QRImage/"+UUID.randomUUID().toString()+".jpg";
					ToolFile.saveAsJPEG(qrImage, filePath);
					ToolAlert.showShort("二维码已经保存在："+filePath);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
		
		btn_make_bar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try {
					
					//生成图片
					Bitmap qrImage = ToolPicture.makeValidateCode(200, 30);
					qr_image.setImageBitmap(qrImage);
					
					ToolAlert.showShort("验证码值："+ToolPicture.gainValidateCodeValue());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
		
	}
	
	/***
	 * 初始化控件
	 */
	private void initView(){
		et_qr_text = (EditText)findViewById(R.id.et_qr_text);
		btn_make_qr = (Button)findViewById(R.id.btn_make_qr);
		btn_make_bar = (Button)findViewById(R.id.btn_make_bar);
		qr_image = (ImageView)findViewById(R.id.qr_image);
	}
}
