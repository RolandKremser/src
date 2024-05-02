// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CheckboxEditor.java

package jclass.cell.editors;

import java.awt.*;
import java.awt.event.*;
import jclass.cell.*;
import jclass.cell.renderers.CheckboxCellRenderer;

public class CheckboxEditor extends Canvas
    implements CellEditor, KeyListener, MouseListener
{

    public CheckboxEditor()
    {
        editable = true;
        renderer = new CheckboxCellRenderer();
        support = new CellEditorSupport();
        addKeyListener(this);
        addMouseListener(this);
    }

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        cellInfo = cellinfo;
        data = obj;
        initData();
        editable = cellinfo.isEditable();
        if(initialevent.getEventType() == 1)
            toggleValue();
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void paint(Graphics g)
    {
        Rectangle rectangle = getBounds();
        g.setColor(cellInfo.isEditable() ? cellInfo.getBackground().brighter() : cellInfo.getBackground());
        g.fillRect(0, 0, rectangle.width, rectangle.height);
        int i = cellInfo.getMarginInsets().left + cellInfo.getBorderInsets().left;
        int j = cellInfo.getMarginInsets().top + cellInfo.getBorderInsets().top;
        g.translate(i, j);
        renderer.draw(g, cellInfo, getCellEditorValue(), false);
        g.translate(-i, -j);
        Utilities.drawBorder(g, 8, 2, 0, 0, rectangle.width, rectangle.height, Color.black, Color.white);
    }

    public boolean isFocusTraversable()
    {
        return true;
    }

    public Object getCellEditorValue()
    {
        return new Boolean(currentValue);
    }

    public Component getComponent()
    {
        return this;
    }

    public boolean isModified()
    {
        return ((Boolean)data).booleanValue() != currentValue;
    }

    public boolean stopCellEditing()
    {
        return true;
    }

    public void cancelCellEditing()
    {
        initData();
        repaint();
    }

    public Dimension getPreferredSize(CellInfo cellinfo, Object obj)
    {
        Rectangle rectangle = cellinfo.getDrawingArea();
        return new Dimension(rectangle.width, rectangle.height);
    }

    public KeyModifier[] getReservedKeys()
    {
        return keys;
    }

    public void addCellEditorListener(CellEditorListener celleditorlistener)
    {
        support.addCellEditorListener(celleditorlistener);
    }

    public void removeCellEditorListener(CellEditorListener celleditorlistener)
    {
        support.removeCellEditorListener(celleditorlistener);
    }

    void initData()
    {
        currentValue = ((Boolean)data).booleanValue();
    }

    void toggleValue()
    {
        if(editable)
            currentValue = !currentValue;
    }

    public void keyPressed(KeyEvent keyevent)
    {
        switch(keyevent.getKeyCode())
        {
        case 10: // '\n'
            support.fireStopEditing(new CellEditorEvent(keyevent));
            return;

        case 27: // '\033'
            support.fireCancelEditing(new CellEditorEvent(keyevent));
            return;

        case 32: // ' '
            toggleValue();
            repaint();
            return;
        }
        if(!keyevent.isActionKey() && Character.isLetterOrDigit(keyevent.getKeyChar()))
        {
            toggleValue();
            repaint();
        }
    }

    public void keyReleased(KeyEvent keyevent)
    {
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void mousePressed(MouseEvent mouseevent)
    {
        toggleValue();
        repaint();
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
        setCursor(Cursor.getDefaultCursor());
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    protected Object data;
    protected boolean currentValue;
    protected boolean editable;
    protected CellRenderer renderer;
    protected CellInfo cellInfo;
    protected CellEditorSupport support;
    protected static final int BORDER_SIZE = 2;
    protected KeyModifier keys[];
}
