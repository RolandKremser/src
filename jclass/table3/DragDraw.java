// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DragDraw.java

package jclass.table3;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

// Referenced classes of package jclass.table3:
//            Draw, Clip, JCCellPosition, JCCellRange, 
//            JCTblEnum, JDKSupport, Span, Table, 
//            TableCellInfo, Widget

class DragDraw extends Draw
{

    static void cell(Table table, int i, int j, int k, int l, int i1, int j1)
    {
        if(table.rowHeight(i) == 0 || table.columnWidth(j) == 0)
            return;
        boolean flag = false;
        Clip clip = Clip.find(table, i, j);
        if(clip == null)
            return;
        if(table.span.span_list.size() > 0)
        {
            JCCellPosition jccellposition = new JCCellPosition();
            if(table.span.find(i, j, jccellposition) != -999 && (i != jccellposition.row || j != jccellposition.column))
            {
                if(table.draw_range.start_row == -999 || !table.draw_range.inside(jccellposition.row, jccellposition.column))
                    cell(table, jccellposition.row, jccellposition.column, k, l, i1, j1);
                return;
            }
        }
        if(table.component_series.size() > 0)
            flag = Widget.draw(table, i, j);
        int k1 = i;
        int l1 = j;
        if(j != -1 && k == -1 && i1 == -1)
            l1 = getTransPosition(j, l, j1);
        if(i != -1 && l == -1 && j1 == -1)
            k1 = getTransPosition(i, k, i1);
        TableCellInfo tablecellinfo = new TableCellInfo(table, clip, k1, l1);
        int i2 = table.textWidthOffset();
        int j2 = table.textHeightOffset();
        Rectangle rectangle = new Rectangle(0, 0, 0, 0);
        getPosition(table, i, j, k, l, i1, j1, rectangle);
        int k2 = rectangle.x;
        int l2 = rectangle.y;
        int i3 = rectangle.width;
        int j3 = rectangle.height;
        Rectangle rectangle1 = new Rectangle();
        JDKSupport.setBounds(rectangle1, k2 + i2, l2 + j2, Math.max(0, i3 - 2 * i2), Math.max(0, j3 - 2 * j2));
        int k3 = rectangle1.x;
        int l3 = rectangle1.y;
        int i4 = rectangle1.width;
        if(table.getMarginWidth() == 0x7fffffff)
            i4 = 0;
        int j4 = rectangle1.height;
        if(table.getMarginHeight() == 0x7fffffff)
            j4 = 0;
        tablecellinfo.isSpanned = false;
        Rectangle rectangle2 = rectangle1;
        if(!table.is_printing)
            Draw.intersect(clip.expose, rectangle2, rectangle2);
        Draw.background(tablecellinfo, k2, l2, i3, j3);
        if(table.edit_row == i && table.edit_column == j && !flag)
            Draw.drawRect(table, tablecellinfo.gc, i, j, table.getFocusRectColor());
        if(flag)
            return;
        int k4 = tablecellinfo.str_height;
        int l4 = tablecellinfo.str_width;
        boolean flag1 = l4 > i4;
        boolean flag2 = k4 > j4;
        int i5 = 0;
        int j5 = 0;
        if(flag1 || flag2)
        {
            i5 = (k2 + i3) - tablecellinfo.cellBorderWidth;
            j5 = (l2 + j3) - tablecellinfo.cellBorderWidth;
        }
        Draw.foreground(table, tablecellinfo, k3, l3, l4, k4, flag1, flag2, i5, j5, rectangle2);
        tablecellinfo = null;
    }

    protected static void getPosition(Table table, int i, int j, int k, int l, int i1, int j1, Rectangle rectangle)
    {
        if(table.getTopRow() < table.getFrozenRows())
            table.top_row = table.getFrozenRows();
        if(table.getLeftColumn() < table.getFrozenColumns())
            table.left_column = table.getFrozenColumns();
        Point point = new Point(0, 0);
        if(k == -1 && j != -1)
        {
            getTransPosition(table.getLeftColumn(), l, j1);
            table.getPosition(i, table.getLeftColumn(), point);
            if(j < table.getFrozenColumns())
            {
                point.x = 0;
                for(int k1 = 0; k1 < j; k1++)
                    point.x += table.getColumnPixelWidth(getTransPosition(k1, l, j1));

            } else
            {
                for(int l1 = table.getLeftColumn(); l1 < j; l1++)
                    point.x += table.getColumnPixelWidth(getTransPosition(l1, l, j1));

            }
            rectangle.x = point.x;
            rectangle.y = point.y;
            rectangle.width = table.getColumnPixelWidth(getTransPosition(j, l, j1));
            rectangle.height = table.getRowPixelHeight(i);
            return;
        }
        if(l == -1 && i != -1)
        {
            getTransPosition(table.getTopRow(), k, i1);
            table.getPosition(table.getTopRow(), j, point);
            if(i < table.getFrozenRows())
            {
                point.y = 0;
                for(int i2 = 0; i2 < i; i2++)
                    point.y += table.getRowPixelHeight(getTransPosition(i2, k, i1));

            } else
            {
                for(int j2 = table.getTopRow(); j2 < i; j2++)
                    point.y += table.getRowPixelHeight(getTransPosition(j2, k, i1));

            }
            rectangle.x = point.x;
            rectangle.y = point.y;
            rectangle.width = table.getColumnPixelWidth(j);
            rectangle.height = table.getRowPixelHeight(getTransPosition(i, k, i1));
            return;
        } else
        {
            table.getPosition(i, j, point);
            rectangle.x = point.x;
            rectangle.y = point.y;
            rectangle.width = table.getColumnPixelWidth(j);
            rectangle.height = table.getRowPixelHeight(i);
            return;
        }
    }

    static int getTransPosition(int i, int j, int k)
    {
        if(j < k)
        {
            if(i >= j && i < k - 1)
                return i + 1;
            if(i == k - 1)
                return j;
        } else
        {
            if(i > k && i <= j)
                return i - 1;
            if(i == k)
                return j;
        }
        return i;
    }

    DragDraw()
    {
    }
}
