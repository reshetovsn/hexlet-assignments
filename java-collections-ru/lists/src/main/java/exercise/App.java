package exercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// BEGIN
public class App {
    public static boolean scrabble(String symbols, String word) {
        char[] symbolsArr = symbols.toCharArray();
        for (var ch : symbolsArr) {
            if (Character.isUpperCase(ch)) {
                return false;
            }
        }
        char[] wordArr = word.toLowerCase().toCharArray();

        List<Character> symbolsList = new ArrayList<>();
        for (var c : symbolsArr) {
            symbolsList.add(c);
        }
        System.out.println(symbolsList);

        List<Character> wordList = new ArrayList<>();
        for (var cc : wordArr) {
            wordList.add(cc);
        }
        System.out.println(wordList);
        System.out.println("////////////////");
        Iterator<Character> itWord = wordList.iterator();
        while (itWord.hasNext()) {
            var chWords = itWord.next();
            if (symbolsList.contains(chWords)) {
                itWord.remove();
                symbolsList.remove(chWords);
                System.out.println(symbolsList);
                System.out.println(wordList);
                System.out.println("***************");
            } else {
                return false;
            }
        }
        return wordList.isEmpty();
    }
}
//END
