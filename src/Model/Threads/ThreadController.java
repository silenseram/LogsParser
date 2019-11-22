package Model.Threads;

import java.util.ArrayList;
import java.util.List;

public class ThreadController {

    public static void killThread(String threadName){
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(RealtimeOutputUpdater.threadName)) t.stop();
        }
    }

    public static boolean isThreadExist(String threadName){
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(RealtimeOutputUpdater.threadName))
                return true;
        }
        return false;
    }

    public static List<String> getAllThreadNames() {
        List<String> threads = new ArrayList<>();
        for (Thread t : Thread.getAllStackTraces().keySet()){
            threads.add(t.getName());
        }
        return threads;
    }
}
