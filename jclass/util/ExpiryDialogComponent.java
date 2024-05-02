// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCVersion.java

package jclass.util;

import java.awt.*;

// Referenced classes of package jclass.util:
//            ExpiryDialog, JCString, JCVersion

final class ExpiryDialogComponent extends Canvas
{

    ExpiryDialogComponent(String s1, int i, Rectangle rectangle)
    {
        s = JCString.parse(this, s1);
        a = i;
        p = rectangle;
    }

    ExpiryDialogComponent(JCString jcstring, int i, Rectangle rectangle)
    {
        s = jcstring;
        a = i;
        p = rectangle;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(getForeground());
        Rectangle rectangle = new Rectangle(p.x, p.y, size().width - p.width, size().height - p.height);
        s.draw(this, g, rectangle, a);
    }

    public final Dimension preferredSize()
    {
        if(s != null)
        {
            Dimension dimension = s.getSize(this, null);
            return new Dimension(dimension.width + p.x + p.width, dimension.height + p.y + p.height);
        } else
        {
            return null;
        }
    }

    JCString s;
    int a;
    Rectangle p;
}
