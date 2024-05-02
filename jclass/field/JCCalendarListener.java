// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCalendarListener.java

package jclass.field;

import jclass.bwt.JCEventListener;

// Referenced classes of package jclass.field:
//            JCCalendarEvent

public interface JCCalendarListener
    extends JCEventListener
{

    public abstract void dateChanged(JCCalendarEvent jccalendarevent);

    public abstract void dateSelected(JCCalendarEvent jccalendarevent);
}
