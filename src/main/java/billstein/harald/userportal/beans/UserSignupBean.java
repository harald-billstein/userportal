package billstein.harald.userportal.beans;


/**
 * Class used to store information about a new user signing up
 * 
 * @author Harald & Stefan
 * @since 2017-12-12
 */
public class UserSignupBean {

  private String userName;
  private String firstName;
  private String lastName;
  private String password;
  private String email;

  public UserSignupBean() {

  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
