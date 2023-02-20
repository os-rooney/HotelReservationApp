import utilities.MainMenuFunctions;

import java.util.Scanner;

public class MainMenu {

  public static void startMainMenu(){
    MainMenuFunctions.listMenuChoices();

    Scanner scanner = new Scanner(System.in);
    boolean exitFlag = false;

    while (!exitFlag) {
      String line = scanner.nextLine().trim();

      try {
        switch (line) {
          case "1" -> MainMenuFunctions.findAndReserveRoom();
          case "2" -> MainMenuFunctions.viewMyReservations();
          case "3" -> MainMenuFunctions.createCustomerAccount();
          case "4" -> AdminMenu.startAdminMenu();
          case "5" -> {
            System.out.println("Exiting program...");
            exitFlag = true;
          }
          default -> System.out.println("Unknown action\n");
        }
      } catch (Exception e) {
        System.out.println("An error occurred while performing the selected action");
      }
    }
  }
}
