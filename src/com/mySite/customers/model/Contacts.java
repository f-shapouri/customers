package com.mySite.customers.model;

public class Contacts {
    private ContactType contactType;
    private String number;

    public Contacts(){}
    public Contacts(ContactType contactType, String number) {
        this.contactType = contactType;
        this.number = number;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return
                "contactType=" + contactType +
                ", number='" + number + '\'' ;
    }
}
