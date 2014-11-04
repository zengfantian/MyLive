package cn.com.hoonsoft.view.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * 垂直进度条
 * @author 曾繁添
 *
 */
public class VerticalProgressBar extends ProgressBar
{

	public VerticalProgressBar(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public VerticalProgressBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public VerticalProgressBar(Context context)
	{
		super(context);
	}

	@Override
	protected synchronized void onDraw(Canvas canvas)
	{
		//反转90度，将水平ProgressBar竖起来
		canvas.rotate(-90);
		
		//将经过旋转后得到的VerticalProgressBar移到正确的位置
		canvas.translate(-getHeight(), 0);
		
		super.onDraw(canvas);
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(heightMeasureSpec, widthMeasureSpec);
		//互换宽高值
		setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		//互换宽高值
		super.onSizeChanged(h, w, oldw, oldh);
	}
}
