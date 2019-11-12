// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Dimension;
import ru.ifmo.cs.components.Register;
import javax.swing.JComponent;
import java.awt.event.KeyListener;
import ru.ifmo.cs.bcomp.State;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JFrame;
import ru.ifmo.cs.components.DataDestination;
import ru.ifmo.cs.bcomp.ControlSignal;
import ru.ifmo.cs.bcomp.SignalListener;
import ru.ifmo.cs.bcomp.Reg;
import java.util.EnumMap;
import ru.ifmo.cs.bcomp.CPU;
import ru.ifmo.cs.bcomp.BasicComp;
import java.awt.Font;
import java.awt.Color;

public class Nightmare
{
    private static final Color LED_OFF;
    private static final Color LED_ON;
    private static final int BIT_RADIUS = 16;
    private static final Font LABEL_FONT;
    private static final Font HINTS_FONT;
    private final long[] delayPeriods;
    private volatile int currentDelay;
    private final BasicComp bcomp;
    private final CPU cpu;
    private EnumMap<Reg, RegisterView> regs;
    private final SignalListener[] listeners;
    
    public Nightmare() throws Exception {
        this.delayPeriods = new long[] { 0L, 1L, 5L, 10L, 25L, 50L, 100L, 1000L };
        this.currentDelay = 3;
        this.regs = new EnumMap<Reg, RegisterView>(Reg.class);
        this.bcomp = new BasicComp();
        this.cpu = this.bcomp.getCPU();
        for (final Reg reg : Reg.values()) {
            if (reg != Reg.MP && reg != Reg.MR) {
                this.regs.put(reg, new RegisterView(reg, this.cpu.getRegister(reg)));
            }
        }
        this.listeners = new SignalListener[] { new SignalListener(this.regs.get(Reg.DR), new ControlSignal[] { ControlSignal.WRDR, ControlSignal.LOAD }), new SignalListener(this.regs.get(Reg.CR), new ControlSignal[] { ControlSignal.WRCR }), new SignalListener(this.regs.get(Reg.IP), new ControlSignal[] { ControlSignal.WRIP }), new SignalListener(this.regs.get(Reg.SP), new ControlSignal[] { ControlSignal.WRSP }), new SignalListener(this.regs.get(Reg.AC), new ControlSignal[] { ControlSignal.WRAC }), new SignalListener(this.regs.get(Reg.BR), new ControlSignal[] { ControlSignal.WRBR }), new SignalListener(this.regs.get(Reg.PS), new ControlSignal[] { ControlSignal.WRPS, ControlSignal.SETC, ControlSignal.SETV, ControlSignal.STNZ, ControlSignal.DINT, ControlSignal.EINT, ControlSignal.HALT, ControlSignal.SET_PROGRAM }), new SignalListener(this.regs.get(Reg.AR), new ControlSignal[] { ControlSignal.WRAR }) };
        this.bcomp.addDestination(this.listeners);
        this.cpu.setTickFinishListener(new Runnable() {
            @Override
            public void run() {
                if (Nightmare.this.delayPeriods[Nightmare.this.currentDelay] != 0L) {
                    try {
                        Thread.sleep(Nightmare.this.delayPeriods[Nightmare.this.currentDelay]);
                    }
                    catch (InterruptedException ex) {}
                }
            }
        });
        final JFrame frame = new JFrame("\u0411\u042d\u0412\u041c");
        frame.setDefaultCloseOperation(3);
        final JPanel panel = new JPanel(new GridLayout(10, 1));
        panel.add(this.regs.get(Reg.DR));
        panel.add(this.regs.get(Reg.CR));
        panel.add(this.regs.get(Reg.IP));
        panel.add(this.regs.get(Reg.SP));
        panel.add(this.regs.get(Reg.AR));
        panel.add(this.regs.get(Reg.AC));
        panel.add(this.regs.get(Reg.BR));
        panel.add(this.regs.get(Reg.PS));
        panel.add(this.regs.get(Reg.IR));
        final JLabel hints = new JLabel("F4 \u0412\u0432\u043e\u0434 \u0430\u0434\u0440\u0435\u0441\u0430 F5 \u0417\u0430\u043f\u0438\u0441\u044c F6 \u0427\u0442\u0435\u043d\u0438\u0435 F7 \u041f\u0443\u0441\u043a F8 \u041f\u0440\u043e\u0434\u043e\u043b\u0436\u0435\u043d\u0438\u0435 F9 \u0420\u0430\u0431\u043e\u0442\u0430/\u041e\u0441\u0442\u0430\u043d\u043e\u0432 Shift-F9 \u0422\u0430\u043a\u0442");
        hints.setFont(Nightmare.HINTS_FONT);
        panel.add(hints);
        frame.add(panel);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.isControlDown()) {
                    switch (e.getKeyCode()) {
                        case 81: {
                            System.exit(0);
                            break;
                        }
                    }
                    return;
                }
                if (e.isShiftDown()) {
                    switch (e.getKeyCode()) {
                        case 120: {
                            Nightmare.this.cpu.invertClockState();
                            break;
                        }
                    }
                    return;
                }
                switch (e.getKeyCode()) {
                    case 48: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(0);
                        break;
                    }
                    case 49: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(1);
                        break;
                    }
                    case 50: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(2);
                        break;
                    }
                    case 51: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(3);
                        break;
                    }
                    case 52: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(4);
                        break;
                    }
                    case 53: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(5);
                        break;
                    }
                    case 54: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(6);
                        break;
                    }
                    case 55: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(7);
                        break;
                    }
                    case 56: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(8);
                        break;
                    }
                    case 57: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(9);
                        break;
                    }
                    case 65: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(10);
                        break;
                    }
                    case 66: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(11);
                        break;
                    }
                    case 67: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(12);
                        break;
                    }
                    case 68: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(13);
                        break;
                    }
                    case 69: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(14);
                        break;
                    }
                    case 70: {
                        Nightmare.this.regs.get(Reg.IR).invertBit(15);
                        break;
                    }
                    case 115: {
                        Nightmare.this.cpu.startSetAddr();
                        break;
                    }
                    case 116: {
                        Nightmare.this.cpu.startWrite();
                        break;
                    }
                    case 117: {
                        Nightmare.this.cpu.startRead();
                        break;
                    }
                    case 118: {
                        Nightmare.this.cpu.startStart();
                        break;
                    }
                    case 119: {
                        Nightmare.this.cpu.startContinue();
                        break;
                    }
                    case 120: {
                        Nightmare.this.cpu.invertRunState();
                        Nightmare.this.regs.get(Reg.PS).bits[State.RUN.ordinal()].repaint();
                        break;
                    }
                    case 122: {
                        Nightmare.this.currentDelay = ((Nightmare.this.currentDelay > 0) ? Nightmare.this.currentDelay : Nightmare.this.delayPeriods.length) - 1;
                        break;
                    }
                    case 123: {
                        Nightmare.this.currentDelay = ((Nightmare.this.currentDelay < Nightmare.this.delayPeriods.length - 1) ? (Nightmare.this.currentDelay + 1) : 0);
                        break;
                    }
                }
            }
        });
        frame.pack();
        frame.setVisible(true);
        frame.requestFocus();
    }
    
    static {
        LED_OFF = new Color(128, 128, 128);
        LED_ON = new Color(0, 160, 0);
        LABEL_FONT = new Font("Courier New", 1, 24);
        HINTS_FONT = new Font("Courier New", 1, 14);
    }
    
    private class BitView extends JComponent
    {
        private final Register reg;
        private final int startbit;
        
        protected BitView(final Register reg, final int startbit) {
            this.reg = reg;
            this.startbit = startbit;
            final Dimension DIMS = new Dimension(36, 36);
            this.setMinimumSize(DIMS);
            this.setMaximumSize(DIMS);
            this.setPreferredSize(DIMS);
            this.setSize(DIMS);
        }
        
        public void paintComponent(final Graphics g) {
            g.setColor((this.reg.getValue(this.startbit) == 1L) ? Nightmare.LED_ON : Nightmare.LED_OFF);
            g.fillOval(2, 2, 32, 32);
        }
    }
    
    private class RegisterView extends JPanel implements DataDestination
    {
        private final Register reg;
        private final BitView[] bits;
        
        private RegisterView(final Reg r, final Register reg) {
            super(new FlowLayout(2, 0, 0));
            this.reg = reg;
            final JLabel label = new JLabel(r.toString());
            label.setFont(Nightmare.LABEL_FONT);
            this.add(label);
            this.bits = new BitView[(int)reg.width];
            for (long i = reg.width - 1L; i >= 0L; --i) {
                this.add(this.bits[(int)i] = new BitView(reg, (int)i));
            }
        }
        
        @Override
        public void setValue(final long value) {
            int i = 0;
            while (i < this.reg.width) {
                this.bits[i++].repaint();
            }
        }
        
        private void invertBit(final int startbit) {
            this.reg.invertBit(startbit);
            this.bits[startbit].repaint();
        }
    }
}
