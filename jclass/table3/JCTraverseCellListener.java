// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCTraverseCellListener.java

package jclass.table3;

import java.util.EventListener;

// Referenced classes of package jclass.table3:
//            JCTraverseCellEvent

public interface JCTraverseCellListener
    extends EventListener
{

    public abstract void traverseCell(JCTraverseCellEvent jctraversecellevent);
}
