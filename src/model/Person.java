package model;


public class Person {

	private Name name;
	private String phone;
	private String email;
	
	public Person(){}
	
	public Person(Name n, String p, String e){
		this.name = n;
		this.phone = p;
		this.email = e;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

