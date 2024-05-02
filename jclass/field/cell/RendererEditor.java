// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RendererEditor.java

package jclass.field.cell;

import java.awt.*;
import jclass.bwt.JCTextInterface;
import jclass.cell.*;
import jclass.field.JCField;
import jclass.field.JCValidateInterface;

abstract class RendererEditor extends JCField
    implements CellRenderer, CellEditor, ValidateInterface
{

    public RendererEditor(Component component, JCValidateInterface jcvalidateinterface)
    {
        super(component, jcvalidateinterface, null, null);
        setEventSource(this);
    }

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        super.initialize(initialevent, cellinfo, obj);
        if(super.vc instanceof CellEditor)
        {
            ((CellEditor)super.vc).initialize(initialevent, cellinfo, super.validator.format(obj));
            if(cellinfo.isEditable())
                super.vc.setBackground(cellinfo.getBackground().brighter());
            if(super.vc instanceof JCTextInterface)
            {
                ((JCTextInterface)super.vc).setSelectedBackground(SystemColor.textHighlight);
                ((JCTextInterface)super.vc).setSelectedForeground(SystemColor.textHighlightText);
            }
        }
    }

    public Component getComponent()
    {
        return super.vc;
    }

    public void cancelCellEditing()
    {
        if(super.vc instanceof CellEditor)
            ((CellEditor)super.vc).cancelCellEditing();
    }

    public KeyModifier[] getReservedKeys()
    {
        if(super.vc instanceof CellEditor)
            return ((CellEditor)super.vc).getReservedKeys();
        else
            return null;
    }

    public void addCellEditorListener(CellEditorListener celleditorlistener)
    {
        if(super.vc instanceof CellEditor)
            ((CellEditor)super.vc).addCellEditorListener(celleditorlistener);
    }

    public void removeCellEditorListener(CellEditorListener celleditorlistener)
    {
        if(super.vc instanceof CellEditor)
            ((CellEditor)super.vc).removeCellEditorListener(celleditorlistener);
    }

    public void draw(Graphics g, CellInfo cellinfo, Object obj, boolean flag)
    {
        if(super.vc instanceof CellRenderer)
            ((CellRenderer)super.vc).draw(g, cellinfo, super.validator.format(obj), flag);
    }

    public Dimension getPreferredSize(CellInfo cellinfo, Object obj)
    {
        if(super.vc instanceof CellRenderer)
            return ((CellRenderer)super.vc).getPreferredSize(cellinfo, super.validator.format(obj));
        else
            return null;
    }
}
