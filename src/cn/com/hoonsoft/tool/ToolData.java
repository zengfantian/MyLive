package cn.com.hoonsoft.tool;

import java.util.HashMap;

import android.inputmethodservice.ExtractEditText;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import cn.com.hoonsoft.dto.DTO;

/**
 * 数据工具类
 * 
 * @author 曾繁添
 * @version 1.0
 * @link http://www.cnblogs.com/fly100/
 * @email mengzhongyouni@gmail.com
 */
@SuppressWarnings("deprecation")
public class ToolData {
	
	/**
	 * 获取表单控件数据
	 * 
	 * @param root 当前表单容器
	 * @param data 当前表单数据
	 * @return 表单数据（CheckBox多选选项以##拼接）
	 */
	public static DTO<String,Object> gainForm(ViewGroup root,DTO<String,Object> data) {
		if (root.getChildCount() > 0) {
			for (int i = 0; i < root.getChildCount(); i++) {
				View view = root.getChildAt(i);
				// 容器级别控件需要进行递归
				if (view instanceof LinearLayout) {
					gainForm((LinearLayout) view,data);
				} else if (view instanceof RelativeLayout) {
					gainForm((RelativeLayout) view,data);
				} else if (view instanceof FrameLayout) {
					gainForm((FrameLayout) view,data);
				} else if (view instanceof AbsoluteLayout) {
					gainForm((AbsoluteLayout) view,data);
				} else if (view instanceof android.widget.RadioGroup) {
					gainForm((android.widget.RadioGroup) view,data);
				} else if (view instanceof cn.com.hoonsoft.view.RadioGroup) {
					gainForm((cn.com.hoonsoft.view.RadioGroup) view,data);
				} else if (view instanceof TableLayout) {
					gainForm((TableLayout) view,data);
				}

				// 非容器级别控件不用递归
				/**
				 * EditText.class
				 */
				else if (view instanceof EditText) {
					data.put((String) view.getTag(), ((EditText) view).getText().toString());
				} else if (view instanceof AutoCompleteTextView) {
					data.put((String) view.getTag(),((AutoCompleteTextView) view).getText().toString());
				} else if (view instanceof MultiAutoCompleteTextView) {
					data.put((String) view.getTag(),((MultiAutoCompleteTextView) view).getText()
									.toString());
				} else if (view instanceof ExtractEditText) {
					data.put((String) view.getTag(), ((ExtractEditText) view).getText().toString());
				}

				/**
				 * RadioButton.class
				 */
				else if (view.getClass().getName().equals(android.widget.RadioButton.class.getName())) {
					if (((android.widget.RadioButton) view).isChecked()) {
						data.put((String) view.getTag(),((android.widget.RadioButton) view).getText().toString());
					}
				}else if (view.getClass().getName().equals(cn.com.hoonsoft.view.RadioButton.class.getName())) {
					if (((cn.com.hoonsoft.view.RadioButton) view).isChecked()) {
						data.put((String) view.getTag(),((cn.com.hoonsoft.view.RadioButton) view).getValue());
					}
				} 

				/**
				 * CheckBox.class(需要拼装选中复选框)
				 */
				else if (view.getClass().getName().equals(android.widget.CheckBox.class.getName())) {
					if (((android.widget.CheckBox) view).isChecked()) {
						if (data.containsKey((String) view.getTag())) {
							Object value = data.get((String) view.getTag());
							value = value+ "##"+ ((android.widget.CheckBox) view).getText().toString();
							data.put((String) view.getTag(), value);
						} else {
							data.put((String) view.getTag(),((android.widget.CheckBox) view).getText().toString());
						}
					}
					
				}else if (view.getClass().getName().equals(cn.com.hoonsoft.view.CheckBox.class.getName())) {
					if (((cn.com.hoonsoft.view.CheckBox) view).isChecked()) {
						if (data.containsKey((String) view.getTag())) {
							Object value = data.get((String) view.getTag());
							value = value+ "##"+ ((cn.com.hoonsoft.view.CheckBox) view).getValue();
							data.put((String) view.getTag(), value);
						} else {
							data.put((String) view.getTag(),((cn.com.hoonsoft.view.CheckBox) view).getValue());
						}
					}
				}

				/**
				 * Spinner.class
				 */
				else if (view.getClass().getName().equals(android.widget.Spinner.class.getName())) {
					data.put((String) view.getTag(),((android.widget.Spinner) view).getSelectedItem().toString());
				}else if (view.getClass().getName().equals(cn.com.hoonsoft.view.SingleSpinner.class.getName())) {
					data.put((String) view.getTag(),((cn.com.hoonsoft.view.SingleSpinner) view).getSelectedValue());
				}
			}
		}

		return data;
	}
}
