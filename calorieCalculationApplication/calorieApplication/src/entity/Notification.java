package entity;

public class Notification {
    private int id;
    private int userId;
    private String notificationText;
    private String role; //ne baze te rolit do te shfaqet mesazhi (per t'u ri-menduar kjo pjese)

    public Notification(int id, int userId, String notificationText, String role) {
        this.id = id;
        this.userId = userId;
        this.notificationText = notificationText;
        this.role = role;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}

    public String getNotificationText() {return notificationText;}
    public void setNotificationText(String notificationText) {this.notificationText = notificationText;}

    /* jo shume e nevojshme, mund dhe te mos vendoset
    public String toString(){

    }*/
}
