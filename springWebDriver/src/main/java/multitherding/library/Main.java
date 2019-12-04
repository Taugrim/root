package multitherding.library;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Book b1=new Book(); b1.size=1000;b1.name="1";b1.forReadRoom=true;
        Book b2=new Book(); b2.size=1000;b2.name="2";b2.forReadRoom=true;
        Book b3=new Book(); b3.size=1000;b3.name="3";b3.forReadRoom=true;
        Book b4=new Book(); b4.size=1000;b4.name="4";b4.forReadRoom=true;
        Book b5=new Book(); b5.size=1000;b5.name="5";b5.forReadRoom=false;
        Book b6=new Book(); b6.size=1000;b6.name="6";b6.forReadRoom=false;
        Exchanger<Reader> readRoom=new Exchanger<>();
        Exchanger<Reader> homer =new Exchanger<>();
        Lib lib=new Lib();
        lib.forReadRoom.add(b1);
        lib.forReadRoom.add(b2);
        lib.forReadRoom.add(b3);
        lib.forReadRoom.add(b4);
        lib.home.add(b5);
        lib.home.add(b6);
        lib.readRoom=readRoom;
        lib.homer=homer;
        RedersGenerator rg=new RedersGenerator(lib);
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        service.execute(rg);
        service.execute(lib);

        service.shutdown();
    }
}
