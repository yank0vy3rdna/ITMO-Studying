// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.components;

public class Consts
{
    public static final DataSource[] consts;
    
    static {
        consts = new DataSource[2];
        for (int i = 0; i < Consts.consts.length; ++i) {
            Consts.consts[i] = new DataConst(i, 1L);
        }
    }
}
