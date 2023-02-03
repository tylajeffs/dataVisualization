

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Vis extends JPanel {

    private Color currentColor;
    private Stroke currentStroke;
    private String currentText;
    private int fontSize = 20;
    private String chartType = "Bar Chart"; //default to bar chart
    private java.util.List<String> labels = new ArrayList<>();
    private List<Double> ratios = new ArrayList<>();
    private int lastXCoordinate;
    private int lastYCoordinate;
    private double maxQueryResult;

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


    public void setBarData(List<String> l, List<Double> r, double max) {

        //clear the data
        //labels.clear();
        //ratios.clear();

        //put in the new data
        labels = l;
        ratios = r;
        maxQueryResult = max;
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

    @Override
    public void paintComponent(Graphics g1) {
        //typecast Graphics to Graphics2D
        Graphics2D g = (Graphics2D)g1;

        //get the width/height of the screen
        int w = getWidth();
        int h = getHeight();

        //starting point
        int y = 0;
        int x = 0;
        //draw the chart
        if(chartType.equals("Bar Chart")) {

            //figure out the spacing
            int lineWidth = 0;
            if(labels.size() != 0) {
                lineWidth = w/(labels.size());
                System.out.println("this is the space: " + lineWidth);
            }

            //draw the bar chart
            for (int i=0; i<labels.size(); i++) {

                //draw the bar line
                int lineHeight = (int)((h-40) * ratios.get(i));
                g.setStroke(new BasicStroke(lineWidth - 40));
                g.setColor(currentColor);
                g.drawLine(x+100, h, x+100, h-lineHeight);

                //draw the labels
                g.setColor(Color.BLACK);
                g.drawString(labels.get(i), x+40, h-40);

                x += (lineWidth);
            }

            //draw the Y axis
            g.setStroke(new BasicStroke(1));
            g.setColor(Color.BLACK);
            g.drawLine(10,0,10,h);
            double delta = maxQueryResult/4;
            for(int i=0; i<5; i++) {
                double yAxisLabel = 0 + (delta*i);
                System.out.println("this is the label: " + yAxisLabel);
                int yy = (h-40) - i*(h/4);
                g.drawString(""+yAxisLabel,10,yy);

            }

        } else if(chartType.equals("Line Chart"))  {

            //figure out the spacing
            int lineWidth = 0;
            if(labels.size() != 0) {
                lineWidth = w/(labels.size());

            }



            //draw the line chart
            for (int i=0; i<labels.size(); i++) {

                //draw the circle
                int lineHeight = (int)((h-40) * ratios.get(i));
                g.setColor(currentColor);
                g.fillOval(x+100-15,h-lineHeight-15,30,30);

                //check to see if this is the first one
                if(i!=0) {
                    //draw the line between the circles
                    g.setStroke(new BasicStroke(3));
                    g.drawLine(lastXCoordinate,lastYCoordinate,x+100,h-lineHeight);

                }
                //save the new coordinates
                lastXCoordinate = x+100;
                lastYCoordinate = h-lineHeight;




                //draw the labels
                g.setColor(Color.BLACK);
                g.drawString(labels.get(i), x+40, h-40);

                x += (lineWidth);
            }

            //draw the Y axis
            g.setStroke(new BasicStroke(1));
            g.setColor(Color.BLACK);
            g.drawLine(10,0,10,h);
            double delta = maxQueryResult/4;
            for(int i=0; i<5; i++) {
                double yAxisLabel = 0 + (delta*i);
                System.out.println("this is the label: " + yAxisLabel);
                int yy = (h-40) - i*(h/4);
                g.drawString(""+yAxisLabel,10,yy);

            }

        }


    }

}

