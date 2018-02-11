package kan.analyzer.core;

import java.util.Objects;

public class Link {

    private String out;

    private String in;

    public Link() {
    }

    public Link( String out, String in ) {
        this.out = out;
        this.in = in;
    }

    public String getOut() {
        return out;
    }

    public void setOut( String out ) {
        this.out = out;
    }

    public String getIn() {
        return in;
    }

    public void setIn( String in ) {
        this.in = in;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Link link = (Link) o;
        return Objects.equals( out, link.out ) &&
                       Objects.equals( in, link.in );
    }

    @Override
    public int hashCode() {
        return Objects.hash( out, in );
    }

    @Override
    public String toString() {
        return "Link{" +
                       "out='" + out + '\'' +
                       ", in='" + in + '\'' +
                       '}';
    }
}
