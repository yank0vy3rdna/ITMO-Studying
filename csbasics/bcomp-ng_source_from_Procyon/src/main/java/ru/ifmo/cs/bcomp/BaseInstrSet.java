// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp;

public class BaseInstrSet
{
    public static final Instruction[] instructions;
    
    static {
        instructions = new Instruction[] { new Instruction(0, "ISZ", Instruction.Type.ADDR), new Instruction(4096, "AND", Instruction.Type.ADDR), new Instruction(8192, "JSR", Instruction.Type.ADDR), new Instruction(12288, "MOV", Instruction.Type.ADDR), new Instruction(16384, "ADD", Instruction.Type.ADDR), new Instruction(20480, "ADC", Instruction.Type.ADDR), new Instruction(24576, "SUB", Instruction.Type.ADDR), new Instruction(32768, "BCS", Instruction.Type.ADDR), new Instruction(36864, "BPL", Instruction.Type.ADDR), new Instruction(40960, "BMI", Instruction.Type.ADDR), new Instruction(45056, "BEQ", Instruction.Type.ADDR), new Instruction(49152, "BR", Instruction.Type.ADDR), new Instruction(57344, "CLF", Instruction.Type.IO), new Instruction(57600, "TSF", Instruction.Type.IO), new Instruction(57856, "IN", Instruction.Type.IO), new Instruction(58112, "OUT", Instruction.Type.IO), new Instruction(61440, "HLT", Instruction.Type.NONADDR), new Instruction(61696, "NOP", Instruction.Type.NONADDR), new Instruction(61952, "CLA", Instruction.Type.NONADDR), new Instruction(62208, "CLC", Instruction.Type.NONADDR), new Instruction(62464, "CMA", Instruction.Type.NONADDR), new Instruction(62720, "CMC", Instruction.Type.NONADDR), new Instruction(62976, "ROL", Instruction.Type.NONADDR), new Instruction(63232, "ROR", Instruction.Type.NONADDR), new Instruction(63488, "INC", Instruction.Type.NONADDR), new Instruction(63744, "DEC", Instruction.Type.NONADDR), new Instruction(64000, "EI", Instruction.Type.NONADDR), new Instruction(64256, "DI", Instruction.Type.NONADDR) };
    }
}
