package multitherding.reentallockpinpong.pinpong;

public enum Type {
    PING("ping"),PONG("pong");

    private final String s;

    Type(String s) {
       this.s=s;
    }
    String typ(){
        return s;
    }
}
