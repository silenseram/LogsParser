package Model.Threads;

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
}
