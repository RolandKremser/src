// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShortCellEditor.java

package jclass.cell.editors;

import javax.swing.text.JTextComponent;
import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.cell.editors:
//            BaseCellEditor

public class ShortCellEditor extends BaseCellEditor
{

    public ShortCellEditor()
    {
    }

    public boolean stopCellEditing()
    {
        String s = getText();
        boolean flag = false;
        Short short1;
        if(s.length() == 0)
        {
            short1 = null;
            flag = true;
        } else
        {
            try
            {
                short1 = Short.valueOf(s.trim());
                flag = true;
            }
            catch(Exception _ex)
            {
                short1 = null;
                flag = false;
            }
        }
        ValidateEvent validateevent = new ValidateEvent(this, super.data, short1, flag);
        return fireValidated(validateevent);
    }

    public Object getCellEditorValue()
    {
        String s = getText();
        Short short1;
        if(s.length() == 0)
            short1 = null;
        else
            try
            {
                short1 = Short.valueOf(s.trim());
            }
            catch(Exception _ex)
            {
                return null;
            }
        return short1;
    }
}
