// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public abstract class DataPart extends BasicComponent implements DataSource, DataDestination
{
    protected long value;
    
    public DataPart(final long width) {
        super(width);
        this.value = 0L;
    }
    
    @Override
    public synchronized long getValue() {
        return this.value;
    }
    
    public abstract void setValue(final long p0, final long p1, final long p2);
}
