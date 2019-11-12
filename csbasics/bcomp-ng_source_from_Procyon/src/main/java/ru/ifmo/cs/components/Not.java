// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class Not extends Control
{
    public Not(final long ctrlbit, final DataDestination... dsts) {
        super(1L, 0L, ctrlbit, dsts);
    }
    
    @Override
    public synchronized void setValue(final long value) {
        super.setValue(this.isOpen(value) ? 0 : 1);
    }
}
