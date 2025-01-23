package DAOTest;
import dao.DatabaseConnection;
import dao.FoodDAOImplementation;


import entity.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class FoodDAOImplementationTest {
    private FoodDAOImplementation foodDAO;
    private DatabaseConnection db = new DatabaseConnection();

        @BeforeEach
        public void setUp() throws Exception {
            DatabaseConnection mockConnection = new DatabaseConnection();
            foodDAO = new FoodDAOImplementation(mockConnection);

            try (Connection connection = db.getConnection();
                 Statement stmt = connection.createStatement()) {

            }
        }

        @Test
        public void testGetAllFoods() {
            List<Food> foods = foodDAO.getAllFoods();
            assertEquals(1, foods.size());
            assertEquals("Banana", foods.get(0).getFoodName());
        }
}

