package combinator;

import com.mifmif.common.regex.Generex;

public class Generator {
    public static String genRegex(String node) {
        return node
                .replaceAll("[a-z]","[a-z]")
                .replaceAll("[A-Z]","[A-Z]")
                .replaceAll("[А-Я]","[А-Я]")
                .replaceAll("[а-я]","[а-я]")
                .replaceAll("\\d","\\\\\\d")
                .replaceAll("\\+","\\\\\\+");
    }
  public static String genBrokenRegex(String node) {
        return node
                .replaceAll("[a-z]","\\\\\\d")
                .replaceAll("[A-Z]","\\\\\\d")
                .replaceAll("[А-Я]","\\\\\\d")
                .replaceAll("[а-я]","\\\\\\d")
                .replaceAll("\\d","[a-zA-zа-яА-я]")
                .replaceAll("\\+","\\\\\\+")
                .replaceAll("\\s","P");
    }
    public static String genRandomStringByRegex(String str){
        return new Generex(genRegex(str)).random();
    } public static String genBrokenRandomStringByRegex(String str){
        return new Generex(genBrokenRegex(str)).random();
    }
}
