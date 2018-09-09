import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String... agrs) throws IOException {
        String p = "Привет кошка." +
                "Как дела?";
        Hammer hammer = new Hammer();
        Lang lang=new Lang();
        System.out.println(hammer.translate(Lang.английский, p));
//        System.out.println(lang.getLangs(Lang.русский));
    }
}
