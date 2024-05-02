// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SqlTimestampCellEditor.java

package jclass.cell.editors;

import java.sql.Timestamp;
import javax.swing.text.JTextComponent;
import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.cell.editors:
//            BaseCellEditor

public class SqlTimestampCellEditor extends BaseCellEditor
{

    public SqlTimestampCellEditor()
    {
    }

    public boolean stopCellEditing()
    {
        String s = getText();
        boolean flag = false;
        Timestamp timestamp;
        if(s.length() == 0)
        {
            timestamp = null;
            flag = true;
        } else
        {
            if(s.indexOf(":") < 0)
                s = s + " 00:00:00.0";
            try
            {
                timestamp = Timestamp.valueOf(s.trim());
                flag = true;
            }
            catch(Exception _ex)
            {
                timestamp = null;
                flag = false;
            }
        }
        ValidateEvent validateevent = new ValidateEvent(this, super.data, timestamp, flag);
        return fireValidated(validateevent);
    }

    public Object getCellEditorValue()
    {
        String s = getText();
        Timestamp timestamp;
        if(s.length() == 0)
        {
            timestamp = null;
        } else
        {
            if(s.indexOf(":") < 0)
                s = s + " 00:00:00.0";
            try
            {
                timestamp = Timestamp.valueOf(s.trim());
            }
            catch(Exception _ex)
            {
                return null;
            }
        }
        return timestamp;
    }
}
