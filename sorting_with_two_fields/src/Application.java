import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Application {

    private List<Record> records;

    public Application() {
        init();
    }

    public void init() {
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
        records = Arrays.asList(record01,record02,record03,record04,record05,record06,record07,record09,record10);
    }

    public static void main(String... args) {
        Application application = new Application();
        application.execute();
        application.executeSQL02();
    }

    public void execute() {
        ReportComparator reportComparator = new ReportComparator();
        Collections.sort(records,reportComparator);
        System.out.println(records);
    }

    // aggregation - max
    public void executeSQL02(){
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

}