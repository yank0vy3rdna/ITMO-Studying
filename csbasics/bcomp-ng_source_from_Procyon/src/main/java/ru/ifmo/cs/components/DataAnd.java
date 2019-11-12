// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class DataAnd extends Control
{
    private final DataSource left;
    private final DataSource right;
    
    public DataAnd(final DataSource left, final DataSource right, final long width, final long ctrlbit, final DataDestination... dsts) {
        super(width, 0L, ctrlbit, dsts);
        this.left = left;
        this.right = right;
    }
    
    @Override
    public synchronized void setValue(final long value) {
        if (this.isOpen(value)) {
            super.setValue(this.left.getValue() & this.right.getValue() & this.mask);
        }
    }
}
