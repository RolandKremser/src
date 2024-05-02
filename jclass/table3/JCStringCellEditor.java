// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCStringCellEditor.java

package jclass.table3;

import javax.swing.text.JTextComponent;
import jclass.cell.CellInfo;
import jclass.cell.InitialEvent;
import jclass.cell.editors.BaseCellEditor;
import jclass.util.JCString;

// Referenced classes of package jclass.table3:
//            TextCellEditor, Table, TableCellInfo

public class JCStringCellEditor extends TextCellEditor
{

    public JCStringCellEditor()
    {
    }

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        super.info = (TableCellInfo)cellinfo;
        super.data = obj;
        JCString jcstring = (JCString)super.data;
        if(super.data != null)
            setText(jcstring.toString2());
        else
            setText("");
        if(initialevent.getEventType() == 1)
        {
            Table table = ((TableCellInfo)cellinfo).getTable();
            jcstring.setURLoffset(0, 0);
            String s = jcstring.getURL(initialevent.getX() - table.margin_width, initialevent.getY() - table.margin_height);
            if(s != null)
            {
                JCString.showURL(s, table.applet_context, table.applet);
                table.cancelEdit(true);
            }
        }
    }

    public Object getCellEditorValue()
    {
        String s = getText();
        return JCString.parse(this, s);
    }
}
