// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCStringCellData.java

package jclass.table3;

import java.util.Vector;
import jclass.cell.*;
import jclass.util.JCString;

// Referenced classes of package jclass.table3:
//            JCStringCellEditor, JCStringCellRenderer, Table

public class JCStringCellData
    implements CellData
{

    public JCStringCellData()
    {
        editor = new JCStringCellEditor();
    }

    public JCStringCellData(JCString jcstring, Table table1)
    {
        data = JCString.parse(table1, jcstring.getString());
        data.setURLoffset(0, 0);
        table = table1;
        editor = new JCStringCellEditor();
    }

    public JCStringCellData(String s, Table table1)
    {
        data = (JCString)JCString.parse(table1, s).clone();
        data.setURLoffset(0, 0);
        table = table1;
        editor = new JCStringCellEditor();
    }

    public CellEditor getEditor()
    {
        return editor;
    }

    public CellRenderer getRenderer()
    {
        return renderer;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object obj)
    {
        if(obj instanceof JCString)
        {
            data = (JCString)obj;
            return;
        }
        if(table != null)
            data = JCString.parse(table, (obj instanceof String) ? (String)obj : obj.toString());
    }

    JCString data;
    Table table;
    static JCStringCellEditor editor = new JCStringCellEditor();
    static JCStringCellRenderer renderer = new JCStringCellRenderer();

}
