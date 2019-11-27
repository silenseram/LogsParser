package Model;

import View.AllertWindow;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.rmi.server.ExportException;

public class FileManager{

    private String url;
    private String fileName;

    public static String getLogFilePath(String fileName){
        return "C://Users//" + System.getProperty("user.name") + "//LogsParser//txtlogs//logs//" + fileName + ".txt";
    }
    public static String getLogChatFilePath(String fileName){
        return "C://Users//" + System.getProperty("user.name") + "//LogsParser//txtlogs//chat//" + fileName + ".txt";
    }

    public static String getConfigFilePath(String fileName){
        return "C://Users//" + System.getProperty("user.name") + "//LogsParser//config//" + fileName + ".properties";
    }

    public static String getLogsAccessorPath(String fileName){
        return "C://Users//" + System.getProperty("user.name") + "//LogsParser//txtlogs//full_logs//" + fileName + ".txt";
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

    public static File downloadChatFile(String url, String filename){
       File f = new File(getLogChatFilePath(filename));
       try {
           URL website = new URL(url);
           //System.out.println(url);
           ReadableByteChannel rbc = Channels.newChannel(website.openStream());
           FileOutputStream fos = new FileOutputStream(f);
           fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

           fos.close();
           rbc.close();
       } catch (Exception e){
           e.printStackTrace();
       }

       return f;
    }

    public static File downloadLogFile(String url, String filename){
        File f = new File(getLogsAccessorPath(filename));
        try {
            URL website = new URL(url);
            //System.out.println(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(f);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            fos.close();
            rbc.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return f;
    }

    public static File downloadLogsAccessFile(String url, String filename){
        File f = new File(getLogFilePath(filename));
        try {
            URL website = new URL(url);
            //System.out.println(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(f);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            fos.close();
            rbc.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return f;
    }
}
