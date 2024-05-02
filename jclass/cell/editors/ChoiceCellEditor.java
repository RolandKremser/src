// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChoiceCellEditor.java

package jclass.cell.editors;

import java.awt.*;
import java.awt.event.*;
import jclass.cell.*;

public class ChoiceCellEditor extends Choice
    implements CellEditor, ItemListener, KeyListener
{

    public ChoiceCellEditor()
    {
        support = new CellEditorSupport();
        ignoreEnterKey = false;
        values = null;
    }

    public ChoiceCellEditor(String as[])
    {
        this(as, null);
    }

    public ChoiceCellEditor(String as[], int ai[])
    {
        support = new CellEditorSupport();
        ignoreEnterKey = false;
        for(int i = 0; i < as.length; i++)
            addItem(as[i]);

        values = ai;
        addItemListener(this);
        addKeyListener(this);
    }

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        if(initialevent.getEventType() == 2 && initialevent.getKey() == 10)
            ignoreEnterKey = true;
        if(obj instanceof Number)
            firstSelection = newSelection = getIndexFromValue(((Number)obj).intValue());
        select(newSelection);
    }

    public Component getComponent()
    {
        return this;
    }

    public Object getCellEditorValue()
    {
        return new Integer(values[newSelection]);
    }

    public boolean isModified()
    {
        return firstSelection != newSelection;
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
        return getComponent().preferredSize();
    }

    public KeyModifier[] getReservedKeys()
    {
        return null;
    }

    public void itemStateChanged(ItemEvent itemevent)
    {
        newSelection = getSelectedIndex();
        support.fireStopEditing(new CellEditorEvent(itemevent));
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void keyPressed(KeyEvent keyevent)
    {
    }

    public void keyReleased(KeyEvent keyevent)
    {
        if(keyevent.getKeyCode() == 27)
            support.fireStopEditing(new CellEditorEvent(keyevent));
        else
        if(keyevent.getKeyCode() == 10 && !ignoreEnterKey)
            support.fireStopEditing(new CellEditorEvent(keyevent));
        ignoreEnterKey = false;
    }

    public void addCellEditorListener(CellEditorListener celleditorlistener)
    {
        support.addCellEditorListener(celleditorlistener);
    }

    public void removeCellEditorListener(CellEditorListener celleditorlistener)
    {
        support.removeCellEditorListener(celleditorlistener);
    }

    private int getIndexFromValue(int i)
    {
        if(values == null)
            return i;
        for(int j = 0; j < values.length; j++)
            if(values[j] == i)
                return j;

        return -1;
    }

    protected int firstSelection;
    protected int newSelection;
    protected CellEditorSupport support;
    protected int values[];
    protected boolean ignoreEnterKey;
}
