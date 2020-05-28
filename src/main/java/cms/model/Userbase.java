package cms.model ;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 * This class contains the metods which are used by both types of users
 */
public abstract class Userbase{

  private String username;
  private String fullname;
  private String password;


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
