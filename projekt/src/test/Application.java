package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Application {
    
    public void setup() {

    }

    @Test
    public void test01() {
        assertEquals("1284131 is prime",true,isPrime(1284131));
    }

    @Test
    public void test02() {
        assertEquals("1284051 is composite",false,isPrime(1284051));
    }
}