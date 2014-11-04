package cn.com.hoonsoft.example.menu;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;
import cn.com.hoonsoft.R;

public class TestExpandableListActivity extends Activity {
	/** Called when the activity is first created. */
	ExpandableListView expandableList;
	TreeViewAdapter adapter;
	SuperTreeViewAdapter superAdapter;
	Button btnNormal, btnSuper;
	// Sample data set. children[i] contains the children (String[]) for
	// groups[i].
	public String[] groups = { "xxxx好友", "xxxx同学", "xxxxx女人" };
	public String[][] child = { { "A君", "B君", "C君", "D君" },
			{ "同学甲", "同学乙", "同学丙" }, { "御姐", "萝莉" } };

	public String[] parent = { "xxxx好友", "xxxx同学" };
	public String[][][] child_grandson = { { { "A君" }, { "AA", "AAA" } },
			{ { "B君" }, { "BBB", "BBBB", "BBBBB" } },
			{ { "C君" }, { "CCC", "CCCC" } },
			{ { "D君" }, { "DDD", "DDDD", "DDDDD" } }, };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_tree_menu);
		this.setTitle("ExpandableListView练习----hellogv");
		btnNormal = (Button) this.findViewById(R.id.btnNormal);
		btnNormal.setOnClickListener(new ClickEvent());
		btnSuper = (Button) this.findViewById(R.id.btnSuper);
		btnSuper.setOnClickListener(new ClickEvent());
		adapter = new TreeViewAdapter(this, TreeViewAdapter.PaddingLeft >> 1);
		superAdapter = new SuperTreeViewAdapter(this, stvClickEvent);
		expandableList = (ExpandableListView) TestExpandableListActivity.this
				.findViewById(R.id.ExpandableListView01);
	}

	class ClickEvent implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			adapter.RemoveAll();
			adapter.notifyDataSetChanged();
			superAdapter.RemoveAll();
			superAdapter.notifyDataSetChanged();

			if (v == btnNormal) {
				List<TreeViewAdapter.TreeNode> treeNode = adapter.GetTreeNode();
				for (int i = 0; i < groups.length; i++) {
					TreeViewAdapter.TreeNode node = new TreeViewAdapter.TreeNode();
					node.parent = groups[i];
					for (int ii = 0; ii < child[i].length; ii++) {
						node.childs.add(child[i][ii]);
					}
					treeNode.add(node);
				}

				adapter.UpdateTreeNode(treeNode);
				expandableList.setAdapter(adapter);
				expandableList
						.setOnChildClickListener(new OnChildClickListener() {

							@Override
							public boolean onChildClick(
									ExpandableListView arg0, View arg1,
									int parent, int children, long arg4) {

								String str = "parent id:"
										+ String.valueOf(parent)
										+ ",children id:"
										+ String.valueOf(children);
								Toast.makeText(TestExpandableListActivity.this, str,
										300).show();
								return false;
							}
						});
			} else if (v == btnSuper) {
				List<SuperTreeViewAdapter.SuperTreeNode> superTreeNode = superAdapter
						.GetTreeNode();
				for (int i = 0; i < parent.length; i++)// 第一层
				{
					SuperTreeViewAdapter.SuperTreeNode superNode = new SuperTreeViewAdapter.SuperTreeNode();
					superNode.parent = parent[i];

					// 第二层
					for (int ii = 0; ii < child_grandson.length; ii++) {
						TreeViewAdapter.TreeNode node = new TreeViewAdapter.TreeNode();
						node.parent = child_grandson[ii][0][0];// 第二级菜单的标题

						for (int iii = 0; iii < child_grandson[ii][1].length; iii++)// 第三级菜单
						{
							node.childs.add(child_grandson[ii][1][iii]);
						}
						superNode.childs.add(node);
					}
					superTreeNode.add(superNode);

				}
				superAdapter.UpdateTreeNode(superTreeNode);
				expandableList.setAdapter(superAdapter);
			}
		}
	}

	/** 三级树形菜单的事件不再可用，本函数由三级树形菜单的子项（二级菜单）进行回调 */
	OnChildClickListener stvClickEvent = new OnChildClickListener() {

		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			String str = "parent id:" + String.valueOf(groupPosition)
					+ ",children id:" + String.valueOf(childPosition);
			Toast.makeText(TestExpandableListActivity.this, str, 300).show();

			return false;
		}

	};
}