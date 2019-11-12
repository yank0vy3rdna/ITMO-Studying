// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import ru.ifmo.cs.bcomp.ControlSignal;

public class BusView
{
    private final ControlSignal[] signals;
    private int[] xs;
    private int[] ys;
    private int[] widths;
    private int[] heights;
    private int[] arrowX;
    private int[] arrowY;
    private boolean isVisible;
    
    public BusView(final ControlSignal... signals) {
        this.arrowX = new int[3];
        this.arrowY = new int[3];
        this.isVisible = true;
        this.signals = signals;
    }
    
    public void calcBounds(final int[][] points) {
        final int npoints = points.length - 1;
        int x2 = 0;
        int y2 = 0;
        int width = 0;
        int height = 0;
        this.xs = new int[npoints];
        this.ys = new int[npoints];
        this.widths = new int[npoints];
        this.heights = new int[npoints];
        for (int i = 0; i < npoints; ++i) {
            final int x3 = points[i][0];
            x2 = points[i + 1][0];
            final int y3 = points[i][1];
            y2 = points[i + 1][1];
            width = x3 - x2;
            if (width != 0) {
                this.xs[i] = ((width < 0) ? (x3 - 4) : (x2 - 4));
                this.widths[i] = Math.abs(width) + 8 + 1;
                this.ys[i] = y3 - 4;
                this.heights[i] = 9;
            }
            else {
                height = y3 - y2;
                this.xs[i] = x3 - 4;
                this.widths[i] = 9;
                this.ys[i] = ((height < 0) ? (y3 - 4) : (y2 - 4));
                this.heights[i] = Math.abs(height) + 8 + 1;
            }
        }
        if (width != 0) {
            this.arrowY[0] = y2;
            this.arrowY[1] = y2 - 8;
            this.arrowY[2] = y2 + 8;
            if (width > 0) {
                this.arrowX[0] = x2 - 12 - 1;
                this.arrowX[1] = (this.arrowX[2] = x2 - 4 - 1);
            }
            else {
                this.arrowX[0] = x2 + 12 + 1;
                this.arrowX[1] = (this.arrowX[2] = x2 + 4 + 1);
            }
        }
        else {
            this.arrowX[0] = x2;
            this.arrowX[1] = x2 - 8;
            this.arrowX[2] = x2 + 8;
            if (height > 0) {
                this.arrowY[0] = y2 - 12 - 1;
                this.arrowY[1] = (this.arrowY[2] = y2 - 4 - 1);
            }
            else {
                this.arrowY[0] = y2 + 12 + 1;
                this.arrowY[1] = (this.arrowY[2] = y2 + 4 + 1);
            }
        }
    }
    
    public void draw(final Graphics g, final Color color) {
        if (this.isVisible) {
            g.setColor(color);
        }
        else {
            g.setColor(DisplayStyles.COLOR_BACKGROUND);
        }
        g.drawPolygon(this.arrowX, this.arrowY, this.arrowX.length);
        g.fillPolygon(this.arrowX, this.arrowY, this.arrowX.length);
        for (int i = 0; i < this.xs.length; ++i) {
            g.fillRect(this.xs[i], this.ys[i], this.widths[i], this.heights[i]);
        }
    }
    
    public ControlSignal[] getSignals() {
        return this.signals;
    }
}
