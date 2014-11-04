package cn.com.hoonsoft.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 启动界面自定义控件
 * @author 曾繁添
 * @version 1.0
 *
 */
public class LauncherLoading extends ImageView implements Runnable
{
	private boolean isStop = false;
	private List<Integer> imageList = new ArrayList<Integer>();
	private int index = 0;
	private int length = 1;
	
	public LauncherLoading(Context context)
	{
		this(context, null);
	}

	public LauncherLoading(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}
	
	@Override
	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		isStop = true;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		setImageResource(imageList.get(index));
	}

	/**
     * 添加图片
     * @param imageId 图片ID
     */
    public void addImage(int imageId){
        imageList.add(imageId);
        length = imageList.size();
    }
    
    public void startAnim()
    {
        new Thread(this).start();
    }
	
	@Override
	public void run()
	{
		while(!isStop)
		{
			index = ++index % length;
			postInvalidate();
			try
			{
				Thread.sleep(400);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
