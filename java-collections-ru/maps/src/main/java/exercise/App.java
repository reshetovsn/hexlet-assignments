package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        String[] words = sentence.toLowerCase().split(" ");
        Map<String, Integer> emptyMap = new HashMap<>();
        if (sentence.equals("")) {
            return emptyMap;
        }
        var count = 0;
        Map<String, Integer> wordsMap = new HashMap<>();
        for (var eachWord : words) {
            if (!wordsMap.containsKey(eachWord)) {
                wordsMap.put(eachWord, 1);
            } else {
                count = wordsMap.get(eachWord) + 1; // или можно здесь через счетчик? count += 1;
                wordsMap.put(eachWord, count);
            }
        }
        return wordsMap;
    }
    public static String toString(Map<String, Integer> hm) {
        if (hm.isEmpty()) {
            return "{}";
        }
        var result = new StringBuilder();
        result.append("{\n");
        for (Map.Entry<String, Integer> word : hm.entrySet()) {
            result.append("  " + word.getKey());
            result.append(": ");
            result.append(word.getValue());
            result.append("\n");
        }
        result.append("}");
        return result.toString();
    }
}
//END
