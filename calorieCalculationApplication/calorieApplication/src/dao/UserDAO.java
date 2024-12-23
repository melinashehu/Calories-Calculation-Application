package dao;

import entity.*;

public interface UserDAO {
    public User getUserById(int id);
    public User getUserByEmailAndPassword(String email, String password);

}
