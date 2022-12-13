package com.window;

import com.event.StudentEvent;
import com.style.Fonts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Vector;

public class Student extends JFrame {

    FlowLayout flowLayout;
    StudentEvent studentEvent;

    Fonts fontStyle;

    // define window width and height

    final int WIDTH = 840;
    final int HEIGHT = 520;
    public static String login_account = "number";
    public static String login_name = "first last";
    public static JLabel showLoginAccount;
    public static JLabel showName;
    public static JTextField insert_delete_course;

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
    JLabel instructor; // define instructor label
    public static JTextField instructortext; // define instructor text field
    JLabel grade; // define grade label
    public static JTextField gradetext; // define grade text field
    JLabel course_id; // define course id label
    public static JTextField course_id_text; // define course id text field


    JButton add, delete, search, reset;

    public static JTextArea resultText; // show the registered courses

    // define chart
    static Vector rwo;
    JScrollPane jScrollPane;
    Object columns[] = {"course_id","course_name", "instructor"}; // title information
    //    static Object a[][];
    static int row;
    public static DefaultTableModel model;
    TableColumnModel columnModel;

    public static JTable table=null;


    public Student() {
        init();
        setResizable(false); // set not resizable
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true); // set is visible
        validate();
    }

    void init() {
        flowLayout = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(null);
        this.setTitle("Student Platform");

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
        panel_1.setBounds(0,40,440,150);
        panel_1.setBackground(Color.white);
        // add 5 buttons
        JLabel label1 = new JLabel("Registration");
        label1.setFont(new Font("Arial",Font.BOLD,18));
        label1.setBounds(10,0,150,30);
        panel_1.add(label1);
        add = new JButton("Add");
        add.setName("add");
        add.setBounds(140,120,100,25);
        // costume the add button
        add.setBorderPainted(false);
        add.setFont(new Font("Calibri",Font.PLAIN,14));
        add.setOpaque(true);
        add.setBackground(new Color(42, 98, 189));
        add.setForeground(Color.white);

        delete = new JButton("Delete");
        delete.setBounds(220,90,100,25);
        delete.setName("delete");

        // costume delete button
        delete.setBorderPainted(false);
        delete.setFont(new Font("Calibri",Font.PLAIN,14));
        delete.setOpaque(true);
//        delete.setBackground(new Color(170,10,10));
        delete.setBackground(new Color(42, 98, 189));
        delete.setForeground(Color.white);

        search = new JButton("Search");
        search.setBounds(10,120,100,25);
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
        // add age
        instructor = new JLabel("Instructor:");
        instructor.setBounds(10,80,100,20);
        instructortext = new JTextField(8);
        instructortext.setBounds(80,80,120,20);
        // add grade
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

        panel_1.add(instructor);
        panel_1.add(instructortext);
        panel_1.add(course_id);
        panel_1.add(course_id_text);

        panel_1.add(add);

        panel_1.add(search);
        panel_1.add(reset);

        // initialize panel2
        panel_2 = new JPanel();
        panel_2.setLayout(flowLayout);
        panel_2.setBounds(0,200,440,280);
        panel_2.setBackground(new Color(200,200,200));

        // initialize panel3
        panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBounds(450,40,WIDTH/2-10,480);
        panel_3.setBackground(Color.white);
        JLabel label3 = new JLabel("Grades");
        label3.setFont(new Font("Arial",Font.BOLD,18));
        label3.setBounds(10,0,150,30);
        JLabel instruction  = new JLabel("---------------------------------------------");
        instruction.setBounds(10,120,400,30);
        instruction.setFont(new Font("Calibri",Font.PLAIN,14));
        JLabel delete_course_id = new JLabel("Course ID:");
        delete_course_id.setBounds(10,90,100,20);
        insert_delete_course = new JTextField();
        insert_delete_course.setBounds(80,90,120,20);
        JButton showgrade = new JButton("Show");
        showgrade.setBounds(10,40,80,25);
        showgrade.setName("show");
        showgrade.setOpaque(true);
        showgrade.setBorderPainted(false);
        showgrade.setBackground(new Color(0,0,0));
        showgrade.setForeground(Color.white);
        showgrade.addActionListener(studentEvent);
        JButton hidegrade = new JButton("Hide");
        hidegrade.setBounds(100,40,80,25);
        hidegrade.setName("hide");
        hidegrade.setOpaque(true);
        hidegrade.setBorderPainted(false);
        hidegrade.setBackground(new Color(0,0,0));
        hidegrade.setForeground(Color.white);
        hidegrade.addActionListener(studentEvent);
        panel_3.add(showgrade);
        panel_3.add(hidegrade);
        panel_3.add(delete_course_id);
        panel_3.add(insert_delete_course);
        panel_3.add(instruction);
        panel_3.add(label3);
        panel_3.add(delete);




        initTable();
        // add result
        resultText = new JTextArea();
        resultText.setBounds(10,150,WIDTH/2-10,280);
        String content = "course_id"+"    "+"course_name"+"      "+"instructor_name"+"    "+"grade"+"\n";
        resultText.setText(content);
        resultText.setEditable(false);
        panel_3.add(resultText);
        this.add(showLoginAccount);
        this.add(showName);
        this.add(topimg);


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
        studentEvent = new StudentEvent();
        item1_1.addActionListener(studentEvent);
        item2_2.addActionListener(studentEvent);
        add.addActionListener(studentEvent);
        reset.addActionListener(studentEvent);
        delete.addActionListener(studentEvent);
        search.addActionListener(studentEvent);
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
            int[] columnWidth = {10,10,10}; // set colum width
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