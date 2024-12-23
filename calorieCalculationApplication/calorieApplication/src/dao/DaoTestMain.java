package dao;

import dao.UserDAOImplementation;
import entity.*;

public class DaoTestMain {
    public static void main(String[] args) {
        UserDAOImplementation userDAO = new UserDAOImplementation();

        User newUser = new User(0, "test1", "test1@example.com", "testPassword", "user");
        boolean isAdded = userDAO.addUser(newUser);
        System.out.println("User added: " + isAdded);

        User userById = userDAO.getUserById(1);
        System.out.println("User fetched by ID: " + (userById != null ? userById : "Not found"));

        User userByCredentials = userDAO.getUserByEmail("test1@example.com");
        System.out.println("Login success: " + (userByCredentials != null ? "Yes" : "No"));

        boolean isDeleted = userDAO.deleteUser(1);
        System.out.println("User deleted: " + isDeleted);
    }
}
