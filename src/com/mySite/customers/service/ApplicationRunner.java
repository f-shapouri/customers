package com.mySite.customers.service;

import com.mySite.customers.view.ConsoleUi;

public class ApplicationRunner {
    public static void main(String[] args) throws Exception {
        try (ConsoleUi consoleUi = new ConsoleUi()) {
            consoleUi.startMenu();
        }
    }
}



