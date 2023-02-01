

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Vis extends JPanel {

    private Color currentColor;
    private Stroke currentStroke;
    private String currentText;
    private int fontSize = 20;
    private String chartType = "";
    private java.util.List<String> labels = new ArrayList<>();
    private List<Double> ratios = new ArrayList<>();

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

    public void setBarData(List<String> l, List<Double> r) {
        //TODO accept the data, then render it.
        labels = l;
        ratios = r;
        repaint();
    }

    public void setTextContent(String s) {
        currentText = s;
        repaint();
    }

    public void setChartType(String s) {
        //SET THE CHART TYPE
        chartType = s;
        repaint();
    }

    public void buildChart(String xLabel, String yLabel ) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g1) {
        //typecast Graphics to Graphics2D
        Graphics2D g = (Graphics2D)g1;

        //get the width of the screen
        int w = getWidth();



        g.setColor(currentColor);
        //g.fillOval(50, 50, 100, 100);

        //g.setStroke(currentStroke);
        //g.drawLine(200, 300, 300, 500);

        //g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        //g.drawString(currentText, 75, 100);

        int y = 200;
        for (int i=0; i<labels.size(); i++) {
            g.drawString(labels.get(i), 10, y);
            int lineWidth = (int)(w * ratios.get(i));
            g.setStroke(new BasicStroke(10));
            g.drawLine(0, y+10, lineWidth, y+10);

            y += 30;
        }
    }

}

