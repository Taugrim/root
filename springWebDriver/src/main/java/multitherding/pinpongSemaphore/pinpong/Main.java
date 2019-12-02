package multitherding.pinpongSemaphore.pinpong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        System.out.println("Available number of cores: " + Runtime.getRuntime().availableProcessors());
        Semaphore sem=new Semaphore(1);
        Ball ball=new Ball();
   Rocket ping=new Rocket(Type.PING,sem,ball);
   Rocket pong=new Rocket(Type.PONG ,sem,ball);
   ping.s=1000;
   pong.s=2000;
new Thread(ping).start();
new Thread(pong).start();

    }
}
