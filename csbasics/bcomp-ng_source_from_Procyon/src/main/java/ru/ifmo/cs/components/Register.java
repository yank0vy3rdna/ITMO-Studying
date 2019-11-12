// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class Register extends DataPart
{
    public Register(final long width) {
        super(width);
    }
    
    @Override
    public synchronized void setValue(final long value) {
        this.value = (value & this.mask);
    }
    
    @Override
    public synchronized void setValue(final long value, final long mask, final long startbit) {
        this.value = (((this.value & ~(mask << (int)startbit)) | (value & mask) << (int)startbit) & this.mask);
    }
    
    public synchronized void invertBit(final long startbit) {
        this.value ^= (1L << (int)startbit & this.mask);
    }
    
    public synchronized long getValue(final long startbit) {
        return this.value >> (int)startbit & 0x1L;
    }
}
