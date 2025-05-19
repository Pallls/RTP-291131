package Exercise5;

public class TestSleep {
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            int threadID = i;

            Thread thread = new Thread(() -> printSequence(threadID));
            thread.start();

            try {
                Thread.sleep(120); // optional delay to manage output order a bit
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }

    private static void printSequence(int threadNum) {
        System.out.println("Thread Number: " + threadNum);
        System.out.println("ONE");
        System.out.println("TWO");
        System.out.println("THREE");
        System.out.println("xxxxxxxxxx");
    }
}
