// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp;

import ru.ifmo.cs.components.Consts;
import ru.ifmo.cs.components.DataCheckZero;
import ru.ifmo.cs.components.Xor;
import ru.ifmo.cs.components.ValveTwo;
import ru.ifmo.cs.components.Extender;
import ru.ifmo.cs.components.BasicComponent;
import ru.ifmo.cs.components.DataAdd;
import ru.ifmo.cs.components.DataPart;
import ru.ifmo.cs.components.PartWriter;
import ru.ifmo.cs.components.DataAnd;
import ru.ifmo.cs.components.ValveValue;
import ru.ifmo.cs.components.Complement;
import ru.ifmo.cs.components.Not;
import ru.ifmo.cs.components.DataSource;
import ru.ifmo.cs.components.Valve;
import ru.ifmo.cs.components.DataDestination;
import ru.ifmo.cs.components.AutoIncRegister;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import ru.ifmo.cs.components.Memory;
import ru.ifmo.cs.components.Bus;
import ru.ifmo.cs.components.Control;
import ru.ifmo.cs.components.Register;
import java.util.EnumMap;

public class CPU
{
    private static final long MR_WIDTH;
    private static final long VR_WIDTH;
    private static final long MP_WIDTH = 8L;
    private static final long AR_WIDTH = 11L;
    private static final long DATA_WIDTH = 16L;
    private static final long PS_WIDTH = 16L;
    private final EnumMap<Reg, Register> regs;
    private final EnumMap<ControlSignal, Control> valves;
    private final EnumMap<Buses, Bus> buses;
    private final EnumMap<RunningCycle, Integer> labels;
    private final MicroCode mc;
    private final Memory mem;
    private final Memory microcode;
    private final Register ps;
    private final Register ir;
    private final Register mp;
    private final Bus vv;
    private final Bus expected;
    private final Bus newmp;
    private volatile boolean clock;
    private final ReentrantLock tick;
    private final ReentrantLock lock;
    private final Condition lockStart;
    private final Condition lockFinish;
    private volatile Runnable tickStartListener;
    private volatile Runnable tickFinishListener;
    private volatile Runnable cpuStartListener;
    private volatile Runnable cpuStopListener;
    private final Thread cpu;
    
    protected CPU() throws Exception {
        this.regs = new EnumMap<Reg, Register>(Reg.class);
        this.valves = new EnumMap<ControlSignal, Control>(ControlSignal.class);
        this.buses = new EnumMap<Buses, Bus>(Buses.class);
        this.labels = new EnumMap<RunningCycle, Integer>(RunningCycle.class);
        this.mc = new MicroCode();
        this.clock = true;
        this.tick = new ReentrantLock();
        this.lock = new ReentrantLock();
        this.lockStart = this.lock.newCondition();
        this.lockFinish = this.lock.newCondition();
        this.tickStartListener = null;
        this.tickFinishListener = null;
        this.cpuStartListener = null;
        this.cpuStopListener = null;
        this.cpu = new Thread(new Runnable() {
            @Override
            public void run() {
                CPU.this.lock.lock();
                try {
                    while (true) {
                        CPU.this.lockFinish.signalAll();
                        CPU.this.lockStart.await();
                        if (CPU.this.cpuStartListener != null) {
                            CPU.this.cpuStartListener.run();
                        }
                        if (CPU.this.clock) {
                            CPU.this.valves.get(ControlSignal.SET_PROGRAM).setValue(1L);
                        }
                        do {
                            if (CPU.this.tickStartListener != null) {
                                CPU.this.tickStartListener.run();
                            }
                            CPU.this.tick.lock();
                            try {
                                CPU.this.step();
                            }
                            finally {
                                CPU.this.tick.unlock();
                            }
                            if (CPU.this.tickFinishListener != null) {
                                CPU.this.tickFinishListener.run();
                            }
                        } while (CPU.this.ps.getValue(State.PROG.ordinal()) == 1L);
                        if (CPU.this.cpuStopListener != null) {
                            CPU.this.cpuStopListener.run();
                        }
                    }
                }
                catch (InterruptedException e) {}
                finally {
                    CPU.this.lock.unlock();
                }
            }
        }, "BComp");
        final Register dr = new Register(16L);
        this.regs.put(Reg.DR, dr);
        final Register cr = new Register(16L);
        this.regs.put(Reg.CR, cr);
        final Register ip = new Register(11L);
        this.regs.put(Reg.IP, ip);
        final Register sp = new Register(11L);
        this.regs.put(Reg.SP, sp);
        final Register ac = new Register(16L);
        this.regs.put(Reg.AC, ac);
        final Register br = new Register(16L);
        this.regs.put(Reg.BR, br);
        this.regs.put(Reg.PS, this.ps = new Register(16L));
        this.regs.put(Reg.IR, this.ir = new Register(16L));
        final Register ar = new Register(11L);
        this.regs.put(Reg.AR, ar);
        final Register mr = new Register(CPU.MR_WIDTH);
        this.regs.put(Reg.MR, mr);
        this.regs.put(Reg.MP, this.mp = new AutoIncRegister(8L));
        this.mem = new Memory(16L, ar);
        this.microcode = new Memory(CPU.MR_WIDTH, this.mp);
        this.valves.put(ControlSignal.CLOCK0, new Valve(this.microcode, CPU.MR_WIDTH, 0L, 0L, new DataDestination[] { mr }));
        final Bus right = new Bus(16L);
        this.buses.put(Buses.RIGHT_INPUT, right);
        final Bus left = new Bus(16L);
        this.buses.put(Buses.LEFT_INPUT, left);
        final Bus rcom = new Bus(16L);
        this.buses.put(Buses.RIGHT_COMPLEMENT, rcom);
        final Bus lcom = new Bus(16L);
        this.buses.put(Buses.LEFT_COMPLEMENT, lcom);
        final Bus aluout = new Bus(19L);
        this.buses.put(Buses.ALU_OUT, aluout);
        final Bus swout = new Bus(18L);
        this.buses.put(Buses.SWITCH_OUT, swout);
        this.buses.put(Buses.VV, this.vv = new Bus(1L));
        this.buses.put(Buses.EXPECTED, this.expected = new Bus(1L));
        this.buses.put(Buses.NEWMP, this.newmp = new Bus(8L));
        final Control clock1 = new Valve(mr, CPU.MR_WIDTH, 0L, 0L, new DataDestination[] { this.newValve(dr, 16L, 0L, ControlSignal.RDDR, right), this.newValve(cr, 16L, 0L, ControlSignal.RDCR, right), this.newValve(ip, 16L, 0L, ControlSignal.RDIP, right), this.newValve(sp, 16L, 0L, ControlSignal.RDSP, right), this.newValve(ac, 16L, 0L, ControlSignal.RDAC, left), this.newValve(br, 16L, 0L, ControlSignal.RDBR, left), this.newValve(this.ps, 16L, 0L, ControlSignal.RDPS, left), this.newValve(this.ir, 16L, 0L, ControlSignal.RDIR, left) });
        this.valves.put(ControlSignal.CLOCK1, clock1);
        clock1.addDestination(new Not(ControlSignal.COMR.ordinal(), new DataDestination[] { new Valve(right, 16L, 0L, 0L, new DataDestination[] { rcom }) }));
        final Control control = clock1;
        final DataDestination[] dsts = { null };
        Control c = (Control)(dsts[0] = new Complement(right, 16L, 0L, ControlSignal.COMR.ordinal(), new DataDestination[] { rcom }));
        control.addDestination(dsts);
        this.valves.put(ControlSignal.COMR, c);
        clock1.addDestination(new Not(ControlSignal.COML.ordinal(), new DataDestination[] { new Valve(left, 16L, 0L, 0L, new DataDestination[] { lcom }) }));
        final Control control2 = clock1;
        final DataDestination[] dsts2 = { null };
        c = (Control)(dsts2[0] = new Complement(left, 16L, 0L, ControlSignal.COML.ordinal(), new DataDestination[] { lcom }));
        control2.addDestination(dsts2);
        this.valves.put(ControlSignal.COMR, c);
        final ValveValue carry = new ValveValue(ControlSignal.PLS1.ordinal());
        clock1.addDestination(carry);
        this.valves.put(ControlSignal.PLS1, carry);
        final Control control3 = clock1;
        final DataDestination[] dsts3 = { null };
        c = (Control)(dsts3[0] = new DataAnd(lcom, rcom, 16L, ControlSignal.SORA.ordinal(), new DataDestination[] { aluout }));
        control3.addDestination(dsts3);
        this.valves.put(ControlSignal.SORA, c);
        final PartWriter writetoH = new PartWriter(swout, 8L, 8L);
        clock1.addDestination(new Not(ControlSignal.SORA.ordinal(), new DataDestination[] { new DataAdd(lcom, rcom, carry, 16L, 0L, new DataDestination[] { aluout }), new Valve(this.ps, 1L, 0L, 0L, new DataDestination[] { new PartWriter(aluout, 1L, 18L) }) }));
        clock1.addDestination(this.newValve(aluout, 8L, 0L, ControlSignal.LTOL, swout));
        clock1.addDestination(this.newValve(aluout, 8L, 0L, ControlSignal.LTOH, writetoH));
        clock1.addDestination(this.newValve(aluout, 8L, 8L, ControlSignal.HTOL, swout));
        clock1.addDestination(this.newValve(aluout, 10L, 8L, ControlSignal.HTOH, new PartWriter(swout, 10L, 8L)));
        final Control vr0 = this.newValve(mr, CPU.VR_WIDTH, 16L, ControlSignal.TYPE, new DataDestination() {
            @Override
            public synchronized void setValue(final long value) {
                CPU.this.newmp.setValue(value >> 8 & BasicComponent.calculateMask(8L));
                CPU.this.expected.setValue(value >> 16 & 0x1L);
            }
        });
        clock1.addDestination(vr0);
        for (long i = 0L; i < 8L; ++i) {
            vr0.addDestination(new Valve(swout, 1L, i, i, new DataDestination[] { this.vv }));
        }
        final PartWriter writeto15 = new PartWriter(swout, 1L, 15L);
        final PartWriter writeto16 = new PartWriter(swout, 1L, 17L);
        final PartWriter ei = new PartWriter(this.ps, 1L, State.EI.ordinal());
        final PartWriter stateProgram = new PartWriter(this.ps, 1L, State.PROG.ordinal());
        this.valves.put(ControlSignal.SEXT, c = new Extender(aluout, 8L, 7L, ControlSignal.SEXT.ordinal() - 16, new DataDestination[] { writetoH }));
        final Control control4 = clock1;
        final DataDestination[] dsts4 = { null };
        final int n = 0;
        final long ctrlbit = ControlSignal.TYPE.ordinal();
        final DataDestination[] dsts5 = { null };
        final int n2 = 0;
        final Register input = mr;
        final long vr_WIDTH = CPU.VR_WIDTH;
        final long startbit = 16L;
        final long ctrlbit2 = 0L;
        final DataDestination[] dsts6 = new DataDestination[26];
        dsts6[0] = c;
        dsts6[1] = new Valve(aluout, 1L, 14L, ControlSignal.SHLT.ordinal() - 16, new DataDestination[] { writeto16 });
        dsts6[2] = this.newValveH(aluout, 16L, 0L, ControlSignal.SHLT, new PartWriter(swout, 16L, 1L));
        dsts6[3] = this.newValveH(aluout, 1L, 18L, ControlSignal.SHL0, swout);
        dsts6[4] = this.newValveH(aluout, 15L, 1L, ControlSignal.SHRT, swout);
        dsts6[5] = new Valve(aluout, 1L, 0L, ControlSignal.SHRT.ordinal() - 16, new DataDestination[] { new PartWriter(swout, 1L, 16L) });
        final int n3 = 6;
        final long ctrlbit3 = ControlSignal.SHRT.ordinal() - 16;
        final long ctrlbit4 = ControlSignal.SHRF.ordinal() - 16;
        final DataDestination[] dsts7 = new DataDestination[2];
        final Control shrf = (Control)(dsts7[0] = new Valve(aluout, 1L, 18L, 0L, new DataDestination[] { writeto15, writeto16 }));
        dsts7[1] = new Not(0L, new DataDestination[] { new Valve(aluout, 1L, 15L, 0L, new DataDestination[] { writeto15, writeto16 }) });
        dsts6[n3] = new ValveTwo(ctrlbit3, ctrlbit4, dsts7);
        dsts6[7] = this.newValveH(swout, 1L, 16L, ControlSignal.SETC, new PartWriter(this.ps, 1L, State.C.ordinal()));
        final Control setv = (Control)(dsts6[8] = new Xor(swout, 2L, 16L, ControlSignal.SETV.ordinal() - 16, new DataDestination[] { new PartWriter(this.ps, 1L, State.V.ordinal()) }));
        dsts6[9] = new DataCheckZero(swout, 16L, ControlSignal.STNZ.ordinal() - 16, new DataDestination[] { new PartWriter(this.ps, 1L, State.Z.ordinal()) });
        dsts6[10] = this.newValveH(swout, 1L, 15L, ControlSignal.STNZ, new PartWriter(this.ps, 1L, State.N.ordinal()));
        dsts6[11] = this.newValveH(swout, 16L, 0L, ControlSignal.WRDR, dr);
        dsts6[12] = this.newValveH(swout, 16L, 0L, ControlSignal.WRCR, cr);
        dsts6[13] = this.newValveH(swout, 11L, 0L, ControlSignal.WRIP, ip);
        dsts6[14] = this.newValveH(swout, 11L, 0L, ControlSignal.WRSP, sp);
        dsts6[15] = this.newValveH(swout, 16L, 0L, ControlSignal.WRAC, ac);
        dsts6[16] = this.newValveH(swout, 16L, 0L, ControlSignal.WRBR, br);
        dsts6[17] = this.newValveH(swout, 16L, 0L, ControlSignal.WRPS, this.ps);
        dsts6[18] = this.newValveH(swout, 11L, 0L, ControlSignal.WRAR, ar);
        dsts6[19] = this.newValveH(this.mem, 16L, 0L, ControlSignal.LOAD, dr);
        dsts6[20] = this.newValveH(dr, 16L, 0L, ControlSignal.STOR, this.mem);
        dsts6[21] = this.newValveH(dr, 16L, 0L, ControlSignal.IO, new DataDestination[0]);
        dsts6[22] = this.newValveH(Consts.consts[1], 1L, 0L, ControlSignal.CLRF, new DataDestination[0]);
        dsts6[23] = this.newValveH(Consts.consts[0], 1L, 0L, ControlSignal.DINT, ei);
        dsts6[24] = this.newValveH(Consts.consts[1], 1L, 0L, ControlSignal.EINT, ei);
        dsts6[25] = this.newValveH(Consts.consts[0], 1L, 0L, ControlSignal.HALT, stateProgram);
        dsts5[n2] = new Valve(input, vr_WIDTH, startbit, ctrlbit2, dsts6);
        dsts4[n] = new Not(ctrlbit, dsts5);
        control4.addDestination(dsts4);
        this.valves.put(ControlSignal.SHRF, shrf);
        this.valves.put(ControlSignal.SETV, setv);
        this.valves.put(ControlSignal.SET_PROGRAM, new Valve(Consts.consts[1], 1L, 0L, 0L, new DataDestination[] { stateProgram }));
        clock1.addDestination(new DataDestination() {
            @Override
            public void setValue(final long value) {
                CPU.this.mp.setValue((CPU.this.vv.getValue() == CPU.this.expected.getValue()) ? CPU.this.newmp.getValue() : 0L);
            }
        });
        for (int j = 0; j < this.mc.getMicroCodeLength(); ++j) {
            this.microcode.setValue(j, this.mc.getMicroCommand(j));
        }
        for (final RunningCycle cycle : RunningCycle.values()) {
            this.labels.put(cycle, this.findLabel(cycle.name()));
        }
        this.mp.setValue(this.labels.get(RunningCycle.STOP) + 1);
    }
    
    private Control newValve(final DataSource input, final long width, final long startbit, final ControlSignal cs, final DataDestination... dsts) {
        final Control valve = new Valve(input, width, startbit, cs.ordinal(), dsts);
        this.valves.put(cs, valve);
        return valve;
    }
    
    private Control newValveH(final DataSource input, final long width, final long startbit, final ControlSignal cs, final DataDestination... dsts) {
        final Control valve = new Valve(input, width, startbit, cs.ordinal() - 16, dsts);
        this.valves.put(cs, valve);
        return valve;
    }
    
    public EnumMap<Reg, Register> getRegisters() {
        return this.regs;
    }
    
    public Register getRegister(final Reg reg) {
        return this.regs.get(reg);
    }
    
    public Memory getMemory() {
        return this.mem;
    }
    
    public Memory getMicroCode() {
        return this.microcode;
    }
    
    public MicroCode getMicroCodeSource() {
        return this.mc;
    }
    
    public synchronized void step() {
        for (final Buses bus : Buses.values()) {
            this.buses.get(bus).resetValue();
        }
        this.valves.get(ControlSignal.CLOCK0).setValue(1L);
        this.valves.get(ControlSignal.CLOCK1).setValue(1L);
    }
    
    void startCPU() throws InterruptedException {
        this.lock.lock();
        try {
            this.cpu.start();
            this.lockFinish.await();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void stopCPU() {
        this.cpu.interrupt();
    }
    
    public boolean isLocked() {
        return this.lock.isLocked();
    }
    
    public void tickLock() {
        this.tick.lock();
    }
    
    public void tickUnlock() {
        this.tick.unlock();
    }
    
    public void addDestination(final ControlSignal cs, final DataDestination dest) {
        this.valves.get(cs).addDestination(dest);
    }
    
    public void removeDestination(final ControlSignal cs, final DataDestination dest) {
        this.valves.get(cs).removeDestination(dest);
    }
    
    public void setTickStartListener(final Runnable tickStartListener) {
        this.tickStartListener = tickStartListener;
    }
    
    public void setTickFinishListener(final Runnable tickFinishListener) {
        this.tickFinishListener = tickFinishListener;
    }
    
    public void setCPUStartListener(final Runnable cpuStartListener) {
        this.cpuStartListener = cpuStartListener;
    }
    
    public void setCPUStopListener(final Runnable cpuStopListener) {
        this.cpuStopListener = cpuStopListener;
    }
    
    public void setRunState(final boolean state) {
        this.tick.lock();
        try {
            this.ps.setValue(state ? 1 : 0, 1L, State.RUN.ordinal());
        }
        finally {
            this.tick.unlock();
        }
    }
    
    public void invertRunState() {
        this.tick.lock();
        try {
            this.ps.invertBit(State.RUN.ordinal());
        }
        finally {
            this.tick.unlock();
        }
    }
    
    public long getRegValue(final Reg reg) {
        return this.regs.get(reg).getValue();
    }
    
    public long getRegWidth(final Reg reg) {
        return this.regs.get(reg).width;
    }
    
    public long getProgramState(final State state) {
        return this.ps.getValue(state.ordinal());
    }
    
    public boolean getClockState() {
        return this.clock;
    }
    
    public void setClockState(final boolean clock) {
        this.tick.lock();
        try {
            if (!(this.clock = clock)) {
                this.valves.get(ControlSignal.HALT).setValue(1L << ControlSignal.HALT.ordinal() - 16);
            }
        }
        finally {
            this.tick.unlock();
        }
    }
    
    public boolean invertClockState() {
        this.setClockState(!this.clock);
        return this.clock;
    }
    
    public final int findLabel(final String label) throws Exception {
        return this.mc.findLabel(label);
    }
    
    private void jump(final long addr) {
        if (addr > 0L) {
            this.mp.setValue(addr);
        }
    }
    
    private boolean startFrom(final long addr) {
        if (this.lock.tryLock()) {
            try {
                this.jump(addr);
                this.lockStart.signal();
            }
            finally {
                this.lock.unlock();
            }
            return true;
        }
        return false;
    }
    
    public boolean startSetAddr() {
        return this.startFrom(this.labels.get(RunningCycle.SETIP));
    }
    
    public boolean startWrite() {
        return this.startFrom(this.labels.get(RunningCycle.WRITE));
    }
    
    public boolean startRead() {
        return this.startFrom(this.labels.get(RunningCycle.READ));
    }
    
    public boolean startStart() {
        return this.startFrom(this.labels.get(RunningCycle.START));
    }
    
    public boolean startContinue() {
        return this.startFrom(0L);
    }
    
    private boolean executeFrom(final long label) {
        if (this.lock.tryLock()) {
            try {
                this.jump(label);
                this.lockStart.signal();
                this.lockFinish.await();
            }
            catch (InterruptedException ex) {}
            finally {
                this.lock.unlock();
            }
            return true;
        }
        return false;
    }
    
    public boolean executeSetAddr() {
        return this.executeFrom(this.labels.get(RunningCycle.SETIP));
    }
    
    public boolean executeSetAddr(final long value) {
        this.ir.setValue(value);
        return this.executeSetAddr();
    }
    
    public boolean executeWrite() {
        return this.executeFrom(this.labels.get(RunningCycle.WRITE));
    }
    
    public boolean executeWrite(final long value) {
        this.ir.setValue(value);
        return this.executeWrite();
    }
    
    public boolean executeRead() {
        return this.executeFrom(this.labels.get(RunningCycle.READ));
    }
    
    public boolean executeStart() {
        return this.executeFrom(this.labels.get(RunningCycle.START));
    }
    
    public boolean executeContinue() {
        return this.executeFrom(0L);
    }
    
    public boolean executeSetMP() {
        if (this.lock.tryLock()) {
            try {
                this.mp.setValue(this.ir.getValue());
            }
            finally {
                this.lock.unlock();
            }
            return true;
        }
        return false;
    }
    
    public boolean executeMCWrite(final long value) {
        if (this.lock.tryLock()) {
            try {
                this.microcode.setValue(value);
                this.mp.setValue(0L);
            }
            finally {
                this.lock.unlock();
            }
            return true;
        }
        return false;
    }
    
    public boolean executeMCRead() {
        if (this.lock.tryLock()) {
            try {
                this.valves.get(ControlSignal.CLOCK0).setValue(1L);
                this.mp.setValue(0L);
            }
            finally {
                this.lock.unlock();
            }
            return true;
        }
        return false;
    }
    
    public RunningCycle getRunningCycle() {
        final long addr = this.mp.getValue();
        final RunningCycle[] cycles = RunningCycle.values();
        int i;
        for (i = cycles.length - 1; i > 0; --i) {
            if (addr >= this.labels.get(cycles[i])) {
                return cycles[i];
            }
        }
        return cycles[i];
    }
    
    static {
        MR_WIDTH = ControlSignal.TYPE.ordinal() + 1;
        VR_WIDTH = CPU.MR_WIDTH - 17L;
    }
    
    private enum Buses
    {
        RIGHT_INPUT, 
        LEFT_INPUT, 
        RIGHT_COMPLEMENT, 
        LEFT_COMPLEMENT, 
        ALU_OUT, 
        SWITCH_OUT, 
        VV, 
        EXPECTED, 
        NEWMP;
    }
}
