package exercise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
    public static String getForwardedVariables(String data){
        String[] lines = data.split("\n");
        List<String> list = Arrays.asList(lines);
        return list.stream()
                .filter(line -> line.startsWith("environment"))
                .map(line -> line.replaceAll("environment=", ""))
                .map(line -> line.split(","))
                .flatMap(Arrays::stream)
                .map(line -> line.replaceAll("[\" ]", ""))
                .filter(line -> line.contains("X_FORWARDED_"))
                .map(line -> line.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END
