package AddNewEntry;

import Dao.CourseDao;
import Dao.TrainerDao;
import MainMenu.ReadData;
import Entities.Trainer;
import java.util.Scanner;

public class NewTrainer {

    private static final Scanner SC = new Scanner(System.in);
    private static final TrainerDao TD = new TrainerDao();
    private static final CourseDao CD = new CourseDao();

    public static void trainer() {
        if (CD.findAll().isEmpty()) {
            System.out.println("No courses found. Would you like to add a new one now? Type y for yes or n for no");
            System.out.println("If you choose no, you will not be able to add a trainer.");
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
            System.out.println("Enter trainer's first name: ");
            String firstName = SC.nextLine();
            System.out.println("Enter trainer's last name: ");
            String lastName = SC.nextLine();
            System.out.println("Enter trainer's subject: ");
            String subject = SC.nextLine();
            System.out.println("New Trainer: " + firstName + " " + lastName + ", Subject: " + subject);
            Trainer trainer = null;
            boolean inputConfirm = true;
            boolean confirmed = false;
            while (inputConfirm) {
                System.out.println("Type ok to confirm or cancel to discard");
                String confirm = SC.nextLine();
                if (confirm.equalsIgnoreCase("ok")) {
                    trainer = new Trainer(firstName, lastName, subject);
                    TD.create(trainer);
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
                boolean createNewCourseForTrainer = true;
                boolean addCourseToTrainer = true;
                while (addCourseToTrainer) {
                    while (createNewCourseForTrainer) {
                        System.out.println("Type the course's number that the trainer teaches or new to create a new course: ");
                        ReadData.printArithmeticList(CD.findAll());
                        boolean choose = true;
                        String choiceCourse = "";
                        while (choose) {
                            choiceCourse = SC.nextLine();
                            if (Check.checkIfInt(choiceCourse) == true && Integer.parseInt(choiceCourse) <= CD.findAll().size() && Integer.parseInt(choiceCourse) > 0) {
                                TD.addCourseToTrainer(CD.findAll().get(Integer.parseInt(choiceCourse) - 1), TD.findByMaxId());
                                choose = false;
                                createNewCourseForTrainer = false;
                            } else if (choiceCourse.equalsIgnoreCase("new")) {
                                NewCourse.course();
                                if (!TD.getCourses(trainer).isEmpty()) {
                                    createNewCourseForTrainer = false;
                                }
                                choose = false;
                            } else {
                                System.out.println("Invalid number");
                            }
                        }
                    }

                    boolean addAnotherCourseToTrainer = true;
                    while (addAnotherCourseToTrainer) {
                        System.out.println("Does the trainer "+trainer+" teach in another course too? Type y for yes, n for no");
                        String choice = SC.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            addAnotherCourseToTrainer = false;
                            createNewCourseForTrainer=true;
                        } else if (choice.equalsIgnoreCase("n")) {
                            addAnotherCourseToTrainer = false;
                            addCourseToTrainer = false;
                        } else {
                            System.out.println("Invalid input");
                        }
                    }
                }
            }
        }
    }
}
