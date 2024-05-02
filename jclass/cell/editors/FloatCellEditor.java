// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FloatCellEditor.java

package jclass.cell.editors;

import javax.swing.text.JTextComponent;
import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.cell.editors:
//            BaseCellEditor

public class FloatCellEditor extends BaseCellEditor
{

    public FloatCellEditor()
    {
    }

    public boolean stopCellEditing()
    {
        String s = getText();
        boolean flag = false;
        Float float1;
        if(s.length() == 0)
        {
            float1 = null;
            flag = true;
        } else
        {
            try
            {
                if(s.trim().equalsIgnoreCase("infinity"))
                    float1 = new Float((1.0F / 0.0F));
                else
                    float1 = Float.valueOf(s.trim());
                flag = true;
            }
            catch(Exception _ex)
            {
                float1 = null;
                flag = false;
            }
        }
        ValidateEvent validateevent = new ValidateEvent(this, super.data, float1, flag);
        return fireValidated(validateevent);
    }

    public Object getCellEditorValue()
    {
        String s = getText();
        Float float1;
        if(s.length() == 0)
            float1 = null;
        else
            try
            {
                float1 = Float.valueOf(s.trim());
            }
            catch(Exception _ex)
            {
                return null;
            }
        return float1;
    }
}
