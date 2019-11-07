package Model;

import View.AllertWindow;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileManager{

    private String url;
    private String fileName;

    public static File downloadFile(String url, String fileName){
        String filepath = getFilePath(fileName);
        File f = new File(filepath);
        try {

            URL website = new URL(url);
            //System.out.println(url + " <---> " + filepath);

            ReadableByteChannel channel = Channels.newChannel(website.openStream());
            FileOutputStream stream = new FileOutputStream(filepath);

            stream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);

            //System.out.println("Download successfull");

            //idk
            channel.close();
            stream.close();

            return f;
        } catch (Exception e) {
            //AllertWindow.dispplay("Ошибка!", "Невозможно загрузить файл");
            e.printStackTrace();
            return null;
        }
    }

    public static String getFilePath(String fileName){
        return "C://Users//" + System.getProperty("user.name") + "//Desktop//logs//" + fileName + ".txt";
    }

}
