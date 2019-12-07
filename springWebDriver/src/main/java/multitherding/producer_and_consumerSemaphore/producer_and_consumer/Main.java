package multitherding.producer_and_consumerSemaphore.producer_and_consumer;


public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory(5);
        factory.getListC(1).forEach(q -> new Thread(q).start());
        factory.getListP(1).forEach(q -> new Thread(q).start());
//        new Thread(factory.qeue).start();

    }
}
