// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui;

import ru.ifmo.cs.bcomp.State;
import ru.ifmo.cs.bcomp.MCDecoder;
import ru.ifmo.cs.bcomp.Utils;
import java.util.Iterator;
import ru.ifmo.cs.bcomp.Reg;
import ru.ifmo.cs.components.DataDestination;
import ru.ifmo.cs.bcomp.ControlSignal;
import java.util.Scanner;
import java.util.ArrayList;
import ru.ifmo.cs.bcomp.CPU;
import ru.ifmo.cs.bcomp.BasicComp;

public class CLI
{
    private final BasicComp bcomp;
    private final CPU cpu;
    private final ArrayList<Long> writelist;
    private int sleeptime;
    private volatile long savedPointer;
    private volatile boolean printOnStop;
    private volatile boolean printRegsTitle;
    private volatile boolean printMicroTitle;
    private volatile int sleep;
    private Scanner input;
    
    public CLI() throws Exception {
        this.writelist = new ArrayList<Long>();
        this.sleeptime = 1;
        this.printOnStop = true;
        this.printRegsTitle = false;
        this.printMicroTitle = false;
        this.sleep = 0;
        this.input = new Scanner(System.in);
        this.bcomp = new BasicComp();
        (this.cpu = this.bcomp.getCPU()).addDestination(ControlSignal.STOR, new DataDestination() {
            @Override
            public void setValue(final long value) {
                final long addr = CLI.this.cpu.getRegValue(Reg.AR);
                if (!CLI.this.writelist.contains(addr)) {
                    CLI.this.writelist.add(addr);
                }
            }
        });
        this.cpu.setCPUStartListener(new Runnable() {
            @Override
            public void run() {
                if (!CLI.this.printOnStop) {
                    return;
                }
                CLI.this.writelist.clear();
                CLI.this.savedPointer = CLI.this.cpu.getRegValue(CLI.this.cpu.getClockState() ? Reg.IP : Reg.MP);
                CLI.this.printRegsTitle();
            }
        });
        this.cpu.setCPUStopListener(new Runnable() {
            @Override
            public void run() {
                CLI.this.sleep = 0;
                if (!CLI.this.printOnStop) {
                    return;
                }
                CLI.this.printRegs(CLI.this.writelist.isEmpty() ? "" : (" " + CLI.this.getMemory(CLI.this.writelist.remove(0))));
                for (final Long wraddr : CLI.this.writelist) {
                    CLI.this.println(String.format("%1$34s", " ") + CLI.this.getMemory(wraddr));
                }
            }
        });
        this.cpu.setTickFinishListener(new Runnable() {
            @Override
            public void run() {
                if (CLI.this.sleep <= 0) {
                    return;
                }
                try {
                    Thread.sleep(CLI.this.sleep);
                }
                catch (InterruptedException ex) {}
            }
        });
    }
    
    private String getReg(final Reg reg) {
        return Utils.toHex(this.cpu.getRegValue(reg), this.cpu.getRegWidth(reg));
    }
    
    private String getMemory(final long addr) {
        return Utils.toHex(addr, 11L) + " " + Utils.toHex(this.cpu.getMemory().getValue(addr), 16L);
    }
    
    private void printMicroMemory(final long addr) {
        if (this.printMicroTitle) {
            this.println("\u0410\u0434\u0440    \u041c\u041a    \t\u041c\u0435\u0442\u043a\u0430\t\t\u0420\u0430\u0441\u0448\u0438\u0444\u0440\u043e\u0432\u043a\u0430");
            this.printMicroTitle = false;
        }
        this.println(MCDecoder.getFormattedMC(this.cpu, addr));
    }
    
    private void printRegsTitle() {
        if (!this.printRegsTitle) {
            return;
        }
        String space;
        this.println("\u0410\u0434\u0440 " + (this.cpu.getClockState() ? "\u0417\u043d\u0447\u043d" : "   \u041c\u041a    ") + (space = "  ") + Reg.IP.name() + space + Reg.AR.name() + space + Reg.SP.name() + space + Reg.CR.name() + (space = "   ") + Reg.DR.name() + space + Reg.BR.name() + space + Reg.AC.name() + "  NZVC " + (this.cpu.getClockState() ? "\u0410\u0434\u0440 \u0417\u043d\u0447\u043d" : "\u0421\u0447\u041c\u041a"));
        this.printRegsTitle = false;
    }
    
    private void printRegs(final String add) {
        this.println((this.cpu.getClockState() ? this.getMemory(this.savedPointer) : (Utils.toHex(this.savedPointer, 8L) + " " + Utils.toHex(this.cpu.getMicroCode().getValue(this.savedPointer), 40L))) + " " + this.getReg(Reg.IP) + " " + this.getReg(Reg.AR) + " " + this.getReg(Reg.SP) + " " + this.getReg(Reg.CR) + " " + this.getReg(Reg.DR) + " " + this.getReg(Reg.BR) + " " + this.getReg(Reg.AC) + " " + Utils.toBinary(this.cpu.getRegValue(Reg.PS) & 0xFL, 4) + (this.cpu.getClockState() ? add : ("  " + this.getReg(Reg.MP))));
    }
    
    private void printIO(final int ioaddr) {
        this.println("\u0412\u0423" + ioaddr + ": \u0424\u043b\u0430\u0433 =  \u0420\u0414\u0412\u0423 = ");
    }
    
    private boolean checkCmd(final String cmd, final String check) {
        return cmd.equalsIgnoreCase(check.substring(0, Math.min(check.length(), cmd.length())));
    }
    
    private void checkResult(final boolean result) throws Exception {
        if (!result) {
            throw new Exception("\u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f \u043d\u0435 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0430: \u0432\u044b\u043f\u043e\u043b\u043d\u044f\u0435\u0442\u0441\u044f \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u043c\u0430");
        }
    }
    
    protected void printHelp() {
        this.println("\u0414\u043e\u0441\u0442\u0443\u043f\u043d\u044b\u0435 \u043a\u043e\u043c\u0430\u043d\u0434\u044b:\na[ddress]\t- \u041f\u0443\u043b\u044c\u0442\u043e\u0432\u0430\u044f \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f \"\u0412\u0432\u043e\u0434 \u0430\u0434\u0440\u0435\u0441\u0430\"\nw[rite]\t\t- \u041f\u0443\u043b\u044c\u0442\u043e\u0432\u0430\u044f \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f \"\u0417\u0430\u043f\u0438\u0441\u044c\"\nr[ead]\t\t- \u041f\u0443\u043b\u044c\u0442\u043e\u0432\u0430\u044f \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f \"\u0427\u0442\u0435\u043d\u0438\u0435\"\ns[tart]\t\t- \u041f\u0443\u043b\u044c\u0442\u043e\u0432\u0430\u044f \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f \"\u041f\u0443\u0441\u043a\"\nc[continue]\t- \u041f\u0443\u043b\u044c\u0442\u043e\u0432\u0430\u044f \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f \"\u041f\u0440\u043e\u0434\u043e\u043b\u0436\u0438\u0442\u044c\"\nru[n]\t\t- \u041f\u0435\u0440\u0435\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u0440\u0435\u0436\u0438\u043c\u0430 \u0420\u0430\u0431\u043e\u0442\u0430/\u041e\u0441\u0442\u0430\u043d\u043e\u0432\ncl[ock]\t\t- \u041f\u0435\u0440\u0435\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u0440\u0435\u0436\u0438\u043c\u0430 \u043f\u043e\u0442\u0430\u043a\u0442\u043e\u0432\u043e\u0433\u043e \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f\nma[ddress]\t- \u041f\u0435\u0440\u0435\u0445\u043e\u0434 \u043d\u0430 \u043c\u0438\u043a\u0440\u043e\u043a\u043e\u043c\u0430\u043d\u0434\u0443\nmw[rite] value\t- \u0417\u0430\u043f\u0438\u0441\u044c \u043c\u0438\u043a\u0440\u043e\u043a\u043e\u043c\u0430\u043d\u0434\u044b\nmr[ead]\t\t- \u0427\u0442\u0435\u043d\u0438\u0435 \u043c\u0438\u043a\u0440\u043e\u043a\u043e\u043c\u0430\u043d\u0434\u044b\nmd[ecode]\t- \u0414\u0435\u043a\u043e\u0434\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0442\u0435\u043a\u0443\u0449\u0443\u044e \u043c\u0438\u043a\u0440\u043e\u043a\u043e\u043c\u0430\u043d\u0434\u0443\nio\t\t- \u0412\u044b\u0432\u043e\u0434 \u0441\u043e\u0441\u0442\u043e\u044f\u043d\u0438\u044f \u0432\u0441\u0435\u0445 \u0412\u0423\nio addr\t\t- \u0412\u044b\u0432\u043e\u0434 \u0441\u043e\u0441\u0442\u043e\u044f\u043d\u0438\u044f \u0443\u043a\u0430\u0437\u0430\u043d\u043d\u043e\u0433\u043e \u0412\u0423\nio addr value\t- \u0417\u0430\u043f\u0438\u0441\u044c value \u0432 \u0443\u043a\u0430\u0437\u0430\u043d\u043d\u043e\u0435 \u0412\u0423\nflag addr\t- \u0423\u0441\u0442\u0430\u043d\u043e\u0432\u043a\u0430 \u0444\u043b\u0430\u0433\u0430 \u0433\u043e\u0442\u043e\u0432\u043d\u043e\u0441\u0442\u0438 \u0443\u043a\u0430\u0437\u0430\u043d\u043d\u043e\u0433\u043e \u0412\u0423\nasm\t\t- \u0412\u0432\u043e\u0434 \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u043c\u044b \u043d\u0430 \u0430\u0441\u0441\u0435\u043c\u0431\u043b\u0435\u0440\u0435\nsleep value\t- \u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043c\u0435\u0436\u0434\u0443 \u0442\u0430\u043a\u0442\u0430\u043c\u0438 \u043f\u0440\u0438 \u0444\u043e\u043d\u043e\u0432\u043e\u043c \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0438\n{exit|quit}\t- \u0412\u044b\u0445\u043e\u0434 \u0438\u0437 \u044d\u043c\u0443\u043b\u044f\u0442\u043e\u0440\u0430\n(0000-FFFF)\t- \u0412\u0432\u043e\u0434 \u0448\u0435\u0441\u0442\u043d\u0430\u0434\u0446\u0430\u0442\u0435\u0440\u0438\u0447\u043d\u043e\u0433\u043e \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u0432 \u043a\u043b\u0430\u0432\u0438\u0448\u043d\u044b\u0439 \u0440\u0435\u0433\u0438\u0441\u0442\u0440\nlabelname\t- \u0412\u0432\u043e\u0434 \u0430\u0434\u0440\u0435\u0441\u0430 \u043c\u0435\u0442\u043a\u0438 \u0432 \u043a\u043b\u0430\u0432\u0438\u0448\u043d\u044b\u0439 \u0440\u0435\u0433\u0438\u0441\u0442\u0440");
    }
    
    public void cli() {
        this.println("\u042d\u043c\u0443\u043b\u044f\u0442\u043e\u0440 \u0411\u0430\u0437\u043e\u0432\u043e\u0439 \u042d\u0412\u041c. \u0412\u0435\u0440\u0441\u0438\u044f r" + CLI.class.getPackage().getImplementationVersion() + "\n\u0411\u042d\u0412\u041c \u0433\u043e\u0442\u043e\u0432\u0430 \u043a \u0440\u0430\u0431\u043e\u0442\u0435.\n\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439\u0442\u0435 ? \u0438\u043b\u0438 help \u0434\u043b\u044f \u043f\u043e\u043b\u0443\u0447\u0435\u043d\u0438\u044f \u0441\u043f\u0440\u0430\u0432\u043a\u0438");
        while (true) {
            String line;
            try {
                line = this.fetchLine();
            }
            catch (Exception e) {
                break;
            }
            this.processLine(line);
        }
        Runtime.getRuntime().exit(0);
    }
    
    protected void processLine(String line) {
        final String[] cmds = line.split("[ \t]+");
        if (cmds.length == 0) {
            return;
        }
        int i = 0;
        final boolean b = true;
        this.printMicroTitle = b;
        this.printRegsTitle = b;
        while (i < cmds.length) {
            final String cmd = cmds[i];
            Label_1056: {
                if (!cmd.equals("")) {
                    if (cmd.charAt(0) == '#') {
                        break;
                    }
                    if (this.checkCmd(cmd, "exit") || this.checkCmd(cmd, "quit")) {
                        Runtime.getRuntime().exit(0);
                    }
                    if (this.checkCmd(cmd, "?") || this.checkCmd(cmd, "help")) {
                        this.printHelp();
                    }
                    else {
                        try {
                            if (this.checkCmd(cmd, "address")) {
                                this.checkResult(this.cpu.executeSetAddr());
                                break Label_1056;
                            }
                            if (this.checkCmd(cmd, "write")) {
                                this.checkResult(this.cpu.executeWrite());
                                break Label_1056;
                            }
                            if (this.checkCmd(cmd, "read")) {
                                this.checkResult(this.cpu.executeRead());
                                break Label_1056;
                            }
                            if (this.checkCmd(cmd, "start")) {
                                if (i == cmds.length - 1) {
                                    this.sleep = this.sleeptime;
                                    this.checkResult(this.cpu.startStart());
                                }
                                else {
                                    this.checkResult(this.cpu.executeStart());
                                }
                                break Label_1056;
                            }
                            if (this.checkCmd(cmd, "continue")) {
                                if (i == cmds.length - 1) {
                                    this.sleep = this.sleeptime;
                                    this.checkResult(this.cpu.startContinue());
                                }
                                else {
                                    this.checkResult(this.cpu.executeContinue());
                                }
                                break Label_1056;
                            }
                            if (this.checkCmd(cmd, "clock")) {
                                this.println("\u0422\u0430\u043a\u0442: " + (this.cpu.invertClockState() ? "\u041d\u0435\u0442" : "\u0414\u0430"));
                                break Label_1056;
                            }
                            if (this.checkCmd(cmd, "run")) {
                                this.cpu.invertRunState();
                                this.println("\u0420\u0435\u0436\u0438\u043c \u0440\u0430\u0431\u043e\u0442\u044b: " + ((this.cpu.getProgramState(State.RUN) == 1L) ? "\u0420\u0430\u0431\u043e\u0442\u0430" : "\u041e\u0441\u0442\u0430\u043d\u043e\u0432"));
                                break Label_1056;
                            }
                            if (this.checkCmd(cmd, "maddress")) {
                                this.checkResult(this.cpu.executeSetMP());
                                this.printMicroMemory(this.cpu.getRegValue(Reg.MP));
                                break Label_1056;
                            }
                            if (this.checkCmd(cmd, "mwrite")) {
                                if (i == cmds.length - 1) {
                                    throw new Exception("\u043a\u043e\u043c\u0430\u043d\u0434\u0430 mwrite \u0442\u0440\u0435\u0431\u0443\u0435\u0442 \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442");
                                }
                                final long mc = Long.parseLong(cmds[++i], 16);
                                final long addr = this.cpu.getRegValue(Reg.MP);
                                this.checkResult(this.cpu.executeMCWrite(mc));
                                this.printMicroMemory(addr);
                                break Label_1056;
                            }
                            else {
                                if (this.checkCmd(cmd, "mread")) {
                                    final long addr2 = this.cpu.getRegValue(Reg.MP);
                                    this.checkResult(this.cpu.executeMCRead());
                                    this.printMicroMemory(addr2);
                                    break Label_1056;
                                }
                                if (this.checkCmd(cmd, "mdecode")) {
                                    this.printMicroMemory(this.cpu.getRegValue(Reg.MP));
                                    break Label_1056;
                                }
                                if (this.checkCmd(cmd, "io")) {
                                    if (i == cmds.length - 1) {
                                        for (int ioaddr = 0; ioaddr < 4; ++ioaddr) {
                                            this.printIO(ioaddr);
                                        }
                                        break Label_1056;
                                    }
                                    int ioaddr = Integer.parseInt(cmds[++i], 16);
                                    if (i < cmds.length - 1) {
                                        Integer.parseInt(cmds[++i], 16);
                                    }
                                    this.printIO(ioaddr);
                                    break Label_1056;
                                }
                                else if (this.checkCmd(cmd, "flag")) {
                                    if (i == cmds.length - 1) {
                                        throw new Exception("\u043a\u043e\u043c\u0430\u043d\u0434\u0430 flag \u0442\u0440\u0435\u0431\u0443\u0435\u0442 \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442");
                                    }
                                    final int ioaddr = Integer.parseInt(cmds[++i], 16);
                                    this.printIO(ioaddr);
                                    break Label_1056;
                                }
                                else {
                                    if (this.checkCmd(cmd, "asm") || this.checkCmd(cmd, "assembler")) {
                                        String code = "";
                                        this.println("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0442\u0435\u043a\u0441\u0442 \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u043c\u044b. \u0414\u043b\u044f \u043e\u043a\u043e\u043d\u0447\u0430\u043d\u0438\u044f \u0432\u0432\u0435\u0434\u0438\u0442\u0435 END");
                                        while (true) {
                                            line = this.fetchLine();
                                            if (line.equalsIgnoreCase("END")) {
                                                break;
                                            }
                                            code = code.concat(line.concat("\n"));
                                        }
                                        this.printOnStop = false;
                                        this.printOnStop = true;
                                        break Label_1056;
                                    }
                                    if (this.checkCmd(cmd, "sleep")) {
                                        if (i == cmds.length - 1) {
                                            throw new Exception("\u043a\u043e\u043c\u0430\u043d\u0434\u0430 sleep \u0442\u0440\u0435\u0431\u0443\u0435\u0442 \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442");
                                        }
                                        this.sleeptime = Integer.parseInt(cmds[++i], 16);
                                        break Label_1056;
                                    }
                                }
                            }
                        }
                        catch (Exception e) {
                            this.printOnStop = true;
                            this.println("\u041e\u0448\u0438\u0431\u043a\u0430: " + e.getMessage());
                            break Label_1056;
                        }
                        try {
                            if (Utils.isHexNumeric(cmd) && cmd.length() <= this.cpu.getRegWidth(Reg.IR) / 4L + ((cmd.charAt(0) == '-') ? 1 : 0)) {
                                final int value = Integer.parseInt(cmd, 16);
                                this.cpu.getRegister(Reg.IR).setValue(value);
                            }
                            else {
                                this.println("\u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u0430\u044f \u043a\u043e\u043c\u0430\u043d\u0434\u0430 " + cmd);
                            }
                        }
                        catch (Exception e) {
                            this.println("\u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u0430\u044f \u043a\u043e\u043c\u0430\u043d\u0434\u0430 " + cmd);
                        }
                    }
                }
            }
            ++i;
        }
    }
    
    protected String fetchLine() throws Exception {
        return this.input.nextLine();
    }
    
    protected void println(final String str) {
        System.out.println(str);
    }
}
