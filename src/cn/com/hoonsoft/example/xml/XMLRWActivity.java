package cn.com.hoonsoft.example.xml;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.com.hoonsoft.R;

public class XMLRWActivity extends Activity {
	private Button btn_write_xml, btn_read_xml ;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_xml_rw);
		
		btn_write_xml = (Button)findViewById(R.id.btn_write_xml);
		btn_write_xml.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		btn_read_xml = (Button)findViewById(R.id.btn_read_xml);
		btn_read_xml.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
	}


}
