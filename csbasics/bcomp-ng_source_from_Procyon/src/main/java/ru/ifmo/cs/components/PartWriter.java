// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class PartWriter extends BasicComponent implements DataDestination
{
    private final DataPart dst;
    private final long startbit;
    
    public PartWriter(final DataPart dst, final long width, final long startbit) {
        super(width);
        this.dst = dst;
        this.startbit = startbit;
    }
    
    @Override
    public synchronized void setValue(final long value) {
        this.dst.setValue(value, this.mask, this.startbit);
    }
}
