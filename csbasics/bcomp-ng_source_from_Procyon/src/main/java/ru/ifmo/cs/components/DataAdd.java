// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class DataAdd extends Control
{
    private final DataSource left;
    private final DataSource right;
    private final DataSource carry;
    private final long vmask;
    
    public DataAdd(final DataSource left, final DataSource right, final DataSource carry, final long width, final long ctrlbit, final DataDestination... dsts) {
        super(width + 2L, 0L, ctrlbit, dsts);
        this.left = left;
        this.right = right;
        this.carry = carry;
        this.vmask = BasicComponent.calculateMask(width - 1L);
    }
    
    @Override
    public synchronized void setValue(final long value) {
        if (this.isOpen(value)) {
            final long l = this.left.getValue();
            final long r = this.right.getValue();
            final long c = this.carry.getValue();
            super.setValue(l + r + c + ((l & this.vmask) + (r & this.vmask) + c << 2 & (long)(1 << (int)(this.width - 1L))) & this.mask);
        }
    }
}
