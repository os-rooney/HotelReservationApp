package service;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
  private static ReservationService REFERENCE;
  private final Map<String, IRoom> rooms;
  private final Map<String, Collection<Reservation>> reservations;
  private static final int DEFAULT_DAYS = 7;


  private ReservationService() {
    rooms = new HashMap<>();
    reservations = new HashMap<>();
  }

  public static ReservationService getInstance() {
    if (REFERENCE == null)
      REFERENCE = new ReservationService();
    return REFERENCE;
  }

  public void addRoom(IRoom room) {
    rooms.put(room.getRoomNumber(), room);
  }

  public IRoom getARoom(String roomId) {
    return rooms.get(roomId);
  }

  public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
    Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
    Collection<Reservation> customerReservation = getCustomersReservation(customer);
    if (customerReservation == null) {
      customerReservation = new LinkedList<>();
    }
    customerReservation.add(reservation);
    reservations.put(customer.getEmail(), customerReservation);
    return reservation;
  }

  public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
    return findVacantRooms(checkInDate, checkOutDate);
  }

  public Collection<IRoom> findRoomsForNextAvailableDates(Date checkInDate, Date checkOutDate) {
    Date defaultCheckInDate = addDaysToDate(checkInDate, DEFAULT_DAYS);
    Date defaultCheckOutDate = addDaysToDate(checkOutDate, DEFAULT_DAYS);
    return findVacantRooms(defaultCheckInDate, defaultCheckOutDate);
  }

  private Collection<IRoom> findVacantRooms(Date checkInDate, Date checkOutDate) {
    Set<IRoom> reservedRooms = getAllReservations().stream()
        .filter(r -> reservationConflictsWithDates(r, checkInDate, checkOutDate))
        .map(Reservation::getRoom)
        .collect(Collectors.toSet());

    return rooms.values().stream()
        .filter(r -> !reservedRooms.contains(r))
        .toList();
  }

  private Date addDaysToDate(Date date, int days) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, days);
    return calendar.getTime();
  }

  private boolean reservationConflictsWithDates(Reservation reservation, Date checkInDate, Date checkOutDate) {
    return checkInDate.before(reservation.getCheckOutDate()) &&
        checkOutDate.after(reservation.getCheckInDate());
  }

  private Collection<Reservation> getAllReservations() {
    return reservations.values()
        .stream()
        .flatMap(Collection::stream)
        .toList();
  }

  public Collection<Reservation> getCustomersReservation(Customer customer) {
    return reservations.get(customer.getEmail());
  }

  public void printAllReservation() {
    Collection<Reservation> allReservations = getAllReservations();
    // Check if there are any reservations
    if (allReservations.isEmpty()) {
      System.out.println("No reservations found.");
    } else {
      // Print each reservation
      allReservations.forEach(reservation -> System.out.println(reservation + "\n"));
    }
  }
}
