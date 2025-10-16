package BankProject.BankManagement.Account;

import BankProject.BankManagement.EnumConstants.AccountType;

public class Saving extends Account {
    private double InterestRate;

    public Saving(int id, int accntNo, String accountHolderName, double Balance, double InterestRate, AccountType at) {
        super(id, accntNo, accountHolderName, Balance, at);
        this.InterestRate = InterestRate;
    }


    @Override
    public void withdraw(int amount) {
        if (getBalance() >= amount) {
            setBalance(getBalance() - amount);
            System.out.printf("Withdraw of %d Rs Successful .Balance %.2f", amount, getBalance());
        } else {
            System.out.println("Not Enough Money");
        }
    }

    @Override
    public void deposit(int amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount);
            System.out.printf("Deposit of %d Rs Successful .Balance %.2f", amount, getBalance());
        } else {
            System.out.println("Amount Not Valid Enter a Valid Money");

        }
    }

    public void applyYearlyInterest() {
        double yearlyRate = InterestRate / 100;
        double interest = getBalance() * yearlyRate;
        setBalance(getBalance() + interest);
        System.out.printf(" Yearly Interest Applied: ₹%.2f | New Balance: ₹%.2f\n", interest, getBalance());
    }
}
