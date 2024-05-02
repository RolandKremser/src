// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SqlTimeCellEditor.java

package jclass.cell.editors;

import java.sql.Time;
import javax.swing.text.JTextComponent;
import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.cell.editors:
//            BaseCellEditor

public class SqlTimeCellEditor extends BaseCellEditor
{

    public SqlTimeCellEditor()
    {
    }

    public boolean stopCellEditing()
    {
        String s = getText();
        boolean flag = false;
        Time time;
        if(s.length() == 0)
        {
            time = null;
            flag = true;
        } else
        {
            try
            {
                time = Time.valueOf(s.trim());
                flag = true;
            }
            catch(Exception _ex)
            {
                time = null;
                flag = false;
            }
        }
        ValidateEvent validateevent = new ValidateEvent(this, super.data, time, flag);
        return fireValidated(validateevent);
    }

    public Object getCellEditorValue()
    {
        String s = getText();
        Time time;
        if(s.length() == 0)
            time = null;
        else
            try
            {
                time = Time.valueOf(s.trim());
            }
            catch(Exception _ex)
            {
                return null;
            }
        return time;
    }
}
