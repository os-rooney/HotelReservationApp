package utilities;

import api.AdminResource;
import model.customer.Customer;
import model.enums.RoomType;
import model.room.IRoom;
import model.room.Room;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class AdminMenuFunctions {
  private static final AdminResource adminResource = AdminResource.getInstance();
  public static void listMenuChoices() {
    System.out.print("\nAdmin Menu\n" +
        "--------------------------------------------\n" +
        "1. See all Customers\n" +
        "2. See all Rooms\n" +
        "3. See all Reservations\n" +
        "4. Add a Room\n" +
        "5. Back to Main Menu\n" +
        "--------------------------------------------\n" +
        "Please select a number for the menu option:\n");
  }

  public static void printAllCustomers() {
    Collection<Customer> customers = adminResource.getAllCustomers();

    if (customers.isEmpty()) {
      System.out.println("No customers found.");
    } else {
      customers.forEach(System.out::println);
    }
  }

  public static void showAllRooms() {
    Collection<IRoom> rooms = adminResource.getAllRooms();

    if (rooms.isEmpty()) {
      System.out.println("No rooms found.");
    } else {
      rooms.forEach(System.out::println);
    }
  }

  public static void listAllReservations() {
    adminResource.displayAllReservations();
  }

  public static void addRoom() {
    final Scanner scanner = new Scanner(System.in);

    System.out.println("Enter room number:");
    final String roomNumber = scanner.nextLine();
    System.out.println("Enter price per night:");
    final double roomPrice = promptValidRoomPrice(scanner);
    System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
    final RoomType roomType = getRoomTypeFromUser(scanner);

    final Room room = new Room(roomNumber, roomPrice, roomType);

    adminResource.addRoom(Collections.singletonList(room));
    System.out.println("Room added successfully!");

    addAnotherRoom(scanner);
  }

  private static double promptValidRoomPrice(final Scanner scanner) {
    while (true) {
      String input = scanner.nextLine();
      try {
        return Double.parseDouble(input);
      } catch (NumberFormatException e) {
        System.out.println("Invalid price - Please enter a valid price.");
      }
    }
  }

  private static RoomType getRoomTypeFromUser(final Scanner scanner) {
    try {
      return RoomType.getType(scanner.nextLine());
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid room type! Please, choose 1 for single bed or 2 for double bed:");
      return getRoomTypeFromUser(scanner);
    }
  }

  public static void addAnotherRoom(Scanner scanner) {
    System.out.println("Would you like to add another room? Enter Y (Yes) or N (No)");

    String input = scanner.nextLine();

    while (true) {
      if (input.equalsIgnoreCase("Y")) {
        addRoom();
        return;
      } else if (input.equalsIgnoreCase("N")) {
        listMenuChoices();
        return;
      } else {
        System.out.println("Invalid input. Please enter Y (Yes) or N (No)");
        input = scanner.nextLine();
      }
    }
  }

}
