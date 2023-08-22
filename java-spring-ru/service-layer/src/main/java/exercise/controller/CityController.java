package exercise.controller;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> findCity(@PathVariable long id) {
        return weatherService.getWeatherInCity(id);
    }

    @GetMapping(path = "/search")
    public List<Map<String, String>> findCities(@RequestParam(required = false) String name) {
        List<City> listCities;
        if (name != null) {
            listCities = cityRepository.findByNameStartingWithIgnoreCase(name);

        } else {
            listCities = cityRepository.findAllByOrderByName();
        }
        return listCities.stream()
                .map(city -> {
                    long id = city.getId();
                    Map<String, String> fullData = weatherService.getWeatherInCity(id);
                    return Map.of(
                            "temperature", fullData.get("temperature"),
                            "name", city.getName()
                    );
                })
                .collect(Collectors.toList());
    }
    // END
}

