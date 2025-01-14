package service;

import entity.*;

import java.sql.Date;
import java.util.List;

public class TestService {
    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        System.out.print("Vendosni ID-në e përdoruesit: ");
        int userId = scanner.nextInt();

        if (UserSession.getLoggedInUser() == null || UserSession.getLoggedInUser().getUserId() != userId) {
            System.out.println("Gabim: Përdoruesi me ID " + userId + " nuk ekziston ose nuk është i loguar.");
            return;
        }


        StatisticalReportService reportService = new StatisticalReportService();
        double calorieThreshold = 2500.0;


        StatisticalReport report = reportService.generateUserReport(userId, calorieThreshold);

        System.out.println("Raporti Statistik:");
        System.out.println("Përdoruesi ID: " + report.getUserId());
        System.out.println("Kaloritë totale të konsumuara: " + report.getTotalCalories());
        System.out.println("Shpenzimet totale: " + report.getTotalSpendingMoney());
        System.out.println("Ditët mbi pragun e kalorive: " + report.getDaysAboveCalorieThreshold());*/

        UserService userService = new UserService();
        java.sql.Date testDate = java.sql.Date.valueOf("2025-01-01");
        System.out.println(userService.calculateDaysAboveCalorieThresholdPerWeek(2, 2500));
       //report.printFoodEntriesPerWeekComparison();
        /*List<User> reportList = report.getAvgCaloriesPerUserLast7Days();
        if(reportList.isEmpty()){
            System.out.println("No reports found");
        }else{
            for(User user : reportList){
                System.out.println("User: "+user.getUserName()+", Avg Calories: "+user.getAvgCalories());
            }
        }*/
    }
}