package com.app.model;

public class Passenger {
    private int id;
    private String name;
    private String gender;
    private String contactNumber;
    private String address;
    private String email;

    public Passenger(String address, String contactNumber, String gender, String email, String name) {
        this.address = address;
        this.contactNumber = contactNumber;
        this.gender = gender;
        this.email = email;
        this.name = name;
    }

    public Passenger(int id, String name, String gender, String contactNumber, String address, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
        this.email = email;
    }

    public Passenger() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
