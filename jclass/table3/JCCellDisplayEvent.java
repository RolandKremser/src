// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCellDisplayEvent.java

package jclass.table3;


// Referenced classes of package jclass.table3:
//            TableAWTEvent, Table

public class JCCellDisplayEvent extends TableAWTEvent
{

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public Object getCellData()
    {
        return cell_value;
    }

    public String getDisplayString()
    {
        return display_string;
    }

    public void setDisplayString(String s)
    {
        display_string = s;
    }

    public JCCellDisplayEvent(Table table, int i, int j, Object obj)
    {
        super(table, 5101);
        row = i;
        column = j;
        cell_value = obj;
        if(obj != null)
            display_string = obj.toString();
    }

    public static final int CELL_DATA = 5101;
    int row;
    int column;
    Object cell_value;
    String display_string;
}
