// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Jump.java

package jclass.table3;

import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;

// Referenced classes of package jclass.table3:
//            Clip, JCCellRange, Table, TableScrollbar, 
//            JCAdjustmentEvent

class Jump
{

    static int scroll(TableScrollbar tablescrollbar, JCAdjustmentEvent jcadjustmentevent, int i)
    {
        if(tablescrollbar.getOrientation() == 0)
            return scrollHoriz(tablescrollbar, jcadjustmentevent, i);
        else
            return scrollVert(tablescrollbar, jcadjustmentevent, i);
    }

    private static int newColumnPosition(Clip clip, int i)
    {
        return clip.table.columnPosition(i) - clip.xOffset();
    }

    private static int newRowPosition(Clip clip, int i)
    {
        return clip.table.rowPosition(i) - clip.yOffset();
    }

    private static int scrollHoriz(TableScrollbar tablescrollbar, JCAdjustmentEvent jcadjustmentevent, int i)
    {
        Table table = tablescrollbar.table;
        JCCellRange jccellrange = tablescrollbar.saveRange;
        if(!table.getVisibleColumns(jccellrange))
            return i;
        int j = jccellrange.start_column;
        int k = jccellrange.end_column;
        Clip clip = table.clip;
        int l = j;
        if(jcadjustmentevent.getAdjustmentType() == 2)
        {
            if(table.columnPosition(j) >= clip.horiz_origin + table.frozenColumnWidth())
                l = j - 1;
            else
                return table.getColumnVisibleValue(j);
        } else
        if(jcadjustmentevent.getAdjustmentType() == 1)
            l = j + 1;
        else
        if(jcadjustmentevent.getAdjustmentType() == 3)
        {
            l = table.getColumn(table.columnPosition(j) - clip.width);
            if(l < 0)
                l = 0;
            else
                for(; table.getColumn(table.columnPosition(l) + clip.width) < j; l++);
            if(l == j)
                l--;
        } else
        if(jcadjustmentevent.getAdjustmentType() == 4)
        {
            l = k;
            if(l == j)
                l++;
        } else
        {
            if(i == 0)
                return table.getColumnVisibleValue(table.frozen_columns);
            if(tablescrollbar.atEnd())
                return table.getColumnVisibleValue(table.columns - 1);
            l = table.getColumn(i + clip.xOffset());
        }
        if(l < table.frozen_columns)
            l = table.frozen_columns;
        else
        if(table.getColumn(table.columnPosition(l) + clip.width) > table.columns - 1)
            return table.getColumnVisibleValue(table.columns - 1);
        return newColumnPosition(clip, l);
    }

    private static int scrollVert(TableScrollbar tablescrollbar, JCAdjustmentEvent jcadjustmentevent, int i)
    {
        Table table = tablescrollbar.table;
        JCCellRange jccellrange = tablescrollbar.saveRange;
        if(!table.getVisibleRows(jccellrange))
            return i;
        int j = jccellrange.start_row;
        int k = jccellrange.end_row;
        Clip clip = table.clip;
        int l = j;
        if(jcadjustmentevent.getAdjustmentType() == 2)
        {
            if(table.rowPosition(j) >= clip.vert_origin + table.frozenRowHeight())
                l = j - 1;
            else
                return table.getRowVisibleValue(j);
        } else
        if(jcadjustmentevent.getAdjustmentType() == 1)
            l = j + 1;
        else
        if(jcadjustmentevent.getAdjustmentType() == 3)
        {
            l = table.getRow(table.rowPosition(j) - clip.height);
            if(l < 0)
                l = 0;
            else
                for(; table.getRow(table.rowPosition(l) + clip.height) < j; l++);
            if(l == j)
                l--;
        } else
        if(jcadjustmentevent.getAdjustmentType() == 4)
        {
            l = k;
            if(l == j)
                l++;
        } else
        {
            if(i == 0)
                return table.getRowVisibleValue(table.frozen_rows);
            if(tablescrollbar.atEnd())
                return table.getRowVisibleValue(table.rows - 1);
            l = table.getRow(i + clip.yOffset());
        }
        if(l < table.frozen_rows)
            l = table.frozen_rows;
        else
        if(table.getRow(table.rowPosition(l) + clip.height) > table.rows - 1)
            return table.getRowVisibleValue(table.rows - 1);
        return newRowPosition(clip, l);
    }

    Jump()
    {
    }
}
