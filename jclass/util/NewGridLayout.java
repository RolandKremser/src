// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NewGridLayout.java

package jclass.util;

import java.awt.*;

public class NewGridLayout extends GridLayout
{

    public NewGridLayout(int i, int j)
    {
        this(i, j, 0, 0);
    }

    public NewGridLayout(int i, int j, int k, int l)
    {
        super(i, j, k, l);
        row_heights = new int[0];
        col_widths = new int[0];
        rows = i;
        cols = j;
        hgap = k;
        vgap = l;
    }

    protected void getGridSizes(Container container, boolean flag)
    {
        int i = container.countComponents();
        if(i == 0)
            return;
        int j = rows;
        int k = cols;
        if(j > 0)
            k = ((i + j) - 1) / j;
        else
            j = ((i + k) - 1) / k;
        row_heights = new int[j];
        col_widths = new int[k];
        for(int l = 0; l < i; l++)
        {
            Component component = container.getComponent(l);
            Dimension dimension = flag ? component.getMinimumSize() : component.getPreferredSize();
            int i1 = l / k;
            if(dimension.height > row_heights[i1])
                row_heights[i1] = dimension.height;
            int j1 = l % k;
            if(dimension.width > col_widths[j1])
                col_widths[j1] = dimension.width;
        }

    }

    public void layoutContainer(Container container)
    {
        int i = container.countComponents();
        if(i == 0)
            return;
        Insets insets = container.getInsets();
        getGridSizes(container, false);
        int j = rows;
        int k = cols;
        if(j > 0)
            k = ((i + j) - 1) / j;
        else
            j = ((i + k) - 1) / k;
        Dimension dimension = container.size();
        int l = 0;
        int i1 = insets.left + hgap;
        for(; l < k; l++)
        {
            int j1 = 0;
            int k1 = insets.top + vgap;
            for(; j1 < j; j1++)
            {
                int l1 = j1 * k + l;
                if(l1 < i)
                {
                    int i2 = Math.max(0, Math.min(col_widths[l], dimension.width - insets.right - i1));
                    int j2 = Math.max(0, Math.min(row_heights[j1], dimension.height - insets.bottom - k1));
                    setBounds(l1, j1, l, container.getComponent(l1), i1, k1, i2, j2);
                }
                k1 += row_heights[j1] + vgap;
            }

            i1 += col_widths[l] + hgap;
        }

    }

    public Dimension minimumLayoutSize(Container container)
    {
        Insets insets = container.getInsets();
        getGridSizes(container, true);
        return new Dimension(insets.left + insets.right + sum(col_widths) + (col_widths.length + 1) * hgap, insets.top + insets.bottom + sum(row_heights) + (row_heights.length + 1) * vgap);
    }

    public Dimension preferredLayoutSize(Container container)
    {
        Insets insets = container.getInsets();
        getGridSizes(container, false);
        return new Dimension(insets.left + insets.right + sum(col_widths) + (col_widths.length + 1) * hgap, insets.top + insets.bottom + sum(row_heights) + (row_heights.length + 1) * vgap);
    }

    protected void setBounds(int i, int j, int k, Component component, int l, int i1, int j1, 
            int k1)
    {
        component.setBounds(l, i1, j1, k1);
    }

    final int sum(int ai[])
    {
        if(ai == null)
            return 0;
        int i = 0;
        for(int j = 0; j < ai.length; j++)
            i += ai[j];

        return i;
    }

    protected int hgap;
    protected int vgap;
    protected int rows;
    protected int cols;
    protected int row_heights[];
    protected int col_widths[];
    public static final int VARIABLE = 0;
}
