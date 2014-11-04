package cn.com.hoonsoft.view;

import android.content.Context;
import android.util.AttributeSet;
import cn.com.hoonsoft.model.Option;

public class SingleSpinner extends android.widget.Spinner {

	public SingleSpinner(Context context) {
		this(context,null);
	}
	
	public SingleSpinner(Context context, AttributeSet attrs) {
		this(context, attrs,android.R.attr.spinnerStyle);
	}
	
	public SingleSpinner(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 获取选中的选项
	 */
	public Option getSelectedItem(){
		return (Option)super.getSelectedItem();
	}
	
	/**
	 * 获取选中的选项索引
	 */
	public int getSelectedIndex(){
		return super.getSelectedItemPosition();
	}
	
	/**
	 * 获取选中的选项编码value
	 */
	public String getSelectedValue(){
		return getSelectedItem().getValue();
	}
	
	/**
	 * 获取选中的选项编码显示文本
	 */
	public String getSelectedLabel(){
		return getSelectedItem().getLabel();
	}
	
}
