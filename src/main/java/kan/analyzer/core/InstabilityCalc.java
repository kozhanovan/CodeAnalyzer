package kan.analyzer.core;

public class InstabilityCalc {

    private int out;

    private int in;

    public InstabilityCalc() {
    }

    public InstabilityCalc( int out, int in ) {
        setOut( out );
        setIn( in );
    }

    public double getInstability() {
        if ( 0 == in && 0 == out ) {
            return 0;
        }

        return (double) out / ( out + in );
    }

    public int getOut() {
        return out;
    }

    public void setOut( int out ) {
        this.out = out;
    }

    public int getIn() {
        return in;
    }

    public void setIn( int in ) {
        this.in = in;
    }

    @Override
    public String toString() {
        return "InstabilityCalc{" +
                       "out=" + out +
                       ", in=" + in +
                       '}';
    }
}
