// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class Memory extends BasicComponent implements DataSource, DataDestination
{
    private final long[] memory;
    private final Register ar;
    private volatile long lastaccessed;
    
    public Memory(final long width, final Register ar) {
        super(width);
        final int n = 1;
        this.ar = ar;
        this.memory = new long[n << (int)ar.width];
        for (int i = 0; i < this.memory.length; this.memory[i++] = 0L) {}
    }
    
    public synchronized long getValue(final long addr) {
        return this.memory[(int)addr];
    }
    
    @Override
    public synchronized long getValue() {
        final long value = this.ar.getValue();
        this.lastaccessed = value;
        return this.getValue(value);
    }
    
    public synchronized void setValue(final long addr, final long value) {
        this.memory[(int)addr] = (value & this.mask);
    }
    
    @Override
    public synchronized void setValue(final long value) {
        this.setValue(this.lastaccessed = this.ar.getValue(), value);
    }
    public long getAddrWidth() {
        return this.ar.width;
    }
    
    public long getLastAccessedAddress() {
        return this.lastaccessed;
    }
}
