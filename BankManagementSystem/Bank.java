package BankManagementSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Bank {
  private ArrayList<Account> accounts;
  private HashMap<Integer, Account> accountMap;

  public Bank() {
    accounts = new ArrayList<>();
    accountMap = new HashMap<>();
  }

  public void addAccounts(Account e) {
    accounts.add(e);
    accountMap.put(e.getAccountNumber(), e);
  }

  public void deleteAccounts(int accountNumber) {
    try {
      Account e = accountMap.get(accountNumber);
      if (e == null) {
        throw new AccountNumberNotFoundException("Account Number " + accountNumber + " Not Found");
      }
      accounts.remove(e);
      accountMap.remove(accountNumber);
      System.out.println("Remove Successfully");
    } catch (AccountNumberNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public Account searchAccounts(int accountNumber) throws AccountNumberNotFoundException{
    Account e = accountMap.get(accountNumber);
    if(e == null){
      throw new AccountNumberNotFoundException("Account Number " + accountNumber + " Not Found");
    }
    return e;
  }

  public void displayAllAccounts(){
    for (Account account : accounts) {
      System.out.println(account);
    }
  }

  public void deposit(int accountNumber,double amount){
    try{
      Account account = searchAccounts(accountNumber);
      account.deposite(amount);
      System.out.println("Deposit successfully");
      System.out.println("Current Balance: " + account.getBalance());
    }catch(AccountNumberNotFoundException e){
      System.out.println(e.getMessage());
    }
  }

  public void withdraw(int accountNumber,double amount){
    try{
      Account account = searchAccounts(accountNumber);
      if(account instanceof Transactional){
        Transactional t = (Transactional)account;
        t.withdraw(amount);
        System.out.println("Withdraw successfull");
      }else{
        System.out.println("This account does not support withdraw");
      }
    }catch(AccountNumberNotFoundException e){
      System.out.println(e.getMessage());
    }
  }

  public void transfer(int fromAccountNumber, int toAccountNumber,double amount){
    try{
      Account sender = searchAccounts(fromAccountNumber);
      Account receiver = searchAccounts(toAccountNumber);
      if(sender instanceof Transactional){
        Transactional t = (Transactional)sender;
        t.transfer(toAccountNumber, amount);
        receiver.deposite(amount);
      }else{
        System.out.println("This account does not support Transfer");
      }
    }catch(AccountNumberNotFoundException e){
      System.out.println(e.getMessage());
    }
  }

  public void saveToFile(){
    try{
      FileWriter writer = new FileWriter("BankManagementSystem/Accounts.txt");
      for (Account account : accounts) {
        if(account instanceof SavingsAccount){
          SavingsAccount s = (SavingsAccount)account;
          writer.append("S" + "," + s.getAccountNumber() + "," + s.getAccountHolderName() + "," + s.getBalance() + "\n");
        }else if(account instanceof CurrentAccount){
          CurrentAccount c = (CurrentAccount)account;
          writer.append("C" + "," + c.getAccountNumber() + "," + c.getAccountHolderName() + "," + c.getBalance() + "," + c.getOverdraftLimit() + "\n");
        }
      }
      writer.close();
      System.out.println("File Save successfully");
    }catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  public void loadAccountFromFile(){
    try{
      accounts.clear();
      accountMap.clear();
      BufferedReader reader = new BufferedReader(new FileReader("BankManagementSystem/Accounts.txt"));
      String line;
      while((line = reader.readLine()) != null){
        String[] data = line.split(",");
        String accountType = data[0];
        int accountNumber = Integer.parseInt(data[1]);
        String accountHolderName = data[2];
        double balance = Double.parseDouble(data[3]);
        if(accountType.equals("S")){
          SavingsAccount sa = new SavingsAccount(accountNumber, accountHolderName, balance);
          accounts.add(sa);
          accountMap.put(accountNumber, sa);
        }else if(accountType.equals("C")){
          double overdrift = Double.parseDouble(data[4]);
          CurrentAccount ca = new CurrentAccount(accountNumber, accountHolderName, balance, overdrift);
          accounts.add(ca);
          accountMap.put(accountNumber, ca);
        }
      }

      reader.close();
      System.out.println("File Load successfully");
    }catch(IOException e){
      System.out.println(e.getMessage());
    }
  }
}
