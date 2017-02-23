package main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Application implements IApplication {
    private List<Record> records;

    public Application() {
        records = new ArrayList<>();
		
		Record record01 = new Record(1,"M1",1,'A',10,4.99,'A');
        Record record02 = new Record(2,"M1",1,'A',12,5.99,'B');
        Record record03 = new Record(3,"M1",2,'A',8,5.99,'B');
        Record record04 = new Record(4,"M1",2,'A',10,8.99,'A');
        Record record05 = new Record(5,"M2",2,'C',10,14.99,'C');
        Record record06 = new Record(6,"M3",2,'B',6,8.99,'B');
        Record record07 = new Record(7,"M2",2,'A',10,6.99,'A');
        Record record08 = new Record(8,"M1",3,'B',10,11.99,'C');
        Record record09 = new Record(9,"M1",3,'B',5,8.99,'B');
        Record record10 = new Record(10,"M2",4,'C',18,5.99,'C');
		
        records = Arrays.asList(record01, record02, record03, record04, record05, record06, record07, record09, record10);
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
        /*
        double max = 0;
        for (Record r : records) {
            if (r.getCustomerType() == 'A'){
                if (r.getPrice() > max){
                    max = r.getPrice();
                }
            }
        }
        */
        Predicate<Record> filterPredicate = record -> record.getCustomerType() == 'A';
        Comparator<Record> comparator = (r1, r2) -> Double.compare(r1.getPrice(), r2.getPrice());

        double max = records.stream().filter(filterPredicate).max(comparator).get().getPrice();

        System.out.println("Maximum: " + max);
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