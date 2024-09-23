package tr.com.mcay.springbootmodulerlearning;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tr.com.mcay.springbootmodulerlearning.utils.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3), "2 + 3 should equal 5");
    }
}