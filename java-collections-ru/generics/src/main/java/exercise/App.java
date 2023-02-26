package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
public class App {

    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> whatToFind) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Map<String, String> book : books) {
            boolean isMatch = true;
            for (Map.Entry<String, String> element : whatToFind.entrySet()) {
                String keyWhatToFind = element.getKey();
                String valueWhatToFind = element.getValue();
                String valueBook = book.get(keyWhatToFind);
                if (!valueBook.equals(valueWhatToFind)) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                result.add(book);
            }
        }
        return result;
    }
}
//END
