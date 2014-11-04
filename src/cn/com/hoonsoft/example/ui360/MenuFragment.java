package cn.com.hoonsoft.example.ui360;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.com.hoonsoft.R;

public class MenuFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.demo_ui360_fragment_menu, container, false);
		return view;
	}
}