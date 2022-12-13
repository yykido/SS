package com.style;

import java.awt.*;

public class Fonts {
    public Font title_font;
    public Font content;
    public Font button;
    public Fonts() {
        title_font = new Font("Calibri",Font.BOLD,28);
        content = new Font("Calibri",Font.PLAIN,16);
        button = new Font("Calibri",Font.BOLD,16);
    }
}
