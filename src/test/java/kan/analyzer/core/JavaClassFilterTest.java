package kan.analyzer.core;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class JavaClassFilterTest {

    @Test
    public void acceptClass() {
        // Given .class file
        File f = new File( "a.class" );

        // When accept method is called
        boolean accept = new JavaClassFilter().accept( f );

        // Then the result is true
        Assert.assertTrue( ".class file is not accepted", accept );
    }

    @Test
    public void acceptNotAClass() {
        // Given not a .class file
        File f = new File( "a.class.file" );

        // When accept method is called
        boolean accept = new JavaClassFilter().accept( f );

        // Then the result is false
        Assert.assertFalse( "Not a .class file is accepted", accept );
    }

    @Test
    public void acceptDirectory() {
        // Given user home directory
        File f = new File( System.getProperty( "user.dir" ) );

        // When accept method is called
        boolean accept = new JavaClassFilter().accept( f );

        // Then the result is true
        Assert.assertTrue( "Directory is not accepted", accept );
    }
}