// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Graphics;
import javax.swing.JLabel;
import ru.ifmo.cs.bcomp.RunningCycle;
import java.util.EnumMap;
import ru.ifmo.cs.bcomp.CPU;

public class RunningCycleView extends BCompComponent
{
    private CPU cpu;
    private static final String[] cycleslabels;
    private EnumMap<RunningCycle, Integer> cycles;
    private final JLabel[] labels;
    private RunningCycle lastcycle;
    
    public RunningCycleView(final CPU cpu, final int x, final int y) {
        super("Control Unit", RunningCycleView.cycleslabels.length);
        this.cycles = new EnumMap<RunningCycle, Integer>(RunningCycle.class);
        this.labels = new JLabel[RunningCycleView.cycleslabels.length];
        this.lastcycle = RunningCycle.STOP;
        this.cpu = cpu;
        this.setBounds(x, y, 7 * DisplayStyles.MEM_WIDTH / 4);
        for (int i = 0; i < RunningCycleView.cycleslabels.length; ++i) {
            (this.labels[i] = this.addValueLabel(RunningCycleView.cycleslabels[i])).setBounds(1, this.getValueY(i), this.width - 2, 25);
        }
        this.cycles.put(RunningCycle.INFETCH, 0);
        this.cycles.put(RunningCycle.ADFETCH, 1);
        this.cycles.put(RunningCycle.OPFETCH, 2);
        this.cycles.put(RunningCycle.EXEC, 3);
        this.cycles.put(RunningCycle.INT, 4);
        this.cycles.put(RunningCycle.START, 5);
        this.cycles.put(RunningCycle.READ, 5);
        this.cycles.put(RunningCycle.WRITE, 5);
        this.cycles.put(RunningCycle.SETIP, 5);
    }
    
    public void update() {
        final RunningCycle newcycle = this.cpu.getRunningCycle();
        if (newcycle != this.lastcycle) {
            if (this.lastcycle != RunningCycle.STOP) {
                this.labels[this.cycles.get(this.lastcycle)].setForeground(DisplayStyles.COLOR_TEXT);
            }
            if (newcycle != RunningCycle.STOP) {
                this.labels[this.cycles.get(newcycle)].setForeground(DisplayStyles.COLOR_ACTIVE);
            }
            this.lastcycle = newcycle;
        }
    }
    
    public void updateProg(final boolean prog) {
        this.labels[this.labels.length - 1].setForeground(prog ? DisplayStyles.COLOR_ACTIVE : DisplayStyles.COLOR_TEXT);
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawLine(1, 26, this.width - 2, 26);
    }
    
    @Override
    protected JLabel addValueLabel(final String value) {
        return this.addLabel(value, DisplayStyles.FONT_COURIER_BOLD_18, DisplayStyles.COLOR_VALUE);
    }
    
    static {
        cycleslabels = new String[] { "Instruction fetch", "Address fetch", "Operand fetch", "Execution", "Interrupt", "Operator panel", "Program" };
    }
}
