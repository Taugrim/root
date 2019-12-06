package multitherding.producer_and_consumerExchange.producer_and_consumer;

import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Factory {
    Qeue qeue;
    Exchanger<Boolean> consEx;
    Exchanger<Boolean> prodEx;

    public Factory(int s) {

        this.qeue = new Qeue(s, consEx, prodEx);
        consEx = new Exchanger<>();
        prodEx = new Exchanger<>();
    }

    public Producer getP() {
        return new Producer(qeue, prodEx);
    }

    public Consumer getC() { return new Consumer(qeue, consEx);
    }

    public List<Consumer> getListC(int i) {
        return IntStream.range(0, i).mapToObj(q -> getC()).collect(Collectors.toList());
    }

    public List<Producer> getListP(int i) {
        return IntStream.range(0, i).mapToObj(q -> getP()).collect(Collectors.toList());
    }
}
