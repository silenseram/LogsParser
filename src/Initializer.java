import Model.FileManager;

import java.io.*;

public class Initializer {

    public static void init() throws IOException {
        createDir("C:\\Users\\" + System.getProperty("user.name")  + "\\LogsParser");
        createDir("C:\\Users\\" + System.getProperty("user.name")  + "\\LogsParser\\txtlogs");
        createDir("C:\\Users\\" + System.getProperty("user.name")  + "\\LogsParser\\txtlogs\\chat");
        createDir("C:\\Users\\" + System.getProperty("user.name")  + "\\LogsParser\\txtlogs\\logs");
        createDir("C:\\Users\\" + System.getProperty("user.name")  + "\\LogsParser\\txtlogs\\full_logs");
        createDir("C:\\Users\\" + System.getProperty("user.name")  + "\\LogsParser\\config");

        createConfigFiles();
    }

    private static void createDir(String path){
        File f = new File(path);
        if (!f.exists())
            f.mkdir();
    }

    private static void createConfigFiles() throws IOException {
        File mainCfg = new File("C:\\Users\\" + System.getProperty("user.name")  + "\\LogsParser\\config\\config.properties");
        File serverCfg = new File("C:\\Users\\" + System.getProperty("user.name")  + "\\LogsParser\\config\\servers.properties");
        File timezoneCfg = new File("C:\\Users\\" + System.getProperty("user.name")  + "\\LogsParser\\config\\timezone.properties");
        if (!mainCfg.exists()){
            mainCfg.createNewFile();
            FileWriter fw = new FileWriter(mainCfg.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("server=EasyTech\n" +
                    "logs_server=EasyTech\n" +
                    "theme=dark");
            bw.close();
        }
        if (!timezoneCfg.exists()){
            timezoneCfg.createNewFile();
            FileWriter fw = new FileWriter(timezoneCfg.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("EasyTech=0\n" +
                    "TechnoRPG=1\n" +
                    "Galaxycraft=1\n" +
                    "Lazorcraft=1\n" +
                    "MagicRPG=2\n" +
                    "GregTech=2\n" +
                    "Hitech112_2=2\n" +
                    "Hitech112_1=2\n" +
                    "IventServer2=3\n" +
                    "Magiccraft=3\n" +
                    "TechnomagicRPG=3\n" +
                    "Hitechcraft=3\n" +
                    "Hitechcraft2=3\n" +
                    "Hitechcraft3=3\n" +
                    "Islandcraft=2\n" +
                    "Technomagic=3\n" +
                    "Technomagic2=3");
            bw.close();
        }
        if(!serverCfg.exists()){
            serverCfg.createNewFile();
            FileWriter fw = new FileWriter(serverCfg.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("HiTechClassic=EasyTech\n" +
                    "TechnoRPG=TechnoRPG\n" +
                    "GalaxyCraft=Galaxycraft\n" +
                    "LazorCraft=Lazorcraft\n" +
                    "MagicRPG=MagicRPG\n" +
                    "GregTech=GregTech\n" +
                    "IventServer=IventServer2\n" +
                    "MagicCraft=Magiccraft\n" +
                    "TechnomagicRPG=TechnomagicRPG\n" +
                    "HitechCraft(1)=Hitechcraft\n" +
                    "HitechCraft(2)=Hitechcraft2\n" +
                    "HitechCraft(3)=Hitechcraft3\n" +
                    "TMSkyBlock=Islandcraft");
            bw.close();
        }
    }
}
