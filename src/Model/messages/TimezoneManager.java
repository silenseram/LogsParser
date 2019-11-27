package Model.messages;

import Model.ConfigManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimezoneManager {
    //Main timezone - Moscow

    ConfigManager timeOffsets;
    private String serverName;

    public TimezoneManager(String serverName) {
        this.serverName = serverName;
        this.timeOffsets = new ConfigManager("timezone");
    }

    private int getTimeOffsetByServer(String serverName){
        //System.out.println(timeOffsets.getProperty(serverName) + " servername=" + serverName);
        return Integer.parseInt(timeOffsets.getProperty(serverName));
    }

    public String getChangedTime(String t){
        t = t.substring(1, 9);
        LocalTime localTime = LocalTime.parse(t);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDate.now(), localTime, ZoneId.systemDefault());

        int offset = getTimeOffsetByServer(serverName);
        zonedDateTime = zonedDateTime.plusHours(offset);

//        System.out.println("changed time is" + zonedDateTime.getHour() +
//                ":" + zonedDateTime.getMinute() + ":" + zonedDateTime.getSecond());

        int hours, mins, seconds;
        String h, m, s;

        hours = zonedDateTime.getHour();
        mins = zonedDateTime.getMinute();
        seconds = zonedDateTime.getSecond();

        if (hours < 10){
            h = "0" + hours;
        }
        else {
            h = Integer.toString(hours);
        }
        if (mins < 10){
            m = "0" + mins;
        }
        else{
            m = Integer.toString(mins);
        }
        if (seconds < 10){
            s = "0" + seconds;
        }else {
            s = Integer.toString(seconds);
        }

        return  "[" + h + ":" + m + ":" + s + "]";
    }

    public LocalDate getRealLocalDate(LocalDate localDate){
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, LocalTime.now(), ZoneId.systemDefault());
        int offset = getTimeOffsetByServer(serverName);
        zonedDateTime.minusHours(offset);

        LocalDate res = LocalDate.of(zonedDateTime.getYear(),  zonedDateTime.getMonthValue(), zonedDateTime.getDayOfMonth());
        return res;
    }
}
