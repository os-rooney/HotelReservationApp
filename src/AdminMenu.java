import utilities.AdminMenuFunctions;

import java.util.Scanner;

public class AdminMenu {
  public static void startAdminMenu() {
    Scanner scanner = new Scanner(System.in);
    AdminMenuFunctions.listMenuChoices();

    while (scanner.hasNextLine()) {
      String input = scanner.nextLine().trim();

      if (input.length() != 1) {
        System.out.println("Error: Invalid action\n");
        continue;
      }

      switch (input.charAt(0)) {
        case '1' -> AdminMenuFunctions.printAllCustomers();
        case '2' -> AdminMenuFunctions.showAllRooms();
        case '3' -> AdminMenuFunctions.listAllReservations();
        case '4' -> AdminMenuFunctions.addRoom();
        case '5' -> MainMenu.startMainMenu();
        default -> System.out.println("Unknown action\n");
      }

      if (input.equals("5")) {
        break;
      }
    }
  }
}