import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
    private int philosophersId;

    private int hungryLevel=5;


    private Semaphore leftChopstick;
    private Semaphore rightChopstick;


    public Philosopher(int philosophersId, Semaphore leftChopstick, Semaphore rightChopstick) {
        this.philosophersId = philosophersId;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    public void run() {
        try {
            while (hungryLevel>0) {
                think();
                pickUpLeftChopstick();
                pickUpRightChopstick();
                eat();
                putChopsticks();
            }
        } catch (InterruptedException e) {
            System.out.println(philosophersId + " Philosopher was interrupted.");
        }
    }
    private void think() throws InterruptedException {
        System.out.println(philosophersId + " Philosopher is thinking.");
        System.out.flush();
        Thread.sleep(new Random().nextInt(10));
    }
    private void pickUpLeftChopstick() throws InterruptedException{
        if(leftChopstick.availablePermits() ==0){
            System.out.println(philosophersId + " Philosopher is waiting for left  chopstick");
        }
        leftChopstick.acquire();
        System.out.println(philosophersId + " Philosopher is holding  left  chopstick.");
    }
    private void pickUpRightChopstick()  throws InterruptedException{
        if(rightChopstick.availablePermits() ==0){
            System.out.println(philosophersId + " Philosopher is waiting for right chopstick");
        }

        rightChopstick.acquire();
        System.out.println(philosophersId + " Philosopher is holding  right   chopstick.");

    }
    private void eat() throws InterruptedException {
        System.out.println(philosophersId + " Philosopher is eating.");
        System.out.flush();
        hungryLevel-=1;
        Thread.sleep(new Random().nextInt(10));
    }
    private void putChopsticks() {
        leftChopstick.release();
        rightChopstick.release();
        if(hungryLevel==0){
            System.out.println(philosophersId + " Philosopher finish eating\n");
        }
        System.out.println(philosophersId +" released left and right sticks");
    }
}
