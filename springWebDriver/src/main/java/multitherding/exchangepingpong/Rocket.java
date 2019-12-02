package multitherding.exchangepingpong;

import multitherding.pinpong.Table;


import java.util.concurrent.Exchanger;

public class Rocket implements Runnable {
    Type type;
    Exchanger<Ball> exchanger;
    long s;

    public Rocket(Type type,Exchanger<Ball> exchanger) {
        this.exchanger = exchanger;
this.type=type;
        Thread.currentThread().setName(type.typ());
    }

    @Override
    public void run() {
        while (true) {
            try {

                Ball ball=exchanger.exchange(null);
                Thread.sleep(s);
ball=ball==null||ball.rocket.type.equals(type)?new Ball(this):ball;
                System.out.println(ball.rocket.type.typ());
                exchanger.exchange(ball);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
