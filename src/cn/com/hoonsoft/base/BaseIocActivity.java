package cn.com.hoonsoft.base;

import cn.com.hoonsoft.tool.ToolApplication;
import android.os.Bundle;
import roboguice.activity.RoboActivity;

/**
 * 实现依赖注入的Activity基类
 * @author 曾繁添
 * @version 1.0
 */
public class BaseIocActivity extends RoboActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ToolApplication.pushTask(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		ToolApplication.removeTask(this);
		super.onDestroy();
	}
}
