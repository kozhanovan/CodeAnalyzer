package kan.analyzer.core;

import java.util.ArrayList;

public class ClassInfoList extends ArrayList<ClassInfo> {

    public boolean containsClass( String name ) {
        ClassInfo ci = stream().filter( c -> c.getName().equals( name ) ).findFirst().orElse( null );
        return null != ci;
    }

    public InstabilityCalc getInstabilityCalc( String name ) {
        ClassInfo ci = stream().filter( c -> c.getName().equals( name ) ).findFirst().orElse( null );
        if ( null == ci ) {
            return null;
        }
        return ci.getInstabilityCalc();
    }
}
