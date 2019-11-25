package Control;

import Model.ConfigManager;
import Model.FileManager;
import Model.MCLogs;
import Model.TextUpdaters.Threads.LogsUpdaterThread;
import Model.TextUpdaters.Threads.ServerListUpdater;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;

import java.time.LocalDate;

public class LogsController {

    @FXML
    public void initialize() throws InterruptedException {
        datePicker.setValue(LocalDate.now());
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
    private DatePicker datePicker;
    @FXML
    private ToggleButton toggleButton;

    public void onComboBoxShow(javafx.event.Event event) throws InterruptedException {
        if (!isServerListLoaded){
            ServerListUpdater updater = new ServerListUpdater(comboBox, FileManager.getConfigFilePath("servers"));
            comboBox.getItems().setAll(updater.getData());
            isServerListLoaded = true;
        }
    }

    @FXML
    public void onToggleButtonAction(Event event){
        if (toggleButton.isSelected()) {
            setTextUpdater();
        }else {
            updaterThread.stop();
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
            MCLogs logs = new MCLogs(datePicker.getValue());

            logsUpdaterThread = new LogsUpdaterThread(textArea, datePicker.getValue(), configManager,
                    Thread.currentThread().getName(), logs);
            updaterThread = new Thread(logsUpdaterThread);
            updaterThread.start();
        }
    }
}
