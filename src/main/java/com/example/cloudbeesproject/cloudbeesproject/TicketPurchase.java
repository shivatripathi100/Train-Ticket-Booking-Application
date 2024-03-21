package com.example.cloudbeesproject.cloudbeesproject;

public class TicketPurchase {
  private String from;
  private String to;
  private String firstName;
  private String lastName;
  private String email;
  private double price;
  private String seat;
  private String section;

  public TicketPurchase() {
  }

  public TicketPurchase(String from, String to, String firstName, String lastName, String email, double price,
      String seat, String section) {
    this.from = from;
    this.to = to;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.price = price;
    this.seat = seat;
    this.section = section;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
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

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getSeat() {
    return seat;
  }

  public void setSeat(String seat) {
    this.seat = seat;
  }

  public String getSection() {
    return section;
  }

  public void setSection(String section) {
    this.section = section;
  }
}
