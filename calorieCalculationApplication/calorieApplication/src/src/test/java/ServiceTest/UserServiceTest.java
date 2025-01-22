package ServiceTest;
import dao.FoodDAOImplementation;
import dao.UserDAOImplementation;
import entity.Food;
import entity.StatisticalReport;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserService;
import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        userService = new UserService();

        Field foodDAOField = UserService.class.getDeclaredField("foodDAO");
        foodDAOField.setAccessible(true);
        foodDAOField.set(userService, new FoodDAOImplementation() {
            @Override
            public List<Double> getCalorieValuesForAWeeklyPeriodForAUser(int userId, Date startingDate) {
                return Arrays.asList(500.0, 500.0, 500.0, 500.0);
            }

            @Override
            public List<Double> getMoneySpentFromAUser(int userId, Date startingDate) {
                return Arrays.asList(200.0, 150.0, 150.0);
            }

            @Override
            public List<Double> getTodaysTotalCalories(int userId) {
                return Arrays.asList(400.0, 300.0);
            }

            @Override
            public List<Food> getFoodsForUserByDate(int userId) {
                return Arrays.asList(
                        new Food(1, 1, "Apple", 1500.0, 100.0, Date.valueOf(LocalDate.now())), // Kalori për sot
                        new Food(2, 2, "Pizza", 1800.0, 600.0, Date.valueOf(LocalDate.now().minusDays(1))), // Kalori për dje
                        new Food(3, 3, "Burger", 2600.0, 800.0, Date.valueOf(LocalDate.now().minusDays(2))), // Kalori për pardje
                        new Food(4, 4, "Salad", 300.0, 200.2, Date.valueOf(LocalDate.now().minusDays(3))) // Kalori për katër ditë më parë
                );
            }

        });

        Field userDAOField = UserService.class.getDeclaredField("userDAO");
        userDAOField.setAccessible(true);
        userDAOField.set(userService, new UserDAOImplementation() {
            @Override
            public List<User> getAllUsers() {
                return Arrays.asList(
                        new User(1, "User1", "User1@gmail.com"),
                        new User(2, "User2", "User2@gmail.com")
                );
            }
        });
    }

    @Test
    void testCalculateTotalCaloriesConsumedPerWeek() {
        int userId = 1;
        Date startingDate = Date.valueOf(LocalDate.now().minusDays(7));

        double totalCalories = userService.calculateTotalCaloriesConsumedPerWeek(userId, startingDate);

        assertEquals(2000.0, totalCalories, "Total calories should be 2000.0");
    }

    @Test
    void testCalculateTotalMoneySpentPerWeek() {
        int userId = 1;
        Date startingDate = Date.valueOf(LocalDate.now().minusDays(7));

        double totalMoneySpent = userService.calculateTotalMoneySpentPerWeek(userId, startingDate);

        assertEquals(500.0, totalMoneySpent, "Total money spent should be 500.0");
    }

    @Test
    void testSumOfTodaysTotalCalories() {
        int userId = 1;

        double todaysCalories = userService.sumofTodaysTotalCalories(userId);

        assertEquals(700.0, todaysCalories, "Today's total calories should be 700.0");
    }
    @Test
    void testGenerateUserReportBelowCalorieThreshold() {
        int userId = 1;
        Date startingDate = Date.valueOf(LocalDate.now().minusDays(7));
        double calorieThreshold = 5000.0;

        StatisticalReport report = userService.generateUserReport(userId, startingDate, calorieThreshold);

        assertEquals(2000.0, report.getTotalCalories(), "Total calories should be 2000.0");
        assertEquals(500.0, report.getTotalSpendingMoney(), "Total spending should be 500.0");
        assertEquals(0, report.getDaysAboveCalorieThreshold(), "Days above calorie threshold should be 0.");
    }


    @Test
    void testGenerateUserReportNoFood() throws NoSuchFieldException, IllegalAccessException {
        int userId = 2;
        Date startingDate = Date.valueOf(LocalDate.now().minusDays(7));
        double calorieThreshold = 2500.0;

        Field foodDAOField = UserService.class.getDeclaredField("foodDAO");
        foodDAOField.setAccessible(true);
        foodDAOField.set(userService, new FoodDAOImplementation() {
            @Override
            public List<Double> getCalorieValuesForAWeeklyPeriodForAUser(int userId, Date startingDate) {
                return userId == 2 ? Arrays.asList() : Arrays.asList(500.0, 500.0, 500.0, 500.0);
            }

            @Override
            public List<Double> getMoneySpentFromAUser(int userId, Date startingDate) {
                return userId == 2 ? Arrays.asList() : Arrays.asList(200.0, 150.0, 150.0);
            }

            @Override
            public List<Double> getTodaysTotalCalories(int userId) {
                return Arrays.asList(400.0, 300.0);
            }

            @Override
            public List<Food> getFoodsForUserByDate(int userId) {
                return userId == 2 ? Arrays.asList() : Arrays.asList(
                        new Food(1, 1, "Apple", 1500.0, 100.0, Date.valueOf(LocalDate.now())),
                        new Food(2, 2, "Pizza", 1800.0, 600.0, Date.valueOf(LocalDate.now().minusDays(1))),
                        new Food(3, 3, "Burger", 2600.0, 800.0, Date.valueOf(LocalDate.now().minusDays(2))),
                        new Food(4, 4, "Salad", 300.0, 200.2, Date.valueOf(LocalDate.now().minusDays(3)))
                );
            }
        });

        StatisticalReport report = userService.generateUserReport(userId, startingDate, calorieThreshold);

        assertEquals(0.0, report.getTotalCalories(), "Total calories should be 0.0 for a user with no food entries.");
        assertEquals(0.0, report.getTotalSpendingMoney(), "Total spending should be 0.0 for a user with no food entries.");
        assertEquals(0, report.getDaysAboveCalorieThreshold(), "Days above calorie threshold should be 0 for a user with no food entries.");
    }

}
