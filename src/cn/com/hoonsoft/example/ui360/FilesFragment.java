package cn.com.hoonsoft.example.ui360;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.example.ui360.adapter.FileItemAdapter;
import cn.com.hoonsoft.example.ui360.model.FileItem;

public class FilesFragment extends Fragment implements OnClickListener {
	private ListView lv_files;
	private PopupWindow menu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.demo_ui360_fragment_files, container, false);
		findView(view);
		init();
		return view;
	}

	private void findView(View v) {
		lv_files = (ListView) v.findViewById(R.id.lv_files);
	}

	private void init() {
		List<FileItem> files = new ArrayList<FileItem>();
		files.add(new FileItem(R.drawable.folder, "音乐", ""));
		files.add(new FileItem(R.drawable.apk, "Baidu.apk", "18.3K 修改时间:2012-12-08"));
		files.add(new FileItem(R.drawable.avi, "哈利 波特.avi", "28.3M 修改时间:2013-09-18"));
		files.add(new FileItem(R.drawable.epub, "不能说的秘密.epub", "18.3B 修改时间:2012-12-08"));
		files.add(new FileItem(R.drawable.mkv, "钢铁侠.mkv", "18.3K 修改时间:2012-12-08"));
		files.add(new FileItem(R.drawable.pdf, "欢迎.pdf", "183.3K 修改时间:2012-12-08"));
		files.add(new FileItem(R.drawable.zip, "游戏.zip", "13.3B 修改时间:2012-04-08"));
		files.add(new FileItem(R.drawable.rar, "集合.rar", "8.3G 修改时间:2012-12-08"));
		files.add(new FileItem(R.drawable.txt, "三国.txt", "18.3K 修改时间:2012-12-08"));

		View menuView = LayoutInflater.from(getActivity()).inflate(R.layout.demo_ui360_pop_menu, null);
		// 未完成
		menu = new PopupWindow(menuView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		menu.setFocusable(false);
		menu.setOutsideTouchable(false);

		FileItemAdapter adapter = new FileItemAdapter(getActivity(), files, this);
		lv_files.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		showPopupWindows(v);
	}

	private void showPopupWindows(View v) {
		// menu.showAtLocation(v, Gravity.CENTER, 0, 0);
	}
}