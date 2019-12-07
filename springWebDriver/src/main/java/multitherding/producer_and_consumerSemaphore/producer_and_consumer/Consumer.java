package multitherding.producer_and_consumerSemaphore.producer_and_consumer;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {
    Qeue qeue;

    Semaphore consSem;
    public Consumer(Qeue qeue,Semaphore consSem) {

        this.qeue = qeue;
        this.consSem = consSem;
    }

    @Override
    public void run() {
        while (true) {

            System.out.println("Consumer  " + qeue.getFirst());
        }
    }
}
