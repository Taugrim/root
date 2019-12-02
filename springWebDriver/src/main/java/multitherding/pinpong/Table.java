package multitherding.pinpong;

public class Table {
Type p=Type.PING;
    public Table() {

    }
    public synchronized void pp(Rocket rocket){
       notifyAll();
       if(!p.equals(rocket.type)){
           System.out.println(rocket.type.typ());
           p=rocket.type;
       }
       else {
           try {
               System.out.println(  rocket.type.typ()+"   wait");
               wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
