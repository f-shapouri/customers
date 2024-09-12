package com.mySite.customers.view;

import com.mySite.customers.model.*;
import com.mySite.customers.service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUi implements AutoCloseable {
    private Scanner scanner = new Scanner(System.in);
    private CustomerService customerService = new CustomerService();

    public void startMenu() {
        int userInput;
        do {
            printMenu();
            userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 0 -> System.out.println("-------End the application------");
                case 1 -> addCustomer();
                case 2 -> printAllCustomers();
                case 3 -> searchCustomer();
                case 4 -> editCustomer();
                case 5 -> deleteCustomer();
                case 6 -> deleteAllCustomers();
            }
        } while (userInput != 0);
    }

    private String getInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    private void printMenu() {
        System.out.println("------------Menu------------");
        System.out.println("0.Exit");
        System.out.println("1.Add new customer");
        System.out.println("2.Print all customers");
        System.out.println("3.Search customer");
        System.out.println("4.Edit customer");
        System.out.println("5.Delete customer");
        System.out.println("6.Delete All customers");
    }

    private void addCustomer() {
        System.out.println("Select customer type: 1: Real customer 2: Legal customer");
        String input = scanner.nextLine();
        List<Contacts> contactList = new ArrayList<>();
        String customerName = getInput("Enter name:");
        String address = getInput("Enter address: ");
        String postalCode = getInput("Enter postalCode: ");

        if (input.equals("1")) {
            String lastName = getInput("Enter family:");
            String nationalCode = getInput("Enter nationalCode:");
            String fatherName = getInput("Enter father name:");
            int contactCount = Integer.parseInt(getInput("How many contacts do you want to add?"));
            addContact(contactCount, contactList);
            Customer customer = new RealCustomer(customerName, address, postalCode, lastName, nationalCode, fatherName, contactList);
            customerService.addCustomer(customer);
        } else if (input.equals("2")) {
            String identifierCode = getInput("Enter identifierCode:");
            int contactCount = Integer.parseInt(getInput("How many contacts do you want to add?"));
            addContact(contactCount, contactList);
            Customer customer = new LegalCustomer(customerName, address, postalCode, identifierCode, contactList);
            customerService.addCustomer(customer);
        } else {
            System.out.println("Invalid customer type selected");
        }
    }

    private void addContact(int contactCount, List<Contacts> contactList) {
        for (int i = 1; i <= contactCount; i++) {
            Contacts contacts = new Contacts();
            String contactType = getInput("Select contact type: 1: Mobile 2: Home 3: Work 4: Fax 5: Email 6: Other");
            switch (contactType) {
                case "1" -> contacts.setContactType(ContactType.MOBILE);
                case "2" -> contacts.setContactType(ContactType.HOME);
                case "3" -> contacts.setContactType(ContactType.WORK);
                case "4" -> contacts.setContactType(ContactType.FAX);
                case "5" -> contacts.setContactType(ContactType.EMAIL);
                case "6" -> contacts.setContactType(ContactType.OTHER);
                default -> {
                    System.out.println("Invalid contact type selected");
                    i--;
                    continue;
                }
            }
            String number = getInput("Enter number:");
            contacts.setNumber(number);
            contactList.add(contacts);
        }
    }

    private void printAllCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        if (allCustomers.isEmpty()) {
            System.out.println("There aren't any customer in list");
        } else {
            System.out.println("All contacts");
            allCustomers.forEach(System.out::println);
        }
    }

    private void searchCustomer() {
        String name = getInput("Enter name:");
        List<Customer> customers = customerService.searchCustomer(name);
        customers.forEach(System.out::println);
    }

    private void editCustomer() {
        String name = getInput("Enter name:");
        List<Customer> customers = customerService.searchCustomer(name);

        if (customers.isEmpty()) {
            System.out.println("No customers found with the name: " + name);
            return;
        }

        Optional<LegalCustomer> legalCustomerOptional = customers.stream()
                .filter(customer -> customer.isCompany())
                .map(customer -> (LegalCustomer) customer)
                .findFirst();

        Optional<RealCustomer> RealCustomerOptional = customers.stream()
                .filter(customer -> !customer.isCompany())
                .map(customer -> (RealCustomer) customer)
                .findFirst();

        if (legalCustomerOptional.isPresent()) {
            LegalCustomer legalCustomer = legalCustomerOptional.get();
            String companyName = getInput("Enter new companyName:");
            String newIdentifierCode = getInput("Enter new nationalIdentifier code:");
            String newAddress = getInput("Enter new address:");
            String newPostalCode = getInput("Enter new postal code:");

            legalCustomer.setCustomerName(companyName);
            legalCustomer.setIdentifierCode(newIdentifierCode);
            legalCustomer.setAddress(newAddress);
            legalCustomer.setPostalCode(newPostalCode);

            System.out.println("Leagal Customer updated successfully.");

        } else if (RealCustomerOptional.isPresent()) {
            RealCustomer realCustomer = RealCustomerOptional.get();
            String firstName = getInput("Enter new firstName:");
            String lastName = getInput("Enter new lastName:");
            String nationalCode = getInput("Enter new nationalCode:");
            String fatherName = getInput("Enter new fatherName:");
            String newAddress = getInput("Enter new address:");
            String newPostalCode = getInput("Enter new postal code:");

            realCustomer.setCustomerName(firstName);
            realCustomer.setLastName(lastName);
            realCustomer.setNationalCode(nationalCode);
            realCustomer.setFatherName(fatherName);
            realCustomer.setAddress(newAddress);
            realCustomer.setPostalCode(newPostalCode);

            System.out.println("Real Customer updated successfully.");

        } else {
            System.out.println("Customer not found.");
        }

    }

    private void deleteCustomer() {
        String name = getInput("Enter customerName for delete:");
        List<Customer> customers = customerService.searchCustomer(name);

        if (customers.isEmpty()) {
            System.out.println("No customers found with the name: " + name);
        } else {
            customerService.deleteCustomer(name);
            System.out.println("Customer " + name + " deleted successfully");
        }
    }

    private void deleteAllCustomers() {
        String confirmItem = getInput("Are you sure to delete all customers?\n 1: Yes 2: No");
        if (confirmItem.equals("1")) {
            customerService.deleteAllCustomers();
            System.out.println("Customers deleted successfully");
        } else if (confirmItem.equals("2")){
            System.out.println("Delete canceled");
        }
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }
}
