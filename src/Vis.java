

import javax.swing.JPanel;
import java.awt.*;

public class Vis extends JPanel {

    private Color milly;
    private Stroke joseph;

    public Vis() {
        super();
        milly = Color.MAGENTA;
        joseph = new BasicStroke(10);
    }

    public void setCircleColor(Color c) {
        milly = c;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g1) {
        //TODO typecast Graphics to Graphics2D
        Graphics2D g = (Graphics2D)g1;
        //TODO draw some simple shapes
        g.setColor(milly);
        g.fillOval(50, 50, 100, 100);

        g.setStroke(joseph);
        g.drawLine(200, 300, 300, 500);
    }

}

