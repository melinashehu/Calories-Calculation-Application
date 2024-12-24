package calendar;


import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class Calendar {
    public static java.sql.Date calculateEndWeekDate(Date startingDate){
        LocalDate localStartDate = startingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEndDate = localStartDate.plusDays(7);
        return Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    }
}
