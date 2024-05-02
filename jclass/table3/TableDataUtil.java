// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableDataUtil.java

package jclass.table3;

import java.awt.Component;
import java.util.Vector;

// Referenced classes of package jclass.table3:
//            Chain, Clip, JCCellRange, JCDisplayComponentEvent, 
//            JCTblEnum, Layout, Series, SeriesUtil, 
//            SeriesValue, Shift, Size, Span, 
//            Table, WidgetSeriesValue

class TableDataUtil
{

    static void addColumn(Table table, int i, int j, int k, int l)
    {
        if(i < l - j)
            Shift.columnSeries(table, i, j, 3);
        if(!table.isDisplayable())
            return;
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return;
        }
        Size.setDimensions(table, 3, k, l - 1, -998, 0, i, 1, true);
        resize_table(table);
        if(table.isShowing())
            table.repaint();
        redisplayComponents(table);
    }

    static void addRow(Table table, int i, int j, int k, int l)
    {
        if(i < k - j)
            Shift.rowSeries(table, i, j, 4);
        if(!table.isDisplayable())
            return;
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return;
        } else
        {
            Size.setDimensions(table, 4, k - 1, l, i, j, -998, 0, true);
            resize_table(table);
            redisplayComponents(table);
            return;
        }
    }

    static void deleteColumns(Table table, int i, int j, int k, int l)
    {
        Shift.columnSeries(table, i, j, 1);
        if(!table.isDisplayable())
            return;
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return;
        }
        Size.setDimensions(table, 1, k, l + j, -998, 0, i, j, true);
        if(table.left_column >= l)
            table.left_column = table.clip.horiz_origin = 0;
        resize_table(table);
        redisplayComponents(table);
    }

    static void deleteRow(Table table, int i, int j, int k, int l)
    {
        Shift.rowSeries(table, i, j, 2);
        if(!table.isDisplayable())
            return;
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return;
        }
        Size.setDimensions(table, 2, k + j, l, i, j, -998, 0, true);
        if(table.top_row >= k)
            table.top_row = table.clip.vert_origin = 0;
        resize_table(table);
        redisplayComponents(table);
    }

    static boolean moveColumns(Table table, int i, int j, int k)
    {
        if(!validateMoveColumns(table, i, j, k))
            return false;
        if(i + j == k)
            return true;
        moveColumnSeries(table, i, j, k);
        table.column_widths.move(i, j, k);
        resize_table(table);
        if(table.isVisible())
            table.repaint();
        redisplayComponents(table);
        return true;
    }

    static boolean dragColumn(Table table, int i, int j)
    {
        return moveColumns(table, i, 1, j);
    }

    static boolean moveRows(Table table, int i, int j, int k)
    {
        if(!validateMoveRows(table, i, j, k))
            return false;
        if(i + j == k)
            return true;
        moveRowSeries(table, i, j, k);
        table.row_heights.move(i, j, k);
        resize_table(table);
        if(table.isShowing())
            table.repaint();
        redisplayComponents(table);
        return true;
    }

    static boolean dragRow(Table table, int i, int j)
    {
        return moveRows(table, i, 1, j);
    }

    static void moveColumnSeries(Table table, int i, int j, int k)
    {
        for(int l = 0; l < table.series_list.size(); l++)
        {
            Series series = (Series)table.series_list.elementAt(l);
            SeriesUtil.moveColumns(series, i, j, k);
        }

        selectColumns(table, i, j, k);
        spanColumns(table, i, j, k);
    }

    static void moveRowSeries(Table table, int i, int j, int k)
    {
        for(int l = 0; l < table.series_list.size(); l++)
        {
            Series series = (Series)table.series_list.elementAt(l);
            SeriesUtil.moveRows(series, i, j, k);
        }

        selectRows(table, i, j, k);
        spanRows(table, i, j, k);
    }

    static void selectColumns(Table table, int i, int j, int k)
    {
        if(table.selected_cells.size() == 0)
            return;
        for(int l = table.selected_cells.size() - 1; l >= 0; l--)
        {
            JCCellRange jccellrange = (JCCellRange)table.selected_cells.elementAt(l);
            if(jccellrange.end_column != 0x7fffffff)
            {
                int i1 = Math.min(jccellrange.start_column, jccellrange.end_column);
                int j1 = Math.max(jccellrange.start_column, jccellrange.end_column);
                if(j1 >= i && i1 <= (i + j) - 1 || i1 < k && k <= j1)
                {
                    table.selected_cells.removeElementAt(l);
                } else
                {
                    jccellrange.start_column = adjustPosition(jccellrange.start_column, i, j, k);
                    jccellrange.end_column = adjustPosition(jccellrange.end_column, i, j, k);
                }
            }
        }

    }

    static void selectRows(Table table, int i, int j, int k)
    {
        if(table.selected_cells.size() == 0)
            return;
        for(int l = table.selected_cells.size() - 1; l >= 0; l--)
        {
            JCCellRange jccellrange = (JCCellRange)table.selected_cells.elementAt(l);
            if(jccellrange.end_row != 0x7fffffff)
            {
                int i1 = Math.min(jccellrange.start_row, jccellrange.end_row);
                int j1 = Math.max(jccellrange.start_row, jccellrange.end_row);
                if(j1 >= i && i1 <= (i + j) - 1 || i1 < k && k <= j1)
                {
                    table.selected_cells.removeElementAt(l);
                } else
                {
                    jccellrange.start_row = adjustPosition(jccellrange.start_row, i, j, k);
                    jccellrange.end_row = adjustPosition(jccellrange.end_row, i, j, k);
                }
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
            if(i1 != -998 && i1 != -997 && j1 != -998 && j1 != -997 && j1 != 0x7fffffff)
            {
                jccellrange.start_column = adjustPosition(jccellrange.start_column, i, j, k);
                jccellrange.end_column = (jccellrange.start_column + j1) - i1;
            }
        }

    }

    static void spanRows(Table table, int i, int j, int k)
    {
        if(table.span.span_list_orig == null || table.span.span_list_orig.size() == 0)
            return;
        for(int l = 0; l < table.span.span_list_orig.size(); l++)
        {
            JCCellRange jccellrange = (JCCellRange)table.span.span_list_orig.elementAt(l);
            int i1 = jccellrange.start_row;
            int j1 = jccellrange.end_row;
            if(i1 != -998 && i1 != -997 && j1 != -998 && j1 != -997 && j1 != 0x7fffffff)
            {
                jccellrange.start_row = adjustPosition(jccellrange.start_row, i, j, k);
                jccellrange.end_row = (jccellrange.start_row + j1) - i1;
            }
        }

    }

    static boolean swapColumns(Table table, int i, int j)
    {
        swapColumnSeries(table, i, j);
        int k = table.column_widths.getValue(i);
        int l = table.column_widths.getValue(j);
        table.column_widths.setValue(i, i, l);
        table.column_widths.setValue(j, j, k);
        resize_table(table);
        if(table.isVisible())
            table.repaint();
        redisplayComponents(table);
        return true;
    }

    static void swapColumnSeries(Table table, int i, int j)
    {
        for(int k = 0; k < table.series_list.size(); k++)
        {
            Series series = (Series)table.series_list.elementAt(k);
            SeriesUtil.swapColumns(series, i, j);
        }

        if(table.selected_cells.size() != 0)
            table.selected_cells.removeAllElements();
        spanColumns(table, i, 1, j);
    }

    static boolean swapRows(Table table, int i, int j)
    {
        swapRowSeries(table, i, j);
        int k = table.row_heights.getValue(i);
        int l = table.row_heights.getValue(j);
        table.row_heights.setValue(i, i, l);
        table.row_heights.setValue(j, j, k);
        resize_table(table);
        if(table.isVisible())
            table.repaint();
        redisplayComponents(table);
        return true;
    }

    static void swapRowSeries(Table table, int i, int j)
    {
        for(int k = 0; k < table.series_list.size(); k++)
        {
            Series series = (Series)table.series_list.elementAt(k);
            SeriesUtil.swapRows(series, i, j);
        }

        if(table.selected_cells.size() != 0)
            table.selected_cells.removeAllElements();
        spanRows(table, i, 1, j);
    }

    static boolean swapRowArrays(Table table, int ai[], int ai1[])
    {
        int ai2[] = new int[ai1.length];
        int ai3[] = new int[ai1.length];
        int ai4[] = new int[ai1.length];
        for(int i = 0; i < ai1.length; i++)
            ai3[ai[i]] = i;

        for(int j = 0; j < ai1.length; j++)
            ai2[ai1[j]] = j;

        for(int k = 0; k < ai1.length; k++)
            ai4[k] = ai2[ai[k]];

        for(int l = 0; l < table.series_list.size(); l++)
        {
            Series series = (Series)table.series_list.elementAt(l);
            SeriesUtil.reorderRows(series, ai4);
        }

        Chain chain = new Chain();
        for(int i1 = 0; i1 < table.rows; i1++)
            if(i1 < ai1.length)
                chain.appendNocalc(table.rowHeight(ai3[ai1[i1]]));
            else
                chain.appendNocalc(table.rowHeight(i1));

        chain.calcPositions(0);
        table.row_heights = chain;
        if(table.selected_cells.size() != 0)
            if(table.mode == 1 && table.selection_policy == 3)
            {
                Vector vector = new Vector();
                for(int j1 = 0; j1 < table.selected_cells.size(); j1++)
                {
                    JCCellRange jccellrange = (JCCellRange)table.selected_cells.elementAt(j1);
                    int k1 = Math.min(jccellrange.start_row, jccellrange.end_row);
                    int l1 = Math.max(jccellrange.start_row, jccellrange.end_row);
                    if(l1 == 0x7fffffff)
                        l1 = table.getNumRows() - 1;
                    for(int i2 = k1; i2 <= l1; i2++)
                        if(ai.length > i2 && ai2.length > ai[i2])
                            vector.addElement(new JCCellRange(ai2[ai[i2]], jccellrange.start_column, ai2[ai[i2]], jccellrange.end_column));

                }

                table.setSelectedCells(vector);
            } else
            {
                table.selected_cells.removeAllElements();
            }
        return true;
    }

    static boolean swapColumnArrays(Table table, int ai[], int ai1[])
    {
        int ai2[] = new int[ai1.length];
        int ai3[] = new int[ai1.length];
        int ai4[] = new int[ai1.length];
        for(int i = 0; i < ai1.length; i++)
            ai3[ai[i]] = i;

        for(int j = 0; j < ai1.length; j++)
            ai2[ai1[j]] = j;

        for(int k = 0; k < ai1.length; k++)
            ai4[k] = ai2[ai[k]];

        for(int l = 0; l < table.series_list.size(); l++)
        {
            Series series = (Series)table.series_list.elementAt(l);
            SeriesUtil.reorderColumns(series, ai4);
        }

        Chain chain = new Chain();
        for(int i1 = 0; i1 < table.columns; i1++)
            if(i1 < ai1.length)
                chain.appendNocalc(table.columnWidth(ai3[ai1[i1]]));
            else
                chain.appendNocalc(table.columnWidth(i1));

        chain.calcPositions(0);
        table.column_widths = chain;
        if(table.selected_cells.size() != 0)
            if(table.mode == 1 && table.selection_policy == 3)
            {
                Vector vector = new Vector();
                for(int j1 = 0; j1 < table.selected_cells.size(); j1++)
                {
                    JCCellRange jccellrange = (JCCellRange)table.selected_cells.elementAt(j1);
                    int k1 = Math.min(jccellrange.start_column, jccellrange.end_column);
                    int l1 = Math.max(jccellrange.start_column, jccellrange.end_column);
                    if(l1 == 0x7fffffff)
                        l1 = table.getNumColumns() - 1;
                    for(int i2 = k1; i2 <= l1; i2++)
                        vector.addElement(new JCCellRange(jccellrange.start_row, ai2[ai[i2]], jccellrange.end_row, ai2[ai[i2]]));

                }

                table.setSelectedCells(vector);
            } else
            {
                table.selected_cells.removeAllElements();
            }
        resize_table(table);
        if(table.isVisible())
            table.repaint();
        redisplayComponents(table);
        return true;
    }

    static boolean validateMoveColumns(Table table, int i, int j, int k)
    {
        if(j <= 0)
            return false;
        if(i < 0 || i + j > table.columns)
            return false;
        if(k < 0 || k > table.columns || i <= k && k < i + j)
            return false;
        return j < table.columns - table.frozen_columns;
    }

    static boolean validateMoveRows(Table table, int i, int j, int k)
    {
        if(j <= 0)
            return false;
        if(i < 0 || i + j > table.rows)
            return false;
        if(k < 0 || k > table.rows || i <= k && k < i + j)
            return false;
        return j < table.rows - table.frozen_rows;
    }

    static int adjustPosition(int i, int j, int k, int l)
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

    private static void displayComponent(Table table, Component component, int i, int j)
    {
        if(!Table.isCell(i, j) && !Table.isLabel(i, j) || !component.isShowing())
        {
            return;
        } else
        {
            JCDisplayComponentEvent jcdisplaycomponentevent = new JCDisplayComponentEvent(table, i, j, component);
            table.fireJCDisplayComponentEvent(jcdisplaycomponentevent);
            table.paint(i, j);
            return;
        }
    }

    static void redisplayComponents(Table table)
    {
        Series series = table.component_series;
        for(int i = 0; i < series.size(); i++)
        {
            WidgetSeriesValue widgetseriesvalue = (WidgetSeriesValue)series.getValue(i);
            if(widgetseriesvalue != null)
            {
                int j = ((SeriesValue)series.elementAt(i)).row;
                int k = ((SeriesValue)series.elementAt(i)).column;
                displayComponent(table, widgetseriesvalue.component, j, k);
                for(int l = 0; l < widgetseriesvalue.clones.length; l++)
                    displayComponent(table, widgetseriesvalue.clones[l], widgetseriesvalue.row[l], widgetseriesvalue.column[l]);

            }
        }

    }

    static void resize_table(Table table)
    {
        Layout.doLayout(table);
        if(table.span.doSpansExist())
            table.span.copy();
    }

    TableDataUtil()
    {
    }
}
