package Exercise6;

class MyThread extends Thread {
    private volatile boolean active = true;

    @Override
    public void run(){
        while(active){
            System.out.println("Thread is Running..");
            try{
                Thread.sleep(300);
            }catch(Exception e){
                System.out.println("Thread was interrupted");
            }
        }
    }

    public void shutdown(){
        active = false;
    }
}



