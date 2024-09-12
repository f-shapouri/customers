package com.mySite.customers.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private boolean isCompany;

    public Customer() {
    }

    public Customer(String customerName,String address, String postalCode, boolean isCompany) {
        this.customerId = ID_GENERATOR.getAndIncrement();
        this.customerName=customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.isCompany = isCompany;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    @Override
    public String toString() {
        return "customerId=" + customerId + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", isCompany=" + isCompany ;
    }
}
