package BankProject.BankManagement.Account;

import BankProject.BankManagement.EnumConstants.AccountType;

public abstract class Account {
    private final int  customerId;
    private final int accntNo;
    private String accountHolderName;
    private double balance;
    private AccountType accountType;

    public Account(int id, int accntNo, String accountHolderName, double balance, AccountType at) {
        this.customerId = id;
        this.accntNo = accntNo;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = at;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void getDetails() {

        System.out.println("Account Type : " + accountType);
        System.out.println("Account NO : " + accntNo);
        System.out.println("Account Name : " + accountHolderName);
        System.out.println("Account Balance : " + balance);
    }
    public String toString() {
        return "Customer ID : " + customerId +
                "\nAccount Type: " + accountType +
                "\nAccount No: " + accntNo +
                "\nAccount Holder: " + accountHolderName +
                "\nBalance: " + String.format("%.2f", balance) +"\n";
    }

    abstract void withdraw(int amount);
    abstract void deposit(int amount);

}

