package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.Objects;

// BEGIN
public class App {
    public static long getCountOfFreeEmails(List<String> emailsList) {
        long counterYandex = emailsList.stream()
                .filter(mail -> Objects.nonNull(mail))
                .filter(mail -> mail.contains("@yandex.ru"))
                .count();
        long counterGmail = emailsList.stream()
                .filter(mail -> Objects.nonNull(mail))
                .filter(mail -> mail.contains("@gmail.com"))
                .count();
        long counterHotmail = emailsList.stream()
                .filter(mail -> Objects.nonNull(mail))
                .filter(mail -> mail.contains("@hotmail.com"))
                .count();
        long sum = counterYandex + counterGmail + counterHotmail;
        return sum;
    }
}
// END
