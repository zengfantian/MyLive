package cn.com.hoonsoft.dto;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 数据传输对象
 * 
 * @author Java
 * @version 1.0
 */
public class DTO<K, V> extends HashMap<K, V>  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6377960213315892547L;

	/**
	 * 赋值
	 * 
	 * @param objKey
	 *            键值
	 * @param objValue
	 *            对应值
	 */
	public V put(K objKey, V objValue) {
		if (readonly) {
			throw new RuntimeException("属性只读");
		} else {
			return super.put(objKey, objValue);
		}

	}

	/**
	 * 只读开关
	 */
	private boolean readonly = false;

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

}
