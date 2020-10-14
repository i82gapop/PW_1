package board_manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import contact_manager_package.Contact;

public class Flash_Post extends Post{

    protected Date date_start;
    protected Date date_end;

    public Flash_Post(int identifier, String title, String body, Contact owner, Date date_start, Date date_end){

        super(identifier, title, body, owner);

        this.date_start = date_start;
        this.date_end = date_end;
    }
    
    public Date getDateStart() {return date_start;}
    public Date getDateEnd() {return date_end;}

    public String getDateString(){

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(date_start) + "|" + format.format(date_end);
    }

    public void setDateStart(Date date_start) {this.date_start = date_start;}
    public void setDateEnd(Date date_end) {this.date_end = date_end;}

    public String toString(){

        if(date_end!=null | date_start!=null){

            return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner.getFullname() + "; Publication: " + publication + "; Starting Date: " + date_start + "; Expiration Date: " + date_end + "}";
        }

        else{

            return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner.getFullname() + "; Publication: " + publication + "}";
        }
    }

    public String toStringFile(){

        return identifier + "|" + title + "|" + body + "|" + owner.getFullname() + "|" + getPublicationString() + "|" + getStatusString() + "|" + getDateString();
    }
}
