package com.codingNinjas.Bank.Account.Registration;

/**
 * This class is an implementation of an "Account" Interface based on the selection
 * done in the console for account type. You need to complete this class
 * based on the following tasks.
 *
 * Tasks:
 * a. Create attribute amount(double)
 * b. Override the methods of Account Interface.
 * c. Build the logic for all the methods based on the description mentioned in the Account Interface.
 **/

public class currentAccount implements Account {
    private double amount;

    public void init() {
        System.out.println("CurrentAccount is instantiated and I am the init() method");
    }

    public void destroy() {
        System.out.println("CurrentAccount is destroyed and I am the destroy() method");
    }
    // Constructor
    public currentAccount(double initialBalance) {
        this.amount = initialBalance;
    }

    @Override
    public String getAccountType() {
        return "Current Account";
    }

    @Override
    public void addBalance(double balance) {
        this.amount += balance;
    }

    @Override
    public double getBalance() {
        return this.amount;
    }
}