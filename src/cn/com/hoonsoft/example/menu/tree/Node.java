package cn.com.hoonsoft.example.menu.tree;

/**
 * 软件名称:android无限级树源码
 * 作者：shaolong
 * 网址:http://londroid.5d6d.com
 * 
 * londroid(龙卓论坛)申明：
 * 1)本站不保证所提供软件或程序的完整性和安全性
 * 2)转载或使用本站提供的资源或源码请勿删除本说明文件或文字
 * 3)本站源码为网上搜集或网友提供或本站技术人员编写,
 * 如果涉及或侵害到您的版权,请立即写信通知我们
 * 4)本站提供代码只可供研究学习使用,切勿用于商业用途,由此引起一切后果与本站无关
 * 5)源码后续升级或修补,我们会在该源码跟帖发布
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 树节点
 * @author LongShao
 *
 */
public class Node {
    private Node parent;//父节点
    private List<Node> children = new ArrayList<Node>();//子节点
    private String text;//节点显示的文字
    private String value;//节点的值
    private int icon = -1;//是否显示小图标,-1表示隐藏图标
    private boolean isChecked = false;//是否处于选中状态
    private boolean isExpanded = true;//是否处于展开状态
    private boolean hasCheckBox = true;//是否拥有复选框
    
    /**
     * Node构造函数
     * @param text 节点显示的文字
     * @param value 节点的值
     */
    public Node(String text,String value){
    	this.text = text;
    	this.value = value;
    }
    
    /**
     * 设置父节点
     * @param node
     */
    public void setParent(Node node){
    	this.parent = node;
    }
    /**
     * 获得父节点
     * @return
     */
    public Node getParent(){
    	return this.parent;
    }
    /**
     * 设置节点文本
     * @param text
     */
    public void setText(String text){
    	this.text = text;
    }
    /**
     * 获得节点文本
     * @return
     */
    public String getText(){
    	return this.text;
    }
    /**
     * 设置节点值
     * @param value
     */
    public void setValue(String value){
    	this.value = value;
    }
    /**
     * 获得节点值
     * @return
     */
    public String getValue(){
    	return this.value;
    }
    /**
     * 设置节点图标文件
     * @param icon
     */
    public void setIcon(int icon){
    	this.icon = icon;
    }
    /**
     * 获得图标文件
     * @return
     */
    public int getIcon(){
    	return icon;
    }
    /**
     * 是否根节点
     * @return
     */
    public boolean isRoot(){
    	return parent==null?true:false;
    }
    /**
     * 获得子节点
     * @return
     */
    public List<Node> getChildren(){
    	return this.children;
    }
    /**
     * 添加子节点
     * @param node
     */
    public void add(Node node){
    	if(!children.contains(node)){
    		children.add(node);
    	}
    }
    /**
     * 清除所有子节点
     */
    public void clear(){
    	children.clear();
    }
    /**
     * 删除一个子节点
     * @param node
     */
    public void remove(Node node){
    	if(!children.contains(node)){
    		children.remove(node);
    	}
    }
    /**
     * 删除指定位置的子节点
     * @param location
     */
    public void remove(int location){
    	children.remove(location);
    }
    /**
     * 获得节点的级数,根节点为0
     * @return
     */
    public int getLevel(){
    	return parent==null?0:parent.getLevel()+1;
    }
    /**
     * 设置节点选中状态
     * @param isChecked
     */
    public void setChecked(boolean isChecked){
    	this.isChecked = isChecked;
    }
    /**
     * 获得节点选中状态
     * @return
     */
    public boolean isChecked(){
        return isChecked;
    }
    /**
     * 设置是否拥有复选框
     * @param hasCheckBox
     */
    public void setCheckBox(boolean hasCheckBox){
    	this.hasCheckBox = hasCheckBox;
    }
    /**
     * 是否拥有复选框
     * @return
     */
    public boolean hasCheckBox(){
        return hasCheckBox;
    }
    
    /**
     * 是否叶节点,即没有子节点的节点
     * @return
     */
    public boolean isLeaf(){
    	return children.size()<1?true:false;
    }
    /**
    * 当前节点是否处于展开状态 
    * @return
    */
    public boolean isExpanded(){
        return isExpanded;
    }
    /**
     * 设置节点展开状态
     * @return
     */
    public void setExpanded(boolean isExpanded){
    	 this.isExpanded =  isExpanded;
    }
    /**
     * 递归判断父节点是否处于折叠状态,有一个父节点折叠则认为是折叠状态
     * @return
     */
    public boolean isParentCollapsed(){
    	if(parent==null)return !isExpanded;
    	if(!parent.isExpanded())return true;
    	return parent.isParentCollapsed();
    }
    /**
     * 递归判断所给的节点是否当前节点的父节点
     * @param node 所给节点
     * @return
     */
    public boolean isParent(Node node){
    	if(parent==null)return false;
    	if(node.equals(parent))return true;
    	return parent.isParent(node);
    }
}
