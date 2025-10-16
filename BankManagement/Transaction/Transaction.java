package BankProject.BankManagement.Transaction;
import BankProject.BankManagement.EnumConstants.AccountType;

public class Transaction {
    private int  tId;
    private int  accNo;
    private String  custName;
    private AccountType at;
    private String about;

    public Transaction(int tId, int accNo, String custName, AccountType at, String about) {
        this.tId = tId;
        this.accNo = accNo;
        this.custName = custName;
        this.at = at;
        this.about = about;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "tId=" + tId +
                ", accNo=" + accNo +
                ", custName='" + custName + '\'' +
                ", at=" + at +
                ", about='" + about + '\'' +
                '}';
    }
}
