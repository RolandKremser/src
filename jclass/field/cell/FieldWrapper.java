// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FieldWrapper.java

package jclass.field.cell;

import java.awt.*;
import jclass.bwt.JCTextInterface;
import jclass.cell.*;
import jclass.field.JCField;

class FieldWrapper
    implements CellRenderer, CellEditor, ValidateInterface
{

    public FieldWrapper(Component component)
    {
        vc = component;
    }

    public void setField(JCField jcfield)
    {
        field = jcfield;
        field.setEventSource(this);
    }

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        if(vc instanceof CellEditor)
        {
            ((CellEditor)vc).initialize(initialevent, cellinfo, obj);
            if(cellinfo.isEditable())
                vc.setBackground(cellinfo.getBackground().brighter());
            if(vc instanceof JCTextInterface)
            {
                ((JCTextInterface)vc).setSelectedBackground(SystemColor.textHighlight);
                ((JCTextInterface)vc).setSelectedForeground(SystemColor.textHighlightText);
            }
        }
    }

    public Component getComponent()
    {
        return vc;
    }

    public Object getCellEditorValue()
    {
        if(vc instanceof CellEditor)
            return ((CellEditor)vc).getCellEditorValue();
        else
            return null;
    }

    public boolean stopCellEditing()
    {
        if(vc instanceof CellEditor)
            return ((CellEditor)vc).stopCellEditing();
        else
            return true;
    }

    public boolean isModified()
    {
        if(vc instanceof CellEditor)
            return ((CellEditor)vc).isModified();
        else
            return false;
    }

    public void cancelCellEditing()
    {
        if(vc instanceof CellEditor)
            ((CellEditor)vc).cancelCellEditing();
    }

    public KeyModifier[] getReservedKeys()
    {
        if(vc instanceof CellEditor)
            return ((CellEditor)vc).getReservedKeys();
        else
            return null;
    }

    public void addCellEditorListener(CellEditorListener celleditorlistener)
    {
        if(vc instanceof CellEditor)
            ((CellEditor)vc).addCellEditorListener(celleditorlistener);
    }

    public void removeCellEditorListener(CellEditorListener celleditorlistener)
    {
        if(vc instanceof CellEditor)
            ((CellEditor)vc).removeCellEditorListener(celleditorlistener);
    }

    public void draw(Graphics g, CellInfo cellinfo, Object obj, boolean flag)
    {
        if(vc instanceof CellRenderer)
            ((CellRenderer)vc).draw(g, cellinfo, obj, flag);
    }

    public Dimension getPreferredSize(CellInfo cellinfo, Object obj)
    {
        if(vc instanceof CellRenderer)
            return ((CellRenderer)vc).getPreferredSize(cellinfo, obj);
        else
            return null;
    }

    public void addValidateListener(ValidateListener validatelistener)
    {
        if(field != null)
            field.addValidateListener(validatelistener);
    }

    public void removeValidateListener(ValidateListener validatelistener)
    {
        if(field != null)
            field.removeValidateListener(validatelistener);
    }

    private Component vc;
    protected JCField field;
}
