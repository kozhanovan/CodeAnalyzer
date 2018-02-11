package kan.analyzer.core;

import java.util.Objects;

public class ClassInfo implements IComponent {

    private String name;

    private AbstractionCalc abstractionCalc;

    private InstabilityCalc instabilityCalc;

    @Override
    public double getAbstraction() {
        return getAbstractionCalc().getAbstraction();
    }

    @Override
    public double getInstability() {
        return getInstabilityCalc().getInstability();
    }

    @Override
    public String getName() {
        return name;
    }

    public ClassInfo withName( String name ) {
        this.name = name;
        return this;
    }

    public AbstractionCalc getAbstractionCalc() {
        return abstractionCalc;
    }

    public ClassInfo withAbstractCalc( AbstractionCalc abstractionCalc ) {
        this.abstractionCalc = abstractionCalc;
        return this;
    }

    public InstabilityCalc getInstabilityCalc() {
        return instabilityCalc;
    }

    public ClassInfo withInstabilityCalc( InstabilityCalc instabilityCalc ) {
        this.instabilityCalc = instabilityCalc;
        return this;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        ClassInfo classInfo = (ClassInfo) o;
        return Objects.equals( name, classInfo.name );
    }

    @Override
    public int hashCode() {
        return Objects.hash( name );
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                       "name='" + name + '\'' +
                       ", instabilityCalc=" + instabilityCalc +
                       '}';
    }
}
