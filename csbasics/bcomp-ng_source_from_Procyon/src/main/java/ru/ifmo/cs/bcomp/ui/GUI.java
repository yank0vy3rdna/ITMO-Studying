// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui;

import ru.ifmo.cs.bcomp.IOCtrl;
import javax.swing.JFrame;
import java.awt.Component;
import ru.ifmo.cs.bcomp.ui.components.DisplayStyles;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import ru.ifmo.cs.bcomp.ui.components.BasicView;
import ru.ifmo.cs.bcomp.CPU;
import ru.ifmo.cs.bcomp.BasicComp;
import ru.ifmo.cs.bcomp.ui.components.ActivateblePanel;
import javax.swing.JTabbedPane;
import ru.ifmo.cs.bcomp.ui.components.ComponentManager;
import javax.swing.JApplet;

public class GUI extends JApplet
{
    private ComponentManager cmanager;
    private JTabbedPane tabs;
    private ActivateblePanel activePanel;
    private final BasicComp bcomp;
    private final CPU cpu;
    
    public GUI() throws Exception {
        this.activePanel = null;
        this.bcomp = new BasicComp();
        this.cpu = this.bcomp.getCPU();
    }
    
    @Override
    public void init() {
        this.cmanager = new ComponentManager(this);
        final ActivateblePanel[] panels = { new BasicView(this) };
        (this.tabs = new JTabbedPane()).addKeyListener(this.cmanager.getKeyListener());
        this.tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent e) {
                if (GUI.this.activePanel != null) {
                    GUI.this.activePanel.panelDeactivate();
                }
                GUI.this.activePanel = (ActivateblePanel)GUI.this.tabs.getSelectedComponent();
                GUI.this.activePanel.panelActivate();
            }
        });
        this.tabs.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent e) {
                super.focusGained(e);
                for (final ActivateblePanel panel : panels) {
                    panel.redrawArrows();
                }
            }
        });
        this.tabs.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                for (final ActivateblePanel panel : panels) {
                    panel.revalidate();
                    panel.repaint();
                    panel.redrawArrows();
                }
            }
        });
        for (final ActivateblePanel pane : panels) {
            pane.setPreferredSize(DisplayStyles.PANE_SIZE);
            this.tabs.addTab(pane.getPanelName(), pane);
        }
        this.add(this.tabs);
    }
    
    @Override
    public void start() {
        this.cmanager.switchFocus();
    }
    
    public void gui() throws Exception {
        final JFrame frame = new JFrame("\u0411\u042d\u0412\u041c");
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().add(this);
        this.init();
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(frame.getSize());
        this.start();
    }
    
    public BasicComp getBasicComp() {
        return this.bcomp;
    }
    
    public CPU getCPU() {
        return this.cpu;
    }
    
    public IOCtrl[] getIOCtrls() {
        return null;
    }
    
    public ComponentManager getComponentManager() {
        return this.cmanager;
    }
}
