package service;

import model.customer.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

  private static CustomerService REFERENCE;
  private final Map<String, Customer> customers;
  private CustomerService(){
    customers = new HashMap<>();
  }

  public static CustomerService getInstance(){
    if(REFERENCE == null)
      REFERENCE = new CustomerService();
    return REFERENCE;
  }
  public void addCustomer(String email, String firstName, String lastName){
    customers.put(email, new Customer(firstName, lastName, email));
  }

  public Customer getCustomer(String customerEmail){
    return customers.get(customerEmail);
  }

  public Collection<Customer> getAllCustomers(){
    return customers.values();
  }
}
