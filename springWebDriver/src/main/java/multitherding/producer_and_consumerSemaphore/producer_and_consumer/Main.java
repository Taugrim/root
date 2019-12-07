package multitherding.producer_and_consumerSemaphore.producer_and_consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Available number of cores: " + Runtime.getRuntime().availableProcessors());

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Factory factory = new Factory(5);
        factory.getListC(10).forEach(q -> service.execute(q));
        factory.getListP(1).forEach(q -> service.execute(q));

        service.shutdown();

    }
}
