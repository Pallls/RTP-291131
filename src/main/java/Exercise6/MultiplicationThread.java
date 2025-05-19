package Exercise6;

public class MultiplicationThread implements Runnable {
    private int numbers;


    public MultiplicationThread(int numbers) {
        this.numbers = numbers;
    }

    public void run() {

        for(int i=0;i<3;i++){
            int results = numbers * i;
            System.out.println(Thread.currentThread().getName() + " : " + numbers + " * " + i + " = " + results);
            try{
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    public static void main (String[] args){
        Thread t1 = new Thread(new MultiplicationThread(1), "Thread-0");
        Thread t2 = new Thread(new MultiplicationThread(2), "Thread-1");
        Thread t3 = new Thread(new MultiplicationThread(3), "Thread-2");

        t1.start();
        t2.start();
        t3.start();
    }

}