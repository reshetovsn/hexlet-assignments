package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> people) {
        List<String> oldestMen = people.stream()
                .filter(man -> man.get("gender").equals("male"))
                .map(man -> man.get("birthday") + "_" + man.get("name") )
                .sorted((birthday1, birthday2) -> birthday1.compareTo(birthday2))
                .map(man -> man.replaceAll("[[0-9]-_]", ""))
                .collect(Collectors.toList());
        return oldestMen;
    }
}
// END
