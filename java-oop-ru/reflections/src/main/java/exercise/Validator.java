package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
       List<String> list = new ArrayList<>();
       for (Field field : address.getClass().getDeclaredFields()) {
           if (field.isAnnotationPresent(NotNull.class)) {
               try {
                   field.setAccessible(true);
                   if (field.get(address) == null) {
                       list.add(field.getName());
                   }
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
               }
           }
       }
       return list;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {

        Map<String, List<String>> map = new HashMap<>();
        List<String> errors = new ArrayList<>();

        for (Field field : address.getClass().getDeclaredFields()) {
            errors.clear();

            NotNull notNull = field.getAnnotation(NotNull.class);
            if (notNull != null) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        errors.add("can not be null");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            MinLength minLength = field.getAnnotation(MinLength.class);
            if (minLength != null) {
                try {
                    field.setAccessible(true);
                    if ((field.get(address) == null) || (((String) field.get(address)).length() < minLength.minLength())) {
                        errors.add("length less than " + minLength.minLength());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (!errors.isEmpty()) {
                map.put(field.getName(), new ArrayList<>(errors));
            }
        }
        return map;
    }
}
// END
