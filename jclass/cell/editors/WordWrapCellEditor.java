// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WordWrapCellEditor.java

package jclass.cell.editors;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import jclass.cell.*;
import jclass.cell.CellEditor;
import jclass.util.JCEnvironment;
import jclass.util.JCWordWrap;

public class WordWrapCellEditor extends JScrollPane
    implements CellEditor, KeyListener
{

    public WordWrapCellEditor()
    {
        support = new CellEditorSupport();
        selectAll = false;
        editable = true;
        validate_support = new ValidateSupport();
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
        cellInfo = cellinfo;
        data = obj;
        selectAll = cellinfo.getSelectAll();
        editable = cellinfo.isEditable();
        text.setEditable(editable);
        if(!text.isEditable())
            selectAll = false;
        if(data != null)
        {
            java.awt.FontMetrics fontmetrics = cellinfo.getFontMetrics();
            Insets insets = cellinfo.getBorderInsets();
            int i = cellinfo.getDrawingArea().width + insets.left + insets.right;
            String s = data.toString();
            if((JCEnvironment.getOS() == 1 || JCEnvironment.getJavaVersion() == 102) && (char)initialevent.getKey() >= ' ' && (char)initialevent.getKey() <= '~')
                s = s + String.valueOf((char)initialevent.getKey());
            setText(JCWordWrap.wrapText(s, fontmetrics, i - 25, "\n", true));
        } else
        {
            setText("");
        }
        originalText = getText();
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
        if(editable)
            return JCWordWrap.replace(getText(), "\n", "");
        else
            return data;
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
        setText(originalText);
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

    public void selectAllText()
    {
        if(!isDisplayable())
            return;
        if(selectAll)
        {
            text.setCaretPosition(0);
            text.setCaretPosition(data != null ? data.toString().length() : 0);
            text.selectAll();
            return;
        } else
        {
            text.setCaretPosition(data != null ? data.toString().length() : 0);
            return;
        }
    }

    String unwrapText(String s)
    {
        return JCWordWrap.replace(s, "\n", "");
    }

    String wrapText(String s)
    {
        String s1 = "";
        if(s != null)
        {
            java.awt.FontMetrics fontmetrics = cellInfo.getFontMetrics();
            Insets insets = cellInfo.getBorderInsets();
            int i = cellInfo.getDrawingArea().width + insets.left + insets.right;
            s1 = JCWordWrap.wrapText(s, fontmetrics, i - 25, "\n", true);
        }
        return s1;
    }

    public void show()
    {
        super.show();
        selectAllText();
    }

    public void setBounds(int i, int j, int k, int l)
    {
        super.setBounds(i, j, k, l);
        setText(wrapText(unwrapText(getText())));
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
    protected CellInfo cellInfo;
    protected String originalText;
    protected InitialEvent initialEvent;
    protected CellEditorSupport support;
    protected boolean selectAll;
    protected boolean editable;
    protected ValidateSupport validate_support;
    protected JTextArea text;
    final int VERTICAL_SB_WIDTH = 25;
    protected KeyModifier keys[];
}
