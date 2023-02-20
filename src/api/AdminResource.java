package api;

import model.customer.Customer;
import model.room.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
  private static AdminResource REFERENCE;
  private CustomerService customerService;
  private ReservationService reservationService;

  private AdminResource(){
    customerService = CustomerService.getInstance();
    reservationService = ReservationService.getInstance();
  }

  public static AdminResource getInstance(){
    if(REFERENCE == null)
      REFERENCE = new AdminResource();
    return REFERENCE;
  }

  public Customer getCustomer(String email){
    return customerService.getCustomer(email);
  }

  public void addRoom(List<IRoom> rooms){
    rooms.forEach(reservationService::addRoom);
  }

  public Collection<IRoom> getAllRooms(){
    return reservationService.getAllRooms();
  }

  public Collection<Customer> getAllCustomers(){
    return customerService.getAllCustomers();
  }

  public void displayAllReservations(){
    reservationService.printAllReservation();
  }
}
