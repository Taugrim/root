package multitherding.producer_and_consumer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Factory {
    Qeue qeue;

    public Factory(int s) {
        this.qeue = new Qeue(s);
    }

    public Producer getP() {
        return new Producer(qeue);
    }

    public Consumer getC() {
        return new Consumer(qeue);
    }

    public List<Consumer> getListC(int i) {
        return IntStream.range(0, i).mapToObj(q -> getC()).collect(Collectors.toList());
    }

    public List<Producer> getListP(int i) {
        return IntStream.range(0, i).mapToObj(q -> getP()).collect(Collectors.toList());
    }
}
