package service;

import entity.StatisticalReport;
import login.UserSession;

import java.util.Scanner;

public class TestReport {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vendosni ID-në e përdoruesit: ");
        int userId = scanner.nextInt();

        if (UserSession.getLoggedInUser() == null || UserSession.getLoggedInUser().getUserId() != userId) {
            System.out.println("Gabim: Përdoruesi me ID " + userId + " nuk ekziston ose nuk është i loguar.");
            return;
        }


        StatisticalReportService reportService = new StatisticalReportService();
        double calorieThreshold = 2500.0;


        StatisticalReport report = reportService.generateReport(userId, calorieThreshold);

        System.out.println("Raporti Statistik:");
        System.out.println("Përdoruesi ID: " + report.getUserId());
        System.out.println("Kaloritë totale të konsumuara: " + report.getTotalCalories());
        System.out.println("Shpenzimet totale: " + report.getTotalSpendingMoney());
        System.out.println("Ditët mbi pragun e kalorive: " + report.getDaysAboveCalorieThreshold());
    }
}