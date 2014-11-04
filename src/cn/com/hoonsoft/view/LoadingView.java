package cn.com.hoonsoft.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

@SuppressLint("WrongCall")
public class LoadingView extends View {
	private Matrix mFgMatrix;
	private Bitmap mFgBitmap;

	public LoadingView(Context context) {
		super(context);
	}

	public LoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setDrawableResId(int iconResId) {
		mFgMatrix = new Matrix();
		mFgBitmap = BitmapFactory.decodeResource(getResources(), iconResId);
		myHandler.sendEmptyMessage(0);
		onMeasure(mFgBitmap.getWidth(), mFgBitmap.getHeight());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(mFgBitmap.getWidth(), mFgBitmap.getHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(mFgBitmap, mFgMatrix, null);
	}

	private Handler myHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			mFgMatrix.postRotate(-10f, mFgBitmap.getWidth() / 2f,
					mFgBitmap.getHeight() / 2f);
			invalidate();// 更新视图
			myHandler.sendEmptyMessageDelayed(msg.what, 20);
		};
	};

}