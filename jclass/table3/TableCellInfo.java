// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableCellInfo.java

package jclass.table3;

import java.awt.*;
import java.io.Serializable;
import jclass.cell.*;

// Referenced classes of package jclass.table3:
//            JCCell, JCCellDisplayEvent, JCCellRange, JCTblEnum, 
//            JDKSupport, Span, StandardCellBorder, Table, 
//            TableDataView, Clip, CellBorder

public class TableCellInfo
    implements CellInfo, Serializable
{

    TableCellInfo(Table table1, Clip clip1, int i, int j)
    {
        this(table1, clip1, i, j, null);
    }

    TableCellInfo(Table table1, Clip clip1, int i, int j, Object obj)
    {
        isSpanned = false;
        cellBounds = new Rectangle();
        table = table1;
        clip = clip1;
        row = i;
        column = j;
        TableDataView tabledataview = table1.dataView;
        if(obj != null)
            cellData = obj;
        else
        if(i == -1)
            cellData = tabledataview.getColumnLabel(j);
        else
        if(j == -1)
            cellData = tabledataview.getRowLabel(i);
        else
            cellData = tabledataview.getCellData(i, j);
        cellRenderer = null;
        if(cellData instanceof CellData)
        {
            cellRenderer = ((CellData)cellData).getRenderer();
            cellData = ((CellData)cellData).getData();
        }
        if(cellRenderer == null)
            cellRenderer = tabledataview.getCellRenderer(i, j, cellData);
        cellBorder = table1.getCellBorderType(i, j);
        cellBorderSides = table1.getCellBorderSides(i, j);
        cellBorderWidth = table1.getCellBorderWidth();
        isSelected = table1.isSelected(i, j);
        background = isSelected ? getBackgroundColors(getSelectedBackground()) : getBackgroundColors(table1.getBackground(i, j));
        setStringSize();
        isSpanned = getBounds(cellBounds);
        cellWidth = cellBounds.width;
        cellHeight = cellBounds.height;
    }

    public Color getBackground()
    {
        return table.getBackground(row, column);
    }

    public Color getForeground()
    {
        return table.getForeground(row, column);
    }

    public Color getSelectedBackground()
    {
        Color color = table.getSelectedBackground();
        if(color != null)
            return color;
        else
            return table.getBackground(row, column);
    }

    public Color getSelectedForeground()
    {
        Color color = table.getSelectedForeground();
        if(color != null)
            return color;
        else
            return table.getForeground(row, column);
    }

    public Font getFont()
    {
        Font font = table.getFont(row, column);
        if(font == null)
            return null;
        if(font != cellFont)
        {
            cellFontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
            cellFont = font;
        }
        return cellFont;
    }

    public FontMetrics getFontMetrics()
    {
        getFont();
        return cellFontMetrics;
    }

    public int getHorizontalAlignment()
    {
        int i = table.getAlignment(row, column);
        switch(i)
        {
        case 0: // '\0'
        case 3: // '\003'
        case 6: // '\006'
            return 0;

        case 1: // '\001'
        case 4: // '\004'
        case 7: // '\007'
            return 1;

        case 2: // '\002'
        case 5: // '\005'
        case 8: // '\b'
            return 2;
        }
        return 0;
    }

    public int getVerticalAlignment()
    {
        int i = table.getAlignment(row, column);
        switch(i)
        {
        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            return 0;

        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            return 1;

        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
            return 2;
        }
        return 0;
    }

    public Insets getMarginInsets()
    {
        int i = table.getMarginHeight();
        int j = table.getMarginWidth();
        return new Insets(i, j, i, j);
    }

    public Rectangle getDrawingArea()
    {
        JCCell.getBounds(table, null, row, column, size);
        size.width = size.width - (table.getMarginWidth() * 2 + table.getCellBorderWidth() * 2);
        size.height = size.height - (table.getMarginHeight() * 2 + table.getCellBorderWidth() * 2);
        size.x = size.y = 0;
        return size;
    }

    public Insets getBorderInsets()
    {
        int i = table.getCellBorderWidth();
        return new Insets(i, i, i, i);
    }

    public int getBorderStyle()
    {
        return 0;
    }

    public boolean isEditable()
    {
        return table.getEditable(row, column);
    }

    public boolean isEnabled()
    {
        return true;
    }

    public Table getTable()
    {
        return table;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public boolean getMultiline()
    {
        return table.getMultiline(row, column);
    }

    public int getMaxLength()
    {
        return table.getMaxLength(row, column);
    }

    public int getStringCase()
    {
        return table.getStringCase(row, column);
    }

    public boolean getSelectAll()
    {
        return false;
    }

    public int getClipHints()
    {
        int i = table.getClipArrows();
        switch(i)
        {
        case 1: // '\001'
            return 1;

        case 2: // '\002'
            return 2;

        case 0: // '\0'
            return 0;

        case 3: // '\003'
        case 4: // '\004'
        default:
            return 3;
        }
    }

    public Class getDataType()
    {
        if(cellData == null)
            return table.getColumnClass(column);
        else
            return cellData.getClass();
    }

    final void draw(Graphics g, int i, int j)
    {
        if(table.cellDisplayListeners != null)
        {
            JCCellDisplayEvent jccelldisplayevent = new JCCellDisplayEvent(table, row, column, cellData);
            table.fireJCCellDisplayEvent(jccelldisplayevent);
            cellData = jccelldisplayevent.getDisplayString();
        }
        if(cellData == null)
            return;
        if(row == table.edit_row && column == table.edit_column && table.getEditingComponent() != null)
        {
            return;
        } else
        {
            g.translate(i, j);
            cellRenderer.draw(g, this, cellData, isSelected);
            g.translate(-i, -j);
            return;
        }
    }

    final boolean getBounds(Rectangle rectangle)
    {
        if(table.span.doSpansExist())
        {
            Dimension dimension = new Dimension();
            JCCellRange jccellrange = new JCCellRange();
            if(table.span.find(row, column, jccellrange, dimension) != -999)
            {
                overflowRow = jccellrange.start_row;
                overflowColumn = jccellrange.start_column;
                Point point = new Point(0, 0);
                table.getPosition(jccellrange.start_row, jccellrange.start_column, point);
                JDKSupport.setBounds(rectangle, point.x, point.y, dimension.width, dimension.height);
                return true;
            }
        }
        overflowRow = -999;
        overflowColumn = -999;
        table.getBoundsInternal(row, column, rectangle);
        return false;
    }

    final Color getBackgroundColors(Color color)
    {
        if(cellBorder instanceof StandardCellBorder)
        {
            StandardCellBorder standardcellborder = (StandardCellBorder)cellBorder;
            if(standardcellborder.getBorderType() != 5)
            {
                if(color.equals(Color.white))
                {
                    topShadow = WHITE_BRIGHTER;
                    bottomShadow = WHITE_DARKER;
                } else
                if(color.equals(Color.black))
                {
                    topShadow = BLACK_BRIGHTER;
                    bottomShadow = BLACK_DARKER;
                } else
                if(color == lastBackground)
                {
                    topShadow = lastTopShadow;
                    bottomShadow = lastBottomShadow;
                } else
                {
                    if(table.cellBorderColor == null)
                    {
                        topShadow = color.brighter();
                        bottomShadow = color.darker();
                    } else
                    {
                        topShadow = table.cellBorderBrighter;
                        bottomShadow = table.cellBorderDarker;
                    }
                    lastBackground = color;
                    lastTopShadow = topShadow;
                    lastBottomShadow = bottomShadow;
                }
            } else
            {
                topShadow = bottomShadow = table.getForeground(row, column);
            }
        } else
        {
            topShadow = bottomShadow = table.getForeground(row, column);
        }
        return color;
    }

    final void setStringSize()
    {
        str_width = 0;
        str_height = 0;
        if(cellData == null)
            return;
        Dimension dimension = cellRenderer.getPreferredSize(this, cellData);
        if(dimension == null)
        {
            FontMetrics fontmetrics = getFontMetrics();
            str_width = getWidth(fontmetrics, cellData.toString());
            str_height = getHeight(fontmetrics, cellData.toString());
            return;
        } else
        {
            str_width = dimension.width;
            str_height = dimension.height;
            return;
        }
    }

    private static final int getHeight(FontMetrics fontmetrics, String s)
    {
        if(s == null || s.length() == 0)
            return 0;
        int i = 1;
        for(int j = 0; j < s.length(); j++)
            if(s.charAt(j) == '\n')
                i++;

        return fontmetrics.getHeight() * i;
    }

    private static final int getWidth(FontMetrics fontmetrics, String s)
    {
        if(s == null || s.length() == 0)
            return 0;
        if(s.indexOf('\n') != -1)
        {
            int i = 0;
            int k = 0;
            int j;
            while((j = s.indexOf('\n', i)) != -1) 
            {
                k = Math.max(k, fontmetrics.stringWidth(s.substring(i, j)));
                i = j + 1;
            }
            return Math.max(k, fontmetrics.stringWidth(s.substring(i, s.length())));
        } else
        {
            return fontmetrics.stringWidth(s);
        }
    }

    Table table;
    int row;
    int column;
    Clip clip;
    Object cellData;
    CellRenderer cellRenderer;
    boolean isSpanned;
    int overflowRow;
    int overflowColumn;
    int cellWidth;
    int cellHeight;
    CellBorder cellBorder;
    int cellBorderSides;
    int cellBorderWidth;
    boolean isSelected;
    Color background;
    Color topShadow;
    Color bottomShadow;
    Graphics gc;
    int str_width;
    int str_height;
    private Rectangle cellBounds;
    private static Color lastBackground;
    private static Color lastTopShadow;
    private static Color lastBottomShadow;
    private static final Color WHITE_BRIGHTER = new Color(200, 200, 200);
    private static final Color WHITE_DARKER = new Color(140, 140, 140);
    private static final Color BLACK_BRIGHTER = new Color(125, 125, 125);
    private static final Color BLACK_DARKER = new Color(75, 75, 75);
    private static Font cellFont;
    private static FontMetrics cellFontMetrics;
    static Rectangle size = new Rectangle();

}
