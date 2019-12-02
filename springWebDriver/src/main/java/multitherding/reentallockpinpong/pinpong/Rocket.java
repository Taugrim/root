package multitherding.reentallockpinpong.pinpong;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Rocket implements Runnable {
    private Ball ball;
    Type type;
    long s;
    Lock lock;

    public Rocket(Type type, Lock lock, Ball ball) {
        this.type = type;
        this.lock = lock;
        this.ball = ball;
        Thread.currentThread().setName(type.typ());
    }

    @Override
    public void run() {  lock.lock();
        while (true) {
//            System.out.println("ball "+ball.type.typ());
            if (!ball.type.equals(type)) {

                System.out.println("                                 "+ball.type.typ());
                try {
                    Thread.sleep(s);



                    ball.pp(type);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                lock.newCondition().signalAll();
            }


        }
    }

}
