// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCResizeCellEvent.java

package jclass.table3;


// Referenced classes of package jclass.table3:
//            TableAWTEvent, JCTblEnum, Table

public class JCResizeCellEvent extends TableAWTEvent
{

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public String getParam()
    {
        return param;
    }

    public int getCurrentRowHeight()
    {
        return current_row_height;
    }

    public int getNewRowHeight()
    {
        return new_row_height;
    }

    public void setNewRowHeight(int i)
    {
        new_row_height = i;
    }

    public int getCurrentColumnWidth()
    {
        return current_column_width;
    }

    public int getNewColumnWidth()
    {
        return new_column_width;
    }

    public void setNewColumnWidth(int i)
    {
        new_column_width = i;
    }

    public boolean getAllowResize()
    {
        return doit;
    }

    public void setAllowResize(boolean flag)
    {
        doit = flag;
    }

    public int getType()
    {
        return type;
    }

    public JCResizeCellEvent(Table table, int i, int j, int k, String s, int l, int i1)
    {
        super(table, 5005);
        doit = true;
        type = i;
        row = j;
        column = k;
        param = s;
        current_row_height = i1;
        current_column_width = l;
        new_row_height = new_column_width = -999;
    }

    public static final int RESIZE_CELL = 5005;
    public static final int BEGIN = 1;
    public static final int END = 2;
    int row;
    int column;
    int type;
    String param;
    int current_row_height;
    int current_column_width;
    int new_row_height;
    int new_column_width;
    boolean doit;
}
