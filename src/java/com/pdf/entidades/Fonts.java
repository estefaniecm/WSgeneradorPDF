package com.pdf.entidades;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;

public class Fonts {
//Arial 8
    private final static Font FontA8N = FontFactory.getFont("Arial", 8, Font.NORMAL);
    private final static Font FontA8B = FontFactory.getFont("Arial", 8, Font.BOLD);
    private final static Font FontA8I = FontFactory.getFont("Arial", 8, Font.ITALIC);
    private final static Font FontA8BI = FontFactory.getFont("Arial", 8, Font.BOLDITALIC);

    public static Font getFontA8N() {
        return FontA8N;
    }

    public static Font getFontA8B() {
        return FontA8B;
    }

    public static Font getFontA8I() {
        return FontA8I;
    }

    public static Font getFontA8BI() {
        return FontA8BI;
    }
    //Arial 9
    private final static Font FontA9N = FontFactory.getFont("Arial", 9, Font.NORMAL);
    private final static Font FontA9B = FontFactory.getFont("Arial", 9, Font.BOLD);
    private final static Font FontA9I = FontFactory.getFont("Arial", 9, Font.ITALIC);
    private final static Font FontA9BI = FontFactory.getFont("Arial", 9, Font.BOLDITALIC);

    public static Font getFontA9N() {
        return FontA9N;
    }

    public static Font getFontA9B() {
        return FontA9B;
    }

    public static Font getFontA9I() {
        return FontA9I;
    }

    public static Font getFontA9BI() {
        return FontA9BI;
    }
//Arial 10
    private final static Font FontA10N = FontFactory.getFont("Arial", 10, Font.NORMAL);
    private final static Font FontA10B = FontFactory.getFont("Arial", 10, Font.BOLD);
    private final static Font FontA10I = FontFactory.getFont("Arial", 10, Font.ITALIC);
    private final static Font FontA10BI = FontFactory.getFont("Arial", 10, Font.BOLDITALIC);
    //Arial 11
    private final static Font FontA11N = FontFactory.getFont("Arial", 11, Font.NORMAL);
    private final static Font FontA11B = FontFactory.getFont("Arial", 11, Font.BOLD);
    private final static Font FontA11I = FontFactory.getFont("Arial", 11, Font.ITALIC);
    private final static Font FontA11BI = FontFactory.getFont("Arial", 11, Font.BOLDITALIC);

    public static Font getFontA11N() {
        return FontA11N;
    }

    public static Font getFontA11B() {
        return FontA11B;
    }

    public static Font getFontA11I() {
        return FontA11I;
    }

    public static Font getFontA11BI() {
        return FontA11BI;
    }
// Arial 12
    private final static Font FontA12N = FontFactory.getFont("Arial", 12, Font.NORMAL);
    private final static Font FontA12B = FontFactory.getFont("Arial", 12, Font.BOLD);
    private final static Font FontA12I = FontFactory.getFont("Arial", 12, Font.ITALIC);
    private final static Font FontA12BI = FontFactory.getFont("Arial", 12, Font.BOLDITALIC);
    // Arial 13
    private final static Font FontA13N = FontFactory.getFont("Arial", 13, Font.NORMAL);
    private final static Font FontA13B = FontFactory.getFont("Arial", 13, Font.BOLD);
    private final static Font FontA13I = FontFactory.getFont("Arial", 13, Font.ITALIC);
    private final static Font FontA13BI = FontFactory.getFont("Arial", 13, Font.BOLDITALIC);
// Arial 14
    private final static Font FontA14N = FontFactory.getFont("Arial", 14, Font.NORMAL);
    private final static Font FontA14B = FontFactory.getFont("Arial", 14, Font.BOLD);
    private final static Font FontA14I = FontFactory.getFont("Arial", 14, Font.ITALIC);
    private final static Font FontA14BI = FontFactory.getFont("Arial", 14, Font.BOLDITALIC);

    //Fonts especiales
    private final static Font f = FontFactory.getFont(FontFactory.TIMES_ROMAN, 25, Font.BOLD);
    private final static Font fr = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 9, Font.NORMAL, new BaseColor(217, 37, 48));

    private final static Font fDesc = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 11, Font.BOLD, new BaseColor(192, 0, 0));
    private final static Font fDet = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 10, Font.BOLD, new BaseColor(192, 0, 0));

    public static Font getfDesc() {
        return fDesc;
    }

    public static Font getFontA13N() {
        return FontA13N;
    }

    public static Font getFontA13B() {
        return FontA13B;
    }

    public static Font getFontA13I() {
        return FontA13I;
    }

    public static Font getFontA13BI() {
        return FontA13BI;
    }

    public static Font getfDet() {
        return fDet;
    }

    public static Font getFr() {
        return fr;
    }

    public static Font getF() {
        return f;
    }

    public static Font getFontA12N() {
        return FontA12N;
    }

    public static Font getFontA12B() {
        return FontA12B;
    }

    public static Font getFontA12I() {
        return FontA12I;
    }

    public static Font getFontA12BI() {
        return FontA12BI;
    }

    public static Font getFontA14N() {
        return FontA14N;
    }

    public static Font getFontA14B() {
        return FontA14B;
    }

    public static Font getFontA14I() {
        return FontA14I;
    }

    public static Font getFontA14BI() {
        return FontA14BI;
    }

    public static Font getFontA10N() {
        return FontA10N;
    }

    public static Font getFontA10B() {
        return FontA10B;
    }

    public static Font getFontA10I() {
        return FontA10I;
    }

    public static Font getFontA10BI() {
        return FontA10BI;
    }

}
