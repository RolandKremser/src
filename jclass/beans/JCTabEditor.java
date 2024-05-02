// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCTabEditor.java

package jclass.beans;

import java.awt.*;
import java.beans.*;
import javax.swing.JPanel;

public abstract class JCTabEditor extends JPanel
    implements PropertyEditor
{

    public JCTabEditor()
    {
        support = new PropertyChangeSupport(this);
        target = null;
    }

    public void addPropertyChangeListener(PropertyChangeListener propertychangelistener)
    {
        support.addPropertyChangeListener(propertychangelistener);
    }

    public String getAsText()
    {
        return null;
    }

    public Component getCustomEditor()
    {
        return this;
    }

    public String getJavaInitializationString()
    {
        return "JClass Chart Data Source Editor";
    }

    public String[] getTags()
    {
        return null;
    }

    public Object getValue()
    {
        return target;
    }

    public boolean isPaintable()
    {
        return true;
    }

    public void paintValue(Graphics g, Rectangle rectangle)
    {
    }

    public void removePropertyChangeListener(PropertyChangeListener propertychangelistener)
    {
        support.removePropertyChangeListener(propertychangelistener);
    }

    public void setAsText(String s)
        throws IllegalArgumentException
    {
        throw new IllegalArgumentException(s);
    }

    public void setValue(Object obj)
    {
        try
        {
            target = obj;
        }
        catch(Exception exception)
        {
            exception.printStackTrace(System.out);
        }
    }

    public boolean supportsCustomEditor()
    {
        return true;
    }

    protected PropertyChangeSupport support;
    protected Object target;
}
