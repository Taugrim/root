package multitherding.ships;

import multitherding.ships.types.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PierLoader  implements Runnable {
    Logger log;
    private Tunnel tunnel;
    private Type shipType;

    public PierLoader(Tunnel tunnel, Type shipType) {
        this.tunnel = tunnel;
        this.shipType =shipType;
      log=  LoggerFactory.getLogger(PierLoader.class);

    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.currentThread().setName("Loader "+shipType);

                //Time to load the goods
                Thread.sleep(500);
                Ship ship = tunnel.get(shipType);
                if(ship!=null)
                    while (ship.countCheck()){
                        Thread.sleep(100);
                        ship.add(10);
                        System.out.println(ship.getCount() + " Loaded ship. " + Thread.currentThread().getName());
                    }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}