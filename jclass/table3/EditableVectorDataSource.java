// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EditableVectorDataSource.java

package jclass.table3;

import java.io.Serializable;

// Referenced classes of package jclass.table3:
//            VectorDataSource, EditableTableData

public class EditableVectorDataSource extends VectorDataSource
    implements EditableTableData, Serializable
{

    public EditableVectorDataSource()
    {
    }

    public EditableVectorDataSource(int i, int j, String s, String s1, String s2)
    {
        super(i, j, s, s1, s2);
    }

    public boolean setTableDataItem(Object obj, int i, int j)
    {
        setCell(i, j, obj);
        return true;
    }
}
