package Model;

import java.time.LocalDate;

public class LinkManager {
    private String serverName;
    private LocalDate localDate;

    public LinkManager(String serverName, LocalDate date) {
        //System.out.println(serverName);
        this.serverName = serverName;
        this.localDate = date;
    }

    public String getUrl(){
        String date = TextUtils.getStringDate(localDate);

        if (getServerNumber().equals("s1")) {
            return "https://logs." + getServerNumber() + ".mcskill.ru/p/" + serverName + "_Public_Logs/" + date + ".txt";
        }
        if (getServerNumber().equals("s4")) {
            return "http://logs." + getServerNumber() + ".mcskill.ru/" + serverName + "_Public_Logs/" + date + ".txt";
        }
        if (getServerNumber().equals("s8")) {
            return "https://logs." + getServerNumber() + ".mcskill.ru/" + serverName + "/" + date + ".txt";
        }
        if (getServerNumber().equals("s7")) {
            return "https://logs." + getServerNumber() + ".mcskill.ru/" + serverName + "_public_logs/" + date + ".txt";
        }
        if (getServerName().equals("IventServer2")) {
            return "http://logs." + getServerNumber() + ".mcskill.ru/" + serverName + "_public_logs/" + date + ".txt";
        }

        if (getServerName().equals("Hitech112_Public_Logs")){
            return "http://logs.s4.mcskill.ru/Hitech112_Public_Logs/" + date + ".txt";
        }

        return "https://logs." + getServerNumber() + ".mcskill.ru/" + serverName + "_Public_Logs/" + date + ".txt";
    }

    private String getServerNumber(){
        switch (serverName){
            case "EasyTech":
                return "s1";
            case "TechnoRPG":
                return "s1";
            case "Galaxycraft":
                return "s3";
            case "Lazorcraft":
                return "s3";
            case "MagicRPG":
                return "s3";
            case "GregTech":
                return "s4";
            case "Hitech112":
                return "s4";
            case "IventServer2":
                return "s6";
            case "TechnomagicRPG":
                return "s6";
            case "Magiccraft":
                return "s6";
            case "Hitechcraft":
                return "s7";
            case "Hitechcraft2":
                return "s7";
            case "Hitechcraft3":
                return "s7";
            case "Islandcraft":
                return "s8";
            //case "MagicRPG": return "s8"; break;
            case "Technomagic":
                return "s9";
            case "Technomagic2":
                return "s9";
            default: return "0";
        }
    }






    public String getServerName() {
        return serverName;
    }
    public String getLogsServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
