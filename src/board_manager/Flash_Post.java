package board_manager;


import contact_manager_package.Contact;
import java.util.Date;

public class Flash_Post extends Post{

    protected Date date_start;
    protected Date date_end;

    public Flash_Post(int identifier, String title, String body, Contact owner, Date publication){

        super(identifier, title, body, owner, publication);
    }
    
    public Date getDateStart() {return date_start;}
    public Date getDateEnd() {return date_end;}

    public void setDateStart(Date date_start) {this.date_start = date_start;}
    public void setDateEnd(Date date_end) {this.date_end = date_end;}

    public String toString(){

        if(date_end!=null){

            return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner + "; Publication: " + publication + "; Expiration Date: " + date_end + "}";
        }

        else{

            return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner + "; Publication: " + publication + "}";
        }
    }
}
