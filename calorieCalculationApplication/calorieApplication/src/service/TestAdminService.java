package service;

import Report.AdminReport;
import calendar.Calendar;
import dao.UserDAOImplementation;
import entity.User;
import login.UserSession;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TestAdminService {

    public static void main(String[] args) {

        /*if (UserSession.getLoggedInUser() == null || UserSession.getLoggedInUser().getUserId() != userId) {
            System.out.println("Gabim: Përdoruesi me ID " + userId + " nuk ekziston ose nuk është i loguar.");
            return;
        }*/
        User adminUser = new User();
        adminUser.setUserId(1);
        adminUser.setUserName("admin");
        adminUser.setRole("admin");
        UserSession.setLoggedInUser(adminUser);

        AdminService adminService = new AdminService();

        /*if (!(UserSession.getLoggedInUser().getRole().equals("admin"))) {
            System.out.println("Vetem administratoret mund t'i aksesojne keto te dhena!");
        }*/

        System.out.println("Testimi i metodes generateWeeklyFoodReport");
        try {
            Date startingDate = Date.valueOf("2024-12-28");
            int userId = 1;
            System.out.println(adminService.generateWeeklyFoodReport(userId, startingDate));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Testimi i metodes generateWeeklyFoodReport");
        try {
            Date startingDate = Date.valueOf("2024-12-28");
            List<AdminReport> users = adminService.usersWhoExceededMonthlySpendingLimit(startingDate);
            System.out.println("Users who exceeded monthly spending limit:");
            users.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        User user = new User();
        user.setUserId(2);
        user.setUserName("user");
        user.setRole("user");
        UserSession.setLoggedInUser(user);


        System.out.println("\nTestojme metodat per nje user te thjeshte:");
        try {
            Date startingDate = Date.valueOf("2024-12-01");
            adminService.generateWeeklyFoodReport(2, startingDate);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            Date startingDate = Date.valueOf("2024-12-01");
            adminService.usersWhoExceededMonthlySpendingLimit(startingDate);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
        }

