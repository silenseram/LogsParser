package Model.messages;

public class ChatMessage extends LogRecord {
    private String type;
    private String rawLog;


    public ChatMessage(String raw){
        this.rawLog = raw;
    }

    public String getAllLogWithTime(){
        return rawLog;
    }

    public String getAllLog(){
        String words[] = rawLog.split(" ");
        String result = "";

        for (int i = 1; i < result.length(); i++){
            result += words[i] + " ";
        }
        return result;
    }
}
