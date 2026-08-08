package BankManagementSystem;

public interface Transactional {
  void withdraw(double amount);
  void transfer(int toAccountNumber,double amount);
}
