package Model;

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
            System.out.println(url + " <---> " + filepath);

            ReadableByteChannel channel = Channels.newChannel(website.openStream());
            FileOutputStream stream = new FileOutputStream(filepath);

            stream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);

            System.out.println("Download successfull");

            //хз надо ли оно
            channel.close();
            stream.close();

            return f;
        } catch (Exception e) {
            System.out.println("Download was not successfull");
            return null;
        }
    }

    public static String getFilePath(String fileName){     //временно. в планах прикрутить конфиг
        return "C://Users//" + System.getProperty("user.name") + "//Desktop//logs//" + fileName + ".txt";
    }

}
