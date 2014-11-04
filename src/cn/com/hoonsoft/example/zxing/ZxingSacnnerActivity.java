package cn.com.hoonsoft.example.zxing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.com.hoonsoft.zxing.CaptureActivity;

public class ZxingSacnnerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(this,CaptureActivity.class);
		startActivity(intent);
	}
}
