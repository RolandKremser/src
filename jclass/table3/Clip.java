// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Clip.java

package jclass.table3;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

// Referenced classes of package jclass.table3:
//            Border, DragDraw, Draw, EditHandler, 
//            JCCell, JCCellRange, JCPaintEvent, JCPrintTable, 
//            JCTblEnum, JDKSupport, Table, TableDataView, 
//            Widget

class Clip extends JComponent
{

    Clip(Table table1, int i)
    {
        range = new JCCellRange();
        expose = new Rectangle();
        dragging = false;
        drag_row = -999;
        drag_column = -999;
        over_row = -999;
        over_column = -999;
        suspend_paint = false;
        rect = new Rectangle(0, 0);
        type = i;
        table = table1;
        Clip aclip[] = table.clip_list;
        table.clip_list = new Clip[aclip.length + 1];
        System.arraycopy(aclip, 0, table.clip_list, 0, aclip.length);
        table.clip_list[aclip.length] = this;
        setLayout(null);
        switch(i)
        {
        case 0: // '\0'
            orientation = 3;
            break;

        case 2: // '\002'
            orientation = 2;
            break;

        case 3: // '\003'
            orientation = 1;
            break;

        case 1: // '\001'
            orientation = 0;
            break;

        case 4: // '\004'
            orientation = 2;
            break;

        case 5: // '\005'
            orientation = 1;
            break;

        case 6: // '\006'
            orientation = 0;
            break;

        case 7: // '\007'
            orientation = 0;
            break;
        }
        if(i != 0)
        {
            if(isHorizontal())
                horiz_origin = table.clip.horiz_origin;
            if(isVertical())
                vert_origin = table.clip.vert_origin;
        }
        table.add(this);
    }

    static Clip clip_create(Table table1, int i)
    {
        Clip clip = find_by_type(table1, i);
        if(clip != null)
            return clip;
        else
            return new Clip(table1, i);
    }

    static Clip Create(Table table1, int i)
    {
        Clip clip = find_by_type(table1, i);
        if(clip != null)
            return clip;
        switch(i)
        {
        default:
            break;

        case 2: // '\002'
            if(table1.columns == 0 || table1.frozen_columns <= 0)
                return null;
            break;

        case 3: // '\003'
            if(table1.rows == 0 || table1.frozen_rows <= 0)
                return null;
            break;
        }
        return clip_create(table1, i);
    }

    public static Clip find_by_type(Table table1, int i)
    {
        if(table1 == null)
            return null;
        for(int j = 0; j < table1.clip_list.length; j++)
        {
            Clip clip = table1.clip_list[j];
            if(clip.type == i)
                return clip;
        }

        return null;
    }

    public static Clip find(Table table1, int i, int j)
    {
        if(table1 == null)
            return null;
        if(table1.getPrinting())
            return find_by_type(table1, 8);
        for(int k = 0; k < table1.clip_list.length; k++)
        {
            Clip clip = table1.clip_list[k];
            JCCellRange jccellrange = clip.range;
            if(jccellrange.start_row != -999 && (jccellrange.start_row >= jccellrange.end_row ? jccellrange.end_row : jccellrange.start_row) <= i && (jccellrange.start_row <= jccellrange.end_row ? jccellrange.end_row : jccellrange.start_row) >= i && (jccellrange.start_column >= jccellrange.end_column ? jccellrange.end_column : jccellrange.start_column) <= j && (jccellrange.start_column <= jccellrange.end_column ? jccellrange.end_column : jccellrange.start_column) >= j)
                return clip;
        }

        return null;
    }

    public boolean isHorizontal()
    {
        return (orientation & 1) != 0;
    }

    public boolean isVertical()
    {
        return (orientation & 2) != 0;
    }

    public boolean hasOrientation(int i)
    {
        return (orientation & i) != 0;
    }

    public int xOffset()
    {
        synchronized(getAWTLock())
        {
            int i = range.start_column < 0 ? 0 : table.columnPosition(range.start_column);
            return i;
        }
    }

    public int yOffset()
    {
        synchronized(getAWTLock())
        {
            int i = range.start_row < 0 ? 0 : table.rowPosition(range.start_row);
            return i;
        }
    }

    public boolean IN_ROWRANGE(int i)
    {
        return range.start_row != -999 && i >= range.start_row && i <= range.end_row;
    }

    public boolean IN_COLUMNRANGE(int i)
    {
        return range.start_row != -999 && i >= range.start_column && i <= range.end_column;
    }

    public void paint(Graphics g)
    {
        try
        {
            if(!table.repaint)
            {
                table.needs_repaint = true;
                return;
            } else
            {
                paint(g, g.getClipRect());
                return;
            }
        }
        catch(Exception _ex)
        {
            return;
        }
    }

    public void update(Graphics g)
    {
        paint(g);
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

    public void setBounds(int i, int j, int k, int l)
    {
        synchronized(getAWTLock())
        {
            if(!table.ignore_container_size)
            {
                Dimension dimension = getToolkit().getScreenSize();
                k = Math.max(1, Math.min(k, dimension.width));
                l = Math.max(1, Math.min(l, dimension.height));
            } else
            {
                k = Math.max(1, k);
                l = Math.max(1, l);
            }
        }
        super.setBounds(i, j, k, l);
    }

    void paint(int i, int j)
    {
        JCCell.getBounds(table, null, i, j, rect);
        paintImmediately(rect);
    }

    public void repaint()
    {
        super.repaint();
    }

    void paint(int i, int j, int k, int l)
    {
        Point point = new Point(0, 0);
        synchronized(getAWTLock())
        {
            if(i < range.start_row)
                i = range.start_row;
            if(j < range.start_column)
                j = range.start_column;
            if(k > range.end_row)
                k = range.end_row;
            if(l > range.end_column)
                l = range.end_column;
            table.getPosition(i, j, point);
            JCCell.getBounds(table, null, k, l, rect);
        }
        JDKSupport.setBounds(rect, point.x, point.y, (rect.x + rect.width) - point.x, (rect.y + rect.height) - point.y);
        paint(null, rect, i, j, k, l);
    }

    void paint(JCCellRange jccellrange)
    {
        paint(jccellrange.start_row, jccellrange.start_column, jccellrange.end_row, jccellrange.end_column);
    }

    void paint(Graphics g, Rectangle rectangle)
    {
        int i;
        int j;
        int l;
        int k = l = i = j = -999;
        if(rectangle == null)
            rectangle = new Rectangle(0, 0, size().width, size().height);
        switch(type)
        {
        case 4: // '\004'
            k = l = -1;
            break;

        case 6: // '\006'
            i = range.start_row;
            j = range.end_row;
            k = l = -1;
            break;

        case 5: // '\005'
            i = j = -1;
            break;

        case 7: // '\007'
            k = range.start_column;
            l = range.end_column;
            i = j = -1;
            break;

        case 2: // '\002'
            k = range.start_column;
            l = range.end_column;
            break;

        case 3: // '\003'
            i = range.start_row;
            j = range.end_row;
            break;

        case 1: // '\001'
            k = range.start_column;
            l = range.end_column;
            i = range.start_row;
            j = range.end_row;
            break;
        }
        int i1 = rectangle.y + yOffset() + vert_origin;
        int j1 = rectangle.x + xOffset() + horiz_origin;
        if(i == -999)
            i = table.YtoRow(i1);
        if(j == -999)
            j = table.YtoRow(i1 + rectangle.height);
        if(k == -999)
            k = table.XtoColumn(j1);
        if(l == -999)
            l = table.XtoColumn(j1 + rectangle.width);
        if(!Table.isCell(i, k) && !Table.isLabel(i, k) && table.dataView.getNumRows() != 0 && table.dataView.getNumColumns() != 0)
        {
            return;
        } else
        {
            paint(g, rectangle, i, k, j, l);
            return;
        }
    }

    private void paint(Graphics g, Rectangle rectangle, int i, int j, int k, int l)
    {
        Dimension dimension = size();
        if(dimension.width <= 0 || dimension.height <= 0)
            return;
        synchronized(getAWTLock())
        {
            boolean flag = false;
            JDKSupport.setBounds(expose, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            table.draw_range.reshape(i, j, k, l);
            JCPaintEvent jcpaintevent = null;
            if(table.paintListeners != null)
            {
                jcpaintevent = new JCPaintEvent(table, 1, rectangle, i, j, k, l);
                table.fireJCPaintEvent(jcpaintevent);
            }
            if(g == null)
            {
                g = getGraphics();
                if(g == null)
                    return;
                flag = true;
            }
            g.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            Graphics g1 = null;
            if(table.double_buffer)
            {
                if(dbl_image == null || dbl_image.getWidth(null) < dimension.width || dbl_image.getHeight(null) < dimension.height)
                    try
                    {
                        dbl_image = createImage(dimension.width, dimension.height);
                    }
                    catch(Throwable _ex)
                    {
                        dbl_image = null;
                    }
                if(dbl_image == null || (gc = g1 = dbl_image.getGraphics()) == null)
                    gc = g;
                else
                    gc.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            } else
            {
                gc = g;
            }
            if(gc == null)
                return;
            if(table.getMode() == 1)
            {
                gc.setColor(getBackground());
                gc.fillRect(0, 0, dimension.width, dimension.height);
            }
            int i1 = i;
            int j1 = j;
            int k1 = k;
            int l1 = l;
            if(type == 0)
            {
                JCCellRange jccellrange = table.getVisibleCells();
                i1 = Math.max(i1, jccellrange.start_row);
                j1 = Math.max(j1, jccellrange.start_column);
                k1 = Math.min(k1, jccellrange.end_row);
                l1 = Math.min(l1, jccellrange.end_column);
            }
            for(int i2 = i1; i2 <= k1; i2++)
            {
                for(int j2 = j1; j2 <= l1; j2++)
                    try
                    {
                        if(dragging && (drag_row != over_row || drag_column != over_column))
                            DragDraw.cell(table, i2, j2, drag_row, drag_column, over_row, over_column);
                        else
                            Draw.cell(table, i2, j2);
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace(System.out);
                    }

            }

            if(table.double_buffer)
            {
                g.drawImage(dbl_image, 0, 0, null);
                g1.dispose();
            }
            if(jcpaintevent != null)
            {
                JCPaintEvent jcpaintevent1 = new JCPaintEvent(table, 2, rectangle, i, j, k, l);
                table.fireJCPaintEvent(jcpaintevent1);
            }
            super.paint(g);
            gc = null;
            if(flag)
                g.dispose();
        }
    }

    static JCCellRange get_range(Table table1, int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return new JCCellRange(table1.rows <= 0 ? -999 : table1.frozen_rows, table1.columns <= 0 ? -999 : table1.frozen_columns, table1.rows <= 0 ? -999 : table1.rows - 1, table1.columns <= 0 ? -999 : table1.columns - 1);

        case 1: // '\001'
            if(table1.frozen_rows == 0 || table1.frozen_columns == 0)
                return null;
            else
                return new JCCellRange(0, 0, table1.frozen_rows - 1, table1.frozen_columns - 1);

        case 2: // '\002'
            if(table1.rows == 0 || table1.columns == 0 || table1.frozen_columns == 0)
                return null;
            else
                return new JCCellRange(table1.frozen_rows, 0, table1.rows - 1, table1.frozen_columns - 1);

        case 3: // '\003'
            if(table1.rows == 0 || table1.columns == 0 || table1.frozen_rows == 0)
                return null;
            else
                return new JCCellRange(0, table1.frozen_columns, table1.frozen_rows - 1, table1.columns - 1);

        case 6: // '\006'
            if(table1.frozen_rows == 0 || table1.rowLabelWidth() == 0)
                return null;
            else
                return new JCCellRange(0, -1, table1.frozen_rows - 1, -1);

        case 4: // '\004'
            if(table1.rowLabelWidth() == 0)
                return null;
            else
                return new JCCellRange(table1.frozen_rows, -1, table1.rows - 1, -1);

        case 7: // '\007'
            if(table1.frozen_columns == 0 || table1.columnLabelHeight() == 0)
                return null;
            else
                return new JCCellRange(-1, 0, -1, table1.frozen_columns - 1);

        case 5: // '\005'
            if(table1.columnLabelHeight() == 0)
                return null;
            else
                return new JCCellRange(-1, table1.frozen_columns, -1, table1.columns - 1);
        }
        return null;
    }

    static JCCellRange[] get_rangeList(Table table1)
    {
        JCCellRange ajccellrange[] = new JCCellRange[9];
        for(int i = 0; i < 9; i++)
        {
            JCCellRange jccellrange = get_range(table1, i);
            ajccellrange[i] = jccellrange == null ? new JCCellRange() : jccellrange;
        }

        return ajccellrange;
    }

    void reset_internal_values()
    {
        if((range = get_range(table, type)) == null)
        {
            range = new JCCellRange();
            return;
        } else
        {
            background_color = table.getBackground(range.start_row, range.start_column);
            return;
        }
    }

    static void reshape(Table table1)
    {
        Clip clip3 = table1.clip;
        JDKSupport.setBounds(table1.cell_rect, 0x7fffffff, 0x7fffffff, 0, 0);
        JDKSupport.setBounds(table1.rowlabel_rect, 0x7fffffff, 0x7fffffff, 0, 0);
        JDKSupport.setBounds(table1.collabel_rect, 0x7fffffff, 0x7fffffff, 0, 0);
        int l = clip3.width;
        int i1 = clip3.height;
        if(table1.horizsb_position == 1 && table1.column_label_placement == 0 && table1.frozen_row_placement == 0)
        {
            i1 = table1.containerHeight() - table1.frozenRowLabelOffset() - 2 * table1.frame_shadow;
            if(table1.hasHorizSB())
                i1 -= table1.horizSBheight();
        }
        if(table1.vertsb_position == 1 && table1.row_label_placement == 0 && table1.frozen_column_placement == 0)
        {
            l = table1.containerWidth() - table1.frozenColumnLabelOffset() - 2 * table1.frame_shadow;
            if(table1.hasVertSB())
                l -= table1.vertSBwidth();
        }
        clip3.set_size(table1.cell_rect, table1.frozenColumnLabelOffset(), table1.frozenRowLabelOffset(), l, i1);
        Clip clip1;
        if(table1.frozen_columns > 0 && table1.frozen_rows > 0)
        {
            Clip clip = clip_create(table1, 1);
            clip.set_size(table1.cell_rect, table1.frozenColumnPosition(), table1.frozenRowPosition(), table1.frozenColumnWidth(), table1.frozenRowHeight());
        } else
        if((clip1 = find_by_type(table1, 1)) != null)
            clip1.hide();
        if(table1.frozen_columns > 0 && table1.columns > 0)
        {
            clip1 = clip_create(table1, 2);
            int k = table1.frozen_rows <= 0 ? table1.rowLabelOffset() : table1.frozenRowLabelOffset();
            clip1.set_size(table1.cell_rect, table1.frozenColumnPosition(), k, table1.frozenColumnWidth(), i1);
        } else
        if((clip1 = find_by_type(table1, 2)) != null)
            clip1.hide();
        if(table1.frozen_rows > 0 && table1.rows > 0)
        {
            clip1 = clip_create(table1, 3);
            int j = table1.frozen_columns <= 0 ? table1.columnLabelOffset() : table1.frozenColumnLabelOffset();
            clip1.set_size(table1.cell_rect, j, table1.frozenRowPosition(), l, table1.frozenRowHeight());
        } else
        if((clip1 = find_by_type(table1, 3)) != null)
            clip1.hide();
        if(table1.rowLabelWidth() > 0 && table1.frozen_rows > 0)
        {
            clip1 = clip_create(table1, 6);
            clip1.set_size(table1.rowlabel_rect, table1.rowLabelPosition(), table1.frozenRowPosition(), table1.rowLabelWidth(), table1.frozenRowHeight());
        } else
        if((clip1 = find_by_type(table1, 6)) != null)
            clip1.hide();
        if(table1.rowLabelWidth() > 0)
        {
            clip1 = clip_create(table1, 4);
            clip1.set_size(table1.rowlabel_rect, table1.rowLabelPosition(), table1.frozenRowLabelOffset(), table1.rowLabelWidth(), i1);
        } else
        if((clip1 = find_by_type(table1, 4)) != null)
            clip1.hide();
        if(table1.columnLabelHeight() > 0 && table1.frozen_columns > 0)
        {
            clip1 = clip_create(table1, 7);
            clip1.set_size(table1.collabel_rect, table1.frozenColumnPosition(), table1.columnLabelPosition(), table1.frozenColumnWidth(), table1.columnLabelHeight());
        } else
        if((clip1 = find_by_type(table1, 7)) != null)
            clip1.hide();
        if(table1.columnLabelHeight() > 0)
        {
            clip1 = clip_create(table1, 5);
            clip1.set_size(table1.collabel_rect, table1.frozenColumnLabelOffset(), table1.columnLabelPosition(), l, table1.columnLabelHeight());
        } else
        if((clip1 = find_by_type(table1, 5)) != null)
            clip1.hide();
        for(int i = 0; i < table1.clip_list.length; i++)
        {
            Clip clip2 = table1.clip_list[i];
            clip2.reset_internal_values();
        }

        if(table1.hasComponents())
            Widget.unmanageInvisible(table1);
    }

    private static void drawFrame(Graphics g, Table table1, Rectangle rectangle, boolean flag)
    {
        Color color = table1.getBackground();
        Border.draw(g, table1.frame_bordertype, table1.frame_shadow, 15, rectangle.x - table1.frame_shadow, rectangle.y - table1.frame_shadow, rectangle.width + 2 * table1.frame_shadow, rectangle.height + 2 * table1.frame_shadow, flag ? color : color.brighter(), flag ? color : color.darker(), color);
    }

    static void drawFrames(Graphics g, Table table1, boolean flag)
    {
        drawFrame(g, table1, table1.cell_rect, flag);
        if(table1.rowLabelWidth() > 0)
            drawFrame(g, table1, table1.rowlabel_rect, flag);
        if(table1.columnLabelHeight() > 0)
            drawFrame(g, table1, table1.collabel_rect, flag);
    }

    private void set_size(Rectangle rectangle, int i, int j, int k, int l)
    {
        int i1 = Math.max(1, k);
        int j1 = Math.max(1, l);
        setBounds(i += table.frame_shadow, j += table.frame_shadow, i1, j1);
        boolean flag = false;
        for(int k1 = 0; k1 < table.clip_list.length; k1++)
        {
            if(table.clip_list[k1] != this)
                continue;
            flag = true;
            break;
        }

        if(!flag)
            table.add(this);
        show(k > 0 && l > 0);
        rectangle.x = Math.min(rectangle.x, i);
        rectangle.y = Math.min(rectangle.y, j);
        switch(type)
        {
        case 4: // '\004'
        case 6: // '\006'
            rectangle.width = k;
            rectangle.height += l;
            return;

        case 3: // '\003'
            rectangle.height += l;
            return;

        case 5: // '\005'
        case 7: // '\007'
            rectangle.width += k;
            rectangle.height = l;
            return;

        case 2: // '\002'
            rectangle.width += k;
            return;

        case 0: // '\0'
            rectangle.width += k;
            rectangle.height += l;
            // fall through

        case 1: // '\001'
        default:
            return;
        }
    }

    void copyArea(Graphics g, int i, int j, int k, int l, int i1, int j1)
    {
        if(g == null)
            return;
        if(!table.double_buffer || dbl_image == null)
        {
            g.copyArea(i, j, k, l, i1, j1);
            return;
        } else
        {
            Graphics g1 = g;
            g = dbl_image.getGraphics();
            g.clipRect(0, 0, size().width, size().height);
            g.copyArea(i, j, k, l, i1, j1);
            g.clipRect(i + i1, j + j1, k, l);
            g1.drawImage(dbl_image, 0, 0, null);
            g.dispose();
            return;
        }
    }

    protected void processKeyEvent(KeyEvent keyevent)
    {
        if(keyevent.getID() == 401 && keyevent.getKeyCode() == 9)
        {
            keyevent.consume();
            table.keyPressed(keyevent);
            return;
        }
        if(keyevent.getID() == 401 && (keyevent.getKeyCode() == 17 || keyevent.getKeyCode() == 18))
        {
            keyevent.consume();
            return;
        } else
        {
            super.processKeyEvent(keyevent);
            return;
        }
    }

    public Object getAWTLock()
    {
        return getTreeLock();
    }

    static void setPrintTable(JCPrintTable jcprinttable)
    {
        Clip clip = Create(jcprinttable.print_table, 8);
        clip.printtable = jcprinttable;
    }

    JCPrintTable getPrintTable()
    {
        if(type == 8)
            return printtable;
        else
            return null;
    }

    void paint(Graphics g, JCCellRange jccellrange)
    {
        if(type == 8 && printtable != null)
        {
            gc = g;
            range = jccellrange;
            printtable.paintPage(g, jccellrange);
        }
    }

    void beginDrag(int i, int j, int k, int l)
    {
        if(!dragging || drag_row != i || drag_column != j || over_row != k || over_column != l)
        {
            dragging = true;
            drag_row = i;
            drag_column = j;
            over_row = k;
            over_column = l;
            repaint();
        }
    }

    void endDrag()
    {
        dragging = false;
        drag_row = -999;
        drag_column = -999;
        over_row = -999;
        over_column = -999;
    }

    public boolean isFocusTraversable()
    {
        return true;
    }

    void dispose()
    {
        dbl_image = null;
        removeAll();
        table = null;
    }

    public Graphics getGraphics()
    {
        Graphics g = super.getGraphics();
        Rectangle rectangle = getVisibleRect();
        g.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        return g;
    }

    public void requestFocus()
    {
        if(inRequestFocus)
            return;
        inRequestFocus = true;
        if(table != null && table.editHandler != null && table.editHandler.editComponent != null)
            table.editHandler.editComponent.requestFocus();
        else
            super.requestFocus();
        inRequestFocus = false;
    }

    int orientation;
    JCCellRange range;
    Table table;
    int type;
    int horiz_origin;
    int vert_origin;
    int total_width;
    int total_height;
    int width;
    int height;
    Rectangle expose;
    transient Graphics gc;
    boolean dragging;
    int drag_row;
    int drag_column;
    int over_row;
    int over_column;
    boolean changed;
    transient Image dbl_image;
    Color background_color;
    boolean suspend_paint;
    static final int NONE = 0;
    static final int HORIZONTAL = 1;
    static final int VERTICAL = 2;
    static final int CELL = 0;
    static final int FIXEDCELL = 1;
    static final int FROZENCOLUMN = 2;
    static final int FROZENROW = 3;
    static final int ROWLABEL = 4;
    static final int COLUMNLABEL = 5;
    static final int FROZENROWLABEL = 6;
    static final int FROZENCOLUMNLABEL = 7;
    static final int PRINTPAGE = 8;
    static final int NUM_TYPE = 9;
    private Rectangle rect;
    JCPrintTable printtable;
    protected static boolean inRequestFocus;
}
