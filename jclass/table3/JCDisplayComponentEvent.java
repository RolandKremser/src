// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCDisplayComponentEvent.java

package jclass.table3;

import java.awt.Component;

// Referenced classes of package jclass.table3:
//            TableAWTEvent, Table

public class JCDisplayComponentEvent extends TableAWTEvent
{

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public Component getComponent()
    {
        return component;
    }

    public JCDisplayComponentEvent(Table table, int i, int j, Component component1)
    {
        super(table, 5002);
        row = i;
        column = j;
        component = component1;
    }

    public static final int DISPLAY_COMPONENT = 5002;
    int row;
    int column;
    Component component;
}
