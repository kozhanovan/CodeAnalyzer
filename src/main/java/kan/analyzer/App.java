package kan.analyzer;

import kan.analyzer.core.Analyzer;
import kan.analyzer.core.IComponent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Logger LOG = Logger.getLogger( App.class.getName() );

    public static void main( String[] args ) throws Exception {
        if ( args.length == 0 ) {
            LOG.log( Level.INFO, "Usage: App <root>" );
            return;
        }
        String root = args[0];
        Analyzer analyzer = new Analyzer( root );
        analyzer.runAnalysis();

        for ( IComponent component : analyzer.listClasses() ) {
            System.out.println( component.getName() );
            System.out.print( "  A = " );
            System.out.println( component.getAbstraction() );
            System.out.print( "  I = " );
            System.out.println( component.getInstability() );
            System.out.println( "========" );
        }
    }
}
