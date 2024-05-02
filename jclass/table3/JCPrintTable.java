// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCPrintTable.java

package jclass.table3;

import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JComponent;
import jclass.util.JCListenerList;
import jclass.util.JCVector;

// Referenced classes of package jclass.table3:
//            Clip, Draw, JCCellPosition, JCCellRange, 
//            JCPrintEvent, JCTblEnum, Series, Size, 
//            Span, Table, TableDataView

public class JCPrintTable
{

    public JCPrintTable(Table table1)
    {
        page_resolution = 72;
        margin = new Insets(72, 72, 72, 72);
        margin_unit = 1;
        table = table1;
        print_table = new Table();
        print_table.setPrinting(true);
        Clip.setPrintTable(this);
        setupPrintTable();
    }

    public void paintPage(Graphics g, int i)
    {
        print_table.setDoubleBuffer(false);
        current_page = i;
        JCCellRange jccellrange = getPageCellRange(i);
        Dimension dimension = getTablePageDimension(jccellrange);
        print_table.reshape(0, 0, dimension.width, dimension.height);
        g.setFont(new Font("Dialog", 0, 12));
        g.setColor(Color.white);
        print_table.paint(g, jccellrange);
        print_table.setDoubleBuffer(true);
    }

    public void paintPage(Graphics g, JCCellRange jccellrange)
    {
        Dimension dimension = new Dimension(0, 0);
        dimension.width = ((print_table.getColumnPixelWidth(-1) + print_table.frozenColumnWidth() + print_table.columnPosition(jccellrange.end_column)) - print_table.columnPosition(jccellrange.start_column)) + print_table.getColumnPixelWidth(jccellrange.end_column);
        dimension.height = ((print_table.getRowPixelHeight(-1) + print_table.frozenRowHeight() + print_table.rowPosition(jccellrange.end_row)) - print_table.rowPosition(jccellrange.start_row)) + print_table.getRowPixelHeight(jccellrange.end_row);
        g.setColor(Color.white);
        g.fillRect(0, 0, page_width, page_height);
        Point point = getTableOffset();
        g.setClip(point.x, point.y, table_width, table_height);
        if(print_table.span.span_list.size() != 0)
            splitSpans(jccellrange);
        int i = print_table.getFrozenColumns();
        if(i > 0)
        {
            for(int j = 0; j < i; j++)
                Draw.cell(print_table, -1, j);

        }
        for(int k = jccellrange.start_column; k <= jccellrange.end_column; k++)
            Draw.cell(print_table, -1, k);

        int l = print_table.getFrozenRows();
        if(l > 0)
        {
            for(int i1 = 0; i1 < l; i1++)
                Draw.cell(print_table, i1, -1);

        }
        for(int j1 = jccellrange.start_row; j1 <= jccellrange.end_row; j1++)
            Draw.cell(print_table, j1, -1);

        if(i > 0 && l > 0)
        {
            for(int k1 = 0; k1 < l; k1++)
            {
                for(int k2 = 0; k2 < i; k2++)
                    Draw.cell(print_table, k1, k2);

            }

        }
        if(i > 0)
        {
            for(int l1 = jccellrange.start_row; l1 <= jccellrange.end_row; l1++)
            {
                for(int l2 = 0; l2 < i; l2++)
                    Draw.cell(print_table, l1, l2);

            }

        }
        if(l > 0)
        {
            for(int i2 = 0; i2 < l; i2++)
            {
                for(int i3 = jccellrange.start_column; i3 <= jccellrange.end_column; i3++)
                    Draw.cell(print_table, i2, i3);

            }

        }
        for(int j2 = jccellrange.start_row; j2 <= jccellrange.end_row; j2++)
        {
            for(int j3 = jccellrange.start_column; j3 <= jccellrange.end_column; j3++)
                Draw.cell(print_table, j2, j3);

        }

        doPrintPageHeader(g, current_page);
        if(current_page == getNumPages() - 1)
        {
            Point point1 = new Point(0, 0);
            Point point2 = getPosition(jccellrange, jccellrange.end_row, jccellrange.end_column, point1);
            doPrintEnd(g, current_page, point2.y + print_table.getRowPixelHeight(jccellrange.end_row));
        }
        doPrintBody(g, current_page);
        doPrintPageFooter(g, current_page);
    }

    public Point getPosition(JCCellRange jccellrange, int i, int j, Point point)
    {
        int k = print_table.getFrozenColumns();
        int l = print_table.getFrozenRows();
        if(i == -1)
        {
            if(j < k)
            {
                if(print_table.row_label_placement == 0)
                {
                    if(print_table.frozen_column_placement == 0)
                        point.x = getRowLabelWidth();
                    else
                        point.x = print_table.size().width - print_table.frozenColumnWidth();
                } else
                if(print_table.frozen_column_placement == 0)
                    point.x = 0;
                else
                    point.x = print_table.size().width - getRowLabelWidth() - print_table.frozenColumnWidth();
                point.x += print_table.columnPosition(j) - print_table.columnPosition(0);
            } else
            {
                if(print_table.row_label_placement == 0)
                    point.x = getRowLabelWidth();
                else
                    point.x = 0;
                if(k > 0 && print_table.frozen_column_placement == 0)
                    point.x += print_table.frozenColumnWidth();
                point.x += print_table.columnPosition(j) - print_table.columnPosition(jccellrange.start_column);
            }
            if(print_table.column_label_placement == 0)
                point.y = 0;
            else
                point.y = print_table.size().height - getColumnLabelHeight();
        } else
        if(j == -1)
        {
            if(print_table.row_label_placement == 0)
                point.x = 0;
            else
                point.x = print_table.size().width - getRowLabelWidth();
            if(i < l)
            {
                if(print_table.column_label_placement == 0)
                {
                    if(print_table.frozen_row_placement == 0)
                        point.y = getColumnLabelHeight();
                    else
                        point.y = print_table.size().height - print_table.frozenRowHeight();
                } else
                if(print_table.frozen_row_placement == 0)
                    point.y = 0;
                else
                    point.y = print_table.size().height - getColumnLabelHeight() - print_table.frozenRowHeight();
                point.y += print_table.rowPosition(i) - print_table.rowPosition(0);
            } else
            {
                if(print_table.column_label_placement == 0)
                    point.y = getColumnLabelHeight();
                else
                    point.y = 0;
                if(l > 0 && print_table.frozen_row_placement == 0)
                    point.y += print_table.frozenRowHeight();
                point.y += print_table.rowPosition(i) - print_table.rowPosition(jccellrange.start_row);
            }
        } else
        {
            if(j < k)
            {
                if(print_table.row_label_placement == 0)
                {
                    if(print_table.frozen_column_placement == 0)
                        point.x = getRowLabelWidth();
                    else
                        point.x = print_table.size().width - print_table.frozenColumnWidth();
                } else
                if(print_table.frozen_column_placement == 0)
                    point.x = 0;
                else
                    point.x = print_table.size().width - getRowLabelWidth() - print_table.frozenColumnWidth();
                point.x += print_table.columnPosition(j) - print_table.columnPosition(0);
            } else
            {
                if(print_table.row_label_placement == 0)
                    point.x = getRowLabelWidth();
                else
                    point.x = 0;
                if(k > 0 && print_table.frozen_column_placement == 0)
                    point.x += print_table.frozenColumnWidth();
                point.x += print_table.columnPosition(j) - print_table.columnPosition(jccellrange.start_column);
            }
            if(i < l)
            {
                if(print_table.column_label_placement == 0)
                {
                    if(print_table.frozen_row_placement == 0)
                        point.y = getColumnLabelHeight();
                    else
                        point.y = print_table.size().height - print_table.frozenRowHeight();
                } else
                if(print_table.frozen_row_placement == 0)
                    point.y = 0;
                else
                    point.y = print_table.size().height - getColumnLabelHeight() - print_table.frozenRowHeight();
                point.y += print_table.rowPosition(i) - print_table.rowPosition(0);
            } else
            {
                if(print_table.column_label_placement == 0)
                    point.y = getColumnLabelHeight();
                else
                    point.y = 0;
                if(l > 0 && print_table.frozen_row_placement == 0)
                    point.y += print_table.frozenRowHeight();
                point.y += print_table.rowPosition(i) - print_table.rowPosition(jccellrange.start_row);
            }
        }
        Point point1 = getTableOffset();
        point.x += point1.x;
        point.y += point1.y;
        return point;
    }

    public void setupPrintTable()
    {
        TableData tabledata = table.getDataView().getDataSource();
        print_table.getDataView().setDataSource(tabledata);
        if(print_table.bg_series.getDefault() == null)
            print_table.bg_series.setDefault(table.getBackground());
        if(print_table.fg_series.getDefault() == null)
            print_table.fg_series.setDefault(table.getForeground());
        if(print_table.font_series.getDefault() == null)
            print_table.font_series.setDefault(table.getFont());
        print_table.column_label_height = Size.getColumnLabelMaxHeight(table, -998, 0);
        print_table.row_label_width = Size.getRowLabelMaxWidth(table, -998, 0);
        Size.setDimensions(print_table, 7, print_table.getNumRows(), print_table.getNumColumns(), -998, 0, -998, 0, true);
        print_table.clip_arrows = table.clip_arrows;
        print_table.column_label_placement = table.column_label_placement;
        print_table.column_label_offset = table.column_label_offset;
        print_table.column_label_display = table.column_label_display;
        print_table.frame_bordertype = table.frame_bordertype;
        print_table.frame_shadow = table.frame_shadow;
        print_table.frozen_columns = table.frozen_columns;
        print_table.frozen_rows = table.frozen_rows;
        print_table.frozen_column_placement = table.frozen_column_placement;
        print_table.frozen_row_placement = table.frozen_row_placement;
        print_table.row_label_placement = table.row_label_placement;
        print_table.row_label_offset = table.row_label_offset;
        print_table.row_label_display = table.row_label_display;
        print_table.repeat_bg_colors = table.repeat_bg_colors;
        print_table.repeat_fg_colors = table.repeat_fg_colors;
        print_table.shadow_thickness = table.shadow_thickness;
        print_table.alignment_series = table.alignment_series;
        print_table.bg_series = table.bg_series;
        print_table.bordersides_series = table.bordersides_series;
        print_table.bordertype_series = table.bordertype_series;
        print_table.cellrenderer_series = table.cellrenderer_series;
        print_table.font_series = table.font_series;
        print_table.fg_series = table.fg_series;
        print_table.setSpans(table.getSpans());
        if(print_table.span.origSpanListSize() > 0)
        {
            print_table.span.copy();
            print_table.span.adjust();
        }
        print_table.dataView = table.dataView;
        for(int i = 0; i < table.getNumRows(); i++)
            print_table.setPixelHeight(i, table.getRowPixelHeight(i));

        for(int j = 0; j < table.getNumColumns(); j++)
            print_table.setPixelWidth(j, table.getColumnPixelWidth(j));

        print_table.defaultCellAppearance = null;
        print_table.defaultCellBorder = null;
    }

    public int getMarginUnits()
    {
        return margin_unit;
    }

    public void setMarginUnits(int i)
    {
        if(margin_unit == 0)
        {
            margin_unit = 0;
            return;
        } else
        {
            margin_unit = 1;
            return;
        }
    }

    public int getNumPages()
    {
        if(page_width * page_height == 0)
            return 0;
        else
            return getNumColumnPages() * getNumRowPages();
    }

    public JCCellRange getPageCellRange(int i)
    {
        if(getNumPages() == 0)
        {
            return null;
        } else
        {
            JCCellPosition jccellposition = getPageStartCell(i);
            JCCellPosition jccellposition1 = getPageEndCell(i);
            return new JCCellRange(jccellposition.row, jccellposition.column, jccellposition1.row, jccellposition1.column);
        }
    }

    public Dimension getPageDimensions()
    {
        return new Dimension(page_width, page_height);
    }

    public void setPageDimensions(int i, int j)
    {
        if(i == 0 || j == 0)
        {
            i = 612;
            j = 792;
            page_resolution = 72;
        }
        page_width = i;
        page_height = j;
        setInternalDimensions();
    }

    public int getPageHeight()
    {
        return page_height;
    }

    public void setPageHeight(int i)
    {
        page_height = i;
        setInternalDimensions();
    }

    public Insets getPageMargins()
    {
        return margin;
    }

    public Insets getDefaultPageMargins()
    {
        margin_unit = 1;
        margin = new Insets(72, 72, 72, 72);
        return margin;
    }

    public void setPageMargins(Insets insets)
    {
        if(insets == null)
            insets = getDefaultPageMargins();
        else
            margin = insets;
        setInternalDimensions();
    }

    public int getPageResolution()
    {
        return page_resolution;
    }

    public void setPageResolution(int i)
    {
        page_resolution = i;
        setInternalDimensions();
    }

    public int getPageWidth()
    {
        return page_width;
    }

    public void setPageWidth(int i)
    {
        page_width = i;
        setInternalDimensions();
    }

    private void splitSpans(JCCellRange jccellrange)
    {
        int i = print_table.span.span_list.size();
        JCVector jcvector = new JCVector();
        for(int j = 0; j < i; j++)
        {
            JCCellRange jccellrange1 = (JCCellRange)print_table.span.span_list.elementAt(j);
            JCCellRange jccellrange2 = new JCCellRange(jccellrange1.start_row, jccellrange1.start_column, jccellrange1.end_row, jccellrange1.end_column);
            if(jccellrange1.start_row < jccellrange.start_row && jccellrange1.end_row >= jccellrange.end_row)
                jccellrange2.reshape(jccellrange2.start_row, jccellrange2.start_column, jccellrange.start_row - 1, jccellrange2.end_column);
            if(jccellrange1.start_row <= jccellrange.end_row && jccellrange1.end_row > jccellrange.end_row)
                jccellrange2.reshape(jccellrange2.start_row, jccellrange2.start_column, jccellrange.end_row, jccellrange2.end_column);
            if(jccellrange1.start_column < jccellrange.start_column && jccellrange1.end_column >= jccellrange.end_column)
                jccellrange2.reshape(jccellrange2.start_row, jccellrange2.start_column, jccellrange2.end_row, jccellrange.start_column - 1);
            if(jccellrange1.start_column <= jccellrange.end_column && jccellrange1.end_column > jccellrange.end_column)
                jccellrange2.reshape(jccellrange2.start_row, jccellrange2.start_column, jccellrange2.end_row, jccellrange.end_column);
            jcvector.addElement(jccellrange2);
        }

        print_table.setSpans(jcvector);
        if(print_table.span.origSpanListSize() > 0)
        {
            print_table.span.copy();
            print_table.span.adjust();
        }
    }

    private JCCellPosition getPageStartCell(int i)
    {
        int l = getNumColumnPages();
        getNumRowPages();
        int i1 = i / l + 1;
        int j1 = i % l + 1;
        int k1 = table.getFrozenRows();
        int l1 = table.getFrozenColumns();
        if(j1 == 1)
        {
            l1 = table.getFrozenColumns();
        } else
        {
            int i2 = (table_width - table.row_label_width - table.frozenColumnWidth()) * (j1 - 1);
            int j = 0;
            for(int k2 = l1; k2 < table.getNumColumns(); k2++)
            {
                j += table.getColumnPixelWidth(k2);
                if(j <= i2)
                    l1 = k2;
            }

            l1++;
        }
        if(i1 == 1)
        {
            k1 = table.getFrozenRows();
        } else
        {
            int j2 = (table_height - table.column_label_height - table.frozenRowHeight()) * (i1 - 1);
            int k = 0;
            for(int l2 = k1; l2 < table.getNumRows(); l2++)
            {
                k += table.getRowPixelHeight(l2);
                if(k <= j2)
                    k1 = l2;
            }

            k1++;
        }
        return new JCCellPosition(k1, l1);
    }

    private JCCellPosition getPageEndCell(int i)
    {
        int l = getNumColumnPages();
        int i1 = getNumRowPages();
        int j1 = i / l + 1;
        int k1 = i % l + 1;
        int l1 = table.getFrozenRows();
        int i2 = table.getFrozenColumns();
        if(k1 == l)
        {
            i2 = table.getNumColumns() - 1;
        } else
        {
            int j2 = (table_width - table.row_label_width - table.frozenColumnWidth()) * k1;
            int j = 0;
            for(int l2 = i2; l2 < table.getNumColumns(); l2++)
            {
                j += table.getColumnPixelWidth(l2);
                if(j <= j2)
                    i2 = l2;
            }

        }
        if(j1 == i1)
        {
            l1 = table.getNumRows() - 1;
        } else
        {
            int k2 = (table_height - table.column_label_height - table.frozenRowHeight()) * j1;
            int k = 0;
            for(int i3 = l1; i3 < table.getNumRows(); i3++)
            {
                k += table.getRowPixelHeight(i3);
                if(k <= k2)
                    l1 = i3;
            }

        }
        return new JCCellPosition(l1, i2);
    }

    public int getNumHorizontalPages()
    {
        return getNumColumnPages();
    }

    private int getNumColumnPages()
    {
        int j = 1;
        int k = table.row_label_width;
        int l = table.frozenColumnWidth();
        if(k + l > table_width)
            return 0;
        int i = k + l;
        for(int i1 = table.getFrozenColumns(); i1 < table.getNumColumns(); i1++)
        {
            i += table.getColumnPixelWidth(i1);
            if(i > table_width)
            {
                i = k + l + table.getColumnPixelWidth(i1);
                j++;
            }
        }

        return j;
    }

    public int getNumVerticalPages()
    {
        return getNumRowPages();
    }

    private int getNumRowPages()
    {
        int j = 1;
        int k = table.column_label_height;
        int l = table.frozenRowHeight();
        if(k + l > table_height)
            return 0;
        int i = k + l;
        for(int i1 = table.getFrozenRows(); i1 < table.getNumRows(); i1++)
        {
            i += table.getRowPixelHeight(i1);
            if(i > table_height)
            {
                i = k + l + table.getRowPixelHeight(i1);
                j++;
            }
        }

        return j;
    }

    private Point getTableOffset()
    {
        int i = margin_unit != 0 ? 1 : page_resolution;
        return new Point(i * margin.left, i * margin.top);
    }

    private Dimension getTablePageDimension(JCCellRange jccellrange)
    {
        Dimension dimension = new Dimension(0, 0);
        dimension.width = ((getRowLabelWidth() + print_table.frozenColumnWidth() + print_table.columnPosition(jccellrange.end_column)) - print_table.columnPosition(jccellrange.start_column)) + print_table.getColumnPixelWidth(jccellrange.end_column);
        dimension.height = ((getColumnLabelHeight() + print_table.frozenRowHeight() + print_table.rowPosition(jccellrange.end_row)) - print_table.rowPosition(jccellrange.start_row)) + print_table.getRowPixelHeight(jccellrange.end_row);
        return dimension;
    }

    public Dimension getTableDimensions(int i)
    {
        return getTablePageDimension(getPageCellRange(i));
    }

    private void setInternalDimensions()
    {
        int i = margin_unit != 0 ? 1 : page_resolution;
        table_width = page_width - i * (margin.left + margin.right);
        table_height = page_height - i * (margin.top + margin.bottom);
    }

    private void doPrintPageHeader(Graphics g, int i)
    {
        Graphics g1 = g.create();
        int j = margin_unit != 0 ? 1 : page_resolution;
        g1.setClip(j * margin.left, 0, table_width, j * margin.top);
        g1.translate(j * margin.left, 0);
        JCPrintEvent jcprintevent = new JCPrintEvent(print_table, g1, i + 1, getNumPages(), 1);
        table.fireJCPrintEvent(jcprintevent);
        g1.dispose();
    }

    private void doPrintEnd(Graphics g, int i, int j)
    {
        Graphics g1 = g.create();
        int k = margin_unit != 0 ? 1 : page_resolution;
        g1.setClip(k * margin.left, j, table_width, page_height - j - k * margin.bottom);
        JCPrintEvent jcprintevent = new JCPrintEvent(print_table, g1, i + 1, getNumPages(), 3, this);
        table.fireJCPrintEvent(jcprintevent);
        g1.dispose();
    }

    private void doPrintBody(Graphics g, int i)
    {
        Graphics g1 = g.create();
        JCPrintEvent jcprintevent = new JCPrintEvent(print_table, g1, i + 1, getNumPages(), 4, this);
        table.fireJCPrintEvent(jcprintevent);
        g1.dispose();
    }

    private void doPrintPageFooter(Graphics g, int i)
    {
        Graphics g1 = g.create();
        int j = margin_unit != 0 ? 1 : page_resolution;
        g1.setClip(j * margin.left, page_height - j * margin.bottom, table_width, j * margin.bottom);
        g1.translate(j * margin.left, page_height - j * margin.bottom);
        JCPrintEvent jcprintevent = new JCPrintEvent(print_table, g1, i + 1, getNumPages(), 2, this);
        if(JCListenerList.elements(table.printListeners).hasMoreElements())
            table.fireJCPrintEvent(jcprintevent);
        else
            printPageFooter(jcprintevent);
        g1.dispose();
    }

    private void printPageFooter(JCPrintEvent jcprintevent)
    {
        Graphics g = jcprintevent.getGraphics();
        Rectangle rectangle = g.getClipRect();
        g.setFont(table.getFont());
        String s = "Page " + jcprintevent.getPage() + " of " + jcprintevent.getNumPages();
        g.drawString(s, 0, rectangle.height / 2);
    }

    private int getRowLabelWidth()
    {
        if(print_table.row_label_display)
            return print_table.getColumnPixelWidth(-1);
        else
            return 0;
    }

    private int getColumnLabelHeight()
    {
        if(print_table.column_label_display)
            return print_table.getRowPixelHeight(-1);
        else
            return 0;
    }

    Table table;
    Table print_table;
    int page_width;
    int page_height;
    int page_resolution;
    int table_width;
    int table_height;
    Insets margin;
    int margin_unit;
    public static final int MARGIN_IN_INCHES = 0;
    public static final int MARGIN_IN_PIXELS = 1;
    int current_page;
}
