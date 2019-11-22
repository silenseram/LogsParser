package Control;

import Model.*;
import Model.Threads.RealtimeOutputUpdater;
import Model.Threads.ServerListUpdate;
import Model.Threads.ThreadController;
import View.LogDisplayParams;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    private LogDisplayParams displayParams;

    private void setDisplayParams(){
        if (displayParams == null){
            displayParams = new LogDisplayParams(showTime, privateMessageCheckBox,
                    globalCheckBox, localCheckBox);
        }
    }

//    private void setFileUpdater(){
//
//        if (!ThreadController.isThreadExist(RealtimeFileUpdater.threadName)) {
//            ConfigManager configManager = new ConfigManager("config");
//
//            LocalDate date = LocalDate.now();
//            LinkManager linkManager = new LinkManager(configManager.getServerName(), date);
//
//
//            RealtimeFileUpdater fileUpdater = new RealtimeFileUpdater(linkManager, Thread.currentThread().getName(), LocalDate.now());
//            exec.submit(fileUpdater);
//        }
//    }

    private void setTextUpdater(){
        if (ThreadController.isThreadExist(RealtimeOutputUpdater.threadName))
            return;

        textArea.setScrollTop(Double.MAX_VALUE);
        LocalDate date = LocalDate.now();
        MCLogs logs = new MCLogs(date, displayParams);
        RealtimeOutputUpdater realtimeOutputUpdater = new RealtimeOutputUpdater(logs, textArea, Thread.currentThread().getName(),
                date, new ConfigManager("config"));

        exec.submit(realtimeOutputUpdater);
    }

    private ExecutorService exec = Executors.newFixedThreadPool(1);

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


    @FXML
    public void click(ActionEvent event) throws InterruptedException {
        setDisplayParams();
        //setFileUpdater();
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
//        ServerListUpdate updater = new ServerListUpdate(comboBox, FileManager.getConfigFilePath("servers"));
//        Thread thread = new Thread(updater);
//        thread.start();
    }

    @FXML
    private void comboBoxOnAction(Event e){
        if (comboBox.getValue() == null)
            comboBox.getSelectionModel().select(0);
        //updateServer(); TODO
    }
}
