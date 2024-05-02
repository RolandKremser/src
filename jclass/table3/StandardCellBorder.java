// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StandardCellBorder.java

package jclass.table3;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

// Referenced classes of package jclass.table3:
//            Border, CellBorder

public class StandardCellBorder
    implements CellBorder, Serializable
{

    public StandardCellBorder(int i)
    {
        borderType = i;
    }

    public void setBorderType(int i)
    {
        borderType = i;
    }

    public int getBorderType()
    {
        return borderType;
    }

    public void drawBackground(Graphics g, int i, int j, int k, int l, int i1, int j1, 
            Color color, Color color1, Color color2)
    {
        Border.draw(g, borderType, i, j, k, l, i1, j1, color, color1, color2);
    }

    int borderType;
}
