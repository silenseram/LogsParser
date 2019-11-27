package Model.messages;

import Model.ConfigManager;

public class PrivateMessage {
    public static String getMessage(String rawMessage, boolean showTime, TimezoneManager timezoneManager){
        String words[] = rawMessage.split(" ");

        String time = "";
        if (showTime){
            time = words[0];
            time = timezoneManager.getChangedTime(time);
        }

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
            return time + " " + sender + " " + text;
        }
    }

}