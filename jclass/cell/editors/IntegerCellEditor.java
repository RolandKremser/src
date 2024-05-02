// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntegerCellEditor.java

package jclass.cell.editors;

import javax.swing.text.JTextComponent;
import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.cell.editors:
//            BaseCellEditor

public class IntegerCellEditor extends BaseCellEditor
{

    public IntegerCellEditor()
    {
    }

    public boolean stopCellEditing()
    {
        String s = getText();
        boolean flag = false;
        Integer integer;
        if(s.length() == 0)
        {
            integer = null;
            flag = true;
        } else
        {
            try
            {
                integer = Integer.valueOf(s.trim());
                flag = true;
            }
            catch(Exception _ex)
            {
                integer = null;
                flag = false;
            }
        }
        ValidateEvent validateevent = new ValidateEvent(this, super.data, integer, flag);
        return fireValidated(validateevent);
    }

    public Object getCellEditorValue()
    {
        String s = getText();
        Integer integer;
        if(s.length() == 0)
            integer = null;
        else
            try
            {
                integer = Integer.valueOf(s.trim());
            }
            catch(Exception _ex)
            {
                return null;
            }
        return integer;
    }
}
