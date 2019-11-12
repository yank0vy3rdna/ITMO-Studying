// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class Xor extends Control
{
    private final DataSource input;
    private final long inputwidth;
    private final long inputstartbit;
    
    public Xor(final DataSource input, final long width, final long startbit, final long ctrlbit, final DataDestination... dsts) {
        super(1L, 0L, ctrlbit, dsts);
        this.input = input;
        this.inputwidth = width;
        this.inputstartbit = startbit;
    }
    
    @Override
    public void setValue(final long value) {
        if (this.isOpen(value)) {
            long src = this.input.getValue() >> (int)this.inputstartbit;
            long res = src & 0x1L;
            for (long i = 1L; i < this.inputwidth; ++i) {
                src >>= 1;
                res ^= (src & 0x1L);
            }
            super.setValue(res);
        }
    }
}
