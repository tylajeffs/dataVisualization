

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame implements ActionListener {

    private Vis mainPanel;
    public static final String BAR_CHART = "Bar Chart";
    public static final String LINE_CHART = "Line Chart";
    public static final String STUDENTS_IN_EACH_MAJOR = "How many students are in each major?";
    public static final String STUDENTS_FROM_EACH_AREA = "Where are all the students from?";
    public static final String GPA_OF_EACH_MAJOR = "What is the average GPA of each major?";
    public static final String CREDITS_ATTEMPTED_PER_YEAR = "How many credits did students attempt per year?";
    public static final String NUMBER_OF_WOMEN_PER_MAJOR = "How many women are in each major?";

    private java.util.List<String> labels = new ArrayList<>();
    private java.util.List<Double> values = new ArrayList<>();
    private List<Double> ratios = new ArrayList<>();


    private JMenuBar createMenu() {
        //create the menu bar
        JMenuBar mb = new JMenuBar();

        //create the menus
        JMenu queries = new JMenu("Database Queries");
        JMenu chartTypeToggle = new JMenu("Chart Type");

        //create menu items for the chart type
        JMenuItem bar = new JMenuItem(BAR_CHART);
        bar.addActionListener(this);
        bar.setActionCommand(BAR_CHART);

        JMenuItem line = new JMenuItem(LINE_CHART);
        line.addActionListener(this);
        line.setActionCommand(LINE_CHART);

        //create menu items for the queries
        JMenuItem a = new JMenuItem(STUDENTS_IN_EACH_MAJOR);
        a.addActionListener(this);
        a.setActionCommand(STUDENTS_IN_EACH_MAJOR);

        JMenuItem b = new JMenuItem(STUDENTS_FROM_EACH_AREA);
        b.addActionListener(this);
        b.setActionCommand(STUDENTS_FROM_EACH_AREA);

        JMenuItem c = new JMenuItem(GPA_OF_EACH_MAJOR);
        c.addActionListener(this);
        c.setActionCommand(GPA_OF_EACH_MAJOR);

        JMenuItem d = new JMenuItem(CREDITS_ATTEMPTED_PER_YEAR);
        d.addActionListener(this);
        d.setActionCommand(CREDITS_ATTEMPTED_PER_YEAR);

        JMenuItem e = new JMenuItem(NUMBER_OF_WOMEN_PER_MAJOR);
        e.addActionListener(this);
        e.setActionCommand(NUMBER_OF_WOMEN_PER_MAJOR);


        //add all the menu items to the actual menu
        queries.add(a);
        queries.add(b);
        queries.add(c);
        queries.add(d);
        queries.add(e);
        chartTypeToggle.add(bar);
        chartTypeToggle.add(line);
        mb.add(queries);
        mb.add(chartTypeToggle);
        return mb;
    }

    public Main() {
        JMenuBar menu = createMenu();
        setJMenuBar(menu);

        mainPanel = new Vis();

        setContentPane(mainPanel);
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CS 490R");
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //gets called when someone clicks a menu
        System.out.println("a menu was clicked");
        String cmd = e.getActionCommand();
        try {
            Connection c = DriverManager.getConnection("jdbc:derby:pollster");

            switch (cmd) {
                case BAR_CHART:
                    mainPanel.setChartType(BAR_CHART);
                    break;
                case LINE_CHART:
                    mainPanel.setChartType(LINE_CHART);
                    break;
                case STUDENTS_IN_EACH_MAJOR:
                    mainPanel.setCircleColor(new Color(92, 29, 34));
                    try {
                        //clear all the arraylists
                        ratios.clear();
                        labels.clear();
                        values.clear();

                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("select major, count(*) from cis2019 group by major");
                        while (rs.next() == true) {
                            String major = rs.getString(1);
                            double num = rs.getInt(2);
                            labels.add(major);
                            values.add(num);
                            System.out.println(major + " = " + num);
                        }

                        c.close(); //close the connection

                        //find the biggest value
                        double max = values.stream().max(Double::compare).get();

                        //calculate the ratios
                        for (var v : values) {
                            ratios.add(v / max);
                        }

                        //send the output to the view
                        mainPanel.setBarData(labels, ratios, max);

                    } catch (SQLException ex) {
                        System.out.println("oops, couldn't run query " + ex.toString());
                    }
                    break;
                case STUDENTS_FROM_EACH_AREA:
                    mainPanel.setCircleColor(new Color(135, 53, 121));
                    try {
                        //clear all the arraylists
                        ratios.clear();
                        labels.clear();
                        values.clear();

                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("select home, count(*) from cis2019 group by home");
                        while (rs.next() == true) {
                            String major = rs.getString(1);
                            double num = rs.getInt(2);
                            labels.add(major);
                            values.add(num);
                            System.out.println(major + " = " + num);
                        }

                        c.close(); //close the connection

                        //find the biggest value
                        double max = values.stream().max(Double::compare).get();

                        //calculate the ratios
                        for (var v : values) {
                            ratios.add(v / max);
                        }

                        //send the output to the view
                        mainPanel.setBarData(labels, ratios, max);

                    } catch (SQLException ex) {
                        System.out.println("oops, couldn't run query " + ex.toString());
                    }
                    break;
                case GPA_OF_EACH_MAJOR:
                    mainPanel.setCircleColor(new Color(64, 35, 87));
                    try {
                        //clear all the arraylists
                        ratios.clear();
                        labels.clear();
                        values.clear();

                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("select cast(avg(gpa) as decimal(5,2)), major from cis2019 group by major");
                        while (rs.next() == true) {
                            String major = rs.getString(2);
                            double num = rs.getInt(1);
                            labels.add(major);
                            values.add(num);
                            System.out.println(major + " = " + num);
                        }

                        c.close(); //close the connection

                        //find the biggest value
                        double max = values.stream().max(Double::compare).get();

                        //calculate the ratios
                        for (var v : values) {
                            ratios.add(v / max);
                        }

                        //send the output to the view
                        mainPanel.setBarData(labels, ratios, max);

                    } catch (SQLException ex) {
                        System.out.println("oops, couldn't run query " + ex.toString());
                    }
                    break;
                case CREDITS_ATTEMPTED_PER_YEAR:
                    mainPanel.setCircleColor(new Color(33, 80, 94));
                    try {
                        //clear all the arraylists
                        ratios.clear();
                        labels.clear();
                        values.clear();

                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("select cast(avg(credits_attempted) as decimal(5,2)), gradyear from cis2019 group by gradyear order by gradyear");
                        while (rs.next() == true) {
                            String major = rs.getString(1);
                            double num = rs.getInt(2);
                            labels.add(major);
                            values.add(num);
                            System.out.println(major + " = " + num);
                        }

                        c.close(); //close the connection

                        //find the biggest value
                        double max = values.stream().max(Double::compare).get();

                        //calculate the ratios
                        for (var v : values) {
                            ratios.add(v / max);
                        }

                        //send the output to the view
                        mainPanel.setBarData(labels, ratios, max);

                    } catch (SQLException ex) {
                        System.out.println("oops, couldn't run query " + ex.toString());
                    }
                    break;
                case NUMBER_OF_WOMEN_PER_MAJOR:
                    mainPanel.setCircleColor(new Color(26, 79, 25));
                    try {
                        //clear all the arraylists
                        ratios.clear();
                        labels.clear();
                        values.clear();

                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("select major, count(*) from cis2019 where gender = 'F' group by major ");
                        while (rs.next() == true) {
                            String major = rs.getString(1);
                            double num = rs.getInt(2);
                            labels.add(major);
                            values.add(num);
                            System.out.println(major + " = " + num);
                        }

                        c.close(); //close the connection

                        //find the biggest value
                        double max = values.stream().max(Double::compare).get();

                        //calculate the ratios
                        for (var v : values) {
                            ratios.add(v / max);
                        }

                        //send the output to the view
                        mainPanel.setBarData(labels, ratios, max);

                    } catch (SQLException ex) {
                        System.out.println("oops, couldn't run query " + ex.toString());
                    }
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("oops, could not connect to DB " + ex.toString());
        }
    }
}
