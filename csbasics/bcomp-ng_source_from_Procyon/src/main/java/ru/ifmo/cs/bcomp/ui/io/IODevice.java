// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.io;

import java.awt.Component;
import javax.swing.JFrame;
import ru.ifmo.cs.bcomp.IOCtrl;

public abstract class IODevice
{
    protected final IOCtrl ioctrl;
    protected final String title;
    private JFrame frame;
    
    public IODevice(final IOCtrl ioctrl, final String title) {
        this.frame = null;
        this.ioctrl = ioctrl;
        this.title = title;
    }
    
    protected abstract Component getContent();
    
    public void activate() {
        if (this.frame == null) {
            (this.frame = new JFrame(this.title)).add(this.getContent());
            this.frame.pack();
        }
        this.frame.setVisible(true);
        this.frame.requestFocus();
    }
}
