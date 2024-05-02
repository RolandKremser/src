// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCalendarEvent.java

package jclass.field;

import java.util.Calendar;
import jclass.bwt.JCAWTEvent;

public class JCCalendarEvent extends JCAWTEvent
{

    public JCCalendarEvent(Object obj, int i, Calendar calendar1)
    {
        super(obj, i);
        calendar = calendar1;
    }

    public Calendar getCalendar()
    {
        return calendar;
    }

    protected Calendar calendar;
}
