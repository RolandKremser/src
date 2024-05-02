// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MultiLineEditor.java

package jclass.beans;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeSupport;

// Referenced classes of package jclass.beans:
//            JCTabEditor

public class MultiLineEditor extends JCTabEditor
    implements FocusListener
{

    public MultiLineEditor()
    {
        nl = "<NL>";
        list = new TextArea();
        setLayout(new BorderLayout());
        add(list, "Center");
        resize(200, 300);
        list.addFocusListener(this);
    }

    public void focusGained(FocusEvent focusevent)
    {
    }

    public void focusLost(FocusEvent focusevent)
    {
        Object obj = super.target;
        setValue(list.getText());
        super.support.firePropertyChange(null, obj, super.target);
    }

    public String getAsText()
    {
        return valueToString(getValue(), nl);
    }

    public String getJavaInitializationString()
    {
        return "";
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
        if(obj instanceof String)
        {
            super.target = stringToValue((String)obj);
        } else
        {
            String s = valueToString(obj, "\n");
            if(!s.equals(list.getText()))
                list.setText(s);
            super.target = obj;
        }
    }

    public Object stringToValue(String s)
    {
        return null;
    }

    public String valueToString(Object obj, String s)
    {
        return "";
    }

    String nl;
    protected TextArea list;
}
