package BankProject.BankManagement;

import BankProject.BankManagement.Account.Account;
import BankProject.BankManagement.Account.Current;
import BankProject.BankManagement.Account.Saving;
import BankProject.BankManagement.Customer.Customer;
import BankProject.BankManagement.EnumConstants.AccountType;
import BankProject.BankManagement.EnumConstants.Methodoption;
import BankProject.BankManagement.EnumConstants.Options;
import BankProject.BankManagement.EnumConstants.TransactionType;
import BankProject.BankManagement.SystemAccount.SystemAccount;
import BankProject.BankManagement.Transaction.Transaction;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class BankManagement {
    static String bankName = "SBI";
    static int AccountNumber = 1000;
    static Scanner ip = new Scanner(System.in);
    static List<Account> accountList = new ArrayList<>();
    static List<Customer> customersList = new ArrayList<>();
    static List<Transaction> transactionList = new ArrayList<>();
    //customer id
    static int nextId =1;

    public static void main(String[] args) {
        BankManagement bm = new BankManagement();
        initialStage(bm);
    }
    static void initialStage(BankManagement bm){
        SystemAccount sysAcc = SystemAccount.getInstance();
        System.out.println(sysAcc);
        Options[] Options = BankProject.BankManagement.EnumConstants.Options.values();
        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1 - Create Account");
            System.out.println("2 - List of All Customers");
            System.out.println("3 - List of All Accounts");
            System.out.println("4 - Access Account");
            System.out.println("5 - Exit");
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
                    Customer customer = bm.createCustomer();
                    if(customer !=null){
                        System.out.println("Customer created successfully\n");
                        System.out.println("Your Details\n" + customer);
                        customersList.add(customer);
                    }
                    Account acnt = bm.createAccount(customer.getCustomerId(),customer.getCustomerName());
                    if(acnt != null){
                        processInitialBalance(acnt);
                        System.out.println("Account created successfully\n");
                        System.out.println("Account Details\n" + acnt);
                        accountList.add(acnt);
                    }
                    //customerMap.put(acnt.getCustomerId(), acnt);
                    //System.out.println(accountList.size());
                    break;
                case LISTCUSTOMERDETEILS:
                    bm.listOfCustomers();
                    break;
                case LISTACCOUNTDETAILS:
                    bm.listOfAccounts();
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
                    //act.getDetails();
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

    private static void processInitialBalance(Account account) {
        if (account.getBalance() > 0) {
            SystemAccount systemAcc = SystemAccount.getInstance();

            systemAcc.debit(account.getBalance());

            Transaction initialDebit = new Transaction(
                    TransactionType.WITHDRAW,
                    account.getBalance(),
                    systemAcc.getAccountNumber(),  // from system
                    account.getAccntNo(),
                    false
            );Transaction initialCredit = new Transaction(
                    TransactionType.DEPOSIT,
                    account.getBalance(),
                    systemAcc.getAccountNumber(),  // from system
                    account.getAccntNo(),
                    true
            );
            transactionList.add(initialDebit);
            transactionList.add(initialCredit);
            System.out.println(initialDebit);
            System.out.println(initialCredit);
            System.out.println("✓ Initial Balance processed: ₹" + account.getBalance());
            transactionList.forEach(transaction -> System.out.println(transaction));
        }
    }

    void listOfAccounts() {
        if(customersList.isEmpty()) System.out.println("No Available Accounts");
        accountList.forEach(account -> System.out.println(account));
    }

    void listOfCustomers() {
        if(customersList.isEmpty()) System.out.println("No Available Customers");
        Stream<Customer> st = customersList.stream();
        st.forEach(customer -> System.out.println(customer));
    }

    Customer createCustomer() {
        System.out.println("\n----- Create Customer -----");

        // Generate customer ID
        int customerId = nextId++;

        // Collect customer details
        System.out.print("Enter Customer Name: ");
        String customerName = ip.nextLine();

        System.out.print("Enter Bank Name: ");
        String bankName = ip.nextLine();

        System.out.print("Enter Email: ");
        String email = ip.nextLine();

        System.out.print("Enter Phone: ");
        String phone = ip.nextLine();

        System.out.print("Enter Address: ");
        String address = ip.nextLine();

        // Create and return Customer object
        return new Customer(customerId, customerName, bankName, email, phone, address);
    }

    Account createAccount(int customerId,String customerName) {
        System.out.println("\n----- Create Account -----");
        int accountNo = AccountNumber++;
        //ip.nextLine();
        String name = customerName;
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
        Stream<Account> st = accountList.stream().filter(account -> id==account.getCustomerId());
        System.out.println("Create an account to Access");
        return st.findFirst().orElse(null);
    }

    void handleSavingAccountOperations(Saving acc) {
        while (true) {
            System.out.println("\nChoose Operation:");
            System.out.println("1.Transaction \n2.Checkbalance \n3.Exit");
            int opChoice = ip.nextInt();

            if (opChoice <= 0 || opChoice > Methodoption.values().length) {
                System.out.println("Invalid choice, try again!");
                continue;
            }
            Methodoption operation = Methodoption.values()[opChoice - 1];

            switch (operation) {
                case TRANSACTION:
                    handleSavingTransaction(acc);
                    break;

                case CHECKBALANCE:
                    System.out.print("Your Account Balance" + (acc.getBalance()));
                    break;

                case EXIT:
                    System.out.println("Returning to main menu...");
                    return;

                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }
        }

    }

  /*  private void handleSavingTransaction(Saving acc) {
        System.out.println("\nChoose Operation:");
        System.out.println("1. Deposit\n2. Withdraw\n3.Transfer\n 4.AddInterest\n5. Exit");
        int opChoice = ip.nextInt();
        while (true) {
            if (opChoice <= 0 || opChoice > TransactionType.values().length) {
                System.out.println("Invalid choice, try again!");
                continue;
            }
            TransactionType operation = TransactionType.values()[opChoice - 1];
            switch (operation) {
                case DEPOSIT:
                    System.out.print("Enter amount: ");
                    double depositAmount = ip.nextDouble();
                    acc.deposit((int)depositAmount);
                    Transaction depositTx = new Transaction(
                            TransactionType.DEPOSIT,
                            depositAmount,
                            SystemAccount.getInstance().getAccountNumber(),
                            acc.getAccntNo(),
                            true
                    );
                    SystemAccount.getInstance().debit(depositAmount);
                    transactionList.add(depositTx);
                    System.out.println("Deposit transaction recorded: " + depositTx);
                    break;

                case WITHDRAW:
                    System.out.print("Enter amount: ");
                    int withdrawAmount = ip.nextInt();
                    acc.withdraw(withdrawAmount);
                    Transaction withdrawTx = new Transaction(
                            TransactionType.WITHDRAW,
                            withdrawAmount,
                            acc.getAccntNo(),-1,
                            //SystemAccount.getInstance().getAccountId(),
                            false
                    );
                    transactionList.add(withdrawTx);
                    System.out.println("Withdrawal transaction recorded: " + withdrawTx);
                    break;

                case TRANSFER:
                    System.out.print("Enter recipient account number: ");
                    int toAccountNo = ip.nextInt();

                    System.out.print("Enter transfer amount: ");
                    int transferAmount = ip.nextInt();

                    Account toAccount = findAccountByNumber(toAccountNo);
                    if (toAccount == null) {
                        System.out.println("Recipient account not found!");
                        return;
                    }


                    acc.withdraw(transferAmount);
                    toAccount.deposit(transferAmount);


                    Transaction transferTx = new Transaction(
                            TransactionType.TRANSFER,
                            transferAmount,
                            acc.getAccntNo(),     // from sender
                            toAccount.getAccntNo(),       // to receiver
                            false
                    );
                    transactionList.add(transferTx);
                    System.out.println("Transfer successful: " + transferTx);
                    break;

                case SPECIAL:
                    double interestAmount = acc.applyYearlyInterest();
                    Transaction interestTx = new Transaction(
                            TransactionType.SPECIAL,
                            interestAmount,
                            SystemAccount.getInstance().getAccountNumber(),
                            acc.getAccntNo(),
                            true
                    );
                    SystemAccount.getInstance().debit(interestAmount);
                    transactionList.add(interestTx);
                    System.out.println("Interest transaction recorded: " + interestTx);
                    break;

                case EXIT:
                    return;
            }
        }
    }*/
  private void handleSavingTransaction(Saving acc) {
      while (true) {
          System.out.println("\nChoose Operation:");
          System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. AddInterest\n5. Exit");
          int opChoice = ip.nextInt();

          if (opChoice == 5) {
              System.out.println("Returning to main menu...");
              return;
          }

          if (opChoice <= 0 || opChoice > 5) {
              System.out.println("Invalid choice, try again!");
              continue;
          }

          TransactionType operation;
          switch (opChoice) {
              case 1: operation = TransactionType.DEPOSIT; break;
              case 2: operation = TransactionType.WITHDRAW; break;
              case 3: operation = TransactionType.TRANSFER; break;
              case 4: operation = TransactionType.INTEREST; break;
              default: continue;
          }

          switch (operation) {
              case DEPOSIT:
                  System.out.print("Enter amount: ");
                  double depositAmount = ip.nextDouble();
                  acc.deposit((int) depositAmount);

                  Transaction depositTx = new Transaction(
                          TransactionType.DEPOSIT,
                          depositAmount,
                          SystemAccount.getInstance().getAccountNumber(),
                          acc.getAccntNo(),
                          true
                  );
                  SystemAccount.getInstance().debit(depositAmount);
                  transactionList.add(depositTx);
                  System.out.println("Deposit transaction recorded: " + depositTx);
                  transactionList.forEach(transaction -> System.out.println(transaction));
                  System.out.println(SystemAccount.getInstance().getBalance());
                  break;

              case WITHDRAW:
                  System.out.print("Enter amount: ");
                  int withdrawAmount = ip.nextInt();
                  acc.withdraw(withdrawAmount);

                  Transaction withdrawTx = new Transaction(
                          TransactionType.WITHDRAW,
                          withdrawAmount,
                          acc.getAccntNo(),
                          acc.getAccntNo(),
                          false
                  );

                  transactionList.add(withdrawTx);
                  System.out.println("Withdrawal transaction recorded: " + withdrawTx);
                  transactionList.forEach(transaction -> System.out.println(transaction));
                  System.out.println(SystemAccount.getInstance().getBalance());
                  break;

              case TRANSFER:
                  System.out.print("Enter recipient account number: ");
                  int toAccountNo = ip.nextInt();
                  System.out.print("Enter transfer amount: ");
                  int transferAmount = ip.nextInt();

                  Account toAccount = findAccountByNumber(toAccountNo);
                  if (toAccount == null) {
                      System.out.println("Recipient account not found!");
                      continue;
                  }

                  // Perform transfer
                  acc.withdraw(transferAmount);
                  toAccount.deposit(transferAmount);

                  // Create transfer transaction
                  Transaction transferTx = new Transaction(
                          TransactionType.TRANSFER,
                          transferAmount,
                          acc.getAccntNo(),     // from sender
                          toAccount.getAccntNo(), // to receiver
                          false                 // debit for sender
                  );
                  transactionList.add(transferTx);
                  System.out.println("Transfer successful: " + transferTx);
                  transactionList.forEach(transaction -> System.out.println(transaction));
                  System.out.println(SystemAccount.getInstance().getBalance());
                  break;

              case INTEREST:
                  double interestAmount = acc.applyYearlyInterest();
                  Transaction interestTx = new Transaction(
                          TransactionType.INTEREST,
                          interestAmount,
                          SystemAccount.getInstance().getAccountNumber(),
                          acc.getAccntNo(),
                          true
                  );
                  SystemAccount.getInstance().debit(interestAmount);
                  transactionList.add(interestTx);
                  System.out.println("Interest transaction recorded: " + interestTx);
                  transactionList.forEach(transaction -> System.out.println(transaction));
                  System.out.println(SystemAccount.getInstance().getBalance());
                  break;
          }
      }
  }

    private Account findAccountByNumber(int toAccountNo) {
        return accountList.stream().filter(account -> account.getAccntNo() == toAccountNo).findFirst().orElse(null);
    }

    void handleCurrentAccountOperations(Current acc) {
        while (true) {
            System.out.println("\nChoose Operation:");
            System.out.println("1.Transaction \n2.Checkbalance \n3.Exit");
            int opChoice = ip.nextInt();

            if (opChoice <= 0 || opChoice > Methodoption.values().length) {
                System.out.println("Invalid choice, try again!");
                continue;
            }
            Methodoption operation = Methodoption.values()[opChoice - 1];

            switch (operation) {
                case TRANSACTION:
                    handleCurrentTransaction(acc);
                    break;
                case CHECKBALANCE:
                    System.out.print("Your Account Balance" + (acc.getBalance()));
                    break;
                case EXIT:
                    System.out.println("Returning to main menu...");
                    return;

                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }

        }
    }

    /*private void handleCurrentTransaction(Current acc) {
        System.out.println("\nChoose Operation:");
        System.out.println("1. Deposit\n2. Withdraw\n3.Transfer \n4.CheckRemainingLimit\n5. Exit");
        int opChoice = ip.nextInt();
        while (true) {
            if (opChoice <= 0 || opChoice > TransactionType.values().length) {
                System.out.println("Invalid choice, try again!");
                continue;
            }
            TransactionType operation = TransactionType.values()[opChoice - 1];
            switch (operation) {
                case DEPOSIT:
                    System.out.print("Enter amount: ");
                    double depositAmount = ip.nextDouble();
                    acc.deposit((int)depositAmount);
                    Transaction depositTx = new Transaction(
                            TransactionType.DEPOSIT,
                            depositAmount,
                            SystemAccount.getInstance().getAccountId(),
                            acc.getAccntNo(),
                            true
                    );
                    SystemAccount.getInstance().debit(depositAmount);
                    transactionList.add(depositTx);
                    System.out.println("Deposit transaction recorded: " + depositTx);
                    break;

                case WITHDRAW:
                    System.out.print("Enter amount: ");
                    int withdrawAmount = ip.nextInt();
                    acc.withdraw(withdrawAmount);
                    Transaction withdrawTx = new Transaction(
                            TransactionType.WITHDRAW,
                            withdrawAmount,
                            acc.getAccntNo(),-1,
                            //SystemAccount.getInstance().getAccountId(),
                            false
                    );
                    transactionList.add(withdrawTx);
                    System.out.println("Withdrawal transaction recorded: " + withdrawTx);
                    break;

                case TRANSFER:
                    break;

                case SPECIAL:
                    break;

                case EXIT:
                    return;
            }
        }
    }*/
    private void handleCurrentTransaction(Current acc) {
        while (true) {
            System.out.println("\nChoose Operation:");
            System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. CheckRemainingLimit\n5. Exit");
            int opChoice = ip.nextInt();


            if (opChoice == 5) {
                System.out.println("Returning to main menu...");
                return;
            }

            if (opChoice == 4) {
                System.out.println("Remaining Overdraft Limit: ₹" + acc.getOverdraftRemainingLimit());
                continue;
            }

            if (opChoice <= 0 || opChoice > 3) {
                System.out.println("Invalid choice, try again!");
                continue;
            }

            TransactionType operation;
            switch (opChoice) {
                case 1: operation = TransactionType.DEPOSIT; break;
                case 2: operation = TransactionType.WITHDRAW; break;
                case 3: operation = TransactionType.TRANSFER; break;
                default: continue;
            }

            switch (operation) {
                case DEPOSIT:
                    System.out.print("Enter amount: ");
                    double depositAmount = ip.nextDouble();
                    acc.deposit((int) depositAmount);

                    Transaction depositTx = new Transaction(
                            TransactionType.DEPOSIT,
                            depositAmount,
                            SystemAccount.getInstance().getAccountNumber(),
                            acc.getAccntNo(),
                            true
                    );
                    SystemAccount.getInstance().debit(depositAmount);
                    transactionList.add(depositTx);
                    System.out.println("Deposit transaction recorded: " + depositTx);
                    break;

                case WITHDRAW:
                    System.out.print("Enter amount: ");
                    int withdrawAmount = ip.nextInt();
                    acc.withdraw(withdrawAmount);

                    Transaction withdrawTx = new Transaction(
                            TransactionType.WITHDRAW,
                            withdrawAmount,
                            acc.getAccntNo(),
                            SystemAccount.getInstance().getAccountNumber(),
                            false
                    );

                    transactionList.add(withdrawTx);
                    System.out.println("Withdrawal transaction recorded: " + withdrawTx);
                    break;

                case TRANSFER:
                    System.out.print("Enter recipient account number: ");
                    int toAccountNo = ip.nextInt();
                    System.out.print("Enter transfer amount: ");
                    int transferAmount = ip.nextInt();

                    Account toAccount = findAccountByNumber(toAccountNo);
                    if (toAccount == null) {
                        System.out.println("Recipient account not found!");
                        continue;
                    }


                    acc.withdraw(transferAmount);
                    toAccount.deposit(transferAmount);

                    Transaction transferTx = new Transaction(
                            TransactionType.TRANSFER,
                            transferAmount,
                            acc.getAccntNo(),
                            toAccount.getAccntNo(),
                            false
                    );
                    transactionList.add(transferTx);
                    System.out.println("Transfer successful: " + transferTx);
                    break;
            }
        }
    }
}
