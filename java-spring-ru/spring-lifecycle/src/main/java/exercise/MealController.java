package exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class MealController {
    @Autowired
    Meal meal;
    @Autowired
    MyApplicationConfig myApplicationConfig;

    @GetMapping(path = "/daytime")
    public String bonAppetit() {
        String dayTime = myApplicationConfig.getDaytime().getName();
        return "It is " + dayTime + " now. Enjoy your " + meal.getMealForDaytime(dayTime);
    }
}
// END
