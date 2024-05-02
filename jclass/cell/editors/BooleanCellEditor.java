// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BooleanCellEditor.java

package jclass.cell.editors;

import javax.swing.text.JTextComponent;
import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.cell.editors:
//            BaseCellEditor

public class BooleanCellEditor extends BaseCellEditor
{

    public BooleanCellEditor()
    {
    }

    public boolean stopCellEditing()
    {
        String s = getText();
        boolean flag = false;
        Boolean boolean1;
        if(s.length() == 0)
        {
            boolean1 = null;
            flag = true;
        } else
        if(s.equalsIgnoreCase("false") || s.equalsIgnoreCase("f"))
        {
            boolean1 = new Boolean(false);
            flag = true;
        } else
        if(s.equalsIgnoreCase("true") || s.equalsIgnoreCase("t"))
        {
            boolean1 = new Boolean(true);
            flag = true;
        } else
        {
            boolean1 = null;
            flag = false;
        }
        ValidateEvent validateevent = new ValidateEvent(this, super.data, boolean1, flag);
        return fireValidated(validateevent);
    }

    public Object getCellEditorValue()
    {
        String s = getText();
        if(s.length() == 0)
            return null;
        if(s.equalsIgnoreCase("false") || s.equalsIgnoreCase("f"))
            return new Boolean(false);
        if(s.equalsIgnoreCase("true") || s.equalsIgnoreCase("t"))
            return new Boolean(true);
        else
            return null;
    }
}
