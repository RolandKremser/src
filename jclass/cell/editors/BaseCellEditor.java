// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseCellEditor.java

package jclass.cell.editors;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import jclass.cell.*;
import jclass.cell.CellEditor;
import jclass.util.JCEnvironment;

public class BaseCellEditor extends JTextField
    implements CellEditor, ValidateInterface, KeyListener, MouseListener
{

    public BaseCellEditor()
    {
        support = new CellEditorSupport();
        selectAll = false;
        validate_support = new ValidateSupport();
        addKeyListener(this);
        addMouseListener(this);
        setDoubleBuffered(true);
        if(reservedKeys == null)
        {
            reservedKeys = new KeyModifier[4];
            reservedKeys[0] = new KeyModifier(37);
            reservedKeys[1] = new KeyModifier(39);
            reservedKeys[2] = new KeyModifier(36);
            reservedKeys[3] = new KeyModifier(35);
        }
    }

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        initialEvent = initialevent;
        data = obj;
        selectAll = cellinfo.getSelectAll();
        setEditable(cellinfo.isEditable());
        if(!isEditable())
            selectAll = false;
        if(initialevent.getEventType() == 2 && data != null)
            caretPosition = data.toString().length();
        else
            caretPosition = -1;
        initData();
        selectAllText();
        if(JCEnvironment.getOS() == 1 || JCEnvironment.getJavaVersion() == 102)
        {
            if((char)initialevent.getKey() >= ' ' && (char)initialevent.getKey() <= '~')
                setText(getText() + String.valueOf((char)initialevent.getKey()));
            else
                setText(getText());
            caretPosition++;
        }
        setBackground(cellinfo.getBackground());
        setForeground(cellinfo.getForeground());
        if(cellinfo.isEditable())
            setBackground(cellinfo.getBackground().brighter());
        setSelectedTextColor(SystemColor.textHighlightText);
        setSelectionColor(SystemColor.textHighlight);
    }

    public Component getComponent()
    {
        return this;
    }

    public Object getCellEditorValue()
    {
        return getText();
    }

    public boolean isModified()
    {
        if(data == null)
            return getCellEditorValue() != null;
        return !data.equals(getCellEditorValue());
    }

    protected boolean fireValidated(ValidateEvent validateevent)
    {
        boolean flag = validate_support.fireValidateEvents(validateevent);
        if(flag)
            show();
        return flag;
    }

    public boolean stopCellEditing()
    {
        String s = getText();
        ValidateEvent validateevent = new ValidateEvent(this, data, s, true);
        return fireValidated(validateevent);
    }

    public void cancelCellEditing()
    {
        initData();
        caretPosition = data != null ? data.toString().length() : 0;
        show();
    }

    public Dimension getPreferredSize(CellInfo cellinfo, Object obj)
    {
        return preferredSize();
    }

    public KeyModifier[] getReservedKeys()
    {
        return reservedKeys;
    }

    public void show()
    {
        super.show();
        if(caretPosition == -1)
        {
            String s = getText();
            if(getFont() == null)
                return;
            FontMetrics fontmetrics = getFontMetrics(getFont());
            int i = 5;
            int j;
            for(j = 0; j < s.length(); j++)
            {
                i += fontmetrics.charWidth(s.charAt(j));
                if(i > initialEvent.getX())
                    break;
            }

            select(j, j);
        } else
        {
            caretPosition = Math.min(caretPosition, getText().length());
            select(caretPosition, caretPosition);
        }
        selectAllText();
    }

    public void addCellEditorListener(CellEditorListener celleditorlistener)
    {
        support.addCellEditorListener(celleditorlistener);
    }

    public void removeCellEditorListener(CellEditorListener celleditorlistener)
    {
        support.removeCellEditorListener(celleditorlistener);
    }

    public void processKeyEvent(KeyEvent keyevent)
    {
        FocusManager focusmanager = FocusManager.getCurrentManager();
        FocusManager.disableSwingFocusManager();
        super.processKeyEvent(keyevent);
        FocusManager.setCurrentManager(focusmanager);
    }

    public void keyPressed(KeyEvent keyevent)
    {
        int i = keyevent.getKeyCode();
        switch(i)
        {
        case 10: // '\n'
            support.fireStopEditing(new CellEditorEvent(keyevent));
            return;

        case 27: // '\033'
            support.fireCancelEditing(new CellEditorEvent(keyevent));
            return;
        }
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    public void keyReleased(KeyEvent keyevent)
    {
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    void initData()
    {
        setText(data != null ? data.toString() : "");
    }

    public void selectAllText()
    {
        if(selectAll && isDisplayable())
        {
            try
            {
                setCaretPosition(0);
                setCaretPosition(data != null ? data.toString().length() : 0);
            }
            catch(Exception _ex) { }
            selectAll();
        }
    }

    public void addValidateListener(ValidateListener validatelistener)
    {
        validate_support.addValidateListener(validatelistener);
    }

    public void removeValidateListener(ValidateListener validatelistener)
    {
        validate_support.removeValidateListener(validatelistener);
    }

    protected Object data;
    protected int caretPosition;
    protected InitialEvent initialEvent;
    protected CellEditorSupport support;
    protected boolean selectAll;
    protected KeyModifier reservedKeys[];
    protected ValidateSupport validate_support;
}
