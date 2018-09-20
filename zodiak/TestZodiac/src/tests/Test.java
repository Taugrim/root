package tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;


public class Test {
   Logger logger ;
    int cicles;
    String urlRoot;
    URL url;
    HttpURLConnection conn;
    BufferedReader rd;
    String result = "";
    String s;
    String stat ;
    @BeforeClass
    public void ini() {
        cicles = 100;
        urlRoot = "http://localhost:8080/TestTask-1/Zodiac";
        result = "";
        s = urlRoot + "?TestNumber=";
        stat = urlRoot + "?statistic=1";
        logger = Logger.getLogger(Test.class);
    }
    @org.testng.annotations.AfterClass()
    public void shutdown() {
       logger.info(statistic());
        shutdow();

    }
    @org.testng.annotations.Test(dataProvider = "Data")
    public void Test(int i) {
        String rez=send(i);
        logger.info(rez+"   эталон-"+"[TestNumber] = " + i);
        Assert.assertEquals(rez, "[TestNumber] = " + i,"нет ответа");
        result = "";
    }
    private String statistic() {
        return get(stat, "GET");
    }
    @DataProvider
    public Object[][] Data() {

        Object[][] objects = new Object[cicles][1];
        for (int i = 0; i < cicles; i++)
            objects[i] = new Object[]{i + 1};
        return objects;

    }
    public String send(int num) {
        return get(s + num, "GET");

    }
    public void shutdow() {
        try {
            Socket socket = new Socket("localhost", 8005);
            if (socket.isConnected()) {
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                pw.println("SHUTDOWN");//send shut down command
                pw.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String get(String get, String method) {
        String line;
        try {
            url = new URL(get);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result = line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}


