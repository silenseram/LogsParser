package Model.TextUpdaters.Threads;

import javafx.scene.control.ComboBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerListUpdater {

    private ComboBox comboBox;
    private String filepath;
    public ServerListUpdater(ComboBox comboBox, String filepath) {
        this.comboBox = comboBox;
        this.filepath = filepath;
    }

    public List<String> getData() {
        File f = new File(filepath);
        List<String> serverList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));

            for (String current = reader.readLine(); current != null; current = reader.readLine()) {
                String words[] = current.split("=");
                String serverName = words[0];
                String serverNameInLogs = words[1];
                serverList.add(serverName);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverList;
    }
}
