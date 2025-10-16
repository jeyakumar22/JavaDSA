package BankProject.BankManagement.Account;

import BankProject.BankManagement.EnumConstants.AccountType;

public class Current extends Account {
    private double overDraftLimit;
    private double remainingLimit;

    public Current(int id, int accntNo, String accountHolderName, double Balance, double overDraftLimit, AccountType at) {
        super(id, accntNo, accountHolderName, Balance, at);
        this.overDraftLimit = overDraftLimit;
        this.remainingLimit = overDraftLimit;
    }


    @Override
    public void withdraw(int amount) {
        if (getBalance() + overDraftLimit >= amount) {
            setBalance((getBalance()) - amount);
            if (getBalance() < 0) {
                double overdraftUsed = -getBalance();
                setRemainingLimit(overDraftLimit - overdraftUsed);
            }
            System.out.printf("Withdraw of %d Rs Successful.. Balance %f", amount, getBalance());
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

    void setRemainingLimit(double remain) {
        this.remainingLimit = remain;
    }

    public double getOverdraftRemainingLimit() {
        return remainingLimit;
    }
}
