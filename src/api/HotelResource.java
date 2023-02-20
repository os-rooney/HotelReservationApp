package api;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
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
    return null;
  }

  public void createACustomer(String email, String firstName, String lastName){

  }

  public IRoom getRoom(String roomNumber){
    return null;
  }

  public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
    return null;
  }

  public Collection<Reservation> getCustomersReservations(String customerEmail){
    return null;
  }

  public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
    return null;
  }
}
