package BankManagementSystem;

public abstract class Account {
  private int accountNumber;
  private String accountHolderName;
  private AccountType accountType;
  private double balance;

  public Account(int accountNumber,String accountHolderName,double balance,AccountType accountType){
    this.accountNumber = accountNumber;
    this.accountHolderName = accountHolderName;
    this.balance = balance;
    this.accountType = accountType;
  }

  public int getAccountNumber(){
    return accountNumber;
  }

  public String getAccountHolderName(){
    return accountHolderName;
  }

  public AccountType getAccountType(){
    return accountType;
  }

  public double getBalance(){
    return balance;
  }

  public void deposite(double amount){
    if(amount <= 0){
      System.out.println("Please enter amount greater than 0");
      return;
    }
    balance += amount;
  }

  public String toString(){
    return "Account Number : " + accountNumber + "\n" +
    "Account Holder Name : " + accountHolderName + "\n" +
    "Account Type : " + accountType + "\n" +
    "Balance : " + balance + "\n";
  }

  public void updateBalance(double balance){
    this.balance = balance;
  }
}
