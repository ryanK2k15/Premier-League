package model;

public class Name {


	private String firstName;
	private String middleName;
	private String lastName;
	
	public Name(String fn, String mn, String ln){
		this.firstName = fn;
		this.middleName = mn;
		this.lastName = ln;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString(){
		return this.firstName + " " + this.middleName + " " + this.lastName;
	}
	
	
}
