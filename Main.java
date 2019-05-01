

import java.util.concurrent.Semaphore;

public class Main {
    static final int max = 5;

    public static void main(String[] args) {
        Semaphore[] chopsticks = new Semaphore[max];

        for (int i = 0; i < max; i++) {
            chopsticks[i] = new Semaphore(1);
        }

        Philosopher[] philosophers = new Philosopher[max];

        for (int i = 0; i < max; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % max]);
            new Thread(philosophers[i]).start();
        }

    }
}
