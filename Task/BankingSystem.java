package Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args)  {

        Scanner ip = new Scanner(System.in);

        System.out.println("----- Welcome to our bank -----");
        Customer c ;
        Account account = null;
        int accountNo = 0;
        String bankName ;
        String urName;
        double initialBalance;
        int accountType;
        float interestRate;
        int overDraftLimit;
        AccountType at ;

        List<Account> accountDataBase = new ArrayList<>();

        System.out.println("Create a bank Account  ");
        System.out.println("Enter Bank Name");
        bankName= ip.nextLine();
        System.out.println("Enter Your Name");
        urName= ip.nextLine();
        System.out.println("Enter a Minimum Balance");
        initialBalance= ip.nextInt();
        System.out.println("Enter Type of bank account 1.Saving Account 2.Current Account");
        accountType = ip.nextInt();
        if(accountType == 1) {
            System.out.println("Enter Anual Interest Rate : " );
            interestRate = ip.nextInt();
            accountNo++;
            at = AccountType.SAVINGS;
            account = new Saving(accountNo,urName,initialBalance,interestRate,at);

        }
        else if(accountType == 2) {
            System.out.println("Choose OverDraft Limit [2000,3000,3500]" );
            overDraftLimit = ip.nextInt();
            accountNo++;
            at = AccountType.CURRENT;
            account = new Current(accountNo,urName,initialBalance,overDraftLimit,at);
        }
        else{
            System.out.println("Wrong Type");
        }
        c = new Customer(bankName,account);
        System.out.println("Account Created Successfully!");

        Options[] Options = Task.Options.values();

        while(true){
                System.out.println("\n1.Withdraw 2.Deposit 3.CheckBalance 4.ApplyInterst OR CheckRemainingLimit 5.Exit");
            int choice = ip.nextInt();
            if(choice<=0 || choice>Options.length){
                System.out.println("Enter A Valid Options");
                continue;
            }
            ip.nextLine();
            Options selectedOption = Options[choice - 1];
            switch(selectedOption){
                case WITHDRAW:
                    System.out.println("Enter Withdraw Amount");
                    int withdrawAmount = ip.nextInt();
                    c.withdraw(account,withdrawAmount);

                    break;

                case DEPOSIT:
                    System.out.println("Enter Deposit Amount");
                    int depositAmount = ip.nextInt();
                    c.deposit(account,depositAmount);
                    break;
                case CHECK_BALANCE:
                    c.checkBalance();
                    break;

                case APPLYINTEREST_OR_REMININGLIMIT:
                    if(account instanceof Saving){
                        c.applyInterest();
                    }
                    else if (account != null){
                        c.getRemainingLimit();
                    }
                    break;
                case EXIT:
                    System.out.println("ThankYou");

                    c.viewDetails();
                    return;
                default:
                    System.out.println("Wrong Choice");
                    c.viewDetails();
                    return;
            }
        }
    }
}

class Customer{
    String bankName;
    Account account;

    public Customer(String bankName ,Account a) {
        this.bankName = bankName;
        this.account = a;
    }

    void withdraw (Account a,int amount) {
        account.withdraw(amount);
    }
    void deposit(Account a ,int amount){
        account.deposit(amount);
    }
    void checkBalance(){
        System.out.printf("Your Current Balance %.2f",account.getBalance());
    }
    void viewDetails(){
        System.out.println("Bank name : " + bankName);
        account.getDetails();
    }
    void applyInterest(){
        ((Saving)account).applyYearlyInterest();
    }
    void getRemainingLimit(){
        ((Current)account).getOverdraftRemainingLimit();
    }



}
abstract class Account{
    private int customerId;
    private final int accntNo;
    private String accountHolderName;
    private double balance;
    AccountType accountType;
    public Account(int accntNo , String accountHolderName,double balance,AccountType at){
        this.accntNo=accntNo;
        this.accountHolderName = accountHolderName;
        this.balance=balance;
        this.accountType=at;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    public void getDetails(){

        System.out.println("Account Type : " + accountType);
        System.out.println("Account NO : " + accntNo);
        System.out.println("Account Name : " + accountHolderName);
        System.out.println("Account Balance : " + balance);
    }

    abstract void withdraw(int amount) ;
    abstract void deposit(int amount);

}

class Saving extends Account{
    private double InterestRate;
    public Saving(int accntNo, String accountHolderName, double Balance, double InterestRate, AccountType at) {
        super(accntNo, accountHolderName, Balance,at);
        this.InterestRate=InterestRate;
    }


    @Override
    void withdraw(int amount)  {
        if(getBalance() >= amount){
            setBalance(getBalance()-amount);
            System.out.printf("Withdraw of %d Rs Successful .Balance %.2f",amount,getBalance());
        }
        else{
            System.out.println("Not Enough Money");
        }
    }

    @Override
    void deposit(int amount) {
        if(amount >0){
            setBalance(getBalance()+amount);
            System.out.printf("Deposit of %d Rs Successful .Balance %.2f",amount,getBalance());
        }
        else{
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
class Current extends Account{
    private double  overDraftLimit;
    private double remainingLimit ;
    public Current(int accntNo, String accountHolderName, double Balance, double overDraftLimit,AccountType at) {
        super(accntNo, accountHolderName, Balance,at);
        this.overDraftLimit=overDraftLimit;
        this.remainingLimit=overDraftLimit;
    }


    @Override
    void withdraw(int amount)  {
        if(getBalance() + overDraftLimit >= amount){
            setBalance((getBalance())-amount);
            if(getBalance() < 0) {
                double overdraftUsed = -getBalance();
                setRemainingLimit(overDraftLimit - overdraftUsed);
            }
            System.out.printf("Withdraw of %d Rs Successful.. Balance %f",amount,getBalance());
        }
        else{
            System.out.println("Not Enough Money");
        }
    }

    @Override
    void deposit(int amount) {
        if(amount >0){
            setBalance(getBalance()+amount);
            System.out.printf("Deposit of %d Rs Successful .Balance %.2f",amount,getBalance());
        }
        else{
            System.out.println("Amount Not Valid Enter a Valid Money");
        }
    }
    void setRemainingLimit(double remain){
        this.remainingLimit = remain;
    }
    void getOverdraftRemainingLimit(){
        System.out.println(remainingLimit);
    }
}