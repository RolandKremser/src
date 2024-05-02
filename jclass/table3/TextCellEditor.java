// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TextCellEditor.java

package jclass.table3;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.text.JTextComponent;
import jclass.cell.*;
import jclass.cell.editors.BaseCellEditor;
import jclass.cell.editors.StringCellEditor;
import jclass.util.JCEnvironment;

// Referenced classes of package jclass.table3:
//            JCTblEnum, TableCellInfo

public class TextCellEditor extends StringCellEditor
{

    public TextCellEditor()
    {
    }

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        info = (TableCellInfo)cellinfo;
        if(initialevent.getEventType() == 2 && (initialevent instanceof BaseInitialEvent))
        {
            BaseInitialEvent baseinitialevent = (BaseInitialEvent)initialevent;
            baseinitialevent.setKey(convertKey((char)baseinitialevent.getKey()));
        }
        super.initialize(initialevent, cellinfo, obj);
        if(JCEnvironment.getVendorString().startsWith("Microsoft"))
        {
            if((char)initialevent.getKey() >= ' ' && (char)initialevent.getKey() <= '~')
                setText(getText() + String.valueOf((char)initialevent.getKey()));
            else
                setText(getText());
            super.caretPosition++;
        }
    }

    public void keyPressed(KeyEvent keyevent)
    {
        super.keyPressed(keyevent);
        if(JCEnvironment.getOS() != 1 || JCEnvironment.getJavaVersion() < 110)
            return;
        char c = keyevent.getKeyChar();
        if(c >= ' ' && c <= '~')
        {
            int i = info.getMaxLength();
            if(i < 0x7fffffff)
            {
                int k = getText().length();
                if(k >= i)
                    keyevent.consume();
            }
        }
        int j = info.getStringCase();
        if(j == 2)
        {
            keyevent.setKeyChar(Character.toUpperCase(keyevent.getKeyChar()));
            return;
        }
        if(j == 1)
        {
            keyevent.setModifiers(0);
            keyevent.setKeyChar(Character.toLowerCase(keyevent.getKeyChar()));
        }
    }

    public void keyTyped(KeyEvent keyevent)
    {
        char c = keyevent.getKeyChar();
        if(c >= ' ' && c <= '~')
        {
            int i = info.getMaxLength();
            if(i < 0x7fffffff)
            {
                int k = getText().length();
                if(k >= i)
                    keyevent.consume();
            }
        }
        int j = info.getStringCase();
        if(j == 2)
        {
            keyevent.setModifiers(1);
            keyevent.setKeyChar(Character.toUpperCase(keyevent.getKeyChar()));
            return;
        }
        if(j == 1)
        {
            keyevent.setModifiers(0);
            keyevent.setKeyChar(Character.toLowerCase(keyevent.getKeyChar()));
        }
    }

    public char convertKey(char c)
    {
        int i = info.getStringCase();
        if(i == 2)
            return Character.toUpperCase(c);
        if(i == 1)
            return Character.toLowerCase(c);
        else
            return c;
    }

    protected TableCellInfo info;
}
