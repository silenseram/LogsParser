package Model.messages;

public class PrivateMessage {

    private String sender;
    private String reciever;
    private String text;
    private String rawMessage;
    private String type;

    int hours, minutes, seconds;

    public PrivateMessage(String rawMessage) {}

    public static String getMessageWithTime(String rawMessage){
        String words[] = rawMessage.split("\\s");

        String time = words[0];

        String type = words[5];
        String text = "";
        String sender = "";
        String reciever = "";

        if (type != "r") {
            sender = words[1];
            reciever = words[6];
            for (int i = 7; i < words.length; i++){
                text += words[i] + " ";
            }
        } else if (type == "r"){
            sender = words[1];
            for (int i = 7; i < words.length; i++){
                text += words[i] + " ";
            }
        }
        if (type == "r"){
            return time + " " + sender + "/r " + text;
        } else{
            return time + " " + sender + "-> " + reciever + ": " + text;
        }
    }
    public static String getMessage(String rawMessage){
        String words[] = rawMessage.split("\\s");

        String time = words[0];

        String type = words[5];
        String text = "";
        String sender = "";
        String reciever = "";

        if (type != "r") {
            sender = words[1];
            reciever = words[6];
            for (int i = 7; i < words.length; i++){
                text += words[i] + " ";
            }
        } else if (type == "r"){
            sender = words[1];
            for (int i = 7; i < words.length; i++){
                text += words[i] + " ";
            }
        }
        if (type == "r"){
            return sender + "/r " + text;
        } else{
            return sender + "-> " + reciever + ": " + text;
        }
    }

}
