package AddNewEntry;

import MainMenu.ReadData;
import Entities.Course;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import Dao.CourseDao;
import Dao.TrainerDao;

public class NewCourse {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Scanner SC = new Scanner(System.in);
    private static final CourseDao CD = new CourseDao();
    private static final TrainerDao TD = new TrainerDao();

    public static void course() {
        System.out.println("Enter course's title: ");
        String title = SC.nextLine();
        System.out.println("Enter course's stream: ");
        String stream = SC.nextLine();
        System.out.println("Enter course's type: ");
        String type = SC.nextLine();
        System.out.println("Enter course's start date: (dd/mm/yyyy)");
        boolean inputStartDate = true;
        String startDate = "";
        while (inputStartDate) {
            startDate = SC.nextLine();
            if (Check.checkDate(startDate) == true) {
                inputStartDate = false;
            } else {
                System.out.println("Invalid date format");
            }
        }
        System.out.println("Enter course's end date: (dd/mm/yyyy)");
        boolean inputEndDate = true;
        String endDate = "";
        boolean inputEndDate1 = true;
        while (inputEndDate) {
            while (inputEndDate1) {
                endDate = SC.nextLine();
                if (Check.checkDate(endDate) == true) {
                    inputEndDate1 = false;
                } else {
                    System.out.println("Invalid date format");
                }
            }
            if (LocalDate.parse(endDate, FORMAT).isBefore(LocalDate.parse(startDate, FORMAT))) {
                System.out.println("End Date can't be older than start date");
                inputEndDate1 = true;
            } else {
                inputEndDate = false;
            }
        }
        System.out.println("New Course: " + title + " " + stream + " " + type + ", start date: " + startDate + ", end date: " + endDate);
        Course course = null;
        boolean inputConfirm = true;
        boolean confirmed = false;
        while (inputConfirm) {
            System.out.println("Type ok to confirm or cancel to discard");
            String confirm = SC.nextLine();
            if (confirm.equalsIgnoreCase("ok")) {
                course = new Course(title, stream, type, LocalDate.parse(startDate, FORMAT), LocalDate.parse(endDate, FORMAT));
                CD.create(course);
                confirmed = true;
                inputConfirm = false;
            } else if (confirm.equalsIgnoreCase("cancel")) {
                inputConfirm = false;
            } else {
                System.out.println("Invalid input");
            }
        }
        while (confirmed) {
            confirmed = false;
            if (TD.findAll().isEmpty()) {
                System.out.println("No trainers found. Would you like to add a new one now? Type y for yes or n for no");
                System.out.println("If you choose no, the course will not have a trainer until you create one.");
                boolean addTrainerNow = true;
                while (addTrainerNow) {
                    String choice = SC.nextLine();
                    if (choice.equalsIgnoreCase("y")) {
                        NewTrainer.trainer();
                        addTrainerNow = false;
                    } else if (choice.equalsIgnoreCase("n")) {
                        addTrainerNow = false;
                    } else {
                        System.out.println("Invalid option");
                    }
                }
            } else {
                System.out.println("Type the number of the trainer that teaches this course or new to create a new one");
                ReadData.printArithmeticList(TD.findAll());
                boolean choose = true;
                String choice = "";
                while (choose) {
                    choice = SC.nextLine();
                    if ((Check.checkIfInt(choice) == true && Integer.parseInt(choice) <= TD.findAll().size() && Integer.parseInt(choice) > 0)) {
                        TD.addCourseToTrainer(course, TD.findAll().get(Integer.parseInt(choice) - 1));
                        choose = false;
                    } else if (choice.equalsIgnoreCase("new")) {
                        NewTrainer.trainer();
                        choose = false;
                    } else {
                        System.out.println("Invalid number");
                    }
                }
            }
        }
    }
}
