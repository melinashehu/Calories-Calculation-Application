package entity;

/**
 * Represents a user in the system.
 * This class should NOT be modified without careful consideration.
 * Any changes to this class can impact the other parts of the application.
 */


public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    private String role;
    private double avgCalories;
    /**
     * @author :Melina
     */
    private boolean exceededCalories;



    public User(){

    }

    public User(int id, String userName, String email, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public User(String userName, double avgCalories) {
        this.userName = userName;
        this.avgCalories = avgCalories;
    }
    public User(String userName, double avgCalories, boolean exceededCalories) {
        this.userName = userName;
        this.avgCalories = avgCalories;
        this.exceededCalories = exceededCalories;
    }
    public int getUserId() {
        return id;
    }
    public void setUserId(int id) {this.id = id;}

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getUserPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getRole(){return role;}
    public void setRole(String role){this.role = role;}

    public double getAvgCalories() {
        return avgCalories;
    }
    public void setAvgCalories(double averageCalories) {
        this.avgCalories = averageCalories;
    }

    public boolean isExceededCalories() {
        return exceededCalories;
    }

    public void setExceededCalories(boolean exceededCalories) {
        this.exceededCalories = exceededCalories;
    }

    public String toString(){
        return "User{"+"name= "+userName+" , email= "+email+", password= "+password+", role="+role+"}";
    }
}
