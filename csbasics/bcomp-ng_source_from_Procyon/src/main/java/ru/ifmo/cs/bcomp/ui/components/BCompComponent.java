// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Graphics;
import ru.ifmo.cs.bcomp.Utils;
import java.awt.Color;
import javax.swing.JLabel;

public class BCompComponent extends BorderedComponent
{
    protected JLabel title;
    
    BCompComponent(final String title, final int ncells, final Color color) {
        super(3 + 25 * (ncells + 1));
        this.title = this.addLabel(title, DisplayStyles.FONT_COURIER_BOLD_18, color);
    }
    
    public BCompComponent(final String title, final Color colorTitleBG) {
        this(title, 1, colorTitleBG);
    }
    
    public BCompComponent(final String title, final int ncells) {
        this(title, ncells, DisplayStyles.COLOR_TITLE);
    }
    
    private final JLabel addValueLabel(final String value, final Color color) {
        return this.addLabel(value, DisplayStyles.FONT_COURIER_BOLD_21, color);
    }
    
    protected final JLabel addValueLabel(final Color color) {
        return this.addValueLabel("", color);
    }
    
    protected JLabel addValueLabel(final String value) {
        return this.addValueLabel(value, DisplayStyles.COLOR_VALUE);
    }
    
    protected final JLabel addValueLabel() {
        return this.addValueLabel("", DisplayStyles.COLOR_VALUE);
    }
    
    @Override
    protected void setBounds(final int x, final int y, final int width) {
        super.setBounds(x, y, width);
        this.title.setBounds(1, 1, width - 2, 25);
    }
    
    protected int getValueY(final int n) {
        return 2 + 25 * (n + 1);
    }
    
    private int getPixelWidth(final int chars) {
        return 2 + DisplayStyles.FONT_COURIER_BOLD_21_WIDTH * (1 + chars);
    }
    
    protected int getValueWidth(final int width) {
        return this.getPixelWidth(Utils.getHexWidth(width));
    }
    
    protected int getValueWidth(final int width, final boolean hex) {
        return hex ? this.getValueWidth(width) : this.getPixelWidth(Utils.getBinaryWidth(width));
    }
    
    protected void setTitle(final String title) {
        this.title.setText(title);
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
    }
}
