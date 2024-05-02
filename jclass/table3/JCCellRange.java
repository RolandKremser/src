// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCellRange.java

package jclass.table3;

import java.awt.Rectangle;
import java.io.Serializable;

// Referenced classes of package jclass.table3:
//            JCTblEnum

public class JCCellRange
    implements Serializable
{

    public JCCellRange()
    {
        reshape(-999, -999, -999, -999);
    }

    public JCCellRange(int i, int j, int k, int l)
    {
        reshape(i, j, k, l);
    }

    public JCCellRange(Rectangle rectangle)
    {
        reshape(rectangle.y, rectangle.x, rectangle.y + rectangle.height, rectangle.x + rectangle.width);
    }

    private static final int min(int i, int j)
    {
        if(i < j)
            return i;
        else
            return j;
    }

    private static final int max(int i, int j)
    {
        if(i > j)
            return i;
        else
            return j;
    }

    public final JCCellRange reshape(int i, int j, int k, int l)
    {
        start_row = i;
        start_column = j;
        end_row = k;
        end_column = l;
        return this;
    }

    public final JCCellRange reshape(int i, int j)
    {
        start_row = end_row = i;
        start_column = end_column = j;
        return this;
    }

    public final JCCellRange reshape(JCCellRange jccellrange)
    {
        start_row = jccellrange.start_row;
        start_column = jccellrange.start_column;
        end_row = jccellrange.end_row;
        end_column = jccellrange.end_column;
        return this;
    }

    public final JCCellRange reshape(Rectangle rectangle)
    {
        start_row = rectangle.y;
        start_column = rectangle.x;
        end_row = rectangle.y + rectangle.height;
        end_column = rectangle.x + rectangle.width;
        return this;
    }

    public final boolean equals(JCCellRange jccellrange)
    {
        return jccellrange.start_row == start_row && jccellrange.start_column == start_column && jccellrange.end_row == end_row && jccellrange.end_column == end_column;
    }

    public final boolean intersects(JCCellRange jccellrange)
    {
        return jccellrange.start_column + jccellrange.end_column >= start_column && jccellrange.start_row + jccellrange.end_row >= start_row && jccellrange.start_column < start_column + end_column && jccellrange.start_row < start_row + end_row;
    }

    public final boolean overlaps(JCCellRange jccellrange)
    {
        return end_row >= jccellrange.start_row && start_row <= jccellrange.end_row && end_column >= jccellrange.start_column && start_column <= jccellrange.end_column;
    }

    public final JCCellRange intersection(JCCellRange jccellrange)
    {
        return new JCCellRange(max(start_row, jccellrange.start_row), max(start_column, jccellrange.start_column), min(end_row, jccellrange.end_row), min(end_column, jccellrange.end_column));
    }

    public final void intersection(JCCellRange jccellrange, JCCellRange jccellrange1)
    {
        jccellrange1.reshape(max(start_row, jccellrange.start_row), max(start_column, jccellrange.start_column), min(end_row, jccellrange.end_row), min(end_column, jccellrange.end_column));
    }

    public final boolean inside(int i, int j)
    {
        return start_row != -999 && (start_row >= end_row ? end_row : start_row) <= i && (start_row <= end_row ? end_row : start_row) >= i && (start_column >= end_column ? end_column : start_column) <= j && (start_column <= end_column ? end_column : start_column) >= j;
    }

    public final boolean inRowRange(int i)
    {
        return start_row != -999 && i >= start_row && i <= end_row;
    }

    public final boolean inRowRange2(int i)
    {
        return min(start_row, end_row) <= i && (start_row <= end_row ? end_row : start_row) >= i;
    }

    public final boolean inColumnRange(int i)
    {
        return start_row != -999 && i >= start_column && i <= end_column;
    }

    public final boolean inColumnRange2(int i)
    {
        return min(start_column, end_column) <= i && max(start_column, end_column) >= i;
    }

    public String toString()
    {
        return "R" + start_row + "C" + start_column + ":R" + end_row + "C" + end_column;
    }

    public int start_row;
    public int start_column;
    public int end_row;
    public int end_column;
}
