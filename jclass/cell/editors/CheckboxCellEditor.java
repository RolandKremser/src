// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CheckboxCellEditor.java

package jclass.cell.editors;

import jclass.cell.*;

// Referenced classes of package jclass.cell.editors:
//            FeatherweightCellEditor

public class CheckboxCellEditor extends FeatherweightCellEditor
{

    public CheckboxCellEditor()
    {
        editable = true;
    }

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        data = ((Boolean)obj).booleanValue();
        editable = cellinfo.isEditable();
        if(editable)
        {
            data = !data;
            super.support.fireStopEditing(new CellEditorEvent(this));
        }
    }

    public boolean isModified()
    {
        return editable;
    }

    public Object getCellEditorValue()
    {
        return new Boolean(data);
    }

    protected boolean data;
    protected boolean editable;
}
