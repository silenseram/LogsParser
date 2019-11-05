package GUI;

import GUI.fxml.AllertWindow;
import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

public class Controller {

    @FXML
    private Button btn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private CheckBox showTime;

    @FXML
    private void click(ActionEvent event) throws InterruptedException {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(RealtimeUpdater.threadName)) t.join();
        }

        LocalDate date = datePicker.getValue();
        LocalDate nowDate = LocalDate.now();

        if (date == null){
            date = nowDate;
        }

        if (date.isAfter(nowDate)){
            AllertWindow.dispplay("Ошибка!", "Выбранная дата меньше нынешней");
            return;
        }
        MCLogs logs = new MCLogs(date);

        RealtimeUpdater realtimeUpdater = new RealtimeUpdater(logs, textArea);

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
    public void setOnAction(ActionEvent e){
        //TODO sth
    }

    @FXML
    public void onDateChange(ActionEvent event){
        LocalDate date = datePicker.getValue();
        ConfigManager configManager = new ConfigManager();

        configManager.setDay(date.getDayOfMonth());
        configManager.setMonth(date.getMonthValue());
        configManager.setYear(date.getYear());
        configManager.updateProperty();
        System.out.println(configManager.getDay());
    }

}
