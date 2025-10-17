package BankProject.BankManagement.SystemAccount;

public class SystemAccount {
    private static SystemAccount instance;

    private final int accountId ;
    private final int accountNumber;
    private final String accountName = "SBI Bank System Account";
    private double balance;

    private SystemAccount() {
        this.accountId = 101;
        this.accountNumber = 222;
        this.balance = 0;
    }

    public static SystemAccount getInstance() {
        if (instance == null) {
            instance = new SystemAccount();
        }
        return instance;
    }

    public int getAccountId() { return accountId; }
    public double getBalance() { return balance; }
    public int getAccountNumber() {
        return accountNumber;
    }

    public void debit(double amount) {
        if (amount > 0 ) {
            balance -= amount;
            System.out.println("Debited: " + amount +"from bank");
        }
    }

    @Override
    public String toString() {
        return "SystemAccount{" +
                "accountId=" + accountId +
                ", accountNumber=" + accountNumber +
                ", accountName='" + accountName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
