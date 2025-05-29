package Week10;

public class ProducerConsumerDemo {

    static class SharedData {
        private boolean dataReady = false;
        private String data;

        // Producer method
        public synchronized void produce() {
            try {
                System.out.println("Producer: Preparing data...");
                Thread.sleep(1000); // Simulate time to produce data
                data = "Hello from producer";
                dataReady = true;
                System.out.println("Producer: Data is ready.");
                notifyAll(); // Notify all waiting consumers
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Consumer method
        public synchronized void consume() {
            try {
                while (!dataReady) {
                    System.out.println("Consumer: Waiting for data...");
                    wait(); // Release lock and wait to be notified
                }
                System.out.println("Consumer: Received -> " + data);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Example usage
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        Thread producer = new Thread(sharedData::produce);
        Thread consumer1 = new Thread(sharedData::consume);
        Thread consumer2 = new Thread(sharedData::consume); // Optional: extra consumer

        consumer1.start();
        consumer2.start();
        producer.start();
    }
}
