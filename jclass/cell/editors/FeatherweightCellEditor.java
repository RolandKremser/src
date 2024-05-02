// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FeatherweightCellEditor.java

package jclass.cell.editors;

import java.awt.Component;
import java.awt.Dimension;
import jclass.cell.*;

public abstract class FeatherweightCellEditor
    implements CellEditor
{

    public abstract void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj);

    public Component getComponent()
    {
        return null;
    }

    public abstract Object getCellEditorValue();

    public abstract boolean isModified();

    public boolean stopCellEditing()
    {
        return true;
    }

    public void cancelCellEditing()
    {
    }

    public Dimension getPreferredSize(CellInfo cellinfo, Object obj)
    {
        return null;
    }

    public KeyModifier[] getReservedKeys()
    {
        return null;
    }

    public void addCellEditorListener(CellEditorListener celleditorlistener)
    {
        support.addCellEditorListener(celleditorlistener);
    }

    public void removeCellEditorListener(CellEditorListener celleditorlistener)
    {
        support.removeCellEditorListener(celleditorlistener);
    }

    public FeatherweightCellEditor()
    {
        support = new CellEditorSupport();
    }

    protected CellEditorSupport support;
}
