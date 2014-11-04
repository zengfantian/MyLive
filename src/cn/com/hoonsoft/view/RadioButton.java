package cn.com.hoonsoft.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import cn.com.hoonsoft.R;

public class RadioButton extends android.widget.RadioButton {

	/**单选框对应value**/
	private String mValue;

	public RadioButton(Context context) {
		this(context,null);
	}
	
	public RadioButton(Context context, AttributeSet attrs) {
		this(context, attrs,android.R.attr.radioButtonStyle);
	}

	public RadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		//获取自定义属性和默认值
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,R.styleable.TextView);
		mValue = mTypedArray.getString(R.styleable.TextView_value);
		
		mTypedArray.recycle();
	}

	public String getValue() {
		return mValue;
	}

	public void setValue(String value) {
		this.mValue = value;
	}
}
