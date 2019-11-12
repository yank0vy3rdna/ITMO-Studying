// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class ValveTwo extends Control
{
    private long ctrlbit2;
    
    public ValveTwo(final long ctrlbit1, final long ctrlbit2, final DataDestination... dsts) {
        super(1L, 0L, ctrlbit1, dsts);
        this.ctrlbit2 = ctrlbit2;
    }
    
    @Override
    public synchronized void setValue(final long value) {
        if (this.isOpen(value)) {
            super.setValue(value >> (int)this.ctrlbit2 & 0x1L);
        }
    }
}
