package GUI;

import GUI.fxml.AllertWindow;
import classes.ConfigManager;
import classes.MCLogs;
import classes.PrivateMessage;
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
    private void click(ActionEvent event){

        LocalDate date = datePicker.getValue();
        LocalDate nowDate = LocalDate.now();
        MCLogs mcLogs;
        if (date == null){
            date = nowDate;
        }

        if (date.isAfter(nowDate)){
            AllertWindow.dispplay("Ошибка!", "Выбранная дата меньше нынешней");
            return;
        }

        mcLogs = new MCLogs(date.getDayOfMonth(), date.getMonthValue(), date.getYear());

        String allText = "";
        List<PrivateMessage> messages = mcLogs.getMessages();
        if (messages == null){
            AllertWindow.dispplay("Ошибка!", "Выбранная дата некорректна");
            return;
        }

        String msg = "";
        for (PrivateMessage i : messages){
            msg = "";
            if (showTime.isSelected())
                msg += i.getStringTime() + " ";
            msg += i.getSender() + " -> " + i.getReciever() + ": " + i.getText() + "\n";
            allText += msg;
        }
        textArea.setText(allText);
    }

    @FXML
    private TextArea textArea;

    @FXML
    public void showTimeChange(ActionEvent event){
        click(null);
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
