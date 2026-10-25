
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private int pin;
    private double balance;

    public BankAccount(String accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public void Checkbalance() {
        System.out.printf("Current balance is %.2f%n", balance);
    }

    public void Deposite(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero");
        } else {
            balance += amount;
            System.out.println("Amount Deposited Successfully.........!");
            Checkbalance();
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero");
        } else if (amount > balance) {
            System.out.println("Insufficient Balance..........");
        } else {
            balance -= amount;
            System.out.println("Please Collect your cash.........!");
            Checkbalance();
        }
    }

    public boolean changepin(int oldpin, int newpin) {
        if (pin != oldpin) {
            System.out.println("Incorrect Current PIN.");
            return false;
        }

        if (newpin < 1000 || newpin > 9999) {
            System.out.println("New PIN must contain 4 digits.");
            return false;
        }

        if (newpin == oldpin) {
            System.out.println("New PIN must be different.");
            return false;
        }

        pin = newpin;
        System.out.println("PIN Changed Successfully.");
        return true;
    }

    public boolean authentication(String enteredAccount, int enteredpin) {
        return accountNumber.equals(enteredAccount) && pin == enteredpin;
    }
}

public class ATMManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankAccount account =
                new BankAccount("998877664455", 9035, 0.00);

        System.out.println("=====WELCOME TO ATM=====");
        System.out.println("Enter Account Number:");
        String enteredAccount = sc.nextLine();

        System.out.println("Enter PIN Number:");
        int enteredpin = Integer.parseInt(sc.nextLine());

        if (!account.authentication(enteredAccount, enteredpin)) {
            System.out.println("Invalid Account Number and PIN");
            sc.close();
            return;
        }

        System.out.println("Login Successfully");

        boolean running = true;

        while (running) {
            System.out.println("\n====ATM MENU====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Change PIN");
            System.out.println("5. Exit");
            System.out.println("Enter your Choice:");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    account.Checkbalance();
                    break;

                case 2:
                    System.out.print("Enter Amount to Deposit: ");
                    double depositAmount =
                            Double.parseDouble(sc.nextLine());
                    account.Deposite(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter Amount to Withdraw: ");
                    double withdrawAmount =
                            Double.parseDouble(sc.nextLine());
                    account.withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.println("Enter Old PIN:");
                    int oldpin = Integer.parseInt(sc.nextLine());

                    System.out.println("Enter New PIN:");
                    int newpin = Integer.parseInt(sc.nextLine());

                    account.changepin(oldpin, newpin);
                    break;

                case 5:
                    running = false;
                    System.out.println("Thank You For Using Our ATM");
                    break;

                default:
                    System.out.println("Invalid option. Try again");
            }
        }

        sc.close();
    }
}
