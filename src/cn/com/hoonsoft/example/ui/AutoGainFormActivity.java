package cn.com.hoonsoft.example.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.dto.DTO;
import cn.com.hoonsoft.model.Option;
import cn.com.hoonsoft.tool.ToolAlert;
import cn.com.hoonsoft.tool.ToolData;
import cn.com.hoonsoft.view.SingleSpinner;
import cn.com.hoonsoft.view.SpinnerAdapter;

public class AutoGainFormActivity extends Activity {
	private EditText et_username,et_password;
	private SingleSpinner sp_school;
	private RadioButton radioMale,radioFemale;
	private CheckBox cb_sleep,cb_boll,cb_read;
	private Button btn_view_password,btn_login;
	
	private String LOG_TAG = AutoGainFormActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_from_data);

		initView();

		btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DTO<String, Object> formData = new DTO<String, Object>();
				ToolData.gainForm((RelativeLayout) findViewById(R.id.frame_root),formData);

				ToolAlert.dialog(AutoGainFormActivity.this,"自动获取表单数据结果", formData.toString(),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,int which) {
								dialog.dismiss();
							}
						}, 
						null);
			}
		});
	}
	
	
	/***
	 * 初始化控件
	 */
	private void initView(){
//		et_username = (EditText)findViewById(R.id.et_username);
//		et_password = (EditText)findViewById(R.id.et_password);
//		
		sp_school = (SingleSpinner) findViewById(R.id.sp_school);
		List<Option> data = new ArrayList<Option>();
		data.add(new Option("tjdx","天津大学"));
		data.add(new Option("nkdx","南开大学"));
		data.add(new Option("bjdx","北京大学"));
		data.add(new Option("qhdx","清华大学"));
		
		SpinnerAdapter mSpinnerAdapter = new SpinnerAdapter(this, R.drawable.drop_list_hover, data);
		mSpinnerAdapter.setDropDownViewResource(R.drawable.drop_list_ys);
		sp_school.setAdapter(mSpinnerAdapter);
//		
//		
//		radioMale = (RadioButton)findViewById(R.id.radioMale);
//		radioFemale = (RadioButton)findViewById(R.id.radioFemale);
//		
//		cb_sleep = (CheckBox)findViewById(R.id.cb_sleep);
//		cb_boll = (CheckBox)findViewById(R.id.cb_boll);
//		cb_read = (CheckBox)findViewById(R.id.cb_read);
		
		btn_view_password = (Button)findViewById(R.id.btn_view_password);
		btn_login = (Button)findViewById(R.id.btn_login);
		
		
	}
}
