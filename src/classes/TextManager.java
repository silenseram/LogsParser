package classes;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextManager {

    //НЕНАВИЖУ РЕГУЛЯРКИ
    private String strPattern = "\\[\\d{1,2}:\\d{1,2}:\\d{1,2}\\]\\s.{1,32}\\sissued server command:\\s/(m|msg|t|tell)\\s.{1,32}\\s";
    private Pattern pattern;
    private Matcher matcher;
    private File file;

    public TextManager(String filePath){
        file = new File(filePath);
        pattern = Pattern.compile(strPattern);
    }


    //является ли строка сообщением
    public boolean isMessage(String command){  return pattern.matcher(command).lookingAt();  }

    public void print(){
        for (String i : getAllLines()){
            if (isMessage(i))
                System.out.println(i);
        }
    }

    public List<String> getMessages(){
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (String current = reader.readLine(); current != null; current = reader
                    .readLine()) {
                if (isMessage(current)) {
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
