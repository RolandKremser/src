// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Select.java

package jclass.table3;

import java.util.Vector;
import jclass.util.JCVector;

// Referenced classes of package jclass.table3:
//            Clip, DrawRange, JCCellPosition, JCCellRange, 
//            JCTblEnum, Span, Table

class Select
{

    static boolean getSelectedRange(Table table, int i, JCCellRange jccellrange)
    {
        if(i > table.selected_cells.size() - 1)
        {
            return false;
        } else
        {
            JCCellRange jccellrange1 = (JCCellRange)table.selected_cells.elementAt(i);
            jccellrange.start_row = Math.min(jccellrange1.start_row, jccellrange1.end_row);
            jccellrange.end_row = Math.max(jccellrange1.start_row, jccellrange1.end_row);
            jccellrange.end_row = Math.min(table.rows - 1, jccellrange.end_row);
            jccellrange.start_column = Math.min(jccellrange1.start_column, jccellrange1.end_column);
            jccellrange.end_column = Math.max(jccellrange1.start_column, jccellrange1.end_column);
            jccellrange.end_column = Math.min(table.columns - 1, jccellrange.end_column);
            return true;
        }
    }

    static boolean equal(JCVector jcvector, JCVector jcvector1)
    {
        if(jcvector == null && jcvector1 == null)
            return true;
        if(jcvector == null)
            return false;
        else
            return jcvector.equals(jcvector1);
    }

    static void createSelectedRange(Table table, int i, int j)
    {
        if(i == -998 && j == -998)
            table.selected_cells.removeAllElements();
        int l;
        int k = l = i;
        int j1;
        int i1 = j1 = j;
        if(i == -998 || i == -997 || i == -1)
        {
            k = 0;
            l = 0x7fffffff;
        }
        if(j == -998 || j == -997 || j == -1)
        {
            i1 = 0;
            j1 = 0x7fffffff;
        }
        JCCellRange jccellrange = new JCCellRange(k, i1, l, j1);
        table.selected_cells.addElement(jccellrange);
        DrawRange.draw(table, jccellrange);
        table.afterCellSelected();
    }

    static JCCellRange extendRange(Table table, int i, int j)
    {
        if(table.selected_cells.size() == 0)
            return null;
        JCCellRange jccellrange = (JCCellRange)table.selected_cells.lastElement();
        int k = jccellrange.start_row;
        int l = jccellrange.start_column;
        int i1 = jccellrange.end_row;
        int j1 = jccellrange.end_column;
        i = Math.max(i, 0);
        j = Math.max(j, 0);
        if(j1 == 0x7fffffff)
            i1 = i;
        if(i1 == 0x7fffffff)
            j1 = j;
        if(j1 != 0x7fffffff && i1 != 0x7fffffff)
        {
            i1 = i;
            j1 = j;
        }
        return new JCCellRange(k, l, i1, j1);
    }

    static void setSelectedRange(Table table, int i, int j)
    {
        if(table.selected_cells.size() == 0)
        {
            createSelectedRange(table, i, j);
            return;
        }
        JCCellRange jccellrange1 = (JCCellRange)table.selected_cells.lastElement();
        int i6 = jccellrange1.start_row;
        int j6 = jccellrange1.start_column;
        int k6 = jccellrange1.end_row;
        int l6 = jccellrange1.end_column;
        old_range.reshape(Math.min(i6, k6), Math.min(j6, l6), Math.max(i6, k6), Math.max(j6, l6));
        i = Math.max(i, 0);
        j = Math.max(j, 0);
        if(l6 == 0x7fffffff)
            k6 = i;
        if(k6 == 0x7fffffff)
            l6 = j;
        if(l6 != 0x7fffffff && k6 != 0x7fffffff)
        {
            k6 = i;
            l6 = j;
        }
        ((JCCellRange)table.selected_cells.lastElement()).reshape(i6, j6, k6, l6);
        JCCellRange jccellrange;
        if((jccellrange = table.getVisibleCells()) == null)
            return;
        new_range.reshape(Math.min(i6, k6), Math.min(j6, l6), Math.max(i6, k6), Math.max(j6, l6));
        int i7 = jccellrange.start_row;
        int j7 = jccellrange.end_row;
        int k7 = jccellrange.start_column;
        int l7 = jccellrange.end_column;
        i7 = Math.max(i7, Math.min(old_range.start_row, new_range.start_row));
        k7 = Math.max(k7, Math.min(old_range.start_column, new_range.start_column));
        j7 = Math.min(j7, Math.max(old_range.end_row, new_range.end_row));
        l7 = Math.min(l7, Math.max(old_range.end_column, new_range.end_column));
        if(old_range.end_column == 0x7fffffff)
        {
            for(int k = 0; k < table.frozen_rows; k++)
                if(old_range.inRowRange(k) && !new_range.inRowRange(k))
                    table.paint(k, -1);

            for(int l = i7; l <= j7; l++)
                if(old_range.inRowRange(l) && !new_range.inRowRange(l))
                    table.paint(l, -1);

        }
        if(old_range.end_row == 0x7fffffff)
        {
            for(int i3 = 0; i3 < table.frozen_columns; i3++)
                if(old_range.inColumnRange(i3) && !new_range.inColumnRange(i3))
                    table.paint(-1, i3);

            for(int j3 = k7; j3 <= l7; j3++)
                if(old_range.inColumnRange(j3) && !new_range.inColumnRange(j3))
                    table.paint(-1, j3);

        }
        int i8 = 0x7fffffff;
        int j8 = 0;
        int k8 = 0x7fffffff;
        int l8 = 0;
        for(int i1 = i7; i1 <= j7; i1++)
            if(old_range.inRowRange(i1) && !new_range.inRowRange(i1))
            {
                if(i1 < i8)
                    i8 = i1;
                if(i1 > j8)
                    j8 = i1;
            }

        for(int k3 = k7; k3 <= l7; k3++)
            if(old_range.inColumnRange(k3) && !new_range.inColumnRange(k3))
            {
                if(k3 < k8)
                    k8 = k3;
                if(k3 > l8)
                    l8 = k3;
            }

        Clip clip = Clip.find_by_type(table, 0);
        if(clip != null)
            if(j8 >= i8 && k8 == 0x7fffffff)
                clip.paint(i8, old_range.start_column, j8, old_range.end_column);
            else
            if(l8 >= k8 && i8 == 0x7fffffff)
                clip.paint(old_range.start_row, k8, old_range.end_row, l8);
            else
            if(j8 >= i8 && l8 >= k8)
            {
                clip.paint(i8, k8, j8, l8);
                clip.paint(i8, old_range.start_column, j8, old_range.end_column);
                clip.paint(old_range.start_row, k8, old_range.end_row, l8);
            }
        for(int j1 = i7; j1 <= j7; j1++)
        {
            for(int l3 = 0; l3 < table.frozen_columns; l3++)
                if(old_range.inside(j1, l3) && !new_range.inside(j1, l3))
                    table.paint(j1, l3);

        }

        if(table.frozen_rows > 0)
        {
            for(int k1 = 0; k1 < table.frozen_rows; k1++)
            {
                for(int i4 = k7; i4 <= l7; i4++)
                    if(old_range.inside(k1, i4) && !new_range.inside(k1, i4))
                        table.paint(k1, i4);

                for(int j4 = 0; j4 < table.frozen_columns; j4++)
                    if(old_range.inside(k1, j4) && !new_range.inside(k1, j4))
                        table.paint(k1, j4);

            }

        }
        i7 = Math.max(i7, new_range.start_row);
        j7 = Math.min(j7, new_range.end_row);
        k7 = Math.max(k7, new_range.start_column);
        l7 = Math.min(l7, new_range.end_column);
        if(old_range.overlaps(new_range))
            old_range.intersection(new_range, inter);
        if(new_range.end_column == 0x7fffffff)
        {
            for(int l1 = 0; l1 < table.frozen_rows; l1++)
                if(new_range.inRowRange(l1) && (inter == null || !inter.inRowRange(l1)))
                    table.paint(l1, -1);

            for(int i2 = i7; i2 <= j7; i2++)
                if(inter == null || !inter.inRowRange(i2))
                    table.paint(i2, -1);

        }
        if(new_range.end_row == 0x7fffffff)
        {
            for(int k4 = 0; k4 < table.frozen_columns; k4++)
                if(new_range.inColumnRange(k4) && (inter == null || !inter.inColumnRange(k4)))
                    table.paint(-1, k4);

            for(int l4 = k7; l4 <= l7; l4++)
                if(inter == null || !inter.inColumnRange(l4))
                    table.paint(-1, l4);

        }
        for(int j2 = i7; j2 <= j7; j2++)
        {
            for(int i5 = 0; i5 < table.frozen_columns; i5++)
                if(new_range.inside(j2, i5) && (inter == null || !inter.inside(j2, i5)))
                    table.paint(j2, i5);

        }

        if(table.frozen_rows > 0)
        {
            for(int k2 = 0; k2 < table.frozen_rows; k2++)
            {
                for(int j5 = k7; j5 <= l7; j5++)
                    if(new_range.inside(k2, j5) && (inter == null || !inter.inside(k2, j5)))
                        table.paint(k2, j5);

                for(int k5 = 0; k5 < table.frozen_columns; k5++)
                    if(new_range.inside(k2, k5) && (inter == null || !inter.inside(k2, k5)))
                        table.paint(k2, k5);

            }

        }
        i8 = 0x7fffffff;
        j8 = 0;
        k8 = 0x7fffffff;
        l8 = 0;
        for(int l2 = i7; l2 <= j7; l2++)
            if(inter == null || !inter.inRowRange(l2))
            {
                if(l2 < i8)
                    i8 = l2;
                if(l2 > j8)
                    j8 = l2;
            }

        for(int l5 = k7; l5 <= l7; l5++)
            if(inter == null || !inter.inColumnRange(l5))
            {
                if(l5 < k8)
                    k8 = l5;
                if(l5 > l8)
                    l8 = l5;
            }

        if(clip != null)
        {
            if(j8 >= i8 && k8 == 0x7fffffff)
            {
                clip.paint(i8, old_range.start_column, j8, old_range.end_column);
                return;
            }
            if(l8 >= k8 && i8 == 0x7fffffff)
            {
                clip.paint(old_range.start_row, k8, old_range.end_row, l8);
                return;
            }
            if(j8 >= i8 && l8 >= k8)
            {
                clip.paint(i8, k8, j8, l8);
                clip.paint(i8, old_range.start_column, j8, old_range.end_column);
                clip.paint(old_range.start_row, k8, old_range.end_row, l8);
            }
        }
    }

    static void selectAll(Table table)
    {
        createSelectedRange(table, -998, -998);
        if(!table.repaint)
        {
            table.needs_repaint = true;
            return;
        } else
        {
            DrawRange.draw(table, new JCCellRange(0, 0, 0x7fffffff, 0x7fffffff));
            return;
        }
    }

    static void deselectAll(Table table)
    {
        if(table.selected_cells.size() == 0)
            return;
        JCVector jcvector = (JCVector)table.selected_cells.clone();
        table.selected_cells.removeAllElements();
        if(!table.repaint)
        {
            table.needs_repaint = true;
            return;
        }
        for(int i = 0; i < jcvector.size(); i++)
            DrawRange.draw(table, (JCCellRange)jcvector.elementAt(i));

    }

    static boolean isSelected(Table table, int i, int j)
    {
        if(table.selected_cells.size() > 0)
        {
            for(int k = 0; k < table.selected_cells.size(); k++)
            {
                r = (JCCellRange)table.selected_cells.elementAt(k);
                spannedRange.reshape(-999, -999, -999, -999);
                table.span.find(i, j, spannedRange, null);
                if(spannedRange.overlaps(r))
                    return true;
                if(r.inside(i, j))
                    return true;
                if(Table.isRowLabel(i, j) && r.end_column == 0x7fffffff && r.inRowRange2(i))
                    return true;
                if(Table.isColumnLabel(i, j) && r.end_row == 0x7fffffff && r.inColumnRange2(j))
                    return true;
            }

        }
        return false;
    }

    static void cleanup(Table table)
    {
        int i;
        if((i = table.selected_cells.size()) < 2)
            return;
        JCCellRange jccellrange = (JCCellRange)table.selected_cells.lastElement();
        for(int j = i - 2; j >= 0; j--)
            if(jccellrange.equals(table.selected_cells.elementAt(j)))
                table.selected_cells.removeElementAt(j);

    }

    private static void draw(Table table, int i, int j, JCCellRange ajccellrange[])
    {
        if(table.span.span_list.size() > 0)
        {
            JCCellPosition jccellposition = new JCCellPosition();
            if(table.span.find(i, j, jccellposition) != -999)
            {
                i = jccellposition.row;
                j = jccellposition.column;
            }
        }
        if(ajccellrange != null)
        {
            for(int k = 0; k < ajccellrange.length; k++)
                if(ajccellrange[k].inside(i, j))
                    return;

        }
        table.paint(i, j);
    }

    private static void drawRange(Table table, JCCellRange jccellrange, JCVector jcvector)
    {
        JCCellRange jccellrange1 = table.getVisibleCells();
        if(jccellrange1 == null)
            return;
        int l3 = jccellrange1.start_row;
        int i4 = jccellrange1.end_row;
        int j4 = jccellrange1.start_column;
        int k4 = jccellrange1.end_column;
        JCCellRange ajccellrange[];
        if(jcvector != null && jcvector.size() > 0)
        {
            ajccellrange = new JCCellRange[jcvector.size()];
            jcvector.copyInto(ajccellrange);
        } else
        {
            ajccellrange = null;
        }
        if(jccellrange.end_column == 0x7fffffff)
        {
            for(int i = 0; i < table.frozen_rows; i++)
                if(jccellrange.inRowRange2(i))
                    draw(table, i, -1, ajccellrange);

            for(int j = l3; j <= i4; j++)
                if(jccellrange.inRowRange2(j))
                    draw(table, j, -1, ajccellrange);

        }
        if(jccellrange.end_row == 0x7fffffff)
        {
            for(int l1 = 0; l1 < table.frozen_columns; l1++)
                if(jccellrange.inColumnRange2(l1))
                    draw(table, -1, l1, ajccellrange);

            for(int i2 = j4; i2 <= k4; i2++)
                if(jccellrange.inColumnRange2(i2))
                    draw(table, -1, i2, ajccellrange);

        }
        if(jccellrange.start_row == -1 && jccellrange.end_row == -1)
        {
            for(int j2 = j4; j2 <= k4; j2++)
                if(jccellrange.inside(-1, j2))
                    draw(table, -1, j2, ajccellrange);

            for(int k2 = 0; k2 < table.frozen_columns; k2++)
                if(jccellrange.inside(-1, k2))
                    draw(table, -1, k2, ajccellrange);

        }
        if(jccellrange.start_column == -1 && jccellrange.end_column == -1)
        {
            for(int k = l3; k <= i4; k++)
                if(jccellrange.inside(k, -1))
                    draw(table, k, -1, ajccellrange);

            for(int l = 0; l < table.frozen_rows; l++)
                if(jccellrange.inside(l, -1))
                    draw(table, l, -1, ajccellrange);

        }
        for(int i1 = l3; i1 <= i4; i1++)
        {
            for(int l2 = j4; l2 <= k4; l2++)
                if(jccellrange.inside(i1, l2))
                    draw(table, i1, l2, ajccellrange);

            for(int i3 = 0; i3 < table.frozen_columns; i3++)
                if(jccellrange.inside(i1, i3))
                    draw(table, i1, i3, ajccellrange);

        }

        for(int j1 = 0; j1 < table.frozen_rows; j1++)
        {
            for(int j3 = j4; j3 <= k4; j3++)
                if(jccellrange.inside(j1, j3))
                    draw(table, j1, j3, ajccellrange);

            for(int k3 = 0; k3 < table.frozen_columns; k3++)
                if(jccellrange.inside(j1, k3))
                    draw(table, j1, k3, ajccellrange);

        }

        for(int k1 = 0; k1 < table.span.span_list.size(); k1++)
        {
            JCCellRange jccellrange2 = (JCCellRange)table.span.span_list.elementAt(k1);
            if(jccellrange.overlaps(jccellrange2) && !table.isVisible(jccellrange2.start_row, jccellrange2.start_column))
                draw(table, jccellrange2.start_row, jccellrange2.start_column, ajccellrange);
        }

    }

    static void drawList(Table table, JCVector jcvector)
    {
        Object aobj[] = null;
        if(jcvector != null && jcvector.size() > 0)
            aobj = jcvector.getArrayCopy();
        if(table.selected_cells.size() == 0)
        {
            if(aobj != null)
            {
                for(int i = 0; i < aobj.length; i++)
                    DrawRange.draw(table, (JCCellRange)aobj[i]);

            }
            return;
        }
        if(!table.repaint)
        {
            table.needs_repaint = true;
            return;
        }
        for(int j = 0; j < table.selected_cells.size(); j++)
            drawRange(table, (JCCellRange)table.selected_cells.elementAt(j), jcvector);

        if(aobj != null)
        {
            for(int k = 0; k < aobj.length; k++)
                drawRange(table, (JCCellRange)aobj[k], table.selected_cells);

        }
    }

    static void copy(Table table)
    {
        int i;
        if((i = table.selected_cells.size()) == 0)
            return;
        int j = table.selection_policy;
        i = table.selected_cells.size();
        if(table.mode == 1 && j == 1)
            j = 2;
        switch(j)
        {
        case 0: // '\0'
            table.selected_cells.removeAllElements();
            return;

        case 1: // '\001'
            i = 1;
            JCCellRange jccellrange = (JCCellRange)table.selected_cells.firstElement();
            if(!Table.isCell(jccellrange.start_row, jccellrange.start_column))
            {
                table.selected_cells.removeAllElements();
                return;
            }
            jccellrange.end_row = jccellrange.start_row;
            jccellrange.end_column = jccellrange.start_column;
            break;

        case 2: // '\002'
            i = 1;
            break;
        }
        JCVector jcvector = new JCVector(i);
        for(int k = 0; k < i; k++)
        {
            JCCellRange jccellrange1 = (JCCellRange)table.selected_cells.elementAt(k);
            jcvector.addElement(jccellrange1);
            if(table.mode == 1)
            {
                ((JCCellRange)jcvector.lastElement()).start_column = 0;
                ((JCCellRange)jcvector.lastElement()).end_column = 0x7fffffff;
            }
        }

        table.selected_cells = jcvector;
    }

    static void redisplay(Table table)
    {
        JCVector jcvector = table.selected_cells;
        int i = 0;
        for(int j = jcvector.size(); i < j; i++)
        {
            JCCellRange jccellrange = (JCCellRange)jcvector.elementAt(i);
            DrawRange.draw(table, jccellrange);
        }

    }

    Select()
    {
    }

    static JCCellRange old_range = new JCCellRange();
    static JCCellRange new_range = new JCCellRange();
    static JCCellRange inter = new JCCellRange();
    static JCCellRange r = new JCCellRange();
    static JCCellRange spannedRange = new JCCellRange();

}
