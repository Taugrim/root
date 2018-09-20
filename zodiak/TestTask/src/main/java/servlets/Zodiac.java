package servlets;

import funk.Cloc;
import funk.LogReader;
import org.apache.log4j.Logger;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Zodiac extends HttpServlet {
int reqwests=0;
int victory=0;
    LogReader logReader;
    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }
    private static Logger logger = Logger.getLogger(Zodiac.class);
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        Cloc.ini(logger).start();
        logReader = new LogReader();
        try {
            logReader.reader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        reqwests++;
        analizer(request, response);
        statistic(request, response);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        reqwests++;
        analizer(request, response);
        statistic(request, response);
    }
    private void analizer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long timeStamp = Cloc.getTimeStampLong();
        String in = request.getParameter("TestNumber") == null ? "-1" : (String) request.getParameter("TestNumber");
        Integer req = Integer.parseInt(in);
        String log = timeStamp + " [TestNumber] = " + req;
        int rand = (int) Math.round(Math.random() * 10);
        try {
            Thread.sleep(rand * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (rand <= 5) {
            if (req != null && !req.equals("-1")) {
                logger.info(log);
                System.out.println(req);
                if (logReader.startTimer(logReader.r,log)){
// не могу вернуть результат чтения логов корректно так что б задача закончилась ранее 15 секунд
                    send(response, req);
                    victory++;
                }
            }
        } else send(response, -1);
    }
    private void statistic(HttpServletRequest request, HttpServletResponse response) throws IOException {
       reqwests++;
       victory++;
        if(request.getParameterMap().containsKey("statistic")){


        float procent=(reqwests/victory);
        String in = " reqwests-"+reqwests+"   victory-"+victory+"   procentVictory-"+proc(reqwests,victory)+" %";
           PrintWriter out = response.getWriter();
           out.print(in);
           out.flush();}
    }
    private void send(
            HttpServletResponse response, int resp
    ) throws IOException {
        PrintWriter out = response.getWriter();
        out.print("[TestNumber] = " + resp);
        out.flush();
    }
    protected double proc( double a, double b ) {

        return 100/(a/ b);
    }


}