package cn.com.hoonsoft.config;

import android.os.Environment;

/**
 * 业务常量类
 * @author 曾繁添
 * @version 1.0
 */
public class IConstant {

    /*****************************Common Constant***(start)******************************/
    public final static String JSON_ROOT = "item";
    
    public final static String ERROR = "error";
    
    public final static String HANDLE_CLASS_NAME = "handleClassName";
    
    public static final int PER_PAGE_SIZE = 10;
    
    public final static String USER_INFO = "userInfo";
    
    /*****************************Common Constant***(end)******************************/
    
    /**
     * @alias 编码_用户_类型_个人用户
     */
    public static final String CODE_USER_TYPE_PERSONAL = "personal";

    /**
     * @alias 编码_用户_类型_商家用户
     */
    public static final String CODE_USER_TYPE_MERCHANT = "merchant";
    
    /**
     * @alias 编码_商家用户_类型_快递
     */
    public static final String CODE_MERCHANT_TYPE_EXPRESS = "express";

    /**
     * @alias 编码_商家用户_类型_物流
     */
    public static final String CODE_MERCHANT_TYPE_LOGISTICS = "logistics";

    /**
     * @alias 编码_商家用户_类型_搬家
     */
    public static final String CODE_MERCHANT_TYPE_MOVE = "move";

    /**
     * @alias 编码_商家用户_类型_货车
     */
    public static final String CODE_MERCHANT_TYPE_LORRY = "lorry";

    /**
     * @alias 编码_商家用户_类型_商务租车
     */
    public static final String CODE_MERCHANT_TYPE_RENTCAR = "rentCar";
    
    /**
     * @alias 编码_需求_类型_物流
     */
    public static final String CODE_REQUIRE_TYPE_LOGISTICS = "logistics";

    /**
     * @alias 编码_需求_类型_搬家
     */
    public static final String CODE_REQUIRE_TYPE_MOVE = "move";

    /**
     * @alias 编码_需求_类型_货车
     */
    public static final String CODE_REQUIRE_TYPE_LORRY = "lorry";

    /**
     * @alias 编码_需求_类型_商务租车
     */
    public static final String CODE_REQUIRE_TYPE_RENTCAR = "rentCar";
    
    /*****************************Bussess Constant***(start)******************************/
    
    /**
     * @alias 编码_需求_状态_未交易
     */
    public static final String CODE_REQUIRE_STATUS_NOTRADING = "notrading";

    /**
     * @alias 编码_需求_状态_交易中
     */
    public static final String CODE_REQUIRE_STATUS_TRADING = "trading";

    /**
     * @alias 编码_需求_状态_已完成
     */
    public static final String CODE_REQUIRE_STATUS_FINISHED = "finished";

    /**
     * @alias 编码_需求_状态_已放弃
     */
    public static final String CODE_REQUIRE_STATUS_ABANDONED = "abandoned";
    
    /***选项卡索引：第一个选项卡***/
    public static final int TAB_INDEX_1 = 0;
    
    /***选项卡索引：第二个选项卡***/
    public static final int TAB_INDEX_2 = 1;
    
    /***选项卡索引：第三个选项卡***/
    public static final int TAB_INDEX_3 = 2;
    
    /***选项卡索引：第四个选项卡***/
    public static final int TAB_INDEX_4 = 3;
    
    /**最大绑定车辆数**/
    public static final int  MAX_BOUND_CAR_SIZE=10;
    /*****************************Bussess Constant***(end)******************************/
    
    
    /*******************************相片开始*****************************************/
    /**
	 * 头像路径
	 */
	public static final String IMAGE_UNSPECIFIED = "image/*";
	/**
	 * 拍照
	 */
	public static final int PHOTO_GRAPH = 1;

	/**
	 * 缩放
	 */
	public static final int PHOTO_ZOOM = 2; 
	/**
	 * 结果
	 */
	public static final int PHOTO_RESOULT = 3;
	
	/**
	 * nothing
	 */
	public static final int NONE = 0;
	
	/**
	 * 临时存储图片路径
	 */
	public static final String YIZHAO_PHOTOTEMP = "/yizhao_photoTemp";
	/**
	 * 
	 * the path of SDCARD
	 *
	 */
	public static final String SDCARD = Environment
			.getExternalStorageDirectory().getAbsolutePath();
	/**
	 * 头像截取宽度
	 */
	public static final int PHOTOW=100;
	/**
	 * 头像截取高度
	 */
	public static final int PHOTOH=100;
	/*******************************相片结束*****************************************/
}
