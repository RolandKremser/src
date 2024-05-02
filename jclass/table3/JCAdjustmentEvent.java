// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCAdjustmentEvent.java

package jclass.table3;

import java.awt.AWTEvent;
import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;

public class JCAdjustmentEvent extends AdjustmentEvent
{

    public JCAdjustmentEvent(Adjustable adjustable, int i, int j, int k)
    {
        super(adjustable, i, j, k);
    }

    public JCAdjustmentEvent(AdjustmentEvent adjustmentevent)
    {
        super(adjustmentevent.getAdjustable(), adjustmentevent.getID(), adjustmentevent.getAdjustmentType(), adjustmentevent.getValue());
    }
}
