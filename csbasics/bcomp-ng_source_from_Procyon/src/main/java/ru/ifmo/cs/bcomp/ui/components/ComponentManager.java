// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import java.awt.event.KeyListener;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import ru.ifmo.cs.bcomp.State;
import ru.ifmo.cs.components.DataDestination;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import ru.ifmo.cs.bcomp.ControlSignal;
import java.util.ArrayList;
import ru.ifmo.cs.bcomp.SignalListener;
import ru.ifmo.cs.bcomp.Reg;
import java.util.EnumMap;
import ru.ifmo.cs.bcomp.IOCtrl;
import ru.ifmo.cs.bcomp.CPU;
import ru.ifmo.cs.bcomp.BasicComp;
import ru.ifmo.cs.bcomp.ui.GUI;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.Color;
import java.util.ResourceBundle;
import javax.swing.JRadioButton;

public class ComponentManager
{
    private JRadioButton rbRanStop;
    private JRadioButton rbTact;
    private ResourceBundle res;
    private Color[] buttonColors;
    private ButtonProperties[] buttonProperties;
    private final KeyAdapter keyListener;
    private static final int BUTTON_RUN = 5;
    private static final int BUTTON_CLOCK = 6;
    private JButton[] buttons;
    private ButtonsPanel buttonsPanel;
    private final GUI gui;
    private final BasicComp bcomp;
    private final CPU cpu;
    private final IOCtrl[] ioctrls;
    private final MemoryView mem;
    private FlagView[] flagViews;
    private EnumMap<Reg, RegisterView> regs;
    private InputRegisterView input;
    private ActiveBitView activeBit;
    private volatile BCompPanel activePanel;
    private final long[] delayPeriods;
    private volatile int currentDelay;
    private volatile int savedDelay;
    private final Object lockActivePanel;
    private volatile boolean cuswitch;
    private final SignalListener[] listeners;
    private ArrayList<ControlSignal> openBuses;
    private static final ControlSignal[] busSignals;
    
    public ComponentManager(final GUI gui) {
        this.res = ResourceBundle.getBundle("ru.ifmo.cs.bcomp.ui.components.loc", Locale.getDefault());
        this.buttonColors = new Color[] { DisplayStyles.COLOR_TEXT, DisplayStyles.COLOR_ACTIVE };
        this.buttonProperties = new ButtonProperties[] { new ButtonProperties(new String[] { this.res.getString("setip") }, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    ComponentManager.this.cmdEnterAddr();
                }
            }), new ButtonProperties(new String[] { this.res.getString("read") }, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    ComponentManager.this.cmdRead();
                }
            }), new ButtonProperties(new String[] { this.res.getString("write") }, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    ComponentManager.this.cmdWrite();
                }
            }), new ButtonProperties(new String[] { this.res.getString("start") }, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    ComponentManager.this.cmdStart();
                }
            }), new ButtonProperties(new String[] { this.res.getString("continue") }, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    ComponentManager.this.cmdContinue();
                }
            }), new ButtonProperties(new String[] { this.res.getString("stop"), this.res.getString("run") }, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    ComponentManager.this.cmdInvertRunState();
                }
            }), new ButtonProperties(new String[] { this.res.getString("tick") }, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    ComponentManager.this.cmdInvertClockState();
                }
            }) };
        this.keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 115: {
                        ComponentManager.this.cmdEnterAddr();
                        break;
                    }
                    case 116: {
                        ComponentManager.this.cmdWrite();
                        break;
                    }
                    case 117: {
                        ComponentManager.this.cmdRead();
                        break;
                    }
                    case 118: {
                        ComponentManager.this.cmdStart();
                        break;
                    }
                    case 119: {
                        ComponentManager.this.cmdContinue();
                        break;
                    }
                    case 120: {
                        if (e.isShiftDown()) {
                            ComponentManager.this.cmdInvertClockState();
                            break;
                        }
                        ComponentManager.this.cmdInvertRunState();
                        break;
                    }
                    case 121: {
                        System.exit(0);
                        break;
                    }
                    case 122: {
                        ComponentManager.this.cmdPrevDelay();
                        break;
                    }
                    case 123: {
                        ComponentManager.this.cmdNextDelay();
                        break;
                    }
                    case 81: {
                        if (e.isControlDown()) {
                            System.exit(0);
                            break;
                        }
                        break;
                    }
                }
            }
        };
        this.buttonsPanel = new ButtonsPanel();
        this.flagViews = new FlagView[4];
        this.regs = new EnumMap<Reg, RegisterView>(Reg.class);
        this.activeBit = new ActiveBitView(DisplayStyles.ACTIVE_BIT_X, 466);
        this.delayPeriods = new long[] { 0L, 1L, 5L, 10L, 25L, 50L, 100L, 1000L };
        this.currentDelay = 3;
        this.lockActivePanel = new Object();
        this.cuswitch = false;
        this.openBuses = new ArrayList<ControlSignal>();
        this.gui = gui;
        this.bcomp = gui.getBasicComp();
        this.cpu = gui.getCPU();
        this.input = new InputRegisterView(this, this.cpu.getRegister(Reg.IR));
        this.ioctrls = gui.getIOCtrls();
        this.cpu.setTickStartListener(new Runnable() {
            @Override
            public void run() {
                synchronized (ComponentManager.this.lockActivePanel) {
                    if (ComponentManager.this.activePanel != null) {
                        ComponentManager.this.activePanel.stepStart();
                    }
                }
                ComponentManager.this.openBuses.clear();
            }
        });
        this.cpu.setTickFinishListener(new Runnable() {
            @Override
            public void run() {
                synchronized (ComponentManager.this.lockActivePanel) {
                    if (ComponentManager.this.activePanel != null) {
                        ComponentManager.this.activePanel.stepFinish();
                    }
                }
                if (ComponentManager.this.delayPeriods[ComponentManager.this.currentDelay] != 0L) {
                    try {
                        Thread.sleep(ComponentManager.this.delayPeriods[ComponentManager.this.currentDelay]);
                    }
                    catch (InterruptedException ex) {}
                }
            }
        });
        for (final ControlSignal cs : ComponentManager.busSignals) {
            this.cpu.addDestination(cs, new SignalHandler(cs));
        }
        for (int i = 0; i < 4; ++i) {
            (this.flagViews[i] = new FlagView(0, 0, 25, 25)).setPreferredSize(this.flagViews[i].getSize());
        }
        this.flagViews[0].setTitle("N");
        this.flagViews[1].setTitle("Z");
        this.flagViews[2].setTitle("V");
        this.flagViews[3].setTitle("C");
        for (final Reg reg : Reg.values()) {
            this.regs.put(reg, new RegisterView(this.cpu.getRegister(reg)));
        }
        this.listeners = new SignalListener[] { new SignalListener(this.regs.get(Reg.AR), new ControlSignal[] { ControlSignal.WRAR }), new SignalListener(this.regs.get(Reg.DR), new ControlSignal[] { ControlSignal.WRDR, ControlSignal.LOAD }), new SignalListener(this.regs.get(Reg.CR), new ControlSignal[] { ControlSignal.WRCR }), new SignalListener(this.regs.get(Reg.IP), new ControlSignal[] { ControlSignal.WRIP }), new SignalListener(this.regs.get(Reg.AC), new ControlSignal[] { ControlSignal.WRAC }), new SignalListener(this.regs.get(Reg.PS), new ControlSignal[] { ControlSignal.RDPS, ControlSignal.WRPS, ControlSignal.SETC, ControlSignal.SETV, ControlSignal.STNZ, ControlSignal.DINT, ControlSignal.EINT, ControlSignal.HALT, ControlSignal.SET_PROGRAM }), new SignalListener(this.regs.get(Reg.SP), new ControlSignal[] { ControlSignal.WRSP }), new SignalListener(this.regs.get(Reg.BR), new ControlSignal[] { ControlSignal.WRBR }) };
        this.mem = new MemoryView(this.cpu.getMemory(), 1, 1);
        this.cpu.addDestination(ControlSignal.LOAD, new DataDestination() {
            @Override
            public void setValue(final long value) {
                if (ComponentManager.this.activePanel != null) {
                    ComponentManager.this.mem.eventRead();
                }
                else {
                    ComponentManager.this.mem.updateLastAddr();
                }
            }
        });
        this.cpu.addDestination(ControlSignal.SETC, new DataDestination() {
            @Override
            public void setValue(final long value) {
                ComponentManager.this.flagViews[3].setActive(ComponentManager.this.cpu.getProgramState(State.C) == 1L);
            }
        });
        this.cpu.addDestination(ControlSignal.SETV, new DataDestination() {
            @Override
            public void setValue(final long value) {
                ComponentManager.this.flagViews[2].setActive(ComponentManager.this.cpu.getProgramState(State.V) == 1L);
            }
        });
        this.cpu.addDestination(ControlSignal.STNZ, new DataDestination() {
            @Override
            public void setValue(final long value) {
                ComponentManager.this.flagViews[1].setActive(ComponentManager.this.cpu.getProgramState(State.Z) != 0L);
                ComponentManager.this.flagViews[0].setActive(ComponentManager.this.cpu.getProgramState(State.N) != 0L);
            }
        });
        this.cpu.addDestination(ControlSignal.STOR, new DataDestination() {
            @Override
            public void setValue(final long value) {
                if (ComponentManager.this.activePanel != null) {
                    ComponentManager.this.mem.eventWrite();
                }
                else {
                    ComponentManager.this.mem.updateLastAddr();
                }
            }
        });
    }
    
    public void panelActivate(final BCompPanel component) {
        synchronized (this.lockActivePanel) {
            this.activePanel = component;
            this.bcomp.addDestination(this.listeners);
            this.bcomp.addDestination(this.activePanel.getSignalListeners());
        }
        this.buttonsPanel.setPreferredSize(this.buttonsPanel.getSize());
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = 10;
        constraints.fill = 0;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.insets = new Insets(0, 0, 0, 30);
        this.input.setProperties(0, 0, false, true);
        this.input.setTitle("IR");
        this.input.setPreferredSize(this.input.getSize());
        this.input.setMinimumSize(this.input.getSize());
        this.buttonsPanel.add(this.input, constraints);
        constraints.anchor = 10;
        constraints.insets = new Insets(0, DisplayStyles.REG_16_WIDTH + 26, 0, 20);
        this.activeBit.setPreferredSize(this.activeBit.getSize());
        this.activeBit.setMinimumSize(this.activeBit.getSize());
        this.buttonsPanel.add(this.activeBit, constraints);
        this.mem.setPreferredSize(this.mem.getSize());
        component.add(this.buttonsPanel, "South");
        this.mem.updateMemory();
        this.cuswitch = false;
        this.switchFocus();
    }
    
    public void panelDeactivate() {
    }
    
    public void keyPressed(final KeyEvent e) {
        this.keyListener.keyPressed(e);
    }
    
    public void switchFocus() {
        this.input.setActive();
    }
    
    public RegisterView getRegisterView(final Reg reg) {
        return this.regs.get(reg);
    }
    
    public FlagView getFlagView(final int i) {
        return this.flagViews[i];
    }
    
    public void cmdContinue() {
        this.cpu.startContinue();
    }
    
    public void cmdEnterAddr() {
        this.cpu.startSetAddr();
    }
    
    public void cmdWrite() {
        this.cpu.startWrite();
    }
    
    public void cmdRead() {
        this.cpu.startRead();
    }
    
    public void cmdStart() {
        this.cpu.startStart();
    }
    
    public void cmdInvertRunState() {
        this.cpu.invertRunState();
        final long state = this.cpu.getProgramState(State.RUN);
        this.rbRanStop.setSelected(state == 1L);
        this.rbRanStop.setText(this.buttonProperties[5].texts[(int)state]);
        this.regs.get(Reg.PS).setValue();
    }
    
    public void cmdInvertClockState() {
        final boolean state = this.cpu.invertClockState();
        this.rbTact.setSelected(!state);
    }
    
    public void cmdNextDelay() {
        this.currentDelay = ((this.currentDelay < this.delayPeriods.length - 1) ? (this.currentDelay + 1) : 0);
    }
    
    public void cmdPrevDelay() {
        this.currentDelay = ((this.currentDelay > 0) ? this.currentDelay : this.delayPeriods.length) - 1;
    }
    
    public void saveDelay() {
        this.savedDelay = this.currentDelay;
        this.currentDelay = 0;
    }
    
    public void restoreDelay() {
        this.currentDelay = this.savedDelay;
    }
    
    public ActiveBitView getActiveBit() {
        return this.activeBit;
    }
    
    public KeyListener getKeyListener() {
        return this.keyListener;
    }
    
    public ArrayList<ControlSignal> getActiveSignals() {
        return this.openBuses;
    }
    
    public void clearActiveSignals() {
        this.openBuses.clear();
    }
    
    public MemoryView getMem() {
        return this.mem;
    }
    
    public RegisterView getInput() {
        return this.input;
    }
    
    public ResourceBundle getRes() {
        return this.res;
    }
    
    static {
        busSignals = new ControlSignal[] { ControlSignal.RDDR, ControlSignal.RDCR, ControlSignal.RDIP, ControlSignal.RDAC, ControlSignal.RDPS, ControlSignal.RDIR, ControlSignal.RDBR, ControlSignal.RDSP, ControlSignal.WRDR, ControlSignal.WRCR, ControlSignal.WRIP, ControlSignal.WRAC, ControlSignal.WRPS, ControlSignal.WRAR, ControlSignal.WRBR, ControlSignal.WRSP, ControlSignal.LOAD, ControlSignal.STOR, ControlSignal.IO, ControlSignal.TYPE };
    }
    
    private class SignalHandler implements DataDestination
    {
        private final ControlSignal signal;
        
        public SignalHandler(final ControlSignal signal) {
            this.signal = signal;
        }
        
        @Override
        public void setValue(final long value) {
            ComponentManager.this.openBuses.add(this.signal);
        }
    }
    
    private class ButtonProperties
    {
        final String[] texts;
        public final ActionListener listener;
        
        public ButtonProperties(final String[] texts, final ActionListener listener) {
            this.texts = texts;
            this.listener = listener;
        }
    }
    
    private class ButtonsPanel extends JComponent
    {
        public ButtonsPanel() {
            this.setBounds(0, 510, DisplayStyles.PANE_WIDTH, 50);
            this.setLayout(new GridBagLayout());
            final GridBagConstraints constraints = new GridBagConstraints() {
                {
                    this.anchor = 17;
                    this.fill = 2;
                    this.gridx = 0;
                    this.gridy = 0;
                    this.weightx = 1.0;
                    this.insets = new Insets(1, 1, 1, 1);
                }
            };
            ComponentManager.this.buttons = new JButton[ComponentManager.this.buttonProperties.length];
            for (int i = 0; i < ComponentManager.this.buttons.length - 2; ++i) {
                (ComponentManager.this.buttons[i] = new JButton(ComponentManager.this.buttonProperties[i].texts[0])).setForeground(ComponentManager.this.buttonColors[0]);
                ComponentManager.this.buttons[i].setFont(DisplayStyles.FONT_COURIER_PLAIN_12);
                ComponentManager.this.buttons[i].setFocusable(false);
                ComponentManager.this.buttons[i].addActionListener(ComponentManager.this.buttonProperties[i].listener);
                ComponentManager.this.buttons[i].setCursor(new Cursor(12));
                constraints.gridwidth = ((i == 0) ? 2 : 1);
                if (i > 0) {
                    constraints.gridy = 1;
                }
                if (i == 2) {
                    constraints.gridx = 0;
                }
                this.add(ComponentManager.this.buttons[i], constraints);
                if (i == 2) {
                    final GridBagConstraints gridBagConstraints = constraints;
                    gridBagConstraints.gridx += 3;
                }
                else {
                    final GridBagConstraints gridBagConstraints2 = constraints;
                    ++gridBagConstraints2.gridx;
                }
            }
            constraints.gridy = 0;
            constraints.gridx = 3;
            constraints.fill = 0;
            constraints.anchor = 10;
            ComponentManager.this.rbRanStop = new JRadioButton(ComponentManager.this.buttonProperties[5].texts[0]);
            ComponentManager.this.rbRanStop.setFont(DisplayStyles.FONT_COURIER_PLAIN_12);
            ComponentManager.this.rbRanStop.setBackground(new Color(200, 221, 242));
            ComponentManager.this.rbRanStop.setBorderPainted(false);
            ComponentManager.this.rbRanStop.addActionListener(ComponentManager.this.buttonProperties[5].listener);
            ComponentManager.this.rbRanStop.setCursor(new Cursor(12));
            ComponentManager.this.rbRanStop.setFocusPainted(false);
            ComponentManager.this.rbRanStop.setFocusable(false);
            this.add(ComponentManager.this.rbRanStop, constraints);
            final GridBagConstraints gridBagConstraints3 = constraints;
            ++gridBagConstraints3.gridx;
            ComponentManager.this.rbTact = new JRadioButton(ComponentManager.this.buttonProperties[6].texts[0]);
            ComponentManager.this.rbTact.setFont(DisplayStyles.FONT_COURIER_PLAIN_12);
            ComponentManager.this.rbTact.setBackground(new Color(200, 221, 242));
            ComponentManager.this.rbTact.setBorderPainted(false);
            ComponentManager.this.rbTact.addActionListener(ComponentManager.this.buttonProperties[6].listener);
            ComponentManager.this.rbTact.setCursor(new Cursor(12));
            ComponentManager.this.rbTact.setFocusPainted(false);
            ComponentManager.this.rbTact.setFocusable(false);
            this.add(ComponentManager.this.rbTact, constraints);
        }
    }
}
