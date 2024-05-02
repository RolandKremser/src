// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Span.java

package jclass.table3;

import java.awt.Component;
import java.awt.Dimension;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Vector;
import jclass.util.JCVector;

// Referenced classes of package jclass.table3:
//            Chain, Clip, JCCell, JCCellPosition, 
//            JCCellRange, JCTblEnum, Size, SpanList, 
//            Table, TableDataView

class Span
    implements Serializable
{

    Span(Table table1)
    {
        span_list = new JCVector();
        span_list_orig = new JCVector();
        table = table1;
    }

    int find(int i, int j, JCCellPosition jccellposition)
    {
        JCCellRange jccellrange = new JCCellRange();
        int k = find(i, j, jccellrange, null);
        if(k != -999 && jccellposition != null)
        {
            jccellposition.row = jccellrange.start_row;
            jccellposition.column = jccellrange.start_column;
        }
        return k;
    }

    int find(int i, int j, JCCellRange jccellrange, Dimension dimension)
    {
        int l = -999;
        int i1 = -999;
        for(int k = 0; k < span_list.size(); k++)
        {
            JCCellRange jccellrange1 = (JCCellRange)span_list.elementAt(k);
            if(!jccellrange1.inside(i, j))
                continue;
            if(i == jccellrange1.start_row && j == jccellrange1.start_column)
            {
                i1 = k;
                break;
            }
            if(l == -999)
                l = k;
        }

        if(i1 == -999)
            if(l != -999)
                i1 = l;
            else
                return -999;
        JCCellRange jccellrange2 = (JCCellRange)span_list.elementAt(i1);
        if(jccellrange != null)
            jccellrange.reshape(jccellrange2);
        if(dimension != null)
        {
            dimension.height = (table.rowPosition(jccellrange2.end_row) - table.rowPosition(jccellrange2.start_row)) + table.rowHeight(jccellrange2.end_row);
            dimension.width = (table.columnPosition(jccellrange2.end_column) - table.columnPosition(jccellrange2.start_column)) + table.columnWidth(jccellrange2.end_column);
        }
        return i1;
    }

    JCCellPosition find(int i, int j)
    {
        JCCellRange jccellrange = new JCCellRange();
        if(find(i, j, jccellrange, null) != -999)
            return new JCCellPosition(jccellrange.start_row, jccellrange.start_column);
        else
            return null;
    }

    boolean isSpanned(int i, int j)
    {
        for(int k = 0; k < span_list.size(); k++)
        {
            JCCellRange jccellrange = (JCCellRange)span_list.elementAt(k);
            if(jccellrange.inside(i, j))
                return true;
        }

        return false;
    }

    void adjust()
    {
        if(span_list.size() == 0)
        {
            return;
        } else
        {
            adjustWidth();
            adjustHeight();
            return;
        }
    }

    void copy()
    {
        span_list = new JCVector();
        if(span_list_orig.size() == 0)
            return;
        JCCellRange ajccellrange[] = Clip.get_rangeList(table);
        for(int j1 = 0; j1 < span_list_orig.size(); j1++)
        {
            JCCellRange jccellrange = (JCCellRange)span_list_orig.elementAt(j1);
            if(jccellrange.start_row != -999 && jccellrange.start_column != -999)
            {
                int i = Math.min(jccellrange.start_row, jccellrange.end_row);
                int k = Math.max(jccellrange.start_row, jccellrange.end_row);
                int j = Math.min(jccellrange.start_column, jccellrange.end_column);
                int l = Math.max(jccellrange.start_column, jccellrange.end_column);
                if(!Table.isLabel(i, j) && !Table.isCell(i, j) || !Table.isLabel(k, l) && !Table.isCell(k, l))
                {
                    System.err.println("spanlist.invalidValue: Invalid Range " + jccellrange);
                } else
                {
                    int i1;
                    for(i1 = 0; i1 < ajccellrange.length; i1++)
                        if(ajccellrange[i1].inside(i, j))
                            break;

                    if(i1 != ajccellrange.length && table.rowHeight(i) != 0 && table.columnWidth(j) != 0)
                    {
                        JCCellRange jccellrange1 = new JCCellRange(i, j, k, l);
                        JCCellRange jccellrange2 = ajccellrange[i1].intersection(jccellrange1);
                        jccellrange1.end_row = jccellrange2.end_row;
                        jccellrange1.end_column = jccellrange2.end_column;
                        if(!isOverlapped(span_list, jccellrange1))
                            span_list.addElement(jccellrange1);
                    }
                }
            }
        }

        if(span_list.size() != span_list_orig.size())
            span_list_orig = (JCVector)span_list.clone();
    }

    boolean isOverlapped(JCVector jcvector, JCCellRange jccellrange)
    {
        int i = 0;
        for(int j = jcvector.size(); i < j; i++)
        {
            JCCellRange jccellrange1 = (JCCellRange)jcvector.elementAt(i);
            if(jccellrange.overlaps(jccellrange1))
            {
                System.err.println("spanlist.overlap: Range R" + jccellrange.start_row + "C" + jccellrange.start_column + ":R" + jccellrange.end_row + "C" + jccellrange.end_column + " overlaps " + "R" + jccellrange1.start_row + "C" + jccellrange1.start_column + ":R" + jccellrange1.end_row + "C" + jccellrange1.end_column);
                return true;
            }
        }

        return false;
    }

    int spanListSize()
    {
        return span_list.size();
    }

    int origSpanListSize()
    {
        return span_list_orig.size();
    }

    boolean doSpansExist()
    {
        return span_list.size() > 0;
    }

    private void adjustWidth()
    {
        int j2 = 2 * table.textWidthOffset();
        boolean flag = false;
        int j4 = 2 * table.shadow_thickness + 1;
        boolean flag1 = false;
        SpanList aspanlist[] = new SpanList[span_list.size()];
        for(int i3 = 0; i3 < aspanlist.length; i3++)
            aspanlist[i3] = new SpanList();

        int j3 = 0;
        int k2 = 0;
        for(; j3 < span_list.size(); j3++)
        {
            JCCellRange jccellrange = (JCCellRange)span_list.elementAt(j3);
            int k = aspanlist[k2].start_column = jccellrange.start_column;
            int i1 = aspanlist[k2].end_column = jccellrange.end_column;
            if(k != i1)
            {
                int i = aspanlist[k2].start_row = jccellrange.start_row;
                if(table.getPixelWidth(k) == 33001)
                {
                    Object obj = table.dataView.getData(i, k);
                    int i4 = JCCell.getWidth(table, null, obj, i, k, table.getFont(i, k));
                    if(obj instanceof Component)
                        i4 = (i4 - j2) + j4;
                    aspanlist[k2].width = i4;
                    k2++;
                }
            }
        }

        int l2 = -999;
        for(int k3 = 0; k3 < k2; k3++)
        {
            int l = aspanlist[k3].start_column;
            int j1 = aspanlist[k3].end_column;
            if(l != l2)
            {
                l2 = l;
                int j = aspanlist[k3].start_row;
                int k1 = (table.columnPosition(j1) - table.columnPosition(l + 1)) + table.columnWidth(j1);
                int l1 = aspanlist[k3].width - k1;
                int i2 = 0;
                if(!is_span_column_source(-1, l))
                    i2 = JCCell.getWidth(table, -1, l);
                for(int l3 = 0; l3 < table.rows; l3++)
                    if(!is_span_column_source(l3, l))
                        i2 = Math.max(i2, JCCell.getWidth(table, l3, l));

                l1 = Math.max(1, Math.max(i2 + j2, l1 + j2));
                if(Table.isLabel(j, l) && l1 == 1)
                    l1 += j2;
                if(l1 != table.columnWidth(l))
                {
                    table.column_widths.setValue(l, l, l1);
                    flag1 = true;
                }
            }
        }

        if(flag1)
            Size.getCellTotalWidth(table);
    }

    private void adjustHeight()
    {
        int j2 = 2 * table.textHeightOffset();
        boolean flag = false;
        int j4 = 2 * table.shadow_thickness + 1;
        boolean flag1 = false;
        SpanList aspanlist[] = new SpanList[span_list.size()];
        for(int i3 = 0; i3 < aspanlist.length; i3++)
            aspanlist[i3] = new SpanList();

        int j3 = 0;
        int k2 = 0;
        for(; j3 < span_list.size(); j3++)
        {
            JCCellRange jccellrange = (JCCellRange)span_list.elementAt(j3);
            int i = aspanlist[k2].start_row = jccellrange.start_row;
            int i1 = aspanlist[k2].end_row = jccellrange.end_row;
            if(i != i1)
            {
                int k = aspanlist[k2].start_column = jccellrange.start_column;
                if(table.getPixelHeight(i) == 33001)
                {
                    Object obj = table.dataView.getData(i, k);
                    int i4 = JCCell.getHeight(table, null, obj, i, k, table.getFont(i, k));
                    if(obj instanceof Component)
                        i4 = (i4 - j2) + j4;
                    aspanlist[k2].height = i4;
                    k2++;
                }
            }
        }

        int l2 = -999;
        for(int k3 = 0; k3 < k2; k3++)
        {
            int j = aspanlist[k3].start_row;
            int j1 = aspanlist[k3].end_row;
            if(j != l2)
            {
                l2 = j;
                int l = aspanlist[k3].start_column;
                int k1 = (table.rowPosition(j1) - table.rowPosition(j + 1)) + table.rowHeight(j1);
                int l1 = aspanlist[k3].height - k1;
                int i2 = 0;
                if(!is_span_row_source(j, -1))
                    i2 = JCCell.getHeight(table, j, -1);
                for(int l3 = 0; l3 < table.columns; l3++)
                    if(!is_span_row_source(j, l3))
                        i2 = Math.max(i2, JCCell.getHeight(table, j, l3));

                l1 = Math.max(1, Math.max(i2 + j2, l1 + j2));
                if(Table.isLabel(j, l) && l1 == 1)
                    l1 += j2;
                if(l1 != table.rowHeight(j))
                {
                    table.row_heights.setValue(j, j, l1);
                    flag1 = true;
                }
            }
        }

        if(flag1)
            Size.getCellTotalHeight(table);
    }

    private boolean is_span_column_source(int i, int j)
    {
        for(int k = 0; k < span_list.size(); k++)
        {
            JCCellRange jccellrange = (JCCellRange)span_list.elementAt(k);
            if(jccellrange.start_row == i && jccellrange.start_column == j && jccellrange.start_column != jccellrange.end_column)
                return true;
        }

        return false;
    }

    private boolean is_span_row_source(int i, int j)
    {
        for(int k = 0; k < span_list.size(); k++)
        {
            JCCellRange jccellrange = (JCCellRange)span_list.elementAt(k);
            if(jccellrange.start_row == i && jccellrange.start_column == j && jccellrange.start_row != jccellrange.end_row)
                return true;
        }

        return false;
    }

    private Table table;
    JCVector span_list;
    JCVector span_list_orig;
}
