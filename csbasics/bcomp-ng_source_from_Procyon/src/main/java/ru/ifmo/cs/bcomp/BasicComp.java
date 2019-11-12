// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp;

public class BasicComp
{
    private final CPU cpu;
    
    public BasicComp() throws Exception {
        (this.cpu = new CPU()).startCPU();
    }
    
    public CPU getCPU() {
        return this.cpu;
    }
    
    public void addDestination(final SignalListener[] listeners) {
        this.cpu.tickLock();
        try {
            for (final SignalListener listener : listeners) {
                for (final ControlSignal signal : listener.signals) {
                    this.cpu.addDestination(signal, listener.dest);
                }
            }
        }
        finally {
            this.cpu.tickUnlock();
        }
    }
    
    public void removeDestination(final SignalListener[] listeners) {
        this.cpu.tickLock();
        try {
            for (final SignalListener listener : listeners) {
                for (final ControlSignal signal : listener.signals) {
                    this.cpu.removeDestination(signal, listener.dest);
                }
            }
        }
        finally {
            this.cpu.tickUnlock();
        }
    }
}
