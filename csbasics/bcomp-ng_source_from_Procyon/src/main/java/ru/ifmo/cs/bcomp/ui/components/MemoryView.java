// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Graphics;
import ru.ifmo.cs.bcomp.Utils;
import javax.swing.JLabel;
import ru.ifmo.cs.components.Memory;

public class MemoryView extends BCompComponent
{
    private Memory mem;
    private int addrBitWidth;
    private int valueBitWidth;
    private int lineX;
    private int lastPage;
    private JLabel[] addrs;
    private JLabel[] values;
    
    public MemoryView(final Memory mem, final int x, final int y) {
        super("RAM", 16);
        this.lastPage = 0;
        this.addrs = new JLabel[16];
        this.values = new JLabel[16];
        this.mem = mem;
        this.addrBitWidth = (int)mem.getAddrWidth();
        final int addrWidth = DisplayStyles.FONT_COURIER_BOLD_21_WIDTH * (1 + Utils.getHexWidth(this.addrBitWidth));
        this.valueBitWidth = (int)mem.width;
        final int valueWidth = DisplayStyles.FONT_COURIER_BOLD_21_WIDTH * (1 + Utils.getHexWidth(this.valueBitWidth));
        this.lineX = 1 + addrWidth;
        this.setBounds(x, y, 3 + addrWidth + valueWidth);
        for (int i = 0; i < 16; ++i) {
            (this.addrs[i] = this.addValueLabel(DisplayStyles.COLOR_TITLE)).setBounds(1, this.getValueY(i), addrWidth, 25);
            (this.values[i] = this.addValueLabel(DisplayStyles.COLOR_VALUE)).setBounds(this.lineX + 1, this.getValueY(i), valueWidth, 25);
        }
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawLine(this.lineX, 27, this.lineX, this.height - 2);
        g.drawLine(1, 26, this.width - 2, 26);
    }
    
    void updateValue(final JLabel label, final int value) {
        label.setText(Utils.toHex(value, this.valueBitWidth));
    }
    
    private void updateValue(final int offset) {
        this.updateValue(this.values[offset], (int)this.mem.getValue(this.lastPage + offset));
    }
    
    public void updateMemory() {
        for (int i = 0; i < 16; ++i) {
            this.addrs[i].setText(Utils.toHex(this.lastPage + i, this.addrBitWidth));
            this.updateValue(i);
        }
    }
    
    private int getPage(final int addr) {
        return addr & 0xFFFFFFF0;
    }
    
    private int getPage() {
        return this.getPage((int)this.mem.getAddrWidth());
    }
    
    public void updateLastAddr() {
        this.lastPage = this.getPage();
    }
    
    public void eventRead() {
        final int addr = (int)this.mem.getLastAccessedAddress();
        final int page = this.getPage(addr);
        if (page != this.lastPage) {
            this.lastPage = page;
            this.updateMemory();
        }
    }
    
    public void eventWrite() {
        final int addr = (int)this.mem.getLastAccessedAddress();
        final int page = this.getPage(addr);
        if (page != this.lastPage) {
            this.lastPage = page;
            this.updateMemory();
        }
        else {
            this.updateValue(addr - page);
        }
    }
}
