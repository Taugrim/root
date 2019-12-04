package multitherding.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.stream.Collectors;

public class Lib implements Runnable {
    Exchanger<Reader> readRoom;
    Exchanger<Reader> homer;

    List<Book> forReadRoom;
    List<Book> home;
    List<Reader> readRoomL;
    int sizeReadRoom = 5;

    public Lib() {
        readRoomL = new ArrayList<>();
        home = new ArrayList<>();
        forReadRoom = new ArrayList<>();
    }

    public synchronized void add(Reader reader) {
        if (readRoomL.size() < sizeReadRoom) {
            reader.readRoom=readRoom;
            reader.home=homer;
            reader.books.addAll(getForReadRoomBooks());
            reader.books.addAll(getHomeBooks());
            readRoomL.add(reader);
            new Thread(reader).start();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Book> getForReadRoomBooks() {
        Random random = new Random();
        List<Book> l= forReadRoom.stream().limit(random.nextInt(forReadRoom.size())).collect(Collectors.toList());
        forReadRoom.removeAll(l);
        System.out.println("getForReadRoomBooks "+ l.stream().map(q->q.name).collect(Collectors.joining()));
        return l;
    }

    private List<Book> getHomeBooks() {
        Random random = new Random();
        List<Book> l= home.stream().limit(random.nextInt(home.size())).collect(Collectors.toList());
        home.removeAll(l);
        System.out.println("getHomeBooks "+ l.stream().map(q->q.name).collect(Collectors.joining()));
        return l;
    }


    @Override
    public void run() {
        while (true) {

            try {
                Reader r = readRoom.exchange(null);
                System.out.println("return to readroom "+r.rm.stream().map(q->q.name).collect(Collectors.joining()));
                forReadRoom.addAll(r.rm);
                r.rm = null;
                if ( readRoomL.remove(r)&&readRoomL.size()==sizeReadRoom)    notifyAll();
                r = homer.exchange(null);
                System.out.println("return to home "+r.hom.stream().map(q->q.name).collect(Collectors.joining()));
                home.addAll(r.hom);
                r.hom = null;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
