

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main extends JFrame implements ActionListener {

    private Vis mainPanel;
    public static final String BAR_CHART = "Bar Chart";
    public static final String LINE_CHART = "Line Chart";
    public static final String HOW_MANY_STUDENTS_MAJORED_IN_CS = "How many students majored in CS?";
    public static final String HOW_MANY_STUDENTS_HAVE_A_GPA_UNDER_3_0 = "How many students have a GPA under 3.0?";
    public static final String HOW_MANY_STUDENTS_GRADUATED_IN_2012 = "How many students graduated in 2012?";
    public static final String HOW_MANY_STUDENTS_ARE_FROM_HAWAII_OR_PACIFIC = "How many students are from Hawaii or the Pacific?";
    public static final String HOW_MANY_FEMALE_STUDENTS_MAJORED_IN_CS = "How many female students majored in CS?";

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
        JMenuItem a = new JMenuItem(HOW_MANY_STUDENTS_MAJORED_IN_CS);
        a.addActionListener(this);
        a.setActionCommand(HOW_MANY_STUDENTS_MAJORED_IN_CS);

        JMenuItem b = new JMenuItem(HOW_MANY_STUDENTS_HAVE_A_GPA_UNDER_3_0);
        b.addActionListener(this);
        b.setActionCommand(HOW_MANY_STUDENTS_HAVE_A_GPA_UNDER_3_0);

        JMenuItem c = new JMenuItem(HOW_MANY_STUDENTS_GRADUATED_IN_2012);
        c.addActionListener(this);
        c.setActionCommand(HOW_MANY_STUDENTS_GRADUATED_IN_2012);

        JMenuItem d = new JMenuItem(HOW_MANY_STUDENTS_ARE_FROM_HAWAII_OR_PACIFIC);
        d.addActionListener(this);
        d.setActionCommand(HOW_MANY_STUDENTS_ARE_FROM_HAWAII_OR_PACIFIC);

        JMenuItem e = new JMenuItem(HOW_MANY_FEMALE_STUDENTS_MAJORED_IN_CS);
        e.addActionListener(this);
        e.setActionCommand(HOW_MANY_FEMALE_STUDENTS_MAJORED_IN_CS);


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

        //TODO add a menu bar and (time permitting) a toolbar
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
        System.out.println("a menu was clicked");
        String cmd = e.getActionCommand();
        try {
            Connection c = DriverManager.getConnection("jdbc:derby:pollster");

            switch (cmd) {
                case HOW_MANY_STUDENTS_MAJORED_IN_CS:
                    mainPanel.setCircleColor(Color.RED);
                    try {
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("select count(*) from cis2019 where major='Computer Science'");
                        rs.next();
                        int totalCount = rs.getInt(1);
                        System.out.println("There are " + totalCount + " rows in the table.");

                        //send the output to the view
                        mainPanel.setTextContent("There are " + totalCount + " students who majored in Computer Science");
                    } catch (SQLException ex) {
                        System.out.println("oops, couldn't run query " + ex.toString());
                    }
                    break;
                case HOW_MANY_STUDENTS_HAVE_A_GPA_UNDER_3_0:
                    mainPanel.setCircleColor(Color.BLUE);
                    try {
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("select count(*) from cis2019 where gpa<3");
                        rs.next();
                        int totalCount = rs.getInt(1);
                        System.out.println("There are " + totalCount + " rows in the table.");

                        //send the output to the view
                        mainPanel.setTextContent("There are " + totalCount + " students who have a GPA under 3.0");
                    } catch (SQLException ex) {
                        System.out.println("oops, couldn't run query " + ex.toString());
                    }
                    break;
                case HOW_MANY_STUDENTS_GRADUATED_IN_2012:
                    mainPanel.setCircleColor(Color.CYAN);
                    try {
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("select count(*) from cis2019 where gradyear=2012");
                        rs.next();
                        int totalCount = rs.getInt(1);
                        System.out.println("There are " + totalCount + " rows in the table.");

                        //send the output to the view
                        mainPanel.setTextContent("There are " + totalCount + " students who graduated in 2012");
                    } catch (SQLException ex) {
                        System.out.println("oops, couldn't run query " + ex.toString());
                    }
                    break;
                case HOW_MANY_STUDENTS_ARE_FROM_HAWAII_OR_PACIFIC:
                    mainPanel.setCircleColor(Color.PINK);
                    try {
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("select count(*) from cis2019 where home='Hawaii' or home='Pacific'");
                        rs.next();
                        int totalCount = rs.getInt(1);
                        System.out.println("There are " + totalCount + " rows in the table.");

                        //send the output to the view
                        mainPanel.setTextContent("There are " + totalCount + " students from Hawaii or the Pacific");
                    } catch (SQLException ex) {
                        System.out.println("oops, couldn't run query " + ex.toString());
                    }
                    break;
                case HOW_MANY_FEMALE_STUDENTS_MAJORED_IN_CS:
                    mainPanel.setCircleColor(Color.GREEN);
                    try {
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("select count(*) from cis2019 where gender='F' and major='Computer Science'");
                        rs.next();
                        int totalCount = rs.getInt(1);
                        System.out.println("There are " + totalCount + " rows in the table.");

                        //send the output to the view
                        mainPanel.setTextContent("There are " + totalCount + " women who majored in Computer Science");
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
