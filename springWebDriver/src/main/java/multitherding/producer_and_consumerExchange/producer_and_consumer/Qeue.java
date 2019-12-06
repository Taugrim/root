package multitherding.producer_and_consumerExchange.producer_and_consumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Exchanger;


public class Qeue {
    private final int size;
    Queue<Integer> q;
    Exchanger<Boolean> consEx;
    Exchanger<Boolean> prodEx;

    Qeue(int size, Exchanger<Boolean> consEx, Exchanger<Boolean> prodEx) {
        q = new LinkedList<>();
        this.size = size;
        this.consEx = consEx;
        this.prodEx = prodEx;
    }

    public void addLast(int i) {
        notifyAll();
        if (q.size() >= size) {
            try {
                prodEx.exchange(false);
                System.out.println("wait addLast");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            q.offer(i);
            System.out.println("add " + i);
        }
    }

    public Integer getFirst() {
        notifyAll();
        Integer ii = q.poll();
        if (ii == null) {
            try {
               consEx.exchange(false);
                System.out.println("wait getFirst");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("return " + ii);
        return ii;
    }

}
