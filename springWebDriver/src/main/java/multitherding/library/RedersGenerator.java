package multitherding.library;


import java.util.Random;
import java.util.concurrent.Exchanger;

public class RedersGenerator implements Runnable {



    private final Lib lib;

    public RedersGenerator(Lib lib) {
        this.lib = lib;
    }

    @Override
    public void run() {

        while (true) {
            Thread.currentThread().setName(" RedersGenerator");
            try {
                Reader r=new Reader();
               lib.add(new Reader());

//            lib.add(new Reader());

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}