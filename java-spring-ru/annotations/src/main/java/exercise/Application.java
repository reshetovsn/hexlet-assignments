package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        StringBuilder stringBuilder = new StringBuilder();
        for (Method method: Address.class.getDeclaredMethods()) {

            if (method.isAnnotationPresent(Inspect.class)) {

                try {
                    method.invoke(address);
                    String returnType = method.getReturnType().getSimpleName();
                    var methodName = method.getName();
                    stringBuilder.append("Method ")
                            .append(methodName)
                            .append(" returns a value of type ")
                            .append(returnType)
                            .append("\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(stringBuilder);
        // END
    }
}
