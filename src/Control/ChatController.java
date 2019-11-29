package Control;

import Model.*;
import Model.TextUpdaters.Threads.ChatUpdaterThread;
import Model.TextUpdaters.Threads.ServerListUpdater;
import View.LogDisplayParams;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

public class ChatController {

    @FXML
    public void initialize() throws InterruptedException, IOException {
        localCheckBox.setSelected(true);
        globalCheckBox.setSelected(true);
        privateMessageCheckBox.setSelected(true);
        showTime.setSelected(false);

        ServerListUpdater updater = new ServerListUpdater(comboBox, FileManager.getConfigFilePath("servers"));
        comboBox.getItems().setAll(updater.getData());
        isServerListLoaded = true;
        textArea.setFont(new Font(new ConfigManager("config").getProperty("font"), 14));

        slider.setValue(textArea.getFont().getSize());

        click();
    }

    public ChatController(){
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
        ChatUpdaterThread chatUpdaterThread = new ChatUpdaterThread(logs, textArea, Thread.currentThread().getName(),
                date, configManager);

        updaterThread = new Thread(chatUpdaterThread);
        updaterThread.start();
    }

    @FXML
    private CheckBox localCheckBox;
    @FXML
    private CheckBox globalCheckBox;
    @FXML
    private CheckBox privateMessageCheckBox;
    @FXML
    private CheckBox showTime;
    @FXML
    private ComboBox<String> comboBox;
    String selection = "HiTechClassic";

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
    public void click() throws InterruptedException {
        setDisplayParams();
        setTextUpdater();
    }

    @FXML
    private TextArea textArea;

    @FXML
    private void onShowTimeAction(ActionEvent a) throws InterruptedException {
        click();
    }

    @FXML
    private void onPrivateMessageCheckBoxAction(ActionEvent a) throws InterruptedException{
        click();
    }
    @FXML
    private void onGlobalCheckBoxAction(ActionEvent a) throws InterruptedException{
        click();
    }
    @FXML
    private void onLocalCheckBoxAction(ActionEvent a) throws InterruptedException{
        click();
    }

    @FXML
    private void onShowComboBox(Event a){
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
