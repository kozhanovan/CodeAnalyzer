package kan.analyzer.core;

import javassist.ClassPool;
import javassist.NotFoundException;

import javax.naming.CompoundName;
import javax.naming.InvalidNameException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Analyzer {

    private static final Logger LOG = Logger.getLogger( Analyzer.class.getName() );

    private String root;

    private boolean analysisComplete = false;

    private ClassInfoList classes;

    private List<Link> links = new ArrayList<>();

    public Analyzer() {

    }

    public Analyzer( String root ) {
        setRoot( root );
    }

    public String getRoot() {
        return root;
    }

    public void setRoot( String root ) {
        analysisComplete = false;
        this.root = root;
    }

    public List<IComponent> listClasses() throws NotFoundException {
        if ( !analysisComplete ) {
            runAnalysis();
        }

        return new ArrayList<>( classes );
    }

    public void runAnalysis() throws NotFoundException {
        ClassPool cp = ClassPool.getDefault();
        cp.insertClassPath( root );
        List<String> classFiles = getListOfClassFiles( root );
        classes = toListOfClasses( classFiles );

        for ( ClassInfo cl : classes ) {
            defineLinksForClass( cl );
        }

        for ( Link link : links ) {
            updateInstabilityInfo( link );
        }

        analysisComplete = true;
    }

    private void updateInstabilityInfo( Link link ) {
        InstabilityCalc calc = classes.getInstabilityCalc( link.getOut() );
        calc.setOut( calc.getOut() + 1 );

        calc = classes.getInstabilityCalc( link.getIn() );
        calc.setIn( calc.getIn() + 1 );
    }

    private void defineLinksForClass( ClassInfo cl ) throws NotFoundException {
        ClassPool cp = ClassPool.getDefault();
        Collection refClasses = cp.get( cl.getName() ).getRefClasses();
        for ( Object refClass : refClasses ) {
            String name = refClass.toString();
            if ( !cl.getName().equals( name ) && classes.containsClass( name ) ) {
                links.add( new Link( cl.getName(), name ) );
            }
        }
    }

    private List<String> getListOfClassFiles( String root ) {
        List<String> result = new ArrayList<>();
        File[] files = new File( root ).listFiles( new JavaClassFilter() );
        if ( null == files ) {
            return result;
        }
        for ( File file : files ) {
            File[] filesInDir = file.listFiles();
            if ( null == filesInDir ) {
                result.add( file.getAbsolutePath() );
            } else {
                result.addAll( getListOfClassFiles( file.getAbsolutePath() ) );
            }
        }
        return result;
    }

    private ClassInfoList toListOfClasses( List<String> classFiles ) {
        ClassInfoList result = new ClassInfoList();
        Properties p = new Properties();
        p.setProperty( "jndi.syntax.direction", "right_to_left" );
        p.setProperty( "jndi.syntax.separator", "." );
        for ( String fileName : classFiles ) {
            try {
                CompoundName name = new CompoundName( fileName.replaceAll( System.getProperty( "file.separator" ), "." ), p );
                name.remove( 0 );
                String className = extractClassName( name );
                ClassInfo ci = new ClassInfo().withName( className ).withInstabilityCalc( new InstabilityCalc() )
                                       .withAbstractCalc( new AbstractionCalc( className ) );
                result.add( ci );
            } catch ( InvalidNameException e ) {
                LOG.log( Level.SEVERE, e.getMessage(), e );
            }
        }
        return result;
    }

    private String extractClassName( CompoundName name ) {
        ClassPool cp = ClassPool.getDefault();
        for ( int i = 1; i < name.size(); i++ ) {
            try {
                String s = name.getPrefix( i ).toString();
                cp.get( s );
                return s;
            } catch ( NotFoundException e ) {
                // Just swallow
            }
        }
        return null;
    }
}
