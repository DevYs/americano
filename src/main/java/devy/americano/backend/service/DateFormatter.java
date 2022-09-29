package devy.americano.backend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public static LocalDateTime format(String localDateTime) {
        int today = LocalDateTime.now().getDayOfMonth();
        String ldt = localDateTime.replace("<![CDATA[ ", "")
                .replace("<![CDATA[", "")
                .replace(" ]]>", "")
                .replace("]]>", "")
                .replace("  ", " ")
                .replace("KST", "GMT")
                .replace(".", "-")
                .replace("+09:00", "")
                .replace("Mon," + today, "Mon, " + today)
                .replace("Tue," + today, "Tue, " + today)
                .replace("Wed," + today, "Wed, " + today)
                .replace("Thu," + today, "Thu, " + today)
                .replace("Fri," + today, "Fri, " + today)
                .replace("Sat," + today, "Sat, " + today)
                .replace("Sun," + today, "Sun, " + today)
                ;

        try {
            return LocalDateTime.parse(ldt, DateTimeFormatter.RFC_1123_DATE_TIME);
        } catch(Exception e) {}

        try {
            return LocalDateTime.parse(ldt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch(Exception e) {}

        try {
            return LocalDateTime.parse(ldt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ"));
        } catch(Exception e) {}

        try {
            return LocalDate.parse(ldt, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
        } catch(Exception e) {}

        try {
            return LocalDateTime.parse(ldt, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch(Exception e) {}

        try {
            return LocalDateTime.parse(ldt, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        } catch(Exception e) {}

        try {
            return LocalDateTime.parse(ldt, DateTimeFormatter.ISO_INSTANT);
        } catch(Exception e) {}

        return null;
    }

}
