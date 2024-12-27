package calendar;


import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class Calendar {

    public static java.sql.Date calculateEndWeekDate(Date startingDate){
        LocalDate localStartDate = startingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEndDate = localStartDate.plusDays(7);
        return (Date) Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    }

    // Kjo metodë merr datën aktuale dhe e kthen si java.sql.Date
    public static java.sql.Date getCurrentDate(){
        LocalDate currentDate = LocalDate.now();  // Merr datën aktuale
        return Date.valueOf(currentDate);  // Kthen datën e sotme si java.sql.Date
    }
}
