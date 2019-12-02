package multitherding.pinpong;

public class Rocket implements Runnable {
    Type type;
    Table table;
    long s;

    public Rocket(Type type,Table table) {
        this.type = type;
        this.table = table;
        Thread.currentThread().setName(type.typ());
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.pp(this);

        }
    }

}
