// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CellEditorEventSource.java

package jclass.cell;


// Referenced classes of package jclass.cell:
//            CellEditorListener

public interface CellEditorEventSource
{

    public abstract void addCellEditorListener(CellEditorListener celleditorlistener);

    public abstract void removeCellEditorListener(CellEditorListener celleditorlistener);
}
