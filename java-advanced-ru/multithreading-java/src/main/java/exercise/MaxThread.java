package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread{
    private int[] array;
    int maxNumber;

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public MaxThread(int[] array) {
        this.array = array;
    }
    @Override
    public void run() {
        int max = Arrays.stream(array).max().getAsInt();
        setMaxNumber(max);
    }
}
// END
