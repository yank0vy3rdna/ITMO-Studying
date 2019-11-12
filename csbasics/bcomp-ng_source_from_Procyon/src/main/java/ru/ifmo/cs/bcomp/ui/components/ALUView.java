// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Graphics;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JComponent;

public class ALUView extends JComponent
{
    private int[] xpoints;
    private int[] ypoints;
    
    public ALUView(final int x, final int y, final int width, final int height) {
        final int half = width / 2;
        final int offset = height / 3;
        final int soffset = offset / 3;
        this.xpoints = new int[] { 0, half - soffset, half, half + soffset, width - 1, width - 1 - offset, offset };
        this.ypoints = new int[] { 0, 0, offset, 0, 0, height - 1, height - 1 };
        final JLabel title = new JLabel("ALU", 0);
        title.setFont(DisplayStyles.FONT_COURIER_BOLD_45);
        title.setBounds(offset, offset, width - 2 * offset, height - offset);
        this.add(title);
        this.setBounds(x, y, width, height);
    }
    
    public void paintComponent(final Graphics g) {
        g.setColor(DisplayStyles.COLOR_TITLE);
        g.fillPolygon(this.xpoints, this.ypoints, this.xpoints.length);
        g.setColor(DisplayStyles.COLOR_TEXT);
        g.drawPolygon(this.xpoints, this.ypoints, this.xpoints.length);
    }
}
