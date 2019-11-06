package Model;

public class DateManager {

    private int day;
    private int month;
    private int year;

    public DateManager(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getStringDate(){ // получаю строку вида "dd-mm-yyyy"

        String d, m, y;
        d = Integer.toString(day);
        m = Integer.toString(month);
        y = Integer.toString(year);

        if (d.length() == 1) // в названии файла нужна запись вида 01-10-2017. преобразую день и месяц к такому виду
            d = "0" + d;
        if (m.length() == 1)
            m = "0" + m;

        return d + "-" + m + "-" + y;
    }

    public String getFileName(){  return getStringDate() + ".txt";  }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
