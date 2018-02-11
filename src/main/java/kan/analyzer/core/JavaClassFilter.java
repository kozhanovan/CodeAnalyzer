package kan.analyzer.core;

import java.io.File;
import java.io.FileFilter;

public class JavaClassFilter implements FileFilter {

    public boolean accept( File file ) {
        return file.isDirectory() || file.getName().endsWith( ".class" );
    }
}
