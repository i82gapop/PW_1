package contact_manager_package;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Iterator;

public class Contact {

	private String name;
	private String surname;
	private Date birthday;
	private String email;
	private ArrayList <Interest> interests;
	
	public Contact() {};

	public Contact(String name, String surname, Date birthday, String email) {
		
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.email = email;
	}
	
	
	public Contact(String name, String surname, Date birthday, String email, ArrayList <Interest> interests) {
		
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.email = email;
		this.interests = interests;
	}
	
	public String getName() {return name;}
	public String getSurname() {return surname;}
	public String getFullname() {return name + " " + surname;}
	public Date getBirthdate() {return birthday;}
	public String getEmail() {return email;}
	public ArrayList <Interest> getInterests(){return interests;}

	public int getAge(){

		Calendar calendar = Calendar.getInstance();
		int actual = calendar.get(Calendar.YEAR);
		calendar.setTime(birthday);
		int birth = calendar.get(Calendar.YEAR);
		return actual - birth;
	}

	public void setName(String name) {this.name = name;}
	public void setSurname(String surname) {this.surname = name;}
	public void setBirthdate(Date birthdate) {this.birthday = birthdate;}
	public void setEmail(String email) {this.email = email;}
	public void setInterest(ArrayList<Interest> interests){this.interests = interests;}
	
	
	public boolean addInterest(String interest){

		Iterator <Interest> it = interests.iterator();

		while(it.hasNext()){

			if((it.next() + "") == interest){

				return false;
			}
		}

		Interest aux;
		aux = Interest.valueOf(interest);
		interests.add(aux);
		
		return true;
	}

	public boolean removeInterest(String interest){
		
		Iterator <Interest> it = interests.iterator();

		while(it.hasNext()){

			if((it.next() + "") == interest){

				interests.remove(it.next());
				return true;
			}
		}
		
		return false;
	}

	public String toString(){
	
		return "Contact {Name: " + name + "; Surname: " + surname + "; Birthdate: " + birthday + "; Email: " + email + "; Interests: " + interests + "}";
	}
}
