// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCScrollEvent.java

package jclass.table3;


// Referenced classes of package jclass.table3:
//            TableAWTEvent, TableScrollbar, JCAdjustmentEvent, Table

public class JCScrollEvent extends TableAWTEvent
{

    public TableScrollbar getScrollbar()
    {
        return scrollbar;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int i)
    {
        value = i;
    }

    public JCAdjustmentEvent getEvent()
    {
        return event;
    }

    public int getDirection()
    {
        return direction;
    }

    public int getType()
    {
        return type;
    }

    public JCScrollEvent(Table table, int i, TableScrollbar tablescrollbar, int j, int k, JCAdjustmentEvent jcadjustmentevent)
    {
        super(table, 5006);
        type = i;
        scrollbar = tablescrollbar;
        direction = j;
        value = k;
        event = jcadjustmentevent;
    }

    public static final int SCROLL = 5006;
    public static final int BEGIN = 1;
    public static final int END = 2;
    TableScrollbar scrollbar;
    int type;
    int value;
    JCAdjustmentEvent event;
    int direction;
}
