package Week12;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SessionManager {
    private static final ConcurrentHashMap<String, Long> sessions = new ConcurrentHashMap<>();
    private static final long SESSION_TIMEOUT = 10_000; // 10 second

    public static void updateSession(String sessionId) {
        sessions.put(sessionId, System.currentTimeMillis());
        System.out.println("Updated session: " + sessionId);
    }

    public static void startSessionCleanUpTask(){
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            long now = System.currentTimeMillis();
            for(String sessionId : sessions.keySet()) {
                long lastActive = sessions.get(sessionId);
                if(now - lastActive > SESSION_TIMEOUT){
                    sessions.remove(sessionId);
                    System.out.println("Removed expired session :" + sessionId);
                }
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    public static void printSessions() {
        System.out.println("Active Sessions:");
        sessions.forEach((id, time) -> System.out.println(" - " + id + " (Last Active: " + time + ")"));
    }

    public static void main(String [] args) throws InterruptedException {
        startSessionCleanUpTask();

        Thread user1 = new Thread(() -> {

            for (int i = 0; i < 5; i++) {

                updateSession("user1");

                try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

            }

        });

        Thread user2 = new Thread(() -> {
            for (int i = 0; i<3; i++){
                updateSession("user2");
                try{ Thread.sleep(4000); } catch (InterruptedException ignored) {}
            }
        });

        user1.start();
        user2.start();

        user1.join();
        user2.join();

        Thread.sleep(12000);
        printSessions();
    }
}
