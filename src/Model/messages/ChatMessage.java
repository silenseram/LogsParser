package Model.messages;

public class ChatMessage {

    public static String getMessage(String rawMsg, boolean showTime){
        String words[] = rawMsg.split(" ");
        String text = "";
        String time = "";
        if (showTime){
            time = words[0] + " ";
        }

        for (int i = 1; i < words.length; i++){
            text += words[i] + " ";
        }

        return time + text;
    }

}
