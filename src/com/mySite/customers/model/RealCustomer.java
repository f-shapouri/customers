package com.mySite.customers.model;

import java.util.List;

public class RealCustomer extends Customer {
    private String lastName;
    private String nationalCode;
    private String fatherName;
    private List<Contacts> contacts;

    public RealCustomer(String customerName,String address, String postalCode, String lastName, String nationalCode, String fatherName, List<Contacts> contacts) {
        super(customerName,address, postalCode, false);
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.fatherName = fatherName;
        this.contacts = contacts;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return super.toString() + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ",\n contacts=" + contacts;
    }
}

