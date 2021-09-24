package MainMenu;

import java.util.Scanner;

/**
 *
 * @author Maria Lykoudi
 */
public class BackToMenu {
    private static final Scanner SC = new Scanner(System.in);

    public static void backToMenu() {
        System.out.println("Type menu to go back to main menu or exit to exit program");
        boolean mainMenu = true;
        while (mainMenu) {
            String choice = SC.nextLine();
            if (choice.equalsIgnoreCase("menu")) {
                mainMenu = false;
            } else if (choice.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else {
                System.out.println("Invalid option");
            }
        }
    }
}
