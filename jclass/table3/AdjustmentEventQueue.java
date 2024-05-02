// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AdjustmentEventQueue.java

package jclass.table3;

import java.io.Serializable;

// Referenced classes of package jclass.table3:
//            JCAdjustmentEvent

class AdjustmentEventQueue
    implements Serializable
{

    synchronized void setEvent(JCAdjustmentEvent jcadjustmentevent)
    {
        e = jcadjustmentevent;
        hasEvent = true;
    }

    synchronized JCAdjustmentEvent getEvent()
    {
        if(hasEvent)
        {
            hasEvent = false;
            return e;
        } else
        {
            return null;
        }
    }

    AdjustmentEventQueue()
    {
        hasEvent = false;
    }

    JCAdjustmentEvent e;
    boolean hasEvent;
}
