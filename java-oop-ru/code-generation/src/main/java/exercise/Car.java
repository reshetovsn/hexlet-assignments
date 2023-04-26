package exercise;

import lombok.SneakyThrows;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN

// END
@Value
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    @SneakyThrows
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(this);
        return json;
    }

    @SneakyThrows
    public static Car unserialize(String str) {
        ObjectMapper mapper = new ObjectMapper();
        Car car = mapper.readValue(str, Car.class);
        return car;
    }
    // END
}
