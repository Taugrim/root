package multitherding.producer_and_consumerExchange.producer_and_consumer;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {
    Qeue qeue;
    Exchanger<Integer> prodEx;

    public Producer( Exchanger<Integer> prodEx) {


        this.prodEx = prodEx;
    }
    @Override
    public void run() {
        System.out.println("Producer ");
        while (true) {
            try {int i = new Random().nextInt();
                System.out.println("Producer " + i);
                prodEx.exchange(i);
            } catch (InterruptedException e) {
                System.out.println("Producer " +  e.getMessage());
            }


        }
    }
}
