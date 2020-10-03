package contact_manager_package;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Contact {

	private String name;
	private String surname;
	private Date birthday;
	private String email;
	private ArrayList <Interest> interests = new ArrayList <Interest>();
	
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
	public Date getBirthday() {return birthday;}

	public String getBirthdayString(){

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(birthday);
	}

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

		if(interests.contains(Interest.valueOf(interest))){

			return false;
		}

		if(!checkExistenceInterest(interest)){

			System.out.println("ERROR. No existe ese interes en la base de datos");
			return false;
		}
		
		interests.add(Interest.valueOf(interest));
		return true;
	}

	public boolean removeInterest(String interest){
		
		if(interests.contains(Interest.valueOf(interest))){

			interests.remove(Interest.valueOf(interest));
			return true;
		}
		
		return false;
	}

	public boolean checkExistenceInterest(String interest){
    
        for (Interest i : Interest.values()) {
            
            if(i.name().equals(interest)){

                return true;
            }
        }

        return false;
    }

	@Override

	public String toString(){
	
		return "Contact {Name: " + name + "; Surname: " + surname + "; Birthdate: " + birthday + "; Email: " + email + "; Interests: " + interests + "}";
	}

	public String toStringFile(){

		return name + "|" + surname + "|" + getBirthdayString() + "|" + email + "|" + interests;
	}
}
