// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCResizeCellListener.java

package jclass.table3;

import java.util.EventListener;

// Referenced classes of package jclass.table3:
//            JCResizeCellEvent

public interface JCResizeCellListener
    extends EventListener
{

    public abstract void resizeCellBegin(JCResizeCellEvent jcresizecellevent);

    public abstract void resizeCellEnd(JCResizeCellEvent jcresizecellevent);
}
