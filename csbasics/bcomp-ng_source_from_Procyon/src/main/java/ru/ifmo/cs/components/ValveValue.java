// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class ValveValue extends Control implements DataSource
{
    private volatile long value;
    
    public ValveValue(final long ctrlbit) {
        super(1L, 0L, ctrlbit, new DataDestination[0]);
        this.value = 0L;
    }
    
    @Override
    public synchronized void setValue(final long value) {
        this.value = (this.isOpen(value) ? 1 : 0);
    }
    
    @Override
    public synchronized long getValue() {
        return this.value;
    }
}
