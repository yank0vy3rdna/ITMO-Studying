// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class Bus extends DataPart
{
    public Bus(final long width) {
        super(width);
    }
    
    @Override
    public synchronized void setValue(final long value) {
        this.value |= (value & this.mask);
    }
    
    @Override
    public synchronized void setValue(final long value, final long mask, final long startbit) {
        this.value |= ((value & mask) << (int)startbit & this.mask);
    }
    
    public synchronized void resetValue() {
        this.value = 0L;
    }
}
