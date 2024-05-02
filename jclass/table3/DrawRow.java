// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DrawRow.java

package jclass.table3;

import java.awt.Component;
import java.util.Vector;

// Referenced classes of package jclass.table3:
//            JCCellPosition, JCCellRange, JCTblEnum, Span, 
//            Table

class DrawRow
{

    static void draw_cell(Table table, int i, int j, JCCellPosition jccellposition)
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
        if(!table.isVisible())
            return;
        JCCellRange jccellrange = new JCCellRange();
        int i1;
        int j1;
        if(i == -998 || i == -997)
        {
            if(!table.getVisibleRows(jccellrange))
                return;
            i1 = jccellrange.start_row;
            j1 = jccellrange.end_row;
        } else
        {
            if(!table.isRowVisible(i))
                return;
            i1 = j1 = i;
        }
        JCCellPosition jccellposition = new JCCellPosition();
        if(i != -997)
        {
            for(int i2 = i1; i2 <= j1; i2++)
                draw_cell(table, i2, -1, jccellposition);

            if(i == -998)
            {
                for(int j2 = 0; j2 < table.frozen_rows; j2++)
                    draw_cell(table, j2, -1, jccellposition);

            }
        }
        if(!table.getVisibleColumns(jccellrange))
            return;
        int k1 = jccellrange.start_column;
        int l1 = jccellrange.end_column;
        for(int k2 = i1; k2 <= j1; k2++)
        {
            for(int j = k1; j <= l1; j++)
                draw_cell(table, k2, j, jccellposition);

        }

        if(table.frozen_columns > 0)
        {
            for(int l2 = i1; l2 <= j1; l2++)
            {
                for(int k = 0; k < table.frozen_columns; k++)
                    draw_cell(table, l2, k, jccellposition);

            }

            return;
        }
        if(table.frozen_rows > 0 && (i == -998 || i == -997))
        {
            for(int i3 = 0; i3 < table.frozen_rows; i3++)
            {
                for(int l = k1; l <= l1; l++)
                    draw_cell(table, i3, l, jccellposition);

            }

        }
    }

    DrawRow()
    {
    }
}
