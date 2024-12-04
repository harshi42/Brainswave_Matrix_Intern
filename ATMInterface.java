import java.util.*;

class User {
    private String accountNumber;
    private int pin;
    private double balance;
    private List<String> transactionHistory;

    public User(String accountNumber, int pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance: $" + initialBalance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean authenticate(int pin) {
        return this.pin == pin;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: $" + amount);
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

class ATM {
    private Map<String, User> users;  // Simulating user database (in-memory)
    private int maxPinAttempts = 3;   // Max incorrect PIN attempts

    public ATM() {
        users = new HashMap<>();
        // Pre-populate with some users (this simulates a database)
        users.put("123456789", new User("123456789", 1234, 1000.00));
        users.put("987654321", new User("987654321", 5678, 2000.00));
    }

    // Authenticate the user with account number and PIN
    public User authenticateUser(String accountNumber, int pin) {
        User user = users.get(accountNumber);
        if (user != null && user.authenticate(pin)) {
            return user;
        }
        return null;
    }

    // Lock the account after max failed attempts (simulated)
    public boolean lockAccount(String accountNumber) {
        System.out.println("Account " + accountNumber + " is locked due to multiple incorrect PIN attempts.");
        return false;
    }

    // Getter for maxPinAttempts to avoid direct access from outside
    public int getMaxPinAttempts() {
        return maxPinAttempts;
    }

    // Method to add a new user (for demonstration purposes)
    public void addUser(String accountNumber, int pin, double initialBalance) {
        if (!users.containsKey(accountNumber)) {
            users.put(accountNumber, new User(accountNumber, pin, initialBalance));
            System.out.println("New user added with account number: " + accountNumber);
        } else {
            System.out.println("Account number already exists.");
        }
    }

    // Get the list of users (for debugging purposes)
    public Map<String, User> getUsers() {
        return users;
    }
}

public class ATMInterface {  // Ensure this file is named ATMInterface.java
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();
        User currentUser = null;
        String accountNumber;
        int pin;
        int incorrectAttempts = 0;

        System.out.println("Welcome to the ATM");

        // Allow the user to register if needed (for example, we can add a registration step)
        System.out.print("Do you want to register a new user? (yes/no): ");
        String registrationResponse = scanner.nextLine();
        if (registrationResponse.equalsIgnoreCase("yes")) {
            System.out.print("Enter new account number: ");
            String newAccountNumber = scanner.nextLine();
            System.out.print("Enter new PIN: ");
            int newPin = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character
            System.out.print("Enter initial balance: $");
            double initialBalance = scanner.nextDouble();
            scanner.nextLine();  // Consume the newline character
            atm.addUser(newAccountNumber, newPin, initialBalance);
        }

        // User login loop
        while (true) {
            System.out.print("Please enter your account number: ");
            accountNumber = scanner.nextLine();
            System.out.print("Please enter your PIN: ");
            pin = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            currentUser = atm.authenticateUser(accountNumber, pin);
            if (currentUser != null) {
                System.out.println("Login successful!");
                break;
            } else {
                incorrectAttempts++;
                System.out.println("Incorrect account number or PIN.");
                if (incorrectAttempts >= atm.getMaxPinAttempts()) {
                    atm.lockAccount(accountNumber);
                    return;  // Exit program after locking
                }
            }
        }

        // Main menu loop
        boolean running = true;
        while (running) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character
            
            switch (choice) {
                case 1:
                    System.out.println("Current balance: $" + currentUser.getBalance());
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    if (currentUser.withdraw(withdrawAmount)) {
                        System.out.println("Please take your cash: $" + withdrawAmount);
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                    break;
                case 3:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    currentUser.deposit(depositAmount);
                    System.out.println("Deposit successful!");
                    break;
                case 4:
                    System.out.println("Transaction History:");
                    for (String transaction : currentUser.getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }

        scanner.close();
    }
}

