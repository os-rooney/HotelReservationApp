package service;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReservationService {
  private static final ReservationService REFERENCE = new ReservationService();
  private final Map<String, IRoom> rooms;
  private final Map<String, Collection<Reservation>> reservations;

  private ReservationService(){
    rooms = new HashMap<>();
    reservations = new HashMap<>();
  }

  public static ReservationService getInstance(){
    return REFERENCE;
  }

  public void addRoom(IRoom room){

  }

  public IRoom getARoom(String roomId){
    return null;
  }

  public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
    return null;
  }

  public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
    return null;
  }

  public Collection<Reservation> getCustomersReservation(Customer customer){
    return null;
  }

  public void printAllReservation(){

  }
}
