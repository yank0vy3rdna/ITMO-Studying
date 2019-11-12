// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui;

import ru.ifmo.cs.bcomp.MCDecoder;
import ru.ifmo.cs.bcomp.RunningCycle;
import ru.ifmo.cs.bcomp.BasicComp;
import ru.ifmo.cs.bcomp.MicroCode;
import ru.ifmo.cs.bcomp.CPU;

public class MicroCodeDecoder
{
    private final CPU cpu;
    private final MicroCode mc;
    
    public MicroCodeDecoder() throws Exception {
        this.cpu = new BasicComp().getCPU();
        this.mc = this.cpu.getMicroCodeSource();
        this.cpu.stopCPU();
    }
    
    public void decode() throws Exception {
        final int infetch = this.mc.findLabel(RunningCycle.INFETCH.name());
        final int reserved = this.mc.findLabel(RunningCycle.RESERVED.name());
        int addr = infetch;
        while (addr < reserved) {
            System.out.println(MCDecoder.getFormattedMC(this.cpu, addr++));
        }
    }
}
