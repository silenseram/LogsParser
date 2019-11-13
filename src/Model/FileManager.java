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
        String filepath = getLogFilePath(fileName);
        String tempFile = getLogFilePath("1");

        File tf = new File(tempFile);
        File f = new File(filepath);

        try {

            URL website = new URL(url);
            Integer t = 1;
            ReadableByteChannel channel = Channels.newChannel(website.openStream());
            FileOutputStream stream = new FileOutputStream(tempFile);

            stream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
            swapName(tf, f);

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

    public static String getLogFilePath(String fileName){
        return "C://Users//" + System.getProperty("user.name") + "//LogsParser//txtlogs//" + fileName + ".txt";
    }

    public static String getConfigFilePath(String fileName){
        return "C://Users//" + System.getProperty("user.name") + "//LogsParser//config//" + fileName + ".config";
    }

    private static boolean swapName(File tmp1, File tmp2) {
        String path1 = tmp1.getAbsolutePath().substring(0, tmp1.getAbsolutePath().lastIndexOf(File.separator)+1);
        String fileName1 = tmp2.getName();
        File swapFile1 = new File(path1 + File.separator + fileName1);

        String path2 = tmp2.getAbsolutePath().substring(0, tmp2.getAbsolutePath().lastIndexOf(File.separator)+1);
        String fileName2 = tmp1.getName();
        File swapFile2 = new File(path2 + File.separator + fileName2);

        return (tmp1.renameTo(swapFile1) && tmp2.renameTo(swapFile2));

    }
}
