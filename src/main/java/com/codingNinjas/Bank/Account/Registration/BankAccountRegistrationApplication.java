package com.codingNinjas.Bank.Account.Registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class BankAccountRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountRegistrationApplication.class, args);

        // Step 1: Fetch context from ApplicationContext.xml and initiate scanner
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Account Registration Application!");

        // Step 2: Get user details from console
        System.out.println("Please enter Your name?");
        String name = scanner.nextLine();

        myUser user = (myUser) context.getBean("myUser");
        user.setUserDetails(name);

        // Step 3: Get account details from user and add them to the account list
        boolean addMoreAccounts = true;

        while (addMoreAccounts) {
            System.out.println("Do you want to add account\n1. Yes\n2. No");
            int addAccountChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (addAccountChoice != 1) {
                break;
            }

            System.out.println("Please select the account type\n1. Current\n2. Savings");
            int accountTypeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.println("Enter the opening balance");
            double openingBalance = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            Account account;
            if (accountTypeChoice == 1) {
                account = (currentAccount) context.getBean("currentAccount", openingBalance);
            } else if (accountTypeChoice == 2) {
                account = (savingsAccount) context.getBean("savingsAccount", openingBalance);
            } else {
                System.out.println("Invalid account type. Please try again.");
                continue;
            }

            user.addAccount(account);

            System.out.println("Do you want to add more accounts\n1. Yes\n2. No");
            int response = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            addMoreAccounts = (response == 1);
        }

        // Step 4: Display the list of accounts with their reference ids
        System.out.println("Hi " + user.getName() + ", here is the list of your accounts:");
        for (Account account : user.getAllAccounts()) {
            System.out.println(account.getAccountType() + " : opening balance - " + account.getBalance() + " Reference Id " + account.toString());
        }

        context.close();
        scanner.close();
        System.exit(0);
    }
}