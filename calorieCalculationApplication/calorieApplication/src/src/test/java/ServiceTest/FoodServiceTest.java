package ServiceTest;
import dao.FoodDAOImplementation;
import entity.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.FoodService;
import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodServiceTest {

        private FoodService foodService;

        @BeforeEach
        void setUp() throws Exception {
            foodService = new FoodService();

            Field foodDAOField = FoodService.class.getDeclaredField("foodDAO");
            foodDAOField.setAccessible(true);
            foodDAOField.set(foodService, new FoodDAOImplementation() {
                @Override
                public List<Food> getAllFoodsForAUser(int userId) {
                    return Arrays.asList(
                            new Food(1, userId, "Apple", 1500.0, 100.0, Date.valueOf(LocalDate.now().minusDays(5))),
                            new Food(2, userId, "Pizza", 1800.0, 600.0, Date.valueOf(LocalDate.now().minusDays(3))),
                            new Food(3, userId, "Burger", 2600.0, 800.0, Date.valueOf(LocalDate.now().minusDays(1))),
                            new Food(4, userId, "Salad", 300.0, 200.2, Date.valueOf(LocalDate.now()))
                    );
                }
            });
        }


        @Test
        void testFilterFoodByDateRangeNoFoodInRange() {
            int userId = 1;
            LocalDate startDate = LocalDate.now().minusDays(7);
            LocalDate endDate = LocalDate.now().minusDays(6);
            List<Food> filteredFoods = foodService.filterFoodByDateRange(userId, startDate, endDate);
            assertEquals(0, filteredFoods.size(), "There should be no food entries in the given date range.");
        }

    }
