// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringCellEditor.java

package jclass.cell.editors;


// Referenced classes of package jclass.cell.editors:
//            BaseCellEditor

public class StringCellEditor extends BaseCellEditor
{

    public StringCellEditor()
    {
    }

    public boolean isModified()
    {
        Object obj = getCellEditorValue();
        if(super.data == null || super.data.equals(""))
            return obj != null && !obj.equals("");
        return !super.data.equals(obj);
    }
}
