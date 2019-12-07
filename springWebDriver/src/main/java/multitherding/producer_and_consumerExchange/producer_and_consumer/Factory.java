package multitherding.producer_and_consumerExchange.producer_and_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Factory {
    Qeue qeue;
    Exchanger<Integer> consEx;
    Exchanger<Integer> prodEx;

    public Factory(int s) {

        this.qeue = new Qeue(s);
//        this.qeue = new Qeue(s, consEx, prodEx);
        consEx = new Exchanger<>();
        prodEx = new Exchanger<>();
    }

    public Producer getP() {
        System.out.println("getP");

        return new Producer(prodEx);
    }

    public Consumer getC() {
        System.out.println("getC");

        return new Consumer(consEx);
    }

    public List<Consumer> getListC(int i) {

        qeue.consEx = consEx;
        return IntStream.range(0, i).mapToObj(q -> getC()).collect(Collectors.toList());
    }

    public List<Producer> getListP(int i) {

        qeue.prodEx = prodEx;
        return IntStream.range(0, i).mapToObj(q -> getP()).collect(Collectors.toList());
    }
}
