// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp;

public class Instruction
{
    private int instr;
    private String mnemonics;
    private Type type;
    
    public Instruction(final int instr, final String mnemonics, final Type type) {
        this.instr = instr;
        this.mnemonics = mnemonics;
        this.type = type;
    }
    
    public int getInstr() {
        return this.instr;
    }
    
    public String getMnemonics() {
        return this.mnemonics;
    }
    
    public Type getType() {
        return this.type;
    }
    
    public enum Type
    {
        ADDR, 
        NONADDR, 
        IO;
    }
}
