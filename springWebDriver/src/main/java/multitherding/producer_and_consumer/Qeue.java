package multitherding.producer_and_consumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Qeue   {
    private final int size;
    Queue<Integer> q;
    Qeue(int size){
        q=new LinkedList<>();
        this.size= size;
    }
    synchronized public void addLast(int i){
        notifyAll();
       if(q.size()>=size){
           try {
               wait();
               System.out.println("wait addLast");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }else{
           q.offer(i);
           System.out.println("add "+i);
       }
    }
     synchronized public Integer getFirst(){
        notifyAll();
        Integer ii=q.poll();
       if(ii==null){
           try {
               wait(); System.out.println("wait getFirst");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
         System.out.println("return "+ii);
       return ii;
    }

}
