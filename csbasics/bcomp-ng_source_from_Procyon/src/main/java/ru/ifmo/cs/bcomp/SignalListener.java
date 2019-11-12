// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp;

import ru.ifmo.cs.components.DataDestination;

public class SignalListener
{
    public final DataDestination dest;
    public final ControlSignal[] signals;
    
    public SignalListener(final DataDestination dest, final ControlSignal... signals) {
        this.dest = dest;
        this.signals = signals;
    }
}
