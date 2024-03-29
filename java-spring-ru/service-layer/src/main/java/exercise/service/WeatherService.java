package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.HttpClient;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getWeatherInCity(long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));

        String cityName = city.getName();
        String response = client.get("http://weather/api/v2/cities/" + cityName);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> data;
        try {
            data = mapper.readValue(response, Map.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return data;
    }
    // END
}
