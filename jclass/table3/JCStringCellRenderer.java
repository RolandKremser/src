// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCStringCellRenderer.java

package jclass.table3;

import java.awt.*;
import jclass.cell.CellInfo;
import jclass.cell.CellRenderer;
import jclass.util.JCString;

// Referenced classes of package jclass.table3:
//            Clip, JCPrintTable, Table, TableCellInfo

public class JCStringCellRenderer
    implements CellRenderer
{

    public void draw(Graphics g, CellInfo cellinfo, Object obj, boolean flag)
    {
        Table table = ((TableCellInfo)cellinfo).getTable();
        if(table.getPrinting())
            table = ((TableCellInfo)cellinfo).clip.getPrintTable().table;
        JCString jcstring = (JCString)obj;
        Rectangle rectangle = cellinfo.getDrawingArea();
        bounds.width = rectangle.width;
        bounds.height = rectangle.height;
        g.setFont(cellinfo.getFont());
        g.setColor(flag ? cellinfo.getSelectedForeground() : cellinfo.getForeground());
        byte byte0 = 0;
        switch(cellinfo.getHorizontalAlignment())
        {
        case 1: // '\001'
            byte0 = 1;
            break;

        case 2: // '\002'
            byte0 = 2;
            break;
        }
        jcstring.draw(table, g, bounds, byte0);
    }

    public Dimension getPreferredSize(CellInfo cellinfo, Object obj)
    {
        JCString jcstring = JCString.parse(((TableCellInfo)cellinfo).getTable(), ((JCString)obj).toString2());
        return jcstring.getSize(((TableCellInfo)cellinfo).getTable(), cellinfo.getFont());
    }

    public JCStringCellRenderer()
    {
    }

    private static Rectangle bounds = new Rectangle(0, 0, 0, 0);

}
