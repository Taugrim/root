
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;


public class Hammer {
    //    public static HashMap<String, String> getRu() {
//        return ru;
//    }
//
//    public static HashMap<String, String> getDru() {
//        return dru;
//    }
//
//    public static void setEditorPane1(String editorPane1) {
//        editorPane1 = editorPane1;
//    }
//
//    public static HashMap<String, String> multytran() throws IOException {
//        HashMap<String, String> ru = new HashMap();
//        notranlist = new ArrayList();
//        HashMap<String, String> r = Read.readlingv(StartTranslate.getput());
//        System.out.println(r.size() + "+++++++++++++++++++++++++");
//        for (String s : editorPane1.trim().split("[,;:.!?\\s]+")) {
//            System.out.println(s + "    сырой-=-=-=-=-=-=-=-=-");
//            if (r.containsKey(s)) {
//                System.out.println("ключ есть+++++++++++++++++++++++++++++++++++");
//                ru.put(s, r.get(s));
//            } else {
//                System.out.println("ключа нет-----------------------------");
//                notranlist.add(s);
//            }
//        }
//        return ru;
//    }
//
//    public static String text(HashMap<String, String> ru) {
//        String r = "r hammer++++";
//        StringBuilder builder = new StringBuilder();
//        System.out.println("setru--" + ru.size());
//        String qq = new Character('\t').toString();
//        for (Entry w : ru.entrySet()) {
//            System.out.println("builder  +" + builder);
//            builder.append(w.getKey());
//            System.out.println("builder  +" + builder);
//            builder.append(qq);
//            System.out.println("builder  +" + builder);
//            builder.append(w.getValue());
//            System.out.println("builder  +" + builder);
//            builder.append(";\r\n");
//            System.out.println("builder  +" + builder);
//            r = builder.toString();
//        }
//        return r;
//    }
//
//    public static void dowrite(HashMap<String, String> ru) throws IOException {
//        StringBuilder builder = new StringBuilder();
//        String qq = new Character('\t').toString();
//        BufferedWriter out = new BufferedWriter(new FileWriter(StartTranslate.getput(), true));
//        for (Entry<String, String> w : ru.entrySet()) {
//            builder.append((String) w.getKey());
//            builder.append(qq);
//            builder.append((String) w.getValue());
//            out.write(builder.toString());
//            out.write("\r\n");
//            builder = new StringBuilder();
//        }
//        System.out.println("Запись прошла успешно");
//        out.close();
//    }
//
//    public static ArrayList<Row> multytranyandex(String str) throws IOException {
//        ArrayList<Row> ru = new ArrayList();
//        for (String s : str.trim().split("[,;:.!?\\s]+")) {
//            ru.add(new Row(s.trim(), Hammer.translate(startlv, s.trim())));
//        }
//        return ru;
//    }

    public static String translate(String lang, String input) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) new URL(Stat.URL + Stat.URLtranlate + Stat.key).openConnection();
        connection.setRequestMethod(Stat.type);
        connection.setDoOutput(true);
        String reqwest = "&text=" + URLEncoder.encode(input, "UTF-8") + "&text=" + URLEncoder.encode(input, "UTF-8") + "&lang=" + lang;
//        System.out.println(reqwest);
        new DataOutputStream(connection.getOutputStream()).writeBytes(reqwest);
        String json = new Scanner(connection.getInputStream()).nextLine();
        String translated = json;
//        try (InputStream is = connection.getInputStream();
//            JsonReader rdr = Json.createReader(is)) {
//
//             JsonObject obj = rdr.readObject();
//             JsonArray results = obj.getJsonArray("data");


//        String translated = json.substring(json.indexOf("[") + 2, json.indexOf("]") - 1);
            return translated;
        }
    }


/**
 * Перевод текста
 * Краткое содержание
 * JSON- и JSONP-интерфейсы
 * XML-интерфейс
 * Коды ответов
 * JSON- и JSONP-интерфейсы
 * Ответ возвращается в формате JSON. Если задан параметр callback, JSON-объект оборачивается в функцию, с названием, указанным в этом параметре (JSONP).
 * <p>
 * Синтаксис запроса
 * https://translate.yandex.net/api/v1.5/tr.json/translate
 * ? [key=<API-ключ>]
 * & [text=<переводимый текст>]
 * & [lang=<направление перевода>]
 * & [format=<формат текста>]
 * & [options=<опции перевода>]
 * & [callback=<имя callback-функции>]
 * Query-параметры
 * key
 * API-ключ. Выдается бесплатно.
 * <p>
 * text
 * Текст, который необходимо перевести.
 * <p>
 * В запросе можно использовать несколько параметров text.
 * <p>
 * Внимание.
 * Для исходного текста обязательно используйте URL-кодирование.
 * <p>
 * Ограничения:
 * <p>
 * Для POST-запросов максимальный размер передаваемого текста составляет 10 000 символов.
 * В GET-запросах ограничивается не размер передаваемого текста, а размер всей строки запроса, которая кроме текста может содержать и другие параметры.
 * <p>
 * Максимальный размер строки — от 2 до 10 КБ (зависит от версии используемого браузера).
 * <p>
 * lang
 * Направление перевода.
 * <p>
 * Может задаваться одним из следующих способов:
 * <p>
 * В виде пары кодов языков («с какого»-«на какой»), разделенных дефисом. Например, en-ru обозначает перевод с английского на русский.
 * В виде кода конечного языка (например ru). В этом случае сервис пытается определить исходный язык автоматически.
 * format
 * Формат текста.
 * <p>
 * Возможные значения:
 * <p>
 * plain — текст без разметки (значение по умолчанию);
 * html — текст в формате HTML.
 * options
 * В настоящее время доступна единственная опция — признак включения в ответ автоматически определенного языка переводимого текста. Этому соответствует значение 1 этого параметра.
 * <p>
 * Если язык переводимого текста задан в явном виде, т. е. параметр lang представлен в виде пары кодов, то первый код однозначно определяет исходный язык. Это означает, что параметр options не позволяет переключиться в режим автоматического определения языка. Однако он позволяет понять, правильно ли был указан исходный язык в параметре lang.
 * <p>
 * callback	Имя функции обратного вызова. Используется для получения JSONP-ответа.
 * Query-параметры
 * key
 * API-ключ. Выдается бесплатно.
 * <p>
 * text
 * Текст, который необходимо перевести.
 * <p>
 * В запросе можно использовать несколько параметров text.
 * <p>
 * Внимание.
 * Для исходного текста обязательно используйте URL-кодирование.
 * <p>
 * Ограничения:
 * <p>
 * Для POST-запросов максимальный размер передаваемого текста составляет 10 000 символов.
 * В GET-запросах ограничивается не размер передаваемого текста, а размер всей строки запроса, которая кроме текста может содержать и другие параметры.
 * <p>
 * Максимальный размер строки — от 2 до 10 КБ (зависит от версии используемого браузера).
 * <p>
 * lang
 * Направление перевода.
 * <p>
 * Может задаваться одним из следующих способов:
 * <p>
 * В виде пары кодов языков («с какого»-«на какой»), разделенных дефисом. Например, en-ru обозначает перевод с английского на русский.
 * В виде кода конечного языка (например ru). В этом случае сервис пытается определить исходный язык автоматически.
 * format
 * Формат текста.
 * <p>
 * Возможные значения:
 * <p>
 * plain — текст без разметки (значение по умолчанию);
 * html — текст в формате HTML.
 * options
 * В настоящее время доступна единственная опция — признак включения в ответ автоматически определенного языка переводимого текста. Этому соответствует значение 1 этого параметра.
 * <p>
 * Если язык переводимого текста задан в явном виде, т. е. параметр lang представлен в виде пары кодов, то первый код однозначно определяет исходный язык. Это означает, что параметр options не позволяет переключиться в режим автоматического определения языка. Однако он позволяет понять, правильно ли был указан исходный язык в параметре lang.
 * <p>
 * callback	Имя функции обратного вызова. Используется для получения JSONP-ответа.
 * Примечание. Все специальные символы должны быть экранированы.
 * Пример запроса
 * POST /api/v1.5/tr.json/translate?lang=en-ru&key=API-KEY HTTP/1.1
 * Host: translate.yandex.net
 * Accept:
 *//*
        Content-Length: 17
        Content-Type: application/x-www-form-urlencoded

        text=Hello World!
        Пример ответа
        HTTP/1.1 200 OK
        Server: nginx
        Content-Type: application/json; charset=utf-8
        Content-Length: 68
        Connection: keep-alive
        Keep-Alive: timeout=120
        X-Content-Type-Options: nosniff
        Date: Thu, 31 Mar 2016 10:50:20 GMT
        {
        "code": 200,
        "lang": "en-ru",
        "text": [
        "Здравствуй, Мир!"
        ]
        }
 */