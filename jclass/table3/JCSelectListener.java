// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCSelectListener.java

package jclass.table3;

import java.util.EventListener;

// Referenced classes of package jclass.table3:
//            JCSelectEvent

public interface JCSelectListener
    extends EventListener
{

    public abstract void selectBegin(JCSelectEvent jcselectevent);

    public abstract void selectEnd(JCSelectEvent jcselectevent);
}
