// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MultilineCellEditor.java

package jclass.cell.editors;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.text.JTextComponent;
import jclass.cell.*;
import jclass.cell.CellEditor;
import jclass.util.JCEnvironment;

public class MultilineCellEditor extends JScrollPane
    implements CellEditor, KeyListener
{

    public MultilineCellEditor()
    {
        support = new CellEditorSupport();
        selectAll = false;
        validate_support = new ValidateSupport();
        suppressEnter = false;
        addKeyListener(this);
        text = new JTextArea();
        getViewport().add(text);
        setDoubleBuffered(true);
        text.setDoubleBuffered(true);
        text.addKeyListener(this);
    }

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        initialEvent = initialevent;
        data = obj;
        selectAll = cellinfo.getSelectAll();
        text.setEditable(cellinfo.isEditable());
        if(!text.isEditable())
            selectAll = false;
        initData();
        if(JCEnvironment.getOS() == 1 || JCEnvironment.getJavaVersion() == 102)
        {
            if((char)initialevent.getKey() >= ' ' && (char)initialevent.getKey() <= '~')
                setText(getText() + String.valueOf((char)initialevent.getKey()));
            else
                setText(getText());
        } else
        if(initialevent.getEventType() == 2)
            suppressEnter = true;
        setBackground(cellinfo.getBackground());
        setForeground(cellinfo.getForeground());
    }

    public Component getComponent()
    {
        validate();
        text.show();
        text.requestFocus();
        return this;
    }

    public Object getCellEditorValue()
    {
        return getText();
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
        boolean flag = fireValidated(validateevent);
        if(flag)
            selectAllText();
        return flag;
    }

    public boolean isModified()
    {
        if(data == null)
            return getCellEditorValue() != null;
        return !data.equals(getCellEditorValue());
    }

    public void cancelCellEditing()
    {
        initData();
        selectAllText();
    }

    public void setText(String s)
    {
        text.setText(s);
    }

    public String getText()
    {
        return text.getText();
    }

    public Dimension minimumSize()
    {
        return new Dimension(0, 0);
    }

    public Dimension getPreferredSize(CellInfo cellinfo, Object obj)
    {
        return preferredSize();
    }

    public KeyModifier[] getReservedKeys()
    {
        if(keys == null)
        {
            keys = new KeyModifier[9];
            keys[0] = new KeyModifier(10);
            keys[1] = new KeyModifier(40);
            keys[2] = new KeyModifier(38);
            keys[3] = new KeyModifier(37);
            keys[4] = new KeyModifier(39);
            keys[5] = new KeyModifier(33);
            keys[6] = new KeyModifier(34);
            keys[7] = new KeyModifier(36);
            keys[8] = new KeyModifier(35);
        }
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

    public void processKeyEvent(KeyEvent keyevent)
    {
        FocusManager focusmanager = FocusManager.getCurrentManager();
        FocusManager.disableSwingFocusManager();
        super.processKeyEvent(keyevent);
        FocusManager.setCurrentManager(focusmanager);
    }

    public void keyPressed(KeyEvent keyevent)
    {
        switch(keyevent.getKeyCode())
        {
        case 10: // '\n'
            if((keyevent.getModifiers() & 2) != 0)
            {
                support.fireStopEditing(new CellEditorEvent(keyevent));
                return;
            }
            break;

        case 27: // '\033'
            support.fireCancelEditing(new CellEditorEvent(keyevent));
            return;

        default:
            if(keyevent.getComponent() == text)
                processKeyEvent(new KeyEvent(this, keyevent.getID(), keyevent.getWhen(), keyevent.getModifiers(), keyevent.getKeyCode(), keyevent.getKeyChar()));
            break;
        }
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
        if(!isDisplayable())
            return;
        if(selectAll)
        {
            text.setCaretPosition(0);
            text.setCaretPosition(getText().length());
            text.selectAll();
            return;
        } else
        {
            text.setCaretPosition(getText().length());
            return;
        }
    }

    public void show()
    {
        super.show();
        selectAllText();
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
    protected InitialEvent initialEvent;
    protected CellEditorSupport support;
    protected boolean selectAll;
    protected ValidateSupport validate_support;
    private boolean suppressEnter;
    protected JTextArea text;
    protected KeyModifier keys[];
}
