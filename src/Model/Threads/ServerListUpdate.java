package Model.Threads;

import javafx.scene.control.ComboBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerListUpdate implements Runnable {

    private ComboBox comboBox;
    private String filepath;
    public final static String threadName = "ServerListUpdater";

    public ServerListUpdate(ComboBox comboBox, String filepath) {
        this.comboBox = comboBox;
        this.filepath = filepath;
    }

    @Override
    public void run() {
        File f = new File(filepath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));

            List<String> serverList = new ArrayList<>();
            for(String current = reader.readLine(); current != null; current = reader.readLine()){
                String words[] = current.split("=");
                String serverName = words[0];
                String serverNameInLogs = words[1];
                serverList.add(serverName);

            }

            comboBox.getItems().setAll(serverList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
