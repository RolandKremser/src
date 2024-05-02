// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ButtonCellEditor.java

package jclass.cell.editors;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import jclass.cell.*;
import jclass.cell.renderers.ButtonCellRenderer;
import jclass.util.JCListenerList;

public class ButtonCellEditor extends ButtonCellRenderer
    implements CellEditor
{

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        data = obj;
        enabled = cellinfo.isEnabled();
        if(enabled)
        {
            support.fireStopEditing(new CellEditorEvent(this));
            ActionEvent actionevent = new ActionEvent(this, 0, (data instanceof String) ? (String)data : "ButtonClick", 0);
            ActionListener actionlistener;
            for(Enumeration enumeration = JCListenerList.elements(action_listeners); enumeration.hasMoreElements(); actionlistener.actionPerformed(actionevent))
                actionlistener = (ActionListener)enumeration.nextElement();

            action(null, this);
        }
    }

    public Component getComponent()
    {
        return null;
    }

    public Object getCellEditorValue()
    {
        return data;
    }

    public boolean isModified()
    {
        return enabled;
    }

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

    public void addActionListener(ActionListener actionlistener)
    {
        action_listeners = JCListenerList.add(action_listeners, actionlistener);
    }

    public void removeActionListener(ActionListener actionlistener)
    {
        action_listeners = JCListenerList.remove(action_listeners, actionlistener);
    }

    public boolean action(Event event, Object obj)
    {
        return true;
    }

    public ButtonCellEditor()
    {
        support = new CellEditorSupport();
        enabled = true;
    }

    protected CellEditorSupport support;
    protected JCListenerList action_listeners;
    protected Object data;
    protected boolean enabled;
}
