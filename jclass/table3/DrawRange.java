// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DrawRange.java

package jclass.table3;

import java.awt.Component;
import java.util.Vector;

// Referenced classes of package jclass.table3:
//            Clip, JCCellPosition, JCCellRange, JCTblEnum, 
//            Span, Table

class DrawRange
{

    static void draw_cell(Table table, int i, int j)
    {
        if(table.span.span_list.size() == 0)
        {
            table.paint(i, j);
            return;
        }
        JCCellPosition jccellposition = new JCCellPosition();
        if(table.span.find(i, j, jccellposition) != -999)
        {
            table.paint(jccellposition.row, jccellposition.column);
            return;
        } else
        {
            table.paint(i, j);
            return;
        }
    }

    static synchronized void draw(Table table, JCCellRange jccellrange)
    {
        JCCellRange jccellrange1 = new JCCellRange();
        if(!table.isVisible())
            return;
        JCCellRange jccellrange2 = table.getVisibleCells();
        if(jccellrange2 == null)
            return;
        int i4 = jccellrange2.start_row;
        int j4 = jccellrange2.end_row;
        int k4 = jccellrange2.start_column;
        int l4 = jccellrange2.end_column;
        if(jccellrange.start_row == -1 && jccellrange.end_row == table.rows - 1)
        {
            jccellrange.start_row = 0;
            jccellrange.end_row = 0x7fffffff;
        }
        if(jccellrange.start_column == -1 && jccellrange.end_column == table.columns - 1)
        {
            jccellrange.start_column = 0;
            jccellrange.end_column = 0x7fffffff;
        }
        if(jccellrange.end_column == 0x7fffffff)
        {
            if(table.frozen_rows > 0)
            {
                for(int i = 0; i < table.frozen_rows; i++)
                    if(jccellrange.inRowRange2(i))
                        draw_cell(table, i, -1);

            }
            for(int j = i4; j <= j4; j++)
                if(jccellrange.inRowRange2(j))
                    draw_cell(table, j, -1);

        }
        if(jccellrange.end_row == 0x7fffffff)
        {
            if(table.frozen_columns > 0)
            {
                for(int i2 = 0; i2 < table.frozen_columns; i2++)
                    if(jccellrange.inColumnRange2(i2))
                        draw_cell(table, -1, i2);

            }
            for(int j2 = k4; j2 <= l4; j2++)
                if(jccellrange.inColumnRange2(j2))
                    draw_cell(table, -1, j2);

        }
        if(jccellrange.start_column == -1 && jccellrange.end_column == -1)
        {
            if(table.frozen_rows > 0)
            {
                for(int k = 0; k < table.frozen_rows; k++)
                    if(jccellrange.inside(k, -1))
                        draw_cell(table, k, -1);

            }
            for(int l = i4; l <= j4; l++)
                if(jccellrange.inside(l, -1))
                    draw_cell(table, l, -1);

        }
        if(jccellrange.start_row == -1 && jccellrange.end_row == -1)
        {
            if(table.frozen_columns > 0)
            {
                for(int k2 = 0; k2 < table.frozen_columns; k2++)
                    if(jccellrange.inside(-1, k2))
                        draw_cell(table, -1, k2);

            }
            for(int l2 = k4; l2 <= l4; l2++)
                if(jccellrange.inside(-1, l2))
                    draw_cell(table, -1, l2);

        }
        if(table.span.span_list.size() == 0)
        {
            jccellrange1.start_row = Math.min(jccellrange.start_row, jccellrange.end_row);
            jccellrange1.end_row = Math.max(jccellrange.start_row, jccellrange.end_row);
            jccellrange1.start_column = Math.min(jccellrange.start_column, jccellrange.end_column);
            jccellrange1.end_column = Math.max(jccellrange.start_column, jccellrange.end_column);
            if(jccellrange1.overlaps(jccellrange2))
            {
                JCCellRange jccellrange3 = jccellrange1.intersection(jccellrange2);
                Clip clip = Clip.find(table, jccellrange3.start_row, jccellrange3.start_column);
                if(clip != null)
                    clip.repaint();
            }
        } else
        {
            for(int i1 = i4; i1 <= j4; i1++)
            {
                for(int i3 = k4; i3 <= l4; i3++)
                    if(jccellrange.inside(i1, i3))
                        draw_cell(table, i1, i3);

            }

        }
        if(table.frozen_columns > 0)
        {
            for(int j1 = i4; j1 <= j4; j1++)
            {
                for(int j3 = 0; j3 < table.frozen_columns; j3++)
                    if(jccellrange.inside(j1, j3))
                        draw_cell(table, j1, j3);

            }

        }
        if(table.frozen_rows > 0)
        {
            for(int k1 = 0; k1 < table.frozen_rows; k1++)
            {
                for(int k3 = k4; k3 <= l4; k3++)
                    if(jccellrange.inside(k1, k3))
                        draw_cell(table, k1, k3);

                for(int l3 = 0; l3 < table.frozen_columns; l3++)
                    if(jccellrange.inside(k1, l3))
                        draw_cell(table, k1, l3);

            }

        }
        for(int l1 = 0; l1 < table.span.span_list.size(); l1++)
        {
            JCCellRange jccellrange4 = (JCCellRange)table.span.span_list.elementAt(l1);
            if(jccellrange.intersects(jccellrange4) && !table.isVisible(jccellrange4.start_row, jccellrange4.start_column))
                draw_cell(table, jccellrange4.start_row, jccellrange4.start_column);
        }

    }

    DrawRange()
    {
    }
}
