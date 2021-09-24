package AddNewEntry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Check {
    
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean checkIfInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (java.lang.NumberFormatException ex) {
            return false;
        }
    }

    public static boolean checkDate(String date) {
        try {
            LocalDate.parse(date, FORMAT);
            return true;
        } catch (java.time.format.DateTimeParseException ex) {
            return false;
        }
    }
}
