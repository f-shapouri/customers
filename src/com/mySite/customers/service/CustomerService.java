package com.mySite.customers.service;

import com.mySite.customers.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CustomerService {
    private List<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerList;
    }

    public List<Customer> searchCustomer(String name) {
        return customerList.stream()
                .filter(customer -> customer.getCustomerName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public void deleteCustomer(String name) {
    customerList.removeIf(customer -> customer.getCustomerName().equalsIgnoreCase(name));
    }

    public void deleteAllCustomers(){
        customerList.clear();
    }
}
