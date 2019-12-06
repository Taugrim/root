package multitherding.producer_and_consumer;

import java.util.Random;

public class Producer implements Runnable {
    Qeue qeue;

    public Producer(Qeue qeue) {
        this.qeue = qeue;
    }

    @Override
    public void run() {
        while (true){
        int i=new Random().nextInt();
        System.out.println("Producer "+i);
        qeue.addLast(i);}
    }
}
