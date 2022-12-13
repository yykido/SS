package com.window;

import com.event.ManagementEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Vector;

public class Manage extends JFrame {

    FlowLayout flowLayout;
    ManagementEvent managementEvent;

    // define window width and height

    final int WIDTH = 600;
    final int HEIGHT = 800;

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

    JLabel name; // define name label
    public static JTextField nametext; // define name text field
    JLabel age; // define age label
    public static JTextField agetext; // define age text field
    JLabel grade; // define grade label
    public static JTextField gradetext; // define grade text field
    JLabel student_id; // define student id label
    public static JTextField student_id_text; // define student id text field
    ButtonGroup group; // define a button group
    public static JRadioButton radioM, radioF,radioB;

    JButton add, delete, modify, search, reset;

    JTextArea resultText;

    // define chart
    static Vector rwo;
    JScrollPane jScrollPane;
    Object columns[] = {"First Name","Last Name", "Gender", "Age","Grade", "Student ID"}; // title information
//    static Object a[][];
    static int row;
    public static DefaultTableModel model;
    static TableColumnModel columnModel;

    public static JTable table=null;


    public Manage() {
        init();
        setResizable(false); // set not resizable
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true); // set is visible
        validate();
    }

    void init() {
        flowLayout = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(null);
        this.setTitle("Student Management System");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x = (width-WIDTH)/2;
        int y = (height- HEIGHT)/2;
        this.setBounds(x,y,WIDTH,HEIGHT);
        ImageIcon img = new ImageIcon("src/img/loginimg.jpg");
        topimg = new JLabel(img);
        topimg.setBounds(0,0, img.getIconWidth(), img.getIconHeight());
        //
        menuBar = new JMenuBar(); // create a menu bar
        menu = new JMenu("Management");
        menu1 = new JMenu("Account");

        item1_1 = new JMenuItem("See all Account");
        item1_1.setName("See all Account");
        item1_2 = new JMenuItem("Modify Student Information");
        item1_2.setName("Modify Student Information");
        item1_3 = new JMenuItem("Modify current Account Information");
        item1_3.setName("Modify current Account Information");
        item2_1 = new JMenuItem("Log in");

        item2_2 = new JMenuItem("Log out");
        item2_2.setName("Log out");

        menu.add(item1_1);
        menu.add(item1_2);
        menu.add(item1_3);
//        menu1.add(item2_1);
        menu1.add(item2_2);
        menuBar.add(menu);
        menuBar.add(menu1);

        // initialize label1
        panel_1 = new JPanel();
        panel_1.setLayout(flowLayout);
        panel_1.setBounds(0,0,WIDTH,150);
        panel_1.setBackground(Color.gray);
        // add 5 buttons
        add = new JButton("Add");
        add.setName("add");
        delete = new JButton("Delete");
        delete.setName("delete");
        modify = new JButton("Modify");
        modify.setName("modify");
        search = new JButton("Search");
        reset = new JButton("Reset");
        reset.setName("reset");
        // add name
        name = new JLabel("Full Name");
        nametext = new JTextField(8);
//        nametext.setPreferredSize(new Dimension(50,20));
        // add age
        age = new JLabel("Age");
        agetext = new JTextField(2);
        // add grade
        grade = new JLabel("Grade");
        gradetext = new JTextField(2);
        // add student id
        student_id = new JLabel("Student ID");
        student_id_text = new JTextField(8);
        // add gender
        group = new ButtonGroup();
        radioM = new JRadioButton("Male");
        radioF = new JRadioButton("Female");
        radioB = new JRadioButton("Bigender");
        // group gender button
        group.add(radioB);
        group.add(radioM);
        group.add(radioF);

        setEvent();


        // add to panel
        panel_1.add(name);
        panel_1.add(nametext);
        panel_1.add(radioM);
        panel_1.add(radioF);
        panel_1.add(radioB);
        panel_1.add(age);
        panel_1.add(agetext);
        panel_1.add(grade);
        panel_1.add(gradetext);
        panel_1.add(student_id);
        panel_1.add(student_id_text);

        panel_1.add(add);
        panel_1.add(delete);
        panel_1.add(modify);
        panel_1.add(search);
        panel_1.add(reset);

        // initialize panel2
        panel_2 = new JPanel();
        panel_2.setLayout(flowLayout);
        panel_2.setBounds(0,200,WIDTH,300);
        panel_2.setBackground(new Color(175,12,12));

        initTable();
        // add result
        resultText = new JTextArea();
        resultText.setBounds(10,500,WIDTH-20,100);
        resultText.setText("abcd");
        resultText.setEditable(false);
        this.add(resultText);

        panel_2.add(jScrollPane);








//        this.setJMenuBar(menuBar);

//        this.add(topimg);
        this.add(panel_1);
        this.add(panel_2);
        this.setJMenuBar(menuBar);



    }
    void setEvent() {
        managementEvent = new ManagementEvent();
        item1_1.addActionListener(managementEvent);
        item2_2.addActionListener(managementEvent);
        add.addActionListener(managementEvent);
        reset.addActionListener(managementEvent);
        delete.addActionListener(managementEvent);
        modify.addActionListener(managementEvent);
    }

    void initTable() {
        table = getTable();
        jScrollPane = new JScrollPane(table); // add a scrolling pane
        jScrollPane.setPreferredSize(new Dimension(WIDTH-20,250)); // set size of pane
        table.setPreferredSize(new Dimension(WIDTH-20,500));
        jScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    }
    JTable getTable() {
        if(table == null) {
            table = new JTable();
            int[] columnWidth = {150,150,150,150,150,150}; // set colum width
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
                column.setPreferredWidth(columnWidth[i]);
            }
            rwo = new Vector(6);
        }
        return table;
    }
}
