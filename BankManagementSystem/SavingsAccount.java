package BankManagementSystem;

public class SavingsAccount extends Account implements Transactional {
  private double minimumBalance;

  public SavingsAccount(int accountNumber, String accountHolderName, double balance) {
    super(accountNumber, accountHolderName, balance, AccountType.SAVINGS);
    this.minimumBalance = 1000;
  }

  @Override
  public void withdraw(double amount) {
    try {
      if (amount <= 0) {
        throw new InvalidAmountException("Invalid Amount please enter amount greater than 0");
      } else if ((getBalance() - amount) < minimumBalance) {
        throw new InsufficientBalanceException("Insufficient balance. Minimum balance of ₹1000 must be maintained.");
      }
      double newBalance = getBalance() - amount;
      updateBalance(newBalance);
      System.out.println("Withdraw Successfull");
    } catch (InvalidAmountException | InsufficientBalanceException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void transfer(int toAccountNumber, double amount) {
    try {
      if (amount <= 0) {
        throw new InvalidAmountException("Invalid Amount please enter amount greater than 0");
      } else if ((getBalance() - amount) < minimumBalance) {
        throw new InsufficientBalanceException("Insufficient balance. Minimum balance of ₹1000 must be maintained.");
      }

      double newBalance = getBalance() - amount;
      updateBalance(newBalance);
      System.out.println("Transfer Successfull");
    } catch (InvalidAmountException | InsufficientBalanceException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public String toString(){
    return super.toString() + "\n" +
    "Minimum Balance: " + minimumBalance + "\n";
  }
}
