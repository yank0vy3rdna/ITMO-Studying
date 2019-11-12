// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import ru.ifmo.cs.bcomp.Utils;
import javax.swing.JLabel;

public class ActiveBitView extends BCompComponent
{
    private final JLabel value;
    
    public ActiveBitView(final int x, final int y) {
        super("", 0, DisplayStyles.COLOR_INPUT_TITLE);
        this.value = this.addValueLabel();
        this.setBounds(x, y, this.getValueWidth(2, true));
        this.value.setBounds(1, 1, this.width - 2, 25);
    }
    
    @Override
    protected void setBounds(final int x, final int y, final int wight) {
        this.setBounds(x, y, this.width = wight, this.height);
    }
    
    public void setValue(final int value) {
        this.value.setText(Utils.toHex(value, 1L));
    }
}
