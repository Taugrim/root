package multitherding.pinpongSemaphore.pinpong;

import java.util.concurrent.Semaphore;

public class Rocket implements Runnable {
    private  Ball ball;
    Type type;
    long s;
    Semaphore sem;
    public Rocket(Type type, Semaphore sem,Ball ball) {
        this.type = type;
        this.sem = sem;
        this.ball = ball;
        Thread.currentThread().setName(type.typ());
    }

    @Override
    public void run() {
        while (true) {
            try { Thread.sleep(s);
                sem.acquire();

               ball.pp(type);

                sem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

}
