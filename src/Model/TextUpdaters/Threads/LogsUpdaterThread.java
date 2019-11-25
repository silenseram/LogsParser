package Model.TextUpdaters.Threads;

import Model.ConfigManager;
import Model.FileUpdater;
import Model.LinkManager;
import Model.MCLogs;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LogsUpdaterThread implements Runnable {

    public static final String threadName = "LogsHandler";
    private LocalDate localDate;
    private ConfigManager configManager;
    private String mainThreadName;
    private MCLogs mcLogs;
    private FileUpdater fileUpdater;
    private TextArea textArea;
    private List<String> logsIn;
    private boolean isStartWrited = false;

    public LogsUpdaterThread(TextArea textArea, LocalDate localDate, ConfigManager configManager, String mainThreadName, MCLogs mcLogs) {
        this.textArea = textArea;
        this.localDate = localDate;
        this.configManager = configManager;
        this.mainThreadName = mainThreadName;
        this.mcLogs = mcLogs;
        this.fileUpdater = new FileUpdater(new LinkManager(configManager.getServerName(), localDate), localDate);
        logsIn = new ArrayList<>();
    }

    @Override
    public void run() {
        fileUpdater.updateLogFile();
        writeLogs();
        String text = "";
        for (int i = 0; i < logsIn.size(); i++) {
            text += logsIn.get(i) + "\n";
        }
        textArea.setText(text);
        textArea.setScrollTop(Double.MAX_VALUE);

        Thread.currentThread().setName(threadName);
        text = "";

        while (true){


            boolean isMainThreadExist = false;
            for (Thread t : Thread.getAllStackTraces().keySet()) {          //if main thread is end then over this thread
                if (t.getName().equals(mainThreadName)) {
                    isMainThreadExist = true;
                }
            }
            if (!isMainThreadExist)
                return;

            fileUpdater.updateLogFile();

            List<String> newMessages = null;
            try {
                newMessages = mcLogs.getAllLogs();

                if (newMessages != null && newMessages.size() != logsIn.size()){
                    for(int i = logsIn.size(); i < newMessages.size(); i++){
                        textArea.appendText(newMessages.get(i) + "\n");
                        logsIn.add(newMessages.get(i));
                    }
                    textArea.setScrollTop(Double.MAX_VALUE);
                }

            } catch (Exception e){
                e.printStackTrace();
            }

//            for (String i : messages){
//                text += i + "\n";
//            }

//            if (textArea.getText() != text && !text.equals(""))
//                textArea.setText(text);
//            textArea.setScrollTop(Double.MAX_VALUE);
            text = "";
            Thread.yield();

        }

    }

    private void writeLogs(){
        for (String i : mcLogs.getAllLogs()){
            logsIn.add(i);
        }
    }
}
