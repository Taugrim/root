package multitherding.library;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.stream.Collectors;

public class Reader implements Runnable {
    List<Book> books;
    Exchanger<Reader> readRoom;
    Exchanger<Reader> home;
    List<Book> rm;
    List<Book> hom;
Reader(){
    books=new ArrayList<>();

}
    @Override
    public void run() {
        rm = books.stream().filter(q -> q.forReadRoom).peek(q -> read(q)).collect(Collectors.toList());
        try {
            readRoom.exchange(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hom = books.stream().filter(q -> !q.forReadRoom).peek(q -> read(q)).collect(Collectors.toList());
        try {
            home.exchange(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void read(Book s) {
        try {
            Thread.currentThread().sleep(s.size);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
