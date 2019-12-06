package multitherding.producer_and_consumerExchange.producer_and_consumer;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {
    Qeue qeue;
    Exchanger<Boolean> prodEx;

    public Producer(Qeue qeue, Exchanger<Boolean> prodEx) {
        this.qeue = qeue;
        this.prodEx = prodEx;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (prodEx.exchange(true)) {

                    Thread.currentThread().wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = new Random().nextInt();
            System.out.println("Producer " + i);
            qeue.addLast(i);
        }
    }
}
