package AddNewEntry;

import Dao.AssignmentDao;
import Dao.CourseDao;
import MainMenu.ReadData;
import Entities.Assignment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class NewAssignment {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Scanner SC = new Scanner(System.in);
    private static final AssignmentDao AD = new AssignmentDao();
    private static final CourseDao CD = new CourseDao();
    

    public static void assignment() {
        if (CD.findAll().isEmpty()) {
            System.out.println("No courses found. Would you like to add a new one now? Type y for yes or n for no");
            System.out.println("If you choose no, you will not be able to add an assignment.");
            boolean addCourseNow = true;
            while (addCourseNow) {
                String choice = SC.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    NewCourse.course();
                    addCourseNow = false;
                } else if (choice.equalsIgnoreCase("n")) {
                    addCourseNow = false;
                } else {
                    System.out.println("Invalid option");
                }
            }
        } else {
            System.out.println("Enter assignment's title: ");
            String title = SC.nextLine();
            System.out.println("Enter assignment's description: ");
            String desc = SC.nextLine();
            System.out.println("Enter assignment's submission date: (dd/mm/yyyy)");
            boolean inputDate = true;
            String subDate = "";
            while (inputDate) {
                subDate = SC.nextLine();
                if (Check.checkDate(subDate) == true) {
                    inputDate = false;
                } else {
                    System.out.println("Invalid date format");
                }

            }
            System.out.println("Type the course's number the assignment belongs: ");
            ReadData.printArithmeticList(CD.findAll());
            boolean choose = true;
            String choice = "";
            while (choose) {
                choice = SC.nextLine();
                if (Check.checkIfInt(choice) == true && Integer.parseInt(choice) <= CD.findAll().size() && Integer.parseInt(choice) > 0) {
                    choose = false;
                } else {
                    System.out.println("Invalid number");
                }
            }
            System.out.println("New Assignment: " + title + ", Description: " + desc + ", Submission Date: " + LocalDate.parse(subDate, FORMAT) + ", Course: " + (CD.findAll().get(Integer.parseInt(choice) - 1)));
            Assignment assignment = null;
            boolean inputConfirm = true;
            while (inputConfirm) {
                System.out.println("Type ok to confirm or cancel to discard");
                String confirm = SC.nextLine();
                if (confirm.equalsIgnoreCase("ok")) {
                    assignment = new Assignment(title, desc, LocalDate.parse(subDate, FORMAT));
                    AD.create(assignment, CD.findAll().get(Integer.parseInt(choice) - 1));
                    inputConfirm = false;
                } else if (confirm.equalsIgnoreCase("cancel")) {
                    inputConfirm = false;
                } else {
                    System.out.println("Invalid input");
                }
            }
        }
    }
}
