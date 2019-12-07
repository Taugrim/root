package multitherding.producer_and_consumerExchange.producer_and_consumer;

import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {

    Exchanger<Integer> consEx;

    public Consumer( Exchanger<Integer> consEx) {


        this.consEx = consEx;
    }

    @Override
    public void run() {
        System.out.println("Consumer  ");


        while (true) {
            try {
                System.out.println("Consumer  " +   consEx.exchange(null));

            } catch (InterruptedException e) {
                System.out.println("Consumer  "+e.getMessage());
            }

        }
    }
}
