// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DoubleCellEditor.java

package jclass.cell.editors;

import javax.swing.text.JTextComponent;
import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.cell.editors:
//            BaseCellEditor

public class DoubleCellEditor extends BaseCellEditor
{

    public DoubleCellEditor()
    {
    }

    public boolean stopCellEditing()
    {
        String s = getText();
        boolean flag = false;
        Double double1;
        if(s.length() == 0)
        {
            double1 = null;
            flag = true;
        } else
        {
            try
            {
                if(s.trim().equalsIgnoreCase("infinity"))
                    double1 = new Double((1.0D / 0.0D));
                else
                    double1 = Double.valueOf(s.trim());
                flag = true;
            }
            catch(Exception _ex)
            {
                double1 = null;
                flag = false;
            }
        }
        ValidateEvent validateevent = new ValidateEvent(this, super.data, double1, flag);
        return fireValidated(validateevent);
    }

    public Object getCellEditorValue()
    {
        String s = getText();
        Double double1;
        if(s.length() == 0)
            double1 = null;
        else
            try
            {
                if(s.trim().equalsIgnoreCase("infinity"))
                    double1 = new Double((1.0D / 0.0D));
                else
                    double1 = Double.valueOf(s.trim());
            }
            catch(Exception _ex)
            {
                return null;
            }
        return double1;
    }
}
