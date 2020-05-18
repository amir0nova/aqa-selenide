//ЗДЕСЬ моя практика работы с датой. Не тестируется.
//SUT:      artifacts/app-card-delivery.jar

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AnyFormatDatePractice {
    public static void main(String[] args) {
        Date date;
        Calendar calendar;
        LocalDate localDate;
        LocalDateTime now;

        final DateFormat dateFormatPoints = new SimpleDateFormat("dd.MM.yyyy");
        // не видит разницы между дефисом (hyphen) и минусом (minus):
        final DateFormat dateFormatHyphen = new SimpleDateFormat("yyyy-MM-dd"); // дефис, доп-е (цифровые) клавиши
        final DateFormat dateFormatMinus = new SimpleDateFormat("yyyy-MM-dd"); // минус, символьные клавиши
        // не видит разницы между слешем (slash) на цифровой (figure) и символьной (symbol) клавиатуре:
        final DateFormat dateFormatSlashSymbols = new SimpleDateFormat("dd/MM/yyyy"); // слеш, символьные клавиши
        final DateFormat dateFormatSlashFigures = new SimpleDateFormat("dd/MM/yyyy"); // слеш, доп-е (цифровые) клавиши

        System.out.println();

        date = new Date();
        System.out.println("Дата (date, точки): " + dateFormatPoints.format(date));
        System.out.println("Дата (date, дефис): " + dateFormatHyphen.format(date));
        System.out.println("Дата (date, минус): " + dateFormatMinus.format(date));
        System.out.println("Дата (date, slash на символьной клавиатуре ): " + dateFormatSlashSymbols.format(date));
        System.out.println("Дата (date, slash на цифровой клавиатуре): " + dateFormatSlashFigures.format(date));

        System.out.println();

        System.out.println("Календарь (текущая дата, без отдельного создания переменной): " + dateFormatPoints.format(Calendar.getInstance().getTime()));
        calendar = Calendar.getInstance();
        System.out.println("Календарь (текущая дата): " + dateFormatPoints.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        System.out.println("Календарь, метод add, сдвиг -3 дней: " + dateFormatPoints.format(calendar.getTime())); //введен как Date date
        calendar.add(Calendar.MONTH, 2);
        System.out.println("Календарь, метод add, сдвиг 2 месяцев: " + dateFormatPoints.format(calendar.getTime()));
        calendar.roll(Calendar.MONTH, -8); //сдвиг только указанного параметра!!!
        System.out.println("Календарь, метод roll, сдвиг -8 месяцев: " + dateFormatPoints.format(calendar.getTime()));
        //System.out.println(dateFormatPoints.format(calendar)); //здесь падает: нужен метод getTime()!!!!!

        System.out.println();

        final DateTimeFormatter onlyDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        localDate = LocalDate.now();
        System.out.println("Дата в Java 8 (localDate): " + onlyDateFormatter.format(localDate));
        System.out.println("Дата в Java 8 (localDate), cдвиг +4 дня: " + onlyDateFormatter.format(localDate.plusDays(4)));
        System.out.println("Дата в Java 8 (localDate), cдвиг -3 месяца: " + onlyDateFormatter.format(localDate.minusMonths(3)));

        System.out.println();

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        now = LocalDateTime.now();
        System.out.println("Дата и время в Java 8 (localDateTime): " + dateTimeFormatter.format(now));
        System.out.println("Дата и время в Java 8 (localDateTime): " + DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm.ss").format(now));

        System.out.println();
        System.out.println("Проверка форматов вывода для Зад. 2.2:");
        // здесь удалила "private static":
        final DateTimeFormatter datePointsFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final DateTimeFormatter dateBlanksFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        final DateTimeFormatter dateWithoutBlanksFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        final DateTimeFormatter dateHyphensFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Ожидаемое dd.MM.yyyy: " + datePointsFormatter.format(localDate.plusDays(4))); // 29.04.2020
        System.out.println("Ожидаемое dd MM yyyy: " + dateBlanksFormatter.format(localDate.plusDays(4))); // 29 04 2020
        System.out.println("Ожидаемое ddMMyyyy: " + dateWithoutBlanksFormatter.format(localDate.plusDays(4))); // 29042020
        System.out.println("Ожидаемое yyyy-MM-dd: " + dateHyphensFormatter.format(localDate.plusDays(4))); // 2020-04-29
    }
}
