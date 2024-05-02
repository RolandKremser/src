// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCell.java

package jclass.table3;

import java.awt.*;
import java.io.Serializable;
import jclass.cell.CellData;
import jclass.cell.CellRenderer;

// Referenced classes of package jclass.table3:
//            Clip, JCCellRange, JCTblEnum, JDKSupport, 
//            Span, Table, TableCellInfo, TableDataView

public class JCCell
    implements Serializable
{

    static final boolean getBounds(Table table, Object obj, int i, int j, Rectangle rectangle)
    {
        if(table.span.doSpansExist())
        {
            Dimension dimension = new Dimension();
            JCCellRange jccellrange = new JCCellRange();
            if(table.span.find(i, j, jccellrange, dimension) != -999)
            {
                Point point = new Point(0, 0);
                table.getPosition(jccellrange.start_row, jccellrange.start_column, point);
                JDKSupport.setBounds(rectangle, point.x, point.y, dimension.width, dimension.height);
                return true;
            }
        }
        table.getBoundsInternal(i, j, rectangle);
        return false;
    }

    static final int getHeight(Table table, int i, int j)
    {
        return getHeight(table, null, table.dataView.getData(i, j), i, j, table.getFont(i, j));
    }

    private static final int getHeight(FontMetrics fontmetrics, String s)
    {
        if(s == null || s.length() == 0)
            return 0;
        int i = 1;
        for(int j = 0; j < s.length(); j++)
            if(s.charAt(j) == '\n')
                i++;

        return fontmetrics.getHeight() * i;
    }

    static final int getHeight(Table table, FontMetrics fontmetrics, Object obj, int i, int j, Font font)
    {
        if(obj == null)
            return 0;
        if(fontmetrics == null)
            fontmetrics = table.getToolkit().getFontMetrics(font);
        if(obj instanceof Component)
            return ((Component)obj).getPreferredSize().height + 1;
        Object obj1 = obj;
        if(obj instanceof CellData)
            obj1 = ((CellData)obj).getData();
        Clip clip = Clip.find(table, i, j);
        TableCellInfo tablecellinfo = new TableCellInfo(table, clip, i, j, obj);
        Dimension dimension = table.getDataView().getCellRenderer(i, j, obj).getPreferredSize(tablecellinfo, obj1);
        if(dimension == null)
        {
            String s = obj1.toString();
            return getHeight(fontmetrics, s);
        } else
        {
            return dimension.height;
        }
    }

    private static final int getWidth(FontMetrics fontmetrics, String s)
    {
        if(s == null || s.length() == 0)
            return 0;
        if(s.indexOf('\n') != -1)
        {
            int i = 0;
            int k = 0;
            int j;
            while((j = s.indexOf('\n', i)) != -1) 
            {
                k = Math.max(k, fontmetrics.stringWidth(s.substring(i, j)));
                i = j + 1;
            }
            return Math.max(k, fontmetrics.stringWidth(s.substring(i, s.length())));
        } else
        {
            return fontmetrics.stringWidth(s);
        }
    }

    static final int getWidth(Table table, int i, int j)
    {
        return getWidth(table, null, table.dataView.getData(i, j), i, j, table.getFont(i, j));
    }

    static final int getWidth(Table table, FontMetrics fontmetrics, Object obj, int i, int j, Font font)
    {
        if(obj == null)
            return 0;
        if(fontmetrics == null)
            fontmetrics = table.getToolkit().getFontMetrics(font);
        if(obj instanceof Component)
            return ((Component)obj).getPreferredSize().width + 1;
        Object obj1 = obj;
        if(obj instanceof CellData)
            obj1 = ((CellData)obj).getData();
        Clip clip = Clip.find(table, i, j);
        TableCellInfo tablecellinfo = new TableCellInfo(table, clip, i, j, obj);
        Dimension dimension = table.getDataView().getCellRenderer(i, j, obj).getPreferredSize(tablecellinfo, obj1);
        if(dimension == null)
        {
            String s = obj1.toString();
            return getWidth(fontmetrics, s);
        } else
        {
            return dimension.width;
        }
    }

    public JCCell()
    {
    }
}
