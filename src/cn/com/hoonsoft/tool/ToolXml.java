package cn.com.hoonsoft.tool;

import com.thoughtworks.xstream.XStream;

/**
 * XML工具类
 * @author 曾繁添
 *
 */
public class ToolXml {

    /**
     * java 转换成xml
     * 
     * @Title: toXml
     * @Description: 将JavaBean转成XML
     * @param bean 对象实例
     * @return String xml字符串
     */
    public static String toXml(Object bean) {
        XStream xstream = new XStream();
        xstream.processAnnotations(bean.getClass()); 
        return xstream.toXML(bean);
    }

    /**
     * 将传入xml文本转换成Java对象
     * @param xml
     * @param bean xml对应的class类
     * @return T xml对应的class类的实例对象 调用的方法实例：
     * PersonBean person=XmlUtil.toBean(xmlStr, PersonBean.class);
     */
    public static <T> T toBean(String xml, Class<T> bean) {
        XStream xstream = new XStream();
        xstream.processAnnotations(bean);
        T obj = (T) xstream.fromXML(xml);
        return obj;
    }
}
