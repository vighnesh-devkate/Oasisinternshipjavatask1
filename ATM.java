package javadeveloper;
/*
  task 2 -Atm System
  Java Devolper Internship 
 */


import java.util.*;

public class ATM {
    private String userId;
    private String userPin;
    private double balance;
    private List<String> transactionHistory;

    public ATM() {
        // For simplicity, you can initialize some test users and transactions here.
        userId = "vighnesh";
        userPin = "Jijau@123";
        balance = 1000.0;
        transactionHistory = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user id: ");
        String inputUserId = scanner.nextLine();

        System.out.print("Enter user pin: ");
        String inputUserPin = scanner.nextLine();

        if (authenticate(inputUserId, inputUserPin)) {
            System.out.println("Authentication successful.");
            showMenu();
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }

    private boolean authenticate(String inputUserId, String inputUserPin) {
        return userId.equals(inputUserId) && userPin.equals(inputUserPin);
    }

    private void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after nextInt()

            switch (option) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    performWithdraw();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Exiting ATM. Have a nice day!");
    }

    private void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    private void performWithdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character after nextDouble()

        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.");
            return;
        }

        if (balance >= amount) {
            balance -= amount;
            String transaction = "Withdraw: -" + amount + ", Balance: " + balance;
            transactionHistory.add(transaction);
            System.out.println("Withdraw successful. Current balance: " + balance);
        } else {
            System.out.println("Insufficient balance. Current balance: " + balance);
        }
    }

    private void performDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character after nextDouble()

        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.");
            return;
        }

        balance += amount;
        String transaction = "Deposit: +" + amount + ", Balance: " + balance;
        transactionHistory.add(transaction);
        System.out.println("Deposit successful. Current balance: " + balance);
    }

    private void performTransfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the user id to transfer: ");
        String recipientUserId = scanner.nextLine();

        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character after nextDouble()

        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.");
            return;
        }

        if (balance >= amount) {
            balance -= amount;
            String transaction = "Transfer to " + recipientUserId + ": -" + amount + ", Balance: " + balance;
            transactionHistory.add(transaction);
            System.out.println("Transfer successful. Current balance: " + balance);
        } else {
            System.out.println("Insufficient balance. Current balance: " + balance);
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
