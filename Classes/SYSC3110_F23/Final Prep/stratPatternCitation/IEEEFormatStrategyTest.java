import org.junit.Test;

import static org.junit.Assert.assertEquals;

//IEEEFormatStrategyTest.java
public class IEEEFormatStrategyTest {
    @Test
    public void testFormatName(){
        CitationStrategy strategy = new IEEEFormatStrategy();
        assertEquals("B. Esfandiari", strategy.formatName("Babak", "Esfandiari"));
    }
}
