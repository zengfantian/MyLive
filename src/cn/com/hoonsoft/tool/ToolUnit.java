package cn.com.hoonsoft.tool;

import android.util.DisplayMetrics;
import cn.com.hoonsoft.model.SysEnv;

/**
 * 单位换算工具类
 * @author 曾繁添<br>
 * 	px  ：像素 <br>
	in  ：英寸<br>
	mm  ：毫米<br>
	pt  ：磅，1/72 英寸<br>
	dp  ：一个基于density的抽象单位，如果一个160dpi的屏幕，1dp=1px<br>
	dip ：等同于dp<br>
	sp  ：同dp相似，但还会根据用户的字体大小偏好来缩放。<br>
	建议使用sp作为文本的单位，其它用dip<br>
	布局时尽量使用单位dip，少使用px <br>
 */
public class ToolUnit {
	
	/**设备显示材质**/
	private static DisplayMetrics mDisplayMetrics = SysEnv.getDisplayMetrics();
	
	/**
	 * sp转换px
	 * @param spValue sp数值
	 * @return px数值
	 */
	public static int spTopx(float spValue) {
        return (int) (spValue * mDisplayMetrics.scaledDensity + 0.5f);
    }

	/**
	 * px转换sp
	 * @param pxValue px数值
	 * @return sp数值
	 */
    public static int pxTosp(float pxValue) {
        return (int) (pxValue / mDisplayMetrics.scaledDensity + 0.5f);
    }

	/**
	 * dip转换px
	 * @param dipValue dip数值
	 * @return px数值
	 */
    public static int dipTopx(int dipValue) {
        return (int) (dipValue * mDisplayMetrics.density + 0.5f);
    }

	/**
	 * px转换dip
	 * @param pxValue px数值
	 * @return dip数值
	 */
    public static int pxTodip(float pxValue) {
        return (int) (pxValue / mDisplayMetrics.density + 0.5f);
    }
}
