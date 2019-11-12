// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui;

public class BCompApp
{
    public static void main(final String[] args) throws Exception {
        String app;
        try {
            app = System.getProperty("mode", "gui");
        }
        catch (Exception e) {
            app = "gui";
        }
        if (app.equals("gui")) {
            final GUI gui = new GUI();
            gui.gui();
            return;
        }
        if (app.equals("cli")) {
            final CLI cli = new CLI();
            cli.cli();
            return;
        }
        if (app.equals("decoder")) {
            final MicroCodeDecoder mpdecoder = new MicroCodeDecoder();
            mpdecoder.decode();
            return;
        }
        if (app.equals("nightmare")) {
            final Nightmare nightmare = new Nightmare();
            return;
        }
        System.err.println("Invalid mode selected");
    }
}
