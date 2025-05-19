package Exercise8;

public class ComparisonOfNormalAndSynchronizedThreads {

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 10;


        NormalThread[] normalThreads = new NormalThread[threadCount];
        long startNormal = System.nanoTime();
        for (int i = 0; i < threadCount; i++) {
            normalThreads[i] = new NormalThread();
            normalThreads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            normalThreads[i].join();
        }
        long endNormal = System.nanoTime();
        double durationNormal = (endNormal - startNormal) / 1_000_000_000.0;


        SynchronizedThread[] syncThreads = new SynchronizedThread[threadCount];
        SharedPrinter printer = new SharedPrinter();
        long startSync = System.nanoTime();
        for (int i = 0; i < threadCount; i++) {
            syncThreads[i] = new SynchronizedThread(printer);
            syncThreads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            syncThreads[i].join();
        }
        long endSync = System.nanoTime();
        double durationSync = (endSync - startSync) / 1_000_000_000.0;


        System.out.printf("Normal thread = %.9f seconds%n", durationNormal);
        System.out.printf("Synchronized thread = %.9f seconds%n", durationSync);
    }
}


class NormalThread extends Thread {
    @Override
    public void run() {
        doSomething();
    }

    private void doSomething() {
        int sum = 0;
        for (int i = 0; i < 10000; i++) {
            sum += i;
        }
    }
}


class SharedPrinter {
    public synchronized void doSomething() {
        int sum = 0;
        for (int i = 0; i < 10000; i++) {
            sum += i;
        }
    }
}


class SynchronizedThread extends Thread {
    private final SharedPrinter printer;

    public SynchronizedThread(SharedPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        printer.doSomething();
    }
}


