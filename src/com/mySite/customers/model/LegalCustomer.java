package com.mySite.customers.model;

import java.util.List;

public class LegalCustomer extends Customer {
    private String identifierCode;
    private List<Contacts> contacts;

    public LegalCustomer(String customerName,String address, String postalCode, String identifierCode, List<Contacts> contacts) {
        super(customerName,address, postalCode, true);
        this.identifierCode = identifierCode;
        this.contacts = contacts;
    }

    public String getIdentifierCode() {
        return identifierCode;
    }

    public void setIdentifierCode(String identifierCode) {
        this.identifierCode = identifierCode;
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
                ", identifierCode='" + identifierCode + '\'' +
                ",\n contacts=" + contacts ;
    }
}
