package exercise;

import lombok.SneakyThrows;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {

    @SneakyThrows
    public static void save(Path pathToFile, Car car) {
        Files.writeString(pathToFile, car.serialize(), StandardOpenOption.WRITE);
    }

    @SneakyThrows
    public static Car extract(Path pathToFile) {
        String content = Files.readString(pathToFile);
        Car car = Car.unserialize(content);
        return car;
    }
}
// END
