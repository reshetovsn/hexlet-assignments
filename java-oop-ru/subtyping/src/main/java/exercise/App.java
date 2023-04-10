package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage initMap) {
        Set<String> keyset = new TreeSet<>(initMap.toMap().keySet());
        for (String keys : keyset) {
            String tempValue = initMap.get(keys, "default");
            initMap.unset(keys);
            initMap.set(tempValue,keys);
        }
    }
}
// END
