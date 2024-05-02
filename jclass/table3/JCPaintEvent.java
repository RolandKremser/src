// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCPaintEvent.java

package jclass.table3;

import java.awt.Rectangle;

// Referenced classes of package jclass.table3:
//            TableAWTEvent, Table

public class JCPaintEvent extends TableAWTEvent
{

    public Rectangle getRect()
    {
        return expose;
    }

    public int getStartRow()
    {
        return start_row;
    }

    public int getStartColumn()
    {
        return start_column;
    }

    public int getEndRow()
    {
        return end_row;
    }

    public int getEndColumn()
    {
        return end_column;
    }

    public int getType()
    {
        return type;
    }

    public JCPaintEvent(Table table, int i, Rectangle rectangle, int j, int k, int l, int i1)
    {
        super(table, 5013);
        type = i;
        expose = rectangle;
        start_row = j;
        start_column = k;
        end_row = l;
        end_column = i1;
    }

    public static final int PAINT = 5013;
    public static final int BEGIN = 1;
    public static final int END = 2;
    Rectangle expose;
    int type;
    int start_row;
    int start_column;
    int end_row;
    int end_column;
}
