package Model;

import java.time.LocalDate;

public class TextUtils {
    public static String getStringDate(LocalDate localDate){
        String words[] = localDate.toString().split("-");
        return words[2] + "-" + words[1] + "-" + words[0];
    }
}
