package Control;

import Model.ConfigManager;
import Model.FileManager;
import Model.MCLogs;
import Model.TextUpdaters.Threads.LogsUpdaterThread;
import Model.TextUpdaters.Threads.ServerListUpdater;
import Model.TextUpdaters.Threads.ThreadController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

import java.time.LocalDate;

public class LogsController {

    @FXML
    public void initialize() throws InterruptedException {
        ServerListUpdater updater = new ServerListUpdater(comboBox, FileManager.getConfigFilePath("servers"));
        comboBox.getItems().setAll(updater.getData());
        isServerListLoaded = true;
        textArea.setFont(new Font(new ConfigManager("config").getProperty("font"), 14));
    }

    private boolean isServerListLoaded = false;
    private LogsUpdaterThread logsUpdaterThread;
    private static ConfigManager configManager = new ConfigManager("config");
    private Thread updaterThread;

    @FXML
    private TextArea textArea;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ToggleButton toggleButton;
    @FXML
    private Slider slider;

    public void onMousePressed(Event e){
        Font font = textArea.getFont();
        font = new Font(font.getName(), slider.getValue());
        textArea.setFont(font);
    }

    public void onMouseReleased(Event e) {
        Font font = textArea.getFont();
        font = new Font(font.getName(), slider.getValue());
        textArea.setFont(font);
    }

    public void onComboBoxShow(javafx.event.Event event) throws InterruptedException {
    }

    @FXML
    public void onToggleButtonAction(Event event) throws InterruptedException {
        if (toggleButton.isSelected()) {
            setTextUpdater();
        }else {
            updaterThread.stop();
            updaterThread = null;
            logsUpdaterThread = null;
        }
    }

    @FXML
    public void onComboBoxAction(Event event){
        ConfigManager servers = new ConfigManager("servers");
        configManager.setProperty("logs_server", servers.getProperty(comboBox.getValue()));
    }

    private void setTextUpdater(){
        if (logsUpdaterThread == null){
            MCLogs logs = new MCLogs(LocalDate.now());

            logsUpdaterThread = new LogsUpdaterThread(textArea, LocalDate.now(), configManager,
                    Thread.currentThread().getName(), logs);
            updaterThread = new Thread(logsUpdaterThread);
            updaterThread.start();
        }
    }
}
