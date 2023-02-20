package model.customer;

import java.util.regex.Pattern;

public class Customer {
  private String firstName;
  private String lastName;
  private String email;

  /**
   * ^ and $ are anchors that match the start and end of the string, respectively.
   * [\w-\.]+ matches one or more word characters, hyphens, or dots, which can appear before the @ symbol.
   * @ matches the literal @ symbol.
   * ([\w-]+\.)+ matches one or more groups of one or more word characters or hyphens, followed by a dot. This is used to match the domain name, which can consist of multiple parts separated by dots.
   * [\w-]{2,4} matches the top-level domain, which can consist of two to four word characters or hyphens.
   */
  private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
  private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

  public Customer(String firstName, String lastName, String email){
    if(EMAIL_PATTERN.matcher(email).matches()){
      throw new IllegalArgumentException("Invalid email format: " + email);
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString(){
    return "Customer: \n" +
        "Firstname: " + firstName + "\n" +
        "Lastname: " + lastName + "\n" +
        "email: " + email + "\n";
  }
}
