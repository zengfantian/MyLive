package cn.com.hoonsoft.example.xml.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("person")
public class Person {

	private String firstName;
	private String lastName;
	private PhoneNumber phonex;
	private PhoneNumber fax;
	
	public Person() {
	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public PhoneNumber getPhonex() {
		return phonex;
	}

	public void setPhonex(PhoneNumber phonex) {
		this.phonex = phonex;
	}

	public PhoneNumber getFax() {
		return fax;
	}

	public void setFax(PhoneNumber fax) {
		this.fax = fax;
	}

	@Override
	public String toString() {
		return "firstName="+firstName + "\t"+ "lastName="+lastName + "\t"+"phonex="+phonex.toString() + "\t"+"fax="+fax.toString();
	}
	
}

