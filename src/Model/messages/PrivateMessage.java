package Model.messages;

public class PrivateMessage extends LogRecord {

    private String sender;
    private String reciever;
    private String text;
    private String rawMessage;
    private String type;

    public PrivateMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    public static String getMessageWithTime(String rawMessage){
        String words[] = rawMessage.split(" ");

        String time = words[0];

        String type = words[5];
        String text = "";
        String sender = "";
        String reciever = "";

        if (!type.equals("/r")) {
            sender = words[1];
            reciever = words[6];
            for (int i = 7; i < words.length; i++){
                text += words[i] + " ";
            }
            return time + " " + sender + " -> " + reciever + ": " + text;
        } else {
            sender = words[1];
            for (int i = 5; i < words.length; i++){
                text += words[i] + " ";
            }
            return time + " " + sender + text;
        }
    }
    public static String getMessage(String rawMessage){
        String words[] = rawMessage.split(" ");

        String time = words[0];

        String type = words[5];
        String text = "";
        String sender = "";
        String reciever = "";

        if (!type.equals("/r")) {
            sender = words[1];
            reciever = words[6];
            for (int i = 7; i < words.length; i++){
                text += words[i] + " ";
            }
            return sender + " -> " + reciever + ": " + text;
        } else {
            sender = words[1];
            for (int i = 5; i < words.length; i++){
                text += words[i] + " ";
            }
            return sender + " " + text;
        }
    }

}
