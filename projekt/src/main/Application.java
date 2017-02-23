package main;

import com.sun.org.apache.regexp.internal.RE;

import java.text.CharacterIterator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
		
        records = Arrays.asList(record01, record02, record03, record04, record05, record06, record07, record08, record09, record10);
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

    public double executeQuery01() {
        // aggregation - average
        // SELECT AVG(price) FROM data WHERE movie IN ('M1','M3') AND customerType = 'B'
        System.out.println("--- query01");

        Predicate<Record> filterPredicate1 = (r) -> r.getCustomerType() == 'B';
        Predicate<Record> filterPredicate2 = (r) -> Arrays.asList("M1", "M2").contains(r.getMovie());

        double avg = records.stream().filter(filterPredicate1).filter(filterPredicate2).mapToDouble((r) -> r.getPrice()).average().getAsDouble();

        System.out.println(avg);

        System.out.println();
        return avg;
    }

    public double executeQuery02() {
        // aggregation - max
        // SELECT MAX(price) FROM data WHERE (customerType = 'A')
        System.out.println("--- query02");

        Predicate<Record> filterPredicate = record -> record.getCustomerType() == 'A';
        Comparator<Record> comparator = (r1, r2) -> Double.compare(r1.getPrice(), r2.getPrice());

        double max = records.stream().filter(filterPredicate).max(comparator).get().getPrice();

        System.out.println("Maximum: " + max);
        System.out.println();

        return max;
    }

    public List<Record> executeQuery03() {
        // sort
        // SELECT * FROM data ORDER by customerType
        System.out.println("--- query03");

        Comparator<Record> comparator = (r1, r2) -> Character.compare(r1.getCustomerType(), r2.getCustomerType());
        List<Record> result = records.stream().sorted(comparator).collect(Collectors.toList());

        result.stream().forEach(System.out::println);

        System.out.println();

        return result;
    }

    public List<String> executeQuery04() {
        // sort
        // SELECT movie,week,price,customerType FROM data ORDER BY movie ASC,week DESC
        System.out.println("--- query04");

        Comparator<Record> comparator1 = (r1, r2) -> r1.getMovie().compareTo(r2.getMovie());
        Comparator<Record> comparator2 = (r1, r2) -> Integer.compare(r1.getWeek(), r2.getWeek());

        List<String> result = records.stream().sorted(comparator1)
                .sorted(comparator2.reversed())
                .map( (r) -> "Movie: " + r.getMovie() + ", Week: " + r.getWeek() + ", Price: " + r.getPrice() + ", Constumer Type: " + r.getCustomerType())
                .collect(Collectors.toList());

        //.forEach((r) -> System.out.println("Movie: " + r.getMovie() + ", Week: " + r.getWeek() + ", Price: " + r.getPrice() + ", Constumer Type: " + r.getCustomerType()));

        result.stream().forEach(System.out::println);

        System.out.println();
        return result;
    }

    public List<String> executeQuery05() {
        // filter
        // SELECT movie,week,price,rowID,seat FROM data WHERE (rowID = 'A' AND seat >= 10 AND seat <= 15)
        System.out.println("--- query05");

        Predicate<Record> filterPredicate1 = record -> record.getRowID() == 'A';
        Predicate<Record> filterPredicate2 = record -> record.getSeat() >= 10;
        Predicate<Record> filterPredicate3 = record -> record.getSeat() <= 15;

        List<String> result = records.stream().filter(filterPredicate1).filter(filterPredicate2).filter(filterPredicate3)
                .map((r) -> "Movie: " + r.getMovie() + ", Week: " + r.getWeek() + ", Price: " + r.getPrice() + ", Row: " + r.getRowID() + ", Seat: " + r.getSeat())
                .collect(Collectors.toList());

        result.stream().forEach(System.out::println);
        System.out.println();

        return result;
    }

    public List<String> executeQuery06() {
        // filter and sort
        // SELECT movie,week,price FROM data WHERE (customerType = 'A') ORDER BY price DESC
        System.out.println("--- query06");

        Predicate<Record> filterPredicate = record -> record.getCustomerType() == 'A';
        Comparator<Record> comparator = (r1, r2) -> Double.compare(r1.getPrice(), r2.getPrice());
        List<String> result = records.stream()
                .filter(filterPredicate)
                .sorted(comparator)
                .map((r) -> "Movie: " + r.getMovie() + ", Week: " + r.getWeek() + ", Price: " + r.getPrice())
                .collect(Collectors.toList());

        result.stream().forEach(System.out::println);
        System.out.println();

        return result;
    }

    public List<String> executeQuery07() {
        // filter, sort and limit
        // SELECT movie,week,price FROM data WHERE (movie = 'M1') ORDER BY price DESC LIMIT 3
        System.out.println("--- query07");

        Predicate<Record> filterPredicate = record -> record.getMovie() == "M1";
        Comparator<Record> comparator = (r1, r2) -> Double.compare(r1.getPrice(), r2.getPrice());

        List<String> result = records.stream().filter(filterPredicate).sorted(comparator.reversed()).limit(3)
                .map((r) -> "Movie: " + r.getMovie() + ", Week: " + r.getWeek() + ", Price: " + r.getPrice())
                .collect(Collectors.toList());

        result.stream().forEach(System.out::println);
        System.out.println();

        return result;
    }

    public long executeQuery08() {
        // aggregation - count
        //SELECT COUNT(*) FROM data WHERE (movie IN ('M1','M2'))
        System.out.println("--- query08");

        Predicate<Record> filterPredicate = (r) -> Arrays.asList("M1", "M2").contains(r.getMovie());

        long result = records.stream().filter(filterPredicate).count();

        System.out.println(result + "\n");
        return result;
    }

    public List<String> executeQuery09() {
        // aggregation - group
        // SELECT customerType,COUNT(*) FROM data GROUP BY customerType
        System.out.println("--- query09");

        List<String> result = records.stream().collect(Collectors.groupingBy((r) -> r.getCustomerType()))
                .entrySet().stream().map(e -> e.getKey() + " " + e.getValue().size()).collect(Collectors.toList());

        result.stream().forEach(System.out::println);
        System.out.println();

        return result;
    }

    public List<String> executeQuery10() {
        // aggregation - group and filter
        // SELECT customerType,COUNT(*) FROM data WHERE (week >=1 AND week <= 3) GROUP BY customerType
        System.out.println("--- query10");

        Predicate<Record> filterPredicateG = record -> record.getWeek() >= 1;
        Predicate<Record> filterPredicateS = record -> record.getWeek() <= 3;

        List<String> result = records.stream().filter(filterPredicateG).filter(filterPredicateS)
                .collect(Collectors.groupingBy((r) -> r.getCustomerType()))
                .entrySet().stream().map(e -> e.getKey() + " " + e.getValue().size()).collect(Collectors.toList());

        result.stream().forEach(System.out::println);

        return result;
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