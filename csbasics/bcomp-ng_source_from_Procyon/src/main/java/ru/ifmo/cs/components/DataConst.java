// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class DataConst extends BasicComponent implements DataSource
{
    private final long value;
    
    public DataConst(final long value, final long width) {
        super(width);
        this.value = (value & this.mask);
    }
    
    @Override
    public long getValue() {
        return this.value;
    }
}
