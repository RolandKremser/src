// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCAboutEditor.java

package jclass.beans;

import java.awt.*;
import jclass.util.JCAboutCanvas;

// Referenced classes of package jclass.beans:
//            JCTabEditor

public class JCAboutEditor extends JCTabEditor
{

    public JCAboutEditor()
    {
    }

    public String getAsText()
    {
        return valueToString(getValue());
    }

    public String getJavaInitializationString()
    {
        return "";
    }

    public void init(String s)
    {
        setLayout(new BorderLayout());
        JCAboutCanvas jcaboutcanvas = new JCAboutCanvas(s);
        add("Center", jcaboutcanvas);
    }

    public void init(String s, String s1)
    {
        init(s, s, s1);
    }

    public void init(String s, String s1, String s2)
    {
        setLayout(new BorderLayout());
        JCAboutCanvas jcaboutcanvas = new JCAboutCanvas(s, s1, s2, null, null);
        add("Center", jcaboutcanvas);
    }

    public void paintValue(Graphics g, Rectangle rectangle)
    {
        String s = getAsText();
        if(s == null)
            s = new String("");
        FontMetrics fontmetrics = g.getFontMetrics();
        int i = (rectangle.height - fontmetrics.getAscent()) / 2;
        g.drawString(s, rectangle.x, (rectangle.y + rectangle.height) - i);
    }

    public void setValue(Object obj)
    {
        super.target = obj;
    }

    public Object stringToValue(String s)
    {
        return null;
    }

    public String valueToString(Object obj)
    {
        return obj.toString();
    }
}
