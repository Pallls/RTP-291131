package Exercise7;
import java.util.Random;

public class DeadlockFixed implements Runnable {
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();
    private final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        Thread myThread1 = new Thread(new DeadlockFixed(), "thread-1");
        Thread myThread2 = new Thread(new DeadlockFixed(), "thread-2");
        myThread1.start();
        myThread2.start();
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            boolean b = random.nextBoolean();
            if (b) {
                lockResourcesInOrder();
            } else {
                lockResourcesInOrder(); // Always lock in the same order to avoid deadlock
            }
        }
    }

    private void lockResourcesInOrder() {
        System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock resource 1.");
        synchronized (resource1) {
            System.out.println("[" + Thread.currentThread().getName() + "] Locked resource 1.");
            System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock resource 2.");
            synchronized (resource2) {
                System.out.println("[" + Thread.currentThread().getName() + "] Locked resource 2.");
            }
        }
    }
}

