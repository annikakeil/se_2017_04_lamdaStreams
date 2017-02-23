import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Application implements IApplication {
    private final String userDirectory = System.getProperty("user.dir");
    private final String fileSeparator = System.getProperty("file.separator");
    private final String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    private final String databaseFile = dataDirectory + "exercise.db";

    private Connection connection;
    private String driverName = "jdbc:hsqldb:";
    private String username = "sa";
    private String password = "";

    public void setupConnection() {
        System.out.println("--- setupConnection");

        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String databaseURL = driverName + databaseFile;
            connection = DriverManager.getConnection(databaseURL,username,password);
            System.out.println("connection : " + connection);
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getStatus() {
        try {
            System.out.println("--- status");
            System.out.println("userDirectory : " + userDirectory);
            System.out.println("dataDirectory : " + dataDirectory);
            System.out.println("databaseFile  : " + databaseFile);
            System.out.println("driverName    : " + driverName);
            System.out.println("username      : " + username);
            System.out.println("password      : " + password);
            System.out.println("connection    : " + connection.hashCode());
            System.out.println("schema        : " + connection.getSchema());
            System.out.println("status        : " + connection.isClosed());
            System.out.println();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public synchronized void update(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlStatement);
            if (result == -1)
                System.out.println("error executing " + sqlStatement);
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void dropTable() {
        System.out.println("--- dropTable");

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("DROP TABLE data");
        System.out.println("sqlStringBuilder : " + sqlStringBuilder.toString());

        update(sqlStringBuilder.toString());

        System.out.println();
    }

    public void createTable() {
        System.out.println("--- createTable");

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE data ").append(" ( ");
        sqlStringBuilder.append("id INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("movie VARCHAR(25) NOT NULL").append(",");
        sqlStringBuilder.append("week INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("rowID VARCHAR(1) NOT NULL").append(",");
        sqlStringBuilder.append("seat INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("price DOUBLE NOT NULL").append(",");
        sqlStringBuilder.append("customerType VARCHAR(1) NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(" )");
        System.out.println("sqlStringBuilder : " + sqlStringBuilder.toString());

        update(sqlStringBuilder.toString());

        System.out.println();
    }

    public void dump(ResultSet resultSet) {
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int maximumNumberColumns = resultSetMetaData.getColumnCount();
            int i;
            Object object;

            for (;resultSet.next();) {
                for (i = 0;i < maximumNumberColumns;++i) {
                    object = resultSet.getObject(i + 1);
                    System.out.print(object.toString() + " ");
                }
                System.out.println(" ");
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public synchronized void queryDump(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            dump(resultSet);
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    private String buildSQLStatement(int id,String movie,int week,String rowID,int seat,double price,String customerType) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO data (id,movie,week,rowID,seat,price,customerType) VALUES (");
        stringBuilder.append(id).append(",");
        stringBuilder.append("'").append(movie).append("'").append(",");
        stringBuilder.append(week).append(",");
        stringBuilder.append("'").append(rowID).append("'").append(",");
        stringBuilder.append(seat).append(",");
        stringBuilder.append(price).append(",");
        stringBuilder.append("'").append(customerType).append("'");
        stringBuilder.append(")");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    private void insertData() {
        update(buildSQLStatement(1,"M1",1,"A",10,4.99,"A"));
        update(buildSQLStatement(2,"M1",1,"A",12,5.99,"B"));
        update(buildSQLStatement(3,"M1",2,"A",8,5.99,"B"));
        update(buildSQLStatement(4,"M1",2,"A",10,8.99,"A"));
        update(buildSQLStatement(5,"M2",2,"C",10,14.99,"C"));
        update(buildSQLStatement(6,"M3",2,"B",6,8.99,"B"));
        update(buildSQLStatement(7,"M2",2,"A",10,6.99,"A"));
        update(buildSQLStatement(8,"M1",3,"B",10,11.99,"C"));
        update(buildSQLStatement(9,"M1",3,"B",5,8.99,"B"));
        update(buildSQLStatement(10,"M2",4,"C",18,5.99,"C"));
        System.out.println();
    }

    public void init() {
        insertData();
    }

    // aggregation - average
    public void executeSQL01() {
        System.out.println("--- executeSQL01");
        String sqlStatement = "SELECT AVG(price) FROM data WHERE movie IN ('M1','M3') AND customerType = 'B'";
        queryDump(sqlStatement);
        System.out.println();
    }

    // aggregation - max
    public void executeSQL02() {
        System.out.println("--- executeSQL02");
        String sqlStatement = "SELECT MAX(price) FROM data WHERE (customerType = 'A')";
        queryDump(sqlStatement);
        System.out.println();
    }

    // sort
    public void executeSQL03() {
        System.out.println("--- executeSQL03");
        String sqlStatement = "SELECT * FROM data ORDER by customerType";
        queryDump(sqlStatement);
        System.out.println();
    }

    // sort
    public void executeSQL04() {
        System.out.println("--- executeSQL04");
        String sqlStatement = "SELECT movie,week,price,customerType FROM data ORDER BY movie ASC,week DESC";
        queryDump(sqlStatement);
        System.out.println();
    }

    // filter
    public void executeSQL05() {
        System.out.println("--- executeSQL05");
        String sqlStatement = "SELECT movie,week,price,rowID,seat FROM data WHERE (rowID = 'A' AND seat >= 10 AND seat <= 15)";
        queryDump(sqlStatement);
        System.out.println();
    }

    // filter and sort
    public void executeSQL06() {
        System.out.println("--- executeSQL06");
        String sqlStatement = "SELECT movie,week,price FROM data WHERE (customerType = 'A') ORDER BY price DESC";
        queryDump(sqlStatement);
        System.out.println();
    }

    // filter, sort and limit
    public void executeSQL07() {
        System.out.println("--- executeSQL07");
        String sqlStatement = "SELECT movie,week,price FROM data WHERE (movie = 'M1') ORDER BY price DESC LIMIT 3";
        queryDump(sqlStatement);
        System.out.println();
    }

    // aggregation - count
    public void executeSQL08() {
        System.out.println("--- executeSQL08");
        String sqlStatement = "SELECT COUNT(*) FROM data WHERE (movie IN ('M1','M2'))";
        queryDump(sqlStatement);
        System.out.println();
    }

    // aggregation - group
    public void executeSQL09() {
        System.out.println("--- executeSQL09");
        String sqlStatement = "SELECT customerType,COUNT(*) FROM data GROUP BY customerType";
        queryDump(sqlStatement);
        System.out.println();
    }

    // aggregation - group and filter
    public void executeSQL10() {
        System.out.println("--- executeSQL10");
        String sqlStatement = "SELECT customerType,COUNT(*) FROM data WHERE (week >=1 AND week <= 3) " +
                              "GROUP BY customerType";
        queryDump(sqlStatement);
        System.out.println();
    }

    public void shutdown() {
        System.out.println("--- shutdown");

        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
            System.out.println("isClosed : " + connection.isClosed());
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public static void main(String... args) {
        Application application = new Application();
        application.setupConnection();
        application.getStatus();
        application.dropTable();
        application.createTable();
        application.init();
        application.executeSQL01();
        application.executeSQL02();
        application.executeSQL03();
        application.executeSQL04();
        application.executeSQL05();
        application.executeSQL06();
        application.executeSQL07();
        application.executeSQL08();
        application.executeSQL09();
        application.executeSQL10();
        application.shutdown();
    }
}