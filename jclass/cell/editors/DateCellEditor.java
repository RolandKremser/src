// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DateCellEditor.java

package jclass.cell.editors;

import java.util.Date;
import javax.swing.text.JTextComponent;
import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.cell.editors:
//            BaseCellEditor

public class DateCellEditor extends BaseCellEditor
{

    public DateCellEditor()
    {
    }

    public boolean stopCellEditing()
    {
        String s = getText();
        boolean flag = false;
        Date date;
        if(s.length() == 0)
        {
            date = null;
            flag = true;
        } else
        {
            try
            {
                date = new Date(s.trim());
                flag = true;
            }
            catch(Exception _ex)
            {
                date = null;
                flag = false;
            }
        }
        ValidateEvent validateevent = new ValidateEvent(this, super.data, date, flag);
        return fireValidated(validateevent);
    }

    public Object getCellEditorValue()
    {
        String s = getText();
        Date date;
        if(s.length() == 0)
            date = null;
        else
            try
            {
                date = new Date(s);
            }
            catch(Exception _ex)
            {
                return null;
            }
        return date;
    }
}
