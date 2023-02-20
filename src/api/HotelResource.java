package api;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class HotelResource {
  private static HotelResource REFERENCE;
  private final CustomerService customerService;
  private final ReservationService reservationService;

  private HotelResource(){
    customerService = CustomerService.getInstance();
    reservationService = ReservationService.getInstance();
  }

  public static HotelResource getInstance(){
    if(REFERENCE == null)
      REFERENCE = new HotelResource();
    return REFERENCE;
  }

  public Customer getCustomer(String email){
    return customerService.getCustomer(email);
  }

  public void createACustomer(String email, String firstName, String lastName){
    customerService.addCustomer(email, firstName, lastName);
  }

  public IRoom getRoom(String roomNumber){
    return reservationService.getARoom(roomNumber);
  }

  public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
    return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
  }

  public Collection<Reservation> getCustomersReservations(String customerEmail){
    Customer customer = getCustomer(customerEmail);

    if (customer == null) {
      return Collections.emptyList();
    }

    return reservationService.getCustomersReservation(getCustomer(customerEmail));
  }


  public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
    return reservationService.findRooms(checkIn, checkOut);
  }

  public Date addDefaultPlusDays(final Date date) {
    return reservationService.addDaysToDate(date);
  }
  public Collection<IRoom> findRoomsForNextAvailableDates(Date DesiredCheckInDate, Date DesiredCheckOutDate) {
    return reservationService.findRoomsForNextAvailableDates(DesiredCheckInDate, DesiredCheckOutDate);
  }
}
