package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int numberOfApart) {
        List<String> result = new ArrayList<>();
        if (apartments.isEmpty()) {
            return result;
        }
        apartments.sort((o1, o2) -> o1.compareTo(o2));
        for (var i = 0; i < numberOfApart; i++) {
            result.add(apartments.get(i).toString());
        }
        return result;
    }
}
// END
