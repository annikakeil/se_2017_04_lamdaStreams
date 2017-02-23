package main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Application implements IApplication {
    private ArrayList<Record> records;

    public Application() {
        records = new ArrayList<>();
    }

    public long convertDateStringToUnixSeconds(String dateString) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return dateFormat.parse(dateString).getTime();
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }
        return -1;
    }

    public void init() {
    }

    public void printRecords() {
        records.stream().forEach(System.out::println);
    }

    public void executeQuery01() {
        System.out.println("--- query01");
        System.out.println();
    }

    public void executeQuery02() {
        System.out.println("--- query02");
        System.out.println();
    }

    public void executeQuery03() {
        System.out.println("--- query03");
        System.out.println();
    }

    public void executeQuery04() {
        System.out.println("--- query04");
        System.out.println();
    }

    public void executeQuery05() {
        System.out.println("--- query05");
        System.out.println();
    }

    public void executeQuery06() {
        System.out.println("--- query06");
        System.out.println();
    }

    public void executeQuery07() {
        System.out.println("--- query07");
        System.out.println();
    }

    public void executeQuery08() {
        System.out.println("--- query08");
        System.out.println();
    }

    public void executeQuery09() {
        System.out.println("--- query09");
        System.out.println();
    }

    public void executeQuery10() {
        System.out.println("--- query10");
        System.out.println();
    }

    public static void main(String... args) {
        Application application = new Application();
        application.init();
        application.printRecords();
        application.executeQuery01();
        application.executeQuery02();
        application.executeQuery03();
        application.executeQuery04();
        application.executeQuery05();
        application.executeQuery06();
        application.executeQuery07();
        application.executeQuery08();
        application.executeQuery09();
        application.executeQuery10();
    }
}