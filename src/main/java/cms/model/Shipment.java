package cms.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("SHIPMENT")
public class Shipment {

  private String cargo;

  public String getDriver() {
    return driver;
  }

  public void setDriver(String driver) {
    this.driver = driver;
  }

  private String driver;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String status;
  private String destination;

  public String getShippingdate() {
    return shippingdate;
  }

  public void setShippingdate(String shippingdate) {
    this.shippingdate = shippingdate;
  }

  private String shippingdate;


  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

}
