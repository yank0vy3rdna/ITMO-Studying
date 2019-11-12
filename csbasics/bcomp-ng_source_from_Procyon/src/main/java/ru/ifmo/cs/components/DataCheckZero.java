// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class DataCheckZero extends Control
{
    private final DataSource input;
    private final long inputmask;
    
    public DataCheckZero(final DataSource input, final long width, final long ctrlbit, final DataDestination... dsts) {
        super(1L, 0L, ctrlbit, dsts);
        this.input = input;
        this.inputmask = BasicComponent.calculateMask(width);
    }
    
    @Override
    public void setValue(final long value) {
        if (this.isOpen(value)) {
            super.setValue(((this.input.getValue() & this.inputmask) == 0x0L) ? 1 : 0);
        }
    }
}
