// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Draw.java

package jclass.table3;

import java.awt.*;
import java.util.Vector;
import javax.swing.JComponent;
import jclass.util.JCEnvironment;

// Referenced classes of package jclass.table3:
//            CellBorder, CellImage, Clip, JCCell, 
//            JCCellPosition, JCCellRange, JCTblEnum, JDKSupport, 
//            Span, StandardCellBorder, Table, TableCellInfo, 
//            Widget

class Draw
{

    static void cell(Table table, int i, int j)
    {
        if(table.rowHeight(i) == 0 || table.columnWidth(j) == 0)
            return;
        boolean flag = false;
        Clip clip = Clip.find(table, i, j);
        if(clip == null)
            return;
        if(table.span.span_list.size() > 0)
        {
            JCCellPosition jccellposition = new JCCellPosition();
            if(table.span.find(i, j, jccellposition) != -999 && (i != jccellposition.row || j != jccellposition.column))
            {
                if(table.draw_range.start_row == -999 || !table.draw_range.inside(jccellposition.row, jccellposition.column))
                    cell(table, jccellposition.row, jccellposition.column);
                return;
            }
        }
        if(table.component_series.size() > 0)
            flag = Widget.draw(table, i, j);
        TableCellInfo tablecellinfo = new TableCellInfo(table, clip, i, j);
        int k = table.textWidthOffset();
        int l = table.textHeightOffset();
        Point point = new Point(0, 0);
        table.getPosition(i, j, point);
        int i1 = point.x;
        int j1 = point.y;
        int k1 = tablecellinfo.cellWidth;
        int l1 = tablecellinfo.cellHeight;
        Rectangle rectangle = new Rectangle();
        JDKSupport.setBounds(rectangle, i1 + k, j1 + l, Math.max(0, k1 - 2 * k), Math.max(0, l1 - 2 * l));
        int i2 = rectangle.x;
        int j2 = rectangle.y;
        int k2 = rectangle.width;
        if(table.getMarginWidth() == 0x7fffffff)
            k2 = 0;
        int l2 = rectangle.height;
        if(table.getMarginHeight() == 0x7fffffff)
            l2 = 0;
        tablecellinfo.isSpanned = false;
        Rectangle rectangle1 = rectangle;
        if(!table.is_printing)
            intersect(clip.expose, rectangle1, rectangle1);
        background(tablecellinfo, i1, j1, k1, l1);
        if(table.edit_row == i && table.edit_column == j && !flag && table.getEditingComponent() == null)
            drawRect(table, tablecellinfo.gc, i, j, table.getFocusRectColor());
        if(flag)
            return;
        int i3 = tablecellinfo.str_height;
        int j3 = tablecellinfo.str_width;
        boolean flag1 = j3 > k2;
        boolean flag2 = i3 > l2;
        int k3 = 0;
        int l3 = 0;
        if(flag1 || flag2)
        {
            k3 = (i1 + k1) - tablecellinfo.cellBorderWidth;
            l3 = (j1 + l1) - tablecellinfo.cellBorderWidth;
        }
        foreground(table, tablecellinfo, i2, j2, j3, i3, flag1, flag2, k3, l3, rectangle1);
        tablecellinfo = null;
    }

    static synchronized void background(TableCellInfo tablecellinfo, int i, int j, int k, int l)
    {
        if(tablecellinfo.clip.gc == null)
            return;
        tablecellinfo.gc = tablecellinfo.clip.gc;
        if(!(tablecellinfo.cellBorder instanceof StandardCellBorder))
        {
            do_background(tablecellinfo.gc, tablecellinfo, i, j, k, l);
            return;
        }
        if(!tablecellinfo.table.double_buffer && k * l <= 0x186a0 || !tablecellinfo.table.useCellDoubleBuffer)
        {
            do_background(tablecellinfo.gc, tablecellinfo, i, j, k, l);
            return;
        }
        StandardCellBorder standardcellborder = (StandardCellBorder)tablecellinfo.cellBorder;
        int i1 = standardcellborder.getBorderType();
        Image image = null;
        int j1;
        for(j1 = 0; j1 < num_cell_images; j1++)
            if(cell_images[j1] != null && cell_images[j1].image != null && cell_images[j1].image.getWidth(null) == k && cell_images[j1].image.getHeight(null) == l && cell_images[j1].sides == tablecellinfo.cellBorderSides && cell_images[j1].border == i1 && cell_images[j1].shadow_thickness == tablecellinfo.cellBorderWidth && cell_images[j1].bg.equals(tablecellinfo.background))
                break;

        if(j1 == num_cell_images)
        {
            if(num_cell_images == 30)
            {
                j1--;
                int k1 = 0;
                int l1 = 0;
                if(JDK102 && cell_images[j1] != null && cell_images[j1].image != null && (k1 = cell_images[j1].image.getWidth(null)) >= k && (l1 = cell_images[j1].image.getHeight(null)) >= l)
                {
                    image = cell_images[j1].image;
                    if(k1 > k || l1 > l)
                    {
                        cell_images[j1].gc.setColor(tablecellinfo.background);
                        cell_images[j1].gc.fillRect(0, 0, k1, l1);
                    }
                }
            }
            if(image == null)
            {
                try
                {
                    image = tablecellinfo.clip.createImage(k, l);
                }
                catch(Exception _ex)
                {
                    image = null;
                }
                if(image == null)
                {
                    do_background(tablecellinfo.gc, tablecellinfo, i, j, k, l);
                    return;
                }
                if(num_cell_images < 30)
                {
                    cell_images[num_cell_images] = new CellImage();
                    num_cell_images++;
                }
                if(cell_images[j1] == null)
                    cell_images[j1] = new CellImage();
                cell_images[j1].image = image;
                cell_images[j1].gc = image.getGraphics();
            }
            cell_images[j1].bg = tablecellinfo.background;
            cell_images[j1].border = i1;
            cell_images[j1].sides = tablecellinfo.cellBorderSides;
            cell_images[j1].shadow_thickness = tablecellinfo.cellBorderWidth;
            cell_images[j1].count = 0;
            do_background(cell_images[j1].gc, tablecellinfo, 0, 0, k, l);
        } else
        {
            image = cell_images[j1].image;
        }
        tablecellinfo.gc.drawImage(image, i, j, null);
        cell_images[j1].count++;
        if(j1 > 0 && cell_images[j1 - 1].count < cell_images[j1].count)
        {
            CellImage cellimage = cell_images[j1 - 1];
            cell_images[j1 - 1] = cell_images[j1];
            cell_images[j1] = cellimage;
        }
    }

    static void do_background(Graphics g, TableCellInfo tablecellinfo, int i, int j, int k, int l)
    {
        g.setColor(tablecellinfo.background);
        g.fillRect(i, j, k, l);
        if(tablecellinfo.cellWidth == 0 || tablecellinfo.cellHeight == 0 || tablecellinfo.cellBorderSides == 0 || tablecellinfo.cellBorderWidth == 0)
            return;
        Color color;
        if(tablecellinfo.table.cellBorderColor != null)
        {
            color = tablecellinfo.table.cellBorderColor;
            if((tablecellinfo.cellBorder instanceof StandardCellBorder) && ((StandardCellBorder)tablecellinfo.cellBorder).getBorderType() == 5)
                tablecellinfo.bottomShadow = tablecellinfo.table.cellBorderDarker;
        } else
        {
            color = tablecellinfo.table.getBackground();
        }
        tablecellinfo.cellBorder.drawBackground(g, tablecellinfo.cellBorderWidth, tablecellinfo.cellBorderSides, i, j, k, l, tablecellinfo.topShadow, tablecellinfo.bottomShadow, color);
    }

    static void foreground(Table table, TableCellInfo tablecellinfo, int i, int j, int k, int l, boolean flag, boolean flag1, 
            int i1, int j1, Rectangle rectangle)
    {
        if(tablecellinfo.clip.gc == null)
            return;
        boolean flag2 = false;
        Graphics g;
        if(flag || flag1)
        {
            flag2 = true;
            g = getGraphics(tablecellinfo.clip, rectangle);
        } else
        {
            g = tablecellinfo.clip.gc;
        }
        tablecellinfo.draw(g, i, j);
        if(flag || flag1)
        {
            if(flag2)
                g.dispose();
            flag2 = false;
            g = tablecellinfo.clip.gc;
            g.setColor(tablecellinfo.getForeground());
        }
        if(flag2)
            g.dispose();
    }

    static Graphics getGraphics(Clip clip, Rectangle rectangle)
    {
        Graphics g = clip.gc.create();
        Rectangle rectangle1 = clip.getVisibleRect();
        g.clipRect(rectangle1.x, rectangle1.y, rectangle1.width, rectangle1.height);
        g.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        return g;
    }

    static void drawRect(Table table, Graphics g, int i, int j, Color color)
    {
        if(table.component_series.size() > 0 && Widget.getComponent(table, i, j, true) != null)
            return;
        JCCell.getBounds(table, null, i, j, cell_size);
        int k = table.shadow_thickness;
        boolean flag = false;
        Clip clip = Clip.find(table, i, j);
        if(clip == null)
            return;
        if(g == null)
        {
            g = clip.getGraphics();
            if(g == null)
                return;
            flag = true;
        }
        g.setColor(color);
        g.drawRect(cell_size.x + k, cell_size.y + k, cell_size.width - 1 - 2 * k, cell_size.height - 1 - 2 * k);
        g.drawRect(cell_size.x + 1 + k, cell_size.y + 1 + k, cell_size.width - 3 - 2 * k, cell_size.height - 3 - 2 * k);
        if(flag)
            g.dispose();
        if(table.double_buffer && clip.dbl_image != null)
        {
            g = clip.dbl_image.getGraphics();
            g.setColor(color);
            g.drawRect(cell_size.x + k, cell_size.y + k, cell_size.width - 1 - 2 * k, cell_size.height - 1 - 2 * k);
            g.drawRect(cell_size.x + 1 + k, cell_size.y + 1 + k, cell_size.width - 3 - 2 * k, cell_size.height - 3 - 2 * k);
            g.dispose();
        }
    }

    static void intersect(Rectangle rectangle, Rectangle rectangle1, Rectangle rectangle2)
    {
        int i = Math.max(rectangle.x, rectangle1.x);
        int j = Math.min(rectangle.x + rectangle.width, rectangle1.x + rectangle1.width);
        int k = Math.max(rectangle.y, rectangle1.y);
        int l = Math.min(rectangle.y + rectangle.height, rectangle1.y + rectangle1.height);
        JDKSupport.setBounds(rectangle2, i, k, j - i, l - k);
    }

    static final void drawTopLines(Graphics g, int i, int j, int k, int l, int i1, Color color)
    {
        g.setColor(color);
        for(int j1 = 0; j1 < i; j1++)
        {
            g.drawLine(j + j1, k + j1, (j + l) - (j1 + 1), k + j1);
            g.drawLine(j + j1, k + j1 + 1, j + j1, (k + i1) - (j1 + 1));
        }

    }

    static final void drawBottomLines(Graphics g, int i, int j, int k, int l, int i1, Color color)
    {
        g.setColor(color);
        for(int j1 = 1; j1 <= i; j1++)
        {
            g.drawLine((j + j1) - 1, (k + i1) - j1, (j + l) - j1, (k + i1) - j1);
            g.drawLine((j + l) - j1, (k + j1) - 1, (j + l) - j1, (k + i1) - j1);
        }

    }

    static void drawShadow(Graphics g, int i, int j, int k, int l, int i1, int j1, Color color, 
            Color color1)
    {
        if(i == 3)
        {
            drawTopLines(g, j, k, l, i1, j1, color1);
            drawBottomLines(g, j, k, l, i1, j1, color);
            return;
        } else
        {
            drawTopLines(g, j, k, l, i1, j1, color);
            drawBottomLines(g, j, k, l, i1, j1, color1);
            return;
        }
    }

    private static void arrow(Graphics g, int i, int j, int k, int l)
    {
        int ai[] = new int[3];
        int ai1[] = new int[3];
        ai[0] = j - l;
        ai1[0] = k - l;
        switch(i)
        {
        case 0: // '\0'
            ai[1] = ai[0];
            ai1[1] = ai1[0] + l;
            ai[2] = j;
            ai1[2] = k - l / 2;
            break;

        case 1: // '\001'
            ai[1] = ai[0] + l;
            ai1[1] = ai1[0];
            ai[2] = j - l / 2;
            ai1[2] = k;
            break;

        default:
            return;
        }
        g.fillPolygon(ai, ai1, 3);
    }

    static synchronized void clearCellImages()
    {
        if(cell_images != null)
        {
            for(int i = 0; i < cell_images.length; i++)
                if(cell_images[i] != null)
                {
                    cell_images[i].image = null;
                    cell_images[i] = null;
                }

        }
        num_cell_images = 0;
    }

    Draw()
    {
    }

    static final int ARROW_RIGHT = 0;
    static final int ARROW_DOWN = 1;
    static final int ARROW_SIZE = 4;
    static final int MAX_CELL_IMAGES = 30;
    static CellImage cell_images[] = new CellImage[30];
    static int num_cell_images;
    static boolean JDK102 = JCEnvironment.getJavaVersion() == 102;
    static Rectangle cell_size = new Rectangle();

}
