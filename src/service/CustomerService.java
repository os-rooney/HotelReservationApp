package service;

import model.customer.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

  private static final CustomerService REFERENCE = new CustomerService();
  private final Map<String, Customer> customers;
  private CustomerService(){
    customers = new HashMap<>();
  }

  public static CustomerService getInstance(){
    return REFERENCE;
  }
  public void addCustomer(String email, String firstName, String lastName){

  }

  public Customer getCustomer(String customerEmail){
    return null;
  }

  public Collection<Customer> getAllCustomers(){
    return null;
  }
}
