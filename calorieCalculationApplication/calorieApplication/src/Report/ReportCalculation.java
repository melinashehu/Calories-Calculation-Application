package Report;

public interface ReportCalculation {
    //    Duhet të shtojmë metoda të reja për të llogaritur:
//    Totalin e kalorive të konsumuara nga përdoruesi.
//    Shpenzimet totale të përdoruesit për ushqime.
//    Numrin e ditëve kur kalorive kanë kaluar një kufi të caktuar
    double calculateTotalCaloriesConsumed(int userId);
    double calculateTotalSpendingMoney(int userId);
    int calculateDaysAboveCalorieThreshold(int userId, double calorieThreshold);
}
