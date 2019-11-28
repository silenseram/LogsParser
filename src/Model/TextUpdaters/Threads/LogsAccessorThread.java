package Model.TextUpdaters.Threads;

import Model.ConfigManager;
import Model.FileUpdater;
import Model.LinkManager;
import Model.MCLogs;
import Model.TextUpdaters.TextManager;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class LogsAccessorThread implements Runnable {
    public static final String threadName = "LogsAccessorHandler";
    private LocalDate localDate;
    private ConfigManager configManager;
    private String mainThreadName;
    private MCLogs mcLogs;
    private FileUpdater fileUpdater;
    private TextArea textArea;
    private String key;

    public LogsAccessorThread(TextArea textArea, LocalDate localDate, ConfigManager configManager,
                              String mainThreadName, MCLogs mcLogs, String key) {
        this.textArea = textArea;
        this.localDate = localDate;
        this.configManager = configManager;
        this.mainThreadName = mainThreadName;
        this.mcLogs = mcLogs;
        this.fileUpdater = new FileUpdater(new LinkManager(configManager.getLogsAccessorServerName(), localDate), localDate);
        this.key = key;
    }

    @Override
    public void run() {
        fileUpdater.updateLogsAccessFile();
        Thread.currentThread().setName(threadName);
        String text = "";

        try {
            List<String> newMessages = mcLogs.getAllLogs();

            if (!key.equals(""))
            for (String i : newMessages){
                if (TextManager.findByPattern(i, key)) {
                    text += i + "\n";
                }
            } else{
                for (String i : newMessages){
                    text += i + "\n";
                }
            }

            if (!text.equals(textArea.getText())) {
                textArea.setText(text);
            }

            textArea.setScrollTop(Double.MAX_VALUE);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
