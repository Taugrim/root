package multitherding.exchangepingpong;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Available number of cores: " + Runtime.getRuntime().availableProcessors());
        Exchanger<Ball> ex=new Exchanger<>();
   Rocket ping=new Rocket(Type.PING,ex);
   Rocket pong=new Rocket(Type.PONG ,ex);
   ping.s=1000;
   pong.s=5000;



        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        service.execute(ping);
        service.execute(pong);

        service.shutdown();
    }
}
