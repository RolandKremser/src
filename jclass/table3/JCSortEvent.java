// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCSortEvent.java

package jclass.table3;


// Referenced classes of package jclass.table3:
//            TableAWTEvent, Table

public class JCSortEvent extends TableAWTEvent
{

    public int[] getColumns()
    {
        return column;
    }

    public int[] getNewRows()
    {
        return new_rows;
    }

    public JCSortEvent(Table table, int ai[], int ai1[])
    {
        super(table, 5008);
        column = ai;
        new_rows = ai1;
    }

    public static final int SORT = 5008;
    int column[];
    int new_rows[];
}
