package entity;
/**
 * Represents a notification in the system.
 * This class should NOT be modified without careful consideration.
 * Any changes to this class can impact the other parts of the application.
 */
import java.util.Date;

/**
 * @author :Amina
 */

public class Notification {
    private int id;
    private int userId;
    private String message;
    private Date dateSent;
    private boolean is_read;

    public Notification(int id, int userId, String message, Date dateSent, boolean is_read) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.dateSent = dateSent;
        this.is_read = is_read;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}

    public String getMessage() {return message;}
    public void setMessage(String message) {this.message= message;}

    public Date getDateSent() {
        return dateSent;
    }
    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public boolean isIs_read() {
        return is_read;
    }
    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }

}
