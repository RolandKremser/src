// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCFieldListener.java

package jclass.field;

import jclass.bwt.JCEventListener;

// Referenced classes of package jclass.field:
//            JCFieldEvent

public interface JCFieldListener
    extends JCEventListener
{

    public abstract void valueChangedBegin(JCFieldEvent jcfieldevent);

    public abstract void valueChangedEnd(JCFieldEvent jcfieldevent);

    public abstract void stateIsInvalid(JCFieldEvent jcfieldevent);
}
