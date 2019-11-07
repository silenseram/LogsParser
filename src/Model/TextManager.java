package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextManager {

    //НЕНАВИЖУ РЕГУЛЯРКИ
    private String privateMessaeStringPattern = "\\[\\d{1,2}:\\d{1,2}:\\d{1,2}\\]\\s.{1,32}\\sissued server command:\\s/(m|msg|t|tell|r)\\s.{1,32}\\s";
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

    public List<String> getNeedStrings(boolean isLocalEnabled, boolean isGlobalEnabled, boolean isPrivateEnabled){
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (String current = reader.readLine(); current != null; current = reader
                    .readLine()) {
                if (isPrivateEnabled && isPrivateMessage(current)) {
                    result.add(current);
                } else if (isLocalEnabled && islocalChatMessage(current)){
                    result.add(current);
                } else if (isGlobalEnabled && isGlobalChatMessage(current)){
                    result.add(current);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<String> getAllLines(){
        List<String> result = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (String current = reader.readLine(); current != null; current = reader.readLine()) {
                result.add(current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
