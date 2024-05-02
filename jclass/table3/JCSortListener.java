// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCSortListener.java

package jclass.table3;

import java.util.EventListener;

// Referenced classes of package jclass.table3:
//            JCSortEvent

public interface JCSortListener
    extends EventListener
{

    public abstract void sort(JCSortEvent jcsortevent);
}
