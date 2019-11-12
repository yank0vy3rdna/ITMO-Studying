// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import javax.swing.JLabel;

public class BCompLabel extends BorderedComponent
{
    public BCompLabel(final int x, final int y, final int width, final String... text) {
        super(text.length * 25 + 2);
        this.setBounds(x, y, width);
        for (int i = 0; i < text.length; ++i) {
            final JLabel title = this.addLabel(text[i], DisplayStyles.FONT_COURIER_BOLD_21, DisplayStyles.COLOR_TITLE);
            title.setBounds(1, 1 + i * 25, width - 2, 25);
        }
    }
}
