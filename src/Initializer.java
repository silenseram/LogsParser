import java.io.*;

public class Initializer {

    public static void init() throws IOException {
        createDir("C:\\Users\\" + System.getProperty("user.name")  + "\\LogsParser");
        createDir("C:\\Users\\" + System.getProperty("user.name")  + "\\LogsParser\\txtlogs");
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
        if (!mainCfg.exists()){
            mainCfg.createNewFile();
            FileWriter fw = new FileWriter(mainCfg.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("server=EasyTech");
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
