package com.event;

import com.window.Register;
import com.window.StudentSystem;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegEvent implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("sign up clicked");
            StudentSystem.register = new Register();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
