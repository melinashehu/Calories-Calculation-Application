package entity;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    private String role; //perdorues i thjeshte apo admin

    public User(){

    }

    public User(int id, String userName, String email, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String toString(){
        return "User{"+"name= "+userName+" , email= "+email+", password= "+password+", role="+role+"}";
    }
}
