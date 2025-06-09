package Assignment2;

import Assignment2.BankAccountWithLock;

public class Main {
    public static void main(String[] args) {
        BankAccountWithLock myAccount = new BankAccountWithLock(1000.0);

        // Reader threads
        Thread viewBalance1 = new Thread(() -> {
            myAccount.getBalance();
        }, "BalanceViewer-1");

        Thread viewBalance2 = new Thread(() -> {
            myAccount.getBalance();
        }, "BalanceViewer-2");

        // Writer threads
        Thread addFunds = new Thread(() -> {
            myAccount.deposit(300.0);
        }, "DepositorThread");

        Thread takeFunds = new Thread(() -> {
            myAccount.withdraw(400.0);
        }, "WithdrawalThread");

        // Start all threads
        viewBalance1.start();
        viewBalance2.start();
        addFunds.start();
        takeFunds.start();

        // Ensure all threads complete
        try {
            viewBalance1.join();
            viewBalance2.join();
            addFunds.join();
            takeFunds.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
    }
}

