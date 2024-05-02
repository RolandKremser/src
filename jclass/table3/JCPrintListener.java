// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCPrintListener.java

package jclass.table3;

import java.util.EventListener;

// Referenced classes of package jclass.table3:
//            JCPrintEvent

public interface JCPrintListener
    extends EventListener
{

    public abstract void printPageHeader(JCPrintEvent jcprintevent);

    public abstract void printPageBody(JCPrintEvent jcprintevent);

    public abstract void printPageFooter(JCPrintEvent jcprintevent);

    public abstract void printEnd(JCPrintEvent jcprintevent);
}
