package devy.americano.backend.service;

import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter {

    public static LocalDateTime format(String localDateTime) {
        String ldt = localDateTime.replace("<![CDATA[ ", "")
                .replace("<![CDATA[", "")
                .replace(" ]]>", "")
                .replace("]]>", "")
                .replace("  ", " ")
                .replace("KST", "GMT")
                .replace(".", "-")
                ;

        try {
            return LocalDateTime.parse(ldt, DateTimeFormatter.RFC_1123_DATE_TIME);
        } catch(Exception e) {}

        try {
            return LocalDate.parse(ldt, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            return LocalDateTime.parse(ldt, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch(Exception e) {}

        try {
            return LocalDateTime.parse(ldt, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        } catch(Exception e) {}

        try {
            return LocalDateTime.parse(ldt, DateTimeFormatter.ISO_INSTANT);
        } catch(Exception e) {}

        try {
            return LocalDateTime.parse(ldt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch(Exception e) {}

        try {
            return LocalDateTime.parse(ldt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ"));
        } catch(Exception e) {}

        return null;
    }

}
