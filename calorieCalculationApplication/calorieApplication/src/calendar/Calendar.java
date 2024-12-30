package calendar;


import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Calendar {

    public static java.sql.Date calculateEndWeekDate(Date startingDate){
        LocalDate localStartDate = Instant.ofEpochMilli(startingDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate localEndDate = localStartDate.plusDays(7);
        return Date.valueOf(localEndDate);
    }

    public static java.sql.Date getCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        return Date.valueOf(currentDate);
    }
}
