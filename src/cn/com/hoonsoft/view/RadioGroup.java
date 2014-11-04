package cn.com.hoonsoft.view;

import android.content.Context;
import android.util.AttributeSet;

public class RadioGroup extends android.widget.RadioGroup {

	public RadioGroup(Context context) {
		super(context);
	}

	public RadioGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// 设置子控件的值
	public void setChildValue() {
		int n = this.getChildCount();
		for (int i = 0; i < n; i++) {
			final RadioButton radio = (RadioButton) this.getChildAt(i);
			if (radio.getValue().equals(this.mValue)) {
				radio.setChecked(true);
			} else {
				radio.setChecked(false);
			}
		}
	}

	// 获取子类的值
	public void getChildValue() {
		int n = this.getChildCount();
		for (int i = 0; i < n; i++) {
			RadioButton radio = (RadioButton) this.getChildAt(i);
			if (radio.isChecked()) {
				this.mValue = radio.getValue();
			}
		}
	}

	private String mValue;
	
	public void setValue(String value) {
		this.mValue = value;
		setChildValue();
	}

	public String getValue() {
		getChildValue();
		return this.mValue;
	}

}
