package BankProject.BankManagement.Transaction;
import BankProject.BankManagement.EnumConstants.AccountType;
import BankProject.BankManagement.EnumConstants.Methodoption;
import BankProject.BankManagement.EnumConstants.TransactionType;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Transaction {
    private static int nextTransactionId=1;
    private int  transactionId;
    private TransactionType transactionType;
    private double transactionAmount;
    private int fromAccount;
    private int toAccount;
    private boolean isCredit;
    private LocalDateTime transactionDate;

    public Transaction(TransactionType transactionType, double transactionAmount, int fromAccount, int toAccount, boolean isCredit) {
        this.transactionId = nextTransactionId++;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.isCredit = isCredit;
        this.transactionDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("ID:%-4d | Type:%-9s | Amount:₹%-8.2f | From:%-4d | To:%-4d | %-6s | Time:%s",
                transactionId,
                transactionType,
                transactionAmount,
                fromAccount,
                toAccount,
                isCredit ? "CREDIT" : "DEBIT",  // Full forms
                transactionDate.format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")));
    }
}
