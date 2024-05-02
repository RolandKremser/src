// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultCellBorder.java

package jclass.table3;

import java.io.Serializable;

// Referenced classes of package jclass.table3:
//            CellBorder

class DefaultCellBorder
    implements Serializable
{

    public DefaultCellBorder(CellBorder cellborder, int i)
    {
        cellBorder = cellborder;
        cellBorderSides = i;
    }

    CellBorder cellBorder;
    int cellBorderSides;
}
