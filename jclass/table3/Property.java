// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Property.java

package jclass.table3;

import java.awt.Component;
import java.awt.Point;
import java.io.Serializable;

// Referenced classes of package jclass.table3:
//            Chain, Clip, DrawColumn, DrawRow, 
//            EditHandler, JCCellPosition, JCCellRange, JCTblEnum, 
//            Layout, PropertyEnum, Select, Size, 
//            Span, Table

class Property
    implements Serializable
{

    public Property(Table table1)
    {
        table = table1;
    }

    void set(int i)
    {
        set(i, -999, -999);
    }

    void set(int i, int j, int k)
    {
        if(!table.isDisplayable())
            return;
        if(table.needs_recalc && (i & 0x40) != 0)
        {
            table.row_heights = new Chain();
            table.column_widths = new Chain();
            table.column_label_height = Size.getColumnLabelMaxHeight(table, -998, 0);
            table.row_label_width = Size.getRowLabelMaxWidth(table, -998, 0);
            Size.setDimensions(table, 7, table.rows, table.columns, -998, 0, -998, 0, true);
            Size.getCellTotalWidth(table);
            Size.getCellTotalHeight(table);
            i &= 0xffc73f7f;
        }
        if(table.repaint && (i & 0x40) != 0)
        {
            if(table.needs_repaint)
                i |= 0x40000;
            if(table.needs_recalc)
                i |= 0x80000;
            table.needs_repaint = table.needs_recalc = false;
        }
        table.validateRes();
        if((i & 0x200000) != 0)
        {
            table.row_label_width = Size.getRowLabelMaxWidth(table, -998, 0);
            i |= 0x180080;
        }
        if((i & 0x4000) != 0)
        {
            table.column_label_height = Size.getColumnLabelMaxHeight(table, -998, 0);
            i |= 0x88080;
        }
        if((i & 0x40) == 0 && ((i & 0x20000) != 0 || (i & 0x8000) != 0 || (i & 0x100000) != 0 || (i & 0x2000) != 0 || (i & 0x1000) != 0 || (i & 0x20) != 0 || (i & 0x100) != 0 || (i & 2) != 0 || (i & 4) != 0 || (i & 8) != 0 || (i & 0x200) != 0))
        {
            if((i & 0x100) != 0 || (i & 2) != 0)
                resizeCells(table, old_rows, old_columns);
            if((i & 0x2000) != 0 || (i & 0x8000) != 0 || (i & 0x1000) != 0 || (i & 0x20000) != 0 || (i & 0x20) != 0 || (i & 4) != 0 || (i & 0x200) != 0)
            {
                if((i & 0x2000) == 0)
                    Size.getColumnWidths(table, -998, 0, -998, 0, null);
                Size.getCellTotalWidth(table);
            }
            if((i & 0x2000) != 0 || (i & 0x100000) != 0 || (i & 0x1000) != 0 || (i & 0x20000) != 0 || (i & 0x20) != 0 || (i & 8) != 0 || (i & 0x200) != 0)
            {
                if((i & 0x1000) == 0)
                    Size.getRowHeights(table, -998, 0, -998, 0, null);
                Size.getCellTotalHeight(table);
            }
            i |= 0xc0000;
        }
        if((i & 0x10000) != 0)
        {
            if(!table.repaint)
                table.needs_repaint = true;
            else
            if(j == -998 || j == -997)
                DrawColumn.draw(table, k);
            else
            if(k == -998 || k == -997)
                DrawRow.draw(table, j);
            else
                table.paint(j, k);
            if(i == 0x10000)
                return;
        }
        if(table.span.origSpanListSize() > 0)
        {
            table.span.copy();
            table.span.adjust();
        }
        boolean flag = table.containerHeight() == 0 || (i & 0x800000) != 0 || (i & 0x80) != 0;
        boolean flag1 = table.containerWidth() == 0 || (i & 0x400000) != 0 || (i & 0x80) != 0;
        if(flag || flag1)
            if(!table.repaint)
            {
                table.needs_recalc = true;
                i &= 0xfff3ffff;
            } else
            if(Size.compute(table, flag1, flag))
                i |= 0xc0000;
        if(table.repaint && (i & 0x80000) != 0)
            Layout.doLayout(table);
        if(table.repaint && (i & 0x1000000) != 0)
            Select.redisplay(table);
        if(table.repaint && ((i & 0x10) != 0 || (i & 0x400) != 0))
        {
            JCCellRange jccellrange = new JCCellRange();
            if((i & 0x10) != 0)
            {
                table.getVisibleColumns(jccellrange);
                table.left_column = jccellrange.start_column;
                table.set_left_column = false;
            } else
            {
                table.getVisibleRows(jccellrange);
                table.top_row = jccellrange.start_row;
                table.set_top_row = false;
            }
        }
        util_set(i);
        if(table.repaint && ((i & 0x40000) != 0 || (i & 0x80000) != 0))
        {
            for(int l = 0; l < table.clip_list.length; l++)
                table.clip_list[l].repaint();

        }
    }

    void util_set(int i)
    {
        if(table.repaint && table.span.doSpansExist())
        {
            JCCellPosition jccellposition = new JCCellPosition();
            if(table.span.find(table.edit_row, table.edit_column, jccellposition) != -999)
            {
                table.edit_row = jccellposition.row;
                table.edit_column = jccellposition.column;
            }
        }
        int j = table.columnWidth(table.edit_column);
        int k = table.rowHeight(table.edit_row);
        if(table.repaint && ((i & 0x40000) != 0 || (i & 0x80000) != 0))
        {
            if(table.hasText())
            {
                table.editHandler.setValues(table.edit_row, table.edit_column);
                return;
            }
        } else
        if(table.repaint && table.hasText())
        {
            Point point = table.getPosition(table.edit_row, table.edit_column, null);
            if(point != null)
                table.editHandler.setSize(point.x, point.y, j, k);
        }
    }

    static void resizeCells(Table table1, int i, int j)
    {
        if(!table1.repaint)
        {
            table1.needs_recalc = true;
            return;
        }
        int k = table1.rows;
        if(table1.rows > i)
        {
            k = i;
            Size.setDimensions(table1, 4, i, j, i, table1.rows - i, -998, 0, table1.columns <= j);
        } else
        if(table1.rows < i)
        {
            k = table1.rows;
            Size.setDimensions(table1, 2, i, j, table1.rows, i - table1.rows, -998, 0, table1.columns <= j);
        }
        if(table1.columns > j)
        {
            Size.setDimensions(table1, 3, i, j, 0, k, j, table1.columns - j, true);
            return;
        }
        if(table1.columns < j)
            Size.setDimensions(table1, 1, i, j, 0, k, table1.columns, j - table1.columns, true);
    }

    int old_rows;
    int old_columns;
    Table table;
}
