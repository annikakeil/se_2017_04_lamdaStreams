package test;

import main.Record;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Application {

    public main.Application app = new main.Application();

    @Test
    public void test01() {
        assertEquals(app.executeQuery01(), 6.989999999999999, 1E-10);
    }

    @Test
    public void test02() {
        assertEquals(app.executeQuery02(), 8.99, 1E-10);
    }

    @Test
    public void test03() {
        List<Integer> expect = Arrays.asList(1, 4, 7, 2, 3, 6, 9, 5, 8, 10);

        List<Record> qResult = app.executeQuery03();

        for (int i = 0; i < expect.size(); i++) {
            assertEquals(expect.get(i), (Integer) qResult.get(i).getId());
        }
    }

    @Test
    public void test04() {
        List<String> expect = Arrays.asList(
                "Movie: M2, Week: 4, Price: 5.99, Constumer Type: C",
                "Movie: M1, Week: 3, Price: 11.99, Constumer Type: C",
                "Movie: M1, Week: 3, Price: 8.99, Constumer Type: B",
                "Movie: M1, Week: 2, Price: 5.99, Constumer Type: B",
                "Movie: M1, Week: 2, Price: 8.99, Constumer Type: A",
                "Movie: M2, Week: 2, Price: 14.99, Constumer Type: C",
                "Movie: M2, Week: 2, Price: 6.99, Constumer Type: A",
                "Movie: M3, Week: 2, Price: 8.99, Constumer Type: B",
                "Movie: M1, Week: 1, Price: 4.99, Constumer Type: A",
                "Movie: M1, Week: 1, Price: 5.99, Constumer Type: B"
        );

        List<String> qResult = app.executeQuery04();

        for (int i = 0; i < expect.size(); i++) {
            assertEquals(expect.get(i), qResult.get(i));
        }
    }

    @Test
    public void test05() {
        List<String> expect = Arrays.asList(
                "Movie: M1, Week: 1, Price: 4.99, Row: A, Seat: 10",
                "Movie: M1, Week: 1, Price: 5.99, Row: A, Seat: 12",
                "Movie: M1, Week: 2, Price: 8.99, Row: A, Seat: 10",
                "Movie: M2, Week: 2, Price: 6.99, Row: A, Seat: 10"
        );

        List<String> qResult = app.executeQuery05();

        for (int i = 0; i < expect.size(); i++) {
            assertEquals(expect.get(i), qResult.get(i));
        }
    }

    @Test
    public void test06() {
        List<String> expect = Arrays.asList(
                "Movie: M1, Week: 1, Price: 4.99",
                "Movie: M2, Week: 2, Price: 6.99",
                "Movie: M1, Week: 2, Price: 8.99"
        );

        List<String> qResult = app.executeQuery06();

        for (int i = 0; i < expect.size(); i++) {
            assertEquals(expect.get(i), qResult.get(i));
        }
    }

    @Test
    public void test07() {
        List<String> expect = Arrays.asList(
                "Movie: M1, Week: 3, Price: 11.99",
                "Movie: M1, Week: 2, Price: 8.99",
                "Movie: M1, Week: 3, Price: 8.99"
        );

        List<String> qResult = app.executeQuery07();

        for (int i = 0; i < expect.size(); i++) {
            assertEquals(expect.get(i), qResult.get(i));
        }
    }

    @Test
    public void test08() {
         assertEquals(app.executeQuery08(), 9);
    }

    @Test
    public void test09() {
        List<String> expect = Arrays.asList(
                "A 3",
                "B 4",
                "C 3"
        );

        List<String> qResult = app.executeQuery09();

        for (int i = 0; i < expect.size(); i++) {
            assertEquals(expect.get(i), qResult.get(i));
        }
    }

    @Test
    public void test10() {
        List<String> expect = Arrays.asList(
                "A 3",
                "B 4",
                "C 2"
        );

        List<String> qResult = app.executeQuery10();

        for (int i = 0; i < expect.size(); i++) {
            assertEquals(expect.get(i), qResult.get(i));
        }
    }
}