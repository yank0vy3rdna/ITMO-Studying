// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Graphics;
import ru.ifmo.cs.bcomp.Utils;
import java.awt.Color;
import javax.swing.JLabel;
import ru.ifmo.cs.components.Register;
import ru.ifmo.cs.components.DataDestination;

public class RegisterView extends BCompComponent implements DataDestination
{
    private int formatWidth;
    private int valuemask;
    private boolean hex;
    private boolean isLeft;
    private final Register reg;
    protected final JLabel value;
    
    public RegisterView(final Register reg, final Color colorTitleBG) {
        super("", 0, colorTitleBG);
        this.value = this.addValueLabel();
        this.reg = reg;
    }
    
    public RegisterView(final Register reg) {
        this(reg, DisplayStyles.COLOR_TITLE);
    }
    
    @Override
    protected void setBounds(final int x, final int y, final int wight) {
        this.setBounds(x, y, this.width = wight, this.height);
    }
    
    protected void setProperties(final int x, final int y, final boolean hex, final int regWidth, final boolean isLeft) {
        this.hex = hex;
        this.formatWidth = regWidth;
        this.valuemask = (1 << regWidth) - 1;
        this.isLeft = isLeft;
        this.setBounds(x, y, this.getValueWidth(regWidth, hex) + 15);
        this.setValue();
        if (!isLeft) {
            this.title.setBounds(1, 1, 25, 26);
            this.value.setBounds(27, 1, this.width - 28, 26);
        }
        else {
            this.title.setBounds(this.width - 26, 1, 25, 26);
            this.value.setBounds(1, 1, this.width - 28, 26);
        }
    }
    
    public void setProperties(final int x, final int y, final boolean hex, final boolean isLeft) {
        this.setProperties(x, y, hex, (int)this.reg.width, isLeft);
    }
    
    protected long getRegWidth() {
        return this.reg.width;
    }
    
    protected void setValue(final String val) {
        this.value.setText(val);
    }
    
    public void setValue() {
        this.setValue(this.hex ? Utils.toHex(this.reg.getValue() & (long)this.valuemask, this.formatWidth) : Utils.toBinary((int)this.reg.getValue() & this.valuemask, this.formatWidth));
    }
    
    @Override
    public void setValue(final long value) {
        this.setValue();
    }
    
    public Register getReg() {
        return this.reg;
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(this.isLeft ? (this.width - 26) : 25, 1, this.isLeft ? (this.width - 26) : 25, 25);
    }
}
