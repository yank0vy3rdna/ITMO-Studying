// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Graphics;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JComponent;

public class FlagView extends JComponent
{
    JLabel title;
    private int x;
    private int y;
    private int wight;
    private int height;
    boolean active;
    
    public FlagView(final int x, final int y, final int wigth, final int height) {
        this.x = x;
        this.y = y;
        this.wight = wigth;
        this.height = height;
        (this.title = new JLabel("", 0)).setFont(DisplayStyles.FONT_COURIER_BOLD_21);
        this.title.setBounds(x, y, wigth, height);
        this.add(this.title);
        this.setBounds(x, y, wigth, height);
    }
    
    public void setTitle(final String title) {
        (this.title = new JLabel(title, 0)).setFont(DisplayStyles.FONT_COURIER_BOLD_21);
        this.title.setBounds(this.x, this.y, this.wight, this.height);
        this.add(this.title);
    }
    
    public void setActive(final boolean active) {
        this.active = active;
        this.setBackground(active ? DisplayStyles.COLOR_TITLE : DisplayStyles.COLOR_VALUE);
    }
    
    public void paintComponent(final Graphics g) {
        g.setColor(this.active ? DisplayStyles.COLOR_TITLE : DisplayStyles.COLOR_VALUE);
        g.fillRect(this.x, this.y, this.wight, this.height);
        g.setColor(DisplayStyles.COLOR_TEXT);
        g.drawRect(this.x, this.y, this.wight - 1, this.height - 1);
    }
}
