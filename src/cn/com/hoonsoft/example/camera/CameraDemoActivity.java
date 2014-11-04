package cn.com.hoonsoft.example.camera;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.tool.ToolPicture;

public class CameraDemoActivity extends Activity {

	private SurfaceView surfaceView;  
    private SurfaceHolder surfaceHolder;  
    private Camera camera;  
    private File picture;  
    private Button btnSave;  
    private final static String TAG = "CameraActivity";  
    private ImageView open_picIcon;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_camera_main);
        
       /* Intent intent=new Intent(MainActivity.this,MyCameraActivity.class);
        startActivity(intent);
        finish();*/
        setupViews();
    }

    private void setupViews(){  
        surfaceView = (SurfaceView) findViewById(R.id.camera_preview); // Camera interface to instantiate components  
        surfaceHolder = surfaceView.getHolder(); // Camera interface to instantiate components  
        surfaceHolder.addCallback(surfaceCallback); // Add a callback for the SurfaceHolder  
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  
          
        btnSave = (Button) findViewById(R.id.save_pic);
        open_picIcon=(ImageView) findViewById(R.id.open_picIcon);
          
        btnSave.setOnClickListener(new OnClickListener() {  
              
            @Override  
            public void onClick(View v) {
            	surfaceView.setVisibility(View.VISIBLE);
            	open_picIcon.setVisibility(View.GONE);
                takePic();  
            }  
        });  
    }  
      
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if (keyCode == KeyEvent.KEYCODE_CAMERA  
                || keyCode == KeyEvent.KEYCODE_SEARCH) {  
            takePic();  
            return true;  
        }  
        return super.onKeyDown(keyCode, event);  
    }  
  
    private void takePic() {  
    	//TODO stop the preview  if remove comment block will system error  
        //camera.stopPreview();
        camera.takePicture(null, null, pictureCallback); // picture  
    }  
  
    // Photo call back  
    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {  
        //@Override  
        public void onPictureTaken(byte[] data, Camera camera) {  
            new SavePictureTask().execute(data);  
            camera.startPreview();  
        }  
    };  
  
    // save pic  
    class SavePictureTask extends AsyncTask<byte[], String, String> {  
        @Override  
        protected String doInBackground(byte[]... params) {  
            String fname = DateFormat.format("yyyyMMddhhmmss", new Date()).toString()+".jpg";  
            Log.i(TAG, "fname="+fname+";dir="+Environment.getExternalStorageDirectory());  
            picture = new File(Environment.getExternalStorageDirectory()+"/images/"+fname);  
           File file= picture.getParentFile();
           
           if(!file.exists()) file.mkdir();
           
            try {  
                FileOutputStream fos = new FileOutputStream(picture.getPath()); // Get file output stream  
                fos.write(params[0]); // Written to the file  
                fos.close();   
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            return null;  
        }  
        @Override
        protected void onPostExecute(String result) {
        	super.onPostExecute(result);
        	
        	Bitmap bitmap=getDiskBitmap(picture.getPath());
        	surfaceView.setVisibility(View.GONE);
        	open_picIcon.setVisibility(View.VISIBLE);
        	open_picIcon.setImageBitmap(bitmap);
        }
        
    }
    
	private Bitmap getDiskBitmap(String filePath) 
	{
		Bitmap bitmap = null;
		try {
			File file = new File(filePath);
			if (file.exists())
				bitmap = BitmapFactory.decodeFile(filePath);

			// 获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转
			int degree = ToolPicture.gainPictureDegree(filePath);
			if (degree != 0)
				bitmap = ToolPicture.rotaingBitmap(degree, bitmap);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return bitmap;
	}  
    
    // SurfaceHodler Callback handle to open the camera, off camera and photo size changes  
    @SuppressLint("NewApi")
	SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {  
  
        public void surfaceCreated(SurfaceHolder holder) {  
            Log.i(TAG, "surfaceCallback====");  
            camera = Camera.open(); // Turn on the camera  
            try {  
                camera.setPreviewDisplay(holder); // Set Preview  
            } catch (IOException e) {  
                camera.release();// release camera  
                camera = null;  
            }  
        }  
  
        @SuppressLint("NewApi")
		public void surfaceChanged(SurfaceHolder holder, int format, int width,   int height) {  
            Log.i(TAG,"====surfaceChanged");  
            Camera.Parameters parameters = camera.getParameters(); // Camera parameters to obtain  
            parameters.setPictureFormat(PixelFormat.JPEG);// Setting Picture Format  
            parameters.set("rotation", 90); // Arbitrary rotation 
            // TODO 三星PAD设置不正确会崩溃
            //parameters.setPictureSize(2048, 1232);//设置图片大小
            //parameters.setPreviewSize(480, 600);//预览大小
            camera.setParameters(parameters); // Setting camera parameters  
            camera.setDisplayOrientation(90); 
            camera.startPreview(); // Start Preview 
        }  
  
        public void surfaceDestroyed(SurfaceHolder holder) {  
            Log.i(TAG,"====surfaceDestroyed");  
            camera.stopPreview();// stop preview  
            camera.release(); // Release camera resources  
            camera = null;  
        }  
    }; 
}