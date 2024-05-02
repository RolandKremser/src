// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCSpinListener.java

package jclass.field;

import jclass.bwt.JCEventListener;

// Referenced classes of package jclass.field:
//            JCSpinEvent

public interface JCSpinListener
    extends JCEventListener
{

    public abstract void spin(JCSpinEvent jcspinevent);
}
