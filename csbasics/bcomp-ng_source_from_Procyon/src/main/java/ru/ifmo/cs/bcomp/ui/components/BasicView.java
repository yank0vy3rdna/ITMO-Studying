// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.util.Iterator;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import ru.ifmo.cs.bcomp.State;
import ru.ifmo.cs.components.DataDestination;
import ru.ifmo.cs.bcomp.SignalListener;
import java.awt.Component;
import java.util.Map;
import ru.ifmo.cs.bcomp.ControlSignal;
import java.util.EnumMap;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import ru.ifmo.cs.bcomp.Reg;
import ru.ifmo.cs.bcomp.ui.GUI;
import ru.ifmo.cs.bcomp.CPU;

public class BasicView extends BCompPanel
{
    private final CPU cpu;
    private final RunningCycleView cycleview;
    private final ALUView alu;
    private final CommutView commutView;
    
    public BasicView(final GUI gui) {
        super(gui.getComponentManager(), new RegisterProperties[] { new RegisterProperties(Reg.AR, DisplayStyles.REG_ACCUM_X_BV, 148, false, false, new GridBagConstraints() {
                {
                    this.fill = 0;
                    this.gridy = 4;
                    this.gridx = 5;
                    this.weightx = 0.0;
                    this.weighty = 0.0;
                    this.gridwidth = 1;
                    this.gridheight = 1;
                    this.anchor = 18;
                    this.insets = new Insets(156, 20, 0, 0);
                }
            }), new RegisterProperties(Reg.DR, DisplayStyles.REG_ACCUM_X_BV, 192, false, false, new GridBagConstraints() {
                {
                    this.fill = 0;
                    this.gridy = 0;
                    this.gridx = 4;
                    this.weightx = 0.5;
                    this.weighty = 0.5;
                    this.insets = new Insets(60, 40, 0, 0);
                }
            }), new RegisterProperties(Reg.IP, DisplayStyles.REG_IP_X_BV, 236, false, false, new GridBagConstraints() {
                {
                    this.fill = 0;
                    this.gridy = 2;
                    this.gridx = 4;
                    this.weightx = 0.5;
                    this.weighty = 0.5;
                    this.insets = new Insets(0, 0, 0, 33);
                }
            }), new RegisterProperties(Reg.CR, DisplayStyles.REG_INSTR_X_BV, 148, false, false, new GridBagConstraints() {
                {
                    this.fill = 0;
                    this.gridy = 1;
                    this.gridx = 4;
                    this.weightx = 0.5;
                    this.weighty = 0.5;
                    this.insets = new Insets(0, 40, 0, 0);
                }
            }), new RegisterProperties(Reg.AC, DisplayStyles.REG_ACCUM_X_BV, 422, false, true, new GridBagConstraints() {
                {
                    this.fill = 0;
                    this.gridy = 0;
                    this.gridx = 3;
                    this.weighty = 0.5;
                    this.weightx = 0.5;
                    this.insets = new Insets(60, 23, 0, 60);
                }
            }), new RegisterProperties(Reg.SP, DisplayStyles.REG_ACCUM_X_BV, 0, false, false, new GridBagConstraints() {
                {
                    this.fill = 0;
                    this.gridy = 3;
                    this.gridx = 4;
                    this.weighty = 0.5;
                    this.insets = new Insets(0, 0, 80, 33);
                }
            }), new RegisterProperties(Reg.BR, 0, 0, false, true, new GridBagConstraints() {
                {
                    this.gridy = 1;
                    this.gridx = 3;
                    this.insets = new Insets(0, 3, 0, 40);
                }
            }), new RegisterProperties(Reg.PS, 0, 0, false, true, new GridBagConstraints() {
                {
                    this.gridy = 2;
                    this.gridx = 3;
                    this.insets = new Insets(0, 3, 0, 40);
                }
            }), new RegisterProperties(Reg.IR, 0, 0, false, true, new GridBagConstraints() {
                {
                    this.gridy = 3;
                    this.gridx = 3;
                    this.insets = new Insets(0, 3, 80, 40);
                }
            }) }, new EnumMap<BusNames, BusView>(BusNames.class) {
            {
                this.put(BusNames.BR_ALU, new BusView(new ControlSignal[] { ControlSignal.RDBR }));
                this.put(BusNames.COMM_BR, new BusView(new ControlSignal[] { ControlSignal.WRBR }));
                this.put(BusNames.PS_ALU, new BusView(new ControlSignal[] { ControlSignal.RDPS }));
                this.put(BusNames.COMM_PS, new BusView(new ControlSignal[] { ControlSignal.WRPS }));
                this.put(BusNames.COMM_ALL, new BusView(new ControlSignal[] { ControlSignal.WRBR, ControlSignal.WRAC, ControlSignal.WRIP, ControlSignal.WRCR, ControlSignal.WRDR, ControlSignal.WRAR, ControlSignal.WRPS, ControlSignal.WRSP }));
                this.put(BusNames.ALU_COMM, new BusView(new ControlSignal[] { ControlSignal.WRBR, ControlSignal.WRAC, ControlSignal.WRIP, ControlSignal.WRCR, ControlSignal.WRDR, ControlSignal.WRAR, ControlSignal.WRPS, ControlSignal.WRSP, ControlSignal.TYPE }));
                this.put(BusNames.DR_ALU, new BusView(new ControlSignal[] { ControlSignal.RDDR }));
                this.put(BusNames.CR_ALU, new BusView(new ControlSignal[] { ControlSignal.RDCR }));
                this.put(BusNames.IP_ALU, new BusView(new ControlSignal[] { ControlSignal.RDIP }));
                this.put(BusNames.SP_ALU, new BusView(new ControlSignal[] { ControlSignal.RDSP }));
                this.put(BusNames.AC_ALU, new BusView(new ControlSignal[] { ControlSignal.RDAC }));
                this.put(BusNames.IR_ALU, new BusView(new ControlSignal[] { ControlSignal.RDIR }));
                this.put(BusNames.COMM_AR, new BusView(new ControlSignal[] { ControlSignal.WRAR }));
                this.put(BusNames.COMM_DR, new BusView(new ControlSignal[] { ControlSignal.WRDR }));
                this.put(BusNames.COMM_CR, new BusView(new ControlSignal[] { ControlSignal.WRCR }));
                this.put(BusNames.COMM_IP, new BusView(new ControlSignal[] { ControlSignal.WRIP }));
                this.put(BusNames.COMM_SP, new BusView(new ControlSignal[] { ControlSignal.WRSP }));
                this.put(BusNames.COMM_AC, new BusView(new ControlSignal[] { ControlSignal.WRAC }));
                this.put(BusNames.MEM_IO, new BusView(new ControlSignal[] { ControlSignal.LOAD, ControlSignal.STOR }));
                this.put(BusNames.MEM_R, new BusView(new ControlSignal[] { ControlSignal.LOAD }));
                this.put(BusNames.MEM_W, new BusView(new ControlSignal[] { ControlSignal.STOR }));
                this.put(BusNames.CU, new BusView(new ControlSignal[] { ControlSignal.TYPE }));
            }
        });
        this.add(this.regPanel, "Center");
        this.cpu = gui.getCPU();
        this.setSignalListeners(new SignalListener[] { new SignalListener(new DataDestination() {
                @Override
                public void setValue(final long value) {
                    BasicView.this.cycleview.updateProg(BasicView.this.cpu.getProgramState(State.PROG) == 1L);
                }
            }, new ControlSignal[] { ControlSignal.HALT, ControlSignal.SET_PROGRAM }) });
        final GridBagConstraints constraintsALU = new GridBagConstraints() {
            {
                this.gridx = 3;
                this.gridy = 4;
                this.gridwidth = 2;
                this.weightx = 0.5;
                this.weighty = 0.5;
                this.anchor = 11;
                this.insets = new Insets(0, 30, 100, 0);
            }
        };
        (this.alu = new ALUView(DisplayStyles.REG_C_X_BV, 301, 181, 80)).setPreferredSize(this.alu.getSize());
        this.regPanel.add(this.alu, constraintsALU);
        final GridBagConstraints constraintsComm = new GridBagConstraints() {
            {
                this.gridx = 3;
                this.gridy = 4;
                this.gridwidth = 2;
                this.weightx = 0.5;
                this.weighty = 0.5;
                this.anchor = 11;
                this.insets = new Insets(97, 30, 0, 0);
            }
        };
        (this.commutView = new CommutView(0, 0, 150, 30)).setPreferredSize(this.commutView.getSize());
        this.regPanel.add(this.commutView, constraintsComm);
        final GridBagConstraints constraintsCycle = new GridBagConstraints() {
            {
                this.gridx = 4;
                this.gridy = 3;
                this.gridheight = 2;
                this.anchor = 10;
                this.insets = new Insets(0, 90, 0, 0);
            }
        };
        (this.cycleview = new RunningCycleView(this.cpu, DisplayStyles.REG_INSTR_X_BV, 301)).setPreferredSize(this.cycleview.getSize());
        this.regPanel.add(this.cycleview, constraintsCycle);
        final GridBagConstraints constraintMem = new GridBagConstraints() {
            {
                this.gridx = 5;
                this.gridy = 0;
                this.gridheight = 5;
                this.weighty = 0.0;
                this.anchor = 17;
                this.insets = new Insets(0, 40, 60, 0);
            }
        };
        this.regPanel.add(this.cmanager.getMem(), constraintMem);
        final GridBagConstraints constraintsF = new GridBagConstraints() {
            {
                this.fill = 0;
                this.gridy = 4;
                this.gridx = 3;
                this.gridwidth = 2;
                this.weightx = 0.5;
                this.weighty = 0.0;
                this.anchor = 11;
                this.insets = new Insets(126, 0, 0, 20);
            }
        };
        this.regPanel.add(this.cmanager.getFlagView(1), constraintsF);
        final Insets insets = constraintsF.insets;
        insets.right += 60;
        this.regPanel.add(this.cmanager.getFlagView(0), constraintsF);
        constraintsF.insets.right = 0;
        constraintsF.insets.left = 140;
        this.regPanel.add(this.cmanager.getFlagView(3), constraintsF);
        constraintsF.insets = new Insets(126, 80, 0, 0);
        this.regPanel.add(this.cmanager.getFlagView(2), constraintsF);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                super.componentResized(e);
                BasicView.this.redrawArrows();
            }
        });
        this.redrawArrows();
    }
    
    @Override
    public void stepStart() {
        this.cycleview.update();
        super.stepStart();
    }
    
    @Override
    public String getPanelName() {
        return this.cmanager.getRes().getString("basename");
    }
    
    @Override
    public void stepFinish() {
        super.stepFinish();
    }
    
    @Override
    public void redrawArrows() {
        for (final BusNames key : this.busesMap.keySet()) {
            final BusView bus = this.busesMap.get(key);
            final RegisterView data = this.cmanager.getRegisterView(Reg.DR);
            final RegisterView addr = this.cmanager.getRegisterView(Reg.AR);
            final RegisterView instr = this.cmanager.getRegisterView(Reg.CR);
            final RegisterView accum = this.cmanager.getRegisterView(Reg.AC);
            final RegisterView irReg = this.cmanager.getRegisterView(Reg.IR);
            final RegisterView ipReg = this.cmanager.getRegisterView(Reg.IP);
            final RegisterView spReg = this.cmanager.getRegisterView(Reg.SP);
            final RegisterView buf = this.cmanager.getRegisterView(Reg.BR);
            final RegisterView rs = this.cmanager.getRegisterView(Reg.PS);
            final int regPanelX = this.regPanel.getX();
            switch (key) {
                case DR_ALU: {
                    bus.calcBounds(new int[][] { { regPanelX + data.getX() - 5, data.getY() + 14 }, { regPanelX + this.alu.getX() + 135, data.getY() + 14 }, { regPanelX + this.alu.getX() + 135, this.alu.getY() - 13 } });
                    continue;
                }
                case CR_ALU: {
                    bus.calcBounds(new int[][] { { regPanelX + instr.getX() - 5, instr.getY() + 14 }, { regPanelX + this.alu.getX() + 135, instr.getY() + 14 }, { regPanelX + this.alu.getX() + 135, this.alu.getY() - 13 } });
                    continue;
                }
                case IP_ALU: {
                    bus.calcBounds(new int[][] { { regPanelX + ipReg.getX() - 5, ipReg.getY() + 14 }, { regPanelX + this.alu.getX() + 135, ipReg.getY() + 14 }, { regPanelX + this.alu.getX() + 135, this.alu.getY() - 13 } });
                    continue;
                }
                case SP_ALU: {
                    bus.calcBounds(new int[][] { { regPanelX + spReg.getX() - 5, spReg.getY() + 14 }, { regPanelX + this.alu.getX() + 135, spReg.getY() + 14 }, { regPanelX + this.alu.getX() + 135, this.alu.getY() - 13 } });
                    continue;
                }
                case AC_ALU: {
                    bus.calcBounds(new int[][] { { regPanelX + accum.getX() + DisplayStyles.REG_16_WIDTH + 4, accum.getY() + 14 }, { regPanelX + this.alu.getX() + 45, accum.getY() + 14 }, { regPanelX + this.alu.getX() + 45, this.alu.getY() - 13 } });
                    continue;
                }
                case BR_ALU: {
                    bus.calcBounds(new int[][] { { regPanelX + buf.getX() + DisplayStyles.REG_16_WIDTH + 4, buf.getY() + 14 }, { regPanelX + this.alu.getX() + 45, buf.getY() + 14 }, { regPanelX + this.alu.getX() + 45, this.alu.getY() - 13 } });
                    continue;
                }
                case PS_ALU: {
                    bus.calcBounds(new int[][] { { regPanelX + rs.getX() + DisplayStyles.REG_16_WIDTH + 4, rs.getY() + 14 }, { regPanelX + this.alu.getX() + 45, rs.getY() + 14 }, { regPanelX + this.alu.getX() + 45, this.alu.getY() - 13 } });
                    continue;
                }
                case IR_ALU: {
                    bus.calcBounds(new int[][] { { regPanelX + accum.getX() + DisplayStyles.REG_16_WIDTH + 4, irReg.getY() + 14 }, { regPanelX + this.alu.getX() + 45, irReg.getY() + 14 }, { regPanelX + this.alu.getX() + 45, this.alu.getY() - 13 } });
                    continue;
                }
                case COMM_AR: {
                    bus.calcBounds(new int[][] { { regPanelX + this.alu.getX() + 90, this.commutView.getY() + this.commutView.getHeight() + 4 }, { regPanelX + this.alu.getX() + 90, addr.getY() + 14 }, { addr.getX() - 13, addr.getY() + 14 } });
                    continue;
                }
                case COMM_DR: {
                    bus.calcBounds(new int[][] { { regPanelX + this.alu.getX() + 90, this.commutView.getY() + this.commutView.getHeight() + 4 }, { regPanelX + this.alu.getX() + 90, this.alu.getY() + 80 + 90 }, { regPanelX + data.getX() + DisplayStyles.REG_16_WIDTH + 20, this.alu.getY() + 80 + 90 }, { regPanelX + data.getX() + DisplayStyles.REG_16_WIDTH + 20, data.getY() + 14 }, { regPanelX + data.getX() + DisplayStyles.REG_16_WIDTH + 13, data.getY() + 14 } });
                    continue;
                }
                case COMM_CR: {
                    bus.calcBounds(new int[][] { { regPanelX + this.alu.getX() + 90, this.commutView.getY() + this.commutView.getHeight() + 4 }, { regPanelX + this.alu.getX() + 90, this.alu.getY() + 80 + 90 }, { regPanelX + instr.getX() + DisplayStyles.REG_16_WIDTH + 20, this.alu.getY() + 80 + 90 }, { regPanelX + instr.getX() + DisplayStyles.REG_16_WIDTH + 20, instr.getY() + 14 }, { regPanelX + instr.getX() + DisplayStyles.REG_16_WIDTH + 13, instr.getY() + 14 } });
                    continue;
                }
                case COMM_IP: {
                    bus.calcBounds(new int[][] { { regPanelX + this.alu.getX() + 90, this.commutView.getY() + this.commutView.getHeight() + 4 }, { regPanelX + this.alu.getX() + 90, this.alu.getY() + 80 + 90 }, { regPanelX + instr.getX() + DisplayStyles.REG_16_WIDTH + 20, this.alu.getY() + 80 + 90 }, { regPanelX + instr.getX() + DisplayStyles.REG_16_WIDTH + 20, ipReg.getY() + 14 }, { regPanelX + ipReg.getX() + DisplayStyles.REG_11_WIDTH + 13, ipReg.getY() + 14 } });
                    continue;
                }
                case COMM_SP: {
                    bus.calcBounds(new int[][] { { regPanelX + this.alu.getX() + 90, this.commutView.getY() + this.commutView.getHeight() + 4 }, { regPanelX + this.alu.getX() + 90, this.alu.getY() + 80 + 90 }, { regPanelX + instr.getX() + DisplayStyles.REG_16_WIDTH + 20, this.alu.getY() + 80 + 90 }, { regPanelX + instr.getX() + DisplayStyles.REG_16_WIDTH + 20, spReg.getY() + 14 }, { regPanelX + spReg.getX() + DisplayStyles.REG_11_WIDTH + 13, spReg.getY() + 14 } });
                    continue;
                }
                case COMM_ALL: {
                    bus.calcBounds(new int[][] { { regPanelX + this.alu.getX() + 90, this.commutView.getY() + this.commutView.getHeight() + 4 }, { regPanelX + this.alu.getX() + 90, this.alu.getY() + 80 + 74 } });
                    continue;
                }
                case COMM_AC: {
                    bus.calcBounds(new int[][] { { regPanelX + this.alu.getX() + 90, this.commutView.getY() + this.commutView.getHeight() + 4 }, { regPanelX + this.alu.getX() + 90, this.alu.getY() + 80 + 90 }, { regPanelX + accum.getX() - 20, this.alu.getY() + 80 + 90 }, { regPanelX + accum.getX() - 20, accum.getY() + 14 }, { regPanelX + accum.getX() - 13, accum.getY() + 14 } });
                    continue;
                }
                case ALU_COMM: {
                    bus.calcBounds(new int[][] { { regPanelX + this.alu.getX() + 90, this.alu.getY() + 80 + 4 }, { regPanelX + this.alu.getX() + 90, this.commutView.getY() - 13 } });
                    continue;
                }
                case COMM_BR: {
                    bus.calcBounds(new int[][] { { regPanelX + this.alu.getX() + 90, this.commutView.getY() + this.commutView.getHeight() + 4 }, { regPanelX + this.alu.getX() + 90, this.alu.getY() + 80 + 90 }, { regPanelX + accum.getX() - 20, this.alu.getY() + 80 + 90 }, { regPanelX + accum.getX() - 20, buf.getY() + 14 }, { regPanelX + accum.getX() - 13, buf.getY() + 14 } });
                    continue;
                }
                case COMM_PS: {
                    bus.calcBounds(new int[][] { { regPanelX + this.alu.getX() + 90, this.commutView.getY() + this.commutView.getHeight() + 4 }, { regPanelX + this.alu.getX() + 90, this.alu.getY() + 80 + 90 }, { regPanelX + accum.getX() - 20, this.alu.getY() + 80 + 90 }, { regPanelX + accum.getX() - 20, rs.getY() + 14 }, { regPanelX + accum.getX() - 13, rs.getY() + 14 } });
                    continue;
                }
                case MEM_IO: {
                    bus.calcBounds(new int[][] { { regPanelX + this.cmanager.getMem().getX() + DisplayStyles.MEM_WIDTH / 4, addr.getY() - 5 }, { regPanelX + this.cmanager.getMem().getX() + DisplayStyles.MEM_WIDTH / 4, this.cmanager.getMem().getY() + 425 + 15 } });
                    continue;
                }
                case MEM_R: {
                    bus.calcBounds((this.cmanager.getMem().getY() < 35) ? new int[][] { { regPanelX + this.cmanager.getMem().getX() - 5, this.cmanager.getMem().getY() + 10 }, { regPanelX + data.getX() + DisplayStyles.REG_16_WIDTH / 2 - 10, this.cmanager.getMem().getY() + 10 }, { regPanelX + data.getX() + DisplayStyles.REG_16_WIDTH / 2 - 10, data.getY() - 13 } } : new int[][] { { regPanelX + this.cmanager.getMem().getX() + DisplayStyles.MEM_WIDTH / 2 + 10, this.cmanager.getMem().getY() - 5 }, { regPanelX + this.cmanager.getMem().getX() + DisplayStyles.MEM_WIDTH / 2 + 10, data.getY() - 65 }, { regPanelX + data.getX() + DisplayStyles.REG_16_WIDTH / 2 - 10, data.getY() - 65 }, { regPanelX + data.getX() + DisplayStyles.REG_16_WIDTH / 2 - 10, data.getY() - 13 } });
                    continue;
                }
                case MEM_W: {
                    bus.calcBounds((this.cmanager.getMem().getY() < 35) ? new int[][] { { regPanelX + data.getX() + DisplayStyles.REG_16_WIDTH / 2 + 10, data.getY() - 5 }, { regPanelX + data.getX() + DisplayStyles.REG_16_WIDTH / 2 + 10, this.cmanager.getMem().getY() + 25 }, { regPanelX + this.cmanager.getMem().getX() - 13, this.cmanager.getMem().getY() + 25 } } : new int[][] { { regPanelX + data.getX() + DisplayStyles.REG_16_WIDTH / 2 + 10, data.getY() - 5 }, { regPanelX + data.getX() + DisplayStyles.REG_16_WIDTH / 2 + 10, data.getY() - 50 }, { regPanelX + this.cmanager.getMem().getX() + DisplayStyles.MEM_WIDTH / 2 - 10, data.getY() - 50 }, { regPanelX + this.cmanager.getMem().getX() + DisplayStyles.MEM_WIDTH / 2 - 10, this.cmanager.getMem().getY() - 13 } });
                    continue;
                }
                case CU: {
                    bus.calcBounds(new int[][] { { regPanelX + this.alu.getX() + 90, this.commutView.getY() + this.commutView.getHeight() + 4 }, { regPanelX + this.alu.getX() + 90, this.alu.getY() + 80 + 90 }, { regPanelX + this.cycleview.getX() + this.cycleview.getWidth() / 2, this.alu.getY() + 80 + 90 }, { regPanelX + this.cycleview.getX() + this.cycleview.getWidth() / 2, this.cycleview.getY() + 200 + 15 } });
                    continue;
                }
            }
        }
    }
}
