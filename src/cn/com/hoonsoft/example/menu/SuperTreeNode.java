package cn.com.hoonsoft.example.menu;

import java.util.ArrayList;
import java.util.List;

public class SuperTreeNode {
	Object parent;
	// 二级树形菜单的结构体
	List<TreeViewAdapter.TreeNode> childs = new ArrayList<TreeViewAdapter.TreeNode>();
}