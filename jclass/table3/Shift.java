// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Shift.java

package jclass.table3;

import java.util.Vector;
import jclass.util.JCVector;

// Referenced classes of package jclass.table3:
//            JCCellRange, JCTblEnum, Select, Series, 
//            SeriesUtil, Size, Span, Table

class Shift
{

    static int adjust(int i, int j, int k, int l)
    {
        if(j > l)
        {
            if(i >= l && i < j)
                i += k;
            else
            if(i >= j && i < j + k)
                i -= j - l;
        } else
        if(i >= j + k && i < l)
            i -= k;
        else
        if(i >= j && i < j + k)
            i += l - j - k;
        return i;
    }

    static int swap(int i, int j, int k)
    {
        if(i == j)
            return k;
        if(i == k)
            return j;
        else
            return i;
    }

    static void labels(Table table, JCVector jcvector, int i, int j, int k)
    {
        if(jcvector.size() == 0 || i >= jcvector.size())
            return;
        if(k > 0)
        {
            for(int l = i; l < i + j; l++)
                jcvector.insertElementAt(null, l);

            return;
        } else
        {
            jcvector.removeElementsAt(i, j);
            return;
        }
    }

    static void selectColumns(Table table, int i, int j, int k)
    {
        if(table.selected_cells.size() == 0)
            return;
        boolean flag = false;
        JCVector jcvector = (JCVector)table.selected_cells.clone();
        for(int l = jcvector.size() - 1; l >= 0; l--)
        {
            JCCellRange jccellrange = (JCCellRange)jcvector.elementAt(l);
            int i1 = jccellrange.start_column;
            int j1 = jccellrange.end_column;
            if(i1 != 0x7fffffff && j1 != 0x7fffffff)
            {
                if(k > 0)
                {
                    if(i1 >= i)
                        jccellrange.start_column += j;
                    if(j1 >= i)
                        jccellrange.end_column += j;
                } else
                if(i1 >= i && i1 < i + j && j1 >= i && j1 < i + j)
                {
                    jcvector.removeElementAt(l);
                } else
                {
                    if(i1 >= i + j)
                        jccellrange.start_column -= j;
                    if(j1 >= i + j)
                        jccellrange.end_column -= j;
                }
                flag = true;
            }
        }

        if(flag)
        {
            Select.deselectAll(table);
            if(jcvector.size() > 0)
            {
                table.selected_cells = jcvector;
                Select.drawList(table, null);
            }
        }
    }

    static void selectRows(Table table, int i, int j, int k)
    {
        if(table.selected_cells.size() == 0)
            return;
        boolean flag = false;
        JCVector jcvector = (JCVector)table.selected_cells.clone();
        for(int l = jcvector.size() - 1; l >= 0; l--)
        {
            JCCellRange jccellrange = (JCCellRange)jcvector.elementAt(l);
            int i1 = jccellrange.start_row;
            int j1 = jccellrange.end_row;
            if(i1 != 0x7fffffff && j1 != 0x7fffffff)
            {
                if(k > 0)
                {
                    if(i1 >= i)
                        jccellrange.start_row += j;
                    if(j1 >= i)
                        jccellrange.end_row += j;
                } else
                if(i1 >= i && i1 < i + j && j1 >= i && j1 < i + j)
                {
                    jcvector.removeElementAt(l);
                } else
                {
                    if(i1 >= i + j)
                        jccellrange.start_row -= j;
                    if(j1 >= i + j)
                        jccellrange.end_row -= j;
                }
                flag = true;
            }
        }

        if(flag)
        {
            Select.deselectAll(table);
            if(jcvector.size() > 0)
            {
                table.selected_cells = jcvector;
                Select.drawList(table, null);
            }
        }
    }

    static void spanColumns(Table table, int i, int j, int k)
    {
        if(table.span.span_list_orig == null || table.span.span_list_orig.size() == 0)
            return;
        for(int l = 0; l < table.span.span_list_orig.size(); l++)
        {
            JCCellRange jccellrange = (JCCellRange)table.span.span_list_orig.elementAt(l);
            int i1 = jccellrange.start_column;
            int j1 = jccellrange.end_column;
            if(i1 != -998 && i1 != -997 && j1 != -998 && j1 != -997)
                if(k > 0)
                {
                    if(i1 >= i)
                    {
                        jccellrange.start_column += j;
                        if(jccellrange.end_column != 0x7fffffff)
                            jccellrange.end_column += j;
                    }
                } else
                if(i1 >= i && i1 < i + j)
                    jccellrange.start_column = -999;
                else
                if(i1 >= i + j)
                {
                    jccellrange.start_column -= j;
                    jccellrange.end_column -= j;
                }
        }

        table.span.copy();
    }

    static void spanRows(Table table, int i, int j, int k)
    {
        if(table.span.span_list_orig.size() == 0)
            return;
        for(int l = 0; l < table.span.span_list_orig.size(); l++)
        {
            JCCellRange jccellrange = (JCCellRange)table.span.span_list_orig.elementAt(l);
            int i1 = jccellrange.start_row;
            int j1 = jccellrange.end_row;
            if(i1 != -998 && i1 != -997 && j1 != -998 && j1 != -997)
                if(k > 0)
                {
                    if(i1 >= i)
                    {
                        jccellrange.start_row += j;
                        if(jccellrange.end_row != 0x7fffffff)
                            jccellrange.end_row += j;
                    }
                } else
                if(i1 >= i && i1 < i + j)
                    jccellrange.start_row = -999;
                else
                if(i1 >= i + j)
                {
                    jccellrange.start_row -= j;
                    jccellrange.end_row -= j;
                }
        }

        table.span.copy();
    }

    static void columnSeries(Table table, int i, int j, int k)
    {
        byte byte0 = ((byte)(k != 3 ? -1 : 1));
        for(int l = 0; l < table.series_list.size(); l++)
        {
            Series series = (Series)table.series_list.elementAt(l);
            SeriesUtil.shiftColumn(series, i, j, byte0);
        }

        selectColumns(table, i, j, byte0);
        spanColumns(table, i, j, byte0);
    }

    static void rowSeries(Table table, int i, int j, int k)
    {
        byte byte0 = ((byte)(k != 4 ? -1 : 1));
        for(int l = 0; l < table.series_list.size(); l++)
        {
            Series series = (Series)table.series_list.elementAt(l);
            SeriesUtil.shiftRow(series, i, j, byte0);
        }

        selectRows(table, i, j, byte0);
        spanRows(table, i, j, byte0);
    }

    Shift()
    {
    }
}
