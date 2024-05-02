// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Border.java

package jclass.table3;

import java.awt.*;

// Referenced classes of package jclass.table3:
//            JCTblEnum

class Border
{

    private static void initRects(int i)
    {
        if(rects != null)
            rects = null;
        rects = new Rectangle[i * 4];
        for(int j = 0; j < i * 4; j++)
            rects[j] = new Rectangle();

    }

    private static void fillRectangles(Graphics g, Color color, Rectangle arectangle[], int i, int j)
    {
        g.setColor(color);
        for(int k = i; k < i + j; k++)
            g.fillRect(arectangle[k].x, arectangle[k].y, arectangle[k].width, arectangle[k].height);

    }

    private static void drawShadow(Graphics g, int i, int j, int k, int l, int i1, int j1, Color color, 
            Color color1)
    {
        if(i <= 0 || i1 <= 0 || j1 <= 0)
            return;
        if(i > i1 / 2)
            i = i1 / 2;
        if(i > j1 / 2)
            i = j1 / 2;
        if(i <= 0)
            return;
        initRects(i);
        int i2 = i + i;
        int j2 = i2 + i;
        for(int k1 = 0; k1 < i; k1++)
        {
            int l1 = (j & 8) == 0 ? 0 : k1;
            rects[k1].x = k;
            rects[k1].y = l + k1;
            rects[k1].width = i1 - l1;
            rects[k1].height = 1;
            l1 = (j & 4) == 0 ? 0 : k1;
            rects[k1 + i].x = k + k1;
            rects[k1 + i].y = l;
            rects[k1 + i].width = 1;
            rects[k1 + i].height = j1 - l1;
            l1 = (j & 1) == 0 ? 0 : k1;
            rects[k1 + i2].x = k + l1;
            rects[k1 + i2].y = (l + j1) - k1 - 1;
            rects[k1 + i2].width = i1 - l1;
            rects[k1 + i2].height = 1;
            l1 = (j & 2) == 0 ? 0 : k1;
            rects[k1 + j2].x = (k + i1) - k1 - 1;
            rects[k1 + j2].y = l + l1;
            rects[k1 + j2].width = 1;
            rects[k1 + j2].height = j1 - l1;
        }

        if((j & 2) != 0)
            fillRectangles(g, color, rects, 0, i);
        if((j & 1) != 0)
            fillRectangles(g, color, rects, i, i);
        if((j & 4) != 0)
            fillRectangles(g, color1, rects, i2, i);
        if((j & 8) != 0)
            fillRectangles(g, color1, rects, j2, i);
    }

    private static void get_rects(int i, int j, int k, int l, int i1, int j1, int k1, int l1, 
            int i2, int j2, int k2, Rectangle arectangle[])
    {
        if(arectangle == null)
            return;
        for(int l2 = 0; l2 < i;)
        {
            int j3 = j + j;
            int i3;
            if((k2 & 1) != 0)
            {
                arectangle[k1 + l2].x = k + j;
                i3 = (k2 & 8) == 0 ? 0 : j3;
            } else
            {
                arectangle[k1 + l2].x = k;
                i3 = (k2 & 8) == 0 ? 0 : j;
            }
            arectangle[k1 + l2].y = l + j;
            arectangle[k1 + l2].width = i1 - i3;
            arectangle[k1 + l2].height = 1;
            if((k2 & 2) != 0)
            {
                arectangle[l1 + l2].y = l + j;
                i3 = (k2 & 4) == 0 ? 0 : j3;
            } else
            {
                arectangle[l1 + l2].y = l;
                i3 = (k2 & 4) == 0 ? 0 : j;
            }
            arectangle[l1 + l2].x = k + j;
            arectangle[l1 + l2].width = 1;
            arectangle[l1 + l2].height = j1 - i3;
            if((k2 & 1) != 0)
            {
                arectangle[i2 + l2].x = k + j;
                i3 = (k2 & 8) == 0 ? 0 : j3;
            } else
            {
                arectangle[i2 + l2].x = k;
                i3 = (k2 & 8) == 0 ? 0 : j;
            }
            arectangle[i2 + l2].y = (l + j1) - j - 1;
            arectangle[i2 + l2].width = i1 - i3;
            arectangle[i2 + l2].height = 1;
            if((k2 & 2) != 0)
            {
                arectangle[j2 + l2].y = l + j;
                i3 = (k2 & 4) == 0 ? 0 : j3;
            } else
            {
                arectangle[j2 + l2].y = l;
                i3 = (k2 & 4) == 0 ? 0 : j;
            }
            arectangle[j2 + l2].x = (k + i1) - j - 1;
            arectangle[j2 + l2].width = 1;
            arectangle[j2 + l2].height = j1 - i3;
            l2++;
            j++;
        }

    }

    private static void drawEtchedShadow(Graphics g, int i, int j, int k, int l, int i1, int j1, Color color, 
            Color color1)
    {
        if(i <= 0 || i1 <= 0 || j1 <= 0)
            return;
        if(i == 1)
        {
            drawShadow(g, i, j, k, l, i1, j1, color, color1);
            return;
        }
        if(i > i1 / 2)
            i = i1 / 2;
        if(i > j1 / 2)
            i = j1 / 2;
        if(i <= 0)
            return;
        i = i % 2 == 0 ? i : i - 1;
        initRects(i);
        int l1 = i / 2;
        int i2 = i + i;
        int j2 = i2 + i;
        int k2 = 0;
        int l2 = l1;
        int i3 = i2;
        int j3 = i2 + l1;
        get_rects(l1, 0, k, l, i1, j1, k2, l2, i3, j3, j, rects);
        k2 = j2;
        l2 = j2 + l1;
        i3 = i;
        j3 = i + l1;
        get_rects(l1, l1, k, l, i1, j1, k2, l2, i3, j3, j, rects);
        int k1 = l1;
        if((j & 2) != 0)
        {
            fillRectangles(g, color, rects, 0, k1);
            fillRectangles(g, color1, rects, j2, k1);
        }
        if((j & 1) != 0)
        {
            fillRectangles(g, color, rects, k1, k1);
            fillRectangles(g, color1, rects, j2 + k1, k1);
        }
        if((j & 4) != 0)
        {
            fillRectangles(g, color, rects, i, k1);
            fillRectangles(g, color1, rects, i2, k1);
        }
        if((j & 8) != 0)
        {
            fillRectangles(g, color, rects, i + k1, k1);
            fillRectangles(g, color1, rects, i2 + k1, k1);
        }
    }

    static synchronized void draw(Graphics g, int i, int j, int k, int l, int i1, int j1, int k1, 
            Color color, Color color1, Color color2)
    {
        switch(i)
        {
        case 5: // '\005'
            drawShadow(g, j, k, l, i1, j1, k1, color1, color1);
            return;

        case 3: // '\003'
            drawShadow(g, j, k, l, i1, j1, k1, color1, color);
            return;

        case 4: // '\004'
            drawShadow(g, j, k, l, i1, j1, k1, color, color1);
            return;

        case 1: // '\001'
            drawEtchedShadow(g, j, k, l, i1, j1, k1, color1, color);
            return;

        case 2: // '\002'
            drawEtchedShadow(g, j, k, l, i1, j1, k1, color, color1);
            return;

        case 7: // '\007'
            drawShadow(g, j, k, l, i1, j1, k1, color, color1);
            drawShadow(g, j - 1, k, l, i1, j1, k1, color2, color2);
            return;

        case 6: // '\006'
            drawShadow(g, j, k, l, i1, j1, k1, color1, color);
            drawShadow(g, j - 1, k, l, i1, j1, k1, color2, color2);
            return;
        }
    }

    Border()
    {
    }

    private static Rectangle rects[] = null;

}
