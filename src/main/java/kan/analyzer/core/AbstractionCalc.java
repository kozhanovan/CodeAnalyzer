package kan.analyzer.core;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractionCalc {

    private static final int ABSTRACT_MODIFIER = 1024;

    private static final int FINAL_MODIFIER = 16;

    private static final Logger LOG = Logger.getLogger( AbstractionCalc.class.getName() );

    private String name;

    public AbstractionCalc( String name ) {
        setName( name );
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getAbstraction() {
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass ctClass = cp.get( name );
            if ( ctClass.isInterface()
                         || classHasModifier( ctClass, ABSTRACT_MODIFIER )
                         || classHasModifier( ctClass, FINAL_MODIFIER ) ) {
                return 1d;
            }
        } catch ( NotFoundException e ) {
            LOG.log( Level.SEVERE, e.getMessage(), e );
        }
        return 0d;
    }

    private boolean classHasModifier( CtClass ctClass, int modifier ) {
        int modifiers = ctClass.getModifiers();
        return ( modifiers & modifier ) != 0;
    }
}
