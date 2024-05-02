// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DrawColumn.java

package jclass.table3;

import java.awt.Component;
import java.util.Vector;

// Referenced classes of package jclass.table3:
//            JCCellPosition, JCCellRange, JCTblEnum, Span, 
//            Table

class DrawColumn
{

    private static void draw_cell(Table table, int i, int j, JCCellPosition jccellposition)
    {
        if(table.span.span_list.size() == 0)
        {
            table.paint(i, j);
            return;
        }
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

    static void draw(Table table, int i)
    {
        JCCellRange jccellrange = new JCCellRange();
        if(!table.isVisible())
            return;
        int k1;
        int l1;
        if(i == -998 || i == -997)
        {
            if(!table.getVisibleColumns(jccellrange))
                return;
            k1 = jccellrange.start_column;
            l1 = jccellrange.end_column;
        } else
        {
            if(!table.isColumnVisible(i))
                return;
            k1 = l1 = i;
        }
        JCCellPosition jccellposition = new JCCellPosition();
        if(i != -997)
        {
            for(int i2 = k1; i2 <= l1; i2++)
                draw_cell(table, -1, i2, jccellposition);

            if(i == -998)
            {
                for(int j2 = 0; j2 < table.frozen_columns; j2++)
                    draw_cell(table, -1, j2, jccellposition);

            }
        }
        if(!table.getVisibleRows(jccellrange))
            return;
        int i1 = jccellrange.start_row;
        int j1 = jccellrange.end_row;
        for(int k2 = i1; k2 <= j1; k2++)
            draw_cell(table, k2, -1, jccellposition);

        for(int l2 = 0; l2 < table.frozen_rows; l2++)
            draw_cell(table, l2, -1, jccellposition);

        for(int i3 = k1; i3 <= l1; i3++)
        {
            for(int j = i1; j <= j1; j++)
                draw_cell(table, j, i3, jccellposition);

        }

        if(table.frozen_rows > 0)
        {
            for(int j3 = k1; j3 <= l1; j3++)
            {
                for(int k = 0; k < table.frozen_rows; k++)
                    draw_cell(table, k, j3, jccellposition);

            }

            return;
        }
        if(table.frozen_columns > 0 && (i == -998 || i == -997))
        {
            for(int k3 = 0; k3 < table.frozen_columns; k3++)
            {
                for(int l = i1; l <= j1; l++)
                    draw_cell(table, l, k3, jccellposition);

            }

        }
    }

    DrawColumn()
    {
    }
}
