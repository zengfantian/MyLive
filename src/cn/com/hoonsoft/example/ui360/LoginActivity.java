package cn.com.hoonsoft.example.ui360;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.com.hoonsoft.R;

public class LoginActivity extends Activity {

	private Button register,login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_ui360_activity_login);
		
		register = (Button)findViewById(R.id.btn_register);
		login =  (Button)findViewById(R.id.btn_login);
		
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}
}