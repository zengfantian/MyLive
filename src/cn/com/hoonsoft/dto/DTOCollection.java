package cn.com.hoonsoft.dto;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 数据传输对象集合
 * 
 * @author Java
 * @version 1.0
 */
public class DTOCollection<E extends DTO<?, ?>> extends AbstractCollection<E>
		implements Serializable, Cloneable {
	/**
     * 
     */
	private static final long serialVersionUID = 2303371883543057514L;

	public Iterator<E> iterator() {
		try {
			return new DTOIterator();
		} catch (Throwable cause) {
			throw new RuntimeException("创建迭代器失败", cause);
		}
	}

	public int size() {
		return this.transferData.size();
	}

	/**
	 * 赋值
	 * 
	 * @exception RuntimeException
	 *                操作DTO失败异常
	 * @param object
	 *            值
	 */
	public boolean add(E object) {
		if (!DTO.class.isInstance(object)) {
			throw new RuntimeException("要添加的对象不是DTO类或其子类的实例");
		}
		if (readonly) {
			throw new RuntimeException("该DTOCollection不可写");
		} else {
			return this.transferData.add(object);
		}
	}

	protected class DTOIterator implements Iterator<E> {

		public boolean hasNext() {
			return cursor != transferData.size();
		}

		public E next() {
			E dtoObject = transferData.get(cursor++);
			dtoObject.setReadonly(readonly);
			return dtoObject;
		}

		public void remove() {
			if (readonly) {
				throw new RuntimeException("该DTOIterator不可写");
			} else {
				transferData.remove(--cursor);
			}

		}

		public int cursor = 0;
	}

	/**
	 * 传递数据
	 */
	private List<E> transferData = new ArrayList<E>();

	protected List<E> getTransferData() {
		return this.transferData;

	}

	/**
	 * 只读开关
	 */
	private boolean readonly = false;

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

}
