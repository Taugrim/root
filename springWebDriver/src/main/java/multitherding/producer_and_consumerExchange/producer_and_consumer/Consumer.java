package multitherding.producer_and_consumerExchange.producer_and_consumer;

import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {
    Qeue qeue;
    Exchanger<Boolean> consEx;

    public Consumer(Qeue qeue, Exchanger<Boolean> consEx) {

        this.qeue = qeue;
        this.consEx = consEx;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (consEx.exchange(true)) {

                    Thread.currentThread().wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer  " + qeue.getFirst());
        }
    }
}
