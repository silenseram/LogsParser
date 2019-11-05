package classes;

public class LinkManager {
    private String serverName;
    DateManager dateManager;

    public LinkManager(String serverName, DateManager dateManager) {
        this.serverName = serverName;
        this.dateManager = dateManager;
    }

    public String getUrl(){
        return "https://logs." + getServerNumber() + ".mcskill.ru/p/" + serverName + "_Public_Logs/" + dateManager.getStringDate() + ".txt";

    }

    private String getServerNumber(){
        switch (serverName){
            case "EasyTech": return "s1";
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

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
