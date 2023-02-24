package exercise;

import java.util.*;

// BEGIN
public class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> mapA, Map<String, Object> mapB){
        Set<String> keys = new TreeSet<>();
        keys.addAll(mapA.keySet());
        keys.addAll(mapB.keySet());

        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        for (String elements : keys) {
            if (mapA.containsKey(elements) && mapB.containsKey(elements)) {
                var valueA = mapA.get(elements);
                var valueB = mapB.get(elements);
                if (!valueA.equals(valueB)) {
                    result.put(elements, "changed");
                } else {
                    result.put(elements, "unchanged");
                }
            } else if (mapA.containsKey(elements) && !mapB.containsKey(elements)) {
                result.put(elements, "deleted");
            } else if (!mapA.containsKey(elements) && mapB.containsKey(elements)) {
                result.put(elements, "added");
            }
        }
        return result;
    }
}
//END
