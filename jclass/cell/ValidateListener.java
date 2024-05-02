// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ValidateListener.java

package jclass.cell;

import java.util.EventListener;

// Referenced classes of package jclass.cell:
//            ValidateEvent

public interface ValidateListener
    extends EventListener
{

    public abstract void stateIsInvalid(ValidateEvent validateevent);

    public abstract void valueChangedBegin(ValidateEvent validateevent);

    public abstract void valueChangedEnd(ValidateEvent validateevent);
}
