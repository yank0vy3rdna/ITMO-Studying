// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class AutoIncRegister extends Register
{
    public AutoIncRegister(final long width) {
        super(width);
    }
    
    @Override
    public synchronized void setValue(final long value) {
        super.setValue((value == 0L) ? (this.value + 1L) : value);
    }
}
