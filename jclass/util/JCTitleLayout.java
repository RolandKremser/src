// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCTitleLayout.java

package jclass.util;

import java.awt.*;

public class JCTitleLayout
    implements LayoutManager
{

    public JCTitleLayout()
    {
        kids = new Component[5];
        top = new int[4];
        left = new int[4];
        right = new int[4];
        bottom = new int[4];
        strings = new String[4];
        for(int i = 0; i < top.length; i++)
            top[i] = left[i] = right[i] = bottom[i] = -999;

    }

    public void add(String s, String s1)
    {
        strings[getIndex(s)] = s1;
    }

    public void addLayoutComponent(String s, Component component)
    {
        int i = getIndex(s);
        if(kids[i] != null)
            remove(s);
        kids[i] = component;
    }

    private int getIndex(String s)
    {
        if("Center".equals(s))
            return 4;
        if("Top".equals(s))
            return 0;
        if("Bottom".equals(s))
            return 3;
        if("Right".equals(s))
            return 2;
        return !"Left".equals(s) ? -999 : 1;
    }

    private static Insets getInsets(Container container)
    {
        return container.getInsets();
    }

    private static Dimension getMinimumSize(Component component)
    {
        return component.getMinimumSize();
    }

    private static Dimension getPreferredSize(Component component)
    {
        return component.getPreferredSize();
    }

    private Dimension getPreferredSize(String s)
    {
        int i = getIndex(s);
        Dimension dimension = new Dimension(0, 0);
        if(kids[i] != null && kids[i].isVisible())
            dimension = getPreferredSize(kids[i]);
        return dimension;
    }

    private static Dimension getSize(Container container)
    {
        return container.getSize();
    }

    private static Point layout(int i, int j, int k, int l)
    {
        Point point = new Point(i, j);
        if(i == -999)
        {
            if(k == -999)
                point.x = l / 2 - j / 2;
            else
                point.x = l - (point.y + k);
        } else
        if(k != -999)
            point.y = l - k - i;
        return point;
    }

    public void layoutContainer(Container container)
    {
        Insets insets = getInsets(container);
        Dimension dimension = getSize(container);
        int i = insets.top;
        int j = dimension.height - insets.bottom;
        int k = insets.left;
        int l = dimension.width - insets.right;
        dimension.width = l - k;
        dimension.height = j - i;
        Dimension dimension1 = getPreferredSize("Top");
        Dimension dimension2 = getPreferredSize("Left");
        Dimension dimension3 = getPreferredSize("Right");
        Dimension dimension4 = getPreferredSize("Bottom");
        Dimension dimension5 = getPreferredSize("Center");
        byte byte0 = 0;
        byte byte1 = 0;
        byte0 = 20;
        byte1 = 20;
        Dimension dimension6 = new Dimension(dimension.width - (dimension2.width + dimension3.width), dimension.height - (dimension1.height + dimension4.height));
        if(dimension6.width < 0)
            dimension6.width = 0;
        if(dimension6.height < 0)
            dimension6.height = 0;
        Point point = new Point(dimension2.width + k, dimension1.height + i);
        if(kids[4] != null && kids[4].isVisible())
        {
            if(dimension6.width > dimension5.width && dimension5.width >= 0)
                dimension6.width = dimension5.width;
            if(dimension6.height > dimension5.height && dimension5.height >= 0)
                dimension6.height = dimension5.height;
        }
        if(dimension6.width > 0 && dimension6.height > 0 && kids[4] != null)
            setBounds(kids[4], point.x, point.y, dimension6.width, dimension6.height);
        if(kids[0] != null && kids[0].isVisible())
        {
            Point point1 = layout(top[0], dimension1.height, bottom[0], point.y - i);
            Point point5 = layout(left[0], dimension1.width, right[0], dimension6.width - byte0);
            setBounds(kids[0], point5.x + point.x, point1.x + i, point5.y, point1.y);
        }
        if(kids[3] != null && kids[3].isVisible())
        {
            Point point2 = layout(top[3], dimension4.height, bottom[3], dimension4.height);
            Point point6 = layout(left[3], dimension4.width, right[3], dimension6.width - byte0);
            setBounds(kids[3], point6.x + point.x, point2.x + point.y + dimension6.height, point6.y, point2.y);
        }
        if(kids[1] != null && kids[1].isVisible())
        {
            Point point3 = layout(top[1], dimension2.height, bottom[1], dimension6.height - byte1);
            Point point7 = layout(left[1], dimension2.width, right[1], point.x - k);
            setBounds(kids[1], point7.x <= 0 ? k : point7.x + k, point3.x + point.y, point7.y, point3.y);
        }
        if(kids[2] != null && kids[2].isVisible())
        {
            Point point4 = layout(top[2], dimension3.height, bottom[2], dimension6.height - byte1);
            Point point8 = layout(left[2], dimension3.width, right[2], dimension3.width);
            setBounds(kids[2], point8.x + point.x + dimension6.width, point4.x + point.y, point8.y, point4.y);
        }
    }

    public Dimension minimumLayoutSize(Container container)
    {
        Dimension dimension = new Dimension(0, 0);
        if(kids[2] != null && kids[2].isVisible())
        {
            Dimension dimension1 = getMinimumSize(kids[2]);
            dimension.height = Math.max(dimension1.height, dimension.height);
        }
        if(kids[1] != null && kids[1].isVisible())
        {
            Dimension dimension2 = getMinimumSize(kids[1]);
            dimension.height = Math.max(dimension2.height, dimension.height);
        }
        if(kids[4] != null && kids[4].isVisible())
        {
            Dimension dimension3 = getMinimumSize(kids[4]);
            dimension.width += dimension3.width;
            dimension.height = Math.max(dimension3.height, dimension.height);
        }
        if(kids[0] != null && kids[0].isVisible())
        {
            Dimension dimension4 = getMinimumSize(kids[0]);
            dimension.width = Math.max(dimension4.width, dimension.width);
            dimension.height += dimension4.height;
        }
        if(kids[3] != null && kids[3].isVisible())
        {
            Dimension dimension5 = getMinimumSize(kids[3]);
            dimension.width = Math.max(dimension5.width, dimension.width);
            dimension.height += dimension5.height;
        }
        Insets insets = getInsets(container);
        dimension.width += insets.left + insets.right;
        dimension.height += insets.top + insets.bottom;
        return dimension;
    }

    public Dimension preferredLayoutSize(Container container)
    {
        Dimension dimension = new Dimension(0, 0);
        if(kids[2] != null && kids[2].isVisible())
        {
            Dimension dimension1 = getPreferredSize(kids[2]);
            dimension.height = Math.max(dimension1.height, dimension.height);
        }
        if(kids[1] != null && kids[1].isVisible())
        {
            Dimension dimension2 = getPreferredSize(kids[1]);
            dimension.height = Math.max(dimension2.height, dimension.height);
        }
        if(kids[4] != null && kids[4].isVisible())
        {
            Dimension dimension3 = getPreferredSize(kids[4]);
            dimension.width += dimension3.width;
            dimension.height = Math.max(dimension3.height, dimension.height);
        }
        if(kids[0] != null && kids[0].isVisible())
        {
            Dimension dimension4 = getPreferredSize(kids[0]);
            dimension.width = Math.max(dimension4.width, dimension.width);
            dimension.height += dimension4.height;
        }
        if(kids[3] != null && kids[3].isVisible())
        {
            Dimension dimension5 = getPreferredSize(kids[3]);
            dimension.width = Math.max(dimension5.width, dimension.width);
            dimension.height += dimension5.height;
        }
        Insets insets = getInsets(container);
        dimension.width += insets.left + insets.right;
        dimension.height += insets.top + insets.bottom;
        return dimension;
    }

    public void remove(String s)
    {
        int i = getIndex(s);
        if(i >= 0 && kids[i] != null)
        {
            Component component = kids[i];
            kids[i] = null;
            component.getParent().remove(component);
        }
    }

    public void removeLayoutComponent(Component component)
    {
        for(int i = 0; i < kids.length; i++)
        {
            if(kids[i] != component)
                continue;
            kids[i] = null;
            break;
        }

    }

    private static void setBounds(Component component, int i, int j, int k, int l)
    {
        component.setBounds(i, j, k, l);
    }

    public void setOffsets(String s, Insets insets)
    {
        int i = getIndex(s);
        top[i] = insets.top;
        bottom[i] = insets.bottom;
        right[i] = insets.right;
        left[i] = insets.right;
    }

    public static final int TOP = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 3;
    public static final int CENTER = 4;
    private Component kids[];
    private int top[];
    private int left[];
    private int right[];
    private int bottom[];
    private String strings[];
    public static final int NOVALUE = -999;
}
