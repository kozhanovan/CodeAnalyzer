package kan.analyzer.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkTest {

    private static final String TEST_OUT = "test_out";

    private static final String TEST_IN = "test_in";

    private Link link;

    @Before
    public void setup() {
        link = new Link();
        link.setOut( TEST_OUT );
        link.setIn( TEST_IN );
    }

    @Test
    public void getOut() {
        // Given existing link
        // When getOut is called
        String actual = link.getOut();

        // Then result is equal to TEST_OUT
        Assert.assertEquals( "Out values are not equal", TEST_OUT, actual );
    }

    @Test
    public void setOut() {
        // Given a string
        String newValue = "new_value";

        // When setOut method is called
        link.setOut( newValue );

        // Then new out equals string
        Assert.assertEquals( "Outs are not equal", newValue, link.getOut() );
    }

    @Test
    public void getIn() {
        // Given existing link
        // When getIn is called
        String actual = link.getIn();

        // Then result is equal to TEST_IN
        Assert.assertEquals( "Out values are not equal", TEST_IN, actual );
    }

    @Test
    public void setIn() {
        // Given a string
        String newValue = "new_value";

        // When setIn method is called
        link.setIn( newValue );

        // Then new out equals string
        Assert.assertEquals( "Ins are not equal", newValue, link.getIn() );
    }

    @Test
    public void equalsEqual() {
        // Given another Link object with values similar to link
        Link another = new Link( TEST_OUT, TEST_IN );

        // When equals method is called
        boolean equals = another.equals( link );

        // Then the result is true
        Assert.assertTrue( "Links are not equal", equals );
    }

    @Test
    public void equalsNull() {
        // Given existing Link instance
        // When is it compared to null
        boolean equals = link.equals( null );

        // Then the result is false
        Assert.assertFalse( "Link compared to null returned true", equals );
    }

    @Test
    public void equalsSelf() {
        // Given existing Link instance
        // When is it compared to self
        boolean equals = link.equals( link );

        // Then the result is true
        Assert.assertTrue( "Self comparison is false", equals );
    }

    @Test
    public void equalsAnotherClass() {
        // Given existing Link instance
        // When is it compared with some other class instance
        boolean equals = link.equals( "" );

        // Then the result is false
        Assert.assertFalse( "Comparison to another class is true", equals );
    }

    @Test
    public void equalsDifferentOut() {
        // Given two Link instances with different out and same in
        Link link2 = new Link( "another out", TEST_IN );

        // When they are compared
        boolean equals = link.equals( link2 );

        // Then the result is false
        Assert.assertFalse( "Links with different outs are same", equals );
    }

    @Test
    public void equalsDifferentIn() {
        // Given two Link instances with different in and same out
        Link link2 = new Link( TEST_OUT, "another in" );

        // When they are compared
        boolean equals = link.equals( link2 );

        // Then the result is false
        Assert.assertFalse( "Links with different ins are same", equals );
    }

    @Test
    public void testHashCode() {
        // Given two equal Link instances
        Link link2 = new Link( TEST_OUT, TEST_IN );

        // When hashCode is called on them
        int actual = link.hashCode();
        int expected = link2.hashCode();

        // Then the result is the same
        Assert.assertEquals( "Hash codes are different", expected, actual );
    }

    @Test
    public void testToString() {
        // Given link instance
        // When toString method is called
        String s = link.toString();

        // Then the result is "Link{out='TEST_OUT', in='TEST_IN'"
        Assert.assertEquals( "Unexpected Link.toString() output", "Link{out='" + TEST_OUT
                                                                          + "', in='" + TEST_IN + "'}", s );
    }
}