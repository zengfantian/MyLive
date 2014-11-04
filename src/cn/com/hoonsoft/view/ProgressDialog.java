package cn.com.hoonsoft.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import cn.com.hoonsoft.tool.ToolResource;

/**
 * 自定义对话框
 * @author 曾繁添
 * @version 1.0
 */
public class ProgressDialog extends Dialog {
	
	public ProgressDialog(Context context, String strMessage) {
		this(context, ToolResource.getStyleId("CustomProgressDialog"),strMessage);
	}

	public ProgressDialog(Context context, int theme, String strMessage) {
		super(context, theme);
		
		this.setContentView(ToolResource.getLayoutId("view_progress_dialog"));
		this.getWindow().getAttributes().gravity = Gravity.CENTER;
		TextView tvMsg = (TextView) this.findViewById(ToolResource.getIdId("tv_msg"));
		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}
	}

//	@Override
//	public void onWindowFocusChanged(boolean hasFocus) {
//
//		if (!hasFocus) {
//			dismiss();
//		}
//	}
}