// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Graphics;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JComponent;

public class CommutView extends JComponent
{
    private int x;
    private int y;
    private int wight;
    private int height;
    
    public CommutView(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.wight = width;
        this.height = height;
        final JLabel title = new JLabel("Commutator", 0);
        title.setFont(DisplayStyles.FONT_COURIER_BOLD_21);
        title.setBounds(x, y, width, height);
        this.add(title);
        this.setBounds(x, y, width, height);
    }
    
    public void paintComponent(final Graphics g) {
        g.setColor(DisplayStyles.COLOR_TITLE);
        g.fillRect(this.x, this.y, this.wight, this.height);
        g.setColor(DisplayStyles.COLOR_TEXT);
        g.drawRect(this.x, this.y, this.wight - 1, this.height - 1);
    }
}
