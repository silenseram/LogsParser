package Model;

import Model.messages.ChatMessage;
import Model.messages.PrivateMessage;
import View.LogDisplayParams;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextManager {

    //НЕНАВИЖУ РЕГУЛЯРКИ
    private String privateMessaeStringPattern = "\\[\\d{1,2}:\\d{1,2}:\\d{1,2}\\]\\s.{1,32}\\sissued server command:\\s/(m|msg|t|tell|r|pm)\\s";
    private String localChatStringPattern = "\\[\\d{1,2}:\\d{1,2}:\\d{1,2}\\]\\s\\[{1}L\\]{1}\\s.{1,32}\\s";
    private String globalChatStringPattern = "\\[\\d{1,2}:\\d{1,2}:\\d{1,2}\\]\\s\\[{1}G\\]{1}\\s.{1,32}\\s";

    private Pattern privateMessagePattern;
    private Pattern localChatPattern;
    private Pattern globalChatPattern;
    private Matcher matcher;
    private File file;

    public TextManager(String filePath){
        file = new File(filePath);
        privateMessagePattern = Pattern.compile(privateMessaeStringPattern);
        localChatPattern = Pattern.compile(localChatStringPattern);
        globalChatPattern = Pattern.compile(globalChatStringPattern);
    }

    public boolean isPrivateMessage(String command){
        return privateMessagePattern.matcher(command).lookingAt();
    }

    public boolean isGlobalChatMessage(String command){
        return globalChatPattern.matcher(command).lookingAt();
    }

    public boolean islocalChatMessage(String command){
        return localChatPattern.matcher(command).lookingAt();
    }

    public List<String> getSelectedLogs(LogDisplayParams params) throws FileNotFoundException{
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"));) {
            for (String current = reader.readLine(); current != null; current = reader
                    .readLine()) {
                String toAdd;
                if (params.isShowPrivateMessages() && isPrivateMessage(current)) {
                    result.add(PrivateMessage.getMessage(current, params.isShowTime()));
                } else if (params.isShowLocalMessages() && islocalChatMessage(current)){
                    result.add(ChatMessage.getMessage(current, params.isShowTime()));
                } else if (params.isShowGlobalMessages() && isGlobalChatMessage(current)){
                    result.add(ChatMessage.getMessage(current, params.isShowTime()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
