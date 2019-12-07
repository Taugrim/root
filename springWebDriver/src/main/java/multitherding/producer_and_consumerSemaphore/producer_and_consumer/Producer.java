package multitherding.producer_and_consumerSemaphore.producer_and_consumer;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class Producer implements Runnable {
    Qeue qeue;
    Semaphore prodSem;

    public Producer(Qeue qeue, Semaphore prodSem) {
        this.qeue = qeue;
        this.prodSem = prodSem;
    }

    @Override
    public void run() {
        while (true) {
            int i = new Random().nextInt();
            System.out.println("Producer " + i);
            qeue.addLast(i);
        }
    }
}
