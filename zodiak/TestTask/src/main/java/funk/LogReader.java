package funk;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Paths.*;


public class LogReader {
    static String dir = System.getProperty("catalina.home") + "/logs/zodiacLog";
   public RandomAccessFile r;
    boolean flag=true;
    public void reader() throws IOException {
        File file = last(dir).toFile();
        boolean resp = false;
        try {
            r= new RandomAccessFile(file, "r");
            String str = null;
            while((str = r.readLine()) != null&&flag) {

            }
            r.seek(r.getFilePointer());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public   boolean startTimer(final RandomAccessFile r, String st) throws IOException {
        flag=false;
        Timer timer = new Timer();
        final boolean[] resp = {false};
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String str = null;
                try {
                    while ((str = r.readLine()) != null) {


                        if(!resp[0])resp[0] = str.contains(st);
                    }
                    r.seek(r.getFilePointer());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 15000);
        System.out.println( resp[0]);
        flag=true;
        reader();
        return resp[0];
    }
    public static Path last(String directory) throws IOException {
        Path dir = get(directory);
        Optional<Path> lastFilePath = Files.list(dir)
                .filter(f -> !Files.isDirectory(f))
                .max(Comparator.comparingLong(f -> f.toFile().lastModified()));
        if (lastFilePath.isPresent()) {
            return lastFilePath.get();
        } else {
            throw new FileNotFoundException();
        }
    }
}