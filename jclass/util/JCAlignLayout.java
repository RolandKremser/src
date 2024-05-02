// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCAlignLayout.java

package jclass.util;

import java.awt.*;
import java.util.Hashtable;

// Referenced classes of package jclass.util:
//            NewGridLayout

public class JCAlignLayout extends NewGridLayout
{

    public JCAlignLayout()
    {
        this(2, 5, 5);
    }

    public JCAlignLayout(int i, int j, int k)
    {
        super(0, i, j, k);
    }

    private int get(Hashtable hashtable, Component component, int i)
    {
        Object obj = hashtable == null ? null : hashtable.get(String.valueOf(component.hashCode()));
        return obj == null ? i : ((Integer)obj).intValue();
    }

    private boolean get(Hashtable hashtable, Component component, boolean flag)
    {
        Object obj = hashtable == null ? null : hashtable.get(String.valueOf(component.hashCode()));
        return obj == null ? flag : ((Boolean)obj).booleanValue();
    }

    public int getLabelVerticalAlignment(Component component)
    {
        return get(alignment, component, 4);
    }

    public boolean getResizeHeight(Component component)
    {
        return get(resize_height, component, false);
    }

    public boolean getResizeWidth(Component component)
    {
        return get(resize_width, component, false);
    }

    protected boolean isLabel(int i)
    {
        return i % 2 == 0;
    }

    protected void setBounds(int i, int j, int k, Component component, int l, int i1, int j1, 
            int k1)
    {
        int l1 = j1;
        int i2 = k1;
        if(isLabel(k) || !getResizeHeight(component))
            i2 = component.getPreferredSize().height;
        if(!isLabel(k))
        {
            if(k >= super.col_widths.length - 1)
                if(getResizeWidth(component))
                {
                    Container container = component.getParent();
                    l1 = container.getSize().width - container.getInsets().right - l;
                } else
                {
                    l1 = component.getPreferredSize().width;
                }
            component.setBounds(l, i1, l1, i2);
            return;
        }
        int j2 = k1;
        if(i < component.getParent().countComponents() - 1)
        {
            Component component1 = component.getParent().getComponents()[i + 1];
            if(component1 != null && !getResizeHeight(component1))
                j2 = component1.getPreferredSize().height;
        }
        switch(getLabelVerticalAlignment(component))
        {
        case 4: // '\004'
            i1 += (j2 - i2) / 2;
            break;

        case 5: // '\005'
            i1 += j2 - i2;
            break;
        }
        component.setBounds(l, i1, l1, i2);
    }

    public void setLabelVerticalAlignment(Component component, int i)
    {
        if(alignment == null)
            alignment = new Hashtable(5);
        alignment.put(String.valueOf(component.hashCode()), new Integer(i));
    }

    public void setResizeHeight(Component component, boolean flag)
    {
        if(resize_height == null)
            resize_height = new Hashtable(5);
        resize_height.put(String.valueOf(component.hashCode()), new Boolean(flag));
    }

    public void setResizeWidth(Component component, boolean flag)
    {
        if(resize_width == null)
            resize_width = new Hashtable(5);
        resize_width.put(String.valueOf(component.hashCode()), new Boolean(flag));
    }

    protected Hashtable alignment;
    protected Hashtable resize_width;
    protected Hashtable resize_height;
    public static final int TOP = 1;
    public static final int MIDDLE = 4;
    public static final int BOTTOM = 5;
}
