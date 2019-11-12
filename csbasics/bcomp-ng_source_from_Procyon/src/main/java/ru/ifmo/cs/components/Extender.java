// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class Extender extends Control
{
    private final DataSource input;
    private final long startbit;
    
    public Extender(final DataSource input, final long width, final long startbit, final long ctrlbit, final DataDestination... dsts) {
        super(width, 0L, ctrlbit, dsts);
        this.input = input;
        this.startbit = startbit;
    }
    
    @Override
    public synchronized void setValue(final long value) {
        if (this.isOpen(value)) {
            super.setValue(((this.input.getValue() >> (int)this.startbit & 0x1L) == 0x1L) ? this.mask : 0L);
        }
    }
}
