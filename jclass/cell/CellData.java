// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CellData.java

package jclass.cell;


// Referenced classes of package jclass.cell:
//            CellEditor, CellRenderer

public interface CellData
{

    public abstract Object getData();

    public abstract CellEditor getEditor();

    public abstract CellRenderer getRenderer();

    public abstract void setData(Object obj);
}
