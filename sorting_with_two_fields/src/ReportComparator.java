import java.util.Comparator;
import com.google.common.collect.ComparisonChain;

public class ReportComparator implements Comparator<Record> {
    public int compare(Record record01,Record record02) {
        return ComparisonChain.start()
                .compare(record01.getCategory(),record02.getCategory())
                .compare(record02.getSubCategory(),record01.getSubCategory())
                .result();
    }
}