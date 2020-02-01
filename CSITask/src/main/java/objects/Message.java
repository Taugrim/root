package objects;

import java.util.Objects;

public class Message {
    public  String snippetSender;
    public  String snippet;
    public  String text;
    public  String theme;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return text.equals(message.text) &&
                theme.equals(message.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, theme);
    }
}
