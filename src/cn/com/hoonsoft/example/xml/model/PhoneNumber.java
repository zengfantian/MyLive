package cn.com.hoonsoft.example.xml.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("phone-number")
public class PhoneNumber {

	private int code;
	private String number;
	
	public PhoneNumber() {
	}

	public PhoneNumber(int code, String number) {
		this.code = code;
		this.number = number;
	}

	@Override
	public String toString() {
		return "code="+code + "\t"+ "number="+number ;
	}
}