/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Logan
 */

public class Customers {

    private int customerID;
    private String fName;
    private String lName;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String emailAddress;

    public Customers() {
        this.customerID = 0;
        this.fName = "";
        this.lName = "";
        this.address = "";
        this.address2 = "";
        this.city = "";
        this.state = "";
        this.zipCode = "";
        this.emailAddress = "";
    }

    public Customers(int customerID, String fName, String lName, String address, String address2, String city, String state, String zipCode, String emailAddress) {
        this.customerID = customerID;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.emailAddress = emailAddress;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Customers{" + "customerID=" + customerID + ", firstName=" + fName + ", lastName=" + lName + ", address=" + address + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", emailAddress=" + emailAddress + '}';
    }

    
    
}

