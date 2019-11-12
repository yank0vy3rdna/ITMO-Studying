// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class Valve extends Control
{
    private final DataSource input;
    
    public Valve(final DataSource input, final long width, final long startbit, final long ctrlbit, final DataDestination... dsts) {
        super(width, startbit, ctrlbit, dsts);
        this.input = input;
    }
    
    @Override
    public synchronized void setValue(final long value) {
        if (this.isOpen(value)) {
            super.setValue(this.input.getValue());
        }
    }
}
