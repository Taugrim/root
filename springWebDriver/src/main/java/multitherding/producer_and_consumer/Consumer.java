package multitherding.producer_and_consumer;

public class Consumer implements Runnable{
    Qeue qeue;

    public Consumer(Qeue qeue) {
        this.qeue=qeue;
    }

    @Override
    public void run() {
        while (true)
        System.out.println("Consumer  "+ qeue.getFirst());
    }
}
