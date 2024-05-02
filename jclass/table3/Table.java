// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Table.java

package jclass.table3;

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.event.*;
import java.beans.Beans;
import java.io.PrintStream;
import java.util.*;
import javax.swing.JComponent;
import jclass.beans.AboutDialog;
import jclass.cell.CellEditor;
import jclass.cell.CellRenderer;
import jclass.util.*;

// Referenced classes of package jclass.table3:
//            CellBorder, Chain, Clip, DefaultCellAppearance, 
//            DefaultCellBorder, Draw, EditHandler, InputHandler, 
//            JCAdjustable, JCCell, JCCellDisplayListener, JCCellPosition, 
//            JCCellRange, JCCreateComponentListener, JCDisplayComponentListener, JCEnterCellEvent, 
//            JCEnterCellListener, JCPaintEvent, JCPaintListener, JCPrintEvent, 
//            JCPrintListener, JCPrintPreview, JCPrintTable, JCResizeCellEvent, 
//            JCResizeCellListener, JCScrollEvent, JCScrollListener, JCSelectEvent, 
//            JCSelectListener, JCSortListener, JCTblEnum, JCTraverseCellListener, 
//            JCVersion, JDKSupport, KeyPair, LabelTrigger, 
//            Layout, MessageBox, PickColumnsDialog, Property, 
//            PropertyEnum, Select, Series, SeriesUtil, 
//            Size, Sort, Span, StandardCellBorder, 
//            TableData, TableDataView, TablePopupMenu, TableScrollbar, 
//            TraverseHandler, TraverseInitial, Widget, JCCellDisplayEvent, 
//            JCCreateComponentEvent, JCDisplayComponentEvent, JCSortEvent, JCTraverseCellEvent

public class Table extends JComponent
    implements Runnable, MouseListener, MouseMotionListener, KeyListener, ActionListener
{

    public Table()
    {
        this(null, null);
    }

    public Table(Applet applet1, String s)
    {
        rows = 10;
        columns = 5;
        allow_cell_resize = 0;
        auto_scroll = 0;
        clip_arrows = 4;
        column_label_placement = 0;
        column_label_display = true;
        double_buffer = true;
        edit_height_policy = 1;
        edit_width_policy = 0;
        estimate_count = -998;
        focus_rect_color = Color.black;
        frame_bordertype = 3;
        frozen_column_placement = 0;
        frozen_row_placement = 0;
        horizsb_attach = 0;
        horizsb_position = 0;
        horizsb_display = 0;
        ignore_container_size = false;
        jump = 0;
        margin_width = 3;
        margin_height = 2;
        min_cell_visibility = 100;
        mode = 0;
        row_label_placement = 0;
        row_label_display = true;
        repaint = true;
        resize_by_labels_only = false;
        resize_even = false;
        selected_bg_set = false;
        selected_fg_set = false;
        select_include_labels = true;
        selection_mode = 0;
        selection_policy = 0;
        shadow_thickness = 1;
        sort_series = true;
        track_cursor = false;
        traversable = false;
        traverseCycle = true;
        vertsb_attach = 0;
        vertsb_position = 0;
        vertsb_display = 0;
        visible_columns = -999;
        visible_rows = -999;
        advanced_editor_renderers = true;
        isLiveTable = false;
        useCellDoubleBuffer = true;
        trackJCStringURL = true;
        allow_select = true;
        selected_cells = new JCVector();
        select_start_row = -999;
        select_last_row = -999;
        select_start_column = -999;
        select_last_column = -999;
        set_property = true;
        popupMenuEnabled = false;
        printHeader = new String("");
        printFooter = new String("");
        showPrintMenu = true;
        showPrintPreview = true;
        series_list = new JCVector();
        alignment_series = new Series(this, 0);
        bg_series = new Series(this, null);
        bordersides_series = new Series(this, 15);
        bordertype_series = new Series(this, new StandardCellBorder(3));
        celleditor_series = new Series(true, this, null);
        cellrenderer_series = new Series(true, this, null);
        charheight_series = new Series(this, 1);
        charwidth_series = new Series(this, 10);
        font_series = new Series(this, null);
        fg_series = new Series(this, null);
        multiline_series = new Series(this, false);
        pixelheight_series = new Series(this, -999);
        pixelwidth_series = new Series(this, -999);
        userdata_series = new Series(this, null);
        in_setComponent = false;
        column_widths = new Chain();
        row_heights = new Chain();
        column_label_height = -999;
        row_label_width = -999;
        cell_rect = new Rectangle();
        rowlabel_rect = new Rectangle();
        collabel_rect = new Rectangle();
        edit_row = -999;
        edit_column = -999;
        clip_list = new Clip[0];
        cursor = -999;
        set_top_row = false;
        set_left_column = false;
        draw_range = new JCCellRange();
        resize_row = -999;
        resize_column = -999;
        scrolling = false;
        in_cell_value_cb = false;
        in_label_value_cb = false;
        in_get_cell = false;
        in_get_label = false;
        needs_repaint = false;
        needs_recalc = false;
        row_triggers = new Hashtable();
        column_triggers = new Hashtable();
        cell_triggers = new Hashtable();
        sort_column = -999;
        sort_direction = -999;
        isJumping = true;
        first_time = true;
        is_printing = false;
        temp_pos = new Point(0, 0);
        setLayout(null);
        name = s;
        span = new Span(this);
        dataView = new TableDataView(this);
        property = new Property(this);
        editHandler = new EditHandler(this);
        inputHandler = new InputHandler(this);
        traverseHandler = new TraverseHandler(this);
        enableEvents(56L);
        setSeriesDefaults();
        traversable = traversable_series.containsValue(Boolean.TRUE);
        track_cursor = true;
        allow_cell_resize = 1;
        clip = Clip.Create(this, 0);
        if(applet1 != null)
            getParameters(applet1);
    }

    void add(Clip clip1)
    {
        clip1.addMouseListener(this);
        clip1.addMouseMotionListener(this);
        clip1.addKeyListener(this);
        super.add(clip1);
    }

    private void removeClip(Clip clip1)
    {
        if(clip1 == null)
        {
            return;
        } else
        {
            clip1.removeMouseListener(this);
            clip1.removeMouseMotionListener(this);
            clip1.removeKeyListener(this);
            return;
        }
    }

    public void add(TableScrollbar tablescrollbar)
    {
        add(tablescrollbar.getComponent());
    }

    public void addNotify()
    {
        if(!isDisplayable())
            super.addNotify();
        validateRes();
        if(bg_series.getDefault() == null)
        {
            bg_series.setDefault(getBackground());
            if(defaultCellAppearance != null && defaultCellAppearance.background == null)
                defaultCellAppearance.background = (Color)bg_series.getDefault();
        }
        if(fg_series.getDefault() == null)
        {
            fg_series.setDefault(getForeground());
            if(defaultCellAppearance != null && defaultCellAppearance.foreground == null)
                defaultCellAppearance.foreground = (Color)fg_series.getDefault();
        }
        if(font_series.getDefault() == null)
        {
            font_series.setDefault(getFont());
            if(defaultCellAppearance != null && defaultCellAppearance.font == null)
                defaultCellAppearance.font = (Font)font_series.getDefault();
        }
        column_label_height = Size.getColumnLabelMaxHeight(this, -998, 0);
        row_label_width = Size.getRowLabelMaxWidth(this, -998, 0);
        Size.setDimensions(this, 7, rows, columns, -998, 0, -998, 0, true);
        if(span.origSpanListSize() > 0)
        {
            span.copy();
            span.adjust();
        }
        if(cursor != -999)
            setCursor(cursor);
        if(!noMoreDialogs && (Beans.isDesignTime() || JCEnvironment.isNetBeansDesignTime()))
        {
            AboutDialog aboutdialog = new AboutDialog(this, "Table", "table3", JCTVersion.getVersionNumber(), new ItemListener() {

                public void itemStateChanged(ItemEvent itemevent)
                {
                    if(itemevent.getStateChange() == 1)
                    {
                        Table.noMoreDialogs = true;
                        return;
                    } else
                    {
                        Table.noMoreDialogs = false;
                        return;
                    }
                }

            }
);
            aboutdialog.setMessage(JCString.parse(null, "[VERT_SPACE=5][FONT=arial-plain-12]You are using a LITE version of JClass LiveTable[DEFAULT_COLOR].  This version is [NEWLINE]permanently enabled, but is missing many of the advanced features [NEWLINE]found in the full JClass LiveTable product.  To evaluate or purchase [NEWLINE]the full product, please visit [COLOR=blue][HREF=http://www.klg.com/jclass]http://www.klg.com/jclass[/HREF][DEFAULT_COLOR].[NEWLINE][VERT_SPACE=1]"));
            aboutdialog.show();
        }
    }

    public boolean cancelEdit(boolean flag)
    {
        return editHandler.cancel(flag);
    }

    public boolean commitEdit(boolean flag)
    {
        return editHandler.commit(flag);
    }

    public boolean dragColumn(int i, int j)
    {
        return dataView.dragColumn(i, j);
    }

    public boolean dragRow(int i, int j)
    {
        return dataView.dragRow(i, j);
    }

    public int findSpan(int i, int j, JCCellRange jccellrange, Dimension dimension)
    {
        if(span.span_list.size() == 0)
            return -999;
        else
            return span.find(i, j, jccellrange, dimension);
    }

    public boolean getBounds(int i, int j, Rectangle rectangle)
    {
        if(Clip.find(this, i, j) == null)
        {
            return false;
        } else
        {
            JCCell.getBounds(this, null, i, j, rectangle);
            return true;
        }
    }

    public boolean getAdvancedEditorRenderers()
    {
        return advanced_editor_renderers;
    }

    public void setAdvancedEditorRenderers(boolean flag)
    {
        advanced_editor_renderers = flag;
        dataView.InitEditorRenderers();
    }

    public int getColumnPixelWidth(int i)
    {
        return columnWidth(i);
    }

    public JCCellPosition getCurrentCell()
    {
        if(edit_row < 0)
            return null;
        else
            return new JCCellPosition(edit_row, edit_column);
    }

    public Component getEditingComponent()
    {
        if(editHandler.getCellEditor() == null)
            return null;
        else
            return editHandler.getCellEditor().getComponent();
    }

    public int getLeftColumn()
    {
        if(left_column < frozen_columns)
        {
            JCCellRange jccellrange = new JCCellRange();
            if(getVisibleColumns(jccellrange))
                left_column = jccellrange.start_column;
        }
        return left_column;
    }

    public void setLeftColumn(int i)
    {
        left_column = Math.min(i, columns - 1);
        set_left_column = true;
        setProperty(0xc0010);
    }

    public int getNumColumns()
    {
        return columns;
    }

    public int getNumRows()
    {
        return rows;
    }

    public void getParameters(Applet applet1)
    {
        applet = applet1;
        applet_context = null;
        if(applet1 != null)
            try
            {
                applet_context = applet1.getAppletContext();
            }
            catch(Exception _ex) { }
        if(applet_context != null && isDisplayable())
            reset();
    }

    public void getParameters(Applet applet1, String s)
    {
        if(applet1 != null)
        {
            applet = applet1;
            try
            {
                applet_context = applet1.getAppletContext();
            }
            catch(Exception _ex) { }
        }
        if(isDisplayable())
            reset();
    }

    public JCCellPosition getPosition(Component component, JCCellPosition jccellposition)
    {
        if(component_series.size() > 0)
            return Widget.getPosition(this, component, jccellposition);
        if(jccellposition != null)
            jccellposition.row = -999;
        return null;
    }

    public Point getPosition(int i, int j, Point point)
    {
        Clip clip1 = Clip.find(this, i, j);
        if(clip1 == null)
        {
            if(point != null)
                point.x = 0x7fffffff;
            return null;
        }
        if(clip1.type == 8)
            return clip1.getPrintTable().getPosition(clip1.range, i, j, point);
        int k = 0;
        int l = 0;
        int i1 = clip1.range.start_column < 0 ? 0 : columnPosition(clip1.range.start_column);
        int j1 = clip1.range.start_row < 0 ? 0 : rowPosition(clip1.range.start_row);
        if(j < 0)
            l = rowPosition(i) - j1 - clip1.vert_origin;
        else
        if(i < 0)
        {
            k = columnPosition(j) - i1 - clip1.horiz_origin;
        } else
        {
            k = columnPosition(j) - i1 - clip1.horiz_origin;
            l = rowPosition(i) - j1 - clip1.vert_origin;
        }
        if(point != null)
            point.move(k, l);
        if(point == null)
            return new Point(k, l);
        else
            return null;
    }

    public int getRowPixelHeight(int i)
    {
        return rowHeight(i);
    }

    public Vector getSelectedCells()
    {
        return selected_cells;
    }

    public void setStyled()
    {
        SystemColor systemcolor = SystemColor.window;
        SystemColor systemcolor1 = SystemColor.windowText;
        SystemColor systemcolor2 = SystemColor.control;
        SystemColor systemcolor3 = SystemColor.controlText;
        SystemColor systemcolor4 = SystemColor.textHighlight;
        SystemColor systemcolor5 = SystemColor.textHighlightText;
        setBackground(-997, -997, systemcolor);
        setForeground(-997, -997, systemcolor1);
        setBackground(-1, -1, systemcolor2);
        setForeground(-1, -1, systemcolor3);
        setCellBorderType(-1, -1, new StandardCellBorder(4));
        setCharWidth(-998, 5);
        setProperty(0x80000);
    }

    public void clearSelectedCells()
    {
        setSelectedCells(((Vector) (null)));
    }

    public void setSelectedCells(Vector vector)
    {
        JCVector jcvector;
        if(vector == null)
            jcvector = new JCVector();
        else
        if(vector instanceof JCVector)
            jcvector = (JCVector)vector;
        else
            jcvector = new JCVector(vector);
        select_last_row = select_last_column = -999;
        if(!isDisplayable())
        {
            selected_cells = jcvector;
            return;
        } else
        {
            JCVector jcvector1 = selected_cells;
            selected_cells = jcvector;
            Select.copy(this);
            Select.drawList(this, jcvector1);
            return;
        }
    }

    public void setSelectedCells(JCCellRange jccellrange)
    {
        JCVector jcvector = new JCVector();
        jcvector.addElement(jccellrange);
        setSelectedCells(((Vector) (jcvector)));
    }

    public boolean getSelectedRange(int i, JCCellRange jccellrange)
    {
        if(selected_cells.size() > 0)
            return Select.getSelectedRange(this, i, jccellrange);
        else
            return false;
    }

    public Vector getSpans()
    {
        return span.span_list;
    }

    public void setSpans(Vector vector)
    {
        if(vector == null)
            span.span_list_orig = new JCVector();
        else
        if(vector instanceof JCVector)
            span.span_list_orig = (JCVector)vector;
        else
            span.span_list_orig = new JCVector(vector);
        if(isDisplayable())
        {
            span.copy();
            span.adjust();
        }
        setProperty(0x30c000);
    }

    public int getTopRow()
    {
        if(top_row < frozen_rows)
        {
            JCCellRange jccellrange = new JCCellRange();
            if(getVisibleRows(jccellrange))
                top_row = jccellrange.start_row;
        }
        return top_row;
    }

    public void setTopRow(int i)
    {
        top_row = Math.min(i, rows - 1);
        set_top_row = true;
        setProperty(0xc0400);
    }

    public static final boolean isCell(int i, int j)
    {
        return i >= 0 && j >= 0;
    }

    public static final boolean isColumnLabel(int i, int j)
    {
        return isLabel(i, j) && i == -1;
    }

    public boolean isFocusTraversable()
    {
        return false;
    }

    public static final boolean isLabel(int i, int j)
    {
        if(i == -1)
            return j >= 0;
        if(j == -1)
            return i >= 0;
        else
            return false;
    }

    public static final boolean isRowLabel(int i, int j)
    {
        return isLabel(i, j) && j == -1;
    }

    public boolean isSelected(int i, int j)
    {
        if(selected_cells.size() == 0)
            return false;
        if(isLabel(i, j) && !select_include_labels)
            return false;
        else
            return Select.isSelected(this, i, j);
    }

    public boolean isTraversable(int i, int j)
    {
        if(!isDisplayable())
            return false;
        if(!IS_TRAVERSABLE(i, j))
            return false;
        if(span.span_list.size() > 0)
        {
            JCCellPosition jccellposition = new JCCellPosition();
            if(span.find(i, j, jccellposition) != -999)
                return i == jccellposition.row && j == jccellposition.column;
        }
        return true;
    }

    public boolean isVisible(int i, int j)
    {
        return isRowVisible(i) && isColumnVisible(j);
    }

    public synchronized void layout()
    {
        if(repaint && isDisplayable())
        {
            Layout.doLayout(this);
            return;
        } else
        {
            needs_repaint = needs_recalc = true;
            return;
        }
    }

    public boolean makeColumnVisible(int i)
    {
        if(!isDisplayable())
            return false;
        if(i < frozen_columns || min_cell_visibility == 0)
            return true;
        i = Math.max(0, Math.min(i, columns - 1));
        if(!clip.range.inColumnRange(i))
            return false;
        int j = getColumnVisibleValue(i);
        if(j == -999)
            return true;
        if(j < 0 || j > cell_total_width || clip.horiz_origin == j)
            return true;
        if(repaint)
        {
            isJumping = false;
            horiz_sb.setValue(j);
            isJumping = true;
        } else
        {
            needs_repaint = true;
            clip.horiz_origin = j;
        }
        return true;
    }

    int getColumnVisibleValue(int i)
    {
        double d1 = ((double)columnWidth(i) * (100D - (double)min_cell_visibility)) / 100D;
        int j = columnPosition(i) - clip.xOffset();
        double d;
        if(j < clip.horiz_origin)
            d = Math.min(clip.horiz_origin, (double)j + d1);
        else
        if(j + columnWidth(i) > clip.size().width + clip.horiz_origin)
        {
            int k = (j + columnWidth(i)) - (clip.size().width + clip.horiz_origin);
            if(j - k < clip.horiz_origin)
            {
                d = j;
            } else
            {
                if((double)k <= d1)
                    return -999;
                d = (double)(clip.horiz_origin + k) - d1;
            }
        } else
        {
            return -999;
        }
        return (int)d;
    }

    public boolean makeRowVisible(int i)
    {
        if(!isDisplayable())
            return false;
        if(i < frozen_rows || min_cell_visibility == 0)
            return true;
        i = Math.max(0, Math.min(i, rows - 1));
        if(!clip.range.inRowRange(i))
            return false;
        int j = getRowVisibleValue(i);
        if(j == -999)
            return true;
        if(j < 0 || j > cell_total_height || clip.vert_origin == j)
            return true;
        if(repaint)
        {
            isJumping = false;
            vert_sb.setValue(j);
            isJumping = true;
        } else
        {
            needs_repaint = true;
            clip.vert_origin = j;
        }
        return true;
    }

    int getRowVisibleValue(int i)
    {
        double d1 = ((double)rowHeight(i) * (100D - (double)min_cell_visibility)) / 100D;
        int j = rowPosition(i) - clip.yOffset();
        double d;
        if(j < clip.vert_origin)
            d = Math.min(clip.vert_origin, (double)j + d1);
        else
        if(j + rowHeight(i) > clip.size().height + clip.vert_origin)
        {
            int k = (j + rowHeight(i)) - (clip.size().height + clip.vert_origin);
            if(j - k < clip.vert_origin)
            {
                d = j;
            } else
            {
                if((double)k <= d1)
                    return -999;
                d = (double)(clip.vert_origin + k) - d1;
            }
        } else
        {
            return -999;
        }
        return (int)d;
    }

    public boolean makeVisible(int i, int j)
    {
        if(!isDisplayable())
            return false;
        if(i == -1 || i == -998)
            return makeColumnVisible(j);
        if(j == -1 || j == -998)
            return makeRowVisible(i);
        if(!makeRowVisible(i))
            return false;
        else
            return makeColumnVisible(j);
    }

    public synchronized Dimension minimumSize()
    {
        return preferredSize();
    }

    public synchronized Dimension getMinimumSize()
    {
        return getPreferredSize();
    }

    public void printAll(Graphics g)
    {
        boolean flag = double_buffer;
        double_buffer = false;
        super.printAll(g);
        double_buffer = flag;
    }

    public synchronized Dimension preferredSize()
    {
        layout();
        Size.compute(this, true, true);
        return new Dimension(Math.max(1, desired_width), Math.max(1, desired_height));
    }

    public synchronized Dimension getPreferredSize()
    {
        return preferredSize();
    }

    public void resetSwappedColumns()
    {
        dataView.resetSwappedColumns();
    }

    public void resetSwappedRows()
    {
        dataView.resetSwappedRows();
    }

    public void resetSortedRows()
    {
        dataView.resetSwappedRows();
    }

    public void setBounds(int i, int j, int k, int l)
    {
        synchronized(getTreeLock())
        {
            if(i == location().x && j == location().y && k == size().width && l == size().height)
                return;
        }
        if(k < 0 || l < 0)
            return;
        if(!ignore_container_size)
        {
            Dimension dimension = getToolkit().getScreenSize();
            k = Math.max(1, Math.min(k, dimension.width));
            l = Math.max(1, Math.min(l, dimension.height));
        } else
        {
            k = Math.max(1, k);
            l = Math.max(1, l);
        }
        super.setBounds(i, j, k, l);
        invalidate();
        validate();
        if(first_time && repaint && traversable && rows > 0 && columns > 0 && edit_row != -996)
        {
            boolean _tmp = selected_cells.size() == 0 && selection_policy != 0;
            TraverseInitial.traverse(this, false);
        }
        first_time = false;
    }

    public void setCursor(int i)
    {
        cursor = i;
        for(int j = 0; j < clip_list.length; j++)
            clip_list[j].setCursor(Cursor.getPredefinedCursor(i));

    }

    public boolean sortByColumn(int i, int j)
    {
        return JCSort.sortByColumn(this, i, j, getFrozenRows(), dataView.getNumRows() - 1, null);
    }

    public boolean sortByColumn(int i, int j, int k, int l)
    {
        return JCSort.sortByColumn(this, i, j, k, l, null);
    }

    public boolean sortByColumn(int ai[], int ai1[])
    {
        return JCSort.sortByColumn(this, ai, ai1, getFrozenRows(), dataView.getNumRows() - 1, null);
    }

    public boolean sortByColumn(int ai[], int ai1[], int i, int j)
    {
        return JCSort.sortByColumn(this, ai, ai1, i, j, null);
    }

    public boolean swapColumns(int i, int j)
    {
        return dataView.swapColumns(i, j);
    }

    public boolean swapRows(int i, int j)
    {
        return dataView.swapRows(i, j);
    }

    public boolean traverse(int i, int j, boolean flag, boolean flag1)
    {
        return editHandler.traverse(i, j, flag1, null, null, flag);
    }

    public void validate()
    {
        if(isValid() || !isDisplayable())
            return;
        synchronized(getTreeLock())
        {
            validateTree();
        }
    }

    public JCCellPosition XYToCell(int i, int j)
    {
        return inputHandler.XYToCellPosition(i, j);
    }

    public int getAlignment(int i, int j)
    {
        if(defaultCellAppearance != null)
            return defaultCellAppearance.alignment;
        else
            return alignment_series.getIntValue(i, j);
    }

    public void setAlignment(int i, int j, int k)
    {
        if(i == -998 && j == -998)
        {
            if(defaultCellAppearance != null)
                defaultCellAppearance.alignment = k;
        } else
        {
            defaultCellAppearance = null;
        }
        alignment_series.setValue(i, j, k);
        setProperty(0x10000, i, j);
    }

    public void setAlignment(JCCellRange jccellrange, int i)
    {
        defaultCellAppearance = null;
        SeriesUtil.setValue(this, alignment_series, jccellrange, new Integer(i));
        setProperty(0x10000, jccellrange);
    }

    public int getAllowCellResize()
    {
        return allow_cell_resize;
    }

    public void setAllowCellResize(int i)
    {
        allow_cell_resize = i;
    }

    public int getAutoScroll()
    {
        return auto_scroll;
    }

    public void setAutoScroll(int i)
    {
        auto_scroll = i;
    }

    public Color getBackground(int i, int j)
    {
        if(defaultCellAppearance != null && defaultCellAppearance.background != null)
            return defaultCellAppearance.background;
        Object obj = bg_series.getValue(i, j);
        if(obj instanceof Color)
            return (Color)obj;
        else
            return getRepeatColor(obj, i, j, true);
    }

    public void setBackground(Color color)
    {
        if(defaultCellAppearance != null)
            defaultCellAppearance.background = color;
        bg_series.setDefault(color);
        super.setBackground(color);
    }

    public void setBackground(int i, int j, int k)
    {
        defaultCellAppearance = null;
        bg_series.setValue(i, j, new Integer(k));
        setProperty(0x10000, i, j);
    }

    public void setBackground(JCCellRange jccellrange, int i)
    {
        defaultCellAppearance = null;
        SeriesUtil.setValue(this, bg_series, jccellrange, new Integer(i));
        setProperty(0x10000, jccellrange);
    }

    public void setBackground(int i, int j, Color color)
    {
        if(i == -998 && j == -998)
        {
            if(defaultCellAppearance != null)
                defaultCellAppearance.background = color;
        } else
        {
            defaultCellAppearance = null;
        }
        bg_series.setValue(i, j, color);
        setProperty(0x10000, i, j);
    }

    public void setBackground(JCCellRange jccellrange, Color color)
    {
        defaultCellAppearance = null;
        SeriesUtil.setValue(this, bg_series, jccellrange, color);
        setProperty(0x10000, jccellrange);
    }

    public Color getCellBorderColor()
    {
        return cellBorderColor;
    }

    public void setCellBorderColor(Color color)
    {
        if(color != null)
        {
            cellBorderColor = color;
            cellBorderDarker = color.darker();
            cellBorderBrighter = color.brighter();
        } else
        {
            cellBorderColor = cellBorderDarker = cellBorderBrighter = null;
        }
        setProperty(0x40000);
    }

    public int getCellBorderSides(int i, int j)
    {
        if(defaultCellBorder != null)
            return defaultCellBorder.cellBorderSides;
        else
            return bordersides_series.getIntValue(i, j);
    }

    public void setCellBorderSides(int i, int j, int k)
    {
        if(i == -998 && j == -998)
        {
            if(defaultCellBorder != null)
                defaultCellBorder.cellBorderSides = k;
        } else
        {
            defaultCellBorder = null;
        }
        bordersides_series.setValue(i, j, k);
        setProperty(0x10000, i, j);
    }

    public void setCellBorderSides(JCCellRange jccellrange, int i)
    {
        defaultCellBorder = null;
        SeriesUtil.setValue(this, bordersides_series, jccellrange, new Integer(i));
        setProperty(0x10000, jccellrange);
    }

    public CellBorder getCellBorderType(int i, int j)
    {
        if(defaultCellBorder != null)
            return defaultCellBorder.cellBorder;
        Object obj = bordertype_series.getValue(i, j);
        if(obj instanceof CellBorder)
            return (CellBorder)obj;
        else
            return new StandardCellBorder(0);
    }

    public void setCellBorderType(int i, int j, CellBorder cellborder)
    {
        if(i == -998 && j == -998)
        {
            if(defaultCellBorder != null)
                defaultCellBorder.cellBorder = cellborder;
        } else
        {
            defaultCellBorder = null;
        }
        bordertype_series.setValue(i, j, cellborder);
        setProperty(0x10000, i, j);
    }

    public void setCellBorderType(int i, int j, int k)
    {
        if(i == -998 && j == -998)
        {
            if(defaultCellBorder != null)
                defaultCellBorder.cellBorder = new StandardCellBorder(k);
        } else
        {
            defaultCellBorder = null;
        }
        setCellBorderType(i, j, ((CellBorder) (new StandardCellBorder(k))));
    }

    public void setCellBorderType(JCCellRange jccellrange, CellBorder cellborder)
    {
        defaultCellBorder = null;
        SeriesUtil.setValue(this, bordertype_series, jccellrange, cellborder);
        setProperty(0x10000, jccellrange);
    }

    public int getCellBorderWidth()
    {
        return shadow_thickness;
    }

    public void setCellBorderWidth(int i)
    {
        shadow_thickness = i;
        setProperty(512);
    }

    public CellEditor getCellEditor(int i, int j)
    {
        return (CellEditor)celleditor_series.getValue(i, j);
    }

    public void setCellEditor(int i, int j, CellEditor celleditor)
    {
        celleditor_series.setValue(i, j, celleditor);
    }

    public void setCellEditor(JCCellRange jccellrange, CellEditor celleditor)
    {
        SeriesUtil.setValue(this, celleditor_series, jccellrange, celleditor);
    }

    public void setCellEditor(Class class1, Class class2)
    {
        if(class1 == null)
            return;
        Hashtable hashtable = getDataView().getEditorsTable();
        if(class2 == null)
        {
            hashtable.remove(class1);
            return;
        } else
        {
            hashtable.put(class1, class2);
            getDataView().setEditorsTable(hashtable);
            return;
        }
    }

    public void setCellEditor(Class class1, CellEditor celleditor)
    {
        if(class1 == null)
            return;
        Hashtable hashtable = getDataView().getEditorsTable();
        if(celleditor == null)
        {
            hashtable.remove(class1);
            return;
        } else
        {
            hashtable.put(class1, celleditor);
            getDataView().setEditorsTable(hashtable);
            return;
        }
    }

    public CellRenderer getCellRenderer(int i, int j)
    {
        return (CellRenderer)cellrenderer_series.getValue(i, j);
    }

    public void setCellRenderer(int i, int j, CellRenderer cellrenderer)
    {
        cellrenderer_series.setValue(i, j, cellrenderer);
        setProperty(0x10000, i, j);
    }

    public void setCellRenderer(JCCellRange jccellrange, CellRenderer cellrenderer)
    {
        SeriesUtil.setValue(this, cellrenderer_series, jccellrange, cellrenderer);
        setProperty(0x10000, jccellrange);
    }

    public void setCellRenderer(Class class1, Class class2)
    {
        if(class1 == null)
            return;
        Hashtable hashtable = getDataView().getRenderersTable();
        if(class2 == null)
        {
            hashtable.remove(class1);
            return;
        } else
        {
            hashtable.put(class1, class2);
            getDataView().setRenderersTable(hashtable);
            return;
        }
    }

    public void setCellRenderer(Class class1, CellRenderer cellrenderer)
    {
        if(class1 == null)
            return;
        Hashtable hashtable = getDataView().getRenderersTable();
        if(cellrenderer == null)
        {
            hashtable.remove(class1);
            return;
        } else
        {
            hashtable.put(class1, cellrenderer);
            getDataView().setRenderersTable(hashtable);
            return;
        }
    }

    public int getCharHeight(int i)
    {
        return charheight_series.getIntValue(i, -998);
    }

    public void setCharHeight(int i, int j)
    {
        charheight_series.setValue(i, -998, j);
        if(!isDisplayable())
            return;
        int k = 0x40080;
        if(i == -1)
            k |= 0x4000;
        else
        if(i >= 0)
        {
            Size.getRowHeights(this, i, i, -998, 0, null);
            k |= 0x1000;
        } else
        {
            k |= 0x104000;
        }
        setProperty(k);
    }

    public int getCharWidth(int i)
    {
        return charwidth_series.getIntValue(-998, i);
    }

    public void setCharWidth(int i, int j)
    {
        charwidth_series.setValue(-998, i, j);
        if(!isDisplayable())
            return;
        int k = 0x40080;
        if(i == -1)
            k |= 0x200000;
        else
        if(i >= 0)
        {
            Size.getColumnWidths(this, -998, 0, i, i, null);
            k |= 0x2000;
        } else
        {
            k |= 0x208000;
        }
        setProperty(k);
    }

    public boolean getColumnLabelDisplay()
    {
        return column_label_display;
    }

    public void setColumnLabelDisplay(boolean flag)
    {
        column_label_display = flag;
        setProperty(0xc4080);
    }

    public int getColumnLabelOffset()
    {
        return column_label_offset;
    }

    public void setColumnLabelOffset(int i)
    {
        column_label_offset = i;
        if(column_label_display)
            setProperty(0x80000);
    }

    public int getColumnLabelPlacement()
    {
        return column_label_placement;
    }

    public void setColumnLabelPlacement(int i)
    {
        column_label_placement = i;
        if(column_label_display)
            setProperty(0x80000);
    }

    public Component getComponent(int i, int j)
    {
        if(component_series.size() > 0)
            return Widget.getComponent(this, i, j, true);
        else
            return null;
    }

    public void setComponent(int i, int j, Component component)
    {
        Widget.setValue(this, i, j, component);
    }

    public TableData getDataSource()
    {
        return getDataView().getDataSource();
    }

    public void setDataSource(TableData tabledata)
    {
        getDataView().setDataSource(tabledata);
    }

    public TableDataView getDataView()
    {
        return dataView;
    }

    public int getClipArrows()
    {
        return clip_arrows;
    }

    public void setClipArrows(int i)
    {
        clip_arrows = i;
        setProperty(0x40000);
    }

    public boolean getDoubleBuffer()
    {
        return double_buffer;
    }

    public void setDoubleBuffer(boolean flag)
    {
        double_buffer = flag;
    }

    public boolean getEditable(int i, int j)
    {
        return editable_series.getBooleanValue(i, j);
    }

    public void setEditable(int i, int j, boolean flag)
    {
        editable_series.setValue(i, j, flag);
    }

    public void setEditable(JCCellRange jccellrange, boolean flag)
    {
        SeriesUtil.setValue(this, editable_series, jccellrange, new Boolean(flag));
        setProperty(0x10000, jccellrange);
    }

    public int getEditHeightPolicy()
    {
        return edit_height_policy;
    }

    public void setEditHeightPolicy(int i)
    {
        edit_height_policy = i;
    }

    public int getEditWidthPolicy()
    {
        return edit_width_policy;
    }

    public void setEditWidthPolicy(int i)
    {
        edit_width_policy = i;
    }

    public Color getFocusRectColor()
    {
        return focus_rect_color;
    }

    public void setFocusRectColor(Color color)
    {
        if(color == null)
            focus_rect_color = Color.black;
        else
            focus_rect_color = color;
        setProperty(0x10000);
    }

    public Font getFont(int i, int j)
    {
        if(defaultCellAppearance != null && defaultCellAppearance.font != null)
            return defaultCellAppearance.font;
        else
            return (Font)font_series.getValue(i, j);
    }

    public void setFont(int i, int j, Font font)
    {
        if(i == -998 && j == -998)
        {
            if(defaultCellAppearance != null)
            {
                defaultCellAppearance.font = font;
                defaultCellAppearance.fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
            }
        } else
        {
            defaultCellAppearance = null;
        }
        font_series.setValue(i, j, font);
        if(!isDisplayable())
            return;
        int k = 0x60080;
        if(j == -998 || j == -1)
            k |= 0x200000;
        if(i == -998 || i == -1)
            k |= 0x4000;
        else
        if(isCell(i, j))
        {
            Size.getColumnWidths(this, -998, 0, j, j, null);
            Size.getRowHeights(this, i, i, -998, 0, null);
            k |= 0x3000;
        } else
        {
            k |= 0x108000;
        }
        setProperty(k);
    }

    public void setFont(JCCellRange jccellrange, Font font)
    {
        defaultCellAppearance = null;
        SeriesUtil.setValue(this, font_series, jccellrange, font);
        setProperty(0x10000, jccellrange);
    }

    public Color getForeground(int i, int j)
    {
        if(defaultCellAppearance != null && defaultCellAppearance.background != null)
            return defaultCellAppearance.foreground;
        Object obj = fg_series.getValue(i, j);
        if(obj instanceof Color)
            return (Color)obj;
        else
            return getRepeatColor(obj, i, j, false);
    }

    public void setForeground(Color color)
    {
        if(defaultCellAppearance != null)
            defaultCellAppearance.foreground = color;
        fg_series.setDefault(color);
        super.setForeground(color);
    }

    public void setForeground(int i, int j, Color color)
    {
        if(i == -998 && j == -998)
        {
            if(defaultCellAppearance != null)
                defaultCellAppearance.foreground = color;
        } else
        {
            defaultCellAppearance = null;
        }
        fg_series.setValue(i, j, color);
        setProperty(0x10000, i, j);
    }

    public void setForeground(JCCellRange jccellrange, Color color)
    {
        defaultCellAppearance = null;
        SeriesUtil.setValue(this, fg_series, jccellrange, color);
        setProperty(0x10000, jccellrange);
    }

    public void setForeground(int i, int j, int k)
    {
        defaultCellAppearance = null;
        fg_series.setValue(i, j, new Integer(k));
        setProperty(0x10000, i, j);
    }

    public void setForeground(JCCellRange jccellrange, int i)
    {
        defaultCellAppearance = null;
        SeriesUtil.setValue(this, fg_series, jccellrange, new Integer(i));
        setProperty(0x10000, jccellrange);
    }

    public int getFrameBorderType()
    {
        return frame_bordertype;
    }

    public void setFrameBorderType(int i)
    {
        if(isDisplayable() && repaint && frame_shadow > 0)
            Clip.drawFrames(getGraphics(), this, true);
        frame_bordertype = i;
        setProperty(0x80000);
    }

    public int getFrameBorderWidth()
    {
        return frame_shadow;
    }

    public void setFrameBorderWidth(int i)
    {
        if(isDisplayable() && repaint && frame_shadow > 0)
            Clip.drawFrames(getGraphics(), this, true);
        frame_shadow = i;
        setProperty(0x80080);
    }

    public int getFrozenColumnPlacement()
    {
        return frozen_column_placement;
    }

    public void setFrozenColumnPlacement(int i)
    {
        frozen_column_placement = i;
        setProperty(0x80000);
    }

    public int getFrozenColumns()
    {
        return frozen_columns;
    }

    public void setFrozenColumns(int i)
    {
        frozen_columns = i;
        setProperty(20);
    }

    public int getFrozenRowPlacement()
    {
        return frozen_row_placement;
    }

    public void setFrozenRowPlacement(int i)
    {
        frozen_row_placement = i;
        setProperty(0x80000);
    }

    public int getFrozenRows()
    {
        return frozen_rows;
    }

    public void setFrozenRows(int i)
    {
        frozen_rows = i;
        setProperty(1032);
    }

    public TableScrollbar getHorizSB()
    {
        return horiz_sb;
    }

    public void setHorizSB(Component component)
    {
        if(component instanceof JCAdjustable)
        {
            if(horiz_sb == null)
                horiz_sb = new TableScrollbar(this, 0);
            horiz_sb.setAdjustable((JCAdjustable)component);
            horiz_sb.setComponent(component);
        }
    }

    public int getHorizSBAttachment()
    {
        return horizsb_attach;
    }

    public void setHorizSBAttachment(int i)
    {
        horizsb_attach = i;
        setProperty(0x80000);
    }

    public int getHorizSBDisplay()
    {
        return horizsb_display;
    }

    public void setHorizSBDisplay(int i)
    {
        horizsb_display = i;
        setProperty(0x80080);
    }

    public int getHorizSBOffset()
    {
        return horizsb_offset;
    }

    public void setHorizSBOffset(int i)
    {
        horizsb_offset = i;
        setProperty(0x80080);
    }

    public int getHorizSBPosition()
    {
        return horizsb_position;
    }

    public void setHorizSBPosition(int i)
    {
        horizsb_position = i;
        setProperty(0x80000);
    }

    public boolean getIgnoreContainerSize()
    {
        return ignore_container_size;
    }

    public void setIgnoreContainerSize(boolean flag)
    {
        ignore_container_size = flag;
        setProperty(0x80000);
    }

    public int getJumpScroll()
    {
        return jump;
    }

    public void setJumpScroll(int i)
    {
        jump = i;
    }

    public int getMarginHeight()
    {
        return margin_height;
    }

    public void setMarginHeight(int i)
    {
        margin_height = i;
        setProperty(0xc0020);
    }

    public int getMarginWidth()
    {
        return margin_width;
    }

    public void setMarginWidth(int i)
    {
        margin_width = i;
        setProperty(0xc0020);
    }

    public int getMaxLength(int i, int j)
    {
        return maxlength_series.getIntValue(i, j);
    }

    public void setMaxLength(int i, int j, int k)
    {
        maxlength_series.setValue(i, j, k);
    }

    public void setMaxLength(JCCellRange jccellrange, int i)
    {
        SeriesUtil.setValue(this, maxlength_series, jccellrange, new Integer(i));
        setProperty(0x10000, jccellrange);
    }

    public int getMinCellVisibility()
    {
        return min_cell_visibility;
    }

    public void setMinCellVisibility(int i)
    {
        min_cell_visibility = i;
    }

    public int getMode()
    {
        return mode;
    }

    public void setMode(int i)
    {
        if(i == 1)
        {
            setModeList();
            return;
        } else
        {
            setModeTable();
            return;
        }
    }

    public void setModeList()
    {
        setModeTable();
        mode = 1;
        horizsb_attach = vertsb_attach = 1;
        horizsb_position = vertsb_position = 1;
        resize_by_labels_only = true;
        selection_policy = 1;
        shadow_thickness = 0;
        setTraversable(-998, -998, false);
        setProperty(0x80000);
    }

    public void setModeTable()
    {
        mode = 0;
        horizsb_attach = vertsb_attach = 0;
        horizsb_position = vertsb_position = 0;
        resize_by_labels_only = false;
        selection_policy = selection_policy = 0;
        shadow_thickness = 1;
        setTraversable(-998, -998, true);
        setProperty(0x80000);
    }

    public boolean getMultiline(int i, int j)
    {
        return multiline_series.getBooleanValue(i, j);
    }

    public void setMultiline(int i, int j, boolean flag)
    {
        multiline_series.setValue(i, j, new Boolean(flag));
        setProperty(0x10000, i, j);
    }

    public void setMultiline(JCCellRange jccellrange, boolean flag)
    {
        SeriesUtil.setValue(this, multiline_series, jccellrange, new Boolean(flag));
        setProperty(0x10000, jccellrange);
    }

    public int getPixelHeight(int i)
    {
        return pixelheight_series.getIntValue(i, -998);
    }

    public void setPixelHeight(int i, int j)
    {
        pixelheight_series.setValue(i, -998, j);
        if(!is_printing && !isDisplayable())
            return;
        int k = 0x40080;
        if(i == -1)
            k |= 0x4000;
        else
        if(i >= 0)
        {
            k |= 0x1000;
            Size.getRowHeights(this, i, i, -998, 0, null);
        } else
        {
            k |= 0x104000;
        }
        setProperty(k);
    }

    public int getPixelWidth(int i)
    {
        return pixelwidth_series.getIntValue(-998, i);
    }

    public void setPixelWidth(int i, int j)
    {
        pixelwidth_series.setValue(-998, i, j);
        if(!is_printing && !isDisplayable())
            return;
        int k = 0x40080;
        if(i == -1)
            k |= 0x200000;
        else
        if(i >= 0)
        {
            k |= 0x2000;
            Size.getColumnWidths(this, -998, 0, i, i, null);
        } else
        {
            k |= 0x208000;
        }
        setProperty(k);
    }

    public boolean getRepaint()
    {
        return repaint;
    }

    public void setRepaint(boolean flag)
    {
        repaint = flag;
        if(repaint && (needs_repaint || needs_recalc))
            setProperty(64);
    }

    public Color[] getRepeatBackgroundColors()
    {
        return repeat_bg_colors;
    }

    public void setRepeatBackgroundColors(Color acolor[])
    {
        repeat_bg_colors = acolor;
        setProperty(0x40000);
    }

    public Color[] getRepeatForegroundColors()
    {
        return repeat_fg_colors;
    }

    public void setRepeatForegroundColors(Color acolor[])
    {
        repeat_fg_colors = acolor;
        setProperty(0x40000);
    }

    public boolean getResizeByLabelsOnly()
    {
        return resize_by_labels_only;
    }

    public void setResizeByLabelsOnly(boolean flag)
    {
        resize_by_labels_only = flag;
    }

    public boolean getResizeEven()
    {
        return resize_even;
    }

    public void setResizeEven(boolean flag)
    {
        resize_even = flag;
    }

    public boolean getRowLabelDisplay()
    {
        return row_label_display;
    }

    public void setRowLabelDisplay(boolean flag)
    {
        row_label_display = flag;
        setProperty(0x2c0080);
    }

    public int getRowLabelOffset()
    {
        return row_label_offset;
    }

    public void setRowLabelOffset(int i)
    {
        row_label_offset = i;
        if(row_label_display)
            setProperty(0x80000);
    }

    public int getRowLabelPlacement()
    {
        return row_label_placement;
    }

    public void setRowLabelPlacement(int i)
    {
        row_label_placement = i;
        if(row_label_display)
            setProperty(0x80000);
    }

    public Color getSelectedBackground()
    {
        if(selected_bg_set)
            return selected_bg;
        else
            return getForeground();
    }

    public void setSelectedBackground(Color color)
    {
        selected_bg = color;
        selected_bg_set = true;
        setProperty(0x1000000);
    }

    public Color getSelectedForeground()
    {
        if(selected_fg_set)
            return selected_fg;
        else
            return getBackground();
    }

    public void setSelectedForeground(Color color)
    {
        selected_fg = color;
        selected_fg_set = true;
        setProperty(0x1000000);
    }

    public boolean getSelectIncludeLabels()
    {
        return select_include_labels;
    }

    public void setSelectIncludeLabels(boolean flag)
    {
        select_include_labels = flag;
        if(getSelectedCells() != null)
            repaint();
    }

    public int getSelectionMode()
    {
        return selection_mode;
    }

    public void setSelectionMode(int i)
    {
        selection_mode = i;
    }

    public int getSelectionPolicy()
    {
        return selection_policy;
    }

    public void setSelectionPolicy(int i)
    {
        clearSelectedCells();
        selection_policy = i;
    }

    public boolean getSortSeries()
    {
        return sort_series;
    }

    public void setSortSeries(boolean flag)
    {
        sort_series = flag;
    }

    public int getStringCase(int i, int j)
    {
        return stringcase_series.getIntValue(i, j);
    }

    public void setStringCase(int i, int j, int k)
    {
        stringcase_series.setValue(i, j, k);
    }

    public void setStringCase(JCCellRange jccellrange, int i)
    {
        SeriesUtil.setValue(this, stringcase_series, jccellrange, new Integer(i));
        setProperty(0x10000, jccellrange);
    }

    public boolean getTrackCursor()
    {
        return track_cursor;
    }

    public void setTrackCursor(boolean flag)
    {
        track_cursor = flag;
    }

    public boolean getTrackJCStringURL()
    {
        return trackJCStringURL;
    }

    public void setTrackJCStringURL(boolean flag)
    {
        trackJCStringURL = flag;
    }

    public boolean getTraversable(int i, int j)
    {
        return traversable_series.getBooleanValue(i, j);
    }

    public void setTraversable(int i, int j, boolean flag)
    {
        traversable_series.setValue(i, j, flag);
        traversable = traversable_series.containsValue(Boolean.TRUE);
        if(isDisplayable() && edit_row != -999 && !getTraversable(edit_row, edit_column))
        {
            editHandler.cancel();
            edit_row = -999;
            edit_column = -999;
        }
    }

    public void setTraversable(JCCellRange jccellrange, boolean flag)
    {
        SeriesUtil.setValue(this, traversable_series, jccellrange, new Boolean(flag));
        setProperty(0x10000, jccellrange);
    }

    public boolean getTraverseCycle()
    {
        return traverseCycle;
    }

    public void setTraverseCycle(boolean flag)
    {
        traverseCycle = flag;
    }

    public Object getUserdata(int i, int j)
    {
        return userdata_series.getValue(i, j);
    }

    public void setUserdata(int i, int j, Object obj)
    {
        userdata_series.setValue(i, j, obj);
    }

    public void setUserdata(JCCellRange jccellrange, Object obj)
    {
        SeriesUtil.setValue(this, userdata_series, jccellrange, obj);
        setProperty(0x10000, jccellrange);
    }

    public int getVariableEstimateCount()
    {
        return estimate_count;
    }

    public void setVariableEstimateCount(int i)
    {
        estimate_count = i;
    }

    public TableScrollbar getVertSB()
    {
        return vert_sb;
    }

    public void setVertSB(Component component)
    {
        if(component instanceof JCAdjustable)
        {
            if(vert_sb == null)
                vert_sb = new TableScrollbar(this, 1);
            vert_sb.setAdjustable((JCAdjustable)component);
            vert_sb.setComponent(component);
        }
    }

    public int getVertSBAttachment()
    {
        return vertsb_attach;
    }

    public void setVertSBAttachment(int i)
    {
        vertsb_attach = i;
        setProperty(0x80000);
    }

    public int getVertSBDisplay()
    {
        return vertsb_display;
    }

    public void setVertSBDisplay(int i)
    {
        vertsb_display = i;
        setProperty(0x80080);
    }

    public int getVertSBOffset()
    {
        return vertsb_offset;
    }

    public void setVertSBOffset(int i)
    {
        vertsb_offset = i;
        setProperty(0x80080);
    }

    public int getVertSBPosition()
    {
        return vertsb_position;
    }

    public void setVertSBPosition(int i)
    {
        vertsb_position = i;
        setProperty(0x80000);
    }

    public int getVisibleColumns()
    {
        return visible_columns;
    }

    public void setVisibleColumns(int i)
    {
        if(i <= 0)
            visible_columns = -999;
        else
            visible_columns = i;
        setProperty(0x4c0090);
    }

    public int getVisibleRows()
    {
        return visible_rows;
    }

    public void setVisibleRows(int i)
    {
        if(i <= 0)
            visible_rows = -999;
        else
            visible_rows = i;
        setProperty(0x8c0480);
    }

    protected void processMouseEvent(MouseEvent mouseevent)
    {
        super.processMouseEvent(mouseevent);
        if(mouseevent.isConsumed())
            return;
        InputHandler inputhandler = inputHandler;
        int i = mouseevent.getID();
        switch(i)
        {
        case 501: 
            inputhandler.mousePressed(mouseevent);
            return;

        case 502: 
            inputhandler.mouseReleased(mouseevent);
            return;

        case 500: 
            inputhandler.mouseClicked(mouseevent);
            return;

        case 505: 
            inputhandler.mouseExited(mouseevent);
            return;

        case 504: 
            inputhandler.mouseEntered(mouseevent);
            return;

        case 503: 
        default:
            return;
        }
    }

    protected void processMouseMotionEvent(MouseEvent mouseevent)
    {
        super.processMouseMotionEvent(mouseevent);
        if(mouseevent.isConsumed())
            return;
        InputHandler inputhandler = inputHandler;
        int i = mouseevent.getID();
        switch(i)
        {
        case 503: 
            inputhandler.mouseMoved(mouseevent);
            return;

        case 506: 
            inputhandler.mouseDragged(mouseevent);
            return;
        }
    }

    protected void processKeyEvent(KeyEvent keyevent)
    {
        if(keyevent.getID() == 401 && keyevent.getKeyCode() == 9)
        {
            keyevent.consume();
        } else
        {
            super.processKeyEvent(keyevent);
            if(keyevent.isConsumed())
                return;
        }
        InputHandler inputhandler = inputHandler;
        int i = keyevent.getID();
        switch(i)
        {
        case 401: 
            inputhandler.keyPressed(keyevent);
            return;

        case 402: 
            inputhandler.keyReleased(keyevent);
            return;

        case 400: 
            inputhandler.keyTyped(keyevent);
            return;
        }
    }

    void dispatchMouseEvent(MouseEvent mouseevent)
    {
        Point point = mouseevent.getComponent().getLocation();
        MouseEvent mouseevent1 = new MouseEvent(this, mouseevent.getID(), mouseevent.getWhen(), mouseevent.getModifiers(), mouseevent.getX() + point.x, mouseevent.getY() + point.y, mouseevent.getClickCount(), mouseevent.isPopupTrigger());
        processEvent(mouseevent1);
    }

    void dispatchKeyEvent(KeyEvent keyevent)
    {
        KeyEvent keyevent1 = new KeyEvent(this, keyevent.getID(), keyevent.getWhen(), keyevent.getModifiers(), keyevent.getKeyCode(), keyevent.getKeyChar());
        processEvent(keyevent1);
    }

    public void mouseDragged(MouseEvent mouseevent)
    {
        dispatchMouseEvent(mouseevent);
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
        dispatchMouseEvent(mouseevent);
    }

    public void mousePressed(MouseEvent mouseevent)
    {
        dispatchMouseEvent(mouseevent);
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
        dispatchMouseEvent(mouseevent);
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
        dispatchMouseEvent(mouseevent);
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
        dispatchMouseEvent(mouseevent);
    }

    public void mouseExited(MouseEvent mouseevent)
    {
        dispatchMouseEvent(mouseevent);
    }

    public void keyPressed(KeyEvent keyevent)
    {
        dispatchKeyEvent(keyevent);
    }

    public void keyReleased(KeyEvent keyevent)
    {
        dispatchKeyEvent(keyevent);
    }

    public void keyTyped(KeyEvent keyevent)
    {
        dispatchKeyEvent(keyevent);
    }

    public synchronized void addCellDisplayListener(JCCellDisplayListener jccelldisplaylistener)
    {
        cellDisplayListeners = JCListenerList.add(cellDisplayListeners, jccelldisplaylistener);
    }

    public synchronized void removeCellDisplayListener(JCCellDisplayListener jccelldisplaylistener)
    {
        cellDisplayListeners = JCListenerList.remove(cellDisplayListeners, jccelldisplaylistener);
    }

    public synchronized void addCreateComponentListener(JCCreateComponentListener jccreatecomponentlistener)
    {
        createComponentListeners = JCListenerList.add(createComponentListeners, jccreatecomponentlistener);
    }

    public synchronized void removeCreateComponentListener(JCCreateComponentListener jccreatecomponentlistener)
    {
        createComponentListeners = JCListenerList.remove(createComponentListeners, jccreatecomponentlistener);
    }

    public synchronized void addDisplayComponentListener(JCDisplayComponentListener jcdisplaycomponentlistener)
    {
        displayComponentListeners = JCListenerList.add(displayComponentListeners, jcdisplaycomponentlistener);
    }

    public synchronized void removeDisplayComponentListener(JCDisplayComponentListener jcdisplaycomponentlistener)
    {
        displayComponentListeners = JCListenerList.remove(displayComponentListeners, jcdisplaycomponentlistener);
    }

    public synchronized void addEnterCellListener(JCEnterCellListener jcentercelllistener)
    {
        enterCellListeners = JCListenerList.add(enterCellListeners, jcentercelllistener);
    }

    public synchronized void removeEnterCellListener(JCEnterCellListener jcentercelllistener)
    {
        enterCellListeners = JCListenerList.remove(enterCellListeners, jcentercelllistener);
    }

    public synchronized void addResizeCellListener(JCResizeCellListener jcresizecelllistener)
    {
        resizeCellListeners = JCListenerList.add(resizeCellListeners, jcresizecelllistener);
    }

    public synchronized void removeResizeCellListener(JCResizeCellListener jcresizecelllistener)
    {
        resizeCellListeners = JCListenerList.remove(resizeCellListeners, jcresizecelllistener);
    }

    public synchronized void addPaintListener(JCPaintListener jcpaintlistener)
    {
        paintListeners = JCListenerList.add(paintListeners, jcpaintlistener);
    }

    public synchronized void removePaintListener(JCPaintListener jcpaintlistener)
    {
        paintListeners = JCListenerList.remove(paintListeners, jcpaintlistener);
    }

    public synchronized void addPrintListener(JCPrintListener jcprintlistener)
    {
        printListeners = JCListenerList.add(printListeners, jcprintlistener);
    }

    public synchronized void removePrintListener(JCPrintListener jcprintlistener)
    {
        printListeners = JCListenerList.remove(printListeners, jcprintlistener);
    }

    public synchronized void addScrollListener(JCScrollListener jcscrolllistener)
    {
        scrollListeners = JCListenerList.add(scrollListeners, jcscrolllistener);
    }

    public synchronized void removeScrollListener(JCScrollListener jcscrolllistener)
    {
        scrollListeners = JCListenerList.remove(scrollListeners, jcscrolllistener);
    }

    public synchronized void addSelectListener(JCSelectListener jcselectlistener)
    {
        selectListeners = JCListenerList.add(selectListeners, jcselectlistener);
    }

    public synchronized void removeSelectListener(JCSelectListener jcselectlistener)
    {
        selectListeners = JCListenerList.remove(selectListeners, jcselectlistener);
    }

    public synchronized void addSortListener(JCSortListener jcsortlistener)
    {
        sortListeners = JCListenerList.add(sortListeners, jcsortlistener);
    }

    public synchronized void removeSortListener(JCSortListener jcsortlistener)
    {
        sortListeners = JCListenerList.remove(sortListeners, jcsortlistener);
    }

    public synchronized void addTraverseCellListener(JCTraverseCellListener jctraversecelllistener)
    {
        traverseCellListeners = JCListenerList.add(traverseCellListeners, jctraversecelllistener);
    }

    public synchronized void removeTraverseCellListener(JCTraverseCellListener jctraversecelllistener)
    {
        traverseCellListeners = JCListenerList.remove(traverseCellListeners, jctraversecelllistener);
    }

    protected void fireJCCellDisplayEvent(JCCellDisplayEvent jccelldisplayevent)
    {
        JCCellDisplayListener jccelldisplaylistener;
        for(Enumeration enumeration = JCListenerList.elements(cellDisplayListeners); enumeration.hasMoreElements(); jccelldisplaylistener.cellDisplay(jccelldisplayevent))
            jccelldisplaylistener = (JCCellDisplayListener)enumeration.nextElement();

    }

    protected void fireJCCreateComponentEvent(JCCreateComponentEvent jccreatecomponentevent)
    {
        JCCreateComponentListener jccreatecomponentlistener;
        for(Enumeration enumeration = JCListenerList.elements(createComponentListeners); enumeration.hasMoreElements(); jccreatecomponentlistener.createComponent(jccreatecomponentevent))
            jccreatecomponentlistener = (JCCreateComponentListener)enumeration.nextElement();

    }

    protected void fireJCDisplayComponentEvent(JCDisplayComponentEvent jcdisplaycomponentevent)
    {
        JCDisplayComponentListener jcdisplaycomponentlistener;
        for(Enumeration enumeration = JCListenerList.elements(displayComponentListeners); enumeration.hasMoreElements(); jcdisplaycomponentlistener.displayComponent(jcdisplaycomponentevent))
            jcdisplaycomponentlistener = (JCDisplayComponentListener)enumeration.nextElement();

    }

    protected void fireJCEnterCellEvent(JCEnterCellEvent jcentercellevent)
    {
        for(Enumeration enumeration = JCListenerList.elements(enterCellListeners); enumeration.hasMoreElements();)
        {
            JCEnterCellListener jcentercelllistener = (JCEnterCellListener)enumeration.nextElement();
            switch(jcentercellevent.getType())
            {
            case 1: // '\001'
                jcentercelllistener.enterCellBegin(jcentercellevent);
                break;

            case 2: // '\002'
                jcentercelllistener.enterCellEnd(jcentercellevent);
                break;
            }
        }

    }

    protected void fireJCPaintEvent(JCPaintEvent jcpaintevent)
    {
        for(Enumeration enumeration = JCListenerList.elements(paintListeners); enumeration.hasMoreElements();)
        {
            JCPaintListener jcpaintlistener = (JCPaintListener)enumeration.nextElement();
            switch(jcpaintevent.getType())
            {
            case 1: // '\001'
                jcpaintlistener.paintBegin(jcpaintevent);
                break;

            case 2: // '\002'
                jcpaintlistener.paintEnd(jcpaintevent);
                break;
            }
        }

    }

    protected void fireJCPrintEvent(JCPrintEvent jcprintevent)
    {
        for(Enumeration enumeration = JCListenerList.elements(printListeners); enumeration.hasMoreElements();)
        {
            JCPrintListener jcprintlistener = (JCPrintListener)enumeration.nextElement();
            switch(jcprintevent.getType())
            {
            case 1: // '\001'
                jcprintlistener.printPageHeader(jcprintevent);
                break;

            case 4: // '\004'
                jcprintlistener.printPageBody(jcprintevent);
                break;

            case 2: // '\002'
                jcprintlistener.printPageFooter(jcprintevent);
                break;

            case 3: // '\003'
                jcprintlistener.printEnd(jcprintevent);
                break;
            }
        }

    }

    protected void fireJCResizeCellEvent(JCResizeCellEvent jcresizecellevent)
    {
        for(Enumeration enumeration = JCListenerList.elements(resizeCellListeners); enumeration.hasMoreElements();)
        {
            JCResizeCellListener jcresizecelllistener = (JCResizeCellListener)enumeration.nextElement();
            switch(jcresizecellevent.getType())
            {
            case 1: // '\001'
                jcresizecelllistener.resizeCellBegin(jcresizecellevent);
                break;

            case 2: // '\002'
                jcresizecelllistener.resizeCellEnd(jcresizecellevent);
                break;
            }
        }

    }

    protected void fireJCScrollEvent(JCScrollEvent jcscrollevent)
    {
        for(Enumeration enumeration = JCListenerList.elements(scrollListeners); enumeration.hasMoreElements();)
        {
            JCScrollListener jcscrolllistener = (JCScrollListener)enumeration.nextElement();
            switch(jcscrollevent.getType())
            {
            case 1: // '\001'
                jcscrolllistener.scrollBegin(jcscrollevent);
                break;

            case 2: // '\002'
                jcscrolllistener.scrollEnd(jcscrollevent);
                break;
            }
        }

    }

    protected void fireJCSelectEvent(JCSelectEvent jcselectevent)
    {
        for(Enumeration enumeration = JCListenerList.elements(selectListeners); enumeration.hasMoreElements();)
        {
            JCSelectListener jcselectlistener = (JCSelectListener)enumeration.nextElement();
            switch(jcselectevent.getType())
            {
            case 1: // '\001'
                jcselectlistener.selectBegin(jcselectevent);
                break;

            case 2: // '\002'
                jcselectlistener.selectEnd(jcselectevent);
                break;
            }
        }

    }

    protected void fireJCSortEvent(JCSortEvent jcsortevent)
    {
        JCSortListener jcsortlistener;
        for(Enumeration enumeration = JCListenerList.elements(sortListeners); enumeration.hasMoreElements(); jcsortlistener.sort(jcsortevent))
            jcsortlistener = (JCSortListener)enumeration.nextElement();

    }

    protected void fireJCTraverseCellEvent(JCTraverseCellEvent jctraversecellevent)
    {
        JCTraverseCellListener jctraversecelllistener;
        for(Enumeration enumeration = JCListenerList.elements(traverseCellListeners); enumeration.hasMoreElements(); jctraversecelllistener.traverseCell(jctraversecellevent))
            jctraversecelllistener = (JCTraverseCellListener)enumeration.nextElement();

    }

    public LabelTrigger getColumnTrigger(int i)
    {
        if(column_triggers.size() == 0)
            return null;
        else
            return (LabelTrigger)column_triggers.get(new Integer(LabelTrigger.adjustModifier(i)));
    }

    public synchronized void setColumnTrigger(int i, int j)
    {
        setColumnTrigger(new LabelTrigger(i, j));
    }

    public synchronized void setColumnTrigger(LabelTrigger labeltrigger)
    {
        column_triggers.put(new Integer(LabelTrigger.adjustModifier(labeltrigger.modifiers)), labeltrigger);
    }

    public LabelTrigger getRowTrigger(int i)
    {
        if(row_triggers.size() == 0)
            return null;
        else
            return (LabelTrigger)row_triggers.get(new Integer(LabelTrigger.adjustModifier(i)));
    }

    public synchronized void setRowTrigger(int i, int j)
    {
        setRowTrigger(new LabelTrigger(i, j));
    }

    public synchronized void setRowTrigger(LabelTrigger labeltrigger)
    {
        if(labeltrigger.getAction() == 1)
        {
            throw new IllegalArgumentException("Invalid action specified for a row trigger");
        } else
        {
            row_triggers.put(new Integer(LabelTrigger.adjustModifier(labeltrigger.modifiers)), labeltrigger);
            return;
        }
    }

    public void removeColumnTrigger(int i)
    {
        column_triggers.remove(new Integer(LabelTrigger.adjustModifier(i)));
    }

    public void removeRowTrigger(int i)
    {
        row_triggers.remove(new Integer(LabelTrigger.adjustModifier(i)));
    }

    public synchronized void setCellTrigger(int i, int j)
    {
        setCellTrigger(new LabelTrigger(i, j));
    }

    public synchronized void setCellTrigger(LabelTrigger labeltrigger)
    {
        cell_triggers.put(new Integer(LabelTrigger.adjustModifier(labeltrigger.modifiers)), labeltrigger);
    }

    LabelTrigger getCellTrigger(int i)
    {
        if(cell_triggers == null)
            return null;
        else
            return (LabelTrigger)cell_triggers.get(new Integer(LabelTrigger.adjustModifier(i)));
    }

    public void removeCellTrigger(int i)
    {
        cell_triggers.remove(new Integer(LabelTrigger.adjustModifier(i)));
    }

    public int getNumColumnTriggers()
    {
        if(column_triggers != null)
            return 0;
        else
            return column_triggers.size();
    }

    public int getNumRowTriggers()
    {
        if(row_triggers != null)
            return 0;
        else
            return row_triggers.size();
    }

    public synchronized void paint(Graphics g)
    {
        if(frame_shadow > 0 && repaint)
            Clip.drawFrames(g, this, false);
        super.paint(g);
    }

    public void paint(int i, int j)
    {
        paint(r.reshape(i, j));
    }

    public void paint(JCCellRange jccellrange)
    {
        if(!isDisplayable() || !isShowing())
            return;
        if(!repaint)
        {
            needs_repaint = true;
            return;
        }
        if(jccellrange == null)
        {
            for(int i = 0; i < clip_list.length; i++)
                clip_list[i].repaint();

        } else
        {
            for(int j = jccellrange.start_row; j <= jccellrange.end_row; j++)
            {
                for(int k = jccellrange.start_column; k <= jccellrange.end_column; k++)
                {
                    Clip clip1 = Clip.find(this, j, k);
                    if(clip1 != null)
                        clip1.paint(j, k);
                }

            }

        }
        super.repaint();
    }

    public void repaint()
    {
    	//System.out.println("Aktuell kein Repaint");
        paint(((JCCellRange) (null)));
    }

    public void print()
    {
        PrintJob printjob;
        try
        {
            Properties properties = new Properties();
            printjob = getToolkit().getPrintJob(getFrame(this), "Print", properties);
        }
        catch(Exception exception)
        {
            System.out.println("PrintJob error: " + exception.getMessage());
            return;
        }
        if(printjob == null)
            return;
        print(printjob);
        if(printjob != null)
            printjob.end();
    }

    public void print(PrintJob printjob)
    {
        if(printjob == null)
            return;
        Dimension dimension = printjob.getPageDimension();
        if(dimension.width == 0 || dimension.height == 0)
        {
            dimension.width = 612;
            dimension.height = 792;
        }
        JCPrintTable jcprinttable = new JCPrintTable(this);
        jcprinttable.setPageDimensions(dimension.width, dimension.height);
        int i = jcprinttable.getNumPages();
        for(int j = 0; j < i; j++)
        {
            Graphics g = printjob.getGraphics();
            if(g != null)
                jcprinttable.paintPage(g, j);
            g.dispose();
        }

    }

    boolean getPrinting()
    {
        return is_printing;
    }

    void setPrinting(boolean flag)
    {
        is_printing = flag;
    }

    public void paint(Graphics g, JCCellRange jccellrange)
    {
        if(is_printing)
        {
            Clip clip1 = Clip.find_by_type(this, 8);
            clip1.paint(g, jccellrange);
            return;
        } else
        {
            paint(jccellrange);
            return;
        }
    }

    Object cellValue(int i, int j)
    {
        return dataView.getCellData(i, j);
    }

    final int colLabelExtraHeight()
    {
        int i = 0;
        if(columns == 0 || column_label_height == 0)
            return i;
        else
            return i + column_label_offset + 2 * frame_shadow;
    }

    final int columnLabelHeight()
    {
        if(columns > 0 && column_label_height > 0)
            return column_label_height + textHeightOffset() * 2;
        else
            return 0;
    }

    final int columnLabelOffset()
    {
        if(row_label_placement == 0)
            return rowLabelWidth() + rowLabelExtraWidth();
        else
            return 0;
    }

    final int columnLabelPosition()
    {
        if(column_label_placement == 6)
            return frozenRowHeight() + cell_height + column_label_offset + 2 * frame_shadow;
        else
            return 0;
    }

    final int columnPosition(int i)
    {
        if(i == -1)
            return columnLabelPosition();
        else
            return column_widths.getPosition(i);
    }

    final int columnWidth(int i)
    {
        if(i < -1)
            return 0;
        if(i == -1)
            return row_label_width + textWidthOffset() * 2;
        if(columns > 0)
            return column_widths.getValue(i);
        else
            return 0;
    }

    final int frozenColumnLabelOffset()
    {
        return columnLabelOffset() + (frozen_column_placement != 0 ? 0 : frozenColumnWidth());
    }

    final int frozenColumnPosition()
    {
        if(frozen_column_placement == 0)
            return columnLabelOffset();
        else
            return columnLabelOffset() + cell_width;
    }

    final int frozenColumnWidth()
    {
        if(columns > 0)
            return column_widths.getPosition(frozen_columns);
        else
            return 0;
    }

    final int frozenRowHeight()
    {
        if(rows > 0)
            return row_heights.getPosition(frozen_rows);
        else
            return 0;
    }

    final int frozenRowPosition()
    {
        if(frozen_row_placement == 0)
            return rowLabelOffset();
        else
            return rowLabelOffset() + cell_height;
    }

    final int frozenRowLabelOffset()
    {
        return rowLabelOffset() + (frozen_row_placement != 0 ? 0 : frozenRowHeight());
    }

    final int containerHeight()
    {
        if(!ignore_container_size)
            return size().height;
        else
            return columnLabelHeight() + frozenRowHeight() + cell_total_height;
    }

    final int containerWidth()
    {
        if(!ignore_container_size)
            return size().width;
        else
            return rowLabelWidth() + frozenColumnWidth() + cell_total_width;
    }

    final void getBoundsInternal(int i, int j, Rectangle rectangle)
    {
        getPosition(i, j, temp_pos);
        JDKSupport.setBounds(rectangle, temp_pos.x, temp_pos.y, columnWidth(j), rowHeight(i));
    }

    static Frame getFrame(Component component)
    {
        Object obj = component;
        Component component1;
        for(component1 = component; component1 != null && !(component1 instanceof Frame);)
        {
            component1 = ((Component) (obj));
            if(obj == null)
                return null;
            obj = ((Component) (obj)).getParent();
        }

        return (Frame)component1;
    }

    Span getSpan()
    {
        return span;
    }

    public JCCellRange getVisibleCells()
    {
        if(!isDisplayable())
            return null;
        JCCellRange jccellrange = new JCCellRange();
        if(!getVisibleRows(jccellrange))
            return null;
        int i = jccellrange.start_row;
        int j = jccellrange.end_row;
        if(!getVisibleColumns(jccellrange))
            return null;
        else
            return jccellrange.reshape(i, jccellrange.start_column, j, jccellrange.end_column);
    }

    boolean getVisibleColumns(JCCellRange jccellrange)
    {
        if(!isDisplayable())
        {
            return false;
        } else
        {
            int i = clip.xOffset() + clip.horiz_origin;
            jccellrange.start_column = XtoColumn(i);
            jccellrange.end_column = XtoColumn((i + clip.width) - 1);
            return true;
        }
    }

    boolean getVisibleRows(JCCellRange jccellrange)
    {
        if(!isDisplayable())
        {
            return false;
        } else
        {
            int i = clip.yOffset() + clip.vert_origin;
            jccellrange.start_row = YtoRow(i);
            jccellrange.end_row = YtoRow((i + clip.height) - 1);
            return true;
        }
    }

    boolean hasComponents()
    {
        return component_series.size() > 0;
    }

    boolean hasHorizSB()
    {
        return horiz_sb != null && horiz_sb.isVisible();
    }

    boolean hasText()
    {
        CellEditor celleditor = editHandler.getCellEditor();
        if(celleditor != null)
        {
            if(celleditor.getComponent() == null)
                return false;
            else
                return celleditor.getComponent().isVisible();
        } else
        {
            return false;
        }
    }

    boolean hasVertSB()
    {
        return vert_sb != null && vert_sb.isVisible();
    }

    int horizSBheight()
    {
        return 16 + horizsb_offset;
    }

    boolean isColumnVisible(int i)
    {
        if(!isDisplayable())
            return false;
        if(i == -1)
            return column_label_height > 0 && columnWidth(i) > 0;
        if(i < 0 || i >= columns || columnWidth(i) <= 0)
            return false;
        Clip clip1 = Clip.find_by_type(this, 2);
        if(clip1 != null && clip1.range.inColumnRange(i))
            return true;
        if(!clip.range.inColumnRange(i))
            return false;
        int j = columnPosition(i) - clip.xOffset();
        return j + columnWidth(i) > clip.horiz_origin && j < clip.width + clip.horiz_origin;
    }

    boolean isRowVisible(int i)
    {
        if(!isDisplayable())
            return false;
        if(i == -1)
            return row_label_width > 0 && rowHeight(i) > 0;
        if(i < 0 || i >= rows || rowHeight(i) <= 0)
            return false;
        Clip clip1 = Clip.find_by_type(this, 3);
        if(clip1 != null && clip1.range.inRowRange(i))
            return true;
        if(!clip.range.inRowRange(i))
            return false;
        int j = rowPosition(i) - clip.yOffset();
        return j + rowHeight(i) > clip.vert_origin && j < clip.height + clip.vert_origin;
    }

    boolean IS_TRAVERSABLE(int i, int j)
    {
        return isCell(i, j) && getRowPixelHeight(i) > 0 && getColumnPixelWidth(j) > 0 && ((Boolean)traversable_series.getValue(i, j)).equals(Boolean.TRUE);
    }

    private Color getRepeatColor(Object obj, int i, int j, boolean flag)
    {
        Color acolor[] = flag ? repeat_bg_colors : repeat_fg_colors;
        if(acolor == null)
        {
            if(flag)
                return getBackground();
            else
                return getForeground();
        } else
        {
            int k = ((Integer)obj).intValue();
            int l = k != -995 ? j : i;
            int i1 = l >= 0 ? l % acolor.length : (acolor.length - (-1 * l) % acolor.length) % acolor.length;
            return acolor[i1];
        }
    }

    private void reset()
    {
        addNotify();
        Layout.doLayout(this);
        if(traversable)
            TraverseInitial.traverse(this, false);
        repaint();
    }

    final int rowHeight(int i)
    {
        if(i < -1)
            return 0;
        if(i == -1)
            return column_label_height + textHeightOffset() * 2;
        if(rows > 0)
            return row_heights.getValue(i);
        else
            return 0;
    }

    final int rowLabelExtraWidth()
    {
        if(rows > 0 && row_label_width > 0)
            return row_label_offset + 2 * frame_shadow;
        else
            return 0;
    }

    final int rowLabelOffset()
    {
        if(column_label_placement == 0)
            return columnLabelHeight() + colLabelExtraHeight();
        else
            return 0;
    }

    final int rowLabelPosition()
    {
        if(row_label_placement == 2)
            return frozenColumnWidth() + cell_width + row_label_offset + 2 * frame_shadow;
        else
            return 0;
    }

    final int rowLabelWidth()
    {
        if(rows > 0 && row_label_width > 0)
            return row_label_width + textWidthOffset() * 2;
        else
            return 0;
    }

    final int rowPosition(int i)
    {
        if(i == -1)
            return rowLabelPosition();
        else
            return row_heights.getPosition(i);
    }

    public void run()
    {
    }

    void setProperty(int i)
    {
        setProperty(i, 0, 0);
    }

    void setProperty(int i, int j, int k)
    {
        property_flags = i;
        if(isDisplayable() && set_property)
            property.set(i, j, k);
    }

    void setProperty(int i, JCCellRange jccellrange)
    {
        property_flags = i;
        if(isDisplayable() && set_property)
        {
            JCCellRange jccellrange1 = new JCCellRange();
            SeriesUtil.cvtRangeContextToRange(this, jccellrange, jccellrange1);
            for(int j = jccellrange1.start_row; j <= jccellrange1.end_row; j++)
            {
                for(int k = jccellrange1.start_column; k <= jccellrange1.end_column; k++)
                    property.set(i, j, k);

            }

        }
    }

    protected void setSeriesDefaults()
    {
        component_series = new Series(this);
        editable_series = new Series(this, true);
        maxlength_series = new Series(this, 0x7fffffff);
        multiline_series = new Series(this, false);
        stringcase_series = new Series(this, 0);
        traversable_series = new Series(this, true);
        defaultCellAppearance = new DefaultCellAppearance(0, null, null, null);
        defaultCellBorder = new DefaultCellBorder(new StandardCellBorder(3), 15);
    }

    int textHeight(int i)
    {
        return rowHeight(i) - 2 * shadow_thickness - 2 * margin_height;
    }

    final int textHeightOffset()
    {
        return margin_height + shadow_thickness;
    }

    int textWidth(int i)
    {
        return columnWidth(i) - 2 * shadow_thickness - 2 * margin_width;
    }

    final int textWidthOffset()
    {
        return margin_width + shadow_thickness;
    }

    void validateRes()
    {
        if(frozen_rows > 0 && frozen_rows >= rows)
        {
            System.out.println("Too many frozen rows: " + frozen_rows);
            frozen_rows = rows - 1;
        }
        if(frozen_columns > 0 && frozen_columns >= columns)
        {
            System.out.println("Too many frozen columns: " + frozen_columns);
            frozen_columns = columns - 1;
        }
        if(top_row != 0)
        {
            top_row = Math.max(0, Math.min(top_row, rows - 1));
            if(top_row != 0)
                set_top_row = true;
        }
        if(left_column != 0)
        {
            left_column = Math.max(0, Math.min(left_column, columns - 1));
            if(left_column != 0)
                set_left_column = true;
        }
        JCCellRange jccellrange = new JCCellRange();
        if(!set_left_column && getVisibleColumns(jccellrange))
            left_column = jccellrange.start_column;
        if(!set_top_row && getVisibleRows(jccellrange))
            top_row = jccellrange.start_row;
    }

    int vertSBwidth()
    {
        return 16 + vertsb_offset;
    }

    private final int xOffset(Clip clip1)
    {
        if(clip1.range.start_column >= 0)
            return columnPosition(clip1.range.start_column);
        else
            return 0;
    }

    int XtoColumn(int i)
    {
        int j = getColumn(i);
        if(j == 0x80000000)
            return 0;
        if(j == 0x7fffffff)
            return columns - 1;
        else
            return j;
    }

    int getColumn(int i)
    {
        if(!isDisplayable())
            return -999;
        if(columns == 0 || i < 0)
            return -999;
        if(left_column == -999 || left_column >= columns)
        {
            left_column = 0;
            JCCellRange jccellrange = new JCCellRange();
            if(getVisibleColumns(jccellrange))
                left_column = jccellrange.start_column;
        }
        return column_widths.getValueAtPosition(i, left_column);
    }

    private final int yOffset(Clip clip1)
    {
        if(clip1.range.start_row >= 0)
            return rowPosition(clip1.range.start_row);
        else
            return 0;
    }

    int YtoRow(int i)
    {
        int j = getRow(i);
        if(j == 0x80000000)
            return 0;
        if(j == 0x7fffffff)
            return rows - 1;
        else
            return j;
    }

    protected TablePopupMenu createPopupMenu()
    {
        return new TablePopupMenu(this);
    }

    protected boolean showPopupMenu(int i, int j)
    {
        popupMenu.show(this, i, j);
        return true;
    }

    int getRow(int i)
    {
        if(!isDisplayable())
            return -999;
        if(rows == 0 || i < 0)
            return -999;
        if(top_row == -999 || top_row >= rows)
        {
            top_row = 0;
            JCCellRange jccellrange = new JCCellRange();
            if(getVisibleRows(jccellrange))
                top_row = jccellrange.start_row;
        }
        return row_heights.getValueAtPosition(i, top_row);
    }

    public void dispose()
    {
        setLayout(null);
        removeAll();
        if(horiz_sb != null)
        {
            horiz_sb.dispose();
            horiz_sb = null;
        }
        if(vert_sb != null)
        {
            vert_sb.dispose();
            vert_sb = null;
        }
        Draw.clearCellImages();
        dataView.dispose();
        dataView = null;
        inputHandler.dispose();
        inputHandler = null;
        for(int i = 0; i < clip_list.length; i++)
        {
            if(clip_list[i] != null)
            {
                clip_list[i].dispose();
                removeClip(clip_list[i]);
            }
            clip_list[i] = null;
        }

        if(clip != null)
        {
            clip.dispose();
            removeClip(clip);
            clip = null;
        }
    }

    public void setActionKey(int i, int j, int k)
    {
        if(action_triggers == null)
            action_triggers = new Hashtable();
        action_triggers.put(new KeyPair(j, k), new Integer(i));
    }

    public int findActionKey(int i, int j)
    {
        if(action_triggers == null)
            return -999;
        for(Enumeration enumeration = action_triggers.keys(); enumeration.hasMoreElements();)
        {
            KeyPair keypair = (KeyPair)enumeration.nextElement();
            if(keypair.equals(i, j))
                return ((Integer)action_triggers.get(keypair)).intValue();
        }

        return -999;
    }

    public void removeActionKeys()
    {
        action_triggers = null;
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        String s = actionevent.getActionCommand();
        if(s.equals(TablePopupMenu.commands[0]))
        {
            hideColumn();
            return;
        }
        if(s.equals(TablePopupMenu.commands[1]))
        {
            showColumn();
            return;
        }
        if(s.equals(TablePopupMenu.commands[2]))
        {
            goToRow();
            return;
        }
        if(s.equals(TablePopupMenu.commands[3]))
        {
            print();
            return;
        }
        if(s.equals(TablePopupMenu.commands[4]))
            printPreview();
    }

    protected void printPreview()
    {
        JCPrintTable jcprinttable = new JCPrintTable(this);
        JCPrintPreview jcprintpreview = new JCPrintPreview("Print Preview", jcprinttable);
        jcprintpreview.showPage(0);
    }

    protected void hideColumn()
    {
        hideShowColumns(true);
    }

    protected void showColumn()
    {
        hideShowColumns(false);
    }

    protected void goToRow()
    {
        if(getCurrentCell() == null && !traverse(0, 0, false, false))
        {
            return;
        } else
        {
            GotoRowDialog gotorowdialog = new GotoRowDialog(new Frame(), getCurrentCell().row);
            gotorowdialog.open();
            traverse(gotorowdialog.getRow(), getCurrentCell().column, false, false);
            return;
        }
    }

    private void hideShowColumns(boolean flag)
    {
        Vector vector = new Vector();
        TableData tabledata = getDataSource();
        for(int i = 0; i < tabledata.getNumColumns(); i++)
            vector.addElement(tabledata.getTableColumnLabel(i));

        Vector vector1 = new Vector();
        for(int j = 0; j < tabledata.getNumColumns(); j++)
            if(flag && getColumnPixelWidth(j) != 0 || !flag && getColumnPixelWidth(j) == 0)
                vector1.addElement(vector.elementAt(j));

        if(vector1.isEmpty())
        {
            showMessage("Error", "There are no columns to " + (flag ? "hide" : "show"));
            return;
        }
        PickColumnsDialog pickcolumnsdialog = new PickColumnsDialog(new Frame(), flag, vector1);
        pickcolumnsdialog.pack();
        pickcolumnsdialog.center();
        pickcolumnsdialog.show();
        int ai[] = pickcolumnsdialog.getSelected();
        if(ai != null && ai.length != 0)
        {
            if(!flag)
            {
                int k = getNumRows();
                setVariableEstimateCount(k >= 100 ? 100 : k);
            }
            setRepaint(false);
            for(int l = 0; l < ai.length; l++)
            {
                for(int i1 = 0; i1 < vector.size(); i1++)
                {
                    String s = (String)vector1.elementAt(ai[l]);
                    String s1 = (String)vector.elementAt(i1);
                    if(s.compareTo(s1) != 0)
                        continue;
                    setPixelWidth(i1, flag ? 0 : 33002);
                    break;
                }

            }

            setRepaint(true);
        }
    }

    public Frame getParentFrame()
    {
        Object obj;
        for(obj = this; obj != null && !(obj instanceof Frame); obj = ((Component) (obj)).getParent());
        if(obj != null)
            return (Frame)obj;
        else
            return null;
    }

    protected void showMessage(String s, String s1)
    {
        MessageBox messagebox = new MessageBox(getParentFrame(), s);
        messagebox.setLocation(this);
        messagebox.showMessage(s1);
    }

    public Graphics getGraphics()
    {
        Graphics g = super.getGraphics();
        Rectangle rectangle = getVisibleRect();
        if(g != null)
            g.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        return g;
    }

    public void setPopupMenuEnabled(boolean flag)
    {
        popupMenuEnabled = flag;
        if(popupMenuEnabled)
        {
            if(popupMenu == null)
            {
                popupMenu = createPopupMenu();
                add(popupMenu);
            }
            setColumnTrigger(new LabelTrigger(4, 3));
            setRowTrigger(new LabelTrigger(4, 3));
            setCellTrigger(new LabelTrigger(4, 3));
            return;
        } else
        {
            removeColumnTrigger(4);
            removeRowTrigger(4);
            removeCellTrigger(4);
            return;
        }
    }

    public boolean getPopupMenuEnabled()
    {
        return popupMenuEnabled;
    }

    protected boolean isTraverseAllowed(int i, int j)
    {
        return true;
    }

    protected void afterCellSelected()
    {
    }

    protected Class getColumnClass(int i)
    {
        return null;
    }

    //public static final String version = JCVersion.getVersionString();
    TableDataView dataView;
    Property property;
    int rows;
    int columns;
    int allow_cell_resize;
    int auto_scroll;
    Color cellBorderColor;
    Color cellBorderDarker;
    Color cellBorderBrighter;
    int clip_arrows;
    int column_label_placement;
    int column_label_offset;
    boolean column_label_display;
    boolean double_buffer;
    int edit_height_policy;
    int edit_width_policy;
    int estimate_count;
    Color focus_rect_color;
    int frame_bordertype;
    int frame_shadow;
    int frozen_columns;
    int frozen_rows;
    int frozen_column_placement;
    int frozen_row_placement;
    TableScrollbar horiz_sb;
    int horizsb_attach;
    int horizsb_position;
    int horizsb_offset;
    int horizsb_display;
    boolean ignore_container_size;
    int jump;
    int left_column;
    int margin_width;
    int margin_height;
    int min_cell_visibility;
    int mode;
    int row_label_placement;
    int row_label_offset;
    boolean row_label_display;
    boolean repaint;
    Color repeat_bg_colors[];
    Color repeat_fg_colors[];
    boolean resize_by_labels_only;
    boolean resize_even;
    Color selected_bg;
    Color selected_fg;
    boolean selected_bg_set;
    boolean selected_fg_set;
    boolean select_include_labels;
    int selection_mode;
    int selection_policy;
    int shadow_thickness;
    boolean sort_series;
    Span span;
    int top_row;
    boolean track_cursor;
    boolean traversable;
    boolean traverseCycle;
    TableScrollbar vert_sb;
    int vertsb_attach;
    int vertsb_position;
    int vertsb_offset;
    int vertsb_display;
    int visible_columns;
    int visible_rows;
    boolean advanced_editor_renderers;
    protected boolean isLiveTable;
    protected boolean useCellDoubleBuffer;
    protected boolean trackJCStringURL;
    boolean allow_select;
    JCVector selected_cells;
    int select_start_row;
    int select_last_row;
    int select_start_column;
    int select_last_column;
    boolean set_property;
    int property_flags;
    EditHandler editHandler;
    InputHandler inputHandler;
    TraverseHandler traverseHandler;
    protected TablePopupMenu popupMenu;
    protected boolean popupMenuEnabled;
    protected String printHeader;
    protected String printFooter;
    protected boolean showPrintMenu;
    protected boolean showPrintPreview;
    JCVector series_list;
    Series alignment_series;
    Series bg_series;
    Series bordersides_series;
    Series bordertype_series;
    Series celleditor_series;
    Series cellrenderer_series;
    Series charheight_series;
    Series charwidth_series;
    Series component_series;
    Series editable_series;
    Series font_series;
    Series fg_series;
    Series maxlength_series;
    Series multiline_series;
    Series pixelheight_series;
    Series pixelwidth_series;
    Series stringcase_series;
    Series traversable_series;
    Series userdata_series;
    protected JCListenerList cellDisplayListeners;
    protected JCListenerList createComponentListeners;
    protected JCListenerList displayComponentListeners;
    protected JCListenerList enterCellListeners;
    protected JCListenerList paintListeners;
    protected JCListenerList printListeners;
    protected JCListenerList resizeCellListeners;
    protected JCListenerList scrollListeners;
    protected JCListenerList selectListeners;
    protected JCListenerList sortListeners;
    protected JCListenerList traverseCellListeners;
    boolean in_setComponent;
    Chain column_widths;
    Chain row_heights;
    int column_label_height;
    int row_label_width;
    int cell_total_width;
    int cell_total_height;
    int cell_width;
    int cell_height;
    DefaultCellAppearance defaultCellAppearance;
    DefaultCellBorder defaultCellBorder;
    Rectangle cell_rect;
    Rectangle rowlabel_rect;
    Rectangle collabel_rect;
    int edit_row;
    int edit_column;
    Clip clip_list[];
    Clip clip;
    int cursor;
    boolean set_top_row;
    boolean set_left_column;
    JCCellRange draw_range;
    int desired_width;
    int desired_height;
    int resize_row;
    int resize_column;
    boolean scrolling;
    boolean in_cell_value_cb;
    boolean in_label_value_cb;
    boolean in_get_cell;
    boolean in_get_label;
    protected boolean needs_repaint;
    protected boolean needs_recalc;
    String name;
    transient Applet applet;
    transient AppletContext applet_context;
    transient String url;
    static boolean preloaded;
    Hashtable row_triggers;
    Hashtable column_triggers;
    Hashtable cell_triggers;
    Hashtable action_triggers;
    int sort_column;
    int sort_direction;
    boolean isJumping;
    static final String label_action_strings[] = {
        "Select", "Sort", "Drag"
    };
    static final int label_action_values[] = {
        0, 1, 2
    };
    Frame frame;
    Frame lite_dialog;
    private static boolean noMoreDialogs;
    boolean first_time;
    private static JCCellRange r = new JCCellRange();
    boolean is_printing;
    private Point temp_pos;



}
