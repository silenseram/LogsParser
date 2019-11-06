package Control;

import Model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class Controller {

    @FXML
    private void setOnScroll(ActionEvent event){
        System.out.println(1);
    }

    @FXML
    private Button btn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private CheckBox showTime;

    @FXML
    private void onMouseExited(MouseEvent r){
        textArea.setScrollTop(Double.MAX_VALUE); //DOESNT WORK, TODO
    }

    @FXML
    private void click(ActionEvent event) throws InterruptedException {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(RealtimeUpdater.threadName)) t.stop();
        }
        textArea.setScrollTop(Double.MAX_VALUE);
        LocalDate date = LocalDate.now();
        MCLogs logs = new MCLogs(date);

        RealtimeUpdater realtimeUpdater = new RealtimeUpdater(logs, textArea, Thread.currentThread().getName());

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
}
