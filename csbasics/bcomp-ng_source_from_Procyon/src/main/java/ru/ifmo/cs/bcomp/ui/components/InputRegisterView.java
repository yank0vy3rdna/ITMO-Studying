// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import ru.ifmo.cs.bcomp.Reg;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import ru.ifmo.cs.bcomp.Utils;
import ru.ifmo.cs.components.Register;

public class InputRegisterView extends RegisterView
{
    private final ComponentManager cmanager;
    private final Register reg;
    private final ActiveBitView activeBitView;
    private boolean active;
    private int regWidth;
    private int bitno;
    private int formattedWidth;
    
    public InputRegisterView(final ComponentManager cmgr, final Register reg) {
        super(reg, DisplayStyles.COLOR_TITLE);
        this.active = false;
        this.cmanager = cmgr;
        this.reg = reg;
        this.activeBitView = this.cmanager.getActiveBit();
        final int regWidth = (int)reg.width;
        this.regWidth = regWidth;
        this.bitno = regWidth - 1;
        this.formattedWidth = Utils.getBinaryWidth(this.regWidth);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (!InputRegisterView.this.value.isFocusOwner()) {
                    InputRegisterView.this.reqFocus();
                }
            }
        });
        this.value.setFocusable(true);
        this.value.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(final FocusEvent e) {
                InputRegisterView.this.active = true;
                InputRegisterView.this.setActiveBit(InputRegisterView.this.bitno);
            }
            
            @Override
            public void focusLost(final FocusEvent e) {
                InputRegisterView.this.active = false;
                InputRegisterView.this.setValue();
            }
        });
        this.value.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 8:
                    case 37: {
                        InputRegisterView.this.moveLeft();
                        break;
                    }
                    case 39: {
                        InputRegisterView.this.moveRight();
                        break;
                    }
                    case 38: {
                        InputRegisterView.this.invertBit();
                        break;
                    }
                    case 48:
                    case 96: {
                        InputRegisterView.this.setBit(0);
                        break;
                    }
                    case 49:
                    case 97: {
                        InputRegisterView.this.setBit(1);
                        break;
                    }
                    default: {
                        InputRegisterView.this.cmanager.keyPressed(e);
                        break;
                    }
                }
            }
        });
        this.value.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (!InputRegisterView.this.value.isFocusOwner()) {
                    InputRegisterView.this.reqFocus();
                }
                final int bitno = Utils.getBitNo(e.getX(), InputRegisterView.this.formattedWidth, DisplayStyles.FONT_COURIER_BOLD_21_WIDTH);
                if (bitno < 0) {
                    return;
                }
                InputRegisterView.this.setActiveBit(bitno);
                if (e.getClickCount() > 1) {
                    InputRegisterView.this.invertBit();
                }
            }
        });
    }
    
    private void setActiveBit(final int bitno) {
        this.activeBitView.setValue(this.bitno = bitno);
        this.setValue();
    }
    
    private void moveLeft() {
        this.setActiveBit((this.bitno + 1) % this.regWidth);
    }
    
    private void moveRight() {
        this.setActiveBit(((this.bitno == 0) ? this.regWidth : this.bitno) - 1);
    }
    
    private void invertBit() {
        this.reg.invertBit(this.bitno);
        this.setValue();
    }
    
    private void setBit(final int value) {
        this.reg.setValue(value, 1L, this.bitno);
        this.moveRight();
    }
    
    @Override
    public void setValue() {
        if (this.active) {
            final StringBuilder str = new StringBuilder("<html>" + Utils.toBinary((int)this.reg.getValue(), this.regWidth) + "</html>");
            final int pos = 6 + this.formattedWidth - Utils.getBinaryWidth(this.bitno + 1);
            str.insert(pos + 1, "</font>");
            str.insert(pos, "<font color=\"#FF0000\">");
            this.setValue(str.toString());
            this.cmanager.getRegisterView(Reg.IR).setValue(Utils.toBinary((int)this.reg.getValue(), this.regWidth));
        }
        else {
            super.setValue("<html>" + Utils.toBinary((int)this.reg.getValue(), this.regWidth) + "</html>");
        }
    }
    
    public void reqFocus() {
        try {
            this.value.requestFocus();
        }
        catch (Exception ex) {}
        this.value.requestFocusInWindow();
    }
    
    public void setActive() {
        this.reqFocus();
        this.active = true;
        this.setActiveBit(this.bitno);
    }
}
