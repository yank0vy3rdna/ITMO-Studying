// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.awt.GridBagConstraints;
import ru.ifmo.cs.bcomp.Reg;

public class RegisterProperties
{
    public final Reg reg;
    public final int x;
    public final int y;
    public final boolean hex;
    public final GridBagConstraints constraints;
    public final boolean isLeft;
    
    public RegisterProperties(final Reg reg, final int x, final int y, final boolean hex, final boolean isLeft, final GridBagConstraints constraints) {
        this.reg = reg;
        this.x = x;
        this.y = y;
        this.hex = hex;
        this.constraints = constraints;
        this.isLeft = isLeft;
    }
}
