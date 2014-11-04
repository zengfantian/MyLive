package cn.com.hoonsoft.view;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;
import cn.com.hoonsoft.model.Option;

/***下拉框适配器***/
public class SpinnerAdapter extends ArrayAdapter<Option>{

	public SpinnerAdapter(Context context, int resource,List<Option> objects) {
		super(context, resource, objects);
	}

	public SpinnerAdapter(Context context, int resource,int textViewResourceId, List<Option> objects) {
		super(context, resource, textViewResourceId, objects);
	}

	public SpinnerAdapter(Context context, int resource, Option[] objects) {
		super(context, resource, objects);
	}
	
	public SpinnerAdapter(Context context, int resource,int textViewResourceId, Option[] objects) {
		super(context, resource, textViewResourceId, objects);
	}
}
