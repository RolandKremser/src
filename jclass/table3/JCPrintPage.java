// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCPrintPreview.java

package jclass.table3;

import java.awt.*;

// Referenced classes of package jclass.table3:
//            JCPrintPreview

class JCPrintPage extends Canvas
{

    JCPrintPage(Dimension dimension)
    {
        preferredDimension = dimension;
    }

    public void setImage(Image image)
    {
        page = image;
        repaint();
    }

    public Dimension getPreferredSize()
    {
        return preferredDimension;
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void paint(Graphics g)
    {
        Dimension dimension = getSize();
        try
        {
            Image image = createImage(dimension.width, dimension.height);
            Graphics g1 = image.getGraphics();
            g1.setColor(Color.gray);
            g1.fillRect(0, 0, dimension.width, dimension.height);
            if(page != null)
            {
                Dimension dimension1 = new Dimension(page.getWidth(this), page.getHeight(this));
                Point point = new Point((dimension.width - dimension1.width) / 2, (dimension.height - dimension1.height) / 2);
                g1.setColor(Color.black);
                g1.drawRect(point.x - 2, point.y - 2, dimension1.width + 2, dimension1.height + 2);
                g1.setColor(Color.white);
                g1.fillRect(point.x - 1, point.y - 1, dimension1.width + 1, dimension1.height + 1);
                g1.drawImage(page, point.x, point.y, this);
                g.drawImage(image, 0, 0, this);
                return;
            }
        }
        catch(Exception _ex) { }
    }

    Image page;
    Dimension preferredDimension;
}
