// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WidgetSeriesValue.java

package jclass.table3;

import java.awt.Component;
import java.awt.Container;
import java.io.Serializable;

class WidgetSeriesValue
    implements Serializable
{

    WidgetSeriesValue(Component component1)
    {
        clones = new Component[0];
        component = component1;
    }

    void destroy()
    {
        finalize();
    }

    protected void finalize()
    {
        try
        {
            if(component != null && component.getParent() != null)
                component.getParent().remove(component);
            for(int i = 0; i < clones.length; i++)
                if(clones[i] != null && clones[i].getParent() != null)
                    clones[i].getParent().remove(clones[i]);

            return;
        }
        catch(Exception _ex)
        {
            return;
        }
    }

    Component component;
    boolean needs_displaycb[];
    Component clones[];
    int row[];
    int column[];
    boolean moved[];
}
