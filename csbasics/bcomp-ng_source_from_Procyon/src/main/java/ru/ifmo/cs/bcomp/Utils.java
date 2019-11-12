// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp;

public class Utils
{
    private static final char[] digits;
    private static final String[] flags;
    
    public static String toBinaryFlag(final long value) {
        return Utils.flags[(int)value];
    }
    
    public static int getBinaryWidth(final int width) {
        return width + (width - 1 >> 2);
    }
    
    public static String toBinary(long value, final int width) {
        final int chars = getBinaryWidth(width);
        final char[] buf = new char[chars];
        int pos = chars;
        for (int i = 0; i < width; ++i) {
            if (i != 0 && (i & 0x3) == 0x0) {
                buf[--pos] = ' ';
            }
            buf[--pos] = Utils.digits[(int)(value & 0x1L)];
            value >>= 1;
        }
        return new String(buf);
    }
    
    public static int getBitNo(int pos, final int width, final int charWidth) {
        pos -= charWidth >> 1;
        if (pos < 0) {
            return -1;
        }
        pos = width - pos / charWidth;
        if (pos % 5 == 0) {
            return -1;
        }
        return pos - pos / 5 - 1;
    }
    
    public static int getHexWidth(final int width) {
        return width + 3 >> 2;
    }
    
    public static String toHex(long value, final long width) {
        int chars;
        char[] buf;
        for (chars = getHexWidth((int)width), buf = new char[chars]; chars > 0; buf[--chars] = Utils.digits[(int)(value & 0xFL)], value >>= 4) {}
        return new String(buf);
    }
    
    public static boolean isNumeric(final String s, final int radix) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int i = 0;
        if (s.charAt(0) == '-') {
            ++i;
            if (s.length() == 1) {
                return false;
            }
        }
        while (i < s.length()) {
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
            ++i;
        }
        return true;
    }
    
    public static boolean isHexNumeric(final String s) {
        return isNumeric(s, 16);
    }
    
    public static ControlSignal[] cs(final ControlSignal... signals) {
        return signals;
    }
    
    static {
        digits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        flags = new String[] { "0", "1" };
    }
}
