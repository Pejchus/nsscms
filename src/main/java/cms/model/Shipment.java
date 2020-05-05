package cms.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("SHIPMENT")
public class Shipment {

  private String cargo;
  private String vehicle;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String status;
  private String destination;


  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }


  public String getVehicle() {
    return vehicle;
  }

  public void setVehicle(String assignedvehicle) {
    this.vehicle = assignedvehicle;
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
