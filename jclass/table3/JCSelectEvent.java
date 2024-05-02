// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCSelectEvent.java

package jclass.table3;

import java.awt.Event;

// Referenced classes of package jclass.table3:
//            TableAWTEvent, Table

public class JCSelectEvent extends TableAWTEvent
{

    public Event getEvent()
    {
        Event event = new Event(null, 0, null);
        event.modifiers = modifiers;
        event.clickCount = clickCount;
        event.when = when;
        return event;
    }

    public long getWhen()
    {
        return when;
    }

    public int getModifiers()
    {
        return modifiers;
    }

    public int getClickCount()
    {
        return clickCount;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public void setRow(int i)
    {
        row = i;
    }

    public void setColumn(int i)
    {
        column = i;
    }

    public int getStage()
    {
        return stage;
    }

    public String getParam()
    {
        return param;
    }

    public int getStateChange()
    {
        return state_change;
    }

    public boolean getAllowSelection()
    {
        return doit;
    }

    public void setAllowSelection(boolean flag)
    {
        doit = flag;
    }

    public int getType()
    {
        return type;
    }

    public JCSelectEvent(Table table, int i, int j, int k, String s, long l, 
            int i1, int j1)
    {
        super(table, 5007);
        stage = 0;
        doit = true;
        type = i;
        row = j;
        column = k;
        param = s;
        when = l;
        modifiers = i1;
        clickCount = j1;
    }

    public static final int SELECT = 5007;
    public static final int INITIAL = 0;
    public static final int EXTEND = 1;
    public static final int SELECTED = 1;
    public static final int DESELECTED = 2;
    public static final int BEGIN = 1;
    public static final int END = 2;
    int row;
    int column;
    int type;
    int stage;
    int state_change;
    String param;
    boolean doit;
    long when;
    int clickCount;
    int modifiers;
}
