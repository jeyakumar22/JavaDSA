package BankProject.BankManagement.Customer;

public class Customer {
    private int customerId;
    private String customerName;
    private String BankName;
    private String email;
    private String phone;
    private String address;

    public Customer(int customerId, String customerName, String bankName, String email, String phone, String address) {
        this.customerId = customerId;
        this.customerName = customerName;
        BankName = bankName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String toString() {
        return "Customer ID : " + customerId +
                "\nCustomer Name: " + customerName +
                "\nBank Name: " + BankName +
                "\nEmail: " + email +
                "\nPhone: " + phone +
                "\nAddress: " + address +"\n";
    }
}