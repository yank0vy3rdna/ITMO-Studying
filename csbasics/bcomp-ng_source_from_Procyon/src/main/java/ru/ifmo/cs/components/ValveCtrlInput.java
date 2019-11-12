// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class ValveCtrlInput extends Valve
{
    private final DataSource ctrl;
    
    public ValveCtrlInput(final DataSource input, final long width, final long startbit, final DataSource ctrl, final int ctrlbit) {
        super(input, width, startbit, ctrlbit, new DataDestination[0]);
        this.ctrl = ctrl;
    }
    
    @Override
    public synchronized void setValue(final long value) {
        super.setValue(this.ctrl.getValue());
    }
}
