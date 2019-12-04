package multitherding.reentallockpinpong.pinpong;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        System.out.println("Available number of cores: " + Runtime.getRuntime().availableProcessors());

        ReentrantLock locker = new ReentrantLock();
        Ball ball = new Ball();
        ball.type = Type.PING;
        Rocket ping = new Rocket(Type.PING, locker, ball);
        Rocket pong = new Rocket(Type.PONG, locker, ball);
        ping.s = 100;
        pong.s = 200;
        new Thread(ping).start();
        new Thread(pong).start();

    }
}
