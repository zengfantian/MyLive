package tests;

import android.test.AndroidTestCase;
import cn.com.hoonsoft.R;
import cn.com.hoonsoft.example.xml.model.Person;
import cn.com.hoonsoft.example.xml.model.PhoneNumber;
import cn.com.hoonsoft.tool.ToolFile;
import cn.com.hoonsoft.tool.ToolXml;

public class ToolXmlTest extends AndroidTestCase {
	
	private static final String TAG = ToolXmlTest.class.getSimpleName();
	
	public void testXmlToBean() throws Throwable{
		String result = ToolFile.readRawValue(getContext(), R.raw.person);
		System.out.println("Xml数据="+result);
		Person obj = ToolXml.toBean(result,Person.class);
		System.out.println("Xml-->Bean="+obj);
	}
	
	public void testBeanToXml() throws Throwable{
		Person joe = new Person("Joe","Walnes"); 
		joe.setPhonex(new PhoneNumber(123,"1234-456")); 
		joe.setFax(new PhoneNumber(123,"9999-999")); 
		String result = ToolXml.toXml(joe);
		System.out.println("Bean-->xml="+result);
	}
}
