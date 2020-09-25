package contact_manager_package;

import java.util.Date;

public class Contact {

	private String name;
	private String surname;
	private Date birthday;
	private String email;
	//private Interests interests;
	
	public Contact() {};
	
	public Contact(String name, String surname, Date birthday, String email /*, Interests interests*/) {
		
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.email = email;
	}
	
	public String getName() {return name;}
	public String getSurname() {return surname;}
	public Date getBirthdate() {return birthday;}
	public String getEmail() {return email;}
	
	public void setName(String name) {this.name = name;}
	public void setSurname(String surname) {this.surname = name;}
	public void setBirthdate(Date birthdate) {this.birthday = birthdate;}
	public void setEmail(String email) {this.email = email;}
}
