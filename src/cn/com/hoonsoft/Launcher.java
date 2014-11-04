package cn.com.hoonsoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import cn.com.hoonsoft.example.SampleListActivity;

public class Launcher extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View view = getLayoutInflater().inflate(R.layout.launcher, null);
		AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
		animation.setDuration(1000);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				skip();
			}
		});
		view.setAnimation(animation);
		setContentView(view);
	}

	private void skip() {
		Intent intent = new Intent(this, SampleListActivity.class);
		startActivity(intent);
		finish();
	}
}