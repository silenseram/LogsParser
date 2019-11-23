package Control;

import Model.*;
import Model.Threads.RealtimeOutputUpdater;
import Model.Threads.ServerListUpdater;
import Model.Threads.ThreadController;
import View.LogDisplayParams;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class Controller {

    public Controller(){
        configManager = new ConfigManager("config");
    }

    private LogDisplayParams displayParams;
    public ConfigManager configManager;
    private Thread updaterThread;
    private boolean isServerListLoaded = false;

    private void setDisplayParams(){
        if (displayParams == null){
            displayParams = new LogDisplayParams(showTime, privateMessageCheckBox,
                    globalCheckBox, localCheckBox);
        }
    }

    private void setTextUpdater(){
        if (updaterThread != null){
            return;
        }
        textArea.setScrollTop(Double.MAX_VALUE);
        LocalDate date = LocalDate.now();
        MCLogs logs = new MCLogs(date, displayParams);
        RealtimeOutputUpdater realtimeOutputUpdater = new RealtimeOutputUpdater(logs, textArea, Thread.currentThread().getName(),
                date, configManager);

        updaterThread = new Thread(realtimeOutputUpdater);
        updaterThread.start();
    }

    @FXML
    private Button btn;
    @FXML
    private CheckBox localCheckBox;
    @FXML
    private CheckBox globalCheckBox;
    @FXML
    private CheckBox privateMessageCheckBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private CheckBox showTime;
    @FXML
    private ComboBox<String> comboBox;
    String selection = "HiTechClassic";

    @FXML
    public void click(ActionEvent event) throws InterruptedException {
        setDisplayParams();
        setTextUpdater();
    }

    @FXML
    private TextArea textArea;

    @FXML
    private void onShowTimeAction(ActionEvent a) throws InterruptedException {
        click(null);
    }

    @FXML
    private void onPrivateMessageCheckBoxAction(ActionEvent a) throws InterruptedException{
        click(null);
    }
    @FXML
    private void onGlobalCheckBoxAction(ActionEvent a) throws InterruptedException{
        click(null);
    }
    @FXML
    private void onLocalCheckBoxAction(ActionEvent a) throws InterruptedException{
        click(null);
    }

    @FXML
    private void onChoiceBoxTouch(Event a){
        System.out.println(1);
    }

    @FXML
    private void onShowComboBox(Event a){
        if (!isServerListLoaded) {
            ServerListUpdater updater = new ServerListUpdater(comboBox, FileManager.getConfigFilePath("servers"));
            comboBox.getItems().setAll(updater.getData());
            isServerListLoaded = true;
        }
        selection = comboBox.getValue();
    }

    @FXML
    private void comboBoxOnAction(Event e) throws InterruptedException {
        ConfigManager servers = new ConfigManager("servers");
        configManager.setProperty("server", servers.getProperty(comboBox.getValue()));
        configManager.updateProperties();

        if (updaterThread != null) {
            updaterThread.stop();
            updaterThread = null;
            setTextUpdater();
        }

    }
}
