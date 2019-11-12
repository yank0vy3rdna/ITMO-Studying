// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp;

import java.util.Iterator;
import java.util.ArrayList;

public class MCDecoder
{
    private static ControlSignal[] signals;
    private static ControlSignal[] LEFT;
    private static ControlSignal[] RIGHT;
    
    public static final String[] decodeMC(final CPU cpu, final long addr) {
        final MicroCode mc = cpu.getMicroCodeSource();
        final String[] res = new String[3];
        final ArrayList<ControlSignal> cs = new ArrayList<ControlSignal>();
        final long cmd = cpu.getMicroCode().getValue(addr);
        res[0] = mc.getLabel((int)addr);
        res[1] = Utils.toHex(cmd, 40L);
        for (int i = 0; i < 16; ++i) {
            if ((cmd & 1L << i) != 0x0L) {
                cs.add(MCDecoder.signals[i]);
            }
        }
        if ((cmd & 1L << ControlSignal.TYPE.ordinal()) == 0x0L) {
            for (int i = 16; i < ControlSignal.TYPE.ordinal(); ++i) {
                if ((cmd & 1L << i) != 0x0L) {
                    cs.add(MCDecoder.signals[i]);
                }
            }
            res[2] = decodeOMC(cs);
        }
        else {
            res[2] = decodeCMC(mc, cs, cmd >> 16 & 0xFFL, cmd >> 24 & 0xFFL, cmd >> 32 & 0x1L);
        }
        return res;
    }
    
    public static String getFormattedMC(final CPU cpu, final long addr) {
        final String[] decoded = decodeMC(cpu, addr);
        return Utils.toHex(addr, 8L) + " " + decoded[1] + "\t" + ((decoded[0] == null) ? "\t\t" : (decoded[0] + ((decoded[0].length() > 7) ? "\t" : "\t\t"))) + ((decoded[2] == null) ? "No operations" : decoded[2]);
    }
    
    private static String decodeCMC(final MicroCode mc, final ArrayList<ControlSignal> cs, long checkbit, final long addr, final long expected) {
        final String label = mc.getLabel((int)addr);
        final String aluOutput = getAluOutput(cs);
        String bit = null;
        final String to = ((label == null) ? "" : (label + " @ ")) + Utils.toHex(addr, 8L);
        int i;
        for (i = 0; i < 8 && (checkbit & 0x1L) != 0x1L; ++i, checkbit >>= 1) {}
        if (cs.contains(ControlSignal.HTOL)) {
            i += 8;
        }
        if (aluOutput.equals("PS")) {
            if (i == State.PS0.ordinal()) {
                return "GOTO " + to;
            }
            for (final State state : State.values()) {
                if (i == state.ordinal()) {
                    bit = state.name();
                }
            }
        }
        else {
            bit = "" + i;
        }
        return "if " + aluOutput + "(" + bit + ") = " + expected + " then GOTO " + to;
    }
    
    private static String decodeOMC(final ArrayList<ControlSignal> cs) {
        final ArrayList<String> operations = new ArrayList<String>();
        final String writelist = getWriteList(cs);
        String result = null;
        if (writelist != null) {
            final String swOutput = getSwOutput(cs);
            operations.add(((swOutput == null) ? "0" : swOutput) + " \u2192 " + writelist);
        }
        if (cs.contains(ControlSignal.STOR)) {
            operations.add("DR \u2192 MEM(AR)");
        }
        if (cs.contains(ControlSignal.LOAD)) {
            operations.add("MEM(AR) \u2192 DR");
        }
        if (cs.contains(ControlSignal.IO)) {
            operations.add("IO");
        }
        if (cs.contains(ControlSignal.CLRF)) {
            operations.add("Clear flags");
        }
        if (cs.contains(ControlSignal.DINT)) {
            operations.add("Disable interrupts");
        }
        if (cs.contains(ControlSignal.EINT)) {
            operations.add("Enable interrupts");
        }
        if (cs.contains(ControlSignal.HALT)) {
            operations.add("Halt");
        }
        for (final String op : operations) {
            result = ((result == null) ? op : (result + "; " + op));
        }
        return result;
    }
    
    private static String getInput(final ArrayList<ControlSignal> cs, final ControlSignal[] rdsignals) {
        String regs = null;
        boolean addpar = false;
        for (final ControlSignal c : rdsignals) {
            if (cs.contains(c)) {
                final String name = c.name().substring(2);
                if (regs == null) {
                    regs = name;
                }
                else {
                    regs = regs + " | " + name;
                    addpar = true;
                }
            }
        }
        return (regs == null) ? null : ((addpar ? "(" : "") + regs + (addpar ? ")" : ""));
    }
    
    private static String getComplement(final ArrayList<ControlSignal> cs, final ControlSignal[] rdsignals, final ControlSignal complement) {
        final String input = getInput(cs, rdsignals);
        if (cs.contains(complement)) {
            if (input == null) {
                return "~0";
            }
            return "~" + input;
        }
        else {
            if (input == null) {
                return null;
            }
            return input;
        }
    }
    
    private static String getAluOutput(final ArrayList<ControlSignal> cs) {
        final String left = getComplement(cs, MCDecoder.LEFT, ControlSignal.COML);
        final String right = getComplement(cs, MCDecoder.RIGHT, ControlSignal.COMR);
        if (cs.contains(ControlSignal.SORA)) {
            return ((left == null) ? "0" : left) + " & " + ((right == null) ? "0" : right);
        }
        final boolean pls1 = cs.contains(ControlSignal.PLS1);
        if (left != null) {
            return left + ((right == null) ? "" : (" + " + right)) + (pls1 ? " + 1" : "");
        }
        if (pls1) {
            return (right == null) ? "1" : (right + " + 1");
        }
        return (right == null) ? "0" : right;
    }
    
    private static String getSwOutput(final ArrayList<ControlSignal> cs) {
        final String alu = getAluOutput(cs);
        if (cs.contains(ControlSignal.HTOH)) {
            if (cs.contains(ControlSignal.LTOL)) {
                return alu;
            }
            return "HTOH(" + alu + ")";
        }
        else if (cs.contains(ControlSignal.LTOL)) {
            if (cs.contains(ControlSignal.SEXT)) {
                return "extend sign " + alu + "(0..7)";
            }
            return "LTOL(" + alu + ")";
        }
        else if (cs.contains(ControlSignal.LTOH)) {
            if (cs.contains(ControlSignal.HTOL)) {
                return "SWAB(" + alu + ")";
            }
            return "LTOH(" + alu + ")";
        }
        else {
            if (cs.contains(ControlSignal.HTOL)) {
                return "HTOL(" + alu + ")";
            }
            if (cs.contains(ControlSignal.SEXT)) {
                return "SEXT(" + alu + ")";
            }
            if (cs.contains(ControlSignal.SHLT)) {
                if (cs.contains(ControlSignal.SHL0)) {
                    return "ROL(" + alu + ")";
                }
                return "SHL(" + alu + ")";
            }
            else {
                if (!cs.contains(ControlSignal.SHRT)) {
                    return null;
                }
                if (cs.contains(ControlSignal.SHRF)) {
                    return "ROR(" + alu + ")";
                }
                return "ASR(" + alu + ")";
            }
        }
    }
    
    private static String getWriteList(final ArrayList<ControlSignal> cs) {
        final ArrayList<String> dsts = new ArrayList<String>();
        String result = null;
        for (int i = ControlSignal.WRDR.ordinal(); i <= ControlSignal.WRAR.ordinal(); ++i) {
            if (cs.contains(MCDecoder.signals[i])) {
                dsts.add(MCDecoder.signals[i].name().substring(2));
            }
        }
        if (cs.contains(ControlSignal.STNZ)) {
            dsts.add("N");
            dsts.add("Z");
        }
        if (cs.contains(ControlSignal.SETV)) {
            dsts.add("V");
        }
        if (cs.contains(ControlSignal.SETC)) {
            dsts.add("C");
        }
        for (final String dst : dsts) {
            result = ((result == null) ? dst : (result + ", " + dst));
        }
        return result;
    }
    
    static {
        MCDecoder.signals = ControlSignal.values();
        MCDecoder.LEFT = new ControlSignal[] { ControlSignal.RDAC, ControlSignal.RDBR, ControlSignal.RDPS, ControlSignal.RDIR };
        MCDecoder.RIGHT = new ControlSignal[] { ControlSignal.RDDR, ControlSignal.RDCR, ControlSignal.RDIP, ControlSignal.RDSP };
    }
}
