// 
// Decompiled by Procyon v0.5.36
// 

package ru.ifmo.cs.bcomp.ui.components;

import ru.ifmo.cs.bcomp.Utils;
import java.awt.geom.AffineTransform;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;

public class DisplayStyles
{
    private static final FontRenderContext fr;
    private static Font fontPlain;
    private static Font fontBold;
    public static final Font FONT_COURIER_PLAIN_12;
    public static final Font FONT_COURIER_BOLD_18;
    public static final Font FONT_COURIER_BOLD_21;
    public static final Font FONT_COURIER_BOLD_25;
    public static final int FONT_COURIER_BOLD_21_WIDTH;
    public static final Font FONT_COURIER_BOLD_45;
    public static final Color COLOR_TEXT;
    public static final Color COLOR_ACTIVE;
    public static final Color COLOR_BUS;
    public static final Color COLOR_TITLE;
    public static final Color COLOR_VALUE;
    public static final Color COLOR_INPUT_TITLE;
    public static final Color COLOR_ACTIVE_INPUT;
    public static final Color COLOR_BACKGROUND;
    public static final String COLOR_ACTIVE_BIT = "<font color=\"#FF0000\">";
    public static final String COLOR_END = "</font>";
    public static final String HTML = "<html>";
    public static final String HTML_END = "</html>";
    public static final int PANE_HEIGHT = 560;
    public static final int CELL_HEIGHT = 25;
    public static final int REG_HEIGHT = 28;
    private static final int REG_1_WIDTH;
    private static final int REG_3_WIDTH;
    private static final int REG_4_WIDTH;
    private static final int REG_5_WIDTH;
    public static final int REG_8_WIDTH;
    public static final int REG_9_WIDTH;
    public static final int REG_11_WIDTH;
    public static final int REG_16_WIDTH;
    private static final int REG_16_HALF;
    private static final int REG_HEIGHT_HALF = 26;
    public static final int BUS_WIDTH = 4;
    public static final int ELEMENT_DELIM = 16;
    private static final int ARROW = 13;
    public static final int BUTTONS_HEIGHT = 50;
    public static final int BUTTONS_SPACE = 2;
    public static final int BUTTONS_Y = 510;
    public static final int REG_KEY_X = 8;
    public static final int REG_KEY_Y = 466;
    public static final int ACTIVE_BIT_X;
    public static final int BUS_LEFT_INPUT_X1;
    public static final int BUS_KEY_ALU = 461;
    public static final int REG_C_X_BV;
    public static final int REG_ACCUM_X_BV;
    public static final int REG_ACCUM_Y_BV = 422;
    public static final int FROM_ALU_X;
    public static final int TO_ACCUM_Y = 408;
    public static final int FROM_ALU_Y1 = 401;
    public static final int FROM_ALU_Y = 385;
    public static final int ALU_WIDTH = 181;
    public static final int ALU_HEIGHT = 80;
    public static final int ALU_Y = 301;
    public static final int BUS_LEFT_INPUT_X;
    public static final int BUS_LEFT_INPUT_DOWN = 287;
    public static final int BUS_LEFT_INPUT_UP = 280;
    public static final int BUS_FROM_ACCUM_X;
    public static final int BUS_FROM_ACCUM_Y = 448;
    public static final int REG_IP_X_BV;
    public static final int REG_IP_Y_BV = 236;
    public static final int BUS_FROM_IP_X;
    public static final int BUS_FROM_IP_Y = 262;
    public static final int BUS_RIGHT_X1;
    public static final int BUS_RIGHT_X;
    public static final int REG_DATA_Y_BV = 192;
    public static final int BUS_FROM_DATA_Y = 224;
    public static final int BUS_TO_DATA_Y = 218;
    public static final int BUS_RIGHT_TO_X;
    public static final int REG_ADDR_Y_BV = 148;
    public static final int BUS_TO_ADDR_Y = 174;
    public static final int BUS_TO_ADDR_X;
    public static final int REG_INSTR_X_BV;
    public static final int BUS_TO_INSTR_X;
    public static final int BUS_TO_DATA_X;
    public static final int BUS_FROM_INSTR_Y = 180;
    public static final int BUS_FROM_INSTR_X;
    public static final int MEM_X = 1;
    public static final int MEM_Y = 1;
    public static final int MEM_WIDTH;
    public static final int BUS_ADDR_X1;
    public static final int BUS_ADDR_X2;
    public static final int BUS_READ_Y = 205;
    public static final int BUS_WRITE_Y = 231;
    public static final int BUS_READ_X1;
    public static final int BUS_READ_X2;
    public static final int CYCLEVIEW_Y = 301;
    public static final int BUS_INSTR_TO_CU_X;
    public static final int BUS_INSTR_TO_CU_Y = 287;
    public static final int PANE_WIDTH;
    public static final Dimension PANE_SIZE;
    private static final int REGS_RIGHT_X;
    public static final int CU_X_IO;
    public static final int REG_ACC_X_IO;
    public static final int CU_Y_IO = 17;
    public static final int REG_ADDR_Y_IO = 61;
    public static final int REG_IP_Y_IO = 105;
    public static final int REG_DATA_Y_IO = 149;
    public static final int REG_INSTR_Y_IO = 193;
    public static final int REG_ACCUM_Y_IO = 237;
    public static final int IO_DELIM;
    public static final int IO_X;
    public static final int FLAG_WIDTH = 100;
    public static final int FLAG_OFFSET;
    public static final int BUS_INTR_Y = 30;
    public static final int LABEL_INTR_Y = 6;
    public static final int BUS_INTR_LEFT_X;
    public static final int IO1_CENTER;
    public static final int IO2_CENTER;
    public static final int IO3_CENTER;
    public static final int BUS_INTR_Y1 = 46;
    public static final int FLAG_Y = 51;
    public static final int BUS_TSF_X1;
    public static final int BUS_TSF_Y2 = 80;
    public static final int BUS_TSF_Y = 96;
    public static final int BUS_TSF_Y1 = 57;
    public static final int BUS_TSF_X;
    public static final int LABEL_TSF_Y = 104;
    public static final int LABEL_ADDR_Y = 124;
    public static final int BUS_IO_ADDR_Y = 145;
    public static final int DECODER_Y = 166;
    public static final int BUS_IO_ADDR_Y1 = 152;
    public static final int BUS_IO_ADDR_Y2 = 162;
    public static final int BUS_IO_ADDR_X;
    private static final int DECODER_HEIGHT = 77;
    public static final int BUS_IO_REQ_Y = 263;
    public static final int BUS_IO_REQ_Y1 = 256;
    public static final int LABEL_REQ_Y = 271;
    public static final int LABEL_IN_Y = 287;
    public static final int BUS_IN_Y = 308;
    public static final int IO_DATA_Y = 328;
    public static final int BUS_IN_Y1 = 250;
    public static final int BUS_IN_Y2 = 323;
    public static final int BUS_IN_X;
    public static final int BUS_OUT_Y = 276;
    public static final int BUS_OUT_Y2 = 369;
    public static final int BUS_OUT_X;
    public static final int LABEL_OUT_Y = 284;
    private static final int MP_REGS_WIDTH;
    public static final int ALU_X_MP;
    public static final int REG_ACC_Y_MP = 201;
    public static final int REG_STATE_Y_MP = 245;
    private static final int REG_RIGHT_X_MP;
    public static final int REG_IP_X_MP;
    public static final int REG_INSTR_X_MP;
    public static final int REG_BUF_X_MP;
    public static final int REG_STATE_X;
    private static final int LEFT_X;
    public static final int MICROMEM_X;
    public static final int TEXTAREA_X = 1;
    public static final int TEXTAREA_Y = 1;
    public static final int TEXTAREA_WIDTH = 600;
    public static final int TEXTAREA_HEIGHT = 558;
    public static final int MEME_WIDTH = 124;
    
    static {
        fr = new FontRenderContext(null, true, true);
        try {
            DisplayStyles.fontPlain = Font.createFont(0, DisplayStyles.class.getClassLoader().getResourceAsStream("Roboto-Regular.ttf"));
            DisplayStyles.fontBold = Font.createFont(0, DisplayStyles.class.getClassLoader().getResourceAsStream("Roboto-Medium.ttf"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            DisplayStyles.fontPlain = new Font("Courier New", 0, 24);
            DisplayStyles.fontBold = new Font("Courier New", 1, 24);
        }
        FONT_COURIER_PLAIN_12 = DisplayStyles.fontPlain.deriveFont(12.0f);
        FONT_COURIER_BOLD_18 = DisplayStyles.fontBold.deriveFont(18.0f);
        FONT_COURIER_BOLD_21 = DisplayStyles.fontBold.deriveFont(21.0f);
        FONT_COURIER_BOLD_25 = DisplayStyles.fontBold.deriveFont(25.0f);
        FONT_COURIER_BOLD_21_WIDTH = (int)Math.round(DisplayStyles.FONT_COURIER_BOLD_21.getStringBounds("0", DisplayStyles.fr).getWidth());
        FONT_COURIER_BOLD_45 = DisplayStyles.fontBold.deriveFont(45.0f);
        COLOR_TEXT = Color.BLACK;
        COLOR_ACTIVE = Color.RED;
        COLOR_BUS = Color.GRAY;
        COLOR_TITLE = new Color(157, 189, 165);
        COLOR_VALUE = new Color(219, 249, 235);
        COLOR_INPUT_TITLE = new Color(207, 239, 215);
        COLOR_ACTIVE_INPUT = new Color(192, 0, 0);
        COLOR_BACKGROUND = new Color(200, 221, 242);
        REG_1_WIDTH = 2 * DisplayStyles.FONT_COURIER_BOLD_21_WIDTH + 2;
        REG_3_WIDTH = 4 * DisplayStyles.FONT_COURIER_BOLD_21_WIDTH + 2;
        REG_4_WIDTH = 5 * DisplayStyles.FONT_COURIER_BOLD_21_WIDTH + 2;
        REG_5_WIDTH = 6 * DisplayStyles.FONT_COURIER_BOLD_21_WIDTH + 2;
        REG_8_WIDTH = (Utils.getBinaryWidth(8) + 1) * DisplayStyles.FONT_COURIER_BOLD_21_WIDTH + 2;
        REG_9_WIDTH = (Utils.getBinaryWidth(9) + 1) * DisplayStyles.FONT_COURIER_BOLD_21_WIDTH + 2;
        REG_11_WIDTH = (Utils.getBinaryWidth(11) + 1) * DisplayStyles.FONT_COURIER_BOLD_21_WIDTH + 17;
        REG_16_WIDTH = (Utils.getBinaryWidth(16) + 1) * DisplayStyles.FONT_COURIER_BOLD_21_WIDTH + 17;
        REG_16_HALF = DisplayStyles.REG_16_WIDTH / 2;
        ACTIVE_BIT_X = 8 + DisplayStyles.REG_16_WIDTH + 16;
        BUS_LEFT_INPUT_X1 = 8 + DisplayStyles.REG_16_HALF;
        REG_C_X_BV = DisplayStyles.BUS_LEFT_INPUT_X1 + 20 + 1;
        REG_ACCUM_X_BV = DisplayStyles.REG_C_X_BV + DisplayStyles.REG_1_WIDTH - 1;
        FROM_ALU_X = DisplayStyles.REG_ACCUM_X_BV + 61;
        BUS_LEFT_INPUT_X = DisplayStyles.BUS_LEFT_INPUT_X1 + 60 + 1;
        BUS_FROM_ACCUM_X = DisplayStyles.REG_C_X_BV - 4 - 1;
        REG_IP_X_BV = DisplayStyles.REG_ACCUM_X_BV + 6 * DisplayStyles.FONT_COURIER_BOLD_21_WIDTH;
        BUS_FROM_IP_X = DisplayStyles.REG_IP_X_BV - 4 - 1;
        BUS_RIGHT_X1 = DisplayStyles.BUS_FROM_IP_X - 16;
        BUS_RIGHT_X = DisplayStyles.REG_C_X_BV + 181 - 45 + 2;
        BUS_RIGHT_TO_X = DisplayStyles.REG_ACCUM_X_BV + DisplayStyles.REG_16_WIDTH + 16 + 4;
        BUS_TO_ADDR_X = DisplayStyles.REG_ACCUM_X_BV + DisplayStyles.REG_11_WIDTH + 13;
        REG_INSTR_X_BV = DisplayStyles.BUS_RIGHT_TO_X + 4 + 16 + 1;
        BUS_TO_INSTR_X = DisplayStyles.REG_INSTR_X_BV - 13 - 1;
        BUS_TO_DATA_X = DisplayStyles.REG_ACCUM_X_BV + DisplayStyles.REG_16_WIDTH + 13;
        BUS_FROM_INSTR_X = DisplayStyles.REG_INSTR_X_BV + 32 + 4;
        MEM_WIDTH = 9 * DisplayStyles.FONT_COURIER_BOLD_21_WIDTH + 3;
        BUS_ADDR_X1 = DisplayStyles.REG_ACCUM_X_BV - 4 - 1;
        BUS_ADDR_X2 = 1 + DisplayStyles.MEM_WIDTH + 13;
        BUS_READ_X1 = DisplayStyles.REG_ACCUM_X_BV - 13 - 1;
        BUS_READ_X2 = 1 + DisplayStyles.MEM_WIDTH + 4;
        BUS_INSTR_TO_CU_X = DisplayStyles.REG_INSTR_X_BV + DisplayStyles.REG_16_HALF;
        PANE_WIDTH = ((5 * DisplayStyles.REG_11_WIDTH < 900) ? 900 : (5 * DisplayStyles.REG_11_WIDTH));
        PANE_SIZE = new Dimension(DisplayStyles.PANE_WIDTH, 560);
        REGS_RIGHT_X = 1 + DisplayStyles.MEM_WIDTH + DisplayStyles.REG_8_WIDTH + 16 - 1;
        CU_X_IO = DisplayStyles.REGS_RIGHT_X - DisplayStyles.REG_8_WIDTH;
        REG_ACC_X_IO = DisplayStyles.CU_X_IO + DisplayStyles.REG_1_WIDTH - 1;
        IO_DELIM = DisplayStyles.REG_8_WIDTH + 16;
        IO_X = DisplayStyles.REG_INSTR_X_BV + DisplayStyles.REG_16_WIDTH - DisplayStyles.REG_8_WIDTH - 2 * DisplayStyles.IO_DELIM;
        FLAG_OFFSET = (DisplayStyles.REG_8_WIDTH - 100) / 2;
        BUS_INTR_LEFT_X = DisplayStyles.REGS_RIGHT_X + 13;
        IO1_CENTER = DisplayStyles.IO_X + DisplayStyles.REG_8_WIDTH / 2;
        IO2_CENTER = DisplayStyles.IO1_CENTER + DisplayStyles.IO_DELIM;
        IO3_CENTER = DisplayStyles.IO2_CENTER + DisplayStyles.IO_DELIM;
        BUS_TSF_X1 = DisplayStyles.IO_X - 16 - 4 - 1 + DisplayStyles.FLAG_OFFSET;
        BUS_TSF_X = DisplayStyles.CU_X_IO + DisplayStyles.REG_8_WIDTH - 16 - 1;
        BUS_IO_ADDR_X = DisplayStyles.CU_X_IO + DisplayStyles.REG_4_WIDTH + 4;
        BUS_IN_X = DisplayStyles.REG_ACC_X_IO + DisplayStyles.REG_4_WIDTH + 13;
        BUS_OUT_X = DisplayStyles.REG_ACC_X_IO + DisplayStyles.REG_4_WIDTH + 4;
        MP_REGS_WIDTH = DisplayStyles.REG_4_WIDTH + 16 + DisplayStyles.REG_5_WIDTH;
        ALU_X_MP = DisplayStyles.CU_X_IO + (DisplayStyles.MP_REGS_WIDTH - 181) / 2;
        REG_RIGHT_X_MP = DisplayStyles.CU_X_IO + DisplayStyles.MP_REGS_WIDTH;
        REG_IP_X_MP = DisplayStyles.REG_RIGHT_X_MP - DisplayStyles.REG_3_WIDTH;
        REG_INSTR_X_MP = DisplayStyles.REG_RIGHT_X_MP - DisplayStyles.REG_4_WIDTH;
        REG_BUF_X_MP = DisplayStyles.REG_RIGHT_X_MP - DisplayStyles.REG_5_WIDTH;
        REG_STATE_X = DisplayStyles.CU_X_IO + (DisplayStyles.MP_REGS_WIDTH - DisplayStyles.REG_9_WIDTH) / 2;
        LEFT_X = DisplayStyles.IO_X + 3 * DisplayStyles.IO_DELIM - 1;
        MICROMEM_X = DisplayStyles.LEFT_X - DisplayStyles.MEM_WIDTH;
    }
}
