package multitherding.producer_and_consumerSemaphore.producer_and_consumer;

import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Factory {
    Qeue qeue;
    Semaphore prodSem;
    Semaphore consSem;

    public Factory(int s) {
        Semaphore prodSem=new Semaphore(1);
        Semaphore consSem=new Semaphore(1);
        this.qeue = new Qeue(s,prodSem,consSem);
//this.prodSem
    }

    public Producer getP() {
        return new Producer(qeue,prodSem);
    }

    public Consumer getC() { return new Consumer(qeue,consSem);
    }

    public List<Consumer> getListC(int i) {
        return IntStream.range(0, i).mapToObj(q -> getC()).collect(Collectors.toList());
    }

    public List<Producer> getListP(int i) {
        return IntStream.range(0, i).mapToObj(q -> getP()).collect(Collectors.toList());
    }
}
