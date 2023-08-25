package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> result = new HashMap<>();
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);
        maxThread.start();
        minThread.start();
        LOGGER.info("Thread " + maxThread.getName() + " started");
        LOGGER.info("Thread " + minThread.getName() + " started");

        try {
            minThread.join();
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        }
        result.put("min", minThread.getMinNumber());
        result.put("max", maxThread.getMaxNumber());
        LOGGER.info("Thread " + maxThread.getName() + " finished");
        LOGGER.info("Thread " + minThread.getName() + " finished");
        return result;
    }
    // END
}
