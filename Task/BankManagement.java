package Task;

import java.util.*;

public class BankManagement {
    static Scanner ip = new Scanner(System.in);
    static List<Account> accountList = new ArrayList<>();
    static HashMap<Integer, Account> customerMap = new HashMap<>();

    public static void main(String[] args) {
        BankManagement bm = new BankManagement();
        Options[] Options = Task.Options.values();
        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1 - Create Account");
            System.out.println("2 - Access Account");
            System.out.println("3 - Exit");
            System.out.print("Enter your choice: ");
            int choice = ip.nextInt();
            if (choice <= 0 || choice > Options.length) {
                System.out.println("Enter A Valid Options");
                continue;
            }
            ip.nextLine();
            Options selectedOption = Options[choice - 1];
            switch (selectedOption) {
                case CREATEACCOUNT:
                    Account acnt = bm.createAccount();
                    System.out.println("Account created successfully!");
                    System.out.println("Your Account type and your Id: " + acnt.getAccountType() + acnt.getCustomerId());
                    accountList.add(acnt);
                    customerMap.put(acnt.getCustomerId(), acnt);
                    break;

                case ACCESSSACCOUNT:
                    System.out.println("Enter your customer Id");
                    int id = ip.nextInt();
                    Account act = bm.findAccountById(id);
                    if(act == null) break;
                    if (act instanceof Saving saving) {
                        bm.handleSavingAccountOperations(saving);
                    } else if (act instanceof Current current) {
                        bm.handleCurrentAccountOperations(current);
                    } else {
                        System.out.println("Invalid account type!");
                    }
                    act.getDetails();
                    System.out.println(act);
                    break;

                case EXIT:
                    System.out.println("ThankYou");
                    return;
                default:
                    System.out.println("Wrong Choice");
                    return;
            }
        }
    }

    Account createAccount() {
        System.out.println("\n----- Create Account -----");
        System.out.print("Enter customer ID: ");
        int customerId = ip.nextInt();
        System.out.print("Enter cccount number: ");
        int accountNo = ip.nextInt();
        ip.nextLine();
        System.out.print("Enter customer name: ");
        String name = ip.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = ip.nextDouble();

        System.out.println("Select Account Type (1-Savings, 2-Current): ");
        int type = ip.nextInt();


        if (type == 1) {
            System.out.print("Enter Interest Rate: ");
            double interest = ip.nextDouble();
            return new Saving(customerId, accountNo, name, balance, interest, AccountType.SAVINGS);
        } else {
            System.out.print("Enter Overdraft Limit: ");
            double overdraft = ip.nextDouble();
            return new Current(customerId, accountNo, name, balance, overdraft, AccountType.CURRENT);
        }
    }

    Account findAccountById(int id) {
        for (int key : customerMap.keySet()) {
            if (id == key) {
                return customerMap.get(id);
            }
        }
        System.out.println("Create an account to Access");
        return null;
    }

    void handleSavingAccountOperations(Saving acc) {
        while (true) {
            System.out.println("\nChoose Operation:");
            System.out.println("1. Deposit\n2. Withdraw\n3. Check Balance\n4. AddInterest\n5. Exit");
            int opChoice = ip.nextInt();

            if (opChoice <= 0 || opChoice > Methodoption.values().length) {
                System.out.println("Invalid choice, try again!");
                continue;
            }
            Methodoption operation = Methodoption.values()[opChoice - 1];

            switch (operation) {
                case DEPOSIT:
                    System.out.print("Enter amount: ");
                    acc.deposit(ip.nextInt());
                    break;

                case WITHDRAW:
                    System.out.print("Enter amount: ");
                    acc.withdraw(ip.nextInt());
                    break;

                case CHECKBALANCE:
                    System.out.print("Your Account Balance" + (acc.getBalance()));
                    break;

                case SPECIAL:
                    acc.applyYearlyInterest();
                    break;

                case EXIT:
                    System.out.println("Returning to main menu...");
                    return; // exit the current method or loop

                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }
        }

    }

    void handleCurrentAccountOperations(Current acc) {
        while (true) {
            System.out.println("\nChoose Operation:");
            System.out.println("1. Deposit\n2. Withdraw\n3. Check Balance\n4.CheckRemainingLimit\n5. Exit");
            int opChoice = ip.nextInt();

            if (opChoice <= 0 || opChoice > Methodoption.values().length) {
                System.out.println("Invalid choice, try again!");
                continue;
            }
            Methodoption operation = Methodoption.values()[opChoice - 1];

            switch (operation) {
                case DEPOSIT:
                    System.out.print("Enter amount: ");
                    acc.deposit(ip.nextInt());
                    break;

                case WITHDRAW:
                    System.out.print("Enter amount: ");
                    acc.withdraw(ip.nextInt());
                    break;

                case CHECKBALANCE:
                    System.out.print("Your Account Balance" + (acc.getBalance()));
                    break;

                case SPECIAL:
                    System.out.print("Your Account Balance" +  acc.getOverdraftRemainingLimit());

                    break;

                case EXIT:
                    System.out.println("Returning to main menu...");
                    return; // exit the current method or loop

                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }

        }
    }

}
    abstract class Account {
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
            return "Account Type: " + accountType +
                    "\nAccount No: " + accntNo +
                    "\nAccount Holder: " + accountHolderName +
                    "\nBalance: " + String.format("%.2f", balance);
        }

        abstract void withdraw(int amount);
        abstract void deposit(int amount);

    }

    class Saving extends Account {
        private double InterestRate;

        public Saving(int id, int accntNo, String accountHolderName, double Balance, double InterestRate, AccountType at) {
            super(id, accntNo, accountHolderName, Balance, at);
            this.InterestRate = InterestRate;
        }


        @Override
        void withdraw(int amount) {
            if (getBalance() >= amount) {
                setBalance(getBalance() - amount);
                System.out.printf("Withdraw of %d Rs Successful .Balance %.2f", amount, getBalance());
            } else {
                System.out.println("Not Enough Money");
            }
        }

        @Override
        void deposit(int amount) {
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

    class Current extends Account {
        private double overDraftLimit;
        private double remainingLimit;

        public Current(int id, int accntNo, String accountHolderName, double Balance, double overDraftLimit, AccountType at) {
            super(id, accntNo, accountHolderName, Balance, at);
            this.overDraftLimit = overDraftLimit;
            this.remainingLimit = overDraftLimit;
        }


        @Override
        void withdraw(int amount) {
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
        void deposit(int amount) {
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

        double getOverdraftRemainingLimit() {
            return remainingLimit;
        }
    }
