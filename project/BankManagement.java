package project;

import java.util.*;

public class BankManagement {
    static Scanner ip = new Scanner(System.in);
    List<Acc> db = new ArrayList<>();
    HashMap<Integer,Acc> customerMap = new HashMap<>();
    public static void main(String[] args) {
        BankManagement b= new BankManagement();
        while(true) {
            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Access Account & Perform Operations");
            System.out.println("3. View All Accounts");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = ip.nextInt();
            ip.nextLine();

            switch(choice) {
                case 1:
                    Acc a =b.createAccount();
                    b.db.add(a);
                    b.customerMap.put(1,a);
                    break;
                case 2:
                    // ACCESS ACCOUNT
                    System.out.print("Enter Customer ID: ");
                    int customerId = ip.nextInt();

                    // Search account by customerId from map/list
                    Acc userAccount = b.findAccountById(customerId);

                    if(userAccount != null) {
                        // SUB-MENU for banking operations
                        while(true) {
                            System.out.println("\n----- BANKING OPERATIONS -----");
                            System.out.println("1. Withdraw");
                            System.out.println("2. Deposit");
                            System.out.println("3. Check Balance");
                            System.out.println("4. Account Details");
                            System.out.println("5. Back to Main Menu");
                            System.out.print("Enter choice: ");

                            int operation = ip.nextInt();

                            switch(operation) {
                                case 1:
                                    // WITHDRAW
                                    System.out.print("Enter amount: ");
                                    int withdrawAmt = ip.nextInt();
                                    userAccount.withdraw(withdrawAmt);
                                    break;

                                case 2:
                                    // DEPOSIT
                                    System.out.print("Enter amount: ");
                                    int depositAmt = ip.nextInt();
                                    userAccount.deposit(depositAmt);
                                    break;

                                case 3:
                                    // CHECK BALANCE
                                    System.out.println("Balance: " + userAccount.getBalance());
                                    break;

                                case 4:
                                    // ACCOUNT DETAILS
                                    userAccount.getDetails();
                                    break;

                                case 5:
                                    // BACK TO MAIN
                                    break;

                                default:
                                    System.out.println("Invalid operation!");
                            }

                            if(operation == 5) break; // Exit sub-menu
                        }
                    } else {
                        System.out.println("Customer ID not found!");
                    }
                    break;

                case 3:
                    // VIEW ALL ACCOUNTS
                    //displayAllAccounts();
                    break;

                case 4:
                    // EXIT
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

        Acc createAccount(){
            System.out.println("\n----- Create Account -----");
            System.out.print("Enter Customer ID: ");
            int customerId = ip.nextInt();
            System.out.print("Enter Account Number: ");
            int accountNo = ip.nextInt();
            ip.nextLine();
            System.out.print("Enter Customer Name: ");
            String name = ip.nextLine();
            System.out.print("Enter Initial Balance: ");
            double balance = ip.nextDouble();

            System.out.println("Select Account Type (1-Savings, 2-Current): ");
            int type = ip.nextInt();

            Acc a;
            if(type == 1) {
                System.out.print("Enter Interest Rate: ");
                double interest = ip.nextDouble();
                a = new Sav(customerId, accountNo, name, balance, interest, AccountType.SAVINGS);
            } else {
                System.out.print("Enter Overdraft Limit: ");
                double overdraft = ip.nextDouble();
                a = new Curr(customerId, accountNo, name, balance, overdraft, AccountType.CURRENT);
            }

            System.out.println("Account created successfully!");
            return a;
        }

    Acc findAccountById(int customerId) {
        // Search in the list
        for(Acc account :db) {
            if(account.getCustomerId() == customerId) {
                return account;
            }
        }
        return null; // Not found
    }
}




abstract class Acc{
    private int customerId;
    private String accountHolderName;
    private final int accntNo;
    private double balance;
    AccountType accountType;


    public Acc(int id,int accntNo,String accountHolderName,double balance,AccountType at){
        this.customerId=id;
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

    public int getCustomerId() {
        return customerId;
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

class Sav extends Acc {
    private double InterestRate;
    public Sav(int id,int accntNo, String accountHolderName, double Balance, double InterestRate, AccountType at) {
        super(id,accntNo, accountHolderName, Balance,at);
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
class Curr extends Acc {
    private double  overDraftLimit;
    private double remainingLimit ;
    public Curr(int id,int accntNo, String accountHolderName, double Balance, double overDraftLimit,AccountType at) {
        super(id,accntNo, accountHolderName, Balance,at);
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