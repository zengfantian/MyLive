package cn.com.hoonsoft.example.image;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.tool.ToolAlert;
import cn.com.hoonsoft.tool.ToolFile;
import cn.com.hoonsoft.tool.ToolPicture;
import cn.com.hoonsoft.tool.ToolString;

import com.google.zxing.WriterException;

public class ImageComposActivity extends Activity {

private static final String TAG = "liuzw";
	
	private ImageView picBGView;
	private ImageView pictureView;
	private ImageView maskView;
	private ImageView frameView;
	private ImageView resultView,resultView2,resultView3,resultView4;
	private Button startProcess;
	private Bitmap picBitmap;
	private Bitmap maskBitmap;
	private Bitmap frameBitmap;
	private Bitmap resultBitmap;
	private Bitmap fengjingBitmap;
	private Bitmap composedBitmap;
	
	private final int WITHOUT = -1;
	private static final int FRAME = 0;
	private static final int MASK = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_image_compose);
		
		//picBGView = (ImageView) findViewById(R.id.pic_bg);
		//picBGView.setImageResource(R.drawable.fengjing);
		//pictureView = (ImageView) findViewById(R.id.pic);
		//pictureView.setImageResource(R.drawable.pip_test);
		//maskView = (ImageView) findViewById(R.id.mask);
		//maskView.setImageResource(resIds[MASK]);
		//frameView = (ImageView) findViewById(R.id.frame);
		//frameView.setImageResource(resIds[FRAME]);
		startProcess = (Button) findViewById(R.id.btnStart);
//		startProcess.setOnClickListener(mListener);
//		resultView = (ImageView) findViewById(R.id.showResult);
		resultView2 = (ImageView) findViewById(R.id.showResult2);
		resultView3 = (ImageView) findViewById(R.id.showResult3);
		resultView4 = (ImageView) findViewById(R.id.showResult4);
		
//		Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.fengjing);
//	    Bitmap alertBitmap=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
//	    
//	    Canvas canvas=new Canvas(alertBitmap);
//	    Paint paint=new Paint();
//	    paint.setColor(Color.BLACK);
//	    
//	    canvas.drawBitmap(bitmap, new Matrix(), paint);
//	    Bitmap user_zft=BitmapFactory.decodeResource(getResources(), R.drawable.user_zft);
//	    canvas.drawBitmap(user_zft, (bitmap.getWidth() - user_zft.getWidth())/2, (bitmap.getHeight() - user_zft.getHeight())/2, null);
//	    
//	    resultView.setImageBitmap(alertBitmap);
	    
	    Bitmap user_zft2 =BitmapFactory.decodeResource(getResources(), R.drawable.user_zft);
		resultView2.setImageBitmap(ToolPicture.toBlack(user_zft2));
		
		resultView3.setImageBitmap(ToolPicture.toRoundCorner(user_zft2,10));
		
		
		//二维码合成
		Bitmap srcBMP = null;
		try {
			srcBMP = ToolPicture.makeQRImage("我叫曾繁添", 400, 400);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bitmap markBMP = ToolPicture.createBitmapBySize(ToolPicture.toRoundCorner(user_zft2,10), 50, 50);
		
        // 创建一个新的和SRC长度宽度一样的位图
        Bitmap newb = Bitmap.createBitmap(srcBMP.getWidth(), srcBMP.getHeight(), srcBMP.getConfig());
        Canvas cv = new Canvas(newb);
        // 在 0，0坐标开始画入原图
        cv.drawBitmap(srcBMP, 0, 0, null);
        // 在原图的右下角画入水印
        cv.drawBitmap(markBMP, (srcBMP.getWidth() - markBMP.getWidth())/2, (srcBMP.getHeight() - markBMP.getHeight())/2, null);
        // 保存
        cv.save(Canvas.ALL_SAVE_FLAG);
        // 存储
        cv.restore();
		resultView4.setImageBitmap(newb);
		
		try {
			String filePath = ToolFile.gainSDCardPath()+"/MyLive/images/"+ToolString.gainUUID()+".jpg";
			ToolFile.saveAsJPEG(newb, filePath);
			ToolAlert.showShort("二维码生成成功："+filePath);
		} catch (IOException e) {
			ToolAlert.showShort("保存文件失败："+e.getMessage());
		}
	}
	
	
	/**
	 * 图片合成
	 */
	private void compose(){
		if(fengjingBitmap == null || fengjingBitmap.isRecycled()){
			Log.e(TAG, "compose ERROR: fengjingBitmap is not valuable");
			return;
		}
		composedBitmap = Bitmap.createBitmap(fengjingBitmap.getWidth(), fengjingBitmap.getHeight(), Bitmap.Config.ARGB_8888);
		if(composedBitmap == null || composedBitmap.isRecycled()){
			Log.e(TAG, "compose ERROR: composedBitmap is not valuable");
			return;
		}
		if(resultBitmap == null || resultBitmap.isRecycled()){
			Log.e(TAG, "compose ERROR: resultBitmap is not valuable");
			return;
		}
		Canvas cv = new Canvas(composedBitmap);
		cv.drawBitmap(fengjingBitmap, 0, 0, null);
		cv.drawBitmap(resultBitmap, 100, 100, null);
		
		if(frameBitmap != null && !frameBitmap.isRecycled()){
			cv.drawBitmap(frameBitmap, 100, 100, null);
		}
		
		cv.save(Canvas.ALL_SAVE_FLAG);
		cv.restore();
		resultView.setImageBitmap(composedBitmap);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//释放资源
		resultView.setImageBitmap(null);
		if(picBitmap != null && !picBitmap.isRecycled()){
			picBitmap.recycle();
			picBitmap = null;
		}
		if(maskBitmap != null && !maskBitmap.isRecycled()){
			maskBitmap.recycle();
			maskBitmap = null;
		}
		if(frameBitmap != null && !frameBitmap.isRecycled()){
			frameBitmap.recycle();
			frameBitmap = null;
		}
		if(resultBitmap != null && !resultBitmap.isRecycled()){
			resultBitmap.recycle();
			resultBitmap = null;
		}
		if(fengjingBitmap != null && !fengjingBitmap.isRecycled()){
			fengjingBitmap.recycle();
			fengjingBitmap = null;
		}
		if(composedBitmap != null && !composedBitmap.isRecycled()){
			composedBitmap.recycle();
			composedBitmap = null;
		}
	}
	
	private View.OnClickListener mListener = new View.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.btnStart:
				compose();
				break;
			}
		}
		
	};
}
