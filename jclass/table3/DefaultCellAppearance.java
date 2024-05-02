// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultCellAppearance.java

package jclass.table3;

import java.awt.*;
import java.io.Serializable;

class DefaultCellAppearance
    implements Serializable
{

    public DefaultCellAppearance(int i, Color color, Color color1, Font font1)
    {
        alignment = i;
        background = color;
        foreground = color1;
        font = font1;
    }

    int alignment;
    Color background;
    Color foreground;
    Font font;
    FontMetrics fm;
}
