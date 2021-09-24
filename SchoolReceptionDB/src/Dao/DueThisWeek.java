package Dao;

import Entities.Assignment;
import Entities.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

public class DueThisWeek {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final StudentDao SD = new StudentDao();
    private static final PerStudentDao PSD = new PerStudentDao();

//      returns a list of students that have to submit assignments within the week of date input
    public static ArrayList<Student> studentsWithAssignmentsDueThisWeek(String date){
    ArrayList<Student> students = new ArrayList();
    LocalDate getDate = LocalDate.parse(date, FORMAT);
    WeekFields weekFields = WeekFields.of(Locale.getDefault());
    int weekNum = getDate.minusDays(2).get(weekFields.ISO.weekOfWeekBasedYear());
    int assignmentWeekNum;
        for (Student student : SD.findAll()) {
            for (Assignment assignment : PSD.assignmentsPerStudentPerCourse(student)) {
                assignmentWeekNum= assignment.getSubDateTime().get(weekFields.ISO.weekOfWeekBasedYear());
                if(assignmentWeekNum==weekNum && getDate.getYear()==assignment.getSubDateTime().getYear()){
                    students.add(student);}
            }
        }
        return students;
    }
}
