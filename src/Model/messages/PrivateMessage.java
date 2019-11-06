package Model.messages;

public class PrivateMessage {

    private String sender;
    private String reciever;
    private String text;
    private String rawMessage;
    private String type;

    int hours, minutes, seconds;

    public PrivateMessage(String rawMessage) {
        this.rawMessage = rawMessage;

        String words[] = rawMessage.split("\\s");

        String temp = words[0];
        temp = temp.replace("[", "");
        temp = temp.replace("]", "");

        String time[] = temp.split(":");

        hours = Integer.parseInt(time[0]);
        minutes = Integer.parseInt(time[1]);
        seconds = Integer.parseInt(time[2]);

        type = words[5];
        text = "";
        if (type != "r") {
            sender = words[1];
            reciever = words[6];
                for (int i = 7; i < words.length; i++){
                    text += words[i] + " ";
                }
        }
    }

    public String getAllMessage(){
        return sender + " -> " + reciever + ": " + text;
    }

    public String getAllMessageWithTime(){
        return getStringTime() + " " + sender + " -> " + reciever + ": " + text;
    }

    public String getSender() {
        return sender;
    }

    public String getReciever() {
        return reciever;
    }

    public String getText() {
        return text;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getStringTime(){
        String h, m, s;
        h = String.valueOf(hours);
        m = String.valueOf(minutes);
        s = String.valueOf(seconds);

        if (hours < 10){
            h = "0" + h;
        }
        if (minutes < 10){
            m = "0" + m;
        }
        if (seconds < 10){
            s = "0" + s;
        }

        return "[" + h + ":" + m + ":" + s + "]";
    }
}
