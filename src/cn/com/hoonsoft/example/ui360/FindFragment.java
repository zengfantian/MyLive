package cn.com.hoonsoft.example.ui360;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.example.ui360.adapter.MenuItemAdapter;
import cn.com.hoonsoft.example.ui360.model.MenuItem;

public class FindFragment extends Fragment {
	private GridView gv_menu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.demo_ui360_fragment_find, container, false);
		findView(view);
		init();
		return view;
	}

	private void findView(View v) {
		gv_menu = (GridView) v.findViewById(R.id.gv_menu);
	}

	private void init() {
		List<MenuItem> menus = new ArrayList<MenuItem>();
		menus.add(new MenuItem(R.drawable.menu_leidian, "海量手机资源", "电子书 音乐 壁纸 铃声"));
		menus.add(new MenuItem(R.drawable.menu_downloaded, "已下载", "可离线下载"));
		menus.add(new MenuItem(R.drawable.menu_photo, "图片", ""));
		menus.add(new MenuItem(R.drawable.menu_video, "视频", ""));
		menus.add(new MenuItem(R.drawable.menu_file, "文档", ""));
		menus.add(new MenuItem(R.drawable.menu_music, "音乐", ""));
		// 计算margin
		int margin = (int) (getResources().getDisplayMetrics().density * 14 * 13 / 9);
		MenuItemAdapter adapter = new MenuItemAdapter(getActivity(), menus, margin);
		gv_menu.setAdapter(adapter);
	}
}