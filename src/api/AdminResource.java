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
    return null;
  }

  public void addRoom(List<IRoom> rooms){

  }

  public Collection<IRoom> getAllRooms(){
    return null;
  }

  public Collection<Customer> getAllCustomers(){
    return null;
  }

  public void displayAllReservations(){

  }
}
