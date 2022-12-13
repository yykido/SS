package com.window;

import javax.swing.*;
import java.awt.*;

public class ModifyAccountInformation extends JFrame {

    FlowLayout flowLayout;


    public ModifyAccountInformation() {
        init();
        setResizable(false); // set not resizable
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true); // set is visible
    }

    void init() {
        flowLayout = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(flowLayout);

    }
}
