// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCreateComponentEvent.java

package jclass.table3;

import java.awt.Component;

// Referenced classes of package jclass.table3:
//            TableAWTEvent, Table

public class JCCreateComponentEvent extends TableAWTEvent
{

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public Component getSourceComponent()
    {
        return source;
    }

    public void setComponent(Component component1)
    {
        component = component1;
    }

    public JCCreateComponentEvent(Table table, int i, int j, Component component1)
    {
        super(table, 5001);
        row = i;
        column = j;
        source = component1;
    }

    public static final int CREATE_COMPONENT = 5001;
    int row;
    int column;
    Component source;
    Component component;
}
