package multitherding.producer_and_consumerExchange.producer_and_consumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Exchanger;


public class Qeue implements Runnable {
    private final int size;
    Queue<Integer> q;
    Exchanger<Integer> consEx;
    Exchanger<Integer> prodEx;

    Qeue(int size) {
        System.out.println("Qeue ");
//    Qeue(int size,Exchanger<Integer> consEx, Exchanger<Integer> prodEx ) {
        q = new LinkedList<>();
        this.size = size;

    }


    @Override
    public void run() {
        System.out.println("Q " );
        while (true) {
            System.out.println("Q 2" );
            try {
                if (q.size() < size) {
                    Integer i=prodEx.exchange(null);
                    System.out.println("prod  "+i);
                    q.offer(i);
                }


                if (q.size() != 0) {
                    consEx.exchange(q.poll());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

