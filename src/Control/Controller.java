package Control;

import Model.*;
import View.LogDisplayParams;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    @FXML
    private void setOnScroll(ActionEvent event){
        System.out.println(1);
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
    private void onMouseExited(MouseEvent r){
        textArea.setScrollTop(Double.MAX_VALUE); //DOESNT WORK, TODO
    }

    @FXML
    public void click(ActionEvent event) throws InterruptedException {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(RealtimeUpdater.threadName)) t.stop();
        }

        setDisplayParams();

        textArea.setScrollTop(Double.MAX_VALUE);
        LocalDate date = LocalDate.now();
        MCLogs logs = new MCLogs(date, displayParams);
        RealtimeUpdater realtimeUpdater = new RealtimeUpdater(logs, textArea, Thread.currentThread().getName(), showTime.isSelected());

        Thread thread = new Thread(realtimeUpdater);
        thread.setName(RealtimeUpdater.threadName);
        thread.start();
    }

    @FXML
    private TextArea textArea;

    @FXML
    public void showTimeChange(ActionEvent event){
        //click(null);
    } //TODO

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
}
