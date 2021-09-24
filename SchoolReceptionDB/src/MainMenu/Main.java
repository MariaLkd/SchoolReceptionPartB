package MainMenu;

import Dao.SyntheticDataDao;
import java.util.Scanner;
import AddNewEntry.NewCourse;
import AddNewEntry.NewAssignment;
import AddNewEntry.NewStudent;
import AddNewEntry.NewTrainer;
import Dao.GenericDao;
import static MainMenu.BackToMenu.backToMenu;
import java.io.FileNotFoundException;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    private static final GenericDao GD = new GenericDao();

    public static void main(String[] args) throws FileNotFoundException {
        GD.createDB();
        boolean runProgram = true;
        while (runProgram) {
            System.out.println("Type the number of the action you want: ");
            System.out.println("1. Add new course");
            System.out.println("2. Add new trainer");
            System.out.println("3. Add new student");
            System.out.println("4. Add new assignment");
            System.out.println("5. Read Data");
            System.out.println("6. Add synthetic data");
            System.out.println("7. Exit");
            String choice = SC.nextLine();
            switch (choice) {
                case "1":
                    NewCourse.course();
                    backToMenu();
                    break;
                case "2":
                    NewTrainer.trainer();
                    backToMenu();
                    break;
                case "3":
                    NewStudent.student();
                    backToMenu();
                    break;
                case "4":
                    NewAssignment.assignment();
                    backToMenu();
                    break;
                case "5":
                    ReadData.menu();
                    backToMenu();
                    break;
                case "6":
                    SyntheticDataDao.add();
                    backToMenu();
                    break;
                case "7":
                    runProgram = false;
                    break;
            }
        }
    }

}
