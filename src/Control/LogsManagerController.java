package Control;

import Model.ConfigManager;
import Model.FileManager;
import Model.MCLogs;
import Model.TextUpdaters.Threads.LogsAccessorThread;
import Model.TextUpdaters.Threads.ServerListUpdater;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

public class LogsManagerController {

    @FXML
    public void initialize(){
        datePicker.setValue(LocalDate.now());
        if (!isLoaded){
            isLoaded = true;
            ServerListUpdater serverListUpdater = new ServerListUpdater(comboBox, FileManager.getConfigFilePath("servers"));
            comboBox.getItems().setAll(serverListUpdater.getData());
        }
        textArea.setFont(new Font(new ConfigManager("config").getProperty("font"), 14));
        slider.setValue(textArea.getFont().getSize());

    }

    private LogsAccessorThread logsAccessorThread;
    private static ConfigManager configManager = new ConfigManager("config");
    private static ConfigManager servers = new ConfigManager("servers");
    private boolean isLoaded = false;

    @FXML
    public Button button;
    @FXML
    public TextField textField;
    @FXML
    public DatePicker datePicker;
    @FXML
    public TextArea textArea;
    @FXML
    public ComboBox<String> comboBox;
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

    @FXML
    private void click(Event event){
        MCLogs logs = new MCLogs(datePicker.getValue());

        logsAccessorThread = new LogsAccessorThread(textArea, datePicker.getValue(), configManager,
                Thread.currentThread().getName(), logs, textField.getText());
        Thread thread = new Thread(logsAccessorThread);
        thread.start();
    }

    @FXML
    private void onComboBoxAction(Event e){
        configManager.setProperty("logs_access", servers.getProperty(comboBox.getValue()));
    }
}
