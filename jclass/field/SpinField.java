// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCSpin.java

package jclass.field;

import java.awt.*;
import jclass.base.BaseComponent;
import jclass.base.TransientComponent;
import jclass.bwt.JCTextField;

// Referenced classes of package jclass.field:
//            JCSpin, SpinArrowButton

class SpinField extends JCTextField
{

    SpinField()
    {
    }

    protected void drawHighlight(Graphics g, boolean flag)
    {
        if(super.highlight == 0)
            return;
        if(!intersects(super.paint_rect, super.highlight))
            return;
        int i = 0;
        int j = 0;
        int k = getSize().width;
        int l = getSize().height;
        Color color = flag ? super.highlight_color : getBackground();
        if(color == null)
            color = Color.black;
        g.setColor(color);
        for(int i1 = 0; i1 < super.highlight;)
        {
            g.drawRect(i, j, k - 1, l - 1);
            i1++;
            i++;
            j++;
            k -= 2;
            l -= 2;
        }

    }
}
