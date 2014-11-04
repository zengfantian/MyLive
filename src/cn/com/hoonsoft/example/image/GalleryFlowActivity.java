package cn.com.hoonsoft.example.image;

import android.app.Activity;
import android.os.Bundle;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.view.GalleryFlow;

public class GalleryFlowActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.demo_layout_gallery);
        
        Integer[] images = { R.drawable.img0001, R.drawable.img0030,
                R.drawable.img0100, R.drawable.img0130, R.drawable.img0200,
                R.drawable.img0230, R.drawable.img0300, R.drawable.img0330,
                R.drawable.img0354 };
        
        ImageAdapter adapter = new ImageAdapter(this, images);
        adapter.createReflectedImages();

        GalleryFlow galleryFlow = (GalleryFlow) findViewById(R.id.Gallery01);
        galleryFlow.setAdapter(adapter);
    }
  
}
