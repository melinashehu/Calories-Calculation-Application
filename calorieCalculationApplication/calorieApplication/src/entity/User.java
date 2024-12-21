package entity;

public class User {
    private String userName;
    private String email;
    private String password;

    public User(){

    }

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String toString(){
        return "User{"+"name= "+userName+" , email= "+email+", password= "+password+" }";
    }
}
