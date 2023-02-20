package utilities;

import api.HotelResource;
import model.reservation.Reservation;
import model.room.IRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenuFunctions {
  private static final HotelResource hotelResource = HotelResource.getInstance();
  private static final String DATE_FORMAT = "MM/dd/yyyy";

  public static void listMenuChoices() {
    System.out.print("\nWelcome to the Hotel Reservation Application\n" +
        "--------------------------------------------\n" +
        "1. Find and reserve a room\n" +
        "2. See my reservations\n" +
        "3. Create an Account\n" +
        "4. Admin\n" +
        "5. Exit\n" +
        "--------------------------------------------\n" +
        "Please select a number for the menu option:\n");
  }

  public static void findAndReserveRoom() {
    final Scanner scanner = new Scanner(System.in);

    System.out.println("Enter Check-In Date (mm/dd/yyyy):");
    Date checkIn = promptAndReadDateInput(scanner);
    System.out.println("Enter Check-Out Date (mm/dd/yyyy):");
    Date checkOut = promptAndReadDateInput(scanner);

    if (checkIn == null || checkOut == null) {
      System.out.println("Invalid date format. Please try again.\n");
      return;
    }

    Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);
    if (availableRooms.isEmpty()) {
      Collection<IRoom> alternativeRooms = hotelResource.findRoomsForNextAvailableDates(checkIn, checkOut);
      if (alternativeRooms.isEmpty()) {
        System.out.println("Sorry, there are no rooms available for the selected dates.\n");
      } else {
        System.out.println("Rooms are not available on the selected dates. Here are some alternatives:\n");
        displayRooms(alternativeRooms);
        handleRoomReservation(scanner, hotelResource.addDefaultPlusDays(checkIn), hotelResource.addDefaultPlusDays(checkOut), alternativeRooms);
      }
    } else {
      System.out.println("Rooms available for the selected dates:\n");
      displayRooms(availableRooms);
      handleRoomReservation(scanner, checkIn, checkOut, availableRooms);
    }
  }

  public static Date promptAndReadDateInput(final Scanner scanner) {
    Date date = null;
    boolean validInput = false;
    while (!validInput) {
      try {
        date = new SimpleDateFormat(DATE_FORMAT).parse(scanner.nextLine());
        validInput = true;
      } catch (ParseException ex) {
        System.out.println("Error: Invalid date.");
        System.out.println("Please enter a date in the format " + DATE_FORMAT);
      }
    }
    return date;
  }

  public static void displayRooms(final Collection<IRoom> rooms) {
    if (rooms.isEmpty()) {
      System.out.println("No rooms found.");
    } else {
      for (IRoom room : rooms) {
        System.out.println(room);
      }
    }
  }

  public static void handleRoomReservation(final Scanner scanner, final Date checkInDate,
                                           final Date checkOutDate, final Collection<IRoom> rooms) {
    System.out.println("Would you like to book? y/n");
    final String bookRoom = scanner.nextLine();

    if ("y".equalsIgnoreCase(bookRoom)) {
      System.out.println("Do you have an account with us? y/n");
      final String haveAccount = scanner.nextLine();

      if ("y".equalsIgnoreCase(haveAccount)) {
        System.out.println("Enter Email format: name@domain.com");
        final String customerEmail = scanner.nextLine();

        if (hotelResource.getCustomer(customerEmail) == null) {
          System.out.println("Customer not found.\nYou may need to create a new account.");
        } else {
          System.out.println("What room number would you like to reserve?");
          final String roomNumber = scanner.nextLine();

          if (rooms.stream().anyMatch(room -> room.getRoomNumber().equalsIgnoreCase(roomNumber))) {
            final IRoom room = hotelResource.getRoom(roomNumber);

            final Reservation reservation = hotelResource
                .bookARoom(customerEmail, room, checkInDate, checkOutDate);
            System.out.println("Reservation created successfully!");
            System.out.println(reservation);
          } else {
            System.out.println("Error: room number not available.\nStart reservation again.");
          }
        }

        listMenuChoices();
      } else {
        System.out.println("Please, create an account.");
        listMenuChoices();
      }
    } else if ("n".equalsIgnoreCase(bookRoom)){
      listMenuChoices();
    } else {
      handleRoomReservation(scanner, checkInDate, checkOutDate, rooms);
    }
  }

  public static void viewMyReservations() {
    final Scanner scanner = new Scanner(System.in);

    System.out.println("Enter your email address: ");
    final String customerEmail = scanner.nextLine();

    Collection<Reservation> reservations = hotelResource.getCustomersReservations(customerEmail);

    if (reservations.isEmpty()) {
      System.out.println("No reservations found.");
    } else {
      System.out.println("Your reservations:\n");
      for (Reservation reservation : reservations) {
        System.out.println(reservation);
      }
    }
  }

  public static void createCustomerAccount() {
    final Scanner scanner = new Scanner(System.in);

    System.out.println("Enter Email format: name@domain.com");
    final String email = scanner.nextLine();

    System.out.println("First Name:");
    final String firstName = scanner.nextLine();

    System.out.println("Last Name:");
    final String lastName = scanner.nextLine();

    try {
      hotelResource.createACustomer(email, firstName, lastName);
      System.out.println("Account created successfully!");

      listMenuChoices();
    } catch (IllegalArgumentException ex) {
      System.out.println(ex.getMessage());
      createCustomerAccount();
    }
  }


}
