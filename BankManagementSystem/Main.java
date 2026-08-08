package BankManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {

            System.out.println("\n========== BANK MANAGEMENT SYSTEM ==========");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Display All Accounts");
            System.out.println("4. Search Account");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. Transfer");
            System.out.println("8. Save Accounts");
            System.out.println("9. Load Accounts");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Account Number: ");
                    int sAccNo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Account Holder Name: ");
                    String sName = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double sBalance = sc.nextDouble();

                    bank.addAccounts(new SavingsAccount(sAccNo, sName, sBalance));
                    System.out.println("Savings Account Created Successfully");
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    int cAccNo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Account Holder Name: ");
                    String cName = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double cBalance = sc.nextDouble();

                    bank.addAccounts(new CurrentAccount(cAccNo, cName, cBalance));
                    System.out.println("Current Account Created Successfully");
                    break;

                case 3:
                    bank.displayAllAccounts();
                    break;

                case 4:
                    try {
                        System.out.print("Enter Account Number: ");
                        int searchNo = sc.nextInt();

                        Account account = bank.searchAccounts(searchNo);

                        System.out.println("\n===== ACCOUNT DETAILS =====");
                        System.out.println(account);

                    } catch (AccountNumberNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Enter Account Number: ");
                    int dAccNo = sc.nextInt();

                    System.out.print("Enter Deposit Amount: ");
                    double dAmount = sc.nextDouble();

                    bank.deposit(dAccNo, dAmount);
                    break;

                case 6:
                    System.out.print("Enter Account Number: ");
                    int wAccNo = sc.nextInt();

                    System.out.print("Enter Withdraw Amount: ");
                    double wAmount = sc.nextDouble();

                    bank.withdraw(wAccNo, wAmount);
                    break;

                case 7:
                    System.out.print("Enter Sender Account Number: ");
                    int fromAcc = sc.nextInt();

                    System.out.print("Enter Receiver Account Number: ");
                    int toAcc = sc.nextInt();

                    System.out.print("Enter Transfer Amount: ");
                    double tAmount = sc.nextDouble();

                    bank.transfer(fromAcc, toAcc, tAmount);
                    break;

                case 8:
                    bank.saveToFile();
                    break;

                case 9:
                    bank.loadAccountFromFile();
                    break;

                case 0:
                    System.out.println("Thank you for using Banking Management System.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
