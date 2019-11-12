// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp;

public class MicroCode
{
    private final omc[] MP;
    
    public MicroCode() {
        this.MP = new omc[] { 
		 new omc(Utils.cs(new ControlSignal[0])), 
		 new omc("INFETCH", Utils.cs(ControlSignal.RDIP, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRAR, ControlSignal.WRBR)), 
		 new omc(Utils.cs(ControlSignal.RDBR, ControlSignal.PLS1, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRIP, ControlSignal.LOAD)), 
		 new omc(Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRCR)), 
		 new CMC(Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 7L, 1L, "CHKBR"), 
		 new CMC(Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 6L, 1L, "CHKABS"), 
		 new CMC(Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 5L, 1L, "CHKABS"), 
		 new CMC(Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 4L, 0L, "ADDRLESS"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "IO"), 
		 new CMC("CHKBR", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 6L, 0L, "CHKABS"), 
		 new CMC(Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 5L, 0L, "CHKABS"), 
		 new CMC(Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 4L, 1L, "BRANCHES"), 
		 new CMC("CHKABS", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 3L, 0L, "OPFETCH"), 
		 new omc("ADFETCH", Utils.cs(ControlSignal.RDCR, ControlSignal.SEXT, ControlSignal.LTOL, ControlSignal.WRBR)), 
		 new CMC(Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 2L, 1L, "T11XX"), 
		 new omc("T10XX", Utils.cs(ControlSignal.RDBR, ControlSignal.RDIP, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRAR)), 
		 new omc(Utils.cs(ControlSignal.LOAD)), 
		 new CMC(Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 1L, 1L, "T101X"), 
		 new CMC("T100X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "RESERVED"), 
		 new CMC("T1000", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "OPFETCH"), 
		 new CMC("T101X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "T1011"), 
		 new omc("T1010", Utils.cs(ControlSignal.RDDR, ControlSignal.PLS1, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new omc(Utils.cs(ControlSignal.STOR)), 
		 new omc(Utils.cs(ControlSignal.RDDR, ControlSignal.COML, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "OPFETCH"), 
		 new omc("T1011", Utils.cs(ControlSignal.RDDR, ControlSignal.COML, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new omc(Utils.cs(ControlSignal.STOR)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "OPFETCH"), 
		 new CMC("T11XX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 1L, 1L, "T111X"), 
		 new CMC("T110X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "RESERVED"), 
		 new omc("T1100", Utils.cs(ControlSignal.RDBR, ControlSignal.RDSP, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "OPFETCH"), 
		 new CMC("T111X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 0L, "T1110"), 
		 new omc("T1111", Utils.cs(ControlSignal.RDBR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "EXEC"), 
		 new omc("T1110", Utils.cs(ControlSignal.RDBR, ControlSignal.RDIP, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new CMC("OPFETCH", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 7L, 0L, "RDVALUE"), 
		 new CMC(Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 6L, 1L, "CMD11XX"), 
		 new omc("RDVALUE", Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRAR)), 
		 new omc(Utils.cs(ControlSignal.LOAD)), 
		 new CMC("EXEC", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 7L, 1L, "CMD1XXX"), 
		 new CMC("CMD0XXX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 6L, 1L, "CMD01XX"), 
		 new CMC("CMD000X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 4L, 1L, "OR"), 
		 new omc("AND", Utils.cs(ControlSignal.RDAC, ControlSignal.RDDR, ControlSignal.SORA, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("OR", Utils.cs(ControlSignal.RDAC, ControlSignal.RDDR, ControlSignal.COML, ControlSignal.COMR, ControlSignal.SORA, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRBR)), 
		 new omc(Utils.cs(ControlSignal.RDBR, ControlSignal.COML, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("CMD01XX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 5L, 1L, "CMD011X"), 
		 new CMC("CMD010X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 4L, 1L, "ADC"), 
		 new omc("ADD", Utils.cs(ControlSignal.RDAC, ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.SETC, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("ADC", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.C.ordinal(), 0L, "ADD"), 
		 new omc(Utils.cs(ControlSignal.RDAC, ControlSignal.RDDR, ControlSignal.PLS1, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.SETC, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("CMD011X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 4L, 1L, "CMP"), 
		 new omc("SUB", Utils.cs(ControlSignal.RDAC, ControlSignal.RDDR, ControlSignal.COMR, ControlSignal.PLS1, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.SETC, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("CMP", Utils.cs(ControlSignal.RDAC, ControlSignal.RDDR, ControlSignal.COMR, ControlSignal.PLS1, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.SETC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("CMD1XXX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 5L, 1L, "CMD101X"), 
		 new CMC("CMD100X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 4L, 1L, "RESERVED"), 
		 new omc("LOOP", Utils.cs(ControlSignal.RDDR, ControlSignal.COML, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new omc(Utils.cs(ControlSignal.STOR)), 
		 new CMC(Utils.cs(ControlSignal.RDDR, ControlSignal.HTOL), 7L, 1L, "INT"), 
		 new omc(Utils.cs(ControlSignal.RDIP, ControlSignal.PLS1, ControlSignal.WRIP)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("CMD101X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 4L, 1L, "SWAM"), 
		 new omc("LD", Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("SWAM", Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRBR)), 
		 new omc(Utils.cs(ControlSignal.RDAC, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new omc(Utils.cs(ControlSignal.RDBR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.WRAC, ControlSignal.STOR)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("CMD11XX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 5L, 1L, "ST"), 
		 new CMC("CMD110X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 4L, 1L, "CALL"), 
		 new omc("JUMP", Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRIP)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("CALL", Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRBR)), 
		 new omc(Utils.cs(ControlSignal.RDIP, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new omc(Utils.cs(ControlSignal.RDBR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRIP)), 
		 new omc("PUSHVAL", Utils.cs(ControlSignal.RDSP, ControlSignal.COML, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRSP, ControlSignal.WRAR)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "STORE"), 
		 new omc("ST", Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRAR)), 
		 new omc(Utils.cs(ControlSignal.RDAC, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new omc("STORE", Utils.cs(ControlSignal.STOR)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("BRANCHES", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 3L, 1L, "BR1XXX"), 
		 new CMC("BR0XXX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 2L, 1L, "BR01XX"), 
		 new CMC("BR00XX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 1L, 1L, "BR001X"), 
		 new CMC("BR000X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "BNE"), 
		 new CMC("BEQ", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.Z.ordinal(), 0L, "INT"), 
		 new omc("BR", Utils.cs(ControlSignal.RDCR, ControlSignal.SEXT, ControlSignal.LTOL, ControlSignal.WRBR)), 
		 new omc(Utils.cs(ControlSignal.RDBR, ControlSignal.RDIP, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRIP)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("BNE", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.Z.ordinal(), 0L, "BR"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("BR001X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "BPL"), 
		 new CMC("BMI", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.N.ordinal(), 1L, "BR"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("BPL", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.N.ordinal(), 0L, "BR"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("BR01XX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 1L, 1L, "BR011X"), 
		 new CMC("BR010X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "BCC"), 
		 new CMC("BCS", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.C.ordinal(), 1L, "BR"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("BCC", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.C.ordinal(), 0L, "BR"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("BR011X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "BVC"), 
		 new CMC("BVS", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.V.ordinal(), 1L, "BR"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("BVC", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.V.ordinal(), 0L, "BR"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("BR1XXX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 2L, 1L, "RESERVED"), 
		 new CMC("BR10XX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 1L, 1L, "BR101X"), 
		 new CMC("BR100X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "BGE"), 
		 new CMC("BLT", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.N.ordinal(), 0L, "BVS"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "BVC"), 
		 new CMC("BGE", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.N.ordinal(), 0L, "BVC"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "BVS"), 
		 new CMC("BR101X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "BFC"), 
		 new CMC("BFS", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.F.ordinal(), 1L, "BR"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("BFC", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.F.ordinal(), 0L, "BR"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("ADDRLESS", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 3L, 1L, "AL1XXX"), 
		 new CMC("AL0XXX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 2L, 1L, "AL01XX"), 
		 new CMC("AL00XX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 1L, 1L, "AL001X"), 
		 new CMC("AL000X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 0L, "INT"), 
		 new CMC("HLT", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "STOP"), 
		 new CMC("AL001X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "AL0011"), 
		 new CMC("AL0010", Utils.cs(ControlSignal.RDCR, ControlSignal.LTOL), 7L, 1L, "CMA"), 
		 new omc("CLA", Utils.cs(ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("CMA", Utils.cs(ControlSignal.RDAC, ControlSignal.COML, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("AL0011", Utils.cs(ControlSignal.RDCR, ControlSignal.LTOL), 7L, 1L, "CMC"), 
		 new omc("CLC", Utils.cs(ControlSignal.SETC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("CMC", Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.C.ordinal(), 1L, "CLC"), 
		 new omc(Utils.cs(ControlSignal.COML, ControlSignal.COMR, ControlSignal.HTOH, ControlSignal.SETC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("AL01XX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 1L, 1L, "AL011X"), 
		 new CMC("AL010X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "AL0101"), 
		 new CMC("AL0100", Utils.cs(ControlSignal.RDCR, ControlSignal.LTOL), 7L, 1L, "ROR"), 
		 new omc("ROL", Utils.cs(ControlSignal.RDAC, ControlSignal.SHLT, ControlSignal.SHL0, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.SETC, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("ROR", Utils.cs(ControlSignal.RDAC, ControlSignal.SHRT, ControlSignal.SHRF, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.SETC, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("AL0101", Utils.cs(ControlSignal.RDCR, ControlSignal.LTOL), 7L, 1L, "ASR"), 
		 new omc("ASL", Utils.cs(ControlSignal.RDAC, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new omc(Utils.cs(ControlSignal.RDAC, ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.SETC, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("ASR", Utils.cs(ControlSignal.RDAC, ControlSignal.SHRT, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.SETC, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("AL011X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "AL0111"), 
		 new CMC("AL0110", Utils.cs(ControlSignal.RDCR, ControlSignal.LTOL), 7L, 1L, "SWAB"), 
		 new omc("SXTB", Utils.cs(ControlSignal.RDAC, ControlSignal.SEXT, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("SWAB", Utils.cs(ControlSignal.RDAC, ControlSignal.HTOL, ControlSignal.LTOH, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("AL0111", Utils.cs(ControlSignal.RDCR, ControlSignal.LTOL), 7L, 1L, "NEG"), 
		 new CMC("AL01110", Utils.cs(ControlSignal.RDCR, ControlSignal.LTOL), 6L, 1L, "DEC"), 
		 new omc("INC", Utils.cs(ControlSignal.RDAC, ControlSignal.PLS1, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.SETC, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("DEC", Utils.cs(ControlSignal.RDAC, ControlSignal.COMR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.SETC, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("NEG", Utils.cs(ControlSignal.RDAC, ControlSignal.COML, ControlSignal.PLS1, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.SETC, ControlSignal.WRAC)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new CMC("AL1XXX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 2L, 1L, "AL11XX"), 
		 new omc("AL10XX", Utils.cs(ControlSignal.RDSP, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRAR)), 
		 new omc(Utils.cs(ControlSignal.LOAD)), 
		 new CMC(Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 1L, 1L, "AL101X"), 
		 new CMC("AL100X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "POPF"), 
		 new omc("POP", Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.WRAC)), 
		 new omc("INCSP", Utils.cs(ControlSignal.RDSP, ControlSignal.PLS1, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRSP)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("POPF", Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRPS)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INCSP"), 
		 new CMC("AL101X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "IRET"), 
		 new omc("RET", Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRIP)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INCSP"), 
		 new omc("IRET", Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRPS)), 
		 new omc(Utils.cs(ControlSignal.RDSP, ControlSignal.PLS1, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRSP, ControlSignal.WRAR)), 
		 new omc(Utils.cs(ControlSignal.LOAD)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "RET"), 
		 new CMC("AL11XX", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 1L, 1L, "AL111X"), 
		 new CMC("AL110X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "PUSHF"), 
		 new omc("PUSH", Utils.cs(ControlSignal.RDAC, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "PUSHVAL"), 
		 new omc("PUSHF", Utils.cs(ControlSignal.RDPS, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "PUSHVAL"), 
		 new CMC("AL111X", Utils.cs(ControlSignal.RDCR, ControlSignal.HTOL), 0L, 1L, "RESERVED"), 
		 new omc("SWAP", Utils.cs(ControlSignal.RDSP, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRAR)), 
		 new omc(Utils.cs(ControlSignal.LOAD)), 
		 new omc(Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRBR)), 
		 new omc(Utils.cs(ControlSignal.RDAC, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new omc(Utils.cs(ControlSignal.RDBR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.WRAC, ControlSignal.STOR)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("IO", Utils.cs(ControlSignal.IO)), 
		 new CMC("INT", Utils.cs(ControlSignal.RDPS, ControlSignal.HTOL), State.RUN.ordinal() - 8, 0L, "STOP"), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.INTR.ordinal(), 0L, "INFETCH"), 
		 new omc(Utils.cs(ControlSignal.RDSP, ControlSignal.COML, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRSP, ControlSignal.WRAR)), 
		 new omc(Utils.cs(ControlSignal.RDIP, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new omc(Utils.cs(ControlSignal.STOR)), 
		 new omc(Utils.cs(ControlSignal.RDSP, ControlSignal.COML, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRSP, ControlSignal.WRAR)), 
		 new omc(Utils.cs(ControlSignal.RDPS, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new omc(Utils.cs(ControlSignal.STOR)), 
		 new omc(Utils.cs(ControlSignal.WRAR)), 
		 new omc(Utils.cs(ControlSignal.LOAD)), 
		 new omc(Utils.cs(ControlSignal.RDDR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRIP, ControlSignal.DINT)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INFETCH"), 
		 new omc("START", Utils.cs(ControlSignal.STNZ, ControlSignal.SETV, ControlSignal.SETC, ControlSignal.WRDR, ControlSignal.WRCR, ControlSignal.WRSP, ControlSignal.WRAC, ControlSignal.WRBR, ControlSignal.WRAR, ControlSignal.DINT, ControlSignal.CLRF)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INT"), 
		 new omc("READ", Utils.cs(ControlSignal.RDIP, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRAR)), 
		 new omc(Utils.cs(ControlSignal.RDIR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new omc(Utils.cs(ControlSignal.RDIP, ControlSignal.PLS1, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRIP, ControlSignal.LOAD)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "STOP"), 
		 new omc("WRITE", Utils.cs(ControlSignal.RDIP, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRAR)), 
		 new omc(Utils.cs(ControlSignal.RDIR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRDR)), 
		 new omc(Utils.cs(ControlSignal.RDIP, ControlSignal.PLS1, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRIP, ControlSignal.STOR)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "STOP"), 
		 new omc("SETIP", Utils.cs(ControlSignal.RDIR, ControlSignal.HTOH, ControlSignal.LTOL, ControlSignal.WRIP)), 
		 new omc("STOP", Utils.cs(ControlSignal.HALT)), 
		 new CMC(Utils.cs(ControlSignal.RDPS, ControlSignal.LTOL), State.PS0.ordinal(), 0L, "INFETCH"), 
		 new omc("RESERVED", Utils.cs(new ControlSignal[0])) };
    }
    
    public int getMicroCodeLength() {
        return this.MP.length;
    }
    
    public long getMicroCommand(final int addr) throws Exception {
        return this.MP[addr].getMicroCommand();
    }
    
    public int findLabel(final String label) throws Exception {
        for (int addr = 0; addr < this.MP.length; ++addr) {
            if (label.equals(this.MP[addr].label)) {
                return addr;
            }
        }
        throw 
 new Exception("Label '" + label + "' not found");
    }
    
    public String getLabel(final int addr) {
        return (addr < this.MP.length) ? this.MP[addr].label : null;
    }
    
    private class omc
    {
        public final String label;
        private final long microcmd;
        private final ControlSignal[] signals;
        
        public omc(final String label, final ControlSignal[] signals) {
            long microcmd = 0L;
            this.label = label;
            this.signals = signals;
            for (final ControlSignal cs : signals) {
                microcmd |= 1L << cs.ordinal();
            }
            this.microcmd = microcmd;
        }
        
        public omc(final MicroCode microCode, final ControlSignal[] signals) {
            this(microCode, null, signals);
        }
        
        public long getMicroCommand() throws Exception {
            if (this.label != null) {
                for (final omc mc : MicroCode.this.MP) {
                    if (this != mc && this.label.equals(mc.label)) {
                        throw new Exception("Found duplicate label '" + this.label + "'");
                    }
                }
            }
            return this.microcmd;
        }
    }
    
    private class CMC extends omc
    {
        private final String labelto;
        private final long microcmd;
        public CMC(final String label, final ControlSignal[] signals, final long startbit, final long expected, final String labelto) {
            super(label, signals);
            this.labelto = labelto;
            this.microcmd = (1L << ControlSignal.TYPE.ordinal()) + (1L << (int)(startbit + 16L)) + (expected << 32);
        }
        
        public CMC(final MicroCode microCode, final ControlSignal[] signals, final long startbit, final long expected, final String labelto) {
        	super(microCode,signals);
            this(microCode, null, signals, startbit, expected, labelto);
        }
        
        @Override
        public long getMicroCommand() throws Exception {
            return this.microcmd | super.getMicroCommand() | (long)MicroCode.this.findLabel(this.labelto) << 24;
        }
    }
}
