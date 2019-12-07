package multitherding.producer_and_consumerExchange.producer_and_consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory(5);
        factory.getListC(1).forEach(q -> new Thread(q).start());
        factory.getListP(10).forEach(q -> new Thread(q).start());
        new Thread(factory.qeue).start();

    }
}
