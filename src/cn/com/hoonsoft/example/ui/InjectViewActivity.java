package cn.com.hoonsoft.example.ui;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.base.BaseIocActivity;
import cn.com.hoonsoft.dto.DTO;
import cn.com.hoonsoft.tool.ToolAlert;
import cn.com.hoonsoft.tool.ToolData;

/**依赖注入XML界面**/
@ContentView(R.layout.demo_roboguice2)
public class InjectViewActivity extends BaseIocActivity {
	
	/**依赖注入控件***/
	@InjectView(R.id.et_username)       EditText et_username;
	@InjectView(R.id.et_password)       EditText et_password;
	@InjectView(R.id.sp_school)         Spinner sp_school;
	@InjectView(R.id.radioMale)         RadioButton radioMale;
	@InjectView(R.id.radioFemale)       RadioButton radioFemale;
	@InjectView(R.id.cb_sleep)          CheckBox cb_sleep;
	@InjectView(R.id.cb_boll)           CheckBox b_boll;
	@InjectView(R.id.cb_read)           CheckBox cb_read;
	@InjectView(R.id.btn_view_password) Button btn_view_password;
	@InjectView(R.id.btn_login)         Button btn_login;
	
	private String LOG_TAG = InjectViewActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//设置下拉框数据源
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.drawable.drop_list_hover,new String[]{"天津大学", "南开大学", "北京大学", "清华大学"});
		adapter.setDropDownViewResource(R.drawable.drop_list_ys);
		sp_school.setAdapter(adapter);
		
		//自动获取表单数据
		btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DTO<String, Object> formData = new DTO<String, Object>();
				ToolData.gainForm((RelativeLayout) findViewById(R.id.frame_root),formData);

				ToolAlert.dialog(InjectViewActivity.this,"自动获取表单数据结果", formData.toString(),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,int which) {
								dialog.dismiss();
							}
						}, 
						null);
				
				Log.i(LOG_TAG, "自动获取数据完毕！");
			}
		});
	}
}
