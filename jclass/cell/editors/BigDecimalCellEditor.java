// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BigDecimalCellEditor.java

package jclass.cell.editors;

import java.math.BigDecimal;
import javax.swing.text.JTextComponent;
import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.cell.editors:
//            BaseCellEditor

public class BigDecimalCellEditor extends BaseCellEditor
{

    public BigDecimalCellEditor()
    {
    }

    public boolean stopCellEditing()
    {
        String s = getText();
        boolean flag = false;
        BigDecimal bigdecimal;
        if(s.length() == 0)
        {
            bigdecimal = null;
            flag = true;
        } else
        {
            try
            {
                bigdecimal = new BigDecimal(s.trim());
                flag = true;
            }
            catch(Exception _ex)
            {
                bigdecimal = null;
                flag = false;
            }
        }
        ValidateEvent validateevent = new ValidateEvent(this, super.data, bigdecimal, flag);
        return fireValidated(validateevent);
    }

    public Object getCellEditorValue()
    {
        String s = getText();
        BigDecimal bigdecimal;
        if(s.length() == 0)
            bigdecimal = null;
        else
            try
            {
                bigdecimal = new BigDecimal(s.trim());
            }
            catch(Exception _ex)
            {
                return null;
            }
        return bigdecimal;
    }
}
