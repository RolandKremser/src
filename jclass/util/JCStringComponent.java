// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCStringComponent.java

package jclass.util;

import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JPanel;

// Referenced classes of package jclass.util:
//            JCString

public class JCStringComponent extends JPanel
{

    public JCStringComponent()
    {
        jcs = null;
        prefSize = null;
    }

    public JCStringComponent(String s)
    {
        jcs = null;
        prefSize = null;
        jcs = JCString.parse(this, s);
    }

    public JCStringComponent(JCString jcstring)
    {
        jcs = null;
        prefSize = null;
        jcs = jcstring;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        if(jcs == null)
        {
            return;
        } else
        {
            g.setColor(getForeground());
            Dimension dimension = size();
            Dimension dimension1 = jcs.getSize(this, null);
            Rectangle rectangle = new Rectangle(0, 0, dimension.width, dimension.height);
            jcs.draw(this, g, rectangle, 1);
            return;
        }
    }

    public Dimension preferredSize()
    {
        if(jcs == null)
            return prefSize;
        if(prefSize == null)
        {
            Dimension dimension = jcs.getSize(this, null);
            prefSize = new Dimension(dimension.width + 2, dimension.height + 2);
        }
        return prefSize;
    }

    public void setText(JCString jcstring)
    {
        jcs = jcstring;
        prefSize = null;
    }

    protected JCString jcs;
    protected Dimension prefSize;
}
