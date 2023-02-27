package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List actual1 = App.take(numbers1, 2);
        List expected1 = new ArrayList<>();
        expected1.add(1);
        expected1.add(2);
        assertThat(actual1).isEqualTo(expected1);

        List<Integer> numbers2 = new ArrayList<>(Arrays.asList(7, 3, 10));
        List actual2  = App.take(numbers2, 8);
        List expected2 = new ArrayList<>(Arrays.asList(7, 3, 10));
        assertThat(actual2).isEqualTo(expected2);

        List<Integer> numbers3 = new ArrayList<>(Arrays.asList(7, 3, 10));
        List actual3  = App.take(numbers3, 0);
        List expected3 = new ArrayList<>();
        assertThat(actual3).isEqualTo(expected3);

        List<Integer> numbers4 = new ArrayList<>();
        List actual4  = App.take(numbers4, 8);
        List expected4 = new ArrayList<>();
        assertThat(actual4).isEqualTo(expected4);
        // END
    }
}
