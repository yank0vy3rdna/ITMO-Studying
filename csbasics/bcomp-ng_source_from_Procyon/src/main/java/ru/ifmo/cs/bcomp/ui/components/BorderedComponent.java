// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Graphics;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComponent;

public class BorderedComponent extends JComponent
{
    protected int width;
    protected final int height;
    
    protected BorderedComponent(final int height) {
        this.height = height;
    }
    
    protected final JLabel addLabel(final String value, final Font font, final Color color) {
        final JLabel label = new JLabel(value, 0);
        label.setFont(font);
        label.setBackground(color);
        label.setOpaque(true);
        this.add(label);
        return label;
    }
    
    protected void setBounds(final int x, final int y, final int width) {
        this.setBounds(x, y, this.width = width, this.height);
    }
    
    public void paintComponent(final Graphics g) {
        g.setColor(DisplayStyles.COLOR_TEXT);
        g.drawRect(0, 0, this.width - 1, this.height - 1);
    }
}
