// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.awt.GridBagLayout;
import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Color;
import java.util.Iterator;
import ru.ifmo.cs.bcomp.ControlSignal;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import ru.ifmo.cs.bcomp.SignalListener;
import java.util.Map;

public abstract class BCompPanel extends ActivateblePanel
{
    protected final ComponentManager cmanager;
    private final RegisterProperties[] regProps;
    protected Map<BusNames, BusView> busesMap;
    private SignalListener[] listeners;
    protected RegPanel regPanel;
    FlagView[] flags;
    
    public BCompPanel(final ComponentManager cmanager, final RegisterProperties[] regProps, final Map baseMap) {
        this.setLayout(new BorderLayout());
        this.cmanager = cmanager;
        this.regProps = regProps;
        this.busesMap = (Map<BusNames, BusView>)baseMap;
        this.regPanel = new RegPanel();
    }
    
    protected void setSignalListeners(final SignalListener[] listeners) {
        this.listeners = listeners;
    }
    
    protected SignalListener[] getSignalListeners() {
        return this.listeners;
    }
    
    private void drawBuses(final Graphics g) {
        final ArrayList<BusView> openbuses = new ArrayList<BusView>();
        final ArrayList<ControlSignal> signals = this.cmanager.getActiveSignals();
        for (final BusView bus : this.busesMap.values()) {
            for (final ControlSignal signal : bus.getSignals()) {
                if (signals.contains(signal)) {
                    openbuses.add(bus);
                }
            }
            bus.draw(g, DisplayStyles.COLOR_BUS);
        }
        for (final BusView bus : openbuses) {
            bus.draw(g, DisplayStyles.COLOR_ACTIVE);
        }
    }
    
    private void drawOpenBuses(final Color color) {
        final Graphics g = this.getGraphics();
        final ArrayList<ControlSignal> signals = this.cmanager.getActiveSignals();
        for (final BusView bus : this.busesMap.values()) {
            for (final ControlSignal signal : bus.getSignals()) {
                if (signals.contains(signal)) {
                    bus.draw(g, color);
                }
            }
        }
    }
    
    public void stepStart() {
        this.drawOpenBuses(DisplayStyles.COLOR_BUS);
    }
    
    public void stepFinish() {
        this.drawOpenBuses(DisplayStyles.COLOR_ACTIVE);
    }
    
    @Override
    public void panelActivate() {
        this.cmanager.panelActivate(this);
        for (final RegisterProperties prop : this.regProps) {
            final RegisterView reg = this.cmanager.getRegisterView(prop.reg);
            reg.setProperties(prop.x, prop.y, prop.hex, prop.isLeft);
            reg.setPreferredSize(reg.getSize());
            reg.setTitle(prop.reg.toString());
            this.regPanel.add(reg, prop.constraints);
        }
    }
    
    @Override
    public void panelDeactivate() {
        this.cmanager.panelDeactivate();
    }
    
    public void paintComponent(final Graphics g) {
        this.drawBuses(g);
    }
    
    protected class RegPanel extends JComponent
    {
        RegPanel() {
            this.setLayout(new GridBagLayout());
        }
    }
}
