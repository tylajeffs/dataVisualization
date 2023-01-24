

import javax.swing.JPanel;
import java.awt.*;

public class Vis extends JPanel {

    private Color currentColor;
    private Stroke currentStroke;
    private String currentText;
    private int fontSize = 20;

    public Vis() {
        super();
        currentColor = Color.MAGENTA;
        currentStroke = new BasicStroke(10);
        currentText = " ";
    }

    public void setCircleColor(Color c) {
        currentColor = c;
        repaint();
    }

    public void setTextContent(String s) {
        currentText = s;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g1) {
        //TODO typecast Graphics to Graphics2D
        Graphics2D g = (Graphics2D)g1;
        //TODO draw some simple shapes
        g.setColor(currentColor);
        //g.fillOval(50, 50, 100, 100);

        //g.setStroke(currentStroke);
        //g.drawLine(200, 300, 300, 500);

        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g.drawString(currentText, 75, 100);
    }

}

