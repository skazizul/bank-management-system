package BankManagementSystem;

public class CurrentAccount extends Account implements Transactional {
  private double overDriftLimit;
  public CurrentAccount(int accountNumber,String accountHolderName,double balance){
    super(accountNumber, accountHolderName, balance, AccountType.CURRENT);
    this.overDriftLimit = 10000;
  }

  public CurrentAccount(int accountNumber,String accountHolderName,double balance,double overDriftLimit){
    super(accountNumber, accountHolderName, balance, AccountType.CURRENT);
    this.overDriftLimit = overDriftLimit;
  }
  public double getOverdraftLimit(){
    return overDriftLimit;
  }
  @Override
  public void withdraw(double amount){
    try{
      if(amount <= 0){
        throw new InvalidAmountException("Invalid Amount please enter amount greater than 0");
      }else if(amount > (getBalance() + overDriftLimit)){
        throw new InsufficientBalanceException("Insufficient balance and overdraft limit exceeded.");
      }

      if(amount > getBalance()){
        double overDriftLimitUsed = amount - getBalance();
        overDriftLimit -= overDriftLimitUsed;
      }
      updateBalance(getBalance() - amount);
      System.out.println("Withdraw successfull");
    }catch(InvalidAmountException | InsufficientBalanceException e){
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void transfer(int toAccountNumber,double amount){
    try{
      if(amount <= 0){
        throw new InvalidAmountException("Invalid Amount please enter amount greater than 0");
      }else if(amount > (getBalance() + overDriftLimit)){
        throw new InsufficientBalanceException("Insufficient balance and overdraft limit exceeded.");
      }

      if(amount > getBalance()){
        double overDriftLimitUsed = amount - getBalance();
        overDriftLimit -= overDriftLimitUsed;
      }
      updateBalance(getBalance() - amount);
      System.out.println("transfer successfull");
      System.out.println("Transferred ₹" + amount + " to Account No: " + toAccountNumber);
      System.out.println("Current Balance: " + getBalance());
      System.out.println("Remaining Overdraft Limit: " + overDriftLimit);
    }catch(InvalidAmountException | InsufficientBalanceException e){
      System.out.println(e.getMessage());
    }
  }

  @Override
  public String toString(){
    return super.toString() + "\n" + 
    "Overdraft Limit: " + overDriftLimit + "\n";
  }
}

