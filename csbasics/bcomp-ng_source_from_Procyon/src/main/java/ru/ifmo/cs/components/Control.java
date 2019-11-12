// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

import java.util.Iterator;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;

public class Control extends BasicComponent implements DataDestination
{
    private final ArrayList<DataDestination> dsts;
    protected final long startbit;
    protected final long ctrlbit;
    
    public Control(final long width, final long startbit, final long ctrlbit, final DataDestination... dsts) {
        super(width);
        this.dsts = new ArrayList<DataDestination>();
        this.startbit = startbit;
        this.ctrlbit = ctrlbit;
        this.dsts.addAll(Arrays.asList(dsts));
    }
    
    public synchronized void addDestination(final DataDestination... dsts) {
        this.dsts.addAll(Arrays.asList(dsts));
    }
    
    public synchronized void removeDestination(final DataDestination dst) {
        final int index = this.dsts.indexOf(dst);
        if (index >= 0) {
            this.dsts.remove(index);
        }
    }
    
    protected boolean isOpen(final long value) {
        return (value >> (int)this.ctrlbit & 0x1L) == 0x1L;
    }
    
    @Override
    public synchronized void setValue(long value) {
        value = (value >> (int)this.startbit & this.mask);
        for (final DataDestination dst : this.dsts) {
            dst.setValue(value);
        }
    }
}
