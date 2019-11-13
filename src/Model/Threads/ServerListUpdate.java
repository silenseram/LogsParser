package Model.Threads;

import javafx.scene.control.ComboBox;

import java.io.*;

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

            for(String current = reader.readLine(); current != null; current = reader.readLine()){
                String words[] = current.split("=");
                String serverName = words[0];
                String serverNameInLogs = words[1];

                comboBox.getItems().add(serverName);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
