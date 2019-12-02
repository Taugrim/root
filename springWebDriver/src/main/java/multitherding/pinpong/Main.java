package multitherding.pinpong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Available number of cores: " + Runtime.getRuntime().availableProcessors());
        Table table=new Table();
   Rocket ping=new Rocket(Type.PING,table);
   Rocket pong=new Rocket(Type.PONG ,table);
   ping.s=1000;
   pong.s=5000;



        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        service.execute(ping);
        service.execute(pong);

        service.shutdown();
    }
}
