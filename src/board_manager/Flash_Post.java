package board_manager;


import contact_manager_package.Contact;
import java.util.Date;
import java.util.ArrayList;

public class Flash_Post extends Post{

    private Date date_end;
    private Date date_pub;
    
    
    public Flash_Post() {};
    
    public Flash_Post(int identifier, String title, String body, Contact owner,ArrayList<Contact> destinators, Date date_pub, Date date_end){

        super(identifier, title, body, owner, destinators, date_pub);
        this.date_pub = date_pub;
        this.date_end = date_end;
    }
    
    
    
    public Date getDatePub() {return date_pub;}
    public Date getDateEnd() {return date_end;}
    public void setDatePub(Date date_pub) {this.date_pub = date_pub;}
    public void setDateEnd(Date date_end) {this.date_end = date_end;}
    

    
    
}
