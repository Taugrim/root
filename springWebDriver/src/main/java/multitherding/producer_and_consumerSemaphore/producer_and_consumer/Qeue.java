package multitherding.producer_and_consumerSemaphore.producer_and_consumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;


public class Qeue {
    private final int size;
    Queue<Integer> q;
    Semaphore prodSem;
    Semaphore consSem;

    Qeue(int size, Semaphore prodSem,Semaphore consSem) {
        q = new LinkedList<>();
        this.size = size;
        this.consSem = consSem;
        this.prodSem = prodSem;
    }

    public void addLast(int i) {

        if (q.size() >= size) {
            try { prodSem.acquire();
                System.out.println("wait addLast");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else { prodSem.release(q.size());
            q.offer(i);
            System.out.println("add " + i);
        }
    }

    public Integer getFirst() {

        Integer ii = q.poll();

            try {
              consSem.acquire();
                System.out.println("wait getFirst");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        consSem.release(q.size());
        System.out.println("return " + ii);
        return ii;
    }

}
