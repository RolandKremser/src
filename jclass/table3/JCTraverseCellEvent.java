// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCTraverseCellEvent.java

package jclass.table3;


// Referenced classes of package jclass.table3:
//            TableAWTEvent, Table

public class JCTraverseCellEvent extends TableAWTEvent
{

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public int getNextRow()
    {
        return next_row;
    }

    public int getNextColumn()
    {
        return next_column;
    }

    public void setNextRow(int i)
    {
        next_row = i;
    }

    public void setNextColumn(int i)
    {
        next_column = i;
    }

    public String getParam()
    {
        return param;
    }

    public JCTraverseCellEvent(Table table, int i, int j, int k, int l, String s)
    {
        super(table, 5003);
        row = i;
        column = j;
        next_row = k;
        next_column = l;
        param = s;
    }

    public static final int TRAVERSE_CELL = 5003;
    int row;
    int column;
    int next_row;
    int next_column;
    String param;
}
