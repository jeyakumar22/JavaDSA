package BankProject.BankManagement.Transaction;
import BankProject.BankManagement.EnumConstants.AccountType;
import BankProject.BankManagement.EnumConstants.Methodoption;
import BankProject.BankManagement.EnumConstants.TransactionType;

import java.util.Date;

public class Transaction {
    private int  transactionId;
    private TransactionType transactionType;
    private double transactionAmount;
    private int fromAccount;
    private int toAccount;
    private boolean isCredit;
    private Date d;

    public Transaction(int transactionId, TransactionType transactionType, double transactionAmount, int fromAccount, int toAccount, boolean isCredit, Date d) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.isCredit = isCredit;
        this.d = d;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionType=" + transactionType +
                ", transactionAmount=" + transactionAmount +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", isCredit=" + isCredit +
                ", d=" + d +
                '}';
    }
}
