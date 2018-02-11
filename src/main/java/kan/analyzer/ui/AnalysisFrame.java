package kan.analyzer.ui;

import kan.analyzer.core.IComponent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnalysisFrame extends JPanel {

    public static final int BORDER = 10;

    private transient List<IComponent> components;

    public AnalysisFrame( List<IComponent> components ) {
        this.components = components;
    }

    @Override
    public void paint( Graphics graphics ) {
        super.paint( graphics );
        Dimension size = getSize();
        int len = Math.min( (int) size.getWidth() - 2 * BORDER, (int) size.getHeight() - 2* BORDER );
        int zx = BORDER;
        int zy = (int)size.getHeight() - BORDER;
        graphics.drawLine( zx, zy, (int) size.getWidth() - BORDER, zy );
        graphics.drawLine( zx, BORDER, zx, zy );
        graphics.drawLine( zx, zy - len, BORDER + len, zy );
        graphics.setColor( Color.RED );

        for ( IComponent component : components ) {
            int x = (int)(len * component.getInstability()) + zx;
            int y = zy - (int) ( len * component.getAbstraction() );
            graphics.fillOval( x, y, 5, 5 );
        }
    }
}
