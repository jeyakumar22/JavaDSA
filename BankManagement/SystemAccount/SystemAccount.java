package BankProject.BankManagement.SystemAccount;

public class SystemAccount {
    // Singleton instance
    private static SystemAccount instance;

    private final int accountId = 0; // Fixed system account ID
    private final String accountName = "SBI Bank System Account";
    private double balance;

    // Private constructor to prevent instantiation
    private SystemAccount() {
        this.balance = 1000000.0; // Initial system balance - can be any large number
    }

    // Public method to get the singleton instance
    public static SystemAccount getInstance() {
        if (instance == null) {
            instance = new SystemAccount();
        }
        return instance;
    }

    // Getters
    public int getAccountId() { return accountId; }
    public String getAccountName() { return accountName; }
    public double getBalance() { return balance; }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "System Account [ID: " + accountId + ", Name: " + accountName + ", Balance: " + balance + "]";
    }
}
