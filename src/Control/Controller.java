package Control;

import Model.*;
import Model.Threads.RealtimeFileUpdater;
import Model.Threads.RealtimeOutputUpdater;
import Model.Threads.ServerListUpdate;
import Model.Threads.ThreadController;
import View.LogDisplayParams;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.time.LocalDate;

public class Controller {

    private LogDisplayParams displayParams;

    private void setDisplayParams(){
        if (displayParams == null){
            displayParams = new LogDisplayParams(showTime, privateMessageCheckBox,
                    globalCheckBox, localCheckBox);
        }
    }

    private void setFileUpdater(){

        if (!ThreadController.isThreadExist(RealtimeFileUpdater.threadName)) {
            ConfigManager configManager = new ConfigManager("config.properties");

            LocalDate date = LocalDate.now();
            LinkManager linkManager = new LinkManager(configManager.getServerName(), date);


            RealtimeFileUpdater fileUpdater = new RealtimeFileUpdater(linkManager, Thread.currentThread().getName(), LocalDate.now());
            Thread thread = new Thread(fileUpdater);
            thread.setName(RealtimeFileUpdater.threadName);

            thread.start();
        }
    }

    private void setTextUpdater(){
        if (ThreadController.isThreadExist(RealtimeOutputUpdater.threadName))
            return;

        textArea.setScrollTop(Double.MAX_VALUE);
        LocalDate date = LocalDate.now();
        MCLogs logs = new MCLogs(date, displayParams);
        RealtimeOutputUpdater realtimeOutputUpdater = new RealtimeOutputUpdater(logs, textArea, Thread.currentThread().getName(),
                showTime.isSelected());

        Thread thread = new Thread(realtimeOutputUpdater);
        thread.setName(RealtimeOutputUpdater.threadName);
        thread.start();
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
    private ComboBox comboBox;


    @FXML
    public void click(ActionEvent event) throws InterruptedException {
        setDisplayParams();
        setFileUpdater();
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
    private void onShowComboBox(Event a){
        System.out.println("1");
        if (ThreadController.isThreadExist(ServerListUpdate.threadName))
            return;
        comboBox.getItems().add(1);

        ServerListUpdate updater = new ServerListUpdate(comboBox, FileManager.getLogFilePath("config.properties"));
        Thread thread = new Thread(updater);
        thread.start();
    }
}
