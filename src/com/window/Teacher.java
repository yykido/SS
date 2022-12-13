package com.window;

import java.awt.*;


import com.event.StudentEvent;
import com.event.TeacherEvent;
import com.style.Fonts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.Vector;

public class Teacher extends JFrame {
    FlowLayout flowLayout;
    TeacherEvent teacherEvent;

    Fonts fontStyle;

    // define window width and height

    final int WIDTH = 840;
    final int HEIGHT = 520;
    public static String login_account = "number";
    public static String login_name = "first last";
    public static JLabel showLoginAccount;
    public static JLabel showName;

    // define labels
    JLabel topimg; // top image label

    //define menu
    JMenuBar menuBar; // define a menu bar
    JMenu menu; //define a menu
    JMenu menu1; // define menu1
    JMenuItem item1_1, item1_2, item1_3, item4;
    JMenuItem item2_1, item2_2, item2_3;
    // see all account
    // modify student information
    // modify current account information

    //define a panel
    JPanel panel_1;
    JPanel panel_2; // define panel2 to store the table
    JPanel panel_3; // define panel3 as grade box;

    JLabel course_name; // define course_name label
    public static JTextField course_nametext; // define course_name text field

    JLabel grade; // define grade label
    public static JTextField gradetext; // define grade text field
    JLabel course_id; // define course id label
    public static JTextField course_id_text; // define course id text field

    public static JTextField course_id_for_search;
    public static JTextField student_name_grading;
    public static JTextField student_score;



    JButton add, delete, reset, set_grade;
    public static JButton search;

    public static JTextArea resultText; // show the registered courses

    // define chart
    static Vector rwo;
    JScrollPane jScrollPane;
    Object columns[] = {"index","course_id","course_name"}; // title information
    //    static Object a[][];
    static int row;
    public static DefaultTableModel model;
    TableColumnModel columnModel;

    public static JTable table=null;


    public Teacher() {
        init();
        setResizable(false); // set not resizable
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true); // set is visible
        validate();
    }

    void init() {
        flowLayout = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(null);
        this.setTitle("Teacher Platform");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x = (width-WIDTH)/2;
        int y = (height- HEIGHT)/2;
        this.setBounds(x,y,WIDTH,HEIGHT);
        ImageIcon img = new ImageIcon("src/img/studenttop.jpg");
        topimg = new JLabel(img);
        topimg.setBounds(0,0, img.getIconWidth(), img.getIconHeight());
        showLoginAccount = new JLabel(login_account);
        showLoginAccount.setBounds(WIDTH-250,0,100,30);
        showLoginAccount.setForeground(Color.white);
        showLoginAccount.setFont(new Font("Arial",Font.PLAIN,16));
        showName = new JLabel(login_name);
        showName.setBounds(WIDTH-140,0,200,30);
        showName.setForeground(Color.white);
        showName.setFont(new Font("Arial",Font.PLAIN,16));



        //
        menuBar = new JMenuBar(); // create a menu bar
        menu = new JMenu("Setting");
        menu1 = new JMenu("Account");

        item1_1 = new JMenuItem("Refresh");
        item1_1.setName("Refresh");
        item1_2 = new JMenuItem("Modify Student Information");
        item1_2.setName("Modify Student Information");
        item1_3 = new JMenuItem("Modify current Account Information");
        item1_3.setName("Modify current Account Information");
        item2_1 = new JMenuItem("Log in");

        item2_2 = new JMenuItem("Log out");
        item2_2.setName("Log out");

        menu.add(item1_1);
//        menu.add(item1_2);
//        menu.add(item1_3);
//        menu1.add(item2_1);
        menu1.add(item2_2);
        menuBar.add(menu);
        menuBar.add(menu1);

        // initialize label1
        panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBounds(0,40,440,130);
        panel_1.setBackground(Color.white);
        // add 5 buttons
        JLabel label1 = new JLabel("My Course");
        label1.setFont(new Font("Arial",Font.BOLD,18));
        label1.setBounds(10,0,100,30);
        panel_1.add(label1);
        add = new JButton("Add");
        add.setName("add");
        add.setBounds(10,90,100,25);

        // costume the add button
        add.setBorderPainted(false);
        add.setFont(new Font("Calibri",Font.PLAIN,14));
        add.setOpaque(true);
        add.setBackground(new Color(42, 98, 189));
        add.setForeground(Color.white);

        delete = new JButton("Delete");
        delete.setBounds(150,90,100,25);
        delete.setName("delete");
        // costume delete button
        delete.setBorderPainted(false);
        delete.setFont(new Font("Calibri",Font.PLAIN,14));
        delete.setOpaque(true);
//        delete.setBackground(new Color(170,10,10));
        delete.setBackground(new Color(42, 98, 189));
        delete.setForeground(Color.white);

        search = new JButton("Search");
        search.setBounds(10,100,100,25);
        search.setName("search");
        // costume search button
        search.setBorderPainted(false);
        search.setFont(new Font("Calibri",Font.PLAIN,14));
        search.setOpaque(true);
        search.setBackground(new Color(42, 98, 189));
        search.setForeground(Color.white);

        reset = new JButton("Reset");
        reset.setName("reset");
        // add course_name
        course_name = new JLabel("Course Name:");
        course_name.setBounds(220,50,100,20);
        course_nametext = new JTextField(10);
        course_nametext.setBounds(310,50,120,20);
//        course_nametext.setPreferredSize(new Dimension(50,20));

//        grade = new JLabel("Grade");
//        gradetext = new JTextField(2);
        // add student id
        course_id = new JLabel("Course ID:");
        course_id.setBounds(10,50,100,20);
        course_id_text = new JTextField(10);
        course_id_text.setBounds(80,50,120,20);



        // group gender button

        setEvent();
        setPosition();


        // add to panel
        panel_1.add(course_name);
        panel_1.add(course_nametext);

        panel_1.add(course_id);
        panel_1.add(course_id_text);

        panel_1.add(add);
        panel_1.add(delete);
        panel_1.add(reset);

        // initialize panel2
        panel_2 = new JPanel();
        panel_2.setLayout(flowLayout);
        panel_2.setBounds(0,180,440,280);
        panel_2.setBackground(new Color(200,200,200));

        // initialize panel3
        panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBounds(450,40,WIDTH/2-10,480);
        panel_3.setBackground(Color.white);
        JLabel label3 = new JLabel("Students Grades");
        label3.setFont(new Font("Arial",Font.BOLD,18));
        label3.setBounds(10,0,150,30);
        JLabel instruction  = new JLabel("---------------------------------------------");
        instruction.setBounds(10,120,400,30);
        instruction.setFont(new Font("Calibri",Font.PLAIN,14));
        // set_grade button
        set_grade = new JButton("Set Grade");
        set_grade.setBounds(150,100,180,25);
        set_grade.setName("set grade");
        // decorate set_grade
        set_grade.setBorderPainted(false);
        set_grade.setFont(new Font("Calibri",Font.PLAIN,14));
        set_grade.setOpaque(true);
        set_grade.setBackground(new Color(0,0,0));
        set_grade.setForeground(Color.white);

        teacherEvent = new TeacherEvent();
        set_grade.addActionListener(teacherEvent);
        JLabel grade1 = new JLabel("Student ID:");
        grade1.setBounds(10,70,100,20);
        JLabel grade2 = new JLabel("Grade:");
        grade2.setBounds(200,40,100,20);
        JLabel courseid =  new JLabel("Course ID:");
        courseid.setBounds(10,40,100,20);
        student_name_grading = new JTextField(10);
        student_name_grading.setBounds(80,70,100,20);
        student_score = new JTextField(3);
        student_score.setBounds(240,40,50,20);
        course_id_for_search = new JTextField();
        course_id_for_search.setBounds(80,40,100,20);
        panel_3.add(course_id_for_search);
        panel_3.add(courseid);
        panel_3.add(instruction);
        panel_3.add(label3);
        panel_3.add(search);
        panel_3.add(set_grade);
        panel_3.add(student_name_grading);
        panel_3.add(student_score);
        panel_3.add(grade1);
        panel_3.add(grade2);




        initTable();
        // add result
        resultText = new JTextArea();
        resultText.setBounds(10,150,WIDTH/2-10,280);
        String content = "course_id"+"\t"+"course_name"+"\t "+"student_id"+"\t       "+"grade"+"\n";
        resultText.setText(content);
        resultText.setEditable(false);
        panel_3.add(resultText);
        this.add(showLoginAccount);
        this.add(showName);
        this.add(topimg);
//        this.add(resultText);

        panel_2.add(jScrollPane);


        JPanel panel0 = new JPanel();
        panel0.setBounds(0,0,WIDTH,40);
        panel0.setBackground(new Color(70, 157, 240));
        this.add(panel_1);

        this.add(panel_2);
        this.setJMenuBar(menuBar);
        this.add(panel_3);
        this.add(panel0);
        showLoginAccount.setText(login_account);



    }
    void setPosition() {
//        panel_1.setBounds(0,0,200,400);
//        panel_2.setBounds(250,0,200,400);


    }
    void setEvent() {
        teacherEvent = new TeacherEvent();
        item1_1.addActionListener(teacherEvent);
        item2_2.addActionListener(teacherEvent);
        add.addActionListener(teacherEvent);
        reset.addActionListener(teacherEvent);
        delete.addActionListener(teacherEvent);
        search.addActionListener(teacherEvent);

    }

    void initTable() {
        table = getTable();
        jScrollPane = new JScrollPane(table); // add a scrolling pane
//        jScrollPane.setBounds(200,0,100,50);
        jScrollPane.setPreferredSize(new Dimension(400,250)); // set size of pane
        table.setPreferredSize(new Dimension(400,500));
//        table.setBounds(200,0,100,100);
        jScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    }
    JTable getTable() {
        if(table == null) {
            table = new JTable();
            int[] columnWidth = {10,10,10,10}; // set colum width
            model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }; // set colum and row and set the table uneditable
            model.setColumnIdentifiers(columns);
            table.setModel(model);
            columnModel = table.getColumnModel(); // get the control of the table
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setResizingAllowed(false);
            int count = columnModel.getColumnCount();//return column size
            for(int i=0;i<count;i++) {
                TableColumn column = columnModel.getColumn(i);
                column.setPreferredWidth(10);
            }
//            rwo = new Vector(4);
        }
        return table;
    }

}
