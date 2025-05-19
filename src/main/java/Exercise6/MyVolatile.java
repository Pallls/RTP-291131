package Exercise6;
import java.io.IOException;

public class MyVolatile {
    public static void main (String[] args) throws IOException{
        MyThread thread = new MyThread();
        thread.start();
        System.out.println("Press ENTER to stop thread");
        System.in.read();
        thread.shutdown();
        System.out.println("Thread Has been requested to stop.");
    }
}

