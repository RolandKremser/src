// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ValidateEvent.java

package jclass.cell;

import java.util.EventObject;

public class ValidateEvent extends EventObject
{

    public ValidateEvent(Object obj)
    {
        super(obj);
    }

    public ValidateEvent(Object obj, Object obj1, Object obj2, boolean flag)
    {
        this(obj);
        old_value = obj1;
        value = obj2;
        beep = false;
        valid = flag;
    }

    public ValidateEvent(Object obj, Object obj1, Object obj2, boolean flag, boolean flag1)
    {
        this(obj, obj1, obj2, flag);
        beep = flag1;
    }

    public boolean getBeep()
    {
        return beep;
    }

    public Object getOldValue()
    {
        return old_value;
    }

    public Object getValue()
    {
        return value;
    }

    public boolean isValid()
    {
        return valid;
    }

    public void setBeep(boolean flag)
    {
        beep = flag;
    }

    public void setValid(boolean flag)
    {
        valid = flag;
    }

    public void setValue(Object obj)
    {
        value = obj;
    }

    protected Object old_value;
    protected Object value;
    protected int position;
    protected boolean beep;
    protected boolean valid;
}
