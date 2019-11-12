// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class BasicComponent
{
    public final long width;
    public final long mask;
    
    public BasicComponent(final long width) {
        this.width = width;
        this.mask = calculateMask(width);
    }
    
    public static long calculateMask(final long width) {
        return (1L << (int)width) - 1L;
    }
}
