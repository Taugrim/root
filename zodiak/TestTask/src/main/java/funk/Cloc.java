package funk;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.Thread.sleep;

public class Cloc implements Runnable {

    static SimpleDateFormat dateFormat;
    Logger logger;
    String timeStamp;
    public static Thread ini(Logger logger) {
        Cloc cloc = new Cloc(logger);
        dateFormat = new SimpleDateFormat("hh:mm:ss");
        return new Thread(cloc);
    }
    private Cloc(Logger logger) {
        this.logger = logger;
    }
    public void run() {
        while (true) {
            timeStamp = getTimeStamp();

            logger.info("[Current Time] ="+timeStamp);
            try {
               sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public static Long getTimeStampLong(){

        return System.currentTimeMillis();
    }
    public static String getTimeStamp(){
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
