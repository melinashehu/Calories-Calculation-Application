package controllerTest;

import javafx.application.Platform;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import controller.*;
import entity.User;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import service.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class AdminReportControllerTests {
    @Mock
    private AdminServiceInterface mockAdminService;

    @Mock
    private FoodServiceInterface mockFoodService;

    @Mock
    private UserServiceInterface mockUserService;

    private AdminReportController controller;

    private TextArea foodEntriesComparisonArea;
    private BarChart<String, Number> barChart;
    private TableView<User> userTable;
    private TableColumn<User, String> userIdColumn;
    private TableColumn<User, Boolean> moneyLimitColumn;
    private static boolean isJavaFXInitialized  = false;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        if (!isJavaFXInitialized) {
            Platform.startup(() -> {});
            isJavaFXInitialized = true;
        }

        foodEntriesComparisonArea = new TextArea();
        barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        userTable = new TableView<>();
        userIdColumn = new TableColumn<>("User ID");
        moneyLimitColumn = new TableColumn<>("Money Limit Exceeded");

        controller = new AdminReportController(mockAdminService, mockFoodService, mockUserService);

        controller.foodEntriesComparisonArea = foodEntriesComparisonArea;
        controller.barChart = barChart;
        controller.userTable = userTable;
        controller.userIdColumn = userIdColumn;
        controller.moneyLimitColumn = moneyLimitColumn;
    }

    @Test
    public void testShowEntriesComparison() {
        String expectedReport = "Test Report";
        when(mockAdminService.printFoodEntriesPerWeekComparison()).thenReturn(expectedReport);

        controller.showEntriesComparison();

        assertEquals(expectedReport, foodEntriesComparisonArea.getText());
    }


    @Test
    public void testShowCaloriesChart() {
        List<Integer> userIds = Arrays.asList(1, 2, 3);
        when(mockUserService.getAllUsersIdsService()).thenReturn(userIds);
        when(mockUserService.calculateTotalCaloriesConsumedPerWeek(anyInt(), any(Date.class))).thenReturn(2000.0);

        controller.showCaloriesChart();

        assertTrue(barChart.getData().size() > 0);
    }



    @Test
    public void testShowUsersThatExceededMoneyLimit() {
        List<Integer> userIds = Arrays.asList(1, 2, 3);
        List<Boolean> exceededLimits = Arrays.asList(true, false, true);
        when(mockUserService.getAllUsersIdsService()).thenReturn(userIds);
        when(mockUserService.getHasExceededMoneyLimitColumnService()).thenReturn(exceededLimits);

        controller.showUsersThatExceededMoneyLimit();

        assertEquals(3, userTable.getItems().size());
        User firstUser = userTable.getItems().get(0);
        assertTrue(firstUser.getHasExceededMoneyLimit());
    }

}
