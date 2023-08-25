package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread{
    private int[] array;
    int minNumber;

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public MinThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        int min  = Arrays.stream(array)
                .min()
                .orElse(0);
        setMinNumber(min);
    }
}
// END
