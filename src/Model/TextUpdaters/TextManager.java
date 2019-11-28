package Model.TextUpdaters;

import Model.ConfigManager;
import Model.messages.ChatMessage;
import Model.messages.PrivateMessage;
import Model.messages.TimezoneManager;
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
    private TimezoneManager timezoneManager;

    public TextManager(String filePath){
        file = new File(filePath);
        privateMessagePattern = Pattern.compile(privateMessaeStringPattern);
        localChatPattern = Pattern.compile(localChatStringPattern);
        globalChatPattern = Pattern.compile(globalChatStringPattern);
        this.timezoneManager = new TimezoneManager(new ConfigManager("config").getServerName());
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
                new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            for (String current = reader.readLine(); current != null; current = reader
                    .readLine()) {
                if (params.isShowPrivateMessages() && isPrivateMessage(current)) {
                    result.add(PrivateMessage.getMessage(current, params.isShowTime(), timezoneManager));
                } else if (params.isShowLocalMessages() && islocalChatMessage(current)){
                    result.add(ChatMessage.getMessage(current, params.isShowTime(), timezoneManager));
                } else if (params.isShowGlobalMessages() && isGlobalChatMessage(current)){
                    result.add(ChatMessage.getMessage(current, params.isShowTime(), timezoneManager));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int size = result.size();
        if (size > 100){
            List<String> res = new ArrayList<>();
            for (int i = size - 100; i < size; i++){
                res.add(result.get(i));
            }
            return res;
        }

        return result;
    }

    public List<String> getAllLogs(){
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            for (String current = reader.readLine(); current != null; current = reader
                    .readLine()) {
                result.add(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<String> getCuttedLogs(){
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            for (String current = reader.readLine(); current != null; current = reader
                    .readLine()) {
                result.add(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int size = result.size();
        if (size > 100){
            List<String> res = new ArrayList<>();
            for (int i = size - 100; i < size; i++){
                res.add(result.get(i));
            }
            return res;
        }

        return result;
    }

    public static boolean findByPattern(String string, String key){
        Pattern pattern = Pattern.compile(".*" + key + ".*");
        return pattern.matcher(string).lookingAt();
    }

}
