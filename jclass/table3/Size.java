// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Size.java

package jclass.table3;

import java.awt.*;
import java.util.Vector;

// Referenced classes of package jclass.table3:
//            Chain, Clip, JCCell, JCTblEnum, 
//            Series, Span, Table, TableDataView

class Size
{

    static int getFontHeight(Table table, int i, int j)
    {
        java.awt.Font font = table.getFont(i, j);
        if(font == null)
            return 0;
        else
            return Toolkit.getDefaultToolkit().getFontMetrics(font).getHeight();
    }

    static int getFontWidth(Table table, int i, int j)
    {
        java.awt.Font font = table.getFont(i, j);
        if(font == null)
            return 0;
        else
            return Toolkit.getDefaultToolkit().getFontMetrics(font).charWidth('W');
    }

    static void setDimensions(Table table, int i, int j, int k, int l, int i1, int j1, int k1, 
            boolean flag)
    {
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return;
        }
        if(l == -998)
        {
            l = 0;
            i1 = table.rows;
        }
        if(j1 == -998)
        {
            j1 = 0;
            k1 = table.columns;
        }
        switch(i)
        {
        case 7: // '\007'
            table.row_heights = new Chain(table.rows);
            table.column_widths = new Chain(table.columns);
            if(table.columns != 0)
            {
                table.column_widths = new Chain(table.columns);
                getColumnWidths(table, -998, 0, -998, 0, null);
                getCellTotalWidth(table);
            } else
            {
                table.cell_total_width = 0;
            }
            if(table.rows != 0)
            {
                getRowHeights(table, -998, 0, -998, 0, null);
                getCellTotalHeight(table);
                return;
            } else
            {
                table.cell_total_height = 0;
                return;
            }

        case 2: // '\002'
        case 4: // '\004'
            if(i == 4)
            {
                Chain chain = new Chain();
                getRowHeights(table, l, (l + i1) - 1, j1, (j1 + k1) - 1, chain);
                table.row_label_width = getRowLabelMaxWidth(table, l, (l + i1) - 1);
                table.row_heights.insert(chain, l);
            } else
            if(i == 2)
            {
                table.row_heights.delete(l, (l + i1) - 1);
                table.row_label_width = getRowLabelMaxWidth(table, -998, 0);
            }
            getCellTotalHeight(table);
            if(flag)
            {
                getColumnWidths(table, l, (l + i1) - 1, j1, (j1 + k1) - 1, null);
                getCellTotalWidth(table);
            }
            return;

        case 1: // '\001'
        case 3: // '\003'
            if(i == 3)
            {
                Chain chain1 = new Chain();
                getColumnWidths(table, l, (l + i1) - 1, j1, (j1 + k1) - 1, chain1);
                table.column_label_height = getColumnLabelMaxHeight(table, j1, (j1 + k1) - 1);
                table.column_widths.insert(chain1, j1);
            } else
            if(i == 1)
            {
                table.column_widths.delete(j1, (j1 + k1) - 1);
                table.column_label_height = getColumnLabelMaxHeight(table, -998, 0);
            }
            getCellTotalWidth(table);
            getRowHeights(table, l, (l + i1) - 1, j1, (j1 + k1) - 1, null);
            getCellTotalHeight(table);
            return;

        case 5: // '\005'
        case 6: // '\006'
        default:
            return;
        }
    }

    static void getCellTotalWidth(Table table)
    {
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return;
        }
        if(table.columns > 0)
        {
            table.cell_total_width = (table.columnPosition(table.columns - 1) + table.columnWidth(table.columns - 1)) - table.frozenColumnWidth();
            return;
        } else
        {
            table.cell_total_width = 0;
            return;
        }
    }

    static void getCellTotalHeight(Table table)
    {
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return;
        }
        if(table.rows > 0)
        {
            table.cell_total_height = (table.rowPosition(table.rows - 1) + table.rowHeight(table.rows - 1)) - table.frozenRowHeight();
            return;
        } else
        {
            table.cell_total_height = 0;
            return;
        }
    }

    static boolean compute(Table table, boolean flag, boolean flag1)
    {
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return false;
        }
        int i = 0;
        int j = 0;
        if(table.visible_columns > 0)
        {
            int k = 0;
            int i1 = Math.min(table.getNumColumns(), table.visible_columns);
            if(i1 <= table.columns - table.frozen_columns)
            {
                k = (table.frozen_columns + i1) - 1;
                i = table.columnPosition(k) + table.columnWidth(k);
            }
            if(i == 0)
            {
                if(table.columns > 0)
                {
                    k = table.columns - 1;
                    i = table.columnPosition(k) + table.columnWidth(k);
                }
                if(i == 0)
                    k = -1;
                i += getColumnWidth(table, k + 1, i1);
            }
            i += table.rowLabelWidth() + table.rowLabelExtraWidth() + 2 * table.frame_shadow;
            if(table.hasVertSB())
                i += table.vertSBwidth();
        } else
        if(flag)
        {
            i = table.cell_total_width + table.frozenColumnWidth() + table.rowLabelWidth() + table.rowLabelExtraWidth() + (table.columns <= 0 ? 0 : 2 * table.frame_shadow);
            if(table.hasVertSB())
                i += table.vertSBwidth();
        } else
        {
            i = table.containerWidth() <= 0 ? table.desired_width : table.containerWidth();
        }
        if(table.visible_rows > 0)
        {
            int l = 0;
            int j1 = Math.min(table.getNumRows(), table.visible_rows);
            if(j1 <= table.rows - table.frozen_rows)
            {
                l = (table.frozen_rows + j1) - 1;
                j = table.rowPosition(l) + table.rowHeight(l);
            }
            if(j == 0)
            {
                if(table.rows > 0)
                {
                    l = table.rows - 1;
                    j = table.rowPosition(l) + table.rowHeight(l);
                }
                if(j == 0)
                    l = -1;
                j += getRowHeight(table, l + 1, j1);
            }
            j += table.columnLabelHeight() + table.colLabelExtraHeight() + 2 * table.frame_shadow;
            if(table.hasHorizSB())
                j += table.horizSBheight();
        } else
        if(flag1)
        {
            j = table.cell_total_height + table.frozenRowHeight() + table.columnLabelHeight() + table.colLabelExtraHeight() + (table.rows <= 0 ? 0 : 2 * table.frame_shadow);
            if(table.hasHorizSB())
                j += table.horizSBheight();
        } else
        {
            j = table.containerHeight() <= 0 ? table.desired_height : table.containerHeight();
        }
        table.desired_width = i;
        table.desired_height = j;
        return true;
    }

    static int getSingleValue(Series series, boolean flag)
    {
        Object obj = null;
        if(series.size() == 1)
            obj = series.getDefault();
        else
        if(series.size() == 2)
        {
            char c = flag ? '\uFC1A' : '\uFC1B';
            char c2 = flag ? '\uFC1B' : '\uFC1A';
            int i = series.findExact(c, c2);
            if(i != -999)
            {
                obj = series.getValue(i);
            } else
            {
                char c1 = flag ? '\uFC1A' : '\uFFFF';
                char c3 = flag ? '\uFFFF' : '\uFC1A';
                int j = series.findExact(c1, c3);
                if(j != -999)
                    obj = series.getDefault();
            }
        }
        if(obj != null)
            return ((Integer)obj).intValue();
        else
            return -900;
    }

    static int getQuickSize(Series series, Series series1, int i, int j, boolean flag)
    {
        int k = -900;
        int l = getSingleValue(series, flag);
        if(l == -999)
            k = getSingleValue(series1, flag);
        else
            return l;
        if(k != -900)
            return k * i + j;
        else
            return -900;
    }

    static void getColumnWidths(Table table, int i, int j, int k, int l, Chain chain)
    {
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return;
        }
        boolean flag = chain == null;
        int k1 = 2 * table.textWidthOffset();
        int k3 = 0;
        boolean flag1 = false;
        int i2;
        int j2;
        char c;
        int l1 = i2 = j2 = c = '\uFC19';
        if(table.columns == 0)
            return;
        if(k >= table.columns)
            return;
        if(i == 0 && j == table.rows - 1)
            flag1 = true;
        else
        if(i == -998)
        {
            boolean flag2 = true;
            i = 0;
            j = table.rows - 1;
        }
        if(k == -998)
        {
            k = 0;
            l = table.columns - 1;
        } else
        {
            l = Math.min(l, table.columns - 1);
        }
        if(flag)
        {
            int i1 = getQuickSize(table.pixelwidth_series, table.charwidth_series, getFontWidth(table, -998, -998), k1, true);
            if(i1 >= 0 && i1 != 33001 && i1 != 33002 && i1 != 33000)
            {
                table.column_widths.setValue(k, l, i1);
                return;
            }
        }
        for(int l3 = k; l3 <= l; l3++)
        {
            int j1 = table.getPixelWidth(l3);
            if(flag && j1 == 33000)
            {
                j1 = table.columnWidth(l3);
                if(j1 <= 0)
                    j1 = table.getCharWidth(l3) * getFontWidth(table, -997, l3) + k1;
            } else
            if(j1 == -999 || !flag && j1 == 33000)
                j1 = table.getCharWidth(l3) * getFontWidth(table, -997, l3) + k1;
            else
            if(j1 == 33001)
                j1 = calcLargestWidth(table, -1, table.rows - 1, l3, 0);
            else
            if(j1 == 33002)
                if(table.estimate_count == -998 || table.estimate_count == -997 || table.estimate_count == 0x7fffffff)
                    j1 = calcLargestWidth(table, -1, table.rows - 1, l3, 0);
                else
                    j1 = calcLargestWidth(table, -1, table.estimate_count, l3, 0);
            j2 = j1;
            int k2 = l3;
            if(flag)
            {
                if(l1 == -999)
                {
                    int l2;
                    l1 = l2 = j2;
                    i2 = k3 = k2;
                } else
                if(l1 != j2)
                {
                    table.column_widths.setValue(i2, k3, l1);
                    i2 = k3 = k2;
                    int i3;
                    l1 = i3 = j2;
                } else
                {
                    k3 = k2;
                    int j3 = j2;
                }
            } else
            {
                chain.append(j2);
            }
        }

        if(j2 != -999 && flag)
            table.column_widths.setValue(i2, k3, l1);
    }

    private static int getRowHeight(Table table, int i, int j)
    {
        int k = 0;
        int l = 2 * table.textHeightOffset();
        for(int i1 = i; i1 <= j; i1++)
        {
            int j1 = table.getPixelHeight(i1);
            if(j1 == -999 || j1 == 33000)
                j1 = table.getCharHeight(i1) * getFontHeight(table, i1, -997) + l;
            else
            if(j1 == 33001)
            {
                j1 = calcLargestHeight(table, i1, -1, table.columns - 1, 0);
                if(j1 == 0)
                    j1 = table.getCharHeight(i1) * getFontHeight(table, i1, -997) + l;
            } else
            if(j1 == 33002)
            {
                if(table.estimate_count == -998 || table.estimate_count == -997 || table.estimate_count == 0x7fffffff)
                    j1 = calcLargestHeight(table, i1, -1, table.columns - 1, 0);
                else
                    j1 = calcLargestHeight(table, i1, -1, table.estimate_count, 0);
                if(j1 == 0)
                    j1 = table.getCharHeight(i1) * getFontHeight(table, i1, -997) + l;
            }
            k += j1;
        }

        return k;
    }

    private static int getColumnWidth(Table table, int i, int j)
    {
        int k = 0;
        int l = 2 * table.textWidthOffset();
        for(int i1 = i; i1 <= j; i1++)
        {
            int j1 = table.getPixelWidth(i1);
            if(j1 == -999 || j1 == 33000)
                j1 = table.getCharWidth(i1) * getFontWidth(table, -997, i1) + l;
            else
            if(j1 == 33001)
            {
                j1 = calcLargestWidth(table, -1, table.rows - 1, i1, 0);
                if(j1 == 0)
                    j1 = table.getCharWidth(i1) * getFontWidth(table, -997, i1) + l;
            } else
            if(j1 == 33002)
            {
                if(table.estimate_count == -998 || table.estimate_count == -997 || table.estimate_count == 0x7fffffff)
                    j1 = calcLargestWidth(table, -1, table.rows - 1, i1, 0);
                else
                    j1 = calcLargestWidth(table, -1, table.estimate_count, i1, 0);
                if(j1 == 0)
                    j1 = table.getCharWidth(i1) * getFontWidth(table, -997, i1) + l;
            }
            k += j1;
        }

        return k;
    }

    static void getRowHeights(Table table, int i, int j, int k, int l, Chain chain)
    {
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return;
        }
        boolean flag = chain == null;
        int k1 = 2 * table.textHeightOffset();
        int k3 = 0;
        boolean flag1 = false;
        int i2;
        int j2;
        char c;
        int l1 = i2 = j2 = c = '\uFC19';
        if(table.rows == 0)
            return;
        if(i >= table.rows)
            return;
        if(k == 0 && l == table.columns - 1)
            flag1 = true;
        else
        if(k == -998)
        {
            boolean flag2 = true;
            k = 0;
            l = table.columns - 1;
        }
        if(i == -998)
        {
            i = 0;
            j = table.rows - 1;
        } else
        {
            j = Math.min(j, table.rows - 1);
        }
        if(flag)
        {
            int i1 = getQuickSize(table.pixelheight_series, table.charheight_series, getFontHeight(table, -998, -998), k1, false);
            if(i1 >= 0 && i1 != 33001 && i1 != 33002 && i1 != 33000)
            {
                table.row_heights.setValue(i, j, i1);
                return;
            }
        }
        for(int l3 = i; l3 <= j; l3++)
        {
            int j1 = table.getPixelHeight(l3);
            if(flag && j1 == 33000)
            {
                j1 = table.rowHeight(l3);
                if(j1 <= 0)
                    j1 = table.getCharHeight(l3) * getFontHeight(table, l3, -997) + k1;
            } else
            if(j1 == -999 || !flag && j1 == 33000)
                j1 = table.getCharHeight(l3) * getFontHeight(table, l3, -997) + k1;
            else
            if(j1 == 33001)
                j1 = calcLargestHeight(table, l3, -1, table.columns - 1, 0);
            else
            if(j1 == 33002)
                if(table.estimate_count == -998 || table.estimate_count == -997 || table.estimate_count == 0x7fffffff)
                    j1 = calcLargestHeight(table, l3, -1, table.columns - 1, 0);
                else
                    j1 = calcLargestHeight(table, l3, -1, table.estimate_count, 0);
            j2 = j1;
            int k2 = l3;
            if(flag)
            {
                if(l1 == -999)
                {
                    int l2;
                    l1 = l2 = j2;
                    i2 = k3 = k2;
                } else
                if(l1 != j2)
                {
                    table.row_heights.setValue(i2, k3, l1);
                    int i3;
                    l1 = i3 = j2;
                    i2 = k3 = k2;
                } else
                {
                    k3 = k2;
                    int j3 = j2;
                }
            } else
            {
                chain.append(j2);
            }
        }

        if(j2 != -999 && flag)
            table.row_heights.setValue(i2, k3, l1);
    }

    private static boolean hasValue(Table table, int i, int j)
    {
        Component component = table.getComponent(i, j);
        return component != null || table.dataView.getData(i, j) != null;
    }

    static int getColumnLabelMaxHeight(Table table, int i, int j)
    {
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return 0;
        }
        int l = table.textHeightOffset() * 2;
        int i1 = 0;
        if(table.columns == 0)
            return 0;
        if(i == -998)
        {
            i = 0;
            j = table.columns - 1;
        } else
        {
            i1 = table.column_label_height;
        }
        int k = table.getPixelHeight(-1);
        if(i1 <= 0 && k > 0 && k != 33001 && k != 33002 && k != 33000)
            i1 = k;
        if(!table.column_label_display)
            return 0;
        if(k == 33000)
            if(table.column_label_height <= 0)
                return Math.max(i1, table.getCharHeight(-1) * getFontHeight(table, -1, -998));
            else
                return Math.max(i1, table.column_label_height);
        if(!table.hasComponents() && !table.column_label_display)
            return i1;
        if(k == -999 && !table.hasComponents())
            return Math.max(i1, table.getCharHeight(-1) * getFontHeight(table, -1, -998));
        if(k != 33001 && k != 33002 && !hasCellComponents(table))
            return Math.max(1, k - l);
        for(int j1 = i; j1 <= j; j1++)
            if(hasValue(table, -1, j1))
                i1 = Math.max(i1, calcLargestHeight(table, -1, j1, j1, 0));

        return i1;
    }

    static int getRowLabelMaxWidth(Table table, int i, int j)
    {
        if(!table.repaint)
        {
            table.needs_recalc = true;
            return 0;
        }
        int l = table.textWidthOffset() * 2;
        int i1 = 0;
        if(table.rows == 0)
            return 0;
        if(i == -998)
        {
            i = 0;
            j = table.rows - 1;
        } else
        {
            i1 = table.row_label_width;
        }
        int k = table.getPixelWidth(-1);
        if(i1 <= 0 && k > 0 && k != 33001 && k != 33002 && k != 33000)
            i1 = k;
        if(!table.row_label_display)
            return 0;
        if(k == 33000)
            if(table.row_label_width <= 0)
                return Math.max(i1, table.getCharWidth(-1) * getFontWidth(table, -998, -1));
            else
                return Math.max(i1, table.row_label_width);
        if(!table.hasComponents() && !table.row_label_display)
            return i1;
        if(k == -999 && !table.hasComponents())
            return Math.max(i1, table.getCharWidth(-1) * getFontWidth(table, -998, -1));
        if(k != 33001 && k != 33002 && !hasCellComponents(table))
            return Math.max(1, k - l);
        for(int j1 = i; j1 <= j; j1++)
            if(hasValue(table, j1, -1))
                i1 = Math.max(i1, calcLargestWidth(table, j1, j1, -1, 0));

        return i1;
    }

    static boolean hasCellComponents(Table table)
    {
        return table.hasComponents() && !hasLabelComponents(table);
    }

    static boolean hasLabelComponents(Table table)
    {
        Clip clip = Clip.find_by_type(table, 4);
        if(clip != null && clip.countComponents() > 0)
            return true;
        clip = Clip.find_by_type(table, 5);
        if(clip != null && clip.countComponents() > 0)
            return true;
        clip = Clip.find_by_type(table, 6);
        if(clip != null && clip.countComponents() > 0)
            return true;
        clip = Clip.find_by_type(table, 7);
        return clip != null && clip.countComponents() > 0;
    }

    static int calcLargestHeight(Table table, int i, int j, int k, int l)
    {
        int j1 = i != -1 ? 2 * table.textHeightOffset() : 0;
        int k1 = 2 * table.shadow_thickness + 1;
        for(int l1 = j; l1 <= k && l1 < table.columns; l1++)
            if(!table.span.doSpansExist() || !table.span.isSpanned(i, l1))
            {
                Object obj = table.getComponent(i, l1);
                if(obj == null)
                    obj = table.dataView.getData(i, l1);
                int i1 = JCCell.getHeight(table, null, obj, i, l1, table.getFont(i, l1));
                if(i1 > 0)
                    l = Math.max(l, i1 + ((obj instanceof Component) ? k1 : j1));
            }

        return l;
    }

    static int calcLargestWidth(Table table, int i, int j, int k, int l)
    {
        int j1 = k != -1 ? 2 * table.textWidthOffset() : 0;
        int k1 = 2 * table.shadow_thickness + 1;
        for(int l1 = i; l1 <= j && l1 < table.rows; l1++)
            if(!table.span.doSpansExist() || !table.span.isSpanned(l1, k))
            {
                Object obj = table.getComponent(l1, k);
                if(obj == null)
                    obj = table.dataView.getData(l1, k);
                int i1 = JCCell.getWidth(table, null, obj, l1, k, table.getFont(l1, k));
                if(i1 > 0)
                    l = Math.max(l, i1 + ((obj instanceof Component) ? k1 : j1));
            }

        return l;
    }

    Size()
    {
    }

    static final int DELETECOLUMNS = 1;
    static final int DELETEROWS = 2;
    static final int INSERTCOLUMNS = 3;
    static final int INSERTROWS = 4;
    static final int MOVECOLUMNS = 5;
    static final int MOVEROWS = 6;
    static final int RESET = 7;
    private static final int NOTFOUND = -900;
}
