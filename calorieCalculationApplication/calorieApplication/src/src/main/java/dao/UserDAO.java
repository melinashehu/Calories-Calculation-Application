package dao;

import entity.*;
import java.util.List;
public interface UserDAO {
    public List<User> getAllUsers();
    public User getUserById(int id);
    public User getUserByEmailAndPassword(String email, String password);
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(int id);
    public List<Integer> getAllUsersIds();
    public List<Boolean> getHasExceededMoneyLimitColumn();
    public int getUserIdByEmail(String email);
}
