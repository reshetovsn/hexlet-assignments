package exercise;

import java.util.Random;

// BEGIN
public class ListThread extends Thread {
    private SafetyList list;

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int i = 0;
        while (i < 1000) {
            try {
                int number = rand.nextInt(100);
                list.add(number);
                sleep(1);
                i++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
// END
