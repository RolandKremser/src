// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCEnterCellEvent.java

package jclass.table3;


// Referenced classes of package jclass.table3:
//            TableAWTEvent, Table

public class JCEnterCellEvent extends TableAWTEvent
{

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public int getType()
    {
        return type;
    }

    public String getParam()
    {
        return param;
    }

    public JCEnterCellEvent(Table table, int i, int j, int k, String s)
    {
        super(table, 5004);
        type = i;
        row = j;
        column = k;
        param = s;
    }

    public static final int ENTER_CELL = 5004;
    public static final int BEGIN = 1;
    public static final int END = 2;
    int row;
    int column;
    int type;
    String param;
}
