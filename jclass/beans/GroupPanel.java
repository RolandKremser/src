// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GroupPanel.java

package jclass.beans;

import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class GroupPanel extends JPanel
{

    public GroupPanel(String s)
    {
        lin = null;
        setLayout(null);
        label = s;
        font = new Font("Dialog", 0, 12);
        FontMetrics fontmetrics = getFontMetrics(font);
        shiftY = fontmetrics.getAscent() / 2;
    }

    public String getLabel()
    {
        return label;
    }

    public Insets insets()
    {
        if(lin == null)
            lin = new Insets(getFontMetrics(font).getHeight() + 2, 2, 2, 2);
        return lin;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Dimension dimension = size();
        FontMetrics fontmetrics = getFontMetrics(font);
        int i = 0;
        int j = shiftY;
        int k = dimension.width - 1;
        int l = dimension.height - shiftY - 1;
        g.setColor(getBackground());
        g.draw3DRect(i, j, k, l, false);
        g.draw3DRect(++i, ++j, k - 2, l - 2, true);
        int i1 = fontmetrics.getAscent();
        k = fontmetrics.stringWidth(label) + 2;
        l = fontmetrics.getHeight();
        i = i1 - 1;
        j = 0;
        g.fillRect(i, j, k, l);
        g.setColor(getForeground());
        g.setFont(font);
        g.drawString(label, i1, i1);
    }

    public void setLabel(String s)
    {
        label = s;
    }

    private String label;
    Insets lin;
    Font font;
    int shiftY;
}
