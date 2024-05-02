// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCPaintListener.java

package jclass.table3;

import java.util.EventListener;

// Referenced classes of package jclass.table3:
//            JCPaintEvent

public interface JCPaintListener
    extends EventListener
{

    public abstract void paintBegin(JCPaintEvent jcpaintevent);

    public abstract void paintEnd(JCPaintEvent jcpaintevent);
}
