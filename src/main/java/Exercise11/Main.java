package Exercise11;

public class Main {
    public static void main(String[] args) {
        BankAccountWithLock account = new BankAccountWithLock(1000.0);

        // Create thread to read balance
        Thread reader1 = new Thread(() -> {
            account.getBalance();
        }, "Reader-1");

        Thread reader2 = new Thread(() -> {
            account.getBalance();
        }, "Reader-2");

        // Create thread to deposit money
        Thread depositor = new Thread(() -> {
            account.deposit(500.0);
        }, "Depositor");

        // Create thread to withdraw money
        Thread withdrawer = new Thread(() -> {
            account.withdraw(700.0);
        }, "Withdrawer");

        // Start all threads
        reader1.start();
        reader2.start();
        depositor.start();
        withdrawer.start();

        // Wait for all threads to finish
        try {
            reader1.join();
            reader2.join();
            depositor.join();
            withdrawer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

