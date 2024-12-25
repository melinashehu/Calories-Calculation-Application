package dao;

import entity.*;

public interface UserDAO {
    public User getUserById(int id);
    public User getUserByEmailAndPassword(String email, String password);
    public boolean addUser(User user); //kthejme boolean per efekt abstraksioni
    public boolean updateUser(User user);
    public boolean deleteUser(int id);
}
