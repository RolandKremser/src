// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LongCellEditor.java

package jclass.cell.editors;

import javax.swing.text.JTextComponent;
import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.cell.editors:
//            BaseCellEditor

public class LongCellEditor extends BaseCellEditor
{

    public LongCellEditor()
    {
    }

    public boolean stopCellEditing()
    {
        String s = getText();
        boolean flag = false;
        Long long1;
        if(s.length() == 0)
        {
            long1 = null;
            flag = true;
        } else
        {
            try
            {
                long1 = Long.valueOf(s.trim());
                flag = true;
            }
            catch(Exception _ex)
            {
                long1 = null;
                flag = false;
            }
        }
        ValidateEvent validateevent = new ValidateEvent(this, super.data, long1, flag);
        return fireValidated(validateevent);
    }

    public Object getCellEditorValue()
    {
        String s = getText();
        Long long1;
        if(s.length() == 0)
            long1 = null;
        else
            try
            {
                long1 = Long.valueOf(s.trim());
            }
            catch(Exception _ex)
            {
                return null;
            }
        return long1;
    }
}
