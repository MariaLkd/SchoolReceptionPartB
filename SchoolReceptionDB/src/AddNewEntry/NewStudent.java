package AddNewEntry;

import Dao.CourseDao;
import Dao.StudentDao;
import MainMenu.ReadData;
import Entities.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class NewStudent {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Scanner SC = new Scanner(System.in);
    private static final StudentDao SD = new StudentDao();
    private static final CourseDao CD = new CourseDao();

    public static void student() {
        System.out.println("Enter student's first name:");
        String firstName = SC.nextLine();
        System.out.println("Enter student's last name:");
        String lastName = SC.nextLine();
        System.out.println("Enter student's date of birth: (dd/mm/yyyy)");
        boolean inputDob = true;
        String dob = "";
        while (inputDob) {
            dob = SC.nextLine();
            try {
                LocalDate.parse(dob, FORMAT);
                inputDob = false;
            } catch (java.time.format.DateTimeParseException ex) {
                System.out.println("Invalid date format");
            }
        }
        System.out.println("Enter student's tuition fees:");
        boolean enterFees = true;
        String tuitionFees = "";
        while (enterFees) {
            tuitionFees = SC.nextLine();
            if (Check.checkIfInt(tuitionFees) == true) {
                enterFees = false;
            } else {
                System.out.println("Please enter an integer");
            }
        }
        if (CD.findAll().isEmpty()) {
            System.out.println("No courses found. Would you like to add a new one now? Type y for yes or n for no");
            System.out.println("If you choose no, the student will not be saved.");
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

        }
        if (!CD.findAll().isEmpty()) {
            Boolean selectCourses = true;
            System.out.println("New Student: " + firstName + " " + lastName + ", Date of Birth: " + FORMAT.format(LocalDate.parse(dob, FORMAT)) + ", Tuition Fees: " + Integer.parseInt(tuitionFees));
            Student student = null;
            boolean inputConfirm = true;
            boolean confirmed = false;
            while (inputConfirm) {
                System.out.println("Type ok to confirm or cancel to discard");
                String confirm = SC.nextLine();
                if (confirm.equalsIgnoreCase("ok")) {
                    student = new Student(firstName, lastName, LocalDate.parse(dob, FORMAT), Integer.parseInt(tuitionFees));
                    SD.create(student);
                    confirmed = true;
                    inputConfirm = false;
                } else if (confirm.equalsIgnoreCase("cancel")) {
                    inputConfirm = false;
                } else {
                    System.out.println("Invalid input");
                }
            }
            if (confirmed==true) {
                while (selectCourses) {
                    System.out.println("Type the number of the course the student " + student.getFirstName()+" "+student.getLastName() + " is enrolled in or new to create a new course");
                    ReadData.printArithmeticList(CD.findAll());
                    boolean choose = true;
                    String choice = "";
                    while (choose) {
                        choice = SC.nextLine();
                        if (Check.checkIfInt(choice) == true && Integer.parseInt(choice) <= CD.findAll().size() && Integer.parseInt(choice) > 0) {
                            SD.addCourseToStudent(CD.findAll().get(Integer.parseInt(choice) - 1),SD.findByMaxId());
                            choose = false;
                        } else if (choice.equalsIgnoreCase("new")) {
                            NewCourse.course();
                            choose = false;
                        } else {
                            System.out.println("Invalid number");
                        }
                    }

                    System.out.println("Is the student enrolled in another course? Type y for yes, n for no");
                    String choice1 = SC.nextLine();
                    if (choice1.equalsIgnoreCase("y")) {
                        selectCourses = true;
                    } else if (choice1.equalsIgnoreCase("n")) {
                        selectCourses = false;
                    } else {
                        System.out.println("Invalid option");
                        selectCourses = false;
                    }
                }
            }
        }
    }
}
