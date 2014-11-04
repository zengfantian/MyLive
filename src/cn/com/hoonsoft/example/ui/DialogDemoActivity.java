package cn.com.hoonsoft.example.ui;

import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.base.BaseIocActivity;
import cn.com.hoonsoft.tool.ToolAlert;
import cn.com.hoonsoft.view.LoadingView;

public class DialogDemoActivity extends BaseIocActivity {

	@InjectView(R.id.button1) Button button1;
	@InjectView(R.id.button2) Button button2;
	@InjectView(R.id.button3) Button button3;
	@InjectView(R.id.button4) Button button4;
//	@InjectView(R.id.button5) Button button5;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_dialog_loading);
		//creteProgressDialog(this, "loading");
		
		button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogDemoActivity.this);
                //    设置Title的图标
                builder.setIcon(R.drawable.ic_launcher);
                //    设置Title的内容
                builder.setTitle("弹出警告框");
                //    设置Content来显示一个信息
                builder.setMessage("确定删除吗？");
                //    设置一个PositiveButton
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(DialogDemoActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(DialogDemoActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                    }
                });
                //    设置一个NeutralButton
                builder.setNeutralButton("忽略", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(DialogDemoActivity.this, "neutral: " + which, Toast.LENGTH_SHORT).show();
                    }
                });
                //    显示出该对话框
                builder.show();
            }
        });
		
		
		button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogDemoActivity.this);
                builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("选择一个城市");
                //    指定下拉列表的显示数据
                final String[] cities = {"广州", "上海", "北京", "香港", "澳门"};
                //    设置一个下拉的列表选择项
                builder.setItems(cities, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(DialogDemoActivity.this, "选择的城市为：" + cities[which], Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
		
		button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogDemoActivity.this);
                builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("请选择性别");
                final String[] sex = {"男", "女", "未知性别"};
                //    设置一个单项选择下拉框
                /**
                 * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                 * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认'女' 会被勾选上
                 * 第三个参数给每一个单选项绑定一个监听器
                 */
                builder.setSingleChoiceItems(sex, 1, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(DialogDemoActivity.this, "性别为：" + sex[which], Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        
                    }
                });
                builder.show();
            }
        });
		
		button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogDemoActivity.this);
                builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("爱好");
                final String[] hobbies = {"篮球", "足球", "网球", "斯诺克"};
                //    设置一个单项选择下拉框
                /**
                 * 第一个参数指定我们要显示的一组下拉多选框的数据集合
                 * 第二个参数代表哪几个选项被选择，如果是null，则表示一个都不选择，如果希望指定哪一个多选选项框被选择，
                 * 需要传递一个boolean[]数组进去，其长度要和第一个参数的长度相同，例如 {true, false, false, true};
                 * 第三个参数给每一个多选项绑定一个监听器
                 */
                builder.setMultiChoiceItems(hobbies, null, new DialogInterface.OnMultiChoiceClickListener()
                {
                    StringBuffer sb = new StringBuffer(100);
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked)
                    {
                        if(isChecked)
                        {
                            sb.append(hobbies[which] + ", ");
                        }
                        Toast.makeText(DialogDemoActivity.this, "爱好为：" + sb.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        
                    }
                });
                builder.show();
            }
        });
		
//		button5.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				LayoutInflater inflater = LayoutInflater.from(DialogDemoActivity.this);
//				View view = inflater.inflate(R.layout.view_window_title, null);
//				ToolAlert.dialog(DialogDemoActivity.this, view);
//			}
//		});
		
		
	}
	
	public static ProgressDialog creteProgressDialog(Context context,String text) {
		
		final ProgressDialog dlg = new ProgressDialog(context);
		
		dlg.show();
		dlg.setContentView(R.layout.demo_dialog_loading);
		LinearLayout root = (LinearLayout) dlg.findViewById(R.id.progressDialog);
		root.setGravity(android.view.Gravity.CENTER);

		LoadingView mLoadView = new LoadingView(context);
		mLoadView.setDrawableResId(R.drawable.ic_launcher);
		root.addView(mLoadView);
		TextView alert = new TextView(context);
		alert.setText(text);
		root.addView(alert);
		return dlg;
	}
}
