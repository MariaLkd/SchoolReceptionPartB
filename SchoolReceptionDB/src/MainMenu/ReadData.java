package MainMenu;

import AddNewEntry.Check;
import Dao.AssignmentDao;
import Dao.CourseDao;
import Dao.DueThisWeek;
import Dao.PerCourseDao;
import Dao.PerStudentDao;
import Dao.StudentDao;
import Dao.TrainerDao;
import Entities.Assignment;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReadData {

    private static final Scanner SC = new Scanner(System.in);
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final StudentDao SD = new StudentDao();
    private static final TrainerDao TD = new TrainerDao();
    private static final CourseDao CD = new CourseDao();
    private static final AssignmentDao AD = new AssignmentDao();
    private static final PerCourseDao PCD = new PerCourseDao();
    private static final PerStudentDao PSD = new PerStudentDao();

    public static void menu() {
        boolean readDataMenu = true;
        System.out.println("1. Print all the students");
        System.out.println("2. Print all the trainers");
        System.out.println("3. Print all the assignments");
        System.out.println("4. Print all the courses");
        System.out.println("5. Print all the students in a specific course");
        System.out.println("6. Print all the trainers in a specific course");
        System.out.println("7. Print all the assignments in a specific course");
        System.out.println("8. Print all the assignments of a specific student");
        System.out.println("9. Print all the students that are attending more than one course");
        System.out.println("10. Enter a date and print all the students that need to submit at least one assignment that week");
        System.out.println("11. Exit");
        while (readDataMenu) {
            String choice = SC.nextLine();
            switch (choice) {
                case "1":
                    readDataMenu = false;

                    if (!SD.findAll().isEmpty()) {
                        System.out.println("Here is a list of all the students: ");
                        printArithmeticList(SD.findAll());
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                case "2":
                    readDataMenu = false;

                    if (!TD.findAll().isEmpty()) {
                        System.out.println("Here is a list of all the trainers: ");
                        printArithmeticList(TD.findAll());
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                case "3":
                    readDataMenu = false;

                    if (!AD.findAll().isEmpty()) {
                        System.out.println("Here is a list of all the assignments: ");
                        printArithmeticList(AD.findAll());
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                case "4":
                    readDataMenu = false;

                    if (!CD.findAll().isEmpty()) {
                        System.out.println("Here is a list of all the courses: ");
                        printArithmeticList(CD.findAll());
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                case "5":
                    readDataMenu = false;
                    if (!CD.findAll().isEmpty()) {
                        boolean choose = true;
                        System.out.println("Type course number: ");
                        printArithmeticList(CD.findAll());
                        while (choose) {
                            choice = SC.nextLine();
                            if (Check.checkIfInt(choice) == true && Integer.parseInt(choice) <= CD.findAll().size()) {
                                choose = false;
                            } else {
                                System.out.println("Invalid number");
                            }
                        }
                        System.out.println(CD.findAll().get(Integer.parseInt(choice) - 1) + " has the following students enrolled: ");
                        if (!PCD.studentsPerCourse(CD.findAll().get(Integer.parseInt(choice) - 1)).isEmpty()) {
                            printArithmeticList(PCD.studentsPerCourse(CD.findAll().get(Integer.parseInt(choice) - 1)));
                        } else {
                            System.out.println("No students found");
                        }
                    } else {
                        System.out.println("No courses found");
                    }
                    break;
                case "6":
                    readDataMenu = false;
                    if (!CD.findAll().isEmpty()) {
                        System.out.println("Type course number: ");
                        printArithmeticList(CD.findAll());
                        boolean choose = true;
                        while (choose) {
                            choice = SC.nextLine();
                            if (Check.checkIfInt(choice) == true && Integer.parseInt(choice) <= CD.findAll().size()) {
                                choose = false;
                            } else {
                                System.out.println("Invalid number");
                            }
                        }
                        System.out.println(CD.findAll().get(Integer.parseInt(choice) - 1) + " has the following trainers teaching: ");
                        if (!PCD.trainersPerCourse(CD.findAll().get(Integer.parseInt(choice) - 1)).isEmpty()) {
                            printArithmeticList(PCD.trainersPerCourse(CD.findAll().get(Integer.parseInt(choice) - 1)));
                        } else {
                            System.out.println("No trainers found");
                        }
                    } else {
                        System.out.println("No courses found");
                    }
                    break;
                case "7":
                    readDataMenu = false;
                    if (!CD.findAll().isEmpty()) {
                        System.out.println("Type course number: ");
                        printArithmeticList(CD.findAll());
                        boolean choose = true;
                        while (choose) {
                            choice = SC.nextLine();
                            if (Check.checkIfInt(choice) == true && Integer.parseInt(choice) <= CD.findAll().size()) {
                                choose = false;
                            } else {
                                System.out.println("Invalid number");
                            }
                        }
                        System.out.println(CD.findAll().get(Integer.parseInt(choice) - 1) + " has the following assignments: ");
                        if (!PCD.assignmentsPerCourse(CD.findAll().get(Integer.parseInt(choice) - 1)).isEmpty()) {
                            printArithmeticList(PCD.assignmentsPerCourse(CD.findAll().get(Integer.parseInt(choice) - 1)));
                        } else {
                            System.out.println("No assignments found");
                        }
                    } else {
                        System.out.println("No courses found");
                    }
                    break;
                case "8":
                    readDataMenu = false;
                    if (!SD.findAll().isEmpty()) {
                        System.out.println("Type student number: ");
                        printArithmeticList(SD.findAll());
                        boolean choose = true;
                        while (choose) {
                            choice = SC.nextLine();
                            if (Check.checkIfInt(choice) == true && Integer.parseInt(choice) <= SD.findAll().size()) {
                                choose = false;
                            } else {
                                System.out.println("Invalid number");
                            }
                        }
                        System.out.println(SD.findAll().get(Integer.parseInt(choice) - 1).getFirstName() + " " + SD.findAll().get(Integer.parseInt(choice) - 1).getLastName() + "has the following assignment(s) to complete: ");
                        if (!PSD.assignmentsPerStudentPerCourse(SD.findAll().get(Integer.parseInt(choice) - 1)).isEmpty()) {
                            int i = 1;
                            for (Assignment assignment : PSD.assignmentsPerStudentPerCourse(SD.findAll().get(Integer.parseInt(choice) - 1))) {
                                System.out.println(i + ". " + assignment + " ,Course: " + assignment.getCourse());
                                i++;
                            }
                        } else {
                            System.out.println("No assignments found");
                        }
                    } else {
                        System.out.println("No students found");
                    }
                    break;
                case "9":
                    readDataMenu = false;
                    if (!SD.findAll().isEmpty()) {
                        printArithmeticList(SD.moreThanOneCourses());
                    } else {
                        System.out.println("No students found");
                    }
                    break;
                case "10":
                    readDataMenu = false;
                    System.out.println("Enter a date: (dd/mm/yyyy)");
                    System.out.println("Monday counts as the first day of the week");
                    boolean inputDate = true;
                    String date = "";
                    while (inputDate) {
                        date = SC.nextLine();
                        try {
                            LocalDate.parse(date, FORMAT);
                            inputDate = false;
                        } catch (java.time.format.DateTimeParseException ex) {
                            System.out.println("Invalid date format");
                        }
                    }
                    if (!DueThisWeek.studentsWithAssignmentsDueThisWeek(date).isEmpty()) {
                        printArithmeticList(DueThisWeek.studentsWithAssignmentsDueThisWeek(date));
                    } else {
                        System.out.println("No assignments found");
                    }
                    break;

                case "11":
                    System.exit(0);

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void printArithmeticList(ArrayList arrayList) {
        int counter = 1;
        for (Object object : arrayList) {
            System.out.println(counter + ". " + object);
            counter++;
        }

    }
}
