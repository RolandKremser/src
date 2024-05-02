// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ByteCellEditor.java

package jclass.cell.editors;

import javax.swing.text.JTextComponent;
import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.cell.editors:
//            BaseCellEditor

public class ByteCellEditor extends BaseCellEditor
{

    public ByteCellEditor()
    {
    }

    public boolean stopCellEditing()
    {
        String s = getText();
        boolean flag = false;
        Byte byte1;
        if(s.length() == 0)
        {
            byte1 = null;
            flag = true;
        } else
        {
            try
            {
                byte1 = Byte.valueOf(s.trim());
                flag = true;
            }
            catch(Exception _ex)
            {
                byte1 = null;
                flag = false;
            }
        }
        ValidateEvent validateevent = new ValidateEvent(this, super.data, byte1, flag);
        return fireValidated(validateevent);
    }

    public Object getCellEditorValue()
    {
        String s = getText();
        Byte byte1;
        if(s.length() == 0)
            byte1 = null;
        else
            try
            {
                byte1 = Byte.valueOf(s.trim());
            }
            catch(Exception _ex)
            {
                return null;
            }
        return byte1;
    }
}
