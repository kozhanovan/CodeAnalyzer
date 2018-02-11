package kan.analyzer.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InstabilityCalcTest {

    private static final int OUT = 4;

    private static final int IN = 2;

    private static final double DELTA = 0.001;

    private InstabilityCalc calc;

    @Before
    public void setUp() {
        calc = new InstabilityCalc();
        calc.setIn( IN );
        calc.setOut( OUT );
    }

    @Test
    public void getInstability() {
        // Given InstabilityCalc
        // When getInstability is called
        double actual = calc.getInstability();

        // Then the result is OUT / (IN + OUT)
        Assert.assertEquals( "Instability is calculated wrong",(double)OUT / (IN + OUT), actual, DELTA );
    }

    @Test
    public void getInstabilityZero() {
        // Given InstabilityCalc with in == out == 0
        InstabilityCalc c = new InstabilityCalc( 0, 0 );

        // When getInstability is called
        double actual = c.getInstability();

        // Then the result is 0
        Assert.assertEquals( "Instability is not 0", 0, actual, DELTA );
    }

    @Test
    public void getOut() {
        // Given InstabilityCalc
        // When getOut is called
        int actual = calc.getOut();

        // Then OUT is returned
        Assert.assertEquals( "Outs are not equal", OUT, actual );
    }

    @Test
    public void setOut() {
        // Given InstabilityCalc
        int newOut = 999;

        // When setOut is called
        calc.setOut( newOut );

        // Then out is set
        Assert.assertEquals( "Out is not set", newOut, calc.getOut() );
    }

    @Test
    public void getIn() {
        // Given InstabilityCalc
        // When getIn is called
        int actual = calc.getIn();

        // Then IN is returned
        Assert.assertEquals( "Ins are not equal", IN, actual );
    }

    @Test
    public void setIn() {
        // Given InstabilityCalc
        int newIn = 888;

        // When setIn is called
        calc.setIn( newIn );

        // Then in is set
        Assert.assertEquals( "In is not set", newIn, calc.getIn() );
    }

    @Test
    public void testToString() {
        // Given InstabilityCalc
        // When toString method is called
        String actual = calc.toString();
        String expected = "InstabilityCalc{out=" + OUT + ", in=" + IN + '}';

        // Then the result is "InstabilityCalc{out=4, in = 2}"
        Assert.assertEquals( "toString result is not expected", expected, actual );
    }
}