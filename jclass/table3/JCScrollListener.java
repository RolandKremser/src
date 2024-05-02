// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCScrollListener.java

package jclass.table3;

import java.util.EventListener;

// Referenced classes of package jclass.table3:
//            JCScrollEvent

public interface JCScrollListener
    extends EventListener
{

    public abstract void scrollBegin(JCScrollEvent jcscrollevent);

    public abstract void scrollEnd(JCScrollEvent jcscrollevent);
}
