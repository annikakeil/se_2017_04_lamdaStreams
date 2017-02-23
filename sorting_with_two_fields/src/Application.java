import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Application {
    private List<Record> records;

    public Application() {
        init();
    }

    public void init() {
        Record record01 = new Record("A",1);
        Record record02 = new Record("C",2);
        Record record03 = new Record("B",1);
        Record record04 = new Record("B",2);
        Record record05 = new Record("A",3);
        Record record06 = new Record("C",2);
        Record record07 = new Record("A",3);
        Record record09 = new Record("A",1);
        Record record10 = new Record("B",2);
        records = Arrays.asList(record01,record02,record03,record04,record05,record06,record07,record09,record10);
    }

    public void execute() {
        ReportComparator reportComparator = new ReportComparator();
        Collections.sort(records,reportComparator);
        System.out.println(records);
    }

    public static void main(String... args) {
        Application application = new Application();
        application.execute();
    }
}