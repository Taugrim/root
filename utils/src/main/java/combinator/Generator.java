package combinator;

public class Generator {
    public static String genRegex(String node) {
        return node
                .replaceAll("[a-z]","[a-z]")
                .replaceAll("[A-Z]","[A-A]")
                .replaceAll("[А-Я]","[А-Я]")
                .replaceAll("[а-я]","[а-я]")
                .replaceAll("\\d","\\\\\\d")
                .replaceAll("\\+","\\\\\\+");
    }
}
