// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableData.java

package jclass.table3;


// Referenced classes of package jclass.table3:
//            TableDataListener

public interface TableData
{

    public abstract Object getTableDataItem(int i, int j);

    public abstract int getNumRows();

    public abstract int getNumColumns();

    public abstract Object getTableRowLabel(int i);

    public abstract Object getTableColumnLabel(int i);

    public abstract void addTableDataListener(TableDataListener tabledatalistener);

    public abstract void removeTableDataListener(TableDataListener tabledatalistener);
}
